package com.makemytrip.runner;

import java.util.Properties;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.makemytrip.baseclass.BaseClass;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.github.bonigarcia.wdm.WebDriverManager;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resource/com/makemytrip/feature/BookAFlight.feature", 
				  glue = "com\\makemytrip\\stepdefinition", 
				  plugin = {"json:target/cucumber.json"},
				  monochrome = true, 
				  strict = true, 
				  dryRun = false
)

public class RunWithChrome extends BaseClass {
	public static Properties prop;
	public static WebDriver driver;

	@BeforeClass
	public static void setUp() throws Throwable {
		prop=readPropertyFile();
		String browser = prop.getProperty("browserName");
		driver = BaseClass.getBrowser(browser);
		setExtentReport();
	}

	@AfterClass
	public static void report() {
		extent.flush();
//		driver.quit();
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("https://www.makemytrip.com");
	}
}
