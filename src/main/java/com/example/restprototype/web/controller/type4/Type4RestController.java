package com.example.restprototype.web.controller.type4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.restprototype.biz.service.ResourceService;

/**
 * REST APIにて基本的なDELETE通信を行う方法
 */
@RestController
public class Type4RestController {
	/** ロガー */
	private static final Logger logger = LoggerFactory.getLogger(Type4RestController.class);
	
	@Autowired
	private ResourceService service;
	
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
		service.delete(id);
		
		for (var val : service.findAll()) {
		    logger.info("Resource: {}", val);
		}
	}
}
