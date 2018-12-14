package com.pricingPortal.helper;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pricingPortal.browser.Browser;


public class Generic extends Browser {
	
	public static String SheetName;
	public static String ScenarioName;
	
	
	/* ********************************************
	 Function Name :navigateToURL 
	 Created By    : 
	 Modified Date :
	 DesCription   :Navigate to another URL
	 ********************************************** */
	public static void navigateToURL(String url) throws Exception
	
	{
				
		Browser.driver.get(url);
		Thread.sleep(1000);
		try {
			Alert alt = Browser.driver.switchTo().alert();
			alt.accept();
			Browser.driver.get(url);
		}catch(Exception e)
		{
			
		}
		
	}

	
	/* ********************************************
	 Function Name :getRandomNumber 
	 Created By    : 
	 Modified Date :
	 DesCription   : Generate the rndom Number
	 ********************************************** */
	
	public static int getRandomNumber(int min, int max){
	    Random random = new Random();
	    return random.ints(min,(max+1)).findFirst().getAsInt();
	}
	
	
	/* ********************************************
	 Function Name :loadTestData 
	 Created By    : 
	 Modified Date :
	 DesCription   :loding theinput data sheet
	 ********************************************** */
	
	public static void loadTestData(String SheetName1,String ScenarioName1)
	{
		SheetName = SheetName1;
		ScenarioName = ScenarioName1;
	}
	
	
	/* ********************************************
	 Function Name :getData 
	 Created By    : 
	 Modified Date :
	 DesCription   :Read the data from Test Data Excel Sheet 
	 ********************************************** */
	
	public static Hashtable<String,String>[] getData(Xls_Reader xls,String sheetname,String testcaseName){
		
		int testcaseStartrow=1;
		while(!(xls.getCellData(sheetname,testcaseStartrow, 0).trim()).equalsIgnoreCase(testcaseName.trim())){
			testcaseStartrow++;
		}
		int testDataStartrownum=testcaseStartrow+2;
		int rows=0;
		while(!xls.getCellData(sheetname, testDataStartrownum+rows, 0 ).equals("")){
			rows++;
		}		
		int colStartRownum=testcaseStartrow+1;
		int cols=0;
		while(!xls.getCellData(sheetname,colStartRownum, cols).equals("")){
			cols++;
		}
		Hashtable [] data=new Hashtable[rows];
		Hashtable<String,String> table=null;
		int index=0;
		for(int rNum=testDataStartrownum;rNum<testDataStartrownum+rows;rNum++){
			table=new Hashtable<String,String>();
			for(int cNum=0;cNum<cols;cNum++){
				String key=xls.getCellData(sheetname, colStartRownum, cNum);
				String value=xls.getCellData(sheetname, rNum,  cNum);
				
				table.put(key, value);
			}
			data[index]=table;
			index++;
			System.out.println();
		}
		return data;
	}

	
	/* ********************************************
	 Function Name :updateData 
	 Created By    : 
	 Modified Date :
	 DesCription   :Update the data to Excel output sheet
	 ********************************************** */
	public static void updateData(Xls_Reader xlsOut,String sheetname,String testcaseName,String parameterName,String Data)
	{
		int testcaseStartrow=1;
		while(!xlsOut.getCellData(sheetname,testcaseStartrow, 0).equalsIgnoreCase(testcaseName)){
			testcaseStartrow++;
		}
		int testDataStartrownum=testcaseStartrow+2;
		int colStartRownum=testcaseStartrow+1;
		int cols=0;
		while(!xlsOut.getCellData(sheetname,colStartRownum, cols).equalsIgnoreCase(parameterName)){
			cols++;
		}
		xlsOut.setCellData(sheetname, testDataStartrownum, cols, Data);
	}	
	
	
	/* ********************************************
	 Function Name :clickElement 
	 Created By    : 
	 Modified Date :
	 DesCription   :Click on WebElement
	 ********************************************** */
	
