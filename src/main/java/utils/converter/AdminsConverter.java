package utils.converter;

import database.model.Admin;
import modelfx.AdminFx;

public class AdminsConverter {

    private static AdminsConverter INSTANCE;

    private AdminsConverter() {}

    public static AdminsConverter getInstance() {
        if (INSTANCE == null)
            INSTANCE = new AdminsConverter();
        return INSTANCE;
    }

    public Admin convertToAdmin(AdminFx adminFx) {
        Admin admin = new Admin();
        admin.setAdminLogin(adminFx.getAdminLogin());
        admin.setAdminPassword(adminFx.getAdminPassword());
        return admin;
    }

    public AdminFx convertToAdminFx(Admin admin) {
        AdminFx adminfx = new AdminFx();
        adminfx.setAdminLogin(admin.getAdminLogin());
        adminfx.setAdminPassword(admin.getAdminPassword());
        return adminfx;
    }
}
