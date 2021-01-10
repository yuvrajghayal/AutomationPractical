package Core;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class WebDriverFactory {

	 private static final Logger logger = LogManager.getLogger(WebDriverFactory.class);
	 private static WebDriver driver;
	 
	 public static WebDriver getWebDriverForBrowser(String browser) throws Exception{
		 
		 System.setProperty("webdriver.chrome.driver","E:\\eclipse\\chromedriver.exe");
		switch(browser.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			logger.info("Chrome Browser invoked");
			break;
		case "firefox":
			driver = new FirefoxDriver();
			logger.info("FireFox Browser Invoked");
			break;
		case "headless":
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("window-size=1200*600");
			driver = new ChromeDriver(options);
			logger.info("Headless Chrome Browser Invoked");
			break;
		default:
			logger.fatal("no such Browser is implemented.Browser Name sent:" + browser);
			throw new Exception("no such Browser is implemented.Browser Name sent:" + browser);
			
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		logger.info("Driver Maximized and implicit timeout set 20 secounds");
		return driver;
		 
	 }
	 public static void navigateToTheUrl(String url) {
		 driver.get(url);
		 logger.info("Browser navigated to the Url" + url);
	 }
	 public static void quitDriver() {
		 driver.quit();
		 logger.info("Driver Closed");
	 }
	 public static void switchBrowserToTab() {
		 WebDriverWait wait = new WebDriverWait(driver,20);
		 wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		 
		 // after Click on product new Tab will open
		 
		 Set<String> handles = driver.getWindowHandles();
		 logger.info("list of Windows Found:" + handles.size());
		 logger.info("Windows Handles:" + handles.toString());
		 Iterator <String> it = handles.iterator();
		 String original = it.next();
		 String nextTab = it.next();
		 driver.switchTo().window(nextTab);
		 logger.info("Switched to the new window");
				 	
	 }
	 public static void switchToOriginalTab() {
		 Set<String> handles = driver.getWindowHandles();
		 logger.info("List of Windows found:" + handles.size());
		 logger.info("Windows Handles:" + handles.toString());
		 Iterator<String> it = handles.iterator();
		 String original = it.next();
		 driver.switchTo().window(original);
		 logger.info("Switched to Original Tab");
	 }
	 public static String getBrowserName()
	 {
		 String browserDefault = "chrome";
		 String browserSentFromCmd = System.getProperty("browser");
		 if(browserSentFromCmd==null)
		 {
			 return browserDefault;
		 }else {
			 return browserSentFromCmd;
		 }
	 }
}
