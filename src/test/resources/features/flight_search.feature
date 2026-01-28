Feature: One-way Flight Search on Skyscanner

  Scenario Outline: Search one-way flight using dynamic test data
    Given user is on Skyscanner flights page
    And user loads flight test data "<TC_ID>"
    When user selects trip-type and class
    And user enters From and To locations
    And user selects departure date
    And user selects travellers and cabin class
    And user unticks Add hotel option
    And user clicks search
    Then flight results should be displayed

    Examples:
      | TC_ID |
      | TC_01 |
      | TC_02 |
      | TC_03 |