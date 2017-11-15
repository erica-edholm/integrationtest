package se.omegapoint.store.steps;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import org.springframework.test.context.ContextConfiguration;
import se.omegapoint.store.glue.SpringConfig;
import se.omegapoint.store.glue.enums.ValidOrInvalid;
import se.omegapoint.store.glue.service.ItemService;

@ContextConfiguration(classes = {SpringConfig.class})
public class ItemStepDefinitions implements En {

    public ItemStepDefinitions(ItemService itemService) {


        Given("^\"(\\d+)\" random \"([^\"]*)\" article$", (Integer nrOfArticles, ValidOrInvalid validOrInvalid) -> itemService.createArticles(nrOfArticles, validOrInvalid));
        And("^valid article is set to price zero$", () -> itemService.setPriceToZeroForArticle());
    }
}
