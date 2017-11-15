package se.omegapoint.store.glue.dto;


import java.util.UUID;

public class ArticleDTO {

    private UUID id;

    private String description;

    private MoneyDTO price;


    protected ArticleDTO() {
    }

    public ArticleDTO(UUID id, String description, MoneyDTO price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    public UUID getId(){
        return id;
    }

    public String getDescription() {
        return description;
    }

    public MoneyDTO getPrice(){ return price; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleDTO that = (ArticleDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return price != null ? price.equals(that.price) : that.price == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ArticleDTO{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

    public void setPrice(MoneyDTO price) {
        this.price = price;
    }
}

