package utils.converter;

import database.model.DietUser;
import modelfx.DietUserFx;

public class DietUserConverter {
    private static DietUserConverter INSTANCE;

    private DietUserConverter() {}

    public static DietUserConverter getInstance() {
        if (INSTANCE == null)
            INSTANCE = new DietUserConverter();
        return INSTANCE;
    }

    public DietUser convertToDietUser(DietUserFx dietUserFx) {
        DietUser dietUser = new DietUser();
        dietUser.setUser(dietUserFx.getUser());
        dietUser.setDietician(dietUserFx.getDietician());
        dietUser.setPlan(dietUserFx.getPlan());
        return dietUser;
    }

    public DietUserFx convertToDietUserFx(DietUser dietUser) {
        DietUserFx dietUserFx = new DietUserFx();
        dietUserFx.setUser(dietUser.getUser());
        dietUserFx.setPlan(dietUser.getPlan());
        dietUserFx.setDietician(dietUser.getDietician());
        return dietUserFx;
    }
}
