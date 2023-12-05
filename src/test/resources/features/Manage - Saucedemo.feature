Feature: Manage - Saucedemo

  Scenario Outline: Verify that Login was successful
    #outline dlea scenario (examples)
    Given user is accessing the login page
    #bdd cucumber Gherkins
    When user enters correct credentials "<username>" "<password>"
      #<> reference daeot ssylku na parametry
    Then user is successfully logged in


    Examples://parametrs
      | username                | password     |
      | standard_user           | secret_sauce |
      | visual_user             | secret_sauce |
     # | problem_user            | secret_sauce |
    # | performance_glitch_user | secret_sauce |
    # | error_user              | secret_sauce |

  Scenario: Verify that Login failed
    Given user is accessing the login page
    #bdd cucumber
    When user enters wrong credentials "username" "password"
    Then logged in is failed with following error message: "Epic sadface: Username and password do not match any user in this service"


  Scenario: Verify that user is able to purchase items
    Given user is accessing the login page
    And user enters correct credentials "standard_user" "secret_sauce"
    # argument(parametry)
    And user is successfully logged in
    When user adds to cart an item
    And user clicks on cart icon
    Then user should see the purchased items


  Scenario: Verify that user is able to remove purchase items
    Given user is accessing the login page
    And user enters correct credentials "standard_user" "secret_sauce"
    # argument(parametry)
    And user is successfully logged in
    And user adds to cart an item
    And user clicks on cart icon
    And user should see the purchased items
    When user clicks on remove button
    Then purchased item should be removed

    Scenario: Verify that user is able to checkout items
      Given user is accessing the login page
      And user enters correct credentials "standard_user" "secret_sauce"
      And user is successfully logged in
      And user adds to cart an item
      And user clicks on cart icon
      And user should see the purchased items
      When user clicks on checkout button
      And enters credentials "Alex" "Sandra" "12345"
      And user clicks continue button
      And user clicks finish button
      Then order is completed with following success message: "Thank you for your order!"


