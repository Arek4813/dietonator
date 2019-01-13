package utils.converter;

import database.model.LogData;
import modelfx.LogDataFx;

public class LogDataConverter {

    private static LogDataConverter INSTANCE;

    private LogDataConverter() {}

    public static LogDataConverter getInstance() {
        if (INSTANCE == null)
            INSTANCE = new LogDataConverter();
        return INSTANCE;
    }

    public LogData convertToLogData(LogDataFx logDataFx) {
        LogData logData = new LogData();
        logData.setLoginValue(logDataFx.getLoginValue());
        logData.setPasswordValue(logDataFx.getPasswordValue());
        logData.setRoleEnumValue(logDataFx.getRoleEnumValue());
        return logData;
    }

    public LogDataFx convertToLogDataFx(LogData logData) {
        LogDataFx logDataFx = new LogDataFx();
        logDataFx.setLoginValue(logData.getLoginValue());
        logDataFx.setPasswordValue(logData.getPasswordValue());
        logDataFx.setRoleEnumValue(logData.getRoleEnumValue());
        return logDataFx;
    }
}
