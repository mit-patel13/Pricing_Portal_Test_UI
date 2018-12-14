package com.pricingPortal.helper;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.pricingPortal.ObjectRepositary.ConfigurationTabOR;

import cucumber.api.java.it.Date;

public class Test {

	public static void main(String[] args) throws Exception {
		

		
			WebDriver driver = new ChromeDriver();
			driver.get("https://pricing.restsolutions-test.cfadevelop.com/");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.findElement(By.id("okta-signin-username")).sendKeys("mit.patel");
			driver.findElement(By.id("okta-signin-password")).sendKeys("CFADev123");
			driver.findElement(By.id("okta-signin-submit")).click();
			
			driver.findElement(By.xpath(ConfigurationTabOR.configurationTabButtn)).click();
			driver.findElement(By.xpath("//h6[text()='Product Relationship Rules']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath(ConfigurationTabOR.newRuleButton)).click();
			driver.findElement(By.xpath(ConfigurationTabOR.ruleNameField)).click();
			driver.findElement(By.xpath(ConfigurationTabOR.ruleNameField)).sendKeys("test123");
			driver.findElement(By.xpath(ConfigurationTabOR.selectProductField)).sendKeys("Fish Sandwich (160034)"); 
			
			
			
			
			//driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
			
		
		}
	
	
		}


