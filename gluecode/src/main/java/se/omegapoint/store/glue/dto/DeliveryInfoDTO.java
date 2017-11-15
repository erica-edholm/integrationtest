package se.omegapoint.store.glue.dto;

import java.util.UUID;

public class DeliveryInfoDTO {

    private UUID id;

    private String firstName;
    private String lastName;
    private String address;
    private PostalCodeDTO postalCode;
    private String city;

    public DeliveryInfoDTO(){

    }
}
