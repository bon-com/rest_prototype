package com.example.restprototype.web.controller.type08;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.restprototype.biz.service.ResourceService;
import com.example.restprototype.web.input.ResourceQuery2;
import com.example.restprototype.web.input.ResourceReq2;
import com.example.restprototype.web.resources.Resource;

/**
 * REST APIにて入力チェックエラーを共通処理にてハンドリングする方法（カスタムエラーメッセージも利用）
 * ResponseEntityExceptionHandlerを使用した共通例外ハンドリングを行う
 * 
 */
@RestController
public class Type8RestController {
	
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
	@PostMapping(value = "type8/create")
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
	 * GETリクエストされたリソースを検索する
	 * 入力チェックを行う
	 * @param id
	 * @return
	 */
	@ResponseBody
	@GetMapping(value = "type8/")
	public List<Resource> get(@Valid ResourceQuery2 queryParam) {
		// 検索結果
		List<Resource> resList = service.findByParam(queryParam);
		
		return resList;
	}
	
}
