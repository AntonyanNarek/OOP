package edu.csf.oop.java.monopoly;

import edu.csf.oop.java.monopoly.logging.Log;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Log.logging();
        Scanner scanner = new Scanner(System.in);
        Fields[] fields = new Fields[40];
        for (int i = 0; i < 40; i++){
            fields[i] = new Fields(0, 600 + 50 * i, 0, 15 * i);
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
        fields[36].setType(2);
        fields[30].setType(3);
        fields[20].setType(4);
        System.out.println("Enter the number of players (from 2 to 5 players)");
        int nPlayers = scanner.nextInt();
        Player[] players = new Player[nPlayers];
        boolean[] startCards = new boolean[40];
        Arrays.fill(startCards, false);
        int[] branch = new int[40];
        Arrays.fill(branch, 0);
        boolean[] mono = new boolean[8];
        Arrays.fill(mono, false);
        for (int i = 0; i < nPlayers; i++){
            players[i] = new Player(10000, 0, mono, startCards, branch);
        }
        GameLogic.startPlay(fields, players);
    }
}
