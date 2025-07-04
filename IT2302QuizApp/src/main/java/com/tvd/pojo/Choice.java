/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tvd.pojo;


public class Choice {
    private int id;
    private String content;
    private boolean is_correct;
    private Question question;

    public Choice(int id, String content, boolean is_correct, Question question) {
        this.id = id;
        this.content = content;
        this.is_correct = is_correct;
        this.question = question;
    }

    public Choice(String content, boolean is_correct) {
        this.content = content;
        this.is_correct = is_correct;
    }
    
    public Choice(int id, String content, boolean is_correct) {
        this.id = id;
        this.content = content;
        this.is_correct = is_correct;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the is_correct
     */
    public boolean isIs_correct() {
        return is_correct;
    }

    /**
     * @param is_correct the is_correct to set
     */
    public void setIs_correct(boolean is_correct) {
        this.is_correct = is_correct;
    }

    /**
     * @return the question
     */
    public Question getQuestion() {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(Question question) {
        this.question = question;
    }
    
}
