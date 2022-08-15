package com.example.demo.aspect.advice;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.annotation.Message;
import com.example.demo.common.util.RefrectionUtil;
import com.example.demo.common.util.StringUtil;

@ControllerAdvice
@Component
public class GlobalControllAdvice {

	@Autowired
    MessageSource msgSource;
    
    @ExceptionHandler(BindException.class)
    public void exceptionHandler(BindException e, Model model, HttpServletResponse response) throws Exception {
    	//response.getWriter().flush();
    	//response.setContentType(MediaType.TEXT_PLAIN_VALUE);
    	try {
    		String a = null;
    		a.codePointAt(0);
    		response.setContentType("text/html; charset=UTF-8");
    		BindingResult bindingResult = e.getBindingResult();
    		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
    		for (int ii = 0; ii < fieldErrors.size(); ii++) {
    			FieldError error = fieldErrors.get(ii);
    			String comma = ii != 0 ? "," : "";
    			String errorFieldName = getErrorFieldName(error);
    			String errorMsg = StringUtil.joinBySemicolon(comma + errorFieldName, getBindingErrorContent(error));
    			response.getWriter().write(errorMsg);
    		}
    		
    	} catch(NullPointerException ee) {
    		int a = 0;
    		
    	}
       //return "/error";
    }

    private String getErrorFieldName(FieldError error) throws Exception {
    	String errorFieldName = "";
		Method method = RefrectionUtil.getDtoClazzMethod(error.getObjectName(), error.getField());
		Message messageClazz = method.getAnnotation(Message.class);
		if (messageClazz != null) {
			errorFieldName = msgSource.getMessage(messageClazz.message(), null, Locale.JAPAN);
		}

		return errorFieldName;
	}

	@ExceptionHandler(NullPointerException.class)
    public void nullPointerExceptionHandler(BindException e, Model model, HttpServletResponse response) throws Exception {
    	int a = 0;
    }
   
    private String getBindingErrorContent(FieldError error) throws InvocationTargetException {
		String errorContent = error.getDefaultMessage();
		if (errorContent.contains("NumberFormatException")) {
			errorContent = msgSource.getMessage("common.error.numberFormat", null, Locale.JAPAN);
		}

		return errorContent;
    }
}