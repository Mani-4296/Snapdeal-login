package com.snapdeal.login;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class LoginValidation {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.snapdeal.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));

		//finding sign-in button and enabling action class to clickandhold
		WebElement signin = driver.findElement(By.className("accountUserName"));
		Actions act = new Actions(driver);
		act.clickAndHold(signin).build().perform();

		//finding login cta and moving to element with action class
		WebElement login = driver.findElement(By.xpath("//span[@class='accountBtn btn rippleWhite']"));
		act.moveToElement(login);
		login.click();

		//switching to login iframe
		driver.switchTo().frame("loginIframe");

		//entering user email address
		WebElement email = driver.findElement(By.xpath("//input[@placeholder='Mobile Number/ Email']"));
		email.sendKeys("testusernew416@gmail.com");

		//clicking on continue CTA
		WebElement cta = driver.findElement(By.xpath("//button[@id='checkUser']"));
		cta.click();
		
		// adding sleep command to enter the code from email, no option to enter password only code is accepted on site
		Thread.sleep(3000);

		// locating the username of the account if signed-in and printing it
		WebElement name = driver.findElement(By.xpath("//span[text()='TestUser']"));
		String username = name.getText();
		System.out.println(username);

		//validating if user is not empty user is signed in
		if (!username.isEmpty()) {
			System.out.println("Successfully logged in to: " + username);
		} else {
			System.out.println("Account not signed-in");
		}
	}

}