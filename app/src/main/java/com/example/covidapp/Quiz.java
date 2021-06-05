package com.example.covidapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Quiz extends AppCompatActivity {
    private TextView optionA, optionB;
    private TextView questionnumber,question,score;
    private TextView checkout1, checkout2;
    int currentIndex;
    int mscore=0;
    int qn=1;
    ProgressBar progressBar;
    int CurrentQuestion,currentOptionA, currentOptionB;


    private answerclass[] questionBank = new answerclass[]
            {
                    new answerclass(R.string.que1,R.string.que1A,R.string.que1B,R.string.ans1),
                    new answerclass(R.string.que2,R.string.que2A,R.string.que2B,R.string.ans2),
                    new answerclass(R.string.que3,R.string.que3A,R.string.que3B,R.string.ans3),
                    new answerclass(R.string.que4,R.string.que4A,R.string.que4B,R.string.ans4),
                    new answerclass(R.string.que5,R.string.que5A,R.string.que5B,R.string.ans5),

                    new answerclass(R.string.que6,R.string.que6A,R.string.que6B,R.string.ans6),
                    new answerclass(R.string.que7,R.string.que7A,R.string.que7B,R.string.ans7),
                    new answerclass(R.string.que8,R.string.que8A,R.string.que8B,R.string.ans8),
                    new answerclass(R.string.que9,R.string.que9A,R.string.que9B,R.string.ans9),
                    new answerclass(R.string.que10,R.string.que10A,R.string.que10B,R.string.ans10),
            };
    final int PROGRESS_BAR= (int) Math.ceil(100/questionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        optionA=findViewById(R.id.optionA);
        optionB=findViewById(R.id.optionB);

        question =findViewById(R.id.question);
        score =findViewById(R.id.score);
        questionnumber=findViewById(R.id.QuestionNumber);


        checkout1=findViewById(R.id.selectedoption);
        checkout2=findViewById(R.id.correctanswer);
        progressBar=findViewById(R.id.progress_bar);

        CurrentQuestion=questionBank[currentIndex].getQuestionid();
        question.setText(CurrentQuestion);
        currentOptionA=questionBank[currentIndex].getOptionA();
        optionA.setText(currentOptionA);
        currentOptionB=questionBank[currentIndex].getOptionB();
        optionB.setText(currentOptionB);

        optionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer(currentOptionA);
                updateQuestion();

            }
        });

        optionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(currentOptionB);
                updateQuestion();

            }
        });

    }

    private void checkAnswer(int userSelection) {

        int correctanswer = questionBank[currentIndex].getAnswerid();
        checkout1.setText(userSelection);
        checkout2.setText(correctanswer);

        String m = checkout1.getText().toString().trim();
        String n = checkout2.getText().toString().trim();

        if(m.equals(n))
        {
            Toast.makeText(getApplicationContext()," ", Toast.LENGTH_SHORT).show();
            mscore=mscore+1;
        }
        else
        {
            Toast.makeText(getApplicationContext()," ", Toast.LENGTH_SHORT).show();

        }


    }

    private void updateQuestion() {

        currentIndex = (currentIndex+1)%questionBank.length;
        if(currentIndex==0)
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Your Score is: "+ mscore + "points");
            if(mscore>5)
                alert.setMessage("Consult A Doctor");
            else
                alert.setMessage("You are Safe");
            alert.setCancelable(false);
            alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                   finish();

                }
            });

            alert.setNegativeButton("Locate Hospital", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    gotourl("https://www.google.co.in/maps");

                }
            });
            alert.show();
        }

        CurrentQuestion=questionBank[currentIndex].getQuestionid();
        question.setText(CurrentQuestion);
        currentOptionA=questionBank[currentIndex].getOptionA();
        optionA.setText(currentOptionA);
        currentOptionB=questionBank[currentIndex].getOptionB();
        optionB.setText(currentOptionB);

        qn=qn+1;

        if(qn<=questionBank.length)
        {
            questionnumber.setText(qn+ "/" +questionBank.length +"Question");
        }

        score.setText("Score" + mscore+"/" +questionBank.length);
        progressBar.incrementProgressBy(PROGRESS_BAR);

    }

    private void gotourl(String s){
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));

    }
}