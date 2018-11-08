package com.lizhb.base;

/**
 * API格式封装体
 * Created by lizhb.
 * Date: 2018/11/5
 * Time: 10:51
 */
public class APIRespose {
    private int code;
    private String message;
    private Object data;
    private boolean more;

    public APIRespose() {
    }
    public APIRespose(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public APIRespose(int code, String message, Object data, boolean more) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.more = more;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isMore() {
        return more;
    }

    public void setMore(boolean more) {
        this.more = more;
    }

    public static APIRespose ofMessage(int status, String message) {
        return new APIRespose(status, message, null, false);
    }

    public enum Status {
        SUCCESS(200, "OK"),
        BAD_REQUEST(400, "BAD REQUEST"),
        INTERNAL_ERROR(500, "UNKNOWN_SERVER_ERROR"),
        NOT_VALID_PARAM(40005, "NOT VALID PARAMS"),
        NOT_SUPPORTED_OPERATION(40006, "OPERATION NOT SUPPORTED"),
        NOT_LOGIN(50000, "NOT LOGIN");

        private int code;
        private String standardMsg;

        Status(int code, String standardMsg) {
            this.code = code;
            this.standardMsg = standardMsg;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getStandardMsg() {
            return standardMsg;
        }

        public void setStandardMsg(String standardMsg) {
            this.standardMsg = standardMsg;
        }
    }
}
