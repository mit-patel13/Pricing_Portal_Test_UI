package com.pricingPortal.stepdefination;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import com.pricingPortal.ObjectRepositary.PricingPortalOR;
import com.pricingPortal.ObjectRepositary.ProductsTabOR;
import com.pricingPortal.browser.Constants;
import com.pricingPortal.helper.Generic;
import com.pricingPortal.pageObjects.ProductsPageObject;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ProductTabStepdef {

	
	ProductsPageObject productObj = new ProductsPageObject();
	
	
	
	@When("^User click on Product tab$")
	public void user_click_on_Product_tab() throws Throwable {
	    
		Generic.clickElement("xpath", ProductsTabOR.ProductsTab);
		
		
	}

	@When("^User click on New product$")
	public void user_click_on_New_product() throws Throwable {
	    
		Generic.clickElement("xpath", ProductsTabOR.newProductbuttn);
		
		
	}
	
	
	@Then("^User verfiy add product pop up will displayed$")
	public void user_verfiy_add_product_pop_up_will_displayed() throws Throwable {
	   
		
		Assert.assertTrue(Generic.verifyElement("xpath", ProductsTabOR.addProductPopUp));
		
	}
	

	@When("^User enter Pin, \"([^\"]*)\"  Name \"([^\"]*)\" and select active in fusion, Status and Catering check box and click save button$")
	public void user_enter_Pin_Name_and_select_active_in_fusion_Status_and_Catering_check_box_and_click_save_button(String pin, String name) throws Throwable {
		productObj.addProductsWithPinDetails(pin,name);
		
		productObj.verifySubmitbuttonEnable();
		Generic.clickElement("xpath", ProductsTabOR.submitButton);

	}

	@Then("^User verify new product will be added successfully on the product grid and verify all the entered details \"([^\"]*)\" and \"([^\"]*)\"\\.$")
	public void user_verify_new_product_will_be_added_successfully_on_the_product_grid_and_verify_all_the_entered_details_and(String pin, String name) throws Throwable {
	  
		Assert.assertTrue(productObj.verifyCreatedProduct(pin, name));
		
		productObj.deleteProduct();
		
		
	}


	
	
	
	///////Validating user can add products without entering PIN/////////////
	
	
	@When("^User enter Name \"([^\"]*)\" and select active in fusion, Status Catering, Category, SubCategory, Grouping, SubGrouping and click save button$")
	public void user_enter_Name_and_select_active_in_fusion_Status_Catering_Category_SubCategory_Grouping_SubGrouping_and_click_save_button(String name) throws Throwable {
	    
		productObj.addProductsWithoutPinDetails(name);
		
		productObj.verifySubmitbuttonEnable();
		Generic.clickElement("xpath", ProductsTabOR.submitButton);
		
		
	}

	@Then("^User verify new product will be added successfully on the product grid and verify all the entered details \"([^\"]*)\"\\.$")
	public void user_verify_new_product_will_be_added_successfully_on_the_product_grid_and_verify_all_the_entered_details(String name) throws Throwable {
	   
		Assert.assertTrue(productObj.verifyCreatedProductWithoutPin(name));
				
		productObj.deleteProduct();
		
	}
	
	
	
	/////////////Validating user can edit products//////////////
	
	@Then("^User verify new product will be added successfully on the product grid and verify all the entered details \"([^\"]*)\" and dont delete the product\\.$")
	public void user_verify_new_product_will_be_added_successfully_on_the_product_grid_and_verify_all_the_entered_details_and_dont_delete_the_product(String name) throws Throwable {
	    
		Assert.assertTrue(productObj.verifyCreatedProductWithoutPin(name));
		
	}
	
	
	@When("^User click on edit button$")
	public void user_click_on_edit_button() throws Throwable {
	    
		
		Generic.clickElement("xpath", ProductsTabOR.editbutton);
	}

	@Then("^User verfiy edit product pop up will displayed$")
	public void user_verfiy_edit_product_pop_up_will_displayed() throws Throwable {
	  
		Assert.assertTrue(Generic.verifyElement("xpath", ProductsTabOR.editProductPopup));
		
	}
	
	
	@When("^User enter Name \"([^\"]*)\" and select active in fusion, Status Catering, Category Beverages, SubCategory, Grouping, SubGrouping and click save button$")
	public void user_enter_Name_and_select_active_in_fusion_Status_Catering_Category_Beverages_SubCategory_Grouping_SubGrouping_and_click_save_button(String name) throws Throwable {
	    
		productObj.editProductsWithoutPinDetails(name);
		
		productObj.verifySubmitbuttonEnable();
		Generic.clickElement("xpath", ProductsTabOR.submitButton);
		
	}

	@Then("^User verify new product will be edited successfully on the product grid and verify all the entered details \"([^\"]*)\"\\.$")
	public void user_verify_new_product_will_be_edited_successfully_on_the_product_grid_and_verify_all_the_entered_details(String name) throws Throwable {
	    
		Assert.assertTrue(productObj.verifyEditedProductWithoutPin(name));
		
		productObj.deleteProduct();
		
	}
	
	////////////////Validate user can upload Menu Hierarchy file/////////////
	
	
	@When("^User click on Edit Menu button$")
	public void user_click_on_Edit_Menu_button() throws Throwable {
	    
		Generic.clickElement("xpath", ProductsTabOR.editMenuButton);
		
	}

	@Then("^User verfiy Upload Menu Hierarchy Excel File pop up will displayed$")
	public void user_verfiy_Upload_Menu_Hierarchy_Excel_File_pop_up_will_displayed() throws Throwable {
	   
		Assert.assertTrue(Generic.verifyElement("xpath", ProductsTabOR.verifyUploadfilePopup));
		
	}

	@When("^User click on choose file button and upload the file by clicking save button$")
	public void user_click_on_choose_file_button_and_upload_the_file_by_clicking_save_button() throws Throwable {
	    
		
		Generic.enterValue("xpath", ProductsTabOR.uploadMenuFile, Constants.uploadMenuHierarchyExcelPath);
		
		Generic.clickElement("xpath", ProductsTabOR.saveMenuFile);
		
	}

	@Then("^User verify the message will displayed$")
	public void user_verify_the_message_will_displayed() throws Throwable {
	    
		Assert.assertTrue(Generic.verifyElement("xpath", ProductsTabOR.verifyMenuFileUploaded));
		
	}
	
	
	
}
