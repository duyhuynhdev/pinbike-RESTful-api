package me.pinbike.dao.non_db;

import me.pinbike.provider.exception.PinBikeException;
import me.pinbike.sharedjava.model.constanst.AC;
import me.pinbike.util.DateTimeUtils;
import me.pinbike.util.LogUtil;
import me.pinbike.util.sms.SMSManager;
import org.apache.log4j.Logger;
import org.fluttercode.datafactory.impl.DataFactory;

import java.util.HashMap;

/**
 * Created by hpduy17 on 1/11/16.
 */
public class ActivationDao {
    private final long expiredTimeInSecond = 10 * DateTimeUtils.MINUTES;
    private final int numberCharOfCode = 4;
    private static HashMap<String, ActCode> regBoard = new HashMap<>();
    private static HashMap<String, Long> codeGenerated = new HashMap<>();
    private DataFactory factory = new DataFactory();
    private Logger logger = LogUtil.getLogger(this.getClass());
    private final int ACTIVE_SUCCESS = 1;
    private final int WRONG_CODE = 2;
    private final int EXPIRED_CODE = 3;
    private final int GET_CODE_FAIL = 4;

    public String getActivationCode(String phoneNumber) {
        try {
            ActCode actCode = new ActCode();
            actCode.code = generate();
            actCode.epochTimeInSecond = DateTimeUtils.now();
            regBoard.put(phoneNumber, actCode);
            // send SMS
            new SMSManager().sendActivationCode(phoneNumber,actCode.code);
            //if success
            codeGenerated.put(actCode.code, DateTimeUtils.now());
            return actCode.code;
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }

    public boolean activateAccount(String phoneNumber, String activationCode) {
        try {
            int errorCode = WRONG_CODE;
            if (regBoard.containsKey(phoneNumber)) {
                if (regBoard.get(phoneNumber).code.equals(activationCode)) {
                    if (DateTimeUtils.now() - regBoard.get(phoneNumber).epochTimeInSecond < expiredTimeInSecond) {
                        errorCode = ACTIVE_SUCCESS;
                    } else {
                        errorCode = EXPIRED_CODE;
                    }
                }
            }
            validate(errorCode);
            return true;
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }

    private void validate(final int errorCode) {
        boolean isSuccess = false;
        String message = "";
        int messageCode = AC.MessageCode.FAIL;
        switch (errorCode) {
            case WRONG_CODE:
                message = "Your activation code is wrong";
                messageCode = AC.MessageCode.WRONG_CODE;
                break;
            case EXPIRED_CODE:
                message = "Your activation code is expired";
                messageCode = AC.MessageCode.ACTIVATION_CODE_EXPIRED;
                break;
            case GET_CODE_FAIL:
                message = "Cannot get activation code in recent";
                messageCode = AC.MessageCode.GET_CODE_FAIL;
                break;
            case ACTIVE_SUCCESS:
                isSuccess = true;
                break;
        }
        if (!isSuccess) {
            throw new PinBikeException(messageCode, message);
        }
    }

    private String generate() {
        String code = factory.getNumberText(numberCharOfCode);
        HashMap<String, Long> generatedList = new HashMap<>(codeGenerated);
        while (generatedList.containsKey(code)) {
            if (DateTimeUtils.now() - generatedList.get(code) > expiredTimeInSecond) {
                codeGenerated.remove(code);
                break;
            }
            code = factory.getNumberText(numberCharOfCode);
        }
        return code;
    }

    private static class ActCode {
        public String code;
        public long epochTimeInSecond;
    }


}
