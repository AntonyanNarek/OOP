package monopoly;

import com.fasterxml.jackson.databind.ObjectMapper;
import commandproviders.CommandProvider;
import commandproviders.ConsoleCommandProvider;
import commandproviders.ScriptedCommandProvider;
import models.Events;
import models.Field;
import models.Player;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static monopoly.Main.log;

public class GameLogic {

    private CommandProvider cp;

    public void startPlay(Field[] fields, Player[] players, int whoIsTurn, boolean isDemo) throws IOException {
        cp = new ConsoleCommandProvider();
        if (isDemo){
            cp = new ScriptedCommandProvider();
        }
        int n = players.length;
        int x = 0;
        while (n > 1) {
            label:
            for (int i = whoIsTurn; i < players.length; i++) {                                             // Цикл игры, пока не останется один игрок, игра будет продолжаться
                if (players[i] == null) {                                                                 // Если игрок с таким номером уже выбыл
                    continue;
                }
                if (players[i].getBlockedMoves() > 0){                                                 // Если у игрока пропуск хода, либо он в тюрьме
                    players[i].setBlockedMoves(players[i].getBlockedMoves() - 1);
                    continue;
                }
                log.info("Ход игрока " + (i + 1));
                System.out.println("Ход игрока " + (i + 1) + ". Баланс: " + players[i].getBalance());
                System.out.println("Чтобы бросить кубики, введите /dice, посмотреть карточки - /cards, добавить филлиал - /add, сдаться - /surrender, /save - сохранить игру");
                boolean yourTurn = true;
                while (yourTurn) {
                    String ans = cp.getNextLine();
                    switch (ans) {
                        case "/dice" -> {
                            x = players[i].getLocation() + dice();
                            if (x >= 40) {                                                                             // Если круг был пройден
                                System.out.println("Поздравляем с прохождением круга, вы получаете 2000 на баланс");
                                players[i].setBalance(players[i].getBalance() + 2000);
                                x = x - 40;
                            }
                            players[i].setLocation(x);
                            fields[x].whereIsPlayer(x);
                            switch (fields[x].getType()) {
                                case 0 -> {
                                    int g = fields[x].getWhoseField();
                                    if (g == 0) {                                                 // Если поле никому не принадлежит
                                        buyFreeField(players, fields, i, x);                   // Покупка поля
                                    } else if (g != i + 1) {                                    // Встал на чужое поле
                                        payRent(players, fields, i, x);                      // Плата аренды
                                        if (players[i] == null) {
                                            n -= 1;
                                            if (n == 1) {
                                                break label;
                                            }
                                        }
                                    }
                                }
                                case 1 -> {
                                    Events event = new Events();
                                    event.getRandomEvent(players[i]);                        // Вытаскивает случайную карточку с событием
                                }
                                case 2 -> payTax(players, fields, i, x);                    // Оплата штрафа
                                case 3 -> jail(players[i]);                                // Тюрьма
                            }
                            System.out.println();
                            yourTurn = false;
                        }
                        case "/cards" -> players[i].checkCards(fields, i);                      // Проверить свои карточки
                        case "/surrender" -> {                                                 // Сдаться
                            players[i].surrender(players, fields, i);
                            if (players[i] == null) {
                                n -= 1;
                                if (n == 1) {
                                    break label;
                                }
                            }
                            yourTurn = false;
                        }
                        case "/add" -> addF(players, fields, i);                             // Добавить филиал
                        case "/save" -> saveGame(players, fields, i);                       // Сохранить игру
                    }
                }
                System.out.println();
            }
            whoIsTurn = 0;
        }
        whoIsWinner(players);
    }

