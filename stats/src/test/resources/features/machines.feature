Feature: tests the machine scenarios
  Scenario: client makes call to POST new machine
    Given the machine db has no data
    Given the client wants to register the machine "mach1:mars"
    When the client sends a POST to the machines end point
    Then the application has inserted the new machine into the database
    And the response status code is 201
