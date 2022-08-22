package com.springbootproject.theData;

public class Question {
    private String theQuestion;   // This name must match the attribute name in the JSON

    public Question() {}  // default ctor used to convert to/from JSON

    public Question(String theQuestion) {
        this.theQuestion = theQuestion;
    }

    public String getTheQuestion() {
        return theQuestion;
    }

    public void setTheQuestion(String theQuestion) {
        this.theQuestion = theQuestion;
    }
}
