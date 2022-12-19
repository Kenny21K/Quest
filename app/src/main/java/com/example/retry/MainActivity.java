package com.example.retry;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;

    int score = 0;
    int totalQuestion = QuestionAnsuer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionsTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.Ans_A);
        ansB = findViewById(R.id.Ans_B);
        ansC = findViewById(R.id.Ans_C);
        ansD = findViewById(R.id.Ans_D);
        submitBtn = findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        totalQuestionsTextView.setText("Total question : " + totalQuestion);

        loadNewQuestion();
    }

    @Override
    public void onClick(View view) {



        Button clickedButton = (Button) view;
        if (clickedButton.getId() == R.id.submit_btn) {
            if(selectedAnswer.equals(QuestionAnsuer.correctAnswer[currentQuestionIndex])){
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();

        } else {
//choices button clicked
            selectedAnswer = clickedButton.getText().toString();

        }
    }

    void loadNewQuestion() {

        if(currentQuestionIndex == totalQuestion){
            finishQuiz();
            return;
        }

        questionTextView.setText(QuestionAnsuer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnsuer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnsuer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnsuer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnsuer.choices[currentQuestionIndex][3]);
    }

    void finishQuiz(){
        EditText etxt = (EditText) findViewById(R.id.editTextTextPersonName);
        String passStatus ="";
        if(score > totalQuestion * 0.60){
            passStatus = "вы гетеро";
        }else {
            passStatus = "чувак в россии это запрещено";
        }
        new AlertDialog.Builder(this).setTitle(passStatus).setMessage("У вас "+etxt.getText().toString()+" "+score+" % вы натурал "+ totalQuestion)
                .setPositiveButton("Заново",(dialogInterface, i) -> restartQuiz())
                .setCancelable(false)
                .show();
    }
    void restartQuiz(){
        score = 0;
        currentQuestionIndex =0;
        loadNewQuestion();
    }
}