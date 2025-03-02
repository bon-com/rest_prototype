package com.example.restprototype.web.controller.common;

import static com.example.restprototype.common.Constant.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restprototype.web.resources.common.ErrorInfo;
import com.example.restprototype.web.resources.common.ValidateErrorInfo;

/**
 * REST APIにてweb.xmlで例外ハンドリングする方法
 * 共通エラーコントローラーにリクエストさせる
 */
@RestController
public class ApiErrorHandleController {

	@GetMapping(value = "/error")
	public ResponseEntity<ErrorInfo> handleError(HttpServletRequest req) {
		Integer status = (Integer) req.getAttribute(ERROR_STATUS_CODE);
		HttpStatus httpStatus = HttpStatus.resolve(status != null ? status : HttpStatus.INTERNAL_SERVER_ERROR.value());
		
		var errorInfo = new ValidateErrorInfo();
		errorInfo.setStatus(httpStatus.value());
		errorInfo.setErrorCode(ERR_CD_SAMPLE);
		if (httpStatus.is4xxClientError()) {
			// 4XX系
			errorInfo.setErrorTitle(ERR_TITLE_BAD_REQUEST);
			errorInfo.setErrorMsg(ERR_MSG_BAD_REQUEST);
		} else {
			// いったん5xx系のみ想定（5xx系かどうかはis5xxServerError()で判定）
			errorInfo.setErrorTitle(ERR_TITLE_INTERNAL_SERVER);
			errorInfo.setErrorMsg(ERR_MSG_INTERNAL_SERVER);
		}
        // ResponseEntity を作成して返却
        return new ResponseEntity<>(errorInfo, httpStatus);
	}
}
