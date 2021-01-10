package PageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage {
private static final Logger logger = LogManager.getLogger(SignInPage.class);
    WebDriver driver;
    
    private By Singin = By.xpath("//a[@title='Log in to your customer account']");
    private By email_Box = By.id("email");
    private By pass_Box = By.id("passwd");
    private By submit_Button = By.id("SubmitLogin");
    
    public SignInPage(WebDriver driver) {
    	this.driver=driver;
    }
    public void ClickOnSingInButton()
    {
       driver.findElement(Singin).click();
       logger.info("Singin Button Clicked");
    }
    public void EnterCredintial(String mail ,String pass)throws Exception
    {
    	driver.findElement(email_Box).sendKeys(mail);
    	Thread.sleep(4000);
    	driver.findElement(pass_Box).sendKeys(pass);
    	Thread.sleep(4000);
    	driver.findElement(submit_Button).click();
    	Thread.sleep(4000);
    }
    
}
