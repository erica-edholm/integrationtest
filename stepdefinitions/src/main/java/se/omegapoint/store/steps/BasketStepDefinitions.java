package se.omegapoint.store.steps;

import cucumber.api.java8.En;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import se.omegapoint.store.glue.SpringConfig;
import se.omegapoint.store.glue.service.BasketService;

@ContextConfiguration(classes = {SpringConfig.class})
public class BasketStepDefinitions implements En {

    private final BasketService basketService;

    @Autowired
    public BasketStepDefinitions(BasketService basketService) {

        this.basketService = basketService;

        Given("^an empty basket without articles$", basketService::givenAnEmptyBasketWithoutArticles);

        When("^adding the article to the basket$", basketService::whenAddingTheArticleToTheBasket);

        Then("^basket contains the item$", basketService::thenBasketContainsTheItem);
    }
}
