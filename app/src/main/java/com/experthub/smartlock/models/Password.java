package com.experthub.smartlock.models;

import android.content.Context;

import java.security.PublicKey;

import io.paperdb.Paper;

public class Password {
    private String PASSWORD_KEY = "password_key";
    public  String PATTERN_SET = "PATTERN SET";
    public String CONFIRM_PATTERN = "Draw the pattern again to confirm";
    public String INCREMENT_PATTERN = "Please try again";
    public String FIRST_USE = "Draw and unlock pattern please";
    public String SCHEMA_FAILED = "You must connect at least four (4) dots!";
    public Boolean isFirst = true;

    public Password(Context context){
        Paper.init(context);
    }

    public String getPASSWORD_KEY() {
        return Paper.book().read(PASSWORD_KEY);
    }

    public void setPASSWORD_KEY(String PASS) {
        Paper.book().write(PASSWORD_KEY, PASS);
    }

    public Boolean getFirst() {
        return isFirst;
    }

    public void setFirst(Boolean first) {
        isFirst = first;
    }

    public Boolean isCorrect(String PASS){
        return PASS.equals(getPASSWORD_KEY());
    }
}
