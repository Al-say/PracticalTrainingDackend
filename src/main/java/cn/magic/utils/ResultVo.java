package cn.magic.utils;

import lombok.Data;
//返回结果
@Data
public class ResultVo<T> {
    private boolean flag;  // 成功失败
    private String message; // 返回消息
    private T data;   // 返回数据

    //成功不添加data
    public static <T> ResultVo<T> ok(String message){
        ResultVo<T> resultVo = new ResultVo<>();
        resultVo.setMessage(message);
        resultVo.setFlag(true);
        return resultVo;
    }

    //成功添加data
    public static <T> ResultVo<T> ok(T data){
        ResultVo<T> resultVo = new ResultVo<>();
        resultVo.setData(data);
        resultVo.setFlag(true);
        return resultVo;
    }

    //成功添加data和message
    public static <T> ResultVo<T> ok(T data, String message){
        ResultVo<T> resultVo = new ResultVo<>();
        resultVo.setData(data);
        resultVo.setFlag(true);
        resultVo.setMessage(message);
        return resultVo;
    }

    //失败
    public static <T> ResultVo<T> fail(String message){
        ResultVo<T> resultVo = new ResultVo<>();
        resultVo.setFlag(false);
        resultVo.setMessage(message);
        return resultVo;
    }

    //失败返回状态数据
    public static <T> ResultVo<T> fail(String message, T data){
        ResultVo<T> resultVo = new ResultVo<>();
        resultVo.setFlag(false);
        resultVo.setMessage(message);
        resultVo.setData(data);
        return resultVo;
    }

    // 异常
    public static <T> ResultVo<T> error(Exception e){
        ResultVo<T> resultVo = new ResultVo<>();
        resultVo.setMessage("系统异常:" + e.getMessage());
        resultVo.setFlag(false);
        return resultVo;
    }
}
