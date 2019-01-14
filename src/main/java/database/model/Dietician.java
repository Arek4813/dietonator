package database.model;

import java.sql.Date;

public class Dietician {

    private String dieticianLogin;

    private String dieticianPassword;

    public String getDieticianLogin() {
        return dieticianLogin;
    }

    public void setDieticianLogin(String dieticianLogin) {
        this.dieticianLogin = dieticianLogin;
    }

    public String getDieticianPassword() {
        return dieticianPassword;
    }

    public void setDieticianPassword(String dieticianPassword) {
        this.dieticianPassword = dieticianPassword;
    }

    public String getDieticianName() {
        return dieticianName;
    }

    public void setDieticianName(String dieticianName) {
        this.dieticianName = dieticianName;
    }

    public String getDieticianSurname() {
        return dieticianSurname;
    }

    public void setDieticianSurname(String dieticianSurname) {
        this.dieticianSurname = dieticianSurname;
    }

    public String getDieticianBirthDate() {
        return dieticianBirthDate;
    }

    public void setDieticianBirthDate(String dieticianBirthDate) {
        this.dieticianBirthDate = dieticianBirthDate;
    }

    public String getDieticianMail() {
        return dieticianMail;
    }

    public void setDieticianMail(String dieticianMail) {
        this.dieticianMail = dieticianMail;
    }

    private String dieticianName;

    private String dieticianSurname;

    private String dieticianBirthDate;

    private String dieticianMail;


}
