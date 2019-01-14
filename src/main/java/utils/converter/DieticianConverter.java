package utils.converter;

import database.model.Dietician;
import modelfx.DieticianFx;

public class DieticianConverter {

    private static DieticianConverter INSTANCE;

    private DieticianConverter() {}

    public static DieticianConverter getInstance() {
        if (INSTANCE == null)
            INSTANCE = new DieticianConverter();
        return INSTANCE;
    }

    public Dietician convertToDietician(DieticianFx dieticianFx) {
        Dietician dietician = new Dietician();
        dietician.setDieticianLogin(dieticianFx.getLogin());
        dietician.setDieticianPassword(dieticianFx.getPassword());
        dietician.setDieticianName(dieticianFx.getName());
        dietician.setDieticianSurname(dieticianFx.getSurname());
        dietician.setDieticianBirthDate(dieticianFx.getBirthDate());
        dietician.setDieticianMail(dieticianFx.getMail());
        return dietician;
    }

    public DieticianFx convertToDieticianFx(Dietician dietician) {
        DieticianFx dieticianFx = new DieticianFx();
        dieticianFx.setLogin(dietician.getDieticianLogin());
        dieticianFx.setPassword(dietician.getDieticianPassword());
        dieticianFx.setName(dietician.getDieticianName());
        dieticianFx.setSurname(dietician.getDieticianSurname());
        dieticianFx.setBirthDate(dietician.getDieticianBirthDate());
        dieticianFx.setMail(dietician.getDieticianMail());
        return dieticianFx;
    }



}
