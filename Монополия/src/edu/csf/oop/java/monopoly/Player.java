package edu.csf.oop.java.monopoly;

import java.util.Arrays;

public class Player {
    private int balance;            // Баланс игрока
    private int location;           // Местоположение на поле
    private boolean[] cards;        // Набор карточек
    private boolean[] monopolies;   // Монополии игрока
    private int[] branch;           // Филлиалы игрока

    public Player(int balance, int location, boolean[] cards, boolean[] monopolies, int[] branch){
        this.balance = balance;
        this.location = location;
        this.branch = branch;
        this.cards = cards;
    }

    public int getBalance(){
        return balance;
    }

    public int getLocation(){
        return location;
    }

    public boolean[] getCards(){
        return Arrays.copyOf(cards, cards.length);
    }

    public int[] getBranch(){
        return Arrays.copyOf(branch, branch.length);
    }

    public void setBalance(int balance){
        this.balance = balance;
    }

    public void setLocation(int location){
        this.location = location;
    }

    public void setCards(boolean[] cards){
        this.cards = cards;
    }

    public void addBranch(int numOfCard){
        branch[numOfCard]++;
    }

    public void deleteBranch(int numOfCard){
        branch[numOfCard]--;
    }

    public boolean checkField(int numOfField){
        return cards[numOfField];
    }

    public static void checkCards(Player[] players, int i){
        for (int k = 0; k < players[i].getCards().length; k++){
            if (players[i].getCards()[k]){
                Fields.whichIsField(k);
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public boolean[] getMonopolies() {
        return Arrays.copyOf(monopolies, monopolies.length);
    }

    public void setMonopolies(boolean[] monopolies) {
        this.monopolies = monopolies;
    }
}
