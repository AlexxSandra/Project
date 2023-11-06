Feature: Manage - Saucedemo

  Scenario Outline: Verify that Login was successful
    Given user is accessing the login page
    When user enters correct credentials "<username>" "<password>"
    Then user is successfully logged in

    Examples:
      | username                | password     |
      | standard_user           | secret_sauce |
      | problem_user            | secret_sauce |
      | performance_glitch_user | secret_sauce |
      | error_user              | secret_sauce |
      | visual_user             | secret_sauce |

  Scenario: Verify that user is able to purchase items
    Given user is accessing the login page
    And user enters correct credentials "standard_user" "secret_sauce"
    And user is successfully logged in
    When user adds to cart an item
    And user clicks on cart icon
    Then user should see the purchased items
