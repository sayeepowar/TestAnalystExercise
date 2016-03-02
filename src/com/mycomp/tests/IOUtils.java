package com.mycomp.tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class IOUtils {
	/**
	 * Input text into the element
	 * @param ar1
	 * @param ar2
	 * @param dr
	 */
	public void input(String ar1,String ar2,WebDriver dr)
	{
		dr.findElement(By.xpath(ar1)).clear();
		dr.findElement(By.xpath(ar1)).sendKeys(ar2);
	}
	/**
	 * Click
	 * @param ar1
	 * @param dr
	 */
	public void input(String ar1,WebDriver dr)
	{
		dr.findElement(By.xpath(ar1)).click();
	}
	/**
	 * Dropdown/Multiselection box
	 * @param ar1
	 * @param ar2
	 * @param dr
	 */
	public void input(String ar1,int ar2,WebDriver dr)
	{
	 Select dd;
	 dd= new Select(dr.findElement(By.xpath(ar1)));
	 dd.selectByIndex(ar2);
	}
}

