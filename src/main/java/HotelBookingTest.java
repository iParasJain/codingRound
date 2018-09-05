import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HotelBookingTest {

	@FindBy(linkText = "Hotels")
	private WebElement hotelLink;

	@FindBy(id = "Tags")
	private WebElement localityTextBox;

	@FindBy(id = "SearchHotelsButton")
	private WebElement searchButton;

	@FindBy(id = "travellersOnhome")
	private WebElement travellerSelection;

	@FindBy(id = "CheckInDate")
	private WebElement checkInDate;
	@FindBy(id = "CheckOutDate")
	private WebElement checkOutDate;
	@FindBy(xpath = "//*[@class='ui-state-default ui-state-highlight ui-state-active ']")
	private WebElement checkInDateCalendar;

	@FindBy(xpath = "//*[@class='ui-state-default ui-state-active ']")
	private WebElement checkoutDateCalendar;

	@FindBy(xpath = "//*[@class='stickyBarShortlistText shortlistCount']")
	private WebElement shortListCount;
	@FindBy(xpath = "//li[contains(@class,'listItem listUnit clearFix')]")
	private List<WebElement> hotelsList;

	@FindBy(id = "ui-id-1")
	private WebElement landMarkAutocomplete;

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
	public void shouldBeAbleToSearchForHotels() {

		selInst.navigateURL("https://www.cleartrip.com/");
		hotelLink.click();

		localityTextBox.sendKeys("Indiranagar, Bangalore");
		selInst.waitFor(3000);
		List<WebElement> udID1ElemList = selInst.waitForElementVisible(landMarkAutocomplete, 20)
				.findElements(By.tagName("a"));
		udID1ElemList.get(0).click();
		checkInDate.click();
		checkInDateCalendar.click();
		selInst.waitFor(3000);
		checkoutDateCalendar.click();
		new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
		searchButton.click();
		selInst.waitForElementVisible(shortListCount, 20);
		Assert.assertTrue(hotelsList.size() > 0);

	}

}
