package utils.converter;

import database.model.User;
import modelfx.UserFx;

public class UserConverter {

    private static UserConverter INSTANCE;

    private UserConverter() {}

    public static UserConverter getInstance() {
        if (INSTANCE == null)
            INSTANCE = new UserConverter();
        return INSTANCE;
    }

    public User convertToUser(UserFx userFx) {
        User user = new User();
        user.setUserLogin(userFx.getLogin());
        //user.setUserPassword(userFx.getPassword());
        user.setUserName(userFx.getName());
        user.setUserSurname(userFx.getSurname());
        user.setUserHeight(userFx.getHeight());
        user.setUserWeight(userFx.getWeight());
        /*user.setUserBirthDate( Date.valueOf( userFx.getBirthDate().getValue() ) );*/
        user.setUserMail(userFx.getMail());

        return user;
    }

    public UserFx convertToUserFx(User user) {
        UserFx userFx = new UserFx();
        userFx.setLogin(user.getUserLogin());
        //userFx.setPassword(user.getUserLogin());
        userFx.setName(user.getUserLogin());
        userFx.setSurname(user.getUserLogin());
        userFx.setWeight(user.getUserWeight());
        userFx.setHeight(user.getUserHeight());
        /*userFx.setBirthDate(user.getUserBirthDate());*/
        userFx.setMail(user.getUserMail());
        return userFx;
    }
}
