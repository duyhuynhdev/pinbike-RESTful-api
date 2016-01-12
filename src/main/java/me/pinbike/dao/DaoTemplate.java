package me.pinbike.dao;

import com.pinride.pinbike.config.Const.PinBike.ErrorCode;
import com.pinride.pinbike.config.adapter.AdapterResponseValue.ResponseListValue;
import com.pinride.pinbike.config.adapter.AdapterResponseValue.ResponseValue;
import com.pinride.pinbike.thriftclient.AbstractClient;
import me.pinbike.provider.exception.PinBikeException;
import me.pinbike.sharedjava.model.constanst.AC;
import me.pinbike.util.PinBikeConstant;
import me.pinbike.util.SendMailUtil;
import org.apache.log4j.Logger;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by hpduy17 on 12/8/15.
 */
public class DaoTemplate<T> {

    protected AbstractClient<T> client;
    protected Logger logger;

    public T insert(T object) {
        try {
            logger.info(object.toString());
            ResponseValue<T> response = client.insert(object);
            validateResponse(response.getErrorCode(), getGenericName() + ".insert()", object.toString());
            return response.getValue();
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }

    public void update(T object) {
        try {
            logger.info(object.toString());
            int errorCode = client.update(object);
            validateResponse(errorCode, getGenericName() + ".update()", object.toString());
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }

    }

    public void delete(long id) {
        try {
            logger.info(id);
            int errorCode = client.remove(id);
            validateResponse(errorCode, getGenericName() + ".delete()", "" + id);
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }

    public T get(long id) {
        try {
            logger.info(id);
            ResponseValue<T> response = client.get(id);
            validateResponse(response.getErrorCode(), getGenericName() + ".get()", "" + id);
            return response.getValue();
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }

    public List<T> getList(List<Long> ids) {
        try {
            if (ids == null || ids.isEmpty())
                return new ArrayList<>();
            logger.info(ids.toArray());
            ResponseListValue<T> response = client.gets(ids);
            validateResponse(response.getErrorCode(), getGenericName() + ".getList()", ids.toString());
            return response.getList();
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }

    public List<T> getAll() {
        try {
            logger.info("no-param");
            ResponseListValue<T> response = client.getAll();
            validateResponse(response.getErrorCode(), getGenericName() + ".getAll()", "no-param");
            return response.getList();
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }

    public void validateResponse(final int errorCode, final String methodName, final String inputObject) {
        boolean isSuccess = false;
        String message = "";
        int messageCode = AC.MessageCode.FAIL;
        switch (errorCode) {
            case ErrorCode.CONNECT_SERVER_FAIL:
                message = "Connect to server fail";
                messageCode = AC.MessageCode.BACKEND_ERROR;
                break;
            case ErrorCode.DUPLICATE_ID:
                message = "Id is duplicated";
                messageCode = AC.MessageCode.ELEMENT_USED;
                break;
            case ErrorCode.FAIL:
                message = "Something fail in backend";
                messageCode = AC.MessageCode.FAIL;
                break;
            case ErrorCode.FAIL_CACHE_LOCAL:
                message = "Fail in CACHE LOCAL";
                messageCode = AC.MessageCode.BACKEND_ERROR;
                break;
            case ErrorCode.FAIL_GEARMAN:
                message = "Fail in GEARMAN";
                messageCode = AC.MessageCode.BACKEND_ERROR;
                break;
            case ErrorCode.FAIL_KAFKA:
                message = "Fail in KAFKA";
                messageCode = AC.MessageCode.BACKEND_ERROR;
                break;
            case ErrorCode.FAIL_MYSQL:
                message = "Fail in MYSQL";
                messageCode = AC.MessageCode.BACKEND_ERROR;
                break;
            case ErrorCode.FAIL_REDIS:
                message = "Fail in REDIS";
                messageCode = AC.MessageCode.BACKEND_ERROR;
                break;
            case ErrorCode.GENERATE_ID_FAIL:
                message = "Generate id fail";
                messageCode = AC.MessageCode.BACKEND_ERROR;
                break;
            case ErrorCode.GENERATE_KEY_FAIL:
                message = "Generate key fail";
                messageCode = AC.MessageCode.BACKEND_ERROR;
                break;
            case ErrorCode.MISS_MAPPING:
                message = "Miss mapping";
                messageCode = AC.MessageCode.BACKEND_ERROR;
                break;
            case ErrorCode.NOT_EXISTS:
                message = "Object not exits";
                messageCode = AC.MessageCode.NOT_EXIST;
                break;
            case ErrorCode.NULL_POINTER:
                message = "Null pointer";
                messageCode = AC.MessageCode.NOT_EXIST;
                break;
            case ErrorCode.PARSER_DATA_FAIL:
                message = "Parse data fail";
                messageCode = AC.MessageCode.BACKEND_ERROR;
                break;
            case ErrorCode.SUCCESS:
                isSuccess = true;
                break;
        }
        if (!isSuccess) {
            PinBikeException ex = new PinBikeException(messageCode, message);
            SendMailUtil.sendPinBikeError(PinBikeConstant.DeveloperMail.backend_mails, errorCode, methodName, inputObject, ex);
            throw ex;
        }
    }

    protected String getGenericName() {
        return ((Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName();
    }


}
