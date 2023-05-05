package com.ichong.advice;

import com.ichong.exception.RedissonRateLimitException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ClassName: CustomExceptionHandler
 * Description:
 *
 * @author 陈高文
 * @date 2023/5/5 11:29
 */
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(RedissonRateLimitException.class)
    public void redissonRateLimitException(RedissonRateLimitException exception, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.write(exception.getMessage());
        out.flush();
        out.close();
    }
}
