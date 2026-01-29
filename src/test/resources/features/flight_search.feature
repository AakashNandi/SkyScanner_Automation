Feature: One-way Flight Search on Skyscanner

  Scenario Outline: Search one-way flight using dynamic test data
    Given user is on flights site page
    And user loads flight test data "<TC_ID>"
    When user selects trip-type and trip class
    And user enters From and To locations
    And user selects Dates
    And user selects travellers
    And user clicks search
    Then flight results should be displayed

    Examples:
      | TC_ID |
      | TC_01 |
      | TC_02 |
      | TC_03 |