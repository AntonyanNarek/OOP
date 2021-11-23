package monopoly;

import com.fasterxml.jackson.databind.ObjectMapper;
import commandproviders.CommandProvider;
import commandproviders.ConsoleCommandProvider;
import commandproviders.ScriptedCommandProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        ConsoleCommandProvider cp = new ConsoleCommandProvider();
        System.out.println("Загрузить игру ( /load ), начать новую ( /new ) или запустить демонстрационный вариант (/demo)?");
        String ans = cp.getNextLine();
        switch (ans) {
            case "/new" -> {
                newGame();
                log.info("Новая игра");
            }
            case "/load" -> {
                loadGame();
                log.info("Загрузка сохранения игры");
            }
            case "/demo" -> {
                demoTest();
                log.info("Запуск демонстраицонного теста");
            }
        }
    }

    private static void newGame() throws IOException {
        ConsoleCommandProvider cp = new ConsoleCommandProvider();
        Field[] fields = new Field[40];
        System.out.println("Введите количество игроков (минимум 2 игрока, рекомендованное максимальное количество - 5 игроков)");
        int nPlayers = Integer.parseInt(cp.getNextLine());
        Player[] players = new Player[nPlayers];
        configArrays(fields, players);
        log.info("Игра началась");
        GameLogic game = new GameLogic();
        game.startPlay(fields, players, 0, false);
    }

    private static void loadGame() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Integer whoIsTurn = mapper.readValue(Paths.get("whoIsTurn.json").toFile(), Integer.class);
        Field[] fields = mapper.readValue(Paths.get("fields.json").toFile(), Field[].class);
        Player[] players = mapper.readValue(Paths.get("players.json").toFile(), Player[].class);
        GameLogic game = new GameLogic();
        game.startPlay(fields, players, whoIsTurn, false);
    }

    private static void demoTest() throws IOException {
        Field[] fields = new Field[40];
        CommandProvider cp = new ScriptedCommandProvider();
        Player[] players = new Player[3];
        configArrays(fields, players);
        log.info("Игра началась");
        GameLogic game = new GameLogic();
        game.startPlay(fields, players, 0, true);
    }

    private static void configArrays(Field[] fields, Player[] players){
        for (int i = 0; i < 40; i++){
            fields[i] = new Field(0, 600 + 50 * i, 0, 15 * i);
        }
        fields[2].setType(1);
        fields[8].setType(1);
        fields[13].setType(1);
        fields[17].setType(1);
        fields[23].setType(1);
        fields[27].setType(1);
        fields[33].setType(1);
        fields[38].setType(1);
        fields[4].setType(2);
        fields[4].setRent(2000);
        fields[36].setType(2);
        fields[36].setRent(1000);
        fields[30].setType(3);
        fields[20].setType(4);
        boolean[] startCards = new boolean[40];
        Arrays.fill(startCards, false);
        int[] branch = new int[40];
        Arrays.fill(branch, 0);
        boolean[] mono = new boolean[8];
        Arrays.fill(mono, false);
        for (int i = 0; i < players.length; i++){
            players[i] = new Player(10000, 0,startCards, mono, branch, 0);
        }
    }

}
