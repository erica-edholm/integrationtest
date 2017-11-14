package se.omegapoint.store.glue.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BasketDTO {

    private UUID id;

    private List<ArticleDTO> articles;

    public BasketDTO() {
        id = UUID.randomUUID();
        articles = new ArrayList<>();
    }

    public BasketDTO(UUID id, List<ArticleDTO> articles) {

        this.id = id;
        this.articles = articles;
    }

    public void add(ArticleDTO articleDTO) {
        articles.add(articleDTO);
    }

    public UUID getId(){
        return id;
    }

    public List<ArticleDTO> getArticles() {
        return articles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasketDTO basketDTO = (BasketDTO) o;

        if (id != null ? !id.equals(basketDTO.id) : basketDTO.id != null) return false;
        return articles != null ? articles.equals(basketDTO.articles) : basketDTO.articles == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (articles != null ? articles.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BasketDTO{" +
                "id=" + id +
                ", articles=" + articles +
                '}';
    }
}

