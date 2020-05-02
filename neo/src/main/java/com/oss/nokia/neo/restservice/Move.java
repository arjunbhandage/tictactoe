
package com.oss.nokia.neo.restservice;

public class Move {

    private Character row;
    private Character column;

    public Move() {
    }

    public Move(Character row, Character column) {
        this.row = row;
        this.column = column;
    }

    public Character getRow() {
        return row;
    }

    public Character getColumn() {
        return column;
    }

}
