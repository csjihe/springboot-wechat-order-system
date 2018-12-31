package com.csjihe.springbootwechatordersystem.VO;


import lombok.Data;

/**
 * HTTP 请求返回的最外层对象
 */

@Data
public class ResultVO<T> {

    /** code used as error code, 0 means no error */
    private Integer code;

    /** hint message */
    private String msg;

    /** content returned */
    private T data;
}
