package com.springboot.cloud.common.core.exception;

import com.springboot.cloud.common.core.entity.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;


@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BaseException.class)
    public Result SZWGBaseException(BaseException e) {
        return Result.fail(e);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result defaultArithmeticHandler(HttpServletRequest req, MethodArgumentNotValidException e) throws Exception {
        return Result.fail(SystemErrorType.ARGUMENT_NOT_VALID);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public Object defaultArithmeticHandler(HttpServletRequest req, ConstraintViolationException e) throws Exception {
        return Result.fail(SystemErrorType.ARGUMENT_NOT_VALID);
    }

    @ExceptionHandler(value = Exception.class)
    public Result exception(Exception e) {
        log.error("系统内部出现错误，错误信息：" + e.getMessage());
        return Result.fail(SystemErrorType.SYSTEM_ERROR);
    }
}
