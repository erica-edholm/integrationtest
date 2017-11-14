package se.omegapoint.store.glue.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import se.omegapoint.store.glue.RestClient;
import se.omegapoint.store.glue.TestSession;
import se.omegapoint.store.glue.dto.BasketDTO;
import se.omegapoint.store.glue.dto.ArticleDTO;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Component
public class BasketService{

    private final String basketURI = "http://ec2-52-59-239-228.eu-central-1.compute.amazonaws.com:8080/api/basket";
    private final RestClient restClient;
    private final TestSession testSession;

    public BasketService(RestClient restClient, TestSession testSession) {
        this.restClient = restClient;
        this.testSession = testSession;
    }

    public void givenAnEmptyBasketWithoutArticles() {
        BasketDTO basketDTO =
                restClient
                        .postToUri(URI.create(basketURI),
                                void.class,
                                new ParameterizedTypeReference<BasketDTO>() {});
        assertThat(basketDTO).isNotNull();
        assertThat(basketDTO.getArticles()).isEmpty();
        testSession.put(BasketDTO.class, basketDTO);
    }

    public void whenAddingTheArticleToTheBasket() {
        BasketDTO basketDTO = testSession.get(BasketDTO.class);
        ArticleDTO articleDTO = testSession.get(ArticleDTO.class);
        List<UUID> itemList = new ArrayList<>();
        itemList.add(articleDTO.getId());
        BasketDTO updatedBasketDTO =
                restClient
                        .postToUri(URI.create(basketURI + "/" + basketDTO.getId()),
                                itemList,
                                new ParameterizedTypeReference<BasketDTO>() {});
        assertThat(updatedBasketDTO).isNotNull();
        testSession.put(BasketDTO.class, updatedBasketDTO);
    }

    public void thenBasketContainsTheItem() {
        BasketDTO basketDTO = testSession.get(BasketDTO.class);
        ArticleDTO articleDTO = testSession.get(ArticleDTO.class);
        assertThat(basketDTO.getArticles()).contains(articleDTO);

    }
}
