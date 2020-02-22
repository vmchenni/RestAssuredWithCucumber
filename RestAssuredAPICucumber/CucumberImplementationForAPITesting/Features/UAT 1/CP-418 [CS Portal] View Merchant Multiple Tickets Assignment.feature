@RegressionUAT1@8.1@CP-418@set1
Feature: CP-2569#CP-418 [CS Portal] View Merchant CS Ticket List - Multiple Tickets Assignment

Summary
As a CS Portal user
I want to be able to assign multiple tickets to myself or other people
So that I do not need to perform the same action several times


  Scenario: CP-1065 CP-1053 [CS Portal] Multiple Tickets Assignment - Login
#    Given User is logged into the system
    Given User is logged in to "UAT1" environment with username "admin" and password "123456"

  #Scenario 10
  Scenario Outline: AC#10 CP-418 [CS Portal] Multiple Tickets Assignment - Verify active CS portal users for the work
    When the user clicks View Peak Ticket button on menu bar for under View Peak Ticket
    Then the user lands on View Ticket landing page and verify title as "All Messages"
    Given the user has selected one or more tickets on all tickets page
    Given the user clicks Assign button
    When the user has selected and active "Peak - CS"
    And expands the assignee dropdown list
    Then the "Peak - CS" drop down list should only show active CS Portal users only
    Examples:
      |Work Group  |
      |SuperAdminAuto  |

  Scenario: CP-418 [CS Portal] Multiple Tickets Assignment - Logout
    Then User should be logged out from the system

  Scenario: CP-1065 CP-1053 [CS Portal] Multiple Tickets Assignment - Login
#    Given User is logged into the system
    Given User is logged in to "UAT1" environment with username "admin.CP-418" and password "Payme@2020"

  #Scenario 1
  Scenario Outline: AC#1 CP-418 Multiple Tickets Assignment - View Peak Ticket
    Given a CS Portal user has logged in CS Portal
    When the user clicks View Peak Ticket button on menu bar for under View Peak Ticket
    Then the user lands on View Ticket landing page and verify title as "<Title>"
    Examples:
      |Title        |
      |All Messages |

  #Scenario 2
  Scenario Outline: AC#2 CP-418 [CS Portal] Multiple Tickets Assignment - Verify Select All Box functionality
    Given the user lands on View Ticket landing page and verify title as "<Title>"
    When the user has checked the Select All Box next to Ticket Number
#    Then all tickets on the same page should be selected but not all tickets within the searching result
    Examples:
      |Title       |
      |All Messages|

  #Scenario 3
  Scenario Outline: AC#3 CP-418 [CS Portal] Multiple Tickets Assignment - Uncheck Select All Box
    Given the user lands on View Ticket landing page and verify title as "<Title>"
    And the Select ALL Box is checked
    When the user unchecks the Select ALL Box next to Ticket Number
    Then all tickets on the same page should be unselected
    Examples:
      |Title       |
      |All Messages|

  #Scenario 4
  Scenario: AC#4 CP-418 [CS Portal] Multiple Tickets Assignment - Verify Assign to me button and Assign button when one or more than one ticket is selected
    When the user has selected one or more tickets
    Then Assign to me button and Assign button should appear

  #Scenario 5
  Scenario: AC#5 CP-418 [CS Portal] Multiple Tickets Assignment - Verify Assign to me and Assign button should disappear when no tickets selected
    When the user has selected no tickets
    Then Assign to me button and Assign button should disappear

  #Scenario 6
  Scenario Outline: AC#6 CP-418 [CS Portal] Multiple Tickets Assignment - Verify Assign to me button functionality
    Given the user selected one or more tickets
    When the user clicks Assign to me button
    Then the new assignee of the selected tickets should be the "<user>" who performed this action
    Examples:
      |user  |
      |SuperAdminAuto / admin.CP-418|

  #Scenario 7
  Scenario Outline: AC#7 CP-418 [CS Portal] Multiple Tickets Assignment - Verify Assign button functionality
#    Then Switch focus to home window
    Given the user has selected one or more tickets from list of tickets
    When the user clicks Assign button
    Then the user should be able to select work group and assignee the tickets will be assigned to "Peak - CS / Unspecified"
    Examples:
      |Assignee  |
      |nilesh.peak.cs.s|

  #Scenario 8
  Scenario: AC#8 CP-418 [CS Portal] Multiple Tickets Assignment - Verify work group drop-down
    Given the user has selected one or more tickets from list of tickets
    And the user clicks Assign button
    When the user has expands the work group drop down list
    Then the work group drop down list should only show active work groups only

  #Scenario 9
  Scenario Outline: AC#9 CP-418 [CS Portal] Multiple Tickets Assignment - Verfiy assignee drop down list when user has not selected active work group
    Given the user clicks Assign button
    When the user has not selected an active "<Work Group>"
    Then the user is not able to expand the "<Assignee>" drop-down list
    Examples:
      |Work Group                      |Assignee                |
      |-- Please Select Work Group --  |-- Please Select User --|

  #Scenario 11
  Scenario Outline: AC#11 CP-418 [CS Portal] Multiple Tickets Assignment - Verify Assign button functionality
    Given the user has selected one or more tickets on all tickets page
    And the user clicks Assign button
    And selects the person the ticket will be assigned to from "SuperAdminAuto" and "admin.CP-418"
    When the user clicks Assign button on Assign To window
    Then the new assignee of the selected tickets should be the person selected by the user from "SuperAdminAuto" and "admin.CP-418"
    Examples:
      |Work Group  |Assignee|
      |SuperAdminAuto  |admin.vishwanath|

  Scenario: CP-418 [CS Portal] Multiple Tickets Assignment - Logout
    Then User should be logged out from the system


