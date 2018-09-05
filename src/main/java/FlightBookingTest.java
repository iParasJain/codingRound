import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FlightBookingTest extends Utilities {

	SelInstance selInst;

	@BeforeTest
	public void launchChromeBrowser() {
		selInst = new Utilities();
		selInst.launchChromeBrowser();
	}

	@AfterTest
	public void quitAllBrowsersSession() {
		if (selInst.getWebDriverInstance() != null)
			selInst.getWebDriverInstance().quit();
	}

	@Test
	public void testThatResultsAppearForAOneWayJourney() {

		selInst.navigateURL("https://www.cleartrip.com/");

		if (!selInst.waitForClickableElement(By.id("OneWay"), 10).isSelected())
			selInst.waitForClickableElement(By.id("OneWay"), 10).click();

		selInst.waitForClickableElement(By.id("FromTag"), 10).clear();
		selInst.waitForClickableElement(By.id("FromTag"), 10).sendKeys("Bangalore");
		selInst.waitFor(2000);
		List<WebElement> udID1ElemList = selInst.waitForClickableElement(By.xpath("//*[@id='ui-id-1']"), 20)
				.findElements(By.tagName("a"));
		udID1ElemList.get(0).click();

		selInst.waitForClickableElement(By.id("ToTag"), 10).clear();
		selInst.waitForClickableElement(By.id("ToTag"), 10).sendKeys("Delhi");

		// wait for the auto complete options to appear for the destination

		// select the first item from the destination auto complete list
		Assert.assertTrue(selInst.waitForClickableElement(By.xpath("//*[@id='ui-id-2']"), 20).isDisplayed());
		selInst.waitFor(2000);
		List<WebElement> udID2ElemList = selInst.waitForClickableElement(By.xpath("//*[@id='ui-id-2']"), 20)
				.findElements(By.tagName("a"));
		udID2ElemList.get(0).click();

		selInst.getWebDriverInstance()
				.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")).click();

		// all fields filled in. Now click on search
		selInst.getWebDriverInstance().findElement(By.id("SearchBtn")).click();

		// verify that result appears for the provided journey search
		Assert.assertTrue(selInst.waitForNonClickableElement(By.className("searchSummary"), 20).isDisplayed());

	}

}
