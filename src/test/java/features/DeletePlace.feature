Feature: Verify delete place API

  @DeletePlace
  Scenario: Verify delete place API
    Given delete place API payload
    When user calls "DeletePlaceAPI" with "POST" Http request
    Then Api call is success with status code 201
    And "status" in response body is "OK"