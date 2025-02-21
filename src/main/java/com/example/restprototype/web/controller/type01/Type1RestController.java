package com.example.restprototype.web.controller.type01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.restprototype.biz.service.ResourceService;
import com.example.restprototype.web.resources.Resource;

/**
 * REST APIにて基本的なGET通信を行う方法
 * メソッドに@ResponseBodyを使用する
 */
@Controller
public class Type1RestController {
	
	@Autowired
	private ResourceService service;
	
	/**
	 * パスパラメータに対応したリソースを返却する
	 * メソッドに@ResponseBodyを付与することで、HTTPレスポンスのbodyに直接書き込める
	 * Spring-webmvcに含まれるSpring-webに含まれるメッセージコンバーターのおかげで
	 * 戻り値のオブジェクトがJSON化される
	 * @param id
	 * @return
	 */
	@ResponseBody
	@GetMapping(value = "type1/{id}")
	public Resource get(@PathVariable String id) {
		// IDをキーにリソース取得　※本来はビジネスロジックの処理
		var res = service.find(id);
		
		return res;
	}
}
