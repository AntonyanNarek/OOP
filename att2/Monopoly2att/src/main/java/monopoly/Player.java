package monopoly;

import commandproviders.CommandProvider;

import java.util.ArrayList;
import java.util.Arrays;

import static monopoly.Main.log;

public class Player {

    private int balance;                      // Баланс игрока
    private int location;                    // Местоположение на поле
    private boolean[] cards;                // Набор карточек
    private boolean[] monopolies;          // Монополии игрока
    private int[] branch;           // Филлиалы игрока
    private int blockedMoves;

    public Player(){
    }

    private CommandProvider cp;

    public Player(int balance, int location, boolean[] cards, boolean[] monopolies, int[] branch, int blockedMoves) {
        this.balance = balance;
        this.location = location;
        this.monopolies = monopolies;
        this.branch = branch;
        this.cards = cards;
        this.blockedMoves = blockedMoves;
    }

    public int getBalance() {
        return balance;
    }

    public int getLocation() {
        return location;
    }

    public boolean[] getCards() {
        return Arrays.copyOf(cards, cards.length);
    }

    public int[] getBranch() {
        return Arrays.copyOf(branch, branch.length);
    }

    public int getBlockedMoves() {
        return blockedMoves;
    }

    public void setBlockedMoves(int blockedMoves) {
        this.blockedMoves = blockedMoves;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public void setCards(boolean[] cards) {
        this.cards = cards;
    }

    public void addBranch(int numOfCard) {
        branch[numOfCard]++;
    }

    public void deleteBranch(int numOfCard) {
        branch[numOfCard]--;
    }

    public boolean checkField(int numOfField) {
        return cards[numOfField];
    }

    public void checkCards(Field[] fields, int i) {
        for (int k = 0; k < getCards().length; k++) {
            if (getCards()[k]) {
                System.out.println(fields[i].whichIsField(k));
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public ArrayList<Integer> checkMono(Field[] fields, int p) {
        int count = 0;
        ArrayList<Integer> numOfMono = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            count = 0;
            for (int j = i * 5; j < (i + 1) * 5; j++) {
                if (fields[j].getType() == 0 && fields[j].getWhoseField() == p + 1) {
                    count++;
                    if (count == 3) {
                        numOfMono.add(i);
                    }
                }
            }
        }
        return numOfMono;
    }

    public void viewMonoCards(Field[] fields, int i, int size){
        int[] b = getBranch();
        for (int j = 0; j < size; j++ ) {
            for (int k = j * 5; k < (j + 1) * 5; k++) {
                if (fields[k].getType() == 0 && fields[k].getWhoseField() == i + 1) {
                    System.out.print(fields[k].whichIsField(k));
                    System.out.print("(" + k + ") - " + b[k] + " уровень; ");
                }
            }
        }
        System.out.println();
    }

    public void surrender(Player[] players, Field[] fields, int i) {   // Если игрок хочет сдаться
        players[i] = null;
        for (int j = 0; j < 40; j++) {
            if (fields[j].getWhoseField() == i + 1) {
                fields[j].setWhoseField(0);
            }
        }
        log.info("Игрок сдался");
    }

    public boolean[] getMonopolies() {
        return Arrays.copyOf(monopolies, monopolies.length);
    }

    public void setMonopolies(boolean[] monopolies) {
        this.monopolies = monopolies;
    }
}
