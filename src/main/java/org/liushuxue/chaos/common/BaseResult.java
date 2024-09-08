package org.liushuxue.chaos.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.liushuxue.chaos.enums.ResultStatusEnum;

import java.io.Serializable;

@Schema(description = "响应结果")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResult<T> implements Serializable {

    @Schema(description = "状态码")
    private Integer code;
    @Schema(description = "消息")
    private String message;
    @Schema(description = "数据" )
    private T data;

    public static BaseResult<String> success() {
        return  success(ResultStatusEnum.SUCCESS.getCode(),ResultStatusEnum.SUCCESS.getMessage(),"");
    }
    public static <T> BaseResult<T> success(Integer code, String message, T data) {
        return  new BaseResult<>(code,message,data);
    }
    public static <T> BaseResult<T> success(T data) {
         return  success(ResultStatusEnum.SUCCESS.getCode(),ResultStatusEnum.SUCCESS.getMessage(),data);
    }

    public static BaseResult<String> error(Integer code, String message) {
         return error(code,message,"");
    }
    public static BaseResult<String> error(ResultStatusEnum httpStatus) {
        return error(httpStatus.getCode(),httpStatus.getMessage(),"");
    }

    public static BaseResult<String> error(ResultStatusEnum httpStatus, String message) {
       return error(httpStatus.getCode(),message,"");
    }

    public static BaseResult<Exception> error(ResultStatusEnum httpStatus, Exception exception) {
        return error(httpStatus.getCode(),httpStatus.getMessage(),exception);
    }

    public static <T> BaseResult<T> error(Integer code, String message, T data) {
        return  new BaseResult<>(code,message,data);
    }
}
