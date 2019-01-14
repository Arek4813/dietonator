package modelfx;

import javafx.beans.InvalidationListener;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.sql.Date;

public class UserFx {

    private StringProperty login;
    private StringProperty password;
    private StringProperty name;
    private StringProperty surname;
    private FloatProperty height;
    private FloatProperty weight;
    private StringProperty birthDate;
    private StringProperty mail;

    public UserFx() {

        login = new SimpleStringProperty();
        password = new SimpleStringProperty();
        name = new SimpleStringProperty();
        surname = new SimpleStringProperty();
        height = new SimpleFloatProperty();
        weight = new SimpleFloatProperty();
        birthDate = new SimpleStringProperty();
        mail = new SimpleStringProperty();

    }



    public String getBirthDate() {
        return birthDate.get();
    }

    public StringProperty birthDateProperty() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate.set( birthDate );
    }

    public String getLogin() {
        return login.get();
    }

    public StringProperty loginProperty() {
        return login;
    }

    public void setLogin(String login) {
        this.login.set( login );
    }

    public String getPassword() {
        return password.get();
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set( name );
    }

    public String getSurname() {
        return surname.get();
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set( surname );
    }

    public float getHeight() {
        return height.get();
    }

    public FloatProperty heightProperty() {
        return height;
    }

    public void setHeight(float height) {
        this.height.set( height );
    }

    public float getWeight() {
        return weight.get();
    }

    public FloatProperty weightProperty() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight.set( weight );
    }

    public String getMail() {
        return mail.get();
    }

    public StringProperty mailProperty() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail.set( mail );
    }
}
