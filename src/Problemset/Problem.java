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

    public String title, body, input1, output1, input2, output2, input3, output3, author;
    public int submission, problemID, trackToDo, timeLimit;
    public Problem()
    {
        
    }
    public Problem(String title, String body, String input1, String output1, String input2, String output2, String input3, String output3, String author, int timeLimit, int problemID) {
        this.title = title;
        this.body = body;
        this.input1 = input1;
        this.output1 = output1;
        this.input2 = input2;
        this.output2 = output2;
        this.input3 = input3;
        this.output3 = output3;
        this.author = author;
        this.timeLimit = timeLimit;
        this.problemID = problemID;
    }
}
