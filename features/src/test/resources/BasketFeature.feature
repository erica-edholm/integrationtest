Feature: Basket tests

  @test
  Scenario: Add articles to basket
    Given an empty basket without articles
    And a random article
    When adding the article to the basket
    Then basket contains the item