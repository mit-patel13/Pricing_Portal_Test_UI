package com.pricingPortal.pageObjects;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.pricingPortal.ObjectRepositary.PricingPortalOR;
import com.pricingPortal.browser.Browser;
import com.pricingPortal.browser.Constants;
import com.pricingPortal.helper.Generic;
import com.pricingPortal.helper.Xls_Reader;

	public class UpdateFilePageObject extends Browser 
	{
		private String SheetName;
		private String ScenarioName;
		
		
		public UpdateFilePageObject()
		{
			this.SheetName = Generic.SheetName;
			this.ScenarioName = Generic.ScenarioName;
			
		}

	public boolean verifyFirstStepEnable() {
		
		boolean status= false;
		
		String activeVal=driver.findElement(By.xpath(PricingPortalOR.firststepbox)).getAttribute("class");
		
		if(activeVal.equalsIgnoreCase("active")) 
			status = true; 
		else 
			status = false;
			
	return status;
		
	}
	
	
	public void chooseFile() {
		
	//Generic.enterValue("xpath", PricingPortalOR.chooseFileButtn, System.getProperty("user.dir")+"/src/test/resources/TestData/Sample Price Increase by Location copy 1.xlsx");	
	Generic.enterValue("xpath", PricingPortalOR.chooseFileButtn, Constants.uploaExcelPath);
		
	}
	
	public void selectDate() throws Exception
	{
		Hashtable<String, String>[] dataArray = Generic.getData(xls, Generic.SheetName, Generic.ScenarioName);
		for (int x = 0; x < dataArray.length; x++) 
		{
			
			
			String dateValue = dataArray[x].get("DateVal");
			if(dateValue.equalsIgnoreCase("TODAY"))
			{
				driver.findElement(By.xpath("//span[@aria-label='Toggle calendar']")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//span[contains(text(),'TODAY')]")).click();
			}else
			{
				WebElement cdate= driver.findElement(By.xpath("//input[@class='k-input']"));				
				cdate.clear();
				cdate.sendKeys(dateValue);
			}	
			
		}

	}
	
	
	public boolean verifySelectedDate()
	{
		boolean status=false;
		Hashtable<String, String>[] dataArray = Generic.getData(xls, Generic.SheetName, Generic.ScenarioName);
		for (int x = 0; x < dataArray.length; x++) 
		{
			String dateValue = dataArray[x].get("DateVal");
			String actualdate = (Generic.getElement("xpath", PricingPortalOR.datevaluebyxpath)).getAttribute("aria-valuetext");
			if(dateValue.equalsIgnoreCase("TODAY"))
			{
				
				String dateVal[] = actualdate.split("-");
				for(int i=0;i<dateVal.length;i++)
				{
					if((new Date()).toString().contains(dateVal[i]))
					{
					status = true ;
					}else
					{
						status= false;
						return status;
					}
				}
			}else
			{
				if(actualdate.contains(dateValue))
				{
					status = true;
				}else
					{
					status = false;
					return status;
					}
				
			}	
			
			
		}
		return status;
	}
	
	
	
	public void updateExcelCellValues() throws Exception
	{
		
		Xls_Reader xlsUpload = new Xls_Reader(Constants.uploaExcelPath);
		int rowCount = xlsUpload.getRowCount("Location List");
		
		
		
		
		WebElement location = driver.findElement(By.xpath("//input[@type='text' and @title='Name Box']"));
		
			
		
		location.clear();
		location.sendKeys("O2");
		Thread.sleep(1000);
		location.click();
		location.sendKeys(Keys.ENTER);
		WebElement value = driver.findElement(By.xpath("//div[@data-role='formulainput' and @class='k-spreadsheet-formula-input']"));
		for(int i = 1;i<rowCount;i++)
		{
			value.clear();
			value.sendKeys(String.valueOf(Generic.getRandomNumber(1, 20)));
			value.sendKeys(Keys.ENTER);
		}
	}
	
	
	public void saveChangeSet() throws Exception
	{
		Generic.clickElement("xpath", PricingPortalOR.saveChangeSet);
	}
	
	public int fetchChaageSetRowNumber() throws Exception
	{
		int row = 0;
		Thread.sleep(2000);
		SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
		Date dat = new Date();
		String currentDate = formater.format(dat);
		
		String dateVal = "";
		String changes = "";
		Xls_Reader xlsUpload = new Xls_Reader(Constants.uploaExcelPath);
		int rowCount = xlsUpload.getRowCount("Location List");
		
		List<WebElement> tables = driver.findElements(By.tagName("tbody"));
		if(tables.size()>1)
		{
			
			int rowCountTable = Generic.RowCount(tables.get(1));
			
		 
			
			for(int i=1;i<=rowCountTable;i++)
			{
				int colNumbers = Generic.ColumnCount(tables.get(1), i);
				if(colNumbers>1)
				{
					dateVal = Generic.getCellData(tables.get(1), i, 5);
					System.out.println(dateVal);
					changes = Generic.getCellData(tables.get(1), i, 6);
					String locations[]=changes.split("#");
					if(dateVal.contains(currentDate) && rowCount==locations.length) {
						row = i;
					}
				}else
				{
					return 0;
				}
				
				
			}
			
		}else
		{
			return 0;
		}
		
		return row;
	}
	
	
	public boolean editChangeSet() throws Exception
	{
		boolean status = false;
		Thread.sleep(2000);
		int row = fetchChaageSetRowNumber();
		if(row>0)
		{
			List<WebElement> tables = driver.findElements(By.tagName("tbody"));
			
			int rowCountTable = Generic.RowCount(tables.get(1));
			WebElement editButton = Generic.getChildItem(tables.get(1), row, 2, "i", 0);
			editButton.click();
			updateExcelCellValues();
			saveChangeSet();
			status= true;
		}else
		{
			return false;
		}
		return status;
	}
	
	
	public boolean  deleteChangeSetRecord() throws Exception
	{
		boolean status = false;
		Thread.sleep(2000);
		int row = fetchChaageSetRowNumber();
		if(row>0)
		{
			
			List<WebElement> tables = driver.findElements(By.tagName("tbody"));
			
			int rowCountTable = Generic.RowCount(tables.get(1));
			WebElement deleteButton = Generic.getChildItem(tables.get(1), row, 1, "i", 0);
			deleteButton.click();
			WebElement confrimDelete = driver.findElement(By.xpath("//span[text()='Yes']/.."));
			confrimDelete.click();
			status= true;
		}else
		{
			return false;
		}
		return status;
		
		
	}
	
}
