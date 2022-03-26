package cn.sichu.graduatemall.common.api;

/**
 * 封装API的错误码
 * 
 * @author sichu
 * @date 2022/03/26
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
