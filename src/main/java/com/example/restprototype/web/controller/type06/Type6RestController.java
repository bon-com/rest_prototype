package com.example.restprototype.web.controller.type06;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.restprototype.biz.service.ResourceService;
import com.example.restprototype.web.input.ResourceReq;
import com.example.restprototype.web.resources.Resource;

/**
 * URIを組み立てる方法
 * UriComponentsBuilderを使用してURIを作成するケース
 */
@RestController
public class Type6RestController {
	
	@Autowired
	private ResourceService service;
	
	/**
	 * POSTリクエストされたリソースを登録する
	 * @param param
	 * @return
	 */
	@PostMapping(value = "type6/create")
	public ResponseEntity<Void> post(@RequestBody ResourceReq req, UriComponentsBuilder builder) {
		// リソース登録
		var resource = new Resource(req.getId(), req.getName(), req.getHogeDate());
		service.create(resource);
		
		// 「http://localhost:8080/rest_prototype/type1/XX」といったURIが生成される
		URI resourceUri = builder
				.path("/type1/{id}") // リソース取得のURIテンプレートつきパス 
				.buildAndExpand(resource.getId()) // URIテンプレートにバインドさせる値
				.encode() // URIエンコード（デフォルト：UTF-8）
				.toUri(); // URI作成
		
		return ResponseEntity.created(resourceUri).build();
	}
}
