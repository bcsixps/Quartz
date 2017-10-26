package com.panshuai.rss;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Test3 {
	static WebDriver driver ;
	
	public static void main(String[] args) {
		//beta 2 version
//		System.setProperty("webdriver.firefox.marionette", "./geckodriver.exe");
		
		//beta 1 version
//		System.setProperty("webdriver.edge.driver", "./MicrosoftWebDriver.exe");
		
		driver = new ChromeDriver();
		driver.get("http://www.facebook.com");
		driver.quit();
	}
}