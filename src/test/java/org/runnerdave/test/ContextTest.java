package org.runnerdave.test;

import java.util.ResourceBundle;

import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import junit.framework.TestCase;

public class ContextTest extends TestCase {

	private ResourceBundle myResources = ResourceBundle.getBundle("ResourceBundle");
	private WebDriver driver;

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public ContextTest(String testName) {
		super(testName);
		//set browser
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
	}

	/**
	 * Rigourous Test :-)
	 * @throws InterruptedException 
	 */
	public void testLogin() throws InterruptedException {
		
		
		driver.get(myResources.getString("website.url"));
		driver.manage().window().maximize();
		
		driver.get(myResources.getString("website.url"));
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//*[@id='login-username']")).sendKeys(myResources.getString("user.email"));
		driver.findElement(By.xpath("//*[@id='login-password']")).sendKeys(myResources.getString("user.password"));
		driver.findElement(By.xpath("//*[@id='login-mainForm']/div[3]/button")).click();
		
		//WebElement headerTitle = driver.findElement(By.xpath("//*[@id='header']/h1/a/span"));
		WebElement headerHref = driver.findElement(By.xpath("html/body/div[1]/div[1]/h1/a"));
		headerHref.click();
		WebElement headerTitle = driver.findElement(By.xpath("html/body/div[1]/div[1]/h1/a/span"));
		Thread.sleep(5000);
		
		System.out.println("Header Title : " + headerTitle.getTagName() + ", "+ headerTitle.getText());
		
		String headingTitleText = driver.findElement(By.xpath("//*[@id='header']/h1/a/span")).getText();		
		
		assertTrue(headingTitleText.contains("TERMINALFOUR"));
	}

	@After
	public void tearDown() throws Exception {
		Thread.sleep(5000);
		driver.close();
	}
}
