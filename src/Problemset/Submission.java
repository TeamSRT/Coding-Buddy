/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Problemset;

/**
 *
 * @author Srishti
 */
public class Submission {
  public  String username, subdate, subtime, probName, answer, code, date, lang;
  public  int verdict, problemID, track;
    public Submission()
    {
        
    }
    public Submission(String probName, String answer, String code, String lang, String date, String time)
    {
        this.probName = probName;
        this.answer = answer;
        this.code = code;
        this.lang = lang; 
        this.subdate = date;
        this.subtime = time;
    }
    public Submission(String probName,String username, String answer, String code, String lang, String date, String time)
    {
        this.probName = probName;
        this.username = username;
        this.answer = answer;
        this.code = code;
        this.lang = lang; 
        this.subdate = date;
        this.subtime = time;
    }
    
}
