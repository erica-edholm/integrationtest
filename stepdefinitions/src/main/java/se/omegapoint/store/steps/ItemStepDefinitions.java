package se.omegapoint.store.steps;

import cucumber.api.java8.En;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import se.omegapoint.store.glue.SpringConfig;
import se.omegapoint.store.glue.service.ItemService;

@ContextConfiguration(classes = {SpringConfig.class})
public class ItemStepDefinitions implements En {

    public ItemStepDefinitions(ItemService itemService) {

        Given("^a random article$", itemService::givenOneRandomArticle);
    }
}
