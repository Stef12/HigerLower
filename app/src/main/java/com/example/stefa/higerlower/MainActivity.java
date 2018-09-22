package com.example.stefa.higerlower;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    private int[] mImages;
    private int currentImageIndex = 0;
    private int score = 0;
    private int highscore = 0;
    private FloatingActionButton mFBDown;
    private FloatingActionButton mFBUp;
    private TextView mScore;
    private TextView mHighScore;
    private ListView mListView;
    private List<String> mList;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = findViewById(R.id.ivImage);
        mImages = new int[]{R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5, R.drawable.d6};
        mFBDown = findViewById(R.id.fabDown);
        mFBUp = findViewById(R.id.fabUp);
        mListView = findViewById(R.id.lvThrows);
        mList = new ArrayList<>();
        mScore = findViewById(R.id.tvScore);
        mHighScore = findViewById(R.id.tvHighScore);

        currentImageIndex = randomNumber();
        mImageView.setImageResource(mImages[currentImageIndex]);

        // Define what happens when the user clicks the down button
        mFBDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newNumber = randomNumber();
                mImageView.setImageResource(mImages[newNumber]);

                if (newNumber < currentImageIndex) {
                    Snackbar.make(findViewById(R.id.clLayout), "You win!", Snackbar.LENGTH_SHORT).show();

                    score++;
                    mScore.setText("score : " + score);
                } else {
                    Snackbar.make(findViewById(R.id.clLayout), "You lose!", Snackbar.LENGTH_SHORT).show();

                    if (score > highscore) {
                        highscore = score;
                        mHighScore.setText("highscore : " + highscore);
                    }
                    score = 0;
                    mScore.setText("score : 0");
                }

                currentImageIndex = newNumber;
                mList.add("Throw is " + (newNumber + 1));
                updateUI();
            }
        });

        // Define what happens when the user clicks the up button
        mFBUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newNumber = randomNumber();
                mImageView.setImageResource(mImages[newNumber]);

                if (newNumber > currentImageIndex) {
                    Snackbar.make(findViewById(R.id.clLayout), "You win!", Snackbar.LENGTH_SHORT).show();

                    score++;
                    mScore.setText("score : " + score);
                } else {
                    Snackbar.make(findViewById(R.id.clLayout), "You lose!", Snackbar.LENGTH_SHORT).show();

                    if (score > highscore) {
                        highscore = score;
                        mHighScore.setText("highscore : " + highscore);
                    }
                    score = 0;
                    mScore.setText("score : 0");
                }

                currentImageIndex = newNumber;
                mList.add("Throw is " + (newNumber + 1));
                updateUI();
            }
        });
    }

    public int randomNumber() {
        int n = new Random().nextInt(6);
        return n;
    }

    public void updateUI() {
        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mList);
            mListView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }
}
