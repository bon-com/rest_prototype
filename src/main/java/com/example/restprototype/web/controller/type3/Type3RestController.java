package com.example.restprototype.web.controller.type3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.restprototype.biz.service.ResourceService;
import com.example.restprototype.web.controller.type4.Type4RestController;
import com.example.restprototype.web.input.ResourceReq;
import com.example.restprototype.web.resources.Resource;

/**
 * REST APIにて基本的なPUT通信を行う方法
 */
@RestController
public class Type3RestController {
	/** ロガー */
	private static final Logger logger = LoggerFactory.getLogger(Type4RestController.class);

	@Autowired
	private ResourceService service;
	
	/**
	 * PUTリクエストされたリソースを更新する
	 * DELETEやPUT（リソースの削除や更新）は、
	 * リクエストが成功したが返却するボディがないという意味のHTTPステータス204を返却する
	 * @param param
	 * @return
	 */
	@PutMapping(value = "type3/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void post(@PathVariable String id, @RequestBody ResourceReq req) {
		// リソース更新
		var resource = new Resource(req.getId(), req.getName(), req.getHogeDate());
		service.update(resource);
		
		for (var val : service.findAll()) {
		    logger.info("Resource: {}", val);
		}
	}
}
