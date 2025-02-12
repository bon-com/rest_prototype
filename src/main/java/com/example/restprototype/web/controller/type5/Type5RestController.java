package com.example.restprototype.web.controller.type5;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restprototype.web.input.ResourceQuery;
import com.example.restprototype.web.resources.Resource;

/**
 * REST APIにて基本的なリソース検索を行う方法
 */
@RestController
public class Type5RestController {
	
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
	 * @param id
	 * @return
	 */
	@ResponseBody
	@GetMapping(value = "type5/")
	public List<Resource> get(ResourceQuery queryParam) {
		// 検索結果
		List<Resource> resList = tmpDbMap.values().stream().filter(r -> {
			boolean nameRes = queryParam.getName() == null || r.getName().contains(queryParam.getName());
			boolean hogeDateRes = queryParam.getHogeDate() == null || r.getHogeDate().equals(queryParam.getHogeDate());
			
			return nameRes && hogeDateRes;
		}).collect(Collectors.toList());
		
		return resList;
	}
}
