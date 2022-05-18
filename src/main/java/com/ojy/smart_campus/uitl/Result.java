package com.ojy.smart_campus.uitl;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import springfox.documentation.builders.ParameterBuilder;

/**
 * 全局统一返回结果类
 * @param <T>
 */
@Data
@ApiModel(value = "全局统一返回结果")
public class Result<T> {

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;

    /**
     * 返回数据
     * @param data
     * @param <T>
     * @return
     */
    protected static <T> Result<T> build(T data) {
        Result<T> result = new Result<>();
        if (data != null) {
            result.setData(data);
        }
        return result;
    }

    public static <T> Result<T> build(T data, ResultCodeEnum resultCodeEnum) {
        Result<T> result = build(data);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }

    /**
     * 操作成功
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> ok(T data) {
        return build(data, ResultCodeEnum.SUCCESS);
    }

    public static <T> Result<T> ok() {
        return Result.ok(null);
    }

    /**
     * 操作失败
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail(T data) {
        return build(data, ResultCodeEnum.FAIL);
    }

    public static <T> Result<T> fail() {
        return Result.fail(null);
    }

    /**
     *
     * @param msg
     * @return
     */
    public Result<T> message(String msg) {
        this.setMessage(msg);
        return this;
    }

    /**
     *
     * @param code
     * @return
     */
    public Result<T> code(Integer code) {
        this.setCode(code);
        return this;
    }

    public  boolean isOk() {
        if (this.getCode().intValue() == ResultCodeEnum.SUCCESS.getCode().intValue()) {
            return true;
        }
        return false;
    }
}
