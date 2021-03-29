package Login;

import javafx.scene.control.Alert;

public class Exception {

    public boolean SignUpException(String Name, String Username, String Email, String Password, String Confirmpassword) {
        boolean empty = false;
        if (Name.isEmpty() || Username.isEmpty() || Email.isEmpty() || Password.isEmpty() || Confirmpassword.isEmpty()) {
            empty = true;
        }
        return empty;
    }

    public boolean signup_SpecharacterException(String Name, String Username, String Email, String Occupation) {
        Alert a = new Alert(Alert.AlertType.NONE);
        a.setAlertType(Alert.AlertType.INFORMATION);
        boolean name_invalid = false;
        boolean email_invalid = false;
        boolean invalid = false;
        for (int i = 0; i < Name.length(); ++i) {
            if (!((Name.charAt(i) >= 'A' && Name.charAt(i) <= 'Z') || (Name.charAt(i) >= 'a' && Name.charAt(i) <= 'z') || (Name.charAt(i) == ' '))) {
                invalid = true;
                name_invalid = true;
            }
        }
        if (name_invalid == true) {
            a.setContentText("You can only use A-Z,a-z, in Name");
            a.showAndWait();
        } else {
            for (int i = 0; i < Email.length(); ++i) {
                if (!((Email.charAt(i) >= 'A' && Email.charAt(i) <= 'Z') || (Email.charAt(i) >= 'a' && Email.charAt(i) <= 'z') || (Email.charAt(i) >= '0' && Email.charAt(i) <= '9') || Email.charAt(i) == '@' || Email.charAt(i) == '.')) {
                    invalid = true;
                    email_invalid = true;
                }
            }
            if (email_invalid == true) {

                a.setContentText("You can only use A-Z,a-z,@,. in Email");
                a.show();
            }
        }
        return invalid;
    }

    public boolean SignInException(String Username, String Password) {
        boolean empty = false;
        if (Username.isEmpty() || Password.isEmpty()) {
            empty = true;
        }
        return empty;
    }

    public boolean forgotpasswordException(String user, String updatedpassword, String email, String confirmPass) {
        boolean d = false;
        if (user.isEmpty() || updatedpassword.isEmpty() || email.isEmpty() || confirmPass.isEmpty()) {
            d = true;
        }
        return d;
    }

}
