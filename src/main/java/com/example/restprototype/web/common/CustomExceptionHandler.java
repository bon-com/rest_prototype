package com.example.restprototype.web.common;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.restprototype.web.resources.common.ValidateErrorInfo;

/**
 * 例外共通ハンドリングクラス
 * ResponseEntityExceptionHandlerを継承して必要なメソッドをオーバーライドすることで
 * REST APIの例外ハンドリングの作成を補助してくれる
 * 
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	/** ValidationMessages.propertiesからカスタムメッセージの取得 */
	@Autowired
	private MessageSource messageSource;

	/**
	 * 入力チェックエラー共通ハンドリング
	 * MethodArgumentNotValidExceptionをハンドリングする
	 * 
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// エラー内容を設定
		var errorInfo = new ValidateErrorInfo();
		errorInfo.setStatus(HttpStatus.BAD_REQUEST.value());
		errorInfo.setErrorTitle("入力エラー");
		errorInfo.setErrorMsg("入力に誤りあり");
		errorInfo.setErrorCode("EXX0001");
		ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.forEach(e -> {
	                // メッセージのキーを取得（例: "NotNull.java.lang.String"）
	                String messageKey = e.getCode() + "." + e.getField().getClass().getName();
	                // フィールド名をメッセージキーに変換
	                String fieldName = messageSource.getMessage(e.getField(), null, Locale.JAPANESE);
	                // カスタムメッセージを取得
	                String msg = messageSource.getMessage(messageKey, new Object[]{fieldName}, Locale.JAPANESE);
	                errorInfo.addDetails(e.getField(), msg);
					});

		// エラー情報を返却
		return super.handleExceptionInternal(ex, errorInfo, headers, status, request);
	}
	

}
