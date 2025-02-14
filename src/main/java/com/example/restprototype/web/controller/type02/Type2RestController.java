package com.example.restprototype.web.controller.type02;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restprototype.biz.service.ResourceService;
import com.example.restprototype.web.input.ResourceReq;
import com.example.restprototype.web.resources.Resource;

/**
 * REST APIにて基本的なPOST通信を行う方法
 * メソッドに@RequestBodyを使用する
 * なお、@RestControllerを使用すると、すべてのメソッドの戻り値が自動的に@ResponseBody状態になる
 */
@RestController
public class Type2RestController {
	
	@Autowired
	private ResourceService service;
	
	/**
	 * POSTリクエストされたリソースを登録する
	 * コントローラーに@RestControllerを付与していることで、
	 * メソッドに@ResponseBodyを付与する必要がなくなる
	 * が、引数をJavaBeansで受け取るには、@RequestBodyが必要になる
	 * @param param
	 * @return
	 */
	@PostMapping(value = "type2/create")
	public ResponseEntity<Void> post(@RequestBody ResourceReq req) {
		// リソース登録
		var resource = new Resource(req.getId(), req.getName(), req.getHogeDate());
		service.create(resource);
		
		// 作成したリソースにアクセスするためのURI
		// レスポンスヘッダーを設定する場合、ResponseEntityを使用する
		// createdメソッドを使用すると、引数に指定したURLがLocationヘッダーに設定される
		// build()メソッドを呼び出すと、レスポンスボディが空になる
		String resourceUri = "http://localhost:8080/rest_prototype/type1/" + resource.getId();
		return ResponseEntity.created(URI.create(resourceUri)).build();
	}
}
