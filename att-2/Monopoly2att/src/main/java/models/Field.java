package models;

import commandproviders.CommandProvider;

import java.util.Scanner;

import static monopoly.Main.log;

public class Field {

    private CommandProvider cp;

    private int type;                 // Тип поля: 0 - обычное поле, 1 - карта события, 2 - штраф, 3 - тюрьма, 4 - автосалоны
    private int price;               // Цена поля
    private int whoseField;         // Кому принадлежит поле
    private int rent;              // Стоимость аренды

    public Field(){
    }

    public Field(int type, int price, int whoseField, int rent){
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

    public String whichIsField(int x){
        String str = "";
        switch (x) {
            case 0 -> str = "Start";
            case 1 -> str = "'Boss'";
            case 2 -> str = "'Событие'";
            case 3 -> str = "'Chanel'";
            case 4 -> str = "'Налог'";
            case 5 -> str = "'Mercedes-Benz'";
            case 6 -> str = "'Puma'";
            case 7 -> str = "'Adidas'";
            case 8 -> str = "'Событие'";
            case 9 -> str = "'Nike'";
            case 10 -> str =" 'Тюрьма'";
            case 11 -> str =" 'Facebook'";
            case 12 -> str =" 'YouTube'";
            case 13 -> str =" 'Событие'";
            case 14 -> str =" 'Instagram'";
            case 15 -> str =" 'BMW'";
            case 16 -> str =" 'Fanta'";
            case 17 -> str =" 'Событие'";
            case 18 -> str =" 'Sprite'";
            case 19 -> str =" 'Coca-Cola'";
            case 20 -> str =" 'Казино'";
            case 21 -> str =" Russian airlines";
            case 22 -> str =" 'European airlines'";
            case 23 -> str =" 'Событие'";
            case 24 -> str =" 'American airlines'";
            case 25 -> str =" 'Audi'";
            case 26 -> str =" 'KFC'";
            case 27 -> str =" 'Событие'";
            case 28 -> str =" 'Burger King'";
            case 29 -> str =" 'McDonalds'";
            case 30 -> str =" 'Arest'";
            case 31 -> str =" 'Holiday hotel'";
            case 32 -> str =" 'Radisson hotel'";
            case 33 -> str =" 'Событие'";
            case 34 -> str =" 'Novotel'";
            case 35 -> str =" 'Land Rover'";
            case 36 -> str =" 'Штраф'";
            case 37 -> str =" 'Apple'";
            case 38 -> str =" 'Событие'";
            case 39 -> str = " 'Nokia'";
        }
        return str;
    }

    public void whereIsPlayer(int x){
        System.out.print("Вы встали на поле: " + x + whichIsField(x));
        log.info("Игрок встал на поле:" + whichIsField(x));
        System.out.println();
    }
}
