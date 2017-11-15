Feature: Basket tests

  @test
  Scenario: Should be able to add article to basket
    Given an empty basket without articles
    And "1" random "valid" article
    When adding the article to the basket
    Then basket "does" contains the articles

  Scenario: Should get exception if an invalid article is added to basket
    Given an empty basket without articles
    And "1" random "invalid" article
    When adding invalid article to the basket
    Then an "404" exception should be returned

  Scenario: Should not be able to add article with price zero
    Given an empty basket without articles
    And "1" random "valid" article
    And valid article is set to price zero
    When adding the article to the basket
    Then basket "doesnt" contains the articles