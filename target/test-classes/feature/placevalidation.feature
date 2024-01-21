#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
Feature: Validating Place APIS
  I want to use this template for my feature file

  #Scenario: Verify if Place is being successfully add using ADD Place API
  #Given Add Place Payload
  #When user calls "AddPlaceAPI" with POST HTTP request
  #Then the API call is success with status code 200
  #And "status" in response body is "OK"
  #And "scope" in response body is "APP"
  @AddPlace
  Scenario Outline: Verify if Place is being successfully added using ADD Place API
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "addPlaceAPI" with "POST" HTTP request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_ID created maps to "<name>" using "getPlaceAPI"

    Examples: 
      | name  | language | address             |
      | TEST1 | English  | TESTING UK          |
      #| TEST2 | French   | TESTING France      |
      #| TEST3 | Spanish  | TESTING Spain       |
      #| TEST4 | Hindi    | TESTING UP          |
      #| TEST5 | Marathi  | TESTING Maharashtra |
      
  @DeletePlace    
  Scenario Outline: Verify if Place is being successfully deleted using Delete Place API
    Given Delete Place Payload with placeID
    When user calls "deletePlaceAPI" with "POST" HTTP request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
  #  And "scope" in response body is "APP"
#
    #Examples: 
      #| name  | language | address             |
      #| TEST1 | English  | TESTING UK          |
      #| TEST2 | French   | TESTING France      |
      #| TEST3 | Spanish  | TESTING Spain       |
      #| TEST4 | Hindi    | TESTING UP          |
      #| TEST5 | Marathi  | TESTING Maharashtra |
 #@tag1
  #Scenario: Verify if Place is being successfully deleted using DELETE Place API
    #Given Add Place Payload
    #When user calls "DeletePlaceAPI" with POST HTTP request
    #Then the API call is success with status code 200
    #And status in response body is "OK"
#
  #@tag2
  #Scenario Outline: Title of your scenario outline
    #Given I want to write a step with <name>
    #When I check for the <value> in step
    #Then I verify the <status> in step
#
    #Examples: 
      #| name  | value | status  |
      #| name1 |     5 | success |
      #| name2 |     7 | Fail    |
