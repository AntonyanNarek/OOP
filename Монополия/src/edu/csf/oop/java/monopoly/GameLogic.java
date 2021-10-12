package edu.csf.oop.java.monopoly;

import java.util.Scanner;

public class GameLogic {

    private static int dice() {
        return (int) (Math.random() * 10) + 2;
    }

    public static void surrender(int i, Player[] players, Fields[] fields) {   // Если игрок хочет сдаться
        players[i] = null;
        for (int j = 0; j < 40; j++) {
            if (fields[j].getWhoseField() == i + 1) {
                fields[j].setWhoseField(0);
            }
        }
    }

    public static void startPlay(Fields[] fields, Player[] players) {
        Scanner scanner = new Scanner(System.in);
        int n = players.length;
        int x = 0;
        while (n > 1) {
            for (int i = 0; i < players.length; i++) {
                if (players[i] == null) {
                    continue;
                }
                System.out.println("Player's move " + (i + 1) + ". Balance: " + players[i].getBalance());
                System.out.println("To roll the dice, enter /dice, view cards - /cards, add a branch to the field - /add, surrender - /surrender");
                String ans = scanner.next();
                if (ans.equals("/dice")) {
                    x = players[i].getLocation() + dice();
                    if (x >= 40) {
                        System.out.println("Congratulations on passing the circle, you get 2000 to the account");
                        players[i].setBalance(players[i].getBalance() + 2000);
                        x = x - 40;
                    }
                    players[i].setLocation(x);
                    Fields.whereIsPlayer(x);
                    int g = fields[x].getWhoseField();
                    if (g == 0) {                                      // Если поле никому не принадлежит
                        Fields.buyFreeField(players, fields, i, x);    // Покупка поля
                    } else if (g != i + 1) {                           // Встал на чужое поле
                        Fields.payRent(players, fields, i, x);         // Плата аренды
                        if (players[i] == null){
                            n -= 1;
                            if (n == 1){
                                break;
                            }
                        }
                    }
                    System.out.println();
                } else if (ans.equals("/cards")) {
                    Player.checkCards(players, i);
                } else if (ans.equals("/surrender")){
                    GameLogic.surrender(i, players, fields);
                    if (players[i] == null){
                        n -= 1;
                        if (n == 1){
                            break;
                        }
                    }
                } else if (ans.equals("/add")){
                }
                System.out.println();
            }
        }
        for (int i = 0; i < players.length; i++) {
            if (players[i] != null) {
                System.out.println("Congratulations to the player " + (i + 1) + " with victory");
            }
        }
    }
}
