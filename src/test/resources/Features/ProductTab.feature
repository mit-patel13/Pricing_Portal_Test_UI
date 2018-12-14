Feature: Validating the Products tab


@Regression10
Scenario Outline: Validating user can add Catering type product
Given User navigate to Pricing Portal home page
And  User Load the test data From Excel "<SheetName>" and "<ScenarioName>"
When User click on Product tab
And  User click on New product
Then User verfiy add product pop up will displayed
When User enter Pin, "<pin>"  Name "<productName>" and select active in fusion, Status and Catering check box and click save button
Then User verify new product will be added successfully on the product grid and verify all the entered details "<pin>" and "<productName>".


Examples:
| SheetName | ScenarioName | pin | productName |
| Updatefunctionality | UpdateFile | 111101 | Test Product 8 |



@Regression11
Scenario Outline: Validating user can add products without entering PIN
Given User navigate to Pricing Portal home page
And  User Load the test data From Excel "<SheetName>" and "<ScenarioName>"
When User click on Product tab
And  User click on New product
Then User verfiy add product pop up will displayed
When User enter Name "<productName>" and select active in fusion, Status Catering, Category, SubCategory, Grouping, SubGrouping and click save button
Then User verify new product will be added successfully on the product grid and verify all the entered details "<productName>".


Examples:
| SheetName | ScenarioName | productName |
| Updatefunctionality | UpdateFile | Test Product 11 |



@Regression12
Scenario Outline: Validating user can edit products
Given User navigate to Pricing Portal home page
And  User Load the test data From Excel "<SheetName>" and "<ScenarioName>"
When User click on Product tab
And  User click on New product
Then User verfiy add product pop up will displayed
When User enter Name "<productName>" and select active in fusion, Status Catering, Category, SubCategory, Grouping, SubGrouping and click save button
Then User verify new product will be added successfully on the product grid and verify all the entered details "<productName>" and dont delete the product.
When User click on edit button
Then User verfiy edit product pop up will displayed
When User enter Name "<productName>" and select active in fusion, Status Catering, Category Beverages, SubCategory, Grouping, SubGrouping and click save button
Then User verify new product will be edited successfully on the product grid and verify all the entered details "<productName>".


Examples:
| SheetName | ScenarioName | productName |
| Updatefunctionality | UpdateFile | Test Product 12 |


@Regression13
Scenario Outline: Validate user can upload Menu Hierarchy file
Given User navigate to Pricing Portal home page
And  User Load the test data From Excel "<SheetName>" and "<ScenarioName>"
When User click on Product tab
And  User click on Edit Menu button
Then User verfiy Upload Menu Hierarchy Excel File pop up will displayed
When User click on choose file button and upload the file by clicking save button
Then User verify the message will displayed 


Examples:
| SheetName | ScenarioName |
| Updatefunctionality | UpdateFile |