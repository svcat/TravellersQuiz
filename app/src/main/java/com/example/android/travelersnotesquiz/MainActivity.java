package com.example.android.travelersnotesquiz;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int TOTAL_QUESTIONS = 6;
    public static final String FINAL_SCORE_STATUS_KEY = "finalScoreStatusKey";
    public static final String RIGHT_ANSWERS_STATUS_KEY = "rightAnswersStatusKey";
    public static final String ISRIGHT_ANSWERS_STATUS_KEY = "isrightAnswersStatusKey";
    boolean finalScoreStatus = false;
    boolean rightAnswersStatus = false;
    boolean isrightAnswersStatus = false;

    /**
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * method for saving status for View which visibility is gone: TextView Total score, TextView rightAnswers
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(FINAL_SCORE_STATUS_KEY, finalScoreStatus);
        outState.putBoolean(RIGHT_ANSWERS_STATUS_KEY, rightAnswersStatus);
        outState.putBoolean(ISRIGHT_ANSWERS_STATUS_KEY, isrightAnswersStatus);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey(FINAL_SCORE_STATUS_KEY)) {
            finalScoreStatus = savedInstanceState.getBoolean(FINAL_SCORE_STATUS_KEY);
            if (finalScoreStatus) {
                showTotalScore(null);
            }
        }
        if (savedInstanceState.containsKey(RIGHT_ANSWERS_STATUS_KEY)) {
            rightAnswersStatus = savedInstanceState.getBoolean(RIGHT_ANSWERS_STATUS_KEY);
            if (rightAnswersStatus) {
                viewAnswers(null);
            }
        }
        if (savedInstanceState.containsKey(ISRIGHT_ANSWERS_STATUS_KEY)) {
            isrightAnswersStatus = savedInstanceState.getBoolean(ISRIGHT_ANSWERS_STATUS_KEY);
            if (isrightAnswersStatus) {
                showAnswerCorrectnessTextView(null);
            }
        }
    }

    /**
     * This method is called when the View result button is clicked.
     */
    public void showTotalScore(View view) {
        //change image for question about Bangkok Airways
        ImageView bangkokairwaysImage = findViewById(R.id.stuffBangkokAirways);
        bangkokairwaysImage.setImageResource(R.drawable.bangkokairwaysanswer);

        // display final user's score
        String message = finalText(measureScore());
        displayMessage(message);

        // toast with final user's score
        showToast(getString(R.string.you_final_result), measureScore());

        // display text view wuth score
        TextView finalScoreTextView = findViewById(R.id.finalScore);
        finalScoreTextView.setVisibility(View.VISIBLE);

        // to save status if user rotates the phone
        finalScoreStatus = true;
        isrightAnswersStatus = true;

        showAnswerCorrectness();
        showAnswerCorrectnessTextView(null);
    }

    /**
     * This method set Text for the correct/incorrect answer
     */
    public String answersCorrectnessText(boolean correctness) {
        if (correctness) {
            return getString(R.string.right_answer);
        } else {
            return getString(R.string.wrong_answer);
        }
    }

    /**
     * This method set Color for the correct/incorrect answer
     */
    public int answersCorrectnessColor(boolean correctness) {
        if (correctness) {
            return Color.rgb(0, 255, 0);
        } else {
            return Color.rgb(255, 0, 0);
        }
    }

    /**
     * This method shows Text for the correct/incorrect answer
     */
    public void showAnswerCorrectness() {
        // BangkokAirways
        TextView correctTextViewBangkokAirways = findViewById(R.id.isRightAnswerBangkokAirways);
        correctTextViewBangkokAirways.setText(answersCorrectnessText(questionBangkokAirways()));
        correctTextViewBangkokAirways.setTextColor(answersCorrectnessColor(questionBangkokAirways()));

        // ThaiNewYear
        TextView correctTextViewThaiNewYear = findViewById(R.id.isRightAnswerThaiNewYear);
        correctTextViewThaiNewYear.setText(answersCorrectnessText(questionThaiNewYear()));
        correctTextViewThaiNewYear.setTextColor(answersCorrectnessColor(questionThaiNewYear()));

        // Spacewar
        TextView correctTextViewSpacewar = findViewById(R.id.isRightAnswerSpacewar);
        correctTextViewSpacewar.setText(answersCorrectnessText(questionSpacewar()));
        correctTextViewSpacewar.setTextColor(answersCorrectnessColor(questionSpacewar()));

        // Durian
        TextView correctTextViewDurian = findViewById(R.id.isRightAnswerDurian);
        correctTextViewDurian.setText(answersCorrectnessText(questionDurian()));
        correctTextViewDurian.setTextColor(answersCorrectnessColor(questionDurian()));

        // KualaLumpur
        TextView correctTextViewKualaLumpur = findViewById(R.id.isRightAnswerKualaLumpur);
        correctTextViewKualaLumpur.setText(answersCorrectnessText(questionKualaLumpur()));
        correctTextViewKualaLumpur.setTextColor(answersCorrectnessColor(questionKualaLumpur()));

        // Bangkok
        TextView correctTextViewBangkok = findViewById(R.id.isRightAnswerBangkok);
        correctTextViewBangkok.setText(answersCorrectnessText(questionBangkok()));
        correctTextViewBangkok.setTextColor(answersCorrectnessColor(questionBangkok()));
    }

    /**
     * This method shows TextViews with Correctness answer
     */
    public void showAnswerCorrectnessTextView(View view) {
        TextView correctnessBangkokTextView = findViewById(R.id.isRightAnswerBangkok);
        correctnessBangkokTextView.setVisibility(View.VISIBLE);

        TextView correctnessBangkokAirwaysTextView = findViewById(R.id.isRightAnswerBangkokAirways);
        correctnessBangkokAirwaysTextView.setVisibility(View.VISIBLE);

        TextView correctnessDurianTextView = findViewById(R.id.isRightAnswerDurian);
        correctnessDurianTextView.setVisibility(View.VISIBLE);

        TextView correctnessKualaLumpurTextView = findViewById(R.id.isRightAnswerKualaLumpur);
        correctnessKualaLumpurTextView.setVisibility(View.VISIBLE);

        TextView correctnessSpacewarTextView = findViewById(R.id.isRightAnswerSpacewar);
        correctnessSpacewarTextView.setVisibility(View.VISIBLE);

        TextView correctnessThaiNewYearTextView = findViewById(R.id.isRightAnswerThaiNewYear);
        correctnessThaiNewYearTextView.setVisibility(View.VISIBLE);

        isrightAnswersStatus = true;
    }

    /**
     * This method shows hidden answers. is called when the answers button is clicked.
     */
    public void viewAnswers(View view) {
        TextView bangkokTextView = findViewById(R.id.rightAnswerBangkok);
        bangkokTextView.setVisibility(View.VISIBLE);

        TextView bangkokAirwaysTextView = findViewById(R.id.rightAnswerBangkokAirways);
        bangkokAirwaysTextView.setVisibility(View.VISIBLE);

        TextView durianTextView = findViewById(R.id.rightAnswerDurian);
        durianTextView.setVisibility(View.VISIBLE);

        TextView kualaLumpurTextView = findViewById(R.id.rightAnswerKualaLumpur);
        kualaLumpurTextView.setVisibility(View.VISIBLE);

        TextView spacewarTextView = findViewById(R.id.rightAnswerSpacewar);
        spacewarTextView.setVisibility(View.VISIBLE);

        TextView thaiNewYearTextView = findViewById(R.id.rightAnswerThaiNewYear);
        thaiNewYearTextView.setVisibility(View.VISIBLE);

        rightAnswersStatus = true;
    }

    /**
     * This method is called when the Send result button is clicked.
     */
    public void sendResultByEmail(View view) {
        String subject = getString(R.string.send_email_subject);
        String text = getString(R.string.send_email_text, measureScore(), TOTAL_QUESTIONS);
        composeEmail(subject, text);
    }

    /**
     * This method measure user's answer for question about Bangkok
     */
    public boolean questionBangkok() {
        EditText answerBangkokEditText = findViewById(R.id.bangkok_lower_case);
        String answerBangkok = answerBangkokEditText.getText().toString();

        if (answerBangkok.toLowerCase().equals(getString(R.string.bangkok))) {
            return true;
        }
        return false;
    }

    /**
     * This method measure user's answer for question about BangkokAirways
     */
    public boolean questionBangkokAirways() {
        CheckBox isVapeStatus = findViewById(R.id.vape);
        boolean isVape = isVapeStatus.isChecked();

        CheckBox isLightertatus = findViewById(R.id.lighter);
        boolean isLighter = isLightertatus.isChecked();

        CheckBox isSwatterStatus = findViewById(R.id.swatter);
        boolean isSwatter = isSwatterStatus.isChecked();

        CheckBox isPowerBankStatus = findViewById(R.id.powerBank);
        boolean isPowerBank = isPowerBankStatus.isChecked();

        if ((isVape) && (isLighter) && (isSwatter) && (isPowerBank)) {
            return true;
        }
        return false;
    }

    /**
     * This method measure user's answer for question about Durian
     */
    public boolean questionDurian() {
        RadioButton isButterfliesStatus = findViewById(R.id.butterflies);
        boolean isButterflies = isButterfliesStatus.isChecked();

        RadioButton isNightMothesStatus = findViewById(R.id.night_mothes);
        boolean isNightMothes = isNightMothesStatus.isChecked();

        RadioButton isColibriStatus = findViewById(R.id.colibri);
        boolean isBColibri = isColibriStatus.isChecked();

        RadioButton isBatsStatus = findViewById(R.id.bats);
        boolean isBats = isBatsStatus.isChecked();

        if (isBats) {
            return true;
        }
        return false;
    }

    /**
     * This method measure user's answer for question about Kuala-Lumpur
     */
    public boolean questionKualaLumpur() {
        EditText answerKualaLumpurEditText = findViewById(R.id.answer_Kuala_Lumpur);
        String answerKualaLumpur = answerKualaLumpurEditText.getText().toString();

        if (answerKualaLumpur.toLowerCase().contains(getString(R.string.lumpur_kuala_lower_case))) {
            return false;
        }
        if (answerKualaLumpur.toLowerCase().contains(getString(R.string.kuala_lower_case))
                && (answerKualaLumpur.toLowerCase().contains(getString(R.string.lumpur_lower_case)))) {
            return true;
        }
        return false;
    }

    /**
     * This method measure user's answer for question about Spacewar
     */
    public boolean questionSpacewar() {
        RadioButton isYesStatus = findViewById(R.id.yes);
        boolean isYes = isYesStatus.isChecked();

        RadioButton isNoStatus = findViewById(R.id.no);
        boolean isNo = isNoStatus.isChecked();

        if (isYes) {
            return true;
        }
        return false;
    }

    /**
     * This method measure user's answer for question about Thai New Year
     */
    public boolean questionThaiNewYear() {
        CheckBox isTraditionalStatus = findViewById(R.id.traditional);
        boolean isTraditional = isTraditionalStatus.isChecked();

        CheckBox isChinesetatus = findViewById(R.id.chinese);
        boolean isChinese = isChinesetatus.isChecked();

        CheckBox isThaiStatus = findViewById(R.id.thai);
        boolean isThai = isThaiStatus.isChecked();

        CheckBox isVietnameseStatus = findViewById(R.id.vietnamese);
        boolean isVietnamese = isVietnameseStatus.isChecked();

        if ((isTraditional) && (isChinese) && (isThai) && (!isVietnamese)) {
            return true;
        }
        return false;
    }

    /**
     * showToast
     */
    public void showToast(String textOfToast, int score) {
        Context context = getApplicationContext();
        CharSequence text = textOfToast + score;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    /**
     * This method measure user's total score
     */
    public int measureScore() {
        int score = 0;
        if (questionBangkokAirways()) {
            score = score + 1;
        }
        if (questionThaiNewYear()) {
            score = score + 1;
        }
        if (questionSpacewar()) {
            score = score + 1;
        }
        if (questionDurian()) {
            score = score + 1;
        }
        if (questionKualaLumpur()) {
            score = score + 1;
        }
        if (questionBangkok()) {
            score = score + 1;
        }
        return score;
    }

    /**
     * This method for sending emails
     */
    public void composeEmail(String subject, String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method displays total score on the screen.
     */
    private void displayMessage(String message) {
        TextView resultTextView = findViewById(R.id.finalScore);
        resultTextView.setText(message);
    }

    /**
     * This method displays created final text with score result
     */
    private String finalText(int score) {
        return getString(R.string.your_total_score, score, TOTAL_QUESTIONS);
    }

}
