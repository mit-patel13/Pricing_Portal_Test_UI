package com.pricingPortal.stepdefination;

import java.util.Date;

import org.junit.Assert;

import com.pricingPortal.ObjectRepositary.PricingPortalOR;
import com.pricingPortal.browser.Browser;
import com.pricingPortal.helper.Generic;
import com.pricingPortal.pageObjects.UpdateFilePageObject;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;





public class PriceGroupChangeSetsStepdef extends Browser {

	
	

	UpdateFilePageObject updateFile = new UpdateFilePageObject();
	int before;
	int after;
	
	public PriceGroupChangeSetsStepdef()
	{
		super();
	
	
	}
	
	
	@Given("^User navigate to Pricing Portal home page$")
	public void user_navigate_to_Pricing_Portal_home_page() throws Throwable {
	   
		
	}
	
	
	@Given("^User Load the test data From Excel \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_Load_the_test_data_From_Excel_and(String arg1, String arg2) throws Throwable {
	   Generic.loadTestData(arg1, arg2);
	
	   
	}


	@When("^User click on Price Group Change Sets$")
	public void user_click_on_Price_Group_Change_Sets() throws Throwable {
	    
		Generic.clickElement("xpath", PricingPortalOR.priceGroupChangeSets);
		
		
	}

	@Then("^User verfiy the New Change Set button$")
	public void user_verfiy_the_New_Change_Set_button() throws Throwable {
	    
		Assert.assertTrue(Generic.verifyElement("xpath", PricingPortalOR.newChangeSetbuttn));
		
		
	}

	@When("^User click on New Change Set button$")
	public void user_click_on_New_Change_Set_button() throws Throwable {
	   
		Generic.clickElement("xpath", PricingPortalOR.newChangeSetbuttn);
		
		
	}

	@Then("^User verify new window will displayed with four steps process$")
	public void user_verify_new_window_will_displayed_with_four_steps_process() throws Throwable {
	    
		Assert.assertTrue(Generic.verifyElement("xpath", PricingPortalOR.firststepbox));
		
		
		
	}

	@Then("^User verfiy only first step will be enable$")
	public void user_verfiy_only_first_step_will_be_enable() throws Throwable {
	    
		Assert.assertTrue(updateFile.verifyFirstStepEnable());
		
		
		
	}

	@When("^User upload the price group change set spreadsheet$")
	public void user_successfully_pass_the_spreadsheet() throws Throwable {
	    
		updateFile.chooseFile();
		
		
	}

	@Then("^User verify an uploaded spreadsheet will displayed$")
	public void user_verify_an_uploaded_spreadsheet_will_displayed() throws Throwable {
	    
		Assert.assertTrue(Generic.verifyElement("className", PricingPortalOR.excelSheetDisplayedbyClassname)); 
		
		
		
	}
	
	
	@When("^User select any future date from the calendar pop up$")
	public void user_select_any_future_date_from_the_calendar_pop_up() throws Throwable {
		updateFile.selectDate();
		
		
	}

	@Then("^User verify the selected date is populated in date field and the box turn green and step three is enable$")
	public void user_verify_the_selected_date_is_populated_in_date_field_and_the_box_turn_green_and_step_three_is_enable() throws Throwable {

		
		Assert.assertTrue(updateFile.verifySelectedDate());
		
	}

	@When("^User enter numeric value under column O$")
	public void user_enter_numeric_value_under_column_O() throws Throwable {
	    
		updateFile.updateExcelCellValues();
		
	}

	@Then("^User verify the save button is enabled and step is completed$")
	public void user_verify_the_save_button_is_enabled_and_step_is_completed() throws Throwable {
	    
		
		Assert.assertTrue(Generic.verifyElement("xpath", PricingPortalOR.saveChangeSet));
	}

	@When("^User click on save button$")
	public void user_click_on_save_button() throws Throwable {
	    
		updateFile.saveChangeSet();
		
	}

	@Then("^User verify the row is added with the new updated details$")
	public void user_verify_the_row_is_added_with_the_new_updated_details() throws Throwable {
	    
		
		/*updateFile.editChangeSet();
		updateFile.deleteChangeSetRecord();*/
		
	}
	
	@When("^User edit the same change set with new price group value$")
	public void user_edit_the_same_change_set_with_new_price_group_value() throws Throwable {
	   
		Assert.assertTrue(updateFile.editChangeSet());
		
	}

	@Then("^User verify the same change set it updated$")
	public void user_verify_the_same_change_set_it_updated() throws Throwable {
	   
		
		
	}

	@When("^User delete the same chnage set$")
	public void user_delete_the_same_chnage_set() throws Throwable {
		before=0;
		before=updateFile.fetchChaageSetRowNumber();
	    
		Assert.assertTrue(updateFile.deleteChangeSetRecord());
		
	}

	@Then("^User verify the change set it deleted$")
	public void user_verify_the_change_set_it_deleted() throws Throwable {
	   
		after=0;
		after=updateFile.fetchChaageSetRowNumber();
		
		System.out.println(before+" "+after);
		boolean status=false;
		if (before>after)
			status=true;
		else 
			status=false;
		
		Assert.assertTrue(status);
		
		
		
		
	}
	
	
}