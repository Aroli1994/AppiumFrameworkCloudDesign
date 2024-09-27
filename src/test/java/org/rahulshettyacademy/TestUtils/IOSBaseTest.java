package org.rahulshettyacademy.TestUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.rahulshettyacademy.pageObjects.ios.HomePage;
import org.rahulshettyacademy.utils.AppiumUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class IOSBaseTest extends AppiumUtils {
	public IOSDriver driver;
	public AppiumDriverLocalService service;
	public HomePage homePage;

	@BeforeClass
	public void configureAppium() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//org//rahulshettyacademy//resources//data.properties");
		prop.load(fis);
		String ipAddress = prop.getProperty("ipAddress");
		String port = prop.getProperty("port");

		service = startAppiumServer(ipAddress, Integer.parseInt(port));

		XCUITestOptions options = new XCUITestOptions();
		options.setDeviceName("iPhone 15 Pro Max");
		options.setApp(System.getProperty("user.dir")
				+ "//src//test//java//org//rahulshettyacademy//resources//UIKitCatalog.app");
		//options.setApp(System.getProperty("user.dir") + "//src//test//java//org//rahulshettyacademy//resources//TestApp 3.app");

		options.setPlatformVersion("17.5");
		options.setWdaLaunchTimeout(Duration.ofSeconds(20));

		// URL url_appium = new URI("http://127.0.0.1:4723").toURL();
		driver = new IOSDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		homePage = new HomePage(driver);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();
	}

}
