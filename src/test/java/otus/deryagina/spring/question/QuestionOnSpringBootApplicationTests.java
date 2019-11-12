package otus.deryagina.spring.question;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class QuestionOnSpringBootApplicationTests {

    @Test
    @DisplayName("Test i18n and l10n: all l10n have same keys in bundle")
    void allL10onsHaveSameKeys() {
        ClassLoader classLoader = getClass().getClassLoader();
        String defaultBundleName = null;
        Properties defaultProperties = new Properties();
        Map<String, Properties> mapFileNameAndKeys = new HashMap<>();
        // consider all files in i18n directory
        File folder = null;
        try {
            folder = new File
                    (classLoader.getResource("i18n/").getFile());
        } catch (NullPointerException npe) {
            fail("no i18n / folder");
        }
        List<File> bundleFiles = getBundleFiles(folder);

        for (File currentFile : bundleFiles) {
            FileInputStream currentFileInput = null;
            try {
                currentFileInput = new FileInputStream(currentFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Properties currentProperties = new Properties();
            try {
                currentProperties.load(currentFileInput);
                String currentFileName = currentFile.getName();
                mapFileNameAndKeys.put(currentFileName, currentProperties);
                if (currentFileName.contains("en_US")) {
                    defaultBundleName = currentFileName;
                    defaultProperties = currentProperties;
                }
            } catch (IOException e) {
                fail(e.getMessage());
            }
        }
        checkPropertyKeysEquality(defaultBundleName, defaultProperties, mapFileNameAndKeys);
    }

    private void checkPropertyKeysEquality(String defaultBundleName, Properties defaultProperties, Map<String, Properties> mapFileNameAndKeys) {
        for (String currentFileName :
                mapFileNameAndKeys.keySet()) {
            Properties currentProperties = mapFileNameAndKeys.get(currentFileName);
            if (currentProperties.size() <= defaultProperties.size()) {
                List<String> keysOfDefaultNotContainedInCurrent = getKeysOfFirstNotContainedInSecond(defaultProperties, currentProperties);
                if (!keysOfDefaultNotContainedInCurrent.isEmpty()) {
                    fail("there are  no keys " + keysOfDefaultNotContainedInCurrent.toString() + " in bundle " + currentFileName);
                }
            } else {
                List<String> keysOfCurrentNotContainedInDefault = getKeysOfFirstNotContainedInSecond(currentProperties, defaultProperties);
                fail("there are  no keys " + keysOfCurrentNotContainedInDefault.toString() + " in bundle " + defaultBundleName);

            }
        }
    }

    private List<File> getBundleFiles(File folder) {
        List<File> bundleFiles = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            if (!fileEntry.isDirectory()) {
                bundleFiles.add(fileEntry);
            }
        }
        return bundleFiles;
    }

    private List<String> getKeysOfFirstNotContainedInSecond(Properties firstProperties, Properties secondProperties) {
        List<String> result = new ArrayList<>();
        for (String currentKey : firstProperties.stringPropertyNames()
        ) {
            if (!secondProperties.containsKey(currentKey)) {
                result.add(currentKey);
            }
        }
        return result;
    }

}
