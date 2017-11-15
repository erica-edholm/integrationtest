package se.omegapoint.store.glue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import se.omegapoint.store.glue.domain.Baskets;
import se.omegapoint.store.glue.dto.BasketDTO;
import se.omegapoint.store.glue.dto.ArticleDTO;
import se.omegapoint.store.glue.dto.ErrorDTO;
import se.omegapoint.store.glue.enums.DoesDoesnt;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Component
public class BasketService{

    /**
     *
     */

    static Baskets baskets = new Baskets();

    @Autowired
    public BasketService() {
    }

    public void givenAnEmptyBasketWithoutArticles() {
        BasketDTO basketDTO = baskets.createAndSaveBasket();
        assertThat(basketDTO).isNotNull();
        assertThat(basketDTO.getArticles()).isEmpty();
    }

    public void whenAddingTheArticlesToBasket() {

        BasketDTO updatedBasketDTO = baskets.addSavedValidArticlesToBasket();
        assertThat(updatedBasketDTO).isNotNull();
    }

    public void whenAddingInvalidArticleToTheBasket() {
        baskets.addSavedInvalidArticlesToBasket();
    }

    public void thenBasketContainsTheArticles(DoesDoesnt doesDoesnt) {
        BasketDTO basketDTO = baskets.getBasketWithArticles();
        List<ArticleDTO> articleDTO = baskets.getArticles().getSavedValidArticles();
        articleDTO.forEach(article ->
                {
                    if(DoesDoesnt.does.equals(doesDoesnt)){
                        assertThat(basketDTO.getArticles()).contains(article);
                    }else if(DoesDoesnt.doesnt.equals(doesDoesnt)){
                        assertThat(basketDTO.getArticles()).doesNotContain(article);
                    }

        }
        );

    }

    public void thenAnErrorShouldBeReturned(HttpStatus expectedHttpStatus) {
        ErrorDTO errorDTO = baskets.getErrorResponse();
        assertThat(errorDTO.getStatus()).isEqualTo(expectedHttpStatus);
    }
}
