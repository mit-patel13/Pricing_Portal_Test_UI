package com.pricingPortal.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.pricingPortal.ObjectRepositary.PricingPortalOR;
import com.pricingPortal.ObjectRepositary.ProductsTabOR;
import com.pricingPortal.browser.Browser;
import com.pricingPortal.helper.Generic;



public class ProductsPageObject extends Browser {

	private String SheetName;
	private String ScenarioName;
	public ProductsPageObject()
	{
		this.SheetName = Generic.SheetName;
		this.ScenarioName = Generic.ScenarioName;
	}
	
	
	
	public void addProductsWithPinDetails(String pin,String name) throws Exception 
	{
	
	
	Generic.clickElement("xpath", ProductsTabOR.enterPIN);
	Generic.enterValue("xpath", ProductsTabOR.enterPIN, pin);
	
	Generic.clickElement("xpath", ProductsTabOR.enterName);
	Generic.enterValue("xpath", ProductsTabOR.enterName, name);
	
	Generic.clickElement("xpath", ProductsTabOR.activeInFusionBox);
	
	WebElement statusListBox = Generic.getElement("xpath", ProductsTabOR.statusDropdown);
	Select status = new Select(statusListBox);
	status.selectByVisibleText("Active");
	
	Generic.clickElement("xpath", ProductsTabOR.cateringCheckBox);
	
	Thread.sleep(2000);
	
	}
	
	public void addProductsWithoutPinDetails(String name) throws Exception 
	{
	
	
	Generic.clickElement("xpath", ProductsTabOR.enterName);
	Generic.enterValue("xpath", ProductsTabOR.enterName, name);
	
	Generic.clickElement("xpath", ProductsTabOR.activeInFusionBox);
	
	WebElement statusListBox = Generic.getElement("xpath", ProductsTabOR.statusDropdown);
	Select status = new Select(statusListBox);
	status.selectByVisibleText("Active");
	
	Generic.clickElement("xpath", ProductsTabOR.cateringCheckBox);
	
	Thread.sleep(2000);
	
	WebElement categoryBox = Generic.getElement("xpath", ProductsTabOR.categoryDropdown);
	Select category = new Select(categoryBox);
	category.selectByVisibleText("Breakfast Items");
	
	WebElement subCategoryBox = Generic.getElement("xpath", ProductsTabOR.subCategoryDropdown);
	Select subCategory = new Select(subCategoryBox);
	subCategory.selectByVisibleText("Bagels");
	
	WebElement groupBox = Generic.getElement("xpath", ProductsTabOR.groupDropdown);
	Select grouping = new Select(groupBox);
	grouping.selectByVisibleText("Bagel w/ Grilled Chicken");
	
	WebElement subGroupBox = Generic.getElement("xpath", ProductsTabOR.subGroupDropdown);
	Select subGrouping = new Select(subGroupBox);
	subGrouping.selectByVisibleText("Bagel w/ Grilled & Egg White");
	
	}
	
	
	public void editProductsWithoutPinDetails(String name) throws Exception 
	{
	
	
	Generic.clickElement("xpath", ProductsTabOR.enterName);
	Generic.enterValue("xpath", ProductsTabOR.enterName, name);
	
	
	
	WebElement statusListBox = Generic.getElement("xpath", ProductsTabOR.statusDropdown);
	Select status = new Select(statusListBox);
	status.selectByVisibleText("Active");
	
	
	
	Thread.sleep(2000);
	
	WebElement categoryBox = Generic.getElement("xpath", ProductsTabOR.categoryDropdown);
	Select category = new Select(categoryBox);
	category.selectByVisibleText("Beverages");
	
	WebElement subCategoryBox = Generic.getElement("xpath", ProductsTabOR.subCategoryDropdown);
	Select subCategory = new Select(subCategoryBox);
	subCategory.selectByVisibleText("Soft Drinks");
	
	WebElement groupBox = Generic.getElement("xpath", ProductsTabOR.groupDropdown);
	Select grouping = new Select(groupBox);
	grouping.selectByVisibleText("Soft Drink");
	
	WebElement subGroupBox = Generic.getElement("xpath", ProductsTabOR.subGroupDropdown);
	Select subGrouping = new Select(subGroupBox);
	subGrouping.selectByVisibleText("Canned Drink");
	
	}
	
	
	public boolean verifySubmitbuttonEnable() {
			
			boolean status= false;
			
			String activeVal=driver.findElement(By.xpath(ProductsTabOR.submitButton)).getAttribute("ng-reflect-disabled");
			
			if(activeVal.equalsIgnoreCase("false")) 
				status = true; 
				
			else 
				status = false;
		
			System.out.println(status);
			
		return status;
			
	}
	
