Feature: Validating the Product Relationship Rule tab


@Regression1
Scenario Outline: Validating user can add products with Product is always X price as a rule type
Given User navigate to Pricing Portal home page
And  User Load the test data From Excel "<SheetName>" and "<ScenarioName>"
When User click on Product Relationship rule tab under Configuration tab
Then User verify Product Relationship rule grid will displayed
When User click on New Rule
Then User verfiy add rule pop up will displayed
When User enter Rule name, Product Name, select Product is always X price type and enter amount.
Then User verify save button will enabled and click on save button.
And  User verify new rule will be added successfully on the Product Relationship rule grid.

Examples:
| SheetName | ScenarioName |
| Updatefunctionality | UpdateFile |



@Regression2
Scenario Outline: Validating user can add products with Product equals another product or set of products as a rule type
Given User navigate to Pricing Portal home page
And  User Load the test data From Excel "<SheetName>" and "<ScenarioName>"
When User click on Product Relationship rule tab under Configuration tab
Then User verify Product Relationship rule grid will displayed
When User click on New Rule
Then User verfiy add rule pop up will displayed
When User enter Rule name, Product Name, select Product equals another product or set of products type and enter set of products.
Then User verify save button will enabled and click on save button.
And  User verify new rule will be added successfully on the Product Relationship rule grid.

Examples:
| SheetName | ScenarioName |
| Updatefunctionality | UpdateFile |




@Regression3
Scenario Outline: Validating user can add products with Product is X percent higher then product Y as a rule type
Given User navigate to Pricing Portal home page
And  User Load the test data From Excel "<SheetName>" and "<ScenarioName>"
When User click on Product Relationship rule tab under Configuration tab
Then User verify Product Relationship rule grid will displayed
When User click on New Rule
Then User verfiy add rule pop up will displayed
When User enter Rule name, Product Name, select Product is X percent higher then product Y and enter percentage and Y product.
Then User verify save button will enabled and click on save button.
And  User verify new rule will be added successfully on the Product Relationship rule grid.

Examples:
| SheetName | ScenarioName |
| Updatefunctionality | UpdateFile |



@Regression4
Scenario Outline: Validating user can add products with Product is $X higher then product Y as a rule type
Given User navigate to Pricing Portal home page
And  User Load the test data From Excel "<SheetName>" and "<ScenarioName>"
When User click on Product Relationship rule tab under Configuration tab
Then User verify Product Relationship rule grid will displayed
When User click on New Rule
Then User verfiy add rule pop up will displayed
When User enter Rule name, Product Name, select Product is $X higher then product Y and enter percentage and Y product.
Then User verify save button will enabled and click on save button.
And  User verify new rule will be added successfully on the Product Relationship rule grid.

Examples:
| SheetName | ScenarioName |
| Updatefunctionality | UpdateFile |



@Regression5
Scenario Outline: Validating user can edit customize rule for the products
Given User navigate to Pricing Portal home page
And  User Load the test data From Excel "<SheetName>" and "<ScenarioName>"
When User click on Product Relationship rule tab under Configuration tab
Then User verify Product Relationship rule grid will displayed
When User click on New Rule
Then User verfiy add rule pop up will displayed
When User enter Rule name, Product Name, select rule type and enter amount, percentage or select different products.
Then User verify save button will enabled and click on save button.
And  User verify new rule will be added successfully on the Product Relationship rule grid.

Examples:
| SheetName | ScenarioName |					
| Updatefunctionality | UpdateFile |


