package uy.com.geocom.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({MethodArgumentNotValidException.class})
	@ResponseBody
	public ErrorMessage handleBadRequest(MethodArgumentNotValidException exception) {
		String errorMsg = exception.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse(exception.getMessage());
		ErrorMessage errorMessage =  new ErrorMessage(HttpStatus.BAD_REQUEST.value(), errorMsg);
		LogManager.getLogger(this.getClass()).info("  ERROR: NO_CONTENT, " + errorMessage);
		logger.error("Error", exception);
		return errorMessage;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({Exception.class})
	@ResponseBody
	public ErrorMessage defaultErrorHandler(Exception exception){
		ErrorMessage errorMessage =  new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),exception.getMessage());
		LogManager.getLogger(this.getClass()).info("  ERROR: INTERNAL_SERVER_ERROR, " + errorMessage);
		logger.error("Error Interno del servidor", exception);
		return errorMessage;
	}
}
