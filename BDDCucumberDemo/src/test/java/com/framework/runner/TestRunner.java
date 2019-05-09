package com.framework.runner;

import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(strict = true, features = { "src/test/resources/FeatureFiles" }, 
		plugin = {
		"pretty", "html:target/site/cucumber-pretty", "json:target/cucumber.json",
		"com.cucumber.listener.ExtentCucumberFormatter:Reports/extentReport.html" }, tags = {
				"@LoginPageFunctionality" } // ~@Ignore
		, monochrome = true, glue = { "com.framework.stepDefinitions", "com.framework.library" }

)

public class TestRunner {

	@AfterClass
	public static void reportSetup() {
		Reporter.loadXMLConfig(new File("src/main/java/com/framework/library/extent-config.xml"));

		Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
		Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
		Reporter.setSystemInfo("64 Bit", System.getProperty("os.name"));
		Reporter.setSystemInfo("3.10.0", "Selenium");
		Reporter.setSystemInfo("3.3.9", "Maven");
		Reporter.setSystemInfo("1.8.0_66", "Java Version");
		Reporter.setTestRunnerOutput("BGS Test Automation");
	}

}