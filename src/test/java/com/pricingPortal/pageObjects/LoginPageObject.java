package com.pricingPortal.pageObjects;

import org.openqa.selenium.By;

import com.pricingPortal.ObjectRepositary.LoginOR;
import com.pricingPortal.browser.Browser;
import com.pricingPortal.browser.Constants;
import com.pricingPortal.helper.Generic;

public class LoginPageObject extends Browser {
	
	
	public static void loginIntoApplication() throws Exception
	{	
		Generic.enterValue("id", LoginOR.userNameByID, Constants.UserName);
		Generic.enterValue("id", LoginOR.passwordByID, Constants.pwd);
		Thread.sleep(1000);
		Generic.clickElement("id", LoginOR.loginBtnByID);
	}

}