	public static boolean clickElement(String locator,String locatorVal) throws InterruptedException
	{
		Thread.sleep(2000);
		boolean status=false;
		WebDriverWait wait = new WebDriverWait(driver,30);
		WebElement element=null;
		switch(locator)
		{
		case "id":
			 wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorVal)));
			//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(locatorVal)));
			element = driver.findElement(By.id(locatorVal));
			break;
		case "xpath":
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorVal)));
			element = driver.findElement(By.xpath(locatorVal));
			break;
		case "className":
			wait.until(ExpectedConditions.elementToBeClickable(By.className(locatorVal)));
			element = driver.findElement(By.className(locatorVal));
			break;
		case "name":
			wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorVal)));
			element = driver.findElement(By.name(locatorVal));
			break;
		
		}
		if(element.isDisplayed() && element.isEnabled())
		{
			element.click();
			status = true;
		}
		else
		{
			status = false;
		}
		return status;
		
	}
	
	
	/* ********************************************
	 Function Name :clickElementUsingActions 
	 Created By    : 
	 Modified Date :
	 DesCription   :Click on WebElement using actions class
	 ********************************************** */
	
	public static boolean clickElementUsingActions(String locator,String locatorVal) throws InterruptedException
	{
		Thread.sleep(2000);
		boolean status=false;
		WebDriverWait wait = new WebDriverWait(driver,30);
		WebElement element=null;
		switch(locator)
		{
		case "id":
			wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorVal)));
			element = driver.findElement(By.id(locatorVal));
			break;
		case "xpath":
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorVal)));
			element = driver.findElement(By.xpath(locatorVal));
			break;
		case "className":
			wait.until(ExpectedConditions.elementToBeClickable(By.className(locatorVal)));
			element = driver.findElement(By.className(locatorVal));
			break;
		case "name":
			wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorVal)));
			element = driver.findElement(By.name(locatorVal));
			break;
		
		}
		if(element.isDisplayed() && element.isEnabled())
		{
			Actions act = new Actions(driver);
			act.moveToElement(element).click().build().perform();
			
			status = true;
		}
		else
		{
			status = false;
		}
		return status;
		
	}
	

	/* ********************************************
	 Function Name :Move To Element
	 Created By    : 
	 Modified Date :
	 DesCription   :Mouse over on the element
	 ********************************************** */
	
	public static boolean moveToElement(String locator,String locatorVal) throws InterruptedException
	{
		Thread.sleep(2000);
		boolean status=false;
		WebDriverWait wait = new WebDriverWait(driver,30);
		WebElement element=null;
		switch(locator)
		{
		case "id":
			wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorVal)));
			element = driver.findElement(By.id(locatorVal));
			break;
		case "xpath":
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorVal)));
			element = driver.findElement(By.xpath(locatorVal));
			break;
		case "className":
			wait.until(ExpectedConditions.elementToBeClickable(By.className(locatorVal)));
			element = driver.findElement(By.className(locatorVal));
			break;
		case "name":
			wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorVal)));
			element = driver.findElement(By.name(locatorVal));
			break;
		
		}
		if(element.isDisplayed() && element.isEnabled())
		{
			Actions act = new Actions(driver);
			act.moveToElement(element).build().perform();
			
			status = true;
		}
		else
		{
			status = false;
		}
		return status;
		
	}
	
	
	/* ********************************************
	 Function Name :Right Click on Element(Context Click)
	 Created By    : 
	 Modified Date :
	 DesCription   :Right click on the element suing action class
	 ********************************************** */
	
	public static boolean rightClickOnElement(String locator,String locatorVal) throws InterruptedException
	{
		Thread.sleep(2000);
		boolean status=false;
		WebDriverWait wait = new WebDriverWait(driver,30);
		WebElement element=null;
		switch(locator)
		{
		case "id":
			wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorVal)));
			element = driver.findElement(By.id(locatorVal));
			break;
		case "xpath":
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorVal)));
			element = driver.findElement(By.xpath(locatorVal));
			break;
		case "className":
			wait.until(ExpectedConditions.elementToBeClickable(By.className(locatorVal)));
			element = driver.findElement(By.className(locatorVal));
			break;
		case "name":
			wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorVal)));
			element = driver.findElement(By.name(locatorVal));
			break;
		
		}
		if(element.isDisplayed() )
		{
			Actions act = new Actions(driver);
			act.contextClick(element).build().perform();
			
			status = true;
		}
		else
		{
			status = false;
		}
		return status;
		
	}
	
	
	/* ********************************************
	 Function Name :clickElementUsingJavaScriptExecutor 
	 Created By    : 
	 Modified Date :
	 DesCription   :Click on WebElement using Java Script Executor
	 ********************************************** */
	
	public static boolean clickElementUsingJavaScriptExecutor(String locator,String locatorVal) throws InterruptedException
	{
		Thread.sleep(2000);
		boolean status=false;
		WebDriverWait wait = new WebDriverWait(driver,30);
		WebElement element=null;
		switch(locator)
		{
		case "id":
			wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorVal)));
			element = driver.findElement(By.id(locatorVal));
			break;
		case "xpath":
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorVal)));
			element = driver.findElement(By.xpath(locatorVal));
			break;
		case "className":
			wait.until(ExpectedConditions.elementToBeClickable(By.className(locatorVal)));
			element = driver.findElement(By.className(locatorVal));
			break;
		case "name":
			wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorVal)));
			element = driver.findElement(By.name(locatorVal));
			break;
		
		}
		if(element.isDisplayed())
		{
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
			
			status = true;
		}
		else
		{
			status = false;
		}
		return status;
		
	}
	
	
	/* ********************************************
	 Function Name :verifyElement 
	 Created By    : 
	 Modified Date :
	 DesCription   :Verifying element presence in webPage 
	 ********************************************** */
	
	public static boolean verifyElement(String locator,String locatorVal)
	{
		boolean status = false;
		WebDriverWait wait = new WebDriverWait(driver,10);
		
		WebElement element=null;
		try {
			switch(locator)
			{
			case "id":
				wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorVal)));
				element = driver.findElement(By.id(locatorVal));
				break;
			case "xpath":
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorVal)));
				element = driver.findElement(By.xpath(locatorVal));
				break;
			case "className":
				wait.until(ExpectedConditions.elementToBeClickable(By.className(locatorVal)));
				element = driver.findElement(By.className(locatorVal));
				break;
			case "name":
				wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorVal)));
				element = driver.findElement(By.name(locatorVal));
				break;
			
			}
			if(element.isDisplayed() && element.isEnabled())
			{
				status = true;
			}
		}catch(Exception e)
		{
			status = false;
		}
				
			
		return status;
		
	}
	
	
	/* ********************************************
	 Function Name :getElement 
	 Created By    : 
	 Modified Date :
	 DesCription   :return the WebElement
	 ********************************************** */
	
	public static WebElement getElement(String locator,String locatorVal)
	{
		WebElement ele=null;
	
		WebDriverWait wait = new WebDriverWait(driver,20);
		WebElement element=null;
		try {
		switch(locator)
		{
		case "id":
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorVal)));
			//wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorVal)));
			element = driver.findElement(By.id(locatorVal));
			break;
		case "xpath":
			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorVal)));
			element = driver.findElement(By.xpath(locatorVal));
			break;
		case "className":
			//wait.until(ExpectedConditions.elementToBeClickable(By.className(locatorVal)));
			element = driver.findElement(By.className(locatorVal));
			break;
		case "name":
			//wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorVal)));
			element = driver.findElement(By.name(locatorVal));
			break;
		
		}
		if(element.isDisplayed() && element.isEnabled())
		{
			ele = element;
		}
		}catch(Exception e)
		{
			ele = null;
		}
					
		return ele;
		
	}
	
	
	/* ********************************************
	 Function Name :getListElements 
	 Created By    : 
	 Modified Date :
	 DesCription   :return the WebElement
	 ********************************************** */
	
	public static List<WebElement> getListElements(String locator,String locatorVal)
	{
		
	
		
		List<WebElement> elements=null;
		try {
		switch(locator)
		{
		case "id":
			
			elements = driver.findElements(By.id(locatorVal));
			break;
		case "xpath":
			
			elements = driver.findElements(By.xpath(locatorVal));
			break;
		case "className":
			
			elements = driver.findElements(By.className(locatorVal));
			break;
		case "name":
			
			elements = driver.findElements(By.name(locatorVal));
			break;
		
		}
		
		}catch(Exception e)
		{
			elements = null;
		}
					
		return elements;
		
	}
	

	/* ********************************************
	 Function Name :enterValue 
	 Created By    : 
	 Modified Date :
	 DesCription   :Enter Value in Text box using SendKeys Method 
	 ********************************************** */
	
	public static boolean enterValue(String locator,String locatorVal,String value)
	{
		boolean status=false;
		WebDriverWait wait = new WebDriverWait(driver,30);
		WebElement element=null;
		switch(locator)
		{
		case "id":
			wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorVal)));
			element = driver.findElement(By.id(locatorVal));
			break;
		case "xpath":
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorVal)));
			element = driver.findElement(By.xpath(locatorVal));
			break;
		case "className":
			wait.until(ExpectedConditions.elementToBeClickable(By.className(locatorVal)));
			element = driver.findElement(By.className(locatorVal));
			break;
		case "name":
			wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorVal)));
			element = driver.findElement(By.name(locatorVal));
			break;
		
		}
		if(element.isDisplayed() && element.isEnabled())
		{
			try {
			element.clear();
			}catch(Exception e)
			{
				
			}
			element.sendKeys(value);
			status = true;
		}
		else
		{
			status = false;
		}
		return status;
		
	}
	
	
	/* ********************************************
	 Function Name :scrollIntoView 
	 Created By    : 
	 Modified Date :
	 DesCription   :Scrolling till element using Java Script Executor
	 ********************************************** */
	
	public static boolean scrollIntoView(String locator,String locatorVal) throws InterruptedException
	{
		Thread.sleep(2000);
		boolean status = false;
		
		WebElement element=null;
		switch(locator)
		{
		case "id":
			
			element = driver.findElement(By.id(locatorVal));
			break;
		case "xpath":
			
			element = driver.findElement(By.xpath(locatorVal));
			break;
		case "className":
			
			element = driver.findElement(By.className(locatorVal));
			break;
		case "name":
		
			element = driver.findElement(By.name(locatorVal));
			break;
		}
		
		JavascriptExecutor je = (JavascriptExecutor) driver;
		// now execute query which actually will scroll until that element is not appeared on page.
		je.executeScript("arguments[0].scrollIntoView(true);",element);
		return status;
	}
	
	
	
	public static void switchToOtherWindow()
	{

		String currentWindowId = driver.getWindowHandle();
		
		Set<String>  allWindows = driver.getWindowHandles();
		
		for(String id:allWindows)
		{
			if(!id.equalsIgnoreCase(currentWindowId))
			{
				driver.switchTo().window(id);
				break;
			}
			
		}
		
	}	
	
	/* ********************************************
	 Function Name :RowCount 
	 Created By    : 
	 Modified Date :
	 DesCription   :Getting the webTable Row Count
	 ********************************************** */
	
	public static int RowCount(WebElement element)
	{
		int row = 0;
		List<WebElement> rows = element.findElements(By.tagName("tr"));
		if(rows.size()>0)
		{
			row = rows.size();
		}
				
		return row;
	}
	
	/* ********************************************
	 Function Name :ColumnCount 
	 Created By    : 
	 Modified Date :
	 DesCription   :Getting the webTable column Count
	 ********************************************** */
	public static int ColumnCount(WebElement element,int rowNumber)
	{
		int col = 0;
		List<WebElement> rows = element.findElements(By.tagName("tr"));
		if(rows.size()>0)
		{
			List<WebElement> cols = rows.get(rowNumber-1).findElements(By.tagName("td"));
			if( cols.size()>0)
			{
				col = cols.size();
			}else
			{
				List<WebElement> cols1 = rows.get(rowNumber-1).findElements(By.tagName("th"));
				if( cols.size()>0)
				{
					col = cols1.size();
				}
				
				
			}
			
		}
				
		return col;
	}
	
	
	/* ********************************************
	 Function Name :getCellData 
	 Created By    : 
	 Modified Date :
	 DesCription   :Getting the values from webTable
	 ********************************************** */
	
	
	public static String getCellData(WebElement element,int rowNumber,int colNumber)
	{
		String cellData = "";
		List<WebElement> rows = element.findElements(By.tagName("tr"));
		if(rows.size()>0)
		{
			List<WebElement> cols = rows.get(rowNumber-1).findElements(By.tagName("td"));
			if(cols.size()>0)
			{
				cellData = cols.get(colNumber-1).getText();
			}
			else
			{
				List<WebElement> cols1 = rows.get(rowNumber-1).findElements(By.tagName("th"));
				if(cols1.size()>0)
				{
					cellData = cols1.get(colNumber-1).getText();
				}
			}
		}
				
		return cellData;
	}
	
	
	/* ********************************************
	 Function Name :getChildItem 
	 Created By    : 
	 Modified Date :
	 DesCription   :Getting the child item from webTable Cell
	 ********************************************** */
	
	public static WebElement getChildItem(WebElement element,int rowNumber,int colNumber,String tagName,int index)
	{
		WebElement childItem = null;
		List<WebElement> rows = element.findElements(By.tagName("tr"));
		if(rows.size()>0)
		{
			List<WebElement> cols = rows.get(rowNumber-1).findElements(By.tagName("td"));
			if(cols.size()>0)
			{
				List<WebElement> childs = cols.get(colNumber-1).findElements(By.tagName(tagName));
				if(childs.size()>0)
				{
					childItem = childs.get(index);
				}
			}else
			{
				List<WebElement> cols1 = rows.get(rowNumber-1).findElements(By.tagName("th"));
				if(cols1.size()>0)
				{
					List<WebElement> childs1 = cols1.get(colNumber-1).findElements(By.tagName(tagName));
					if(childs1.size()>0)
					{
						childItem = childs1.get(index);
					}
				}
			}
		}
				
		return childItem;
	}
	
	
	/* ********************************************
	 Function Name :getCellAsElement 
	 Created By    : 
	 Modified Date :
	 DesCription   :Getting the column as webElement from webTable Cell
	 ********************************************** */
	
	public static WebElement getCellAsElement(WebElement element,int rowNumber,int colNumber)
	{
		WebElement childItem = null;
		List<WebElement> rows = element.findElements(By.tagName("tr"));
		if(rows.size()>0)
		{
			List<WebElement> cols = rows.get(rowNumber-1).findElements(By.tagName("td"));
			if(cols.size()>0)
			{
				childItem = cols.get(colNumber-1);
			}
			else
			{
				List<WebElement> cols1 = rows.get(rowNumber-1).findElements(By.tagName("th"));
				if(cols1.size()>0)
				{
					childItem = cols1.get(colNumber-1);
				}
			}
		}
			
		return childItem;
	}
	
	
	
	
	/* ********************************************
	 Function Name :getRowWithCellText 
	 Created By    : 
	 Modified Date :
	 DesCription   :fetch row number using text 
	 ********************************************** */
		
	public static int getRowWithCellText(WebElement element,String CellValue)
	{
		int row = 0;
		List<WebElement> rows = element.findElements(By.tagName("tr"));
		if(rows.size()>0)
		{
			parent:
			for(int i=0;i<rows.size();i++)
			{
				List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
				if(cols.size()>0)
				{
					for(int k=0;k<cols.size();k++)
					{
						String value = cols.get(k).getText();
						if(CellValue.equalsIgnoreCase(value))
						{
							row = i+1;
							break parent;
						}
					}
				}
				else
				{
					List<WebElement> cols1 = rows.get(i).findElements(By.tagName("th"));
					if(cols1.size()>0)
					{
						for(int k=0;k<cols1.size();k++)
						{
							String value = cols1.get(k).getText();
							if(CellValue.equalsIgnoreCase(value))
							{
								row = i+1;
								break parent;
							}
						}
					}
				}
			}
			
		}
		
		return row;
	}
	
	
	
	
}


