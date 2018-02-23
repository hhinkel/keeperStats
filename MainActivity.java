package com.example.android.keeperstats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    int scoreKirkwood = 0;
    int scoreOpponent = 0;
    int shotsKirkwood = 0;
    int shotsOpponent = 0;
    double percentKirkwood;
    double percentOpponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayScoreForKirkwood(scoreKirkwood);
        displayScoreForOpp(scoreOpponent);
        displayShotsForKirkwood(shotsKirkwood);
        displayShotsForOpp(shotsOpponent);
        percentKirkwood = determinePercent(scoreOpponent, shotsOpponent);
        percentOpponent = determinePercent(scoreKirkwood, shotsKirkwood);
        displayPercentForKirkwood(percentKirkwood);
        displayPercentForOpp(percentOpponent);
    }

    private double determinePercent(double score, double shots) {
        if(score == 0)
            return 1.0;
        else {
            double numOfSaves = shots - score;
            double savePercent = numOfSaves / shots;
            return savePercent;
        }
    }

    public void pointForKirkwood (View view) {
        scoreKirkwood = scoreKirkwood + 1;
        shotsKirkwood = shotsKirkwood + 1;
        double percent = determinePercent(scoreKirkwood, shotsKirkwood);
        displayScoreForKirkwood(scoreKirkwood);
        displayShotsForKirkwood(shotsKirkwood);
        displayPercentForOpp(percent);
    }

    public void pointForOpponent(View view) {
        scoreOpponent = scoreOpponent + 1;
        shotsOpponent = shotsOpponent + 1;
        double percent = determinePercent(scoreOpponent, shotsOpponent);
        displayScoreForOpp(scoreOpponent);
        displayShotsForOpp(shotsOpponent);
        displayPercentForKirkwood(percent);
    }

    public void shotForKirkwood (View view) {
        shotsKirkwood = shotsKirkwood + 1;
        double percent = determinePercent(scoreKirkwood, shotsKirkwood);
        displayShotsForKirkwood(shotsKirkwood);
        displayPercentForOpp(percent);
    }

    public void shotForOpponent (View view) {
        shotsOpponent = shotsOpponent + 1;
        double percent = determinePercent(scoreOpponent, shotsOpponent);
        displayShotsForOpp(shotsOpponent);
        displayPercentForKirkwood(percent);
    }

    public void reset(View view) {
        scoreKirkwood = 0;
        scoreOpponent = 0;
        shotsKirkwood = 0;
        shotsOpponent = 0;
        percentKirkwood = determinePercent(scoreKirkwood, shotsKirkwood);
        percentOpponent = determinePercent(scoreOpponent, shotsOpponent);
        displayScoreForKirkwood(scoreKirkwood);
        displayScoreForOpp(scoreOpponent);
        displayShotsForKirkwood(shotsKirkwood);
        displayShotsForOpp(shotsOpponent);
        displayPercentForKirkwood(percentKirkwood);
        displayPercentForOpp(percentOpponent);
    }

    public void displayScoreForKirkwood(int score) {
        TextView scoreView = (TextView) findViewById(R.id.kirkwood_score);
        scoreView.setText(String.valueOf(score));
    }

    public void displayScoreForOpp(int score) {
        TextView scoreView = (TextView) findViewById(R.id.opp_score);
        scoreView.setText(String.valueOf(score));
    }

    public void displayShotsForKirkwood(int shots) {
        TextView scoreView = (TextView) findViewById(R.id.kirkwood_shots);
        scoreView.setText(String.valueOf(shots));
    }

    public void displayShotsForOpp(int shots) {
        TextView scoreView = (TextView) findViewById(R.id.opp_shots);
        scoreView.setText(String.valueOf(shots));
    }

    public void displayPercentForKirkwood(double percent) {
        TextView scoreView = (TextView) findViewById(R.id.kirkwood_save_percent);
        scoreView.setText(String.format("%.4f",percent));
    }

    public void displayPercentForOpp(double percent) {
        TextView scoreView = (TextView) findViewById(R.id.opp_save_percent);
        scoreView.setText(String.format("%.4f",percent));
    }
}
