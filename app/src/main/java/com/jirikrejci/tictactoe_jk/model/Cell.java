package com.jirikrejci.tictactoe_jk.model;

/**
 * Created by jirikrejci on 04.03.17.
 */

public class Cell {
    private Player mValue;

    public void setValue (Player value) {
        mValue = value;
    }

    public Player getValue () {
        return mValue;
    }

}
