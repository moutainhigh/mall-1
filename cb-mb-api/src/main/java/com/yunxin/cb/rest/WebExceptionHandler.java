package com.yunxin.cb.rest;

import com.yunxin.cb.mall.exception.CommonException;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.vo.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@ControllerAdvice
@ResponseBody
public class WebExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(WebExceptionHandler.class);

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseResult handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("参数解析失败", e);
        return new ResponseResult(Result.FAILURE, "系统繁忙，请稍后重试");
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseResult handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.error("不支持当前请求方法", e);
        return new ResponseResult(Result.FAILURE, "系统繁忙，请稍后重试");
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseResult handleHttpMediaTypeNotSupportedException(Exception e) {
        logger.error("不支持当前媒体类型", e);
        return new ResponseResult(Result.FAILURE, "系统繁忙，请稍后重试");
    }

//    /**
//     * 404 - Not Found
//     */
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(NoHandlerFoundException.class)
//    public ResponseResult handleNotFound(NoHandlerFoundException e) {
//        logger.error("URL不存在", e);
//        return new ResponseResult(Result.FAILURE, e.getMessage());
//    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseResult handleException(Exception e) {
        if (e instanceof CommonException) {
            return new ResponseResult(Result.FAILURE, e.getMessage());
        }
        logger.error("服务运行异常", e);
        return new ResponseResult(Result.FAILURE, "系统繁忙，请稍后重试");
    }

    /**
     * VO参数验证异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error("VO对象参数验证失败", e);
        FieldError fieldError = e.getBindingResult().getFieldError();
        return new ResponseResult(Result.FAILURE, fieldError.getDefaultMessage());
    }

    /**
     * 方法参数验证错误异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseResult handleConstraintViolationException(ConstraintViolationException e) {
        logger.error("方法参数验证失败", e);
        String message = "";
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            message = violation.getMessage();
            break;
        }
        return new ResponseResult(Result.FAILURE, message);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ResponseResult handleThrowable(Throwable e) throws Throwable {
        //LogAspect aop异常处理
        if (e instanceof ConstraintViolationException) {
            ConstraintViolationException ex = (ConstraintViolationException)e;
            return handleConstraintViolationException(ex);
        }
        logger.error("服务运行异常", e);
        return new ResponseResult(Result.FAILURE, "系统繁忙，请稍后重试");
    }


}