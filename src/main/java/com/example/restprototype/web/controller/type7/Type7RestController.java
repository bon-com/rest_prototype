package com.example.restprototype.web.controller.type7;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.restprototype.biz.service.ResourceService;
import com.example.restprototype.web.input.ResourceReq2;
import com.example.restprototype.web.resources.Resource;
import com.example.restprototype.web.resources.common.ValidateErrorInfo;

/**
 * REST APIにて基本的な入力チェックを行う方法
 * Hibernate Validatorライブラリを導入
 * 
 */
@RestController
public class Type7RestController {
	
	@Autowired
	private ResourceService service;
	
	/**
	 * POSTリクエストされたリソースを登録する
	 * 入力チェックを行う
	 * メソッドの@RequestBodyに対して@Validを使用している場合、
	 * MethodArgumentNotValidExceptionがスローされる
	 * @param req
	 * @param builder
	 * @return
	 */
	@PostMapping(value = "type7/create")
	public ResponseEntity<Void> post(@Valid @RequestBody ResourceReq2 req, UriComponentsBuilder builder) {
		// リソース登録
		var resource = new Resource(req.getId(), req.getName(), req.getHogeDate());
		service.create(resource);
		
		// ヘッダー設定用URI
		URI resourceUri = builder
				.path("/type1/{id}") 
				.buildAndExpand(resource.getId())
				.encode()
				.toUri();
		
		return ResponseEntity.created(resourceUri).build();
	}
	
	/**
	 * コントローラー特有の例外ハンドリング
	 * デフォルトのメッセージを返却する
	 * @param ex
	 * @return
	 */
	@ExceptionHandler
	public ResponseEntity<Object> handlerException(MethodArgumentNotValidException ex) {
		// エラー内容を設定
		var errorInfo = new ValidateErrorInfo();
		errorInfo.setStatus(HttpStatus.BAD_REQUEST.value());
		errorInfo.setErrorTitle("入力エラー");
		errorInfo.setErrorMsg("入力に誤りあり");
		errorInfo.setErrorCode("EXX0001");
		ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.forEach(e -> errorInfo.addDetails(e.getField(), e.getDefaultMessage()));
		
        // エラー情報を返却
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
	}
}
