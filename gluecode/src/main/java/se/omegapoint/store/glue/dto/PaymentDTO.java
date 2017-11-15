package se.omegapoint.store.glue.dto;

import java.util.UUID;

public class PaymentDTO {

    private UUID id;
    private BasketDTO basket;
    private MoneyDTO amountToPay;
    private boolean hasBeenPayed;

    public PaymentDTO(){
    }
}
