Feature: Flight Search

  Scenario Outline: Search flight using dynamic test data

    Given user logs in using test data "<TC_ID>"
    And user is on flights site page
    And user loads flight test data "<TC_ID>"

    When user selects trip-type and trip class
    And user enters From and To locations
    And user selects Dates
    And user selects travellers
    And user clicks search

    Then Compare flight results using test data "<TC_ID>"

    Examples:
      | TC_ID |
      | TC_01 |
      | TC_02 |
      | TC_03 |