package xin.utong.configurer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;
import xin.utong.core.Result;
import xin.utong.core.ResultCode;
import xin.utong.core.ServiceException;

/**
 * 统一异常
 * Created by yutong on 2017/7/14.
 */
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger("error");

    /**
     * 未知异常
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Result<Void> handlerUnknownException(Exception ex){
        Result<Void> result = new Result<>();
        result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage("未知异常");
        logger.error("Unknown Error ", ex);
        return result;
    }

    /**
     * 业务异常
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ServiceException.class)
    public Result<Void> handlerServiceException(ServiceException ex) {
        Result<Void> result = new Result<>();
        result.setCode(ResultCode.FAIL).setMessage(ex.getMessage());
        logger.error("Service Error ", ex);
        return result;
    }

    /**
     * 未认证异常
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public Result<Void> handlerAuthenticationException(AuthenticationException ex) {
        Result<Void> result = new Result<>();
        result.setCode(ResultCode.UNAUTHORIZED).setMessage("Unauthorized");
        logger.error("Unauthorized Error ", ex);
        return result;
    }

    /**
     * 未授权异常
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public Result<Void> handlerAccessDeniedException(AccessDeniedException ex) {
        Result<Void> result = new Result<>();
        result.setCode(ResultCode.FORBIDDEN).setMessage("Access Denied");
        logger.error("Access Denied", ex);
        return result;
    }

    /**
     * 404异常
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result<Void> handlerNoHandlerFoundException(NoHandlerFoundException ex) {
        Result<Void> result = new Result<>();
        result.setCode(ResultCode.NOT_FOUND).setMessage("Not Found");
        logger.error("Not Found ", ex);
        return result;
    }
}
