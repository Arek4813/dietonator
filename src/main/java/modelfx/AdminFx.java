package modelfx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AdminFx {

    private StringProperty adminLogin;
    private StringProperty adminPassword;

    public String getAdminLogin() {
        return adminLogin.get();
    }

    public StringProperty adminLoginProperty() {
        return adminLogin;
    }

    public void setAdminLogin(String adminLogin) {
        this.adminLogin.set( adminLogin );
    }

    public String getAdminPassword() {
        return adminPassword.get();
    }

    public StringProperty adminPasswordProperty() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword.set( adminPassword );
    }

    public AdminFx() {
        adminLogin= new SimpleStringProperty();
        adminPassword=new SimpleStringProperty();
    }
}
