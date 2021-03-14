package Login;

public class Exception {

    public boolean SignUpException(String Name, String Username, String Email, String Password, String Confirmpassword) {
        boolean b = false;

        if (Name.isEmpty() || Username.isEmpty() || Email.isEmpty() || Password.isEmpty() || Confirmpassword.isEmpty()) {
            b = true;
        }

        return b;
    }
}
