package com.danunez.connecttres;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
//import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.widget.GridLayout;

public class MainActivity extends AppCompatActivity {


    int player = 0;
    boolean isGameActive = true;
    int[] board = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View view){

        ImageView counter = (ImageView)view;
        System.out.println(view.getTag().toString());

        int tapped = Integer.parseInt(view.getTag().toString());

        if (board[tapped] == 2 && isGameActive) {
            board[tapped] = player;
            counter.setTranslationY(-1000f);


            if (player == 0) {
                counter.setImageResource(R.drawable.circle);
                player = 1;
            } else {
                counter.setImageResource(R.drawable.x);
                player = 0;
            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(200);

            for(int[] winPos: winningPositions){

                if (board[winPos[0]] == board[winPos[1]] &&
                        board[winPos[1]] == board[winPos[2]] &&
                        board[winPos[2]]!=2 ){
                        changeDisplayMessage("Player "+ board[winPos[0]]+" won!");
                    isGameActive = false;

                }else {

                    boolean gameIsOver = true;
                    for(int status : board){
                        if (status == 2){
                            gameIsOver = false;
                        }
                    }

                    if (gameIsOver){
                        changeDisplayMessage("D R A W !!!");
                    }
                }
            }
        }
    }

    public void changeDisplayMessage(String message){
        TextView textView = findViewById(R.id.winningMessageTextView);
        textView.setText(message);
        LinearLayout linearLayout = findViewById(R.id.myLinearLayout);
        linearLayout.setVisibility(View.VISIBLE);
    }

    public void playAgain(View view){
        isGameActive = true;
        LinearLayout linearLayout = findViewById(R.id.myLinearLayout);
        linearLayout.setVisibility(View.INVISIBLE);

        int player = 0;
        for (int i =0 ; i < board.length; i++){
            board[i] = 2;
        }
        GridLayout myGridLayout = findViewById(R.id.myGridLayout);
        for (int i = 0 ; i < myGridLayout.getChildCount() ; i++){
            ((ImageView)myGridLayout.getChildAt(i)).setImageResource(0);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
