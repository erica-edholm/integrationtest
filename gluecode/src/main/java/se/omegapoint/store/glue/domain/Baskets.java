package se.omegapoint.store.glue.domain;

import se.omegapoint.store.glue.dto.BasketDTO;
import se.omegapoint.store.glue.dto.ErrorDTO;

import java.util.ArrayList;
import java.util.List;

public class Baskets {

    private List<Basket> baskets;
    int activeBasket;

    public Baskets() {
        baskets = new ArrayList<>();
        activeBasket = -1;

    }

    public BasketDTO createAndSaveBasket() {
        Basket basket = new Basket();
        BasketDTO createdBasket = basket.createAndSaveBasket();
        baskets.add(basket);
        activeBasket += 1;
        return createdBasket;
    }

    public BasketDTO addSavedValidArticlesToBasket() {
        return baskets.get(activeBasket).addSavedValidArticlesToBasket();
    }

    public void addSavedInvalidArticlesToBasket() {
        baskets.get(activeBasket).addSavedInvalidArticlesToBasket();
    }

    public BasketDTO getBasketWithArticles() {
        return baskets.get(activeBasket).getBasketWithArticles();
    }

    public Articles getArticles() {
        return baskets.get(activeBasket).getArticles();
    }

    public ErrorDTO getErrorResponse() {
        return baskets.get(activeBasket).getErrorResponse();
    }
}

