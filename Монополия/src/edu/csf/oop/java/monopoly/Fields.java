package edu.csf.oop.java.monopoly;

import java.util.Scanner;

public class Fields {
    private int type;         // Тип поля: 0 - обычное поле, 1 - карта события, 2 - штраф, 3 - тюрьма, 4 - казино
    private int price;        // Цена поля
    private int whoseField;   // Кому принадлежит поле
    private int rent;         // Стоимость аренды

    public Fields(int type, int price, int whoseField, int rent){
        this.type = type;
        this.price = price;
        this.whoseField = whoseField;
        this.rent = rent;
    }

    public int getPrice(){
        return price;
    }

    public int getRent(){
        return rent;
    }

    public int getType(){
        return type;
    }

    public int getWhoseField(){
        return whoseField;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public void setType(int type){
        this.type = type;
    }

    public void setRent(int rent){
        this.rent = rent;
    }

    public void setWhoseField(int whoseField){
        this.whoseField = whoseField;
    }

    public static void buyFreeField(Player[] players, Fields[] fields, int i, int x){
        Scanner scanner = new Scanner(System.in);
        if (players[i].getBalance() > fields[x].getPrice()) {
            System.out.println("Would you like to buy this field? Cost: " + fields[x].getPrice() + ". Enter y if yes, and n if no:");
            String answer = scanner.next();
            if (answer.equals("y")) {
                players[i].setBalance(players[i].getBalance() - fields[x].getPrice());
                boolean[] cards = players[i].getCards();
                cards[x] = true;
                players[i].setCards(cards);
                fields[x].setWhoseField(i + 1);
                System.out.println("Congratulations on your purchase");
            }
        } else {
            System.out.println("Unfortunately, there are not enough funds to purchase this field. Press any key to continue");
            scanner.next();
        }
    }

    public static void payRent(Player[] players, Fields[] fields, int i, int x){
        Scanner scanner = new Scanner(System.in);
        System.out.println("This field belongs to the player " + fields[x].getWhoseField() + ". Now you have to pay him an annuity in the amount of " + fields[x].getRent());
        System.out.println("Press Enter to continue, or /surrender to give up");
        String answer = scanner.next();
        if (answer.equals("/surrender")) {
            GameLogic.surrender(i, players, fields);
            System.out.println("You gave up");
            System.out.println();
        } else if (players[i].getBalance() < fields[x].getRent()) {
            System.out.println("You are bankrupt");
            GameLogic.surrender(i, players, fields);
        } else {
            players[i].setBalance(players[i].getBalance() - fields[x].getRent());
            players[fields[x].getWhoseField() - 1].setBalance(players[fields[x].getWhoseField() - 1].getBalance() + fields[x].getRent());
            System.out.println("Rent paid");
        }
    }

    public static void whichIsField(int x){
        switch (x){
            case 0:
                System.out.print(" Start");
                break;
            case 1:
                System.out.print(" 'Boss'");
                break;
            case 2:
                System.out.print(" 'Event'");
                break;
            case 3:
                System.out.print(" 'Chanel'");
                break;
            case 4:
                System.out.print(" 'Tax'");
                break;
            case 5:
                System.out.print(" 'Mercedes-Benz'");
                break;
            case 6:
                System.out.print(" 'Puma'");
                break;
            case 7:
                System.out.print(" 'Adidas'");
                break;
            case 8:
                System.out.print(" 'Event'");
                break;
            case 9:
                System.out.print(" 'Nike'");
                break;
            case 10:
                System.out.print(" 'Jail'");
                break;
            case 11:
                System.out.print(" 'Facebook'");
                break;
            case 12:
                System.out.print(" 'YouTube'");
                break;
            case 13:
                System.out.print(" 'Event'");
                break;
            case 14:
                System.out.print(" 'Instagram'");
                break;
            case 15:
                System.out.print(" 'BMW'");
                break;
            case 16:
                System.out.print(" 'Fanta'");
                break;
            case 17:
                System.out.print(" 'Event'");
                break;
            case 18:
                System.out.print(" 'Sprite'");
                break;
            case 19:
                System.out.print(" 'Coca-Cola'");
                break;
            case 20:
                System.out.print(" 'Casino'");
                break;
            case 21:
                System.out.print(" Russian airlines");
                break;
            case 22:
                System.out.print(" 'European airlines'");
                break;
            case 23:
                System.out.print(" 'Even'");
                break;
            case 24:
                System.out.print(" 'American airlines'");
                break;
            case 25:
                System.out.print(" 'Audi'");
                break;
            case 26:
                System.out.print(" 'KFC'");
                break;
            case 27:
                System.out.print(" 'Event'");
                break;
            case 28:
                System.out.print(" 'Burger King'");
                break;
            case 29:
                System.out.print(" 'McDonalds'");
                break;
            case 30:
                System.out.print(" 'Arest'");
                break;
            case 31:
                System.out.print(" 'Holiday hotel'");
                break;
            case 32:
                System.out.print(" 'Radisson hotel'");
                break;
            case 33:
                System.out.print(" 'Event'");
                break;
            case 34:
                System.out.print(" 'Novotel'");
                break;
            case 35:
                System.out.print(" 'Land Rover'");
                break;
            case 36:
                System.out.print(" 'Штраф'");
                break;
            case 37:
                System.out.print(" 'Apple'");
                break;
            case 38:
                System.out.print(" 'Event'");
                break;
            case 39:
                System.out.print(" 'Nokia'");
                break;
        }
    }

    public static void whereIsPlayer(int x){
        System.out.print("You stood on the field: " + x);
        whichIsField(x);
        System.out.println();
    }
}
