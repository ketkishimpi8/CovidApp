package com.example.covidapp;

public class answerclass {

    private int optionA, optionB, questionid, answerid;

    public answerclass(int quesid, int opa,int opb,int ansid)
    {
        questionid = quesid;
        optionA= opa;
        optionB= opb;
        answerid= ansid;

    }

    public int getAnswerid() {

        return answerid;
    }

    public int getOptionA() {

        return optionA;
    }

    public int getOptionB() {

        return optionB;
    }

    public int getQuestionid() {

        return questionid;
    }
}
