package com.example.demo.aspect;

import java.lang.reflect.InvocationTargetException;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ErrorAspect {

    //全てのクラスを対象
    @AfterThrowing(value="execution(* *..*..*(..))"
            + " && (bean(*Controller) || bean(*Service) || bean(*Repository)) || bean(*GlobalControllAdvice) || bean(*InvocationTargetException)"
            , throwing="ex")
    public void throwingNull(NullPointerException ex) {

        //例外処理の内容（ログ出力）
        System.out.println("===========================================");
        System.out.println("NumberFormatExceptionが発生しました。 : " + ex);
        System.out.println("===========================================");
    }

    @AfterThrowing(value="execution(* *..*..*(..))"
    		+ " && (bean(*Controller) || bean(*Service) || bean(*Repository)) || bean(*GlobalControllAdvice) || bean(*InvocationTargetException)"
    		, throwing="exa")
    public void throwingNull2(InvocationTargetException exa) {
    	
    	//例外処理の内容（ログ出力）
    	System.out.println("===========================================");
    	System.out.println("NumberFormatExceptionが発生しました。 : " + exa);
    	System.out.println("===========================================");
    }

}