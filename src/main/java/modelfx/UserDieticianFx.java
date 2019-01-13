package modelfx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserDieticianFx {

    private StringProperty uLogin;

    private StringProperty dLogin;

    public String getuLogin() {
        return uLogin.get();
    }

    public StringProperty uLoginProperty() {
        return uLogin;
    }

    public void setuLogin(String uLogin) {
        this.uLogin.set( uLogin );
    }

    public String getdLogin() {
        return dLogin.get();
    }

    public StringProperty dLoginProperty() {
        return dLogin;
    }

    public void setdLogin(String dLogin) {
        this.dLogin.set( dLogin );
    }

    public UserDieticianFx() {
        uLogin=new SimpleStringProperty();
        dLogin=new SimpleStringProperty();
    }


}
