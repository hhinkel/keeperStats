package com.example.android.keeperstats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    //State Keys for restoring Application state
    static private final String SCORE_KIRKWOOD = "scoreK";
    static private final String SCORE_OPP = "scoreOpp";
    static private final String SHOTS_KIRKWOOD = "shotsK";
    static private final String SHOTS_OPP = "shotsOpp";
    static private final String PERCENT_KIRKWOOD = "percentK";
    static private final String PERCENT_OPP = "percentOpp";

    //Global Variables
    int scoreKirkwood = 0;
    int scoreOpponent = 0;
    int shotsKirkwood = 0;
    int shotsOpponent = 0;
    double percentKirkwood;
    double percentOpponent;
    TextView scoreViewKirkwood;
    TextView scoreViewOpp;
    TextView shotViewKirkwood;
    TextView shotViewOpp;
    TextView percentViewKirkwood;
    TextView percentViewOpp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreViewKirkwood = (TextView) findViewById(R.id.kirkwood_score);
        scoreViewOpp = (TextView) findViewById(R.id.opp_score);
        shotViewKirkwood = (TextView) findViewById(R.id.kirkwood_shots);
        shotViewOpp = (TextView) findViewById(R.id.opp_shots);
        percentViewKirkwood = (TextView) findViewById(R.id.kirkwood_save_percent);
        percentViewOpp = (TextView) findViewById(R.id.opp_save_percent);
        displayData();
    }

    protected void displayData() {
        displayScoreForKirkwood(scoreKirkwood);
        displayScoreForOpp(scoreOpponent);
        displayShotsForKirkwood(shotsKirkwood);
        displayShotsForOpp(shotsOpponent);
        percentKirkwood = determinePercent(scoreOpponent, shotsOpponent);
        percentOpponent = determinePercent(scoreKirkwood, shotsKirkwood);
        displayPercentForKirkwood(percentKirkwood);
        displayPercentForOpp(percentOpponent);
    }

    //Recovery of variables after activity resumed
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        scoreKirkwood = savedInstanceState.getInt(SCORE_KIRKWOOD);
        scoreOpponent = savedInstanceState.getInt(SCORE_OPP);
        shotsKirkwood = savedInstanceState.getInt(SHOTS_KIRKWOOD);
        shotsOpponent = savedInstanceState.getInt(SHOTS_OPP);
        percentKirkwood = savedInstanceState.getDouble(PERCENT_KIRKWOOD);
        percentOpponent = savedInstanceState.getDouble(PERCENT_OPP);
        displayData();
    }

    //Save Instance state for recovery
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(SCORE_KIRKWOOD, scoreKirkwood);
        savedInstanceState.putInt(SCORE_OPP, scoreOpponent);
        savedInstanceState.putInt(SHOTS_KIRKWOOD, shotsKirkwood);
        savedInstanceState.putInt(SHOTS_OPP, shotsOpponent);
        savedInstanceState.putDouble(PERCENT_KIRKWOOD, percentKirkwood);
        savedInstanceState.putDouble(PERCENT_OPP, percentOpponent);
        super.onSaveInstanceState(savedInstanceState);
    }

    //Determine percent of saves
    private double determinePercent(double score, double shots) {
        if(score == 0)
            return 1.0;
        else {
            double numOfSaves = shots - score;
            double savePercent = numOfSaves / shots;
            return savePercent;
        }
    }

    //Add a point and a shot for Kirkwood
    public void pointForKirkwood (View view) {
        scoreKirkwood = scoreKirkwood + 1;
        shotsKirkwood = shotsKirkwood + 1;
        double percent = determinePercent(scoreKirkwood, shotsKirkwood);
        displayScoreForKirkwood(scoreKirkwood);
        displayShotsForKirkwood(shotsKirkwood);
        displayPercentForOpp(percent);
    }

    //Add a point and a shot for the Opponent
    public void pointForOpponent(View view) {
        scoreOpponent = scoreOpponent + 1;
        shotsOpponent = shotsOpponent + 1;
        double percent = determinePercent(scoreOpponent, shotsOpponent);
        displayScoreForOpp(scoreOpponent);
        displayShotsForOpp(shotsOpponent);
        displayPercentForKirkwood(percent);
    }

    //Add a shot for Kirkwood
    public void shotForKirkwood (View view) {
        shotsKirkwood = shotsKirkwood + 1;
        double percent = determinePercent(scoreKirkwood, shotsKirkwood);
        displayShotsForKirkwood(shotsKirkwood);
        displayPercentForOpp(percent);
    }

    //Add a shot for the Opponent
    public void shotForOpponent (View view) {
        shotsOpponent = shotsOpponent + 1;
        double percent = determinePercent(scoreOpponent, shotsOpponent);
        displayShotsForOpp(shotsOpponent);
        displayPercentForKirkwood(percent);
    }

    //Reset and redisply values
    public void reset(View view) {
        scoreKirkwood = 0;
        scoreOpponent = 0;
        shotsKirkwood = 0;
        shotsOpponent = 0;
        displayData();
    }

    //Display Score for Kirkwood
    public void displayScoreForKirkwood(int score) {
        scoreViewKirkwood.setText(String.valueOf(score));
    }

    //Display Score for Opponent
    public void displayScoreForOpp(int score) {
        scoreViewOpp.setText(String.valueOf(score));
    }

    //Display shots for Kirkwood
    public void displayShotsForKirkwood(int shots) {
        shotViewKirkwood.setText(String.valueOf(shots));
    }

    //Display shots for Opponent
    public void displayShotsForOpp(int shots) {
        shotViewOpp.setText(String.valueOf(shots));
    }

    //Display Keeper Percentage for Kirkwood
    public void displayPercentForKirkwood(double percent) {
        percentViewKirkwood.setText(String.format("%.4f",percent));
    }

    //Display Keeper Percentage for Opponent
    public void displayPercentForOpp(double percent) {
        percentViewOpp.setText(String.format("%.4f",percent));
    }
}
