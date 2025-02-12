package com.example.restprototype.web.controller.type2;

import java.net.URI;
import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restprototype.web.input.ResourceReq;
import com.example.restprototype.web.resources.Resource;

/**
 * REST APIにて基本的なPOST通信を行う方法
 * メソッドに@RequestBodyを使用する
 * なお、@RestControllerを使用すると、すべてのメソッドの戻り値が自動的に@ResponseBody状態になる
 */
@RestController
public class Type2RestController {
	
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
	 * リソース取得
	 * @param id
	 * @return
	 */
	@GetMapping(value = "type2/{id}")
	public Resource get(@PathVariable String id) {
		// IDをキーにリソース取得　※本来はビジネスロジックの処理
		var res = tmpDbMap.get(id);
		
		return res;
	}
	
	// ★★追加------------------------------------------------
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
		var elem = new Resource(req.getId(), req.getName(), req.getHogeDate());
		tmpDbMap.put(elem.getId(), elem);
		
		// 作成したリソースにアクセスするためのURI
		// レスポンスヘッダーを設定する場合、ResponseEntityを使用する
		// createdメソッドを使用すると、引数に指定したURLがLocationヘッダーに設定される
		// build()メソッドを呼び出すと、レスポンスボディが空になる
		String resourceUri = "http://localhost:8080/rest_prototype/type2/" + elem.getId();
		return ResponseEntity.created(URI.create(resourceUri)).build();
	}
	// ★★追加------------------------------------------------
}
