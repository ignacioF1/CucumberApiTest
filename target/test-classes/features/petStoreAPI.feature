#language: en
Feature: Test Pet Store API
  As an API consumer
  I want to test
  GET POST UPDATE DELETE operations
  in Swagger Pet Store API

  Background:
    Given do this before each test

  @GET_OPERATION
  Scenario Outline: Test GET operation - Finds Pets by status - successful response check
    # Request URL: https://petstore.swagger.io/v2/pet/findByStatus
    Given "<STATUS>" pet status
    # Other possible pet status: pending, sold
    When doing a GET operation in Swagger´s Pet Store API, filtering by the given status
    Then assert the response status is 200
    Examples:
    |STATUS   |
    |available|
    |pending  |
    |sold     |

#  Scenario: Test GET operation - Finds Pets by status - 400 response check
    # Request URL: https://petstore.swagger.io/v2/pet/findByStatus
#    Given the INVALID pet status "invalidStatus"
    # Any different status from {available, pending, sold}
#    When doing a GET operation in Swagger´s Pet Store API, filtering by the invalid status
#    Then assert the response status is 400
    # Swagger pet store API is not working correctly, it answers with 200 and an empty response

  @POST_OPERATION
  Scenario: Test POST operation - Add a new pet to the store - successful response check
    # Request URL: https://petstore.swagger.io/v2/pet
    Given a new available pet called "TestPet123" with a random id
    # Choose the name you like
    When doing a POST operation in Swagger´s Pet Store API, adding the new pet
    Then assert the response contains the added pet and a 200 is received
    |JSON TAGS|
    |id       |
    |category |
    |name     |
    |photoUrls|
    |tags     |
    |status   |

#  Scenario: Test POST operation - Add a new pet to the store - 405 response check
    # Request URL: https://petstore.swagger.io/v2/pet
#    Given the INVALID id "invalidId"
    # Only valid IDs are integers
#    When doing a POST operation in Swagger´s Pet Store API with a new pet
#    Then assert the response status is 405
    # Swagger pet store API is not working correctly, it answers with 400 instead of 405

  @PUT_OPERATION
  Scenario: Test PUT operation - Update an existing pet - successful response check
    # Request URL: https://petstore.swagger.io/v2/pet
    Given the pet added in the previous test
    When doing a PUT operation in Swagger´s Pet Store API, updating its status to "sold"
    # Available status: available, pending, sold
    Then assert the pet status was updated correctly and 200 is received

  @DELETE_OPERATION
  Scenario: Test DELETE operation - Deletes a pet - successful response check
    # Request URL: https://petstore.swagger.io/v2/pet/{petId}
    Given the pet added in the POST test
    When doing a DELETE operation by the pet´s id in Swagger´s Pet Store API,
    Then assert the pet was deleted correctly and 200 is received
