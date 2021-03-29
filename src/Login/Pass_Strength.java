/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import javafx.scene.control.Alert;

/**
 *
 * @author SaMiR
 */
public class Pass_Strength {

    boolean pass_length_checker = false;

    public static int pass_strength_checke;

    public boolean pass_length_check(String password) {

        if (password.length() > 7) {

            System.out.println(password.length());
            pass_length_checker = true;

        }

        return pass_length_checker;
    }

    public int pass_strength_check(String password) {
        boolean caps = false, small = false, digit = false, specialKey = false;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isDigit(c)) {
                digit = true;
            } else if (Character.isUpperCase(c)) {
                caps = true;
            } else if (Character.isLowerCase(c)) {
                small = true;
            } else if ((c >= 32 && c <= 64) || (c >= 123 && c <= 126) || c >= 128 && c <= 255) {
                specialKey = true;
            }
        }
        if (small == true || caps == true || digit == true || specialKey == true) {
            pass_strength_checke = 1;
        }
        if ((small == true && caps == true) || (small == true && digit == true) || (caps == true && digit == true)) {
            pass_strength_checke = 2;
        }
        if ((small == true && specialKey == true) || (caps == true && specialKey == true) || (digit == true && specialKey == true)) {
            pass_strength_checke = 2;
        }
        /*
        if (caps == true && digit == true) {
            pass_strength_checke = 2;

        }*/
        if ((small == true && caps == true && digit == true) || (small == true && caps == true && specialKey == true) || (small == true && caps == true && digit == true && specialKey == true)) {
            pass_strength_checke = 3;
        }
        /*
        if (small == true && caps == true && specialKey == true) {
            pass_strength_checke = 3;
        }
        if (small == true && caps == true && digit == true && specialKey == true) {
            pass_strength_checke = 3;
        }*/
        return pass_strength_checke;
    }
}
