Feature: Facebook
  I want to use this template for my feature file
  
  Background: 
    Given Navigate to Facebook Application

  @FBRegistation
  Scenario: Facebook Registration
    Given I Entered All Mandatory Details
    And I Click on SignUp
    When I Validate the Sceanrio