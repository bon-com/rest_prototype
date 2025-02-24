package com.example.restprototype.biz.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.restprototype.web.input.ResourceQuery;
import com.example.restprototype.web.input.ResourceQuery2;
import com.example.restprototype.web.resources.Resource;

@Service
public class ResourceService {

	/** DBの代わりに仮実装 */
	private static Map<String, Resource> tmpDbMap = new ConcurrentHashMap<>();
	
	/**
	 * 初期化（仮想DB）
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
	 * ID指定のリソースを参照
	 * @param id
	 * @return
	 */
	public Resource find(String id) {
		return tmpDbMap.get(id);
	}
	
	/**
	 * リソース登録
	 * @param resource
	 */
	public void create(Resource resource) {
		tmpDbMap.put(resource.getId(), resource);
	}
	
	/**
	 * リソース更新
	 * @param resource
	 */
	public void update(Resource resource) {
		tmpDbMap.put(resource.getId(), resource);
	}

	/**
	 * 全リソース参照
	 * @return
	 */
	public List<Resource> findAll() {
		return new ArrayList<>(tmpDbMap.values());
	}
	
	/**
	 * リソース削除
	 * @param id
	 */
	public void delete(String id) {
		tmpDbMap.remove(id);
	}
	
	/**
	 * リソースの条件検索
	 * @param param
	 * @return
	 */
	public List<Resource> findByParam(ResourceQuery param) {
		// 検索結果
		List<Resource> resList = findAll().stream().filter(r -> {
			boolean nameRes = param.getName() == null || r.getName().contains(param.getName());
			boolean hogeDateRes = param.getHogeDate() == null || r.getHogeDate().equals(param.getHogeDate());
			
			return nameRes && hogeDateRes;
		}).collect(Collectors.toList());
		
		return resList;
	}
	
	/**
	 * リソースの条件検索
	 * @param param
	 * @return
	 */
	public List<Resource> findByParam(ResourceQuery2 param) {
		// 検索結果
		List<Resource> resList = findAll().stream().filter(r -> {
			boolean nameRes = param.getName() == null || r.getName().contains(param.getName());
			boolean hogeDateRes = param.getHogeDate() == null || r.getHogeDate().equals(param.getHogeDate());
			
			return nameRes && hogeDateRes;
		}).collect(Collectors.toList());
		
		return resList;
	}
}
