package utils;

import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvironmentWriter {

    @BeforeSuite(alwaysRun = true)
    public void createEnvironmentFile() throws IOException {

        File dir = new File("target/allure-results");
        dir.mkdirs();

        Properties properties = new Properties();

        properties.setProperty("Browser", "Chrome");
        properties.setProperty("Environment", System.getProperty("env", "QA"));
        properties.setProperty("Branch", System.getProperty("branch", "main"));
        properties.setProperty("Java", System.getProperty("java.version"));

        try (FileOutputStream out =
                     new FileOutputStream(
                             "target/allure-results/environment.properties"
                     )) {
            properties.store(out, null);
        }
    }
}