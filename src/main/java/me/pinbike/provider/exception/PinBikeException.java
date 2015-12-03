package me.pinbike.provider.exception;

import com.pinride.pinbike.framework.util.JSONUtil;
import me.pinbike.util.PinBikeConstant;

import javax.validation.ValidationException;

/**
 * Created by hpduy17 on 10/26/15.
 */
public class PinBikeException extends ValidationException {
    private int messageCode = PinBikeConstant.MessageCode.Element_is_invalid;

    public PinBikeException() {
    }

    public PinBikeException(int messageCode, String message) {
        super(message);
        this.messageCode = messageCode;
    }

    public PinBikeException(int messageCode) {
        this.messageCode = messageCode;
    }

    public PinBikeException(String message, Throwable cause, int messageCode) {
        super(message, cause);
        this.messageCode = messageCode;
    }

    public PinBikeException(Throwable cause, int messageCode) {
        super(cause);
        this.messageCode = messageCode;
    }

    public int getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(int messageCode) {
        this.messageCode = messageCode;
    }

    @Override
    public String toString() {
        return JSONUtil.Serialize(this);
    }
}
