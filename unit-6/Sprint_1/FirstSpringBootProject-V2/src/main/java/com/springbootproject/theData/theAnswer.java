package com.springbootproject.theData;

public class theAnswer {
    String answerReturned;

    public theAnswer() {}// default ctor so server can convert to/from JSON

    public theAnswer(String answerReturned) {
        this.answerReturned = answerReturned;
    }

    public String getAnswerReturned() {
        return answerReturned;
    }

    public void setAnswerReturned(String answerReturned) {
        this.answerReturned = answerReturned;
    }
}
