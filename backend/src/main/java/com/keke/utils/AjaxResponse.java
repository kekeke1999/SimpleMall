package com.keke.utils;

public class AjaxResponse{

    private boolean status;

    private int code;

    private String message;

    private Object data;

    private Object token;

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "AjaxResponse{" +
                "status=" + status +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", token=" + token +
                '}';
    }

    public static AjaxResponse success(){
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setStatus(true);
        resultBean.setCode(200);
        resultBean.setMessage("success");
        return resultBean;
    }

    public static AjaxResponse fail2(){
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setCode(50000);
        resultBean.setMessage("token verify fail");
        resultBean.setStatus(false);
        return resultBean;
    }

    public static AjaxResponse fail(){
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setStatus(false);
        resultBean.setCode(400);
        resultBean.setMessage("failed");
        return resultBean;
    }

    public static AjaxResponse success2(Object token){
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setStatus(true);
        resultBean.setCode(400);
        resultBean.setMessage("success");
        resultBean.setToken(token);
        return resultBean;
    }

    public static AjaxResponse success(Object data){
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setStatus(true);
        resultBean.setCode(400);
        resultBean.setMessage("success");
     //   System.out.println("data:"+data);
        resultBean.setData(data);
    //    System.out.println("resultBean:"+resultBean);
        return resultBean;
    }
}
