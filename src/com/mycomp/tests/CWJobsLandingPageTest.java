package com.mycomp.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;



public class CWJobsLandingPageTest {
	private static WebDriver driver;
	private IOUtils ioUtils;
	private String baseUrl;	
	//private String controlXpath;

	@Before
	public void setUp() throws Exception {
		System.out.println("Opening Driver");
		driver = new FirefoxDriver();
		
		// Navigate to cwjobs.co.uk
		baseUrl = "http://www.cwjobs.co.uk/";
		driver.get(baseUrl);
		ioUtils = new IOUtils();
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Closing Driver");
		driver.close();
		delay(1000);
	}

	@Test
	public void testJobTypeOptionAndCompanyCount() {
		String controlXpath = "//*[@id=\"more-options-toggle\"]/span[1]";
		ioUtils.input(controlXpath, driver);

		// Assert that the job type drop down contains "Permanent"
		WebElement selectJobTypeElemet = driver.findElement(By.xpath("//*[@id=\"JobType\"]"));
		List<WebElement> options = selectJobTypeElemet.findElements(By.tagName("option"));

		List<String> optionTexts = new ArrayList<>();
		for (WebElement option : options) {
			optionTexts.add(option.getText());			
		}		
		assertTrue("Dropdown With Permanent Option",optionTexts.contains("Permanent"));

		// No Of companies that have listed job ads		
		List<WebElement> elements = driver.findElements(By.className("brand-highlight"));
		String companyCount = elements.get(1).getText();
		System.out.println("No. Of Companies: " + companyCount);
	}
	

	@Test
	public void test2() {
		// manager
		String jobPosControlXpath = "//*[@id=\"keywords\"]";
		ioUtils.input(jobPosControlXpath, "manager", driver);
		// Manchester
		String jobLocControlXpath = "//*[@id=\"location\"]";
		ioUtils.input(jobLocControlXpath, "Manchester", driver);

		String moreOptionControlXpath = "//*[@id=\"more-options-toggle\"]/span[1]";
		ioUtils.input(moreOptionControlXpath, driver);

		//delay while more option expands
		delay(1000);
		
		// Annual Salary
		String salaryControlXpath = "//*[@id=\"salaryButtonAnnual\"]";
		ioUtils.input(salaryControlXpath, driver);

		String reqSalaryControlXpath = "//*[@id=\"salaryRate\"]";
		ioUtils.input(reqSalaryControlXpath, 1, driver);

		// Permanent
		String jobTypeControlXpath = "//*[@id=\"JobType\"]";
		ioUtils.input(jobTypeControlXpath, 1, driver);

		// Employer
		String recruiterControlXpath = "//*[@id=\"recruiterTypeButtonEmployer\"]";
		ioUtils.input(recruiterControlXpath, driver);

		// Search
		String searchBtnControlXpath = "//*[@id=\"search-button\"]";
		ioUtils.input(searchBtnControlXpath, driver);
		
		// To check if we atleast get 2 pages of data
		try {
			WebElement element = driver.findElement(By.xpath("//*[@id=\"more\"]/table/tbody/tr/td[2]/a"));						
			assertEquals("2", element.getText());
		}catch(NoSuchElementException e) {
			//e.printStackTrace();
			fail("Less than 2 pages available in search result.");
		}
		// Assert results page html source		
		assertTrue(driver.getPageSource().contains("SITECATALYST CODE"));

	}

	private void delay(long timeInMiliSeconds) {
		try {
			Thread.sleep(timeInMiliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
