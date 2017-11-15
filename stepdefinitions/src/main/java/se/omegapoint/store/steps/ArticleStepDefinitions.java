package se.omegapoint.store.steps;

import cucumber.api.java8.En;
import org.springframework.test.context.ContextConfiguration;
import se.omegapoint.store.glue.SpringConfig;
import se.omegapoint.store.glue.enums.ValidOrInvalid;
import se.omegapoint.store.glue.service.ArticleService;

@ContextConfiguration(classes = {SpringConfig.class})
public class ArticleStepDefinitions implements En {

    public ArticleStepDefinitions(ArticleService articleService) {


        Given("^\"(\\d+)\" random \"([^\"]*)\" article$", (Integer nrOfArticles, ValidOrInvalid validOrInvalid) -> articleService.createArticles(nrOfArticles, validOrInvalid));
        And("^valid article is set to price zero$", () -> articleService.setPriceToZeroForArticle());
    }
}
