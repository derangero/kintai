package com.example.demo.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Web アプリケーション全体のエラーコントローラー。
 * ErrorController インターフェースの実装クラス。
 */
@Controller
@RequestMapping("/error") // エラーページへのマッピング
public class MyErrorController implements ErrorController {

  /**
   * エラーページのパスを返す。
   *
   * @return エラーページのパス
   */
  public String getErrorPath() {
    return "/error";
  }

  /**
   * レスポンス用の ModelAndView オブジェクトを返す。
   *
   * @param req リクエスト情報
   * @param mav レスポンス情報
 * @return 
   * @return HTML レスポンス用の ModelAndView オブジェクト
 * @throws IOException 
   */
  @RequestMapping
  public ModelAndView error(HttpServletRequest req, ModelAndView mav,
		    HttpServletResponse response) throws IOException {

    // どのエラーでも 404 Not Found にする
    // 必要に応じてステータコードや出力内容をカスタマイズ可能
	mav.setStatus(HttpStatus.NOT_FOUND);

    // ビュー名を指定する
	//mav.setViewName("/error/error");
    response.setContentType(MediaType.TEXT_PLAIN_VALUE);
    //response.setContentType("text/html; charset=UTF-8");
	return mav;
  }
}