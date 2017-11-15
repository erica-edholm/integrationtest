package se.omegapoint.store.steps;

import cucumber.api.java8.En;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import se.omegapoint.store.glue.SpringConfig;
import se.omegapoint.store.glue.enums.DoesDoesnt;
import se.omegapoint.store.glue.service.BasketService;

@ContextConfiguration(classes = {SpringConfig.class})
public class BasketStepDefinitions implements En {

    private final BasketService basketService;

    public BasketStepDefinitions(BasketService basketService) {

        this.basketService = basketService;

        Given("^an empty basket without articles$", basketService::givenAnEmptyBasketWithoutArticles);

        When("^adding the article to the basket$", basketService::whenAddingTheArticlesToBasket);

        Then("^basket \"(does|doesnt)\" contains the articles$", (DoesDoesnt doesDoesnt) -> basketService.thenBasketContainsTheArticles(doesDoesnt));
        When("^adding invalid article to the basket$", () -> basketService.whenAddingInvalidArticleToTheBasket());
        Then("^an \"([^\"]*)\" exception should be returned$", (Integer status) -> basketService.thenAnErrorShouldBeReturned(HttpStatus.valueOf(status)));
    }
}
