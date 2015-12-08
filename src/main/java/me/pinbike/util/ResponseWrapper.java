package me.pinbike.util;

/**
 * Created by hpduy17 on 10/13/15.
 */
public class ResponseWrapper<T> {
    protected boolean success = false;
    protected int messageCode;
    protected String message = "";
    protected T result = null;

    public ResponseWrapper() {
        this.success = true;
        this.messageCode = PinBikeConstant.MessageCode.Successfully;
        this.message = "Successfully";
    }

    public ResponseWrapper(T result) {
        this.success = true;
        this.messageCode = PinBikeConstant.MessageCode.Successfully;
        this.message = "Successfully";
        this.result = result;
    }


    public ResponseWrapper(int messageCode, T result) {
        this.success = true;
        this.messageCode = messageCode;
        this.message = "Successfully";
        this.result = result;
    }

    public ResponseWrapper(boolean success, int messageCode, String message) {
        this.success = success;
        this.messageCode = messageCode;
        this.message = message;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(int messageCode) {
        this.messageCode = messageCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
