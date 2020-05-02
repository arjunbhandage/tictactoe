
package com.oss.nokia.neo.restservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.oss.nokia.neo.exceptions.InvalidMoveException;

public class Game {

    private static final String MATCH_ENDED_IN_DRAW = "Match ended in Draw!";
    private Random rand = new Random();
    protected boolean flag;
    private String result = "Match Ongoing!";
    private String name;
    private Character compChar = 'o';
    private Character character;
    private int[][] array = new int[3][3];

    public Game(String name, Character character) {
        this.name = name;
        this.character = character;
    }

    public Game() {
    }

    public Character getCompChar() {
        return compChar;
    }

    protected void setComputerCharacter(Character compChar) {
        this.compChar = compChar;
    }

    public String getName() {
        return name;
    }

    public Character getCharacter() {
        return character;
    }

    public int[][] getGame() {
        return array;
    }

    public void makeMove(Move move) {
        int row = getInt(move.getRow());
        int column = getInt(move.getColumn());
        if (array[row][column] != 0) {
            throw new InvalidMoveException();
        } else {
            array[row][column] = 1;
        }
        if (checkResult(1)) {
            flag = true;
            result = "Player won!";
            return;
        }
        List<Index> availableMoves = getAvailableMoves();
        if (availableMoves.isEmpty()) {
            flag = true;
            result = MATCH_ENDED_IN_DRAW;
        }
    }

    private List<Index> getAvailableMoves() {
        List<Index> availableMoves = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (array[i][j] == 0) {
                    availableMoves.add(new Index(i, j));
                }
            }
        }
        return availableMoves;
    }

    private int getInt(Character character) {
        switch (character) {
        case 'A':
            return 0;
        case 'B':
            return 1;
        case 'C':
            return 2;

        default:
            throw new InvalidMoveException();
        }
    }

    class Index {

        int row;

        int column;

        public Index(int row, int column) {
            this.row = row;
            this.column = column;
        }

    }

    public void computerMove() {
        List<Index> availableMoves = getAvailableMoves();
        if (availableMoves.isEmpty()) {
            flag = true;
            result = MATCH_ENDED_IN_DRAW;
            return;
        }
        int randInt = rand.nextInt(availableMoves.size());
        Index move = availableMoves.get(randInt);
        array[move.row][move.column] = -1;
        if (checkResult(-1)) {
            flag = true;
            result = "Computer won the match!";
        } else if (availableMoves.size()==1) {
            flag = true;
            result = MATCH_ENDED_IN_DRAW;
        }
    }

    private boolean checkResult(int player) {

        return ((array[0][0] + array[0][1] + array[0][2] == player * 3)
                || (array[1][0] + array[1][1] + array[1][2] == player * 3)
                || (array[2][0] + array[2][1] + array[2][2] == player * 3)
                || (array[0][0] + array[1][0] + array[2][0] == player * 3)
                || (array[0][1] + array[1][1] + array[2][1] == player * 3)
                || (array[0][2] + array[1][2] + array[2][2] == player * 3)
                || (array[0][0] + array[1][1] + array[2][2] == player * 3)
                || (array[2][0] + array[1][1] + array[0][2] == player * 3));

    }

    private char marker(int val) {
        if (val == 1) {
            return character;
        } else if (val == -1) {
            return compChar;
        } else {
            return ' ';
        }

    }

    public String board() {
        return "Player Name: " + name + "\n" + "Player Character: " + character + "\n\n" + "      A B C\n"
                + "   A |" + marker(array[0][0]) + "|" + marker(array[0][1]) + "|" + marker(array[0][2])
                + "|\n" + "   B |" + marker(array[1][0]) + "|" + marker(array[1][1]) + "|"
                + marker(array[1][2]) + "|\n" + "   C |" + marker(array[2][0]) + "|" + marker(array[2][1])
                + "|" + marker(array[2][2]) + "|\n\n" + result;
    }

}
