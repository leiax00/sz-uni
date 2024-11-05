package cn.leiax00.app.common.domain.dto;

import cn.leiax00.app.common.constant.HttpStatus;

public class Rst<T> {
    private int code;
    private String msg;
    private T data;

    private Rst(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> Builder<T> newBuilder() {
        return new Builder();
    }

    public static class Builder<T> {
        private int code;
        private String msg;
        private T data;


        public Builder<T> code(int code) {
            this.code = code;
            return this;
        }

        public Builder<T> msg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public Builder<T> status(HttpStatus status) {
            this.code = status.getCode();
            this.msg = status.getDesc();
            return this;
        }

        public Rst<T> build() {
            return new Rst<>(code, msg, data);
        }
    }
}