    private void saveGame(Player[] players, Field[] fields, int i) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(Paths.get("whoIsTurn.json").toFile(), i);
        mapper.writeValue(Paths.get("fields.json").toFile(), fields);
        mapper.writeValue(Paths.get("players.json").toFile(), players);
        log.info("Сохранение игры");
    }

    private void addF(Player[] players, Field[] fields, int i){
        ArrayList<Integer> list = players[i].checkMono(fields, i);
        if (list.size() > 0) {
            players[i].viewMonoCards(fields, i, list.size());
            System.out.println("Введите номер карточки, на которую желаете добавить филиал");
            int num = Integer.parseInt(cp.getNextLine());
            addFilial(players, fields, i, num);
        } else {
            System.out.println("У вас нет монополий");
        }
    }

    private void jail(Player player){
        System.out.println("Вы отправляетесь в тюрьму с противоположным направлением движения");
        player.setLocation(10);
        player.setBlockedMoves(2);
        log.info("Перемещение на поле тюрьмы");
    }

    private void whoIsWinner(Player[] players){
        for (int i = 0; i < players.length; i++) {
            if (players[i] != null) {
                System.out.println("Поздравляем игрока " + (i + 1) + " с победой");
                log.info("Победил игрок " + (i + 1));
            }
        }
    }

    public int dice() {
        int x = (int) (Math.random() * 10) + 2;
        log.info("На кубиках выпало число: " + x);
        return x;
    }

    private void buyFreeField(Player[] players, Field[] fields, int i, int x){
        if (players[i].getBalance() > fields[x].getPrice()) {
            System.out.println("Желаете купить данное поле? Стоимость: " + fields[x].getPrice() + ". Введите y если да, и n если нет:");
            String answer = cp.getNextLine();
            if (answer.equals("y")) {
                players[i].setBalance(players[i].getBalance() - fields[x].getPrice());
                boolean[] cards = players[i].getCards();
                cards[x] = true;
                players[i].setCards(cards);
                fields[x].setWhoseField(i + 1);
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

    private void payTax(Player[] players, Field[] fields, int i, int x){
        System.out.println("Вы должны заплатить налог в размере:" + fields[x].getRent());
        System.out.println("Введите /pay чтобы заплатить, или /surrender чтобы сдаться");
        String answer = cp.getNextLine();
        if (answer.equals("/surrender")) {
            players[i].surrender(players, fields, i);
            System.out.println("Вы сдались");
            System.out.println();
        } else if (players[i].getBalance() < fields[x].getRent()) {
            System.out.println("Вы банкрот");
            players[i].surrender(players, fields, i);
        } else {
            players[i].setBalance(players[i].getBalance() - fields[x].getRent());
            log.info("Оплата налога");
        }
    }

    private void payRent(Player[] players, Field[] fields, int i, int x){
        System.out.println("Данное поле принадлежит игроку " + fields[x].getWhoseField() + ". Вы должны ему заплатить ренту в размере: " + fields[x].getRent());
        System.out.println("Введите /pay чтобы заплатить, или /surrender чтобы сдаться");
        String answer = cp.getNextLine();
        if (answer.equals("/surrender")) {
            players[i].surrender(players, fields, i);
            System.out.println("Вы сдались");
            System.out.println();
        } else if (players[i].getBalance() < fields[x].getRent()) {
            System.out.println("Вы банкрот");
            players[i].surrender(players, fields, i);
        } else {
            players[i].setBalance(players[i].getBalance() - fields[x].getRent());
            players[fields[x].getWhoseField() - 1].setBalance(players[fields[x].getWhoseField() - 1].getBalance() + fields[x].getRent());
            System.out.println("Рента оплачена");
            log.info("Оплата ренты");
        }
    }

    private void addFilial(Player[] players, Field[] fields, int i, int num){
        Scanner scanner = new Scanner(System.in);
        if (fields[num].getWhoseField() == i + 1 && players[i].getBalance() > ((num + 1) / 5) * 250){
            System.out.println("Филиал будет стоить: " + ((num + 1) / 5) * 250 + ", чтобы принять введите y, чтобы отменить n. Ваш баланс: " + players[i].getBalance());
            String answer = cp.getNextLine();
            if (answer.equals("y")) {
                players[i].setBalance(players[i].getBalance() - (num / 5) * 250);
                players[i].addBranch(num);
                fields[num].setRent(fields[num].getRent() * 2);
                System.out.println("Филиал успешно построен. Текущий баланс: " + players[i].getBalance());
                log.info("Построение филиала");
            }
        }
    }
}
