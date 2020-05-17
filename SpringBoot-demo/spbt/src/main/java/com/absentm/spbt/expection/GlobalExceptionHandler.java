package com.absentm.spbt.expection;

import com.absentm.spbt.entity.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public JsonResult handleHttpMessageNotReadableException(MissingServletRequestParameterException ex) {
        logger.error("Lack of request param, {}", ex.getMessage());
        return new JsonResult("400", "Lack of request param");
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult handleTypeMismatchException(NullPointerException ex) {
        logger.error("Null Pointer Exception, {}", ex.getMessage());
        return new JsonResult("500", "Null Pointer Exception");
    }

    @ExceptionHandler(SelfErrorException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult handleBusinessError(SelfErrorException ex) {
        String code = ex.getErrorCode();
        String message = ex.getErrorMsg();
        return new JsonResult(code, message);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult handleUnexpectedServer(Exception ex) {
        logger.error("System exception：", ex);
        return new JsonResult("500", "Occur system exception, please contact with administrator");
    }
}
