@LoginPageFunctionality
Feature: Home Page Functionality

  Background: 
    Given Navigate to HMS Application

  @Regression
  Scenario: Verify User Login
    When I Login to HMS Application Using Below Credentials:
      | username | @testdata.login.validuser.username |
      | password | @testdata.login.validuser.password |
    Then 'HOME' Page is Opened
    Then Validate the Scenario

  @Regression
  Scenario: Verify User Logout
    When I Logout from HMS Application
    Then Validate the Scenario
