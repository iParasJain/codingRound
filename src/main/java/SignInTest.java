import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SignInTest {

	SelInstance selInst;

	@BeforeTest
	public void launchChromeBrowser() {
		selInst = new Utilities();
		selInst.launchChromeBrowser();
		PageFactory.initElements(selInst.getWebDriverInstance(), this);
	}

	@AfterTest
	public void quitAllBrowsersSession() {
		if (selInst.getWebDriverInstance() != null)
			selInst.getWebDriverInstance().quit();
	}

	@Test
	public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
		selInst.navigateURL("https://www.cleartrip.com/");
		selInst.waitForClickableElement(By.linkText("Your trips"), 10).click();
		selInst.waitForClickableElement(By.id("SignIn"), 10).click();
		selInst.waitFor(1000);
		selInst.switchToIframeByLocator(By.id("modal_window"));
		selInst.waitForClickableElement(By.id("signInButton"), 10).click();
		String errors1 = selInst.waitForNonClickableElement(By.id("errors1"), 10).getText();
		Assert.assertTrue(errors1.contains("There were errors in your submission"));

	}

}
