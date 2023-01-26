package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView score_p1;
    private TextView score_p2;
    private Button[][] buttons = new Button[3][3];
    private int counter;
    private boolean player1Turn = true;

    private int p1Points;
    private int p2Points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        score_p1 = findViewById(R.id.tv_p1);
        score_p2 = findViewById(R.id.tv_p2);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resourceID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button resetButton = findViewById(R.id.button_reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                resetTheGame();

            }
        });


    }

    @Override
    public void onClick(View view) {
        if (!(((Button) view).getText().toString().equals(""))) {
            return;
        }
        if (player1Turn) {
            ((Button) view).setText("X");
        } else {
            ((Button) view).setText("O");
        }
        counter++;

        if (checkForWin()) {
            if (player1Turn) {
                player1wins();
            } else {
                player2wins();
            }

        } else if (counter == 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }
    }


    private boolean checkForWin() {

        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")) {
                return true;
            }
        }
        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")) {
            return true;
        }
        if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }


        private void player1wins(){

            p1Points++;
            Toast.makeText(this, "Player 1 Wins!", Toast.LENGTH_SHORT).show();
            updatePointsText();
            resetTheBoard();
        }
        private void player2wins(){

            p2Points++;
            Toast.makeText(this, "Player 2 Wins!", Toast.LENGTH_SHORT).show();
            updatePointsText();
            resetTheBoard();
        }
        private void draw(){

            Toast.makeText(this, "IT'S A DRAW!", Toast.LENGTH_SHORT).show();
            resetTheBoard();
        }
        private void updatePointsText(){
            score_p1.setText("Player 1: " + p1Points );
            score_p2.setText("Player 2: " + p2Points);
        }
        private void resetTheBoard(){
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    buttons[i][j].setText("");
                }
            }
            counter=0;
            player1Turn = true;
        }

        private void resetTheGame(){
            p1Points = 0;
            p2Points = 0;
            updatePointsText();;
            resetTheBoard();
        }


    }


