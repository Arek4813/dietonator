package modelfx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LogDataFx {

    private StringProperty loginValue;
    private StringProperty passwordValue;
    private StringProperty roleEnumValue;

    public LogDataFx() {
        loginValue=new SimpleStringProperty();
        passwordValue=new SimpleStringProperty();
        roleEnumValue=new SimpleStringProperty();
    }

    public String getLoginValue() {
        return loginValue.get();
    }

    public StringProperty loginValueProperty() {
        return loginValue;
    }

    public void setLoginValue(String loginValue) {
        this.loginValue.set(loginValue);
    }

    public String getPasswordValue() {
        return passwordValue.get();
    }

    public StringProperty passwordValueProperty() {
        return passwordValue;
    }

    public void setPasswordValue(String passwordValue) {
        this.passwordValue.set( passwordValue );
    }

    public String getRoleEnumValue() {
        return roleEnumValue.get();
    }

    public StringProperty roleEnumValueProperty() {
        return roleEnumValue;
    }

    public void setRoleEnumValue(String roleEnumValue) {
        this.roleEnumValue.set( roleEnumValue );
    }
}
