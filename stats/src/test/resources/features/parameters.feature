Feature: Insert and retrieve information about the parameters
  Scenario: inserting parameters
    Given there is no parameter in the database
    And user will use the request body in the file "parameters_of_lisboa"
    When the user sends the POST request
    Then the parameters are inserted into the database

  Scenario: upload file of parameter
    Given there is no parameter in the database
    And user selects the file "parameters.csv"
    When the user uploads the file to "/v1/parameters/upload"
    Then the parameters inserted are
      | parameterName     | value | machineId  |
      | log_diameter      | 15    | aufwickler |
      | speed             | 35.6  | aufwickler |
      | core_interference | 25.7  | wickelkopf |
      | speed             | 27.7  | wickelkopf |

  Scenario: retrieve the latestParameters
    Given there is no parameter in the database
    And user will use the request body in the file "parameters_of_lisboa"
    When the user sends the POST request
    And user will use the request body in the file "second_parameters_of_lisboa"
    When the user sends the POST request
    Then the latest parameters for machine "lisboa" is "second_parameters_of_lisboa"

  Scenario: retrieve computed information about the parameters
    Given there is no parameter in the database
    And user will use the request body in the file "parameters_of_lisboa"
    When the user sends the POST request
    And user will use the request body in the file "second_parameters_of_lisboa"
    When the user sends the POST request
    And user will use the request body in the file "third_parameters_of_lisboa"
    When the user sends the POST request
    Then the computed info for machine "lisboa" and parameter "memory" for last 15 minutes is "lisboa_computed_info"
