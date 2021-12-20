Feature: tests the machine scenarios
  Scenario: client makes call to POST new machine
    Given the machine db has no data
    Given the client wants to register the machine "mach1:mars"
    When the client sends a POST to the machines end point
    Then the application has inserted the new machine into the database
    And the response status code is 201

  Scenario: upload file of machines
    Given the machine db has no data
    And user selects the file "machines.csv"
    When the user uploads the file to "/v1/machines/upload"
    Then the machines inserted are
      | id            | name          |
      | ajoparametrit | Ajoparametrit |
      | aufwickler    | Aufwickler    |
      | wickelkopf    | Wickelkopf    |