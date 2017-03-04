package com.jirikrejci.tictactoe_jk.model;

import static com.jirikrejci.tictactoe_jk.model.Player.X;

/**
 * Created by jirikrejci on 04.03.17.
 */

public class Board {
    private Cell [][] mCells = new Cell[3][3];

    private Player mWinner;
    private Gamestate mGameState;
    private Player mCurrentTurnPlayer;

    enum Gamestate {IN_PROGRESS, FINISHED}

    public Board () {
        restart();
    }

    public void restart () {
        mWinner = null;
        mGameState = Gamestate.IN_PROGRESS;
        mCurrentTurnPlayer = X;
        clearCells();
    }

    public Player mark (int i, int j) {
        Player playerThatMoved = null;

        if (isValidMove(i, j)) {
            playerThatMoved = mCurrentTurnPlayer;

            mCells [i][j].setValue(mCurrentTurnPlayer);
            if (isWinningMoveByPlayer(mCurrentTurnPlayer, i, j)) {
                mWinner = mCurrentTurnPlayer;
                mGameState = Gamestate.FINISHED;
            } else {
                flipCurrentTurn();
            }
        }
        return playerThatMoved;
    }

    public Player getWinner () {
        return  mWinner;
    }


    private void clearCells () {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j<3; j++) {
                mCells [i][j] = new Cell();
            }
        }
    }

    private boolean isCellValueAllreadySet (int i, int j) {
        return mCells [i][j].getValue() != null;
    }

    private void flipCurrentTurn () {
        mCurrentTurnPlayer = (mCurrentTurnPlayer == X ? Player.O : X);
    }

    private boolean isOutOfBounds (int i, int j) {
        return (i < 0 || i > 2 || j < 0 || j > 2);
    }

    private boolean isValidMove (int i, int j) {
        return (
                mGameState == Gamestate.IN_PROGRESS
                && !isOutOfBounds(i,j)
                && !isCellValueAllreadySet(i,j)
                );
    }

    private boolean isWinningMoveByPlayer(Player player, int currentRow, int currentCol) {
        // kontrola radku
        if (   mCells[currentRow][0].getValue() == player
            && mCells[currentRow][1].getValue() == player
            && mCells[currentRow][2].getValue() == player
                )  {return true;}
        // kontrola sloupce
        if (   mCells[0][currentCol].getValue() == player
            && mCells[1][currentCol].getValue() == player
            && mCells[2][currentCol].getValue() == player
                ) {return true;}
        if (   mCells[0][0].getValue() == player
            && mCells[1][1].getValue() == player
            && mCells[2][2].getValue() == player
                ) {return true;}
        if (   mCells[0][2].getValue() == player
            && mCells[1][1].getValue() == player
            && mCells[2][0].getValue() == player
                ) {return true;}
        return false;
    }




}
