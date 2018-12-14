package com.pricingPortal.browser;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.pricingPortal.helper.Xls_Reader;
import com.pricingPortal.pageObjects.LoginPageObject;

public class Browser {
	
	public static WebDriver driver;
	public static Properties prop;
	public static String strDriveval;

	//public static Xls_Reader xls = new Xls_Reader("/Users/mit.patel/Documents/workspace/EnterpriseMenuManagement/src/test/resources/TestData/Testdata.xlsx");
	
	//public static Xls_Reader xls = new Xls_Reader("/Users/mit.patel/Documents/workspace/EnterpriseMenuManagement/src/test/resources/TestData/Testdata.xlsx");

	public static Xls_Reader xls = Xls_Reader.forTestData();

	
	public Browser()
	{
		if(!(driver==null)) 
		{
			if(!(driver.toString()==null))
			{
				strDriveval = driver.toString();
				
			}
			else
			{
				strDriveval = "";
			}
			
		}
		if(driver==null || strDriveval.contains("null")) {
			Browser.LoadConfigFile();
			Browser.launchApplication();
			try {
				driver.get(prop.getProperty("applicationURL"));
				//driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				LoginPageObject.loginIntoApplication();
			}catch(Exception e)
			{
				System.out.println(e.getLocalizedMessage());
			}
		}
	}
	
	
	
	
	public static Properties LoadConfigFile()
	{
		try {
			File file = new File(System.getProperty("user.dir")+"/src/main/resources/commonProperties.properties");
			FileInputStream fileinput = new FileInputStream(file);
			prop=new Properties();
			prop.load(fileinput);;
			
			return prop;
		}catch(Exception e)
		{
			throw new RuntimeException("Could not load properties file",e);
		}
	}
	
	public static void launchApplication()
	{
		String browserName =prop.getProperty("browserName");
		
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			driver=new ChromeDriver();	
		}else if(browserName.equalsIgnoreCase("ie"))
		{
			 DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();			  
			 capabilities.setCapability(CapabilityType.BROWSER_NAME, "IE");
			 capabilities.setCapability(InternetExplorerDriver.
			   INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			 driver = new InternetExplorerDriver(); 
			 
		}else if(browserName.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		
		
		
	}
	
	
}
