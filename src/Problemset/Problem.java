/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Problemset;

/**
 *
 * @author ktouf
 */
public class Problem {
    public String title, body, input, output, author;
    public int submission, problemID;

    public Problem(String title, String body, String input, String output, String author, int submission, int problemID) {
        this.title = title;
        this.body = body;
        this.input = input;
        this.output = output;
        this.author = author;
        this.submission = submission;
        this.problemID = problemID;
    }
    
}
