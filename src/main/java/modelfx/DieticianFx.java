package modelfx;

import javafx.beans.InvalidationListener;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.sql.Date;

public class DieticianFx {

    private StringProperty login;
    private StringProperty password;
    private StringProperty name;
    private StringProperty surname;
    private ObjectProperty<Date> birthDate;
    private StringProperty mail;

    public DieticianFx() {

        login = new SimpleStringProperty();
        password = new SimpleStringProperty();
        name = new SimpleStringProperty();
        surname = new SimpleStringProperty();
        birthDate = new ObjectProperty<Date>() {
            @Override
            public void bind(ObservableValue<? extends Date> observable) {
            }

            @Override
            public void unbind() {

            }

            @Override
            public boolean isBound() {
                return false;
            }

            @Override
            public Object getBean() {
                return null;
            }

            @Override
            public String getName() {
                return null;
            }

            @Override
            public Date get() {
                return null;
            }

            @Override
            public void addListener(ChangeListener<? super Date> listener) {

            }

            @Override
            public void removeListener(ChangeListener<? super Date> listener) {

            }

            @Override
            public void addListener(InvalidationListener listener) {

            }

            @Override
            public void removeListener(InvalidationListener listener) {

            }

            @Override
            public void set(Date value) {

            }
        };

        mail = new SimpleStringProperty();
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

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set( password );
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

    public Date getBirthDate() {
        return birthDate.get();
    }

    public ObjectProperty<Date> birthDateProperty() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate.set( birthDate );
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
