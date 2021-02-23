package com.gbhtest;
import static org.junit.Assert.assertTrue;
import java.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GBHPageTest {
	
	//Global variables declaration
	private WebDriver driver;
	String url;

	//Setting up global variables and WebDriver
	@Before
	public void setUp() {
		
		url = "https://gbh.com.do/";
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.get(url);
	}
	
	//Test execution - validating if 
	@Test
	public void testGBHPage() {
		
		 assertTrue(visitURLs());
	}
	
	//Function to visit all URLs of the page's menu.
	public boolean visitURLs() {
		
		List<WebElement> Elements = driver.findElements(By.cssSelector("nav a"));
		
		List<String> links = new ArrayList<String>();
		 
		//Adding all href of the a tags attributes to an Array of links, excluding the # links.
		for(WebElement aTag: Elements) {
			
			if(!aTag.getAttribute("href").equals(url + "#")) {
				
				links.add(aTag.getAttribute("href"));
			}
		}
		
		//Using Hashset to remove duplicated links.
		LinkedHashSet<String> hashSet = new LinkedHashSet<String>(links);
		
		ArrayList<String> uniqueLinks = new ArrayList<String>(hashSet);
		
		//Navigating tru all links of the ArrayList
		for(String uniqueLink: uniqueLinks) {
			
			driver.navigate().to(uniqueLink);
			
			//Fetching the first article tag of the page
			WebElement articleTag = driver.findElement(By.tagName("article"));
			
			//Validating that the article loads some content
			if(!"".equals(articleTag.getText())) {
				
				System.out.println(driver.getTitle() + " - Page successfully loaded!");
				
				driver.navigate().back();
			}
			else {
				
				System.out.println(driver.getTitle() + " - Error, Page not loaded!");
				
				return false;
			}
		}
		
		System.out.println("All pages successfully loaded!");
		
		return true;
	}
	
	//Closing WebDriver/Browser.
	@After
	public void tearDown() {
		
		driver.quit();
		
	}

}
