package model;

import android.util.Patterns;

public class LoginModel {

    private String Email;
    private String Password;

    public LoginModel(String EmailAddress, String Password) {
        this.Email = EmailAddress;
        this.Password = Password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
    }


    public boolean isPasswordLengthGreaterThan5() {
        return getPassword().length() > 5;
    }
}
