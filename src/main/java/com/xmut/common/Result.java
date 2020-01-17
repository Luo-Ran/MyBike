package com.xmut.common;

import java.io.Serializable;
/**
 * 请求返回结果对象
 *
 * @author Administrator
 */
public class Result<T> implements Serializable{
    //返回状态编码
    private String statusCode = ResultType.SUCCESS.getCode();
    //返回提示信息
    private String message = ResultType.SUCCESS.getName();
    //返回结果
    private T data = null;
    //是否成功
    private Boolean success = true;

    /**
     * 获取状态编码
     * @return
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * 设置状态编码
     * @param statusCode
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * 获取提示信息
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置提示信息
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取返回结果
     * @return
     */
    public T getData() {
        return data;
    }

    /**
     * 设置返回结果
     * @param data
     */
    public void setData(T data) {
        this.data = data;
    }

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void addError() {
        this.addError("");
    }

    public void addError(String message) {
        this.success = false;
        this.message = message;
        this.statusCode = ResultType.INTERNAL_SERVER_ERROR.getCode();
        if(this.message == null || "".equals(this.message)){
            this.message = ResultType.INTERNAL_SERVER_ERROR.getName();
        }
    }

    public void success() {
        this.success("");
    }

    public void success(String message) {
        this.success = true;
        this.message = message;
        this.statusCode = ResultType.SUCCESS.getCode();
        if(this.message == null || "".equals(this.message)){
            this.message = ResultType.SUCCESS.getName();
        }
    }

    public void fail() {
        this.fail("");
    }

    public void fail(String message) {
        this.success = false;
        this.message = message;
        this.statusCode = ResultType.FAIL.getCode();
        if(this.message == null || "".equals(this.message)){
            this.message = ResultType.FAIL.getName();
        }
    }

    public void unauthorized() {
        this.unauthorized("");
    }

    public void unauthorized(String message) {
        this.success = false;
        this.message = message;
        this.statusCode = ResultType.UNAUTHORIZED.getCode();

        if(this.message == null || "".equals(this.message)){
            this.message = ResultType.UNAUTHORIZED.getName();
        }
    }

    public void notFound() {
        this.notFound("");
    }

    public void notFound(String message) {
        this.success = false;
        this.message = message;
        this.statusCode = ResultType.NOT_FOUND.getCode();
        if(this.message == null || "".equals(this.message)){
            this.message = ResultType.NOT_FOUND.getName();
        }
    }

    public void againLogin() {
        this.againLogin("");
    }

    public void againLogin(String message) {
        this.success = false;
        this.message = message;
        this.statusCode = ResultType.AGAIN_LOGIN.getCode();
        if(this.message == null || "".equals(this.message)){
            this.message = ResultType.AGAIN_LOGIN.getName();
        }
    }
}
