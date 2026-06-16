package utils;

import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CategoriesWriter {

    @BeforeSuite(alwaysRun = true)
    public void createCategories() throws IOException {

        File dir = new File("target/allure-results");
        dir.mkdirs();

        String json =
                """
                [
                  {
                    "name": "Infrastructure Issues",
                    "matchedStatuses": ["broken"],
                    "messageRegex": ".*timeout.*"
                  },
                  {
                    "name": "Assertion Failures",
                    "matchedStatuses": ["failed"]
                  },
                  {
                    "name": "Skipped Tests",
                    "matchedStatuses": ["skipped"]
                  }
                ]
                """;

        try (FileWriter writer =
                     new FileWriter(
                             "target/allure-results/categories.json"
                     )) {
            writer.write(json);
        }
    }
}