	/*public void addProductsWithoutPinDetails() throws Exception 
	{
	
	
	Generic.clickElement("xpath", ProductsTabOR.enterName);
	Generic.enterValue("xpath", ProductsTabOR.enterName, "Test Product 9");
	
	Generic.clickElement("xpath", ProductsTabOR.activeInFusionBox);
	
	WebElement statusListBox = Generic.getElement("xpath", ProductsTabOR.statusDropdown);
	Select status = new Select(statusListBox);
	status.selectByVisibleText("Active");
	
	Generic.clickElement("xpath", ProductsTabOR.cateringCheckBox);
	
	WebElement categoryBox = Generic.getElement("xpath", ProductsTabOR.categoryDropdown);
	Select category = new Select(categoryBox);
	category.selectByVisibleText("Breakfast Items");
	
	WebElement subCategoryBox = Generic.getElement("xpath", ProductsTabOR.subCategoryDropdown);
	Select subCategory = new Select(subCategoryBox);
	subCategory.selectByVisibleText("Bagels");
	
	WebElement groupBox = Generic.getElement("xpath", ProductsTabOR.groupDropdown);
	Select grouping = new Select(groupBox);
	grouping.selectByVisibleText("Bagel w/ Grilled Chicken");
	
	WebElement subGroupBox = Generic.getElement("xpath", ProductsTabOR.subGroupDropdown);
	Select subGrouping = new Select(subGroupBox);
	subGrouping.selectByVisibleText("Bagel w/ Grilled & Egg White");
	
	Generic.clickElement("xpath", ProductsTabOR.submitButton);
	
	
	}*/
	
	
	public boolean verifyCreatedProduct(String pin,String name) throws Exception {
		
		boolean status= false;
		Thread.sleep(3000);
		List<WebElement> searchBoxs = driver.findElements(By.xpath(ProductsTabOR.searchPinOrNameBox));
	
		searchBoxs.get(2).sendKeys(name);
		
		status = Generic.verifyElement("xpath", "//td[text()='"+pin+"']");
		if(!status)
			return false;
		
		status = Generic.verifyElement("xpath", "//td[text()='"+name+"']");
		
		return status;
		
		
	}
	
	
	public boolean verifyCreatedProductWithoutPin(String name) throws Exception {
		
		boolean status= false;
		Thread.sleep(3000);
		List<WebElement> searchBoxs = driver.findElements(By.xpath(ProductsTabOR.searchPinOrNameBox));
	
		searchBoxs.get(2).sendKeys(name);
		
	
		status = Generic.verifyElement("xpath", "//td[text()='"+name+"']");
		
		return status;
		
		
	}
	
	
	public boolean verifyEditedProductWithoutPin(String name) throws Exception {
		
		boolean status= false;
		Thread.sleep(3000);
		
		status = Generic.verifyElement("xpath", ProductsTabOR.verifyEditProduct);
		
		return status;
		
		
	}
	
	public void deleteProduct() throws Exception
	{
		List<WebElement> deletebtns = driver.findElements(By.xpath(ProductsTabOR.deletebutton));
		if(deletebtns.size()>1)
			deletebtns.get(1).click();
		else
			deletebtns.get(0).click();
		
		Generic.clickElement("xpath", ProductsTabOR.confirmDelete);
		
	}
	
	
	
}
