package se.omegapoint.store.glue.dto;


import java.util.UUID;

public class ArticleDTO {

    private UUID id;

    private String descriptor;

    private Long price;


    protected ArticleDTO() {
    }

    public ArticleDTO(UUID id, String descriptor, Long price) {
        this.id = id;
        this.descriptor = descriptor;
        this.price = price;
    }

    public UUID getId(){
        return id;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public Long getPrice(){ return price; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleDTO that = (ArticleDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (descriptor != null ? !descriptor.equals(that.descriptor) : that.descriptor != null) return false;
        return price != null ? price.equals(that.price) : that.price == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (descriptor != null ? descriptor.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ArticleDTO{" +
                "id=" + id +
                ", descriptor='" + descriptor + '\'' +
                ", price=" + price +
                '}';
    }
}

