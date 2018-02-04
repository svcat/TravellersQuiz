package com.example.android.travelersnotesquiz;

import android.content.Context;
import android.content.Intent;
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

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  /**
   * This method is called when the View result button is clicked.
   */
  public void totalScore(View view) {
    ImageView bangkokairwaysImage = findViewById(R.id.stuffBangkokAirways);
    bangkokairwaysImage.setImageResource(R.drawable.bangkokairwaysanswer);
    String message = finalText(measureScore());
    displayMessage(message);
    showToast(getString(R.string.you_final_result), measureScore());

    TextView finalScoreTextView = findViewById(R.id.finalScore);
    finalScoreTextView.setVisibility(View.VISIBLE);
  }

  /**
   * This method is called when the answers button is clicked.
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
  }

  /**
   * This method is called when the Send result button is clicked. !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
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
   * This method for sending emails !!!!!!!!!!!!!!!!
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
   * This method displays create final text with score result
   */
  private String finalText(int score) {
    return getString(R.string.your_total_score, score, TOTAL_QUESTIONS);
  }

}
