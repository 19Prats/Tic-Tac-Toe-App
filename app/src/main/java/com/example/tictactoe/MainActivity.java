package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    //current user
    // 0 = O
    // 1 = X
    int activeUser = 0;
    boolean gameOn = true;
    boolean tie = false;
    //track the images
    // 2 = empty
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    //declare the winning positions
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},
                                {0,3,6},{1,4,7},{2,5,8},
                                {0,4,8},{2,4,6}};
    TextView status;
    String xTurn = "X's turn to play";
    String oTurn = "O's turn to play";
    String xWon = "X has won";
    String oWon = "O has won";
    Button closeAppButton;
    Button resetAppButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        status = findViewById(R.id.statusBar);
        status.setText(oTurn);
        //setting event listener for close button
        closeAppButton = findViewById(R.id.button3);
        closeAppButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                exitApplication();
            }
        });
        //setting event listener for reset button
        resetAppButton = findViewById(R.id.button2);
        resetAppButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                reset(view);
            }
        });
    }
    public void userClicked(View view){
        //check if tie occured
        int tieCondition = 1;
        for(int i=0;i<gameState.length;i++){
            if(gameState[i] == 2) {
                tieCondition = 0;
                break;
            }else{
                continue;
            }
        }
        if(tieCondition == 1)
            tie = true;
        if(tie){
            status.setText("Oops! It's a tie!");
            findViewById(R.id.button2).setVisibility(View.VISIBLE);
            findViewById(R.id.button3).setVisibility(View.VISIBLE);
            gameOn = false;
        }
        ImageView img = (ImageView)view;
        int currentImage = Integer.parseInt(img.getTag().toString());
        if(gameState[currentImage] == 2 && gameOn){
            gameState[currentImage] = activeUser;
            if(activeUser == 0){
                img.setImageResource(R.drawable.o);
                activeUser = 1;
                status.setText(xTurn);
            }else{
                img.setImageResource(R.drawable.x);
                activeUser = 0;
                status.setText(oTurn);
            }
            img.animate();
        }
//        check if someone won
        for(int[] temp : winningPositions){
            if(gameState[temp[0]] == gameState[temp[1]] && gameState[temp[1]] == gameState[temp[2]] && gameState[temp[0]] != 2){
                if(gameState[temp[0]] == 0){
                    status.setText(oWon);
                }
                else{
                    status.setText(xWon);
                }
                findViewById(R.id.button2).setVisibility(View.VISIBLE);
                findViewById(R.id.button3).setVisibility(View.VISIBLE);
                gameOn = false;
            }
        }
    }
    public void reset(View view){
        gameOn = true;
        tie = false;
        activeUser = 0;
        Arrays.fill(gameState, 2);
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        status.setText(oTurn);
        findViewById(R.id.button2).setVisibility(View.INVISIBLE);
        findViewById(R.id.button3).setVisibility(View.INVISIBLE);
    }
    public void exitApplication(){
        finish();
        System.exit(0);
    }
}