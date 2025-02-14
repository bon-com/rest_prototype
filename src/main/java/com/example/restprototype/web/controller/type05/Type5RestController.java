package com.example.restprototype.web.controller.type05;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restprototype.biz.service.ResourceService;
import com.example.restprototype.web.input.ResourceQuery;
import com.example.restprototype.web.resources.Resource;

/**
 * REST APIにて基本的なリソース検索を行う方法
 */
@RestController
public class Type5RestController {
	
	@Autowired
	private ResourceService service;
	
	/**
	 * @param id
	 * @return
	 */
	@ResponseBody
	@GetMapping(value = "type5/")
	public List<Resource> get(ResourceQuery queryParam) {
		// 検索結果
		List<Resource> resList = service.findByParam(queryParam);
		
		return resList;
	}
}
