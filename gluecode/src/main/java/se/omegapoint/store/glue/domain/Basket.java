package se.omegapoint.store.glue.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import se.omegapoint.store.glue.RestClient;
import se.omegapoint.store.glue.dto.ArticleDTO;
import se.omegapoint.store.glue.dto.BasketDTO;
import se.omegapoint.store.glue.dto.ErrorDTO;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static se.omegapoint.store.glue.Constants.BASE_URL;

public class Basket {


    private final String basketURI = BASE_URL + "/api/basket";

    private RestClient restClient;

    private Articles articles;
    private BasketDTO basket;

    private ErrorDTO errorDTO;

    public Basket(){
        restClient = new RestClient();
        articles = new Articles();
    }

    public BasketDTO createAndSaveBasket() {
        BasketDTO basketDTO =
                restClient
                        .postToUri(URI.create(basketURI),
                                void.class,
                                new ParameterizedTypeReference<BasketDTO>() {});
        this.basket = basketDTO;
        return this.basket;
    }


    public BasketDTO addSavedValidArticlesToBasket(){

        List<ArticleDTO> articleList =  articles.getSavedValidArticles();

        BasketDTO updatedBasketDTO =
                restClient
                        .postToUri(URI.create(basketURI + "/" + basket.getId()),
                                articleList,
                                new ParameterizedTypeReference<BasketDTO>() {});

        this.basket = updatedBasketDTO;
        return this.basket;

    }

    public BasketDTO getBasketWithArticles() {
        assertThat(basket.getArticles()).as("Article list is empty, has add article method not been called?").isNotEmpty();
        return basket;
    }

    public void addSavedInvalidArticlesToBasket() {
        List<ArticleDTO> articleList = articles.getSavedInvalidArticles();
        try {
            ErrorDTO errorDTO = restClient
                    .postToUriInvalid(URI.create(basketURI + "/" + basket.getId()),
                            articleList);
            this.errorDTO = errorDTO;
        } catch (IOException e) {
            throw new AssertionError("Could not transform post to errorDTO " + e);
        }
    }

    public Articles getArticles() {
        return articles;
    }

    public ErrorDTO getErrorResponse() {
        assertThat(errorDTO).isNotNull();
        return this.errorDTO;
    }
}


