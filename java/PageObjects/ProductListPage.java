package PageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductListPage {

	private static final Logger logger = LogManager.getLogger(ProductListPage.class);
	WebDriver driver;
	
	private By tshartclk = By.xpath("//a[@title='T-shirts']");
	private By FadedShortTshirt = By.xpath("//a[@class='quick-view']");
	private By SendToFriend = By.xpath("//a[@id='send_friend_button']");
	private By FrndName = By.xpath("//label[@for='friend_name']");
	private By FrndMail = By.xpath("//label[@for='friend_email']");
	private By SendButton =By.xpath("//button[@id='sendEmail']");	
	public ProductListPage(WebDriver driver) {
		this.driver=driver;
		
	}
	public void ClickOnTShirtsButton()
	{
		driver.findElement(tshartclk).click();
		logger.info("Clicked on T-Shirts Menu");
	}
	public void ClickOnFadedShortMenu()
	{
		driver.findElement(FadedShortTshirt).click();
		logger.info("Clicked on Faded Short T-Shirts Menu");
	}
	public void SendToFriend(String Nme , String mail)
	{
		driver.findElement(SendToFriend).click();
		driver.findElement(FrndName).sendKeys(Nme);
		driver.findElement(FrndMail).sendKeys(mail);
		driver.findElement(SendButton).click();
		logger.info("Shared With Friends");
	}
	
}
