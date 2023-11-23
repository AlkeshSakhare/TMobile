package demo;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Filterme {
	public static WebDriver driver;
	public static JavascriptExecutor js;

	public static void main(String... args) {
		try {
			String url = "https://www.t-mobile.com/tablets";
			driver = new ChromeDriver();
			js = (JavascriptExecutor) driver;
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1L));
			driver.get(url);
			Thread.sleep(5000);

			selectFilter("Brands", "Apple", "Samsung", "TCL");
//			selectFilter("Brands", "TCL");
//			selectFilter("Deals", "New", "Special offer");
//			selectFilter("Operating System", "iPadOS", "Android");
//			selectFilter("Brands", "all");
//			selectFilter("Deals", "all");
//			selectFilter("Operating System", "all");

			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}

	private static void selectFilter(String filterType, String... items) {
		try {
			String filterTypeLocator = "//legend[contains(.,'" + filterType + "')]";
			// System.out.println(filterTypeLocator);
			click(driver.findElement(By.xpath(filterTypeLocator)));
			Thread.sleep(3000);
			if (items[0].equalsIgnoreCase("all")) {
				List<WebElement> allElements = driver
						.findElements(By.xpath("//span[@class='mat-checkbox-label']/span"));
				for (int i = 0; i < allElements.size() / 2; i++) {
					filterTypeLocator = "(//span[@class='mat-checkbox-label']/span)[" + (i + 1) * 2 + "]";
					// System.out.println(filterTypeLocator);
					click(driver.findElement(By.xpath(filterTypeLocator)));
					Thread.sleep(2000);
				}
			} else {
				for (int i = 0; i < items.length; i++) {
					String locator = "//span[@class='mat-checkbox-label']/span[contains(text(),'" + items[i] + "')]";
					WebElement element = driver.findElement(By.xpath(locator));
					click(element);
					Thread.sleep(2000);
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void click(WebElement element) {
		js.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", element);
		js.executeScript("arguments[0].click();", element);
	}

}
