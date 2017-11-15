package se.omegapoint.store.glue.dto;

public class MoneyDTO {
    private Long ören;

    public MoneyDTO(){
    }

    public MoneyDTO(Long ören){
        this.ören = ören;

    }

    public Long getÖren() {
        return ören;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MoneyDTO moneyDTO = (MoneyDTO) o;

        return ören != null ? ören.equals(moneyDTO.ören) : moneyDTO.ören == null;
    }

    @Override
    public int hashCode() {
        return ören != null ? ören.hashCode() : 0;
    }
}
