Feature: Validate Google Add place API

  @AddPlace
  Scenario Outline: Verify add place API
    Given Add place payload with "<website>" and "<language>"
    When user calls "AddPlaceAPI" with "POST" Http request
    Then Api call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And extract the placeId created
    When user calls "GetPlaceAPI" with "GET" Http request
    Then Api call is success with status code 200
    And "website" in response body is "<website>"

    Examples:
      | website           | language  |
#      | http://google.com | French-IN |
      | http://amazon.com | French-IN |

