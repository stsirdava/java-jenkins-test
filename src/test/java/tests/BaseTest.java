package tests;

import org.testng.annotations.BeforeSuite;
import utils.CategoriesWriter;
import utils.EnvironmentWriter;

public class BaseTest {

    private final EnvironmentWriter env = new EnvironmentWriter();
    private final CategoriesWriter cat = new CategoriesWriter();

    @BeforeSuite(alwaysRun = true)
    public void setupAllureMetadata() throws Exception {
        env.createEnvironmentFile();
        cat.createCategories();
    }
}