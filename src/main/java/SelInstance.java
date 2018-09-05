import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class SelInstance {

	public abstract WebDriver getWebDriverInstance();

	public abstract void launchChromeBrowser();

	public abstract void navigateURL(String url);

	public void waitFor(int durationInMilliSeconds) {
		try {
			Thread.sleep(durationInMilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public abstract WebElement waitForClickableElement(By by, int durationInSeconds);

	public abstract WebElement waitForNonClickableElement(By by, int durationInSeconds);

	public abstract WebElement waitForElementVisible(WebElement elem, int durationInSeconds);

	public abstract boolean isElementPresent(By by);

	public abstract void switchToIframeByLocator(By by);

}
