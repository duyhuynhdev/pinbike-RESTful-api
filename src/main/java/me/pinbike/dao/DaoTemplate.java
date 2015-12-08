package me.pinbike.dao;

import com.pinride.pinbike.config.Const.PinBike.ErrorCode;
import com.pinride.pinbike.config.adapter.AdapterResponseValue;
import me.pinbike.provider.exception.PinBikeException;
import me.pinbike.sharedjava.model.constanst.MessageCode;


/**
 * Created by hpduy17 on 12/8/15.
 */
public class DaoTemplate {
    public void validateResponse(AdapterResponseValue.ResponseValue responseValue, String defaultMessage) {
        boolean isSuccess = false;
        String message = "";
        int messageCode = MessageCode.FAIL;
        switch (responseValue.getErrorCode()) {
            case ErrorCode.CONNECT_SERVER_FAIL:
                message = "Connect to server fail";
                messageCode = MessageCode.BACKEND_ERROR;
                break;
            case ErrorCode.DUPLICATE_ID:
                message = "Id is duplicated";
                messageCode = MessageCode.ELEMENT_USED;
                break;
            case ErrorCode.FAIL:
                message = "Something fail in backend";
                messageCode = MessageCode.FAIL;
                break;
            case ErrorCode.FAIL_CACHE_LOCAL:
                message = "Fail in CACHE LOCAL";
                messageCode = MessageCode.BACKEND_ERROR;
                break;
            case ErrorCode.FAIL_GEARMAN:
                message = "Fail in GEARMAN";
                messageCode = MessageCode.BACKEND_ERROR;
                break;
            case ErrorCode.FAIL_KAFKA:
                message = "Fail in KAFKA";
                messageCode = MessageCode.BACKEND_ERROR;
                break;
            case ErrorCode.FAIL_MYSQL:
                message = "Fail in MYSQL";
                messageCode = MessageCode.BACKEND_ERROR;
                break;
            case ErrorCode.FAIL_REDIS:
                message = "Fail in REDIS";
                messageCode = MessageCode.BACKEND_ERROR;
                break;
            case ErrorCode.GENERATE_ID_FAIL:
                message = "Generate id fail";
                messageCode = MessageCode.BACKEND_ERROR;
                break;
            case ErrorCode.GENERATE_KEY_FAIL:
                message = "Generate key fail";
                messageCode = MessageCode.BACKEND_ERROR;
                break;
            case ErrorCode.MISS_MAPPING:
                message = "Miss mapping";
                messageCode = MessageCode.BACKEND_ERROR;
                break;
            case ErrorCode.NOT_EXISTS:
                message = "Object not exits";
                messageCode = MessageCode.NOT_EXIST;
                break;
            case ErrorCode.NULL_POINTER:
                message = "Null pointer";
                messageCode = MessageCode.NOT_EXIST;
                break;
            case ErrorCode.PARSER_DATA_FAIL:
                message = "Parse data fail";
                messageCode = MessageCode.NOT_EXIST;
                break;
            case ErrorCode.SUCCESS:
                isSuccess = true;
                break;
        }
        if (!isSuccess) {
            throw new PinBikeException(messageCode, message);
        }
    }
}
