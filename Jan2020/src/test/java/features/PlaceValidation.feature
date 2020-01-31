Feature: Validating Place API
  Scenario Outline: Verify if place being successfully added PreCondition
    Given Add place payload location lat as "<lat>" , lng as "<lng>", accuracy as "<accuracy>", name as "<name>" phone as "<phone_number>", address as "<address>", types as "<types>", website as "<website>" and language as "<language>"

#    Given Add place payload
    When User calls "add Place" api with post http request
    Then the API call is success with status code 200
    And Status in response body is OK
    Examples:
      | lat | lng | accuracy | name | phone_number | address | types | website | language |
      | -38.383498|33.427362|50|Frontline house|(+91) 983 893 3937|29, side layout, cohen 09|shoe park#shop|http://google.com|French-IN|
      | -38.383498|33.427362|50|Frontline house|(+91) 983 893 3937|29, side layout, cohen 09|shoe park#shop|http://google.com|French-IN|
      | -38.383498|33.427362|50|Frontline house|(+91) 983 893 3937|29, side layout, cohen 09|shoe park#shop|http://google.com|French-IN|