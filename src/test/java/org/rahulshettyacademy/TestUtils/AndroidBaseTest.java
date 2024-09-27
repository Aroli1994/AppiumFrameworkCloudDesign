package org.rahulshettyacademy.TestUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.rahulshettyacademy.pageObjects.android.FormPage;
import org.rahulshettyacademy.utils.AppiumUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class AndroidBaseTest extends AppiumUtils {
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public FormPage formPage;

	@BeforeClass(alwaysRun = true)
	public void configureAppium() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//org//rahulshettyacademy//resources//data.properties");

		String ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress");

		prop.load(fis);
		//String ipAddress = prop.getProperty("ipAddress");
		String port = prop.getProperty("port");

		service = startAppiumServer(ipAddress, Integer.parseInt(port));

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(prop.getProperty("AndroidDeviceName")); // emulator
		// options.setDeviceName("Android Device"); //real device which is generic name
		options.setChromedriverExecutable(System.getProperty("user.dir")
				+ "//src//test//java//org//rahulshettyacademy//drivers//chromedriver//chromedriver91");
		// options.setApp(System.getProperty("user.dir") +
		// "//src//test//java//org//rahulshettyacademy//resources//General-Store.apk");
		options.setApp(System.getProperty("user.dir")
				+ "//src//test//java//org//rahulshettyacademy//resources//General-Store.apk");

		// URL url_appium = new URI("http://127.0.0.1:4723").toURL();
		driver = new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		formPage = new FormPage(driver);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.quit();
		service.stop();
	}

}
