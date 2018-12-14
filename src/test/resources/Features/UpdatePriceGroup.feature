Feature: Validating theupdate price group change 

@Regression
Scenario Outline: Validate pricegroup change set 
Given User navigate to Pricing Portal home page
And  User Load the test data From Excel "<SheetName>" and "<ScenarioName>"
When User click on Price Group Change Sets
Then User verfiy the New Change Set button
When User click on New Change Set button
Then User verify new window will displayed with four steps process
And User verfiy only first step will be enable
When User upload the price group change set spreadsheet
Then User verify an uploaded spreadsheet will displayed
When User select any future date from the calendar pop up
Then User verify the selected date is populated in date field and the box turn green and step three is enable
When User enter numeric value under column O
Then User verify the save button is enabled and step is completed
When User click on save button
Then User verify the row is added with the new updated details
When User edit the same change set with new price group value
Then User verify the same change set it updated
When User delete the same chnage set
Then User verify the change set it deleted


Examples:
| SheetName | ScenarioName |
| Updatefunctionality | UpdateFile |


