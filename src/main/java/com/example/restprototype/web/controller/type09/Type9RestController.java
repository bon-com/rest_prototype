package com.example.restprototype.web.controller.type09;

import static com.example.restprototype.common.Constant.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.restprototype.biz.service.ResourceService;
import com.example.restprototype.web.resources.Resource;

/**
 * REST APIにてアプリ例外を共通処理にてハンドリングする方法
 */
@RestController
public class Type9RestController {
	
	@Autowired
	private ResourceService service;
	
	/**
	 * パスパラメータに対応したリソースを返却する
	 * @param id
	 * @return
	 */
	@GetMapping(value = "type9/{id}")
	public Resource get(@PathVariable String id) {
		// IDをキーにリソース取得　※本来はビジネスロジックの処理
		var res = service.find(id);
		if (res == null) {
			// リソースがない場合、例外をスローする
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(ERR_MSG_NOT_FOUND_TEMPLATE, id));
		}
		
		return res;
	}
}
