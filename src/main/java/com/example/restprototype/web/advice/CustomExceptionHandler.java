package com.example.restprototype.web.advice;

import static com.example.restprototype.common.Constant.*;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.restprototype.web.resources.common.ErrorInfo;
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
	 * @Valid または @Validated が付与されたリクエストボディ (@RequestBody) のバリデーションエラーで
	 * MethodArgumentNotValidExceptionがスローされる
	 * 
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// エラー内容を設定
		var errorInfo = createInputError(ex.getBindingResult());

		// エラー情報を返却
		return super.handleExceptionInternal(ex, errorInfo, headers, status, request);
	}

	/**
	 * 入力チェックエラー共通ハンドリング
	 * BindExceptionをハンドリングする
	 * @ModelAttributeや @RequestParam で受け取ったデータのバインド時にエラーが発生したとき
	 * BindExceptionがスローされる
	 * 
	 */
	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		// エラー内容を設定
		var errorInfo = createInputError(ex.getBindingResult());

		// エラー情報を返却
		return super.handleExceptionInternal(ex, errorInfo, headers, status, request);
	}

	/**
	 * エラー情報を返却する（入力チェックエラー用）
	 * @param ex
	 * @return
	 */
	private ValidateErrorInfo createInputError(BindingResult ex) {
		var errorInfo = new ValidateErrorInfo();
		errorInfo.setStatus(HttpStatus.BAD_REQUEST.value());
		errorInfo.setErrorTitle(ERR_TITLE_VALIDATION);
		errorInfo.setErrorMsg(ERR_MSG_VALIDATION);
		errorInfo.setErrorCode(ERR_CD_SAMPLE);
		ex.getFieldErrors()
				.stream()
				.forEach(e -> {
					// メッセージのキーを取得（例: "NotNull.java.lang.String"）
					String messageKey = e.getCode() + "." + e.getField().getClass().getName();
					// フィールド名をメッセージキーに変換
					String fieldName = messageSource.getMessage(e.getField(), null, Locale.JAPANESE);
					// カスタムメッセージを取得
					String msg = messageSource.getMessage(messageKey, new Object[] { fieldName }, Locale.JAPANESE);
					errorInfo.addDetails(e.getField(), msg);
				});

		return errorInfo;

	}

	/**
	 * リソースなしエラー共通ハンドリング
	 * ResponseStatusExceptionをキャッチしてハンドリングする
	 * ResponseStatusExceptionに設定したステータスをもとにハンドリングはできないため、
	 * 一律この例外をキャッチしたうえで、ステータスコードを判定して分岐する必要はある
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<ErrorInfo> handleResponseStatusException(ResponseStatusException ex) {
		var errorInfo = new ErrorInfo();
		errorInfo.setStatus(ex.getStatus().value());
		errorInfo.setErrorTitle(ERR_TITLE_ABOUT_RESOURCE);
		errorInfo.setErrorMsg(ex.getReason());
		errorInfo.setErrorCode(ERR_CD_SAMPLE);

		return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
	}

}
