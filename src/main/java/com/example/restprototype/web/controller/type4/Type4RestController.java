package com.example.restprototype.web.controller.type4;

import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.restprototype.web.resources.Resource;

/**
 * REST APIにて基本的なDELETE通信を行う方法
 */
@RestController
public class Type4RestController {
	/** ロガー */
	private static final Logger logger = LoggerFactory.getLogger(Type4RestController.class);
	
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
	
	// ★★追加------------------------------------------------
	/**
	 * DELETEリクエストされたリソースを削除する
	 * DELETEやPUT（リソースの削除や更新）は、
	 * リクエストが成功したが返却するボディがないという意味のHTTPステータス204を返却する
	 * @param param
	 * @return
	 */
	@DeleteMapping(value = "type4/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void post(@PathVariable String id) {
		// リソース削除
		tmpDbMap.remove(id);
		
		for (var val : tmpDbMap.values()) {
		    logger.info("Resource: {}", val);
		}
	}
	// ★★追加------------------------------------------------
}
