package se.omegapoint.store.glue.service;

import org.springframework.stereotype.Component;
import se.omegapoint.store.glue.RestClient;
import se.omegapoint.store.glue.dto.ArticleDTO;
import se.omegapoint.store.glue.dto.MoneyDTO;
import se.omegapoint.store.glue.enums.ValidOrInvalid;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Component
public class ArticleService {



    public ArticleService() {
    }

    public void givenOneRandomValidArticle() {
        ArticleDTO randomArticle = BasketService.baskets.getArticles().saveRandomArticleFromArticleList();
        assertThat(randomArticle).isNotNull();

    }

    public void givenOneRandomInvalidArticle() {
        ArticleDTO invalidArticle = new ArticleDTO(UUID.randomUUID(), "invalid article", new MoneyDTO(Integer.toUnsignedLong(1234)));
        BasketService.baskets.getArticles().saveInvalidArticle(invalidArticle);
    }

    public void createArticles(Integer nrOfArticles, ValidOrInvalid validOrInvalid) {
        for(int i = 0; i < nrOfArticles; i++){
            switch(validOrInvalid){
                case valid:
                    givenOneRandomValidArticle();
                    break;
                case invalid:
                    givenOneRandomInvalidArticle();
                    break;
                default:
                    throw new AssertionError(validOrInvalid + " is not implemented yet");
            }
        }


    }

    public void setPriceToZeroForArticle() {
        BasketService.baskets.getArticles().updatePriceTo(0L);
    }
}
