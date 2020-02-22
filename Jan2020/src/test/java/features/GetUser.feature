Feature: Get Users
  Scenario: GetUserList
    Given User calls get user API
    Then Verify the first_name as "Michael"