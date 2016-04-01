package org.runnerdave;

import java.util.ResourceBundle;

import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Application to demonstrate use of Selenium with a target website
 * 
 * @author David Jiménez runnerdave-at-gmail.com
 *
 */
public class Context {

	private static ResourceBundle myResources = ResourceBundle.getBundle("ResourceBundle");
	private static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		String browser = myResources.getString("driver.browser");
		switch (browser) {
		case "FF":
			driver = new FirefoxDriver();
			break;
		case "ie":
			driver = new InternetExplorerDriver();
			break;
		case "chrome":
			driver = new ChromeDriver();
			break;
		}

		StopWatch pageLoad = new StopWatch();
		pageLoad.start();
		// Open the Application
		driver.get(myResources.getString("website.url"));
		driver.manage().window().maximize();
		// Wait for the required button
		new WebDriverWait(driver, 10).until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='login-mainForm']/div[3]/button")));

		// Stop the StopWatch
		pageLoad.stop();
		System.out.println("Total Page Load Time: " + pageLoad.getTime() + "milliseconds");

		driver.findElement(By.xpath("//*[@id='login-username']")).sendKeys(myResources.getString("user.email"));
		driver.findElement(By.xpath("//*[@id='login-password']")).sendKeys(myResources.getString("user.password"));
		driver.findElement(By.xpath("//*[@id='login-mainForm']/div[3]/button")).click();

		// start another stop watch
		pageLoad.reset();
		pageLoad.start();
		new WebDriverWait(driver, 10)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='ex-240-0']")));
		driver.findElement(By.xpath("//*[@id='ex-240-0']")).click();

		// Stop the StopWatch
		pageLoad.stop();
		System.out.println("Site structure Load Time: " + pageLoad.getTime() + "milliseconds");

		WebElement connections = driver.findElement(By.className("connectionDataGraph"));

		System.out.println(connections.getText());

		// driver.close();

	}

}
