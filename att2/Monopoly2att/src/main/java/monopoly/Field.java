package monopoly;

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

    public void buyFreeField(Player[] players, int i, int x){
        if (players[i].getBalance() > getPrice()) {
            System.out.println("Желаете купить данное поле? Стоимость: " + getPrice() + ". Введите y если да, и n если нет:");
            String answer = cp.getNextLine();
            if (answer.equals("y")) {
                players[i].setBalance(players[i].getBalance() - getPrice());
                boolean[] cards = players[i].getCards();
                cards[x] = true;
                players[i].setCards(cards);
                setWhoseField(i + 1);
                System.out.println("Поздравляем с приобретением");
                log.info("Покупка поля");
            } else {
                log.info("Отказ от покупки поля");
            }
        } else {
            System.out.println("К сожалению, недостаточно средств для покупки поля. Нажмите enter чтобы продолжить");
            log.info("Не хватает средств для покупки поля");
            cp.getNextLine();
        }
    }

    public void payTax(Player[] players, Field[] fields, int i){
        System.out.println("Вы должны заплатить налог в размере:" + getRent());
        System.out.println("Введите /pay чтобы заплатить, или /surrender чтобы сдаться");
        String answer = cp.getNextLine();
        if (answer.equals("/surrender")) {
            players[i].surrender(players, fields, i);
            System.out.println("Вы сдались");
            System.out.println();
        } else if (players[i].getBalance() < getRent()) {
            System.out.println("Вы банкрот");
            players[i].surrender(players, fields, i);
        } else {
            players[i].setBalance(players[i].getBalance() - getRent());
            log.info("Оплата налога");
        }
    }

    public void payRent(Player[] players, Field[] fields, int i){
        System.out.println("Данное поле принадлежит игроку " + getWhoseField() + ". Вы должны ему заплатить ренту в размере: " + getRent());
        System.out.println("Введите /pay чтобы заплатить, или /surrender чтобы сдаться");
        String answer = cp.getNextLine();
        if (answer.equals("/surrender")) {
            players[i].surrender(players, fields, i);
            System.out.println("Вы сдались");
            System.out.println();
        } else if (players[i].getBalance() < getRent()) {
            System.out.println("Вы банкрот");
            players[i].surrender(players, fields, i);
        } else {
            players[i].setBalance(players[i].getBalance() - getRent());
            players[getWhoseField() - 1].setBalance(players[getWhoseField() - 1].getBalance() + getRent());
            System.out.println("Рента оплачена");
            log.info("Оплата ренты");
        }
    }

    public void addFilial(Player[] players, int i, int num){
        Scanner scanner = new Scanner(System.in);
        if (getWhoseField() == i + 1 && players[i].getBalance() > ((num + 1) / 5) * 250){
            System.out.println("Филиал будет стоить: " + ((num + 1) / 5) * 250 + ", чтобы принять введите y, чтобы отменить n. Ваш баланс: " + players[i].getBalance());
            String answer = cp.getNextLine();
            if (answer.equals("y")) {
                players[i].setBalance(players[i].getBalance() - (num / 5) * 250);
                players[i].addBranch(num);
                setRent(getRent() * 2);
                System.out.println("Филиал успешно построен. Текущий баланс: " + players[i].getBalance());
                log.info("Построение филиала");
            }
        }
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
