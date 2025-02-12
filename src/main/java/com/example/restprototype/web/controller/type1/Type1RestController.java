package com.example.restprototype.web.controller.type1;

import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.restprototype.web.resources.Resource;

/**
 * REST APIにて基本的なGET通信を行う方法
 * メソッドに@ResponseBodyを使用する
 */
@Controller
public class Type1RestController {
	
	/** DBの代わりに仮実装　※本来はビジネスロジック */
	private static Map<String, Resource> tmpDbMap = new ConcurrentHashMap<>();
	
	/**
	 * 初期化（仮実装）　※本来はビジネスロジック
	 */
	static {
		var dto1 = new Resource("1", "りんご", LocalDate.of(2025, 2, 1));
		var dto2 = new Resource("2", "ごりら", LocalDate.of(2024, 6, 5));
		var dto3 = new Resource("3", "らっぱ", LocalDate.of(2023, 5, 10));
		
		// 初期化
		tmpDbMap.put(dto1.getId(), dto1);
		tmpDbMap.put(dto2.getId(), dto2);
		tmpDbMap.put(dto3.getId(), dto3);
	}
	
	/**
	 * パスパラメータに対応したリソースを返却する
	 * メソッドに@ResponseBodyを付与することで、HTTPレスポンスのbodyに直接書き込める
	 * MappingJackson2HttpMessageConverterをDIコンテナに管理させているので
	 * 戻り値のオブジェクトがJSON化される
	 * @param id
	 * @return
	 */
	@ResponseBody
	@GetMapping(value = "type1/{id}")
	public Resource get(@PathVariable String id) {
		// IDをキーにリソース取得　※本来はビジネスロジックの処理
		var res = tmpDbMap.get(id);
		
		return res;
	}
}
