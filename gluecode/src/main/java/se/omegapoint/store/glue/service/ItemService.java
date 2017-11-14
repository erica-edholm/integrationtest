package se.omegapoint.store.glue.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import se.omegapoint.store.glue.RestClient;
import se.omegapoint.store.glue.TestSession;
import se.omegapoint.store.glue.dto.ArticleDTO;
import se.omegapoint.store.glue.exceptions.NotFoundException;

import java.net.URI;
import java.util.List;

import static se.omegapoint.store.glue.Constants.BASE_URL;

@Component
public class ItemService {

    private final String itemURI = BASE_URL + "/api/articles";

    private final RestClient restClient;

    private final TestSession testSession;

    public ItemService(RestClient restClient, TestSession testSession) {
        this.restClient = restClient;
        this.testSession = testSession;
    }

    public void givenOneRandomArticle() {
        List<ArticleDTO> articleDTOS = restClient.get(URI.create(itemURI), new ParameterizedTypeReference<List<ArticleDTO>>() {});
        ArticleDTO randomItem =  articleDTOS.stream().findAny().orElseThrow(() -> new NotFoundException("Could not find any articles"));
        testSession.put(ArticleDTO.class, randomItem);
    }
}
