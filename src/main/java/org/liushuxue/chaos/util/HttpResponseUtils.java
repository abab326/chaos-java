package org.liushuxue.chaos.util;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.liushuxue.chaos.common.BaseResult;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HttpResponseUtils {

    public static <T> void  send(HttpServletResponse response, BaseResult<T> baseResultDTO) throws IOException {
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        response.setStatus(baseResultDTO.getCode());
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(JSON.toJSONString(baseResultDTO).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
