@HMSRegistration
Feature: Home Page Functionality

  Background: 
    Given Navigate to HMS Application

  
  Scenario: Verify User Login
    When I Login to HMS Application Using Below Credentials:
      | username | @testdata.login.validuser.username |
      | password | @testdata.login.validuser.password |
    Then 'HOME' Page is Opened
    Then Validate the Scenario
    
   
  Scenario: Verify Emergency Registration
    When I Login to HMS Application Using Below Credentials:
      | username | @testdata.login.validuser.username |
      | password | @testdata.login.validuser.password |
    Then 'HOME' Page is Opened
    Then I Click on Registration from Left Panel
    Then I Click on Emergency Registration
    And I Input and Submit All Mandatory Fields in Form
    And I Logout From Application
    Then Validate the Scenario
