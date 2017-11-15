package se.omegapoint.store.glue.domain;

import org.springframework.core.ParameterizedTypeReference;
import se.omegapoint.store.glue.RestClient;
import se.omegapoint.store.glue.dto.ArticleDTO;
import se.omegapoint.store.glue.dto.MoneyDTO;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static se.omegapoint.store.glue.Constants.BASE_URL;

public class Articles {

    private final String articleURI = BASE_URL + "/api/articles";

    private RestClient restClient;

    private List<ArticleDTO> validArticleList;

    private List<ArticleDTO> invalidArticleList;

    Articles(){
        validArticleList = new ArrayList<>();
        invalidArticleList = new ArrayList<>();
        restClient = new RestClient();
    }


    public ArticleDTO saveRandomArticleFromArticleList() {
        Random random = new Random();
        List<ArticleDTO> articleDTOS = restClient.get(URI.create(articleURI), new ParameterizedTypeReference<List<ArticleDTO>>() {});
        ArticleDTO randomArticle = articleDTOS.get(random.nextInt(articleDTOS.size()));
        validArticleList.add(randomArticle);
        return randomArticle;
    }

    public void saveInvalidArticle(ArticleDTO invalidArticle) {
        invalidArticleList.add(invalidArticle);
    }


    public List<ArticleDTO> getSavedValidArticles() {
        assertThat(validArticleList).as("No previously saved valid articles found").isNotEmpty();
        return validArticleList;
    }

    public List<ArticleDTO> getSavedInvalidArticles() {
        assertThat(invalidArticleList).as("No previously saved invalid articles found").isNotEmpty();
        return invalidArticleList;
    }

    public void updatePriceTo(Long newprice) {
        validArticleList.forEach(article -> article.setPrice(new MoneyDTO(newprice)));
        invalidArticleList.forEach(article -> article.setPrice(new MoneyDTO(newprice)));
    }
}
