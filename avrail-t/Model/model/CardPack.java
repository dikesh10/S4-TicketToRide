package Model.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CardPack {
    private static ArrayList<Card> packCards = new ArrayList<>();
    private static Card[] cards = new Card[5];

    /*********************************************************************
     * Getters, setters, add/remove Methods
     *********************************************************************/
    public CardPack() {
        addCardToPack();
        addCardsToArray();
    }

    public ArrayList<Card> getPackCards() {
        return packCards;
    }

    public void addCardToPack() {
        int total = 110;// 98;//110
        int[] numberofcard = { 12, 12, 12, 12, 12, 14, 12, 12, 12 };
                             // 0   1   2   3  4    5   6   7   8

        while (total > 0) {
            int carte = (int) (Math.random() * 9);
            if (numberofcard[carte] > 0) {
                Card card = new Card(carte);
                addCardToPack(card);
                numberofcard[carte]--;
                total--;
            }
        }

        Collections.shuffle(packCards);

    }

    public void addCardToPack(Card c) {
        if (c != null) {
            packCards.add(c);
        } else {
            System.out.println("Can't add card to the pack, bc it is null");
        }
    }

    public void removeCardOfPack(Card c) {
        for (int i = 0; i < packCards.size(); i++) {
            if (packCards.get(i) == c) {
                packCards.remove(i);
                return;
            }
        }
        System.out.println("The card couldn't be removed from the Pack, bc it hasn't been found");
    }

    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    public void addCardsToArray() {
        for (int i = 0; i < cards.length; i++) {
            int lastelement = packCards.size() - 1;
            Card card = packCards.get(lastelement);
            cards[i] = card;
            packCards.remove(lastelement);
        }

    }

    public void RemoveCard(int i) {
        cards[i] = null;

    }

    public void makeVivid(int i) {
        if (cards[i] == null) {
            return;
        }

        int color = cards[i].getColor();
        int a = (color * 10) + color;
        if (color == 0) {
            cards[i].setColor(123);

        } else {
            cards[i].setColor(a);
        }
    }

    public void makeNormalImage(int i) {
        if (cards[i] == null) {
            return;
        }
        int color = cards[i].getColor();
        if (color == 123) {
            cards[i].setColor(0);
            return;
        }
        if (color >= 11 && color <= 88) {
            color = color / 10;
            cards[i].setColor(color);
            return;

        }

    }

    public void addImagesCard() {
        if (packCards.size() < 2) {
            return;
        }
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] == null) {
                int lastelement = packCards.size() - 1;
                Card card = packCards.get(lastelement);
                cards[i] = null;
                cards[i] = card;
                packCards.remove(lastelement);
            }

        }

    }

}
