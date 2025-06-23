package com.example.restprototype.web.controller.type13;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restprototype.biz.service.ResourceService;
import com.example.restprototype.web.resources.Resource;

/**
 * REST APIにて疑似的にHTTPエラー以外を発生させる
 */
@RestController
public class Type13RestController {
	
	@Autowired
	private ResourceService service;
	
	/**
	 * タイムアウトを発生させる
	 * @throws InterruptedException 
	 */
	@GetMapping(value = "type13/timeout")
	public Resource get() throws InterruptedException {
		// IDをキーにリソース取得　※本来はビジネスロジックの処理
		var res = service.find("1");
		Thread.sleep(15000); // 15秒待つ
		return res;
	}
}
