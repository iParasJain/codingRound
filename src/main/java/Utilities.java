import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.javafx.PlatformUtil;

public class Utilities extends SelInstance {

	private WebDriver driver;

	public WebDriver getWebDriverInstance() {
		return driver;
	}

	public void setWebDriverInstance(WebDriver driver) {
		this.driver = driver;
	}

	public void launchChromeBrowser() {
		setDriverPath();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		this.driver = new ChromeDriver(options);
		setWebDriverInstance(this.driver);

	}

	public WebElement waitForNonClickableElement(By by, int durationInSeconds) {
		WebDriverWait wait = new WebDriverWait(this.driver, durationInSeconds);
		return wait.until(ExpectedConditions.presenceOfElementLocated(by));

	}

	public WebElement waitForElementVisible(WebElement elem, int durationInSeconds) {
		WebDriverWait wait = new WebDriverWait(this.driver, durationInSeconds);
		return wait.until(ExpectedConditions.visibilityOf(elem));

	}

	public WebElement waitForClickableElement(By by, int durationInSeconds) {
		WebDriverWait wait = new WebDriverWait(this.driver, durationInSeconds);
		return wait.until(ExpectedConditions.elementToBeClickable(by));

	}

	public boolean isElementPresent(By by) {
		try {
			this.driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private void setDriverPath() {
		if (PlatformUtil.isMac()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver");
		}
		if (PlatformUtil.isWindows()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		}
		if (PlatformUtil.isLinux()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
		}
	}

	public void switchToIframeByLocator(By by) {

		this.driver.switchTo().frame(this.driver.findElement(by));
		// TODO Auto-generated method stub

	}

	public void navigateURL(String url) {

		this.driver.get(url);
		// TODO Auto-generated method stub

	}

}
