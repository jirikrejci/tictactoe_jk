package com.jirikrejci.tictactoe_jk.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.jirikrejci.tictactoe_jk.R;
import com.jirikrejci.tictactoe_jk.model.Board;
import com.jirikrejci.tictactoe_jk.model.Player;

public class TicTacToeActivity extends AppCompatActivity {

    private static String TAG = TicTacToeActivity.class.getSimpleName();

    View grpWinnerGroup;
    TextView txtWinnerLabel;
    ViewGroup buttonGrid;

    Board modelBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tictactoe);

        grpWinnerGroup = (View) findViewById(R.id.winnerGroup);
        txtWinnerLabel = (TextView) findViewById(R.id.winnerPlayerLabel);
        buttonGrid = (ViewGroup) findViewById(R.id.buttonGrid);

        modelBoard = new Board();   // model  resatrs is included in constructor
    }

    public void onCellClicked(View view) {
        String tag = view.getTag().toString();
        int i = Integer.valueOf(tag.substring(0,1));
        int j = Integer.valueOf(tag.substring(1,2));

        Log.d (TAG, "Stisknuto [" + i + ", " + j + "]");
        Player playerThatMoved = modelBoard.mark(i,j);

        if (playerThatMoved != null) {
            ((TextView) view).setText(playerThatMoved.toString());
            if (modelBoard.getWinner() != null) {
                grpWinnerGroup.setVisibility(View.VISIBLE);
                txtWinnerLabel.setText(playerThatMoved.name());
            }
        }
    }


    private void resetGame() {
        modelBoard.restart();
        grpWinnerGroup.setVisibility(View.GONE);
        txtWinnerLabel.setText("");

        for (int i = 0; i < buttonGrid.getChildCount(); i++) {
            ((Button) buttonGrid.getChildAt(i)).setText("");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tictactoe_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_reset_game:
                resetGame();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
