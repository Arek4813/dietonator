package utils.converter;

import database.model.UserDietician;
import modelfx.UserDieticianFx;

public class UserDieticianConverter {
    private static UserDieticianConverter INSTANCE;

    private UserDieticianConverter() {}

    public static UserDieticianConverter getInstance() {
        if (INSTANCE == null)
            INSTANCE = new UserDieticianConverter();
        return INSTANCE;
    }

    public UserDietician convertToUserDietician(UserDieticianFx userDieticianFx) {
        UserDietician userDietician = new UserDietician();
        userDietician.setuLogin(userDieticianFx.getuLogin());
        userDietician.setdLogin(userDieticianFx.getdLogin());
        return userDietician;
    }

    public UserDieticianFx convertToUserDieticianFx(UserDietician userDietician) {
        UserDieticianFx userDieticianFx = new UserDieticianFx();
        userDieticianFx.setuLogin(userDietician.getuLogin());
        userDieticianFx.setdLogin(userDietician.getdLogin());
        return userDieticianFx;
    }
}
