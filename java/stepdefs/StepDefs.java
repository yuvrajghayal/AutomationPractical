package stepdefs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import Core.WebDriverFactory;
import PageObjects.ProductListPage;
import PageObjects.SignInPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs {

private static final Logger logger = LogManager.getLogger(StepDefs.class);
	
	WebDriver driver;
	String base_url="http://Automationpractice.com";
	int implicit_wait_timeout_in_sec = 20;
	Scenario scn;
	ProductListPage productListPage;
	SignInPage signInPage;
	
	
@Before
	
	public void setUp(Scenario scn) throws Exception
	{
		this.scn = scn;
		
		String browserName = WebDriverFactory.getBrowserName();
		driver = WebDriverFactory.getWebDriverForBrowser(browserName);
		logger.info("Browser Invoked");	
		productListPage = new ProductListPage(driver);
		signInPage = new SignInPage(driver);
}
@After(order=1)
public void cleanUp()
{
	WebDriverFactory.quitDriver();
	scn.log("Browser Closed");
}
 @After(order=2)
    public void takeScreenShot(Scenario s) {
        if (s.isFailed()) {
            TakesScreenshot scrnShot = (TakesScreenshot)driver;
            byte[] data = scrnShot.getScreenshotAs(OutputType.BYTES);
            scn.attach(data, "image/png","Failed Step Name: " + s.getName());
        }else
        {
            scn.log("Test case is passed, no screen shot captured");
        }
 
        
}
 @Given("User Navigate To The application Url")
 public void User_Navigate_To_The_application_Url()
 {
	 WebDriverFactory.navigateToTheUrl(base_url);
	 scn.log("Browser Navigated To the Url : " + base_url);
 }
 @When("User is able to SignIn the application")
 public void User_is_able_to_SignIn_the_application() {
	 signInPage.ClickOnSingInButton();
 }
 @And("User enter mail as{String}and password as{String} and click submit button")
 public void User_enter_mail_as_and_password_and_click_submit_button() throws Exception{
	 signInPage.EnterCredintial("yuvrajghayal@gmail.com", "Yuvraj@1");
 }
 @Then("User logged in to the account")
 public void User_logged_in_to_the_account() {
	 signInPage.ClickOnSingInButton();
 }
}
