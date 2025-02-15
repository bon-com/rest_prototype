package com.example.restprototype.web.controller.type10;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST APIにてweb.xmlで例外ハンドリングする方法
 */
@RestController
public class Type10RestController {
	
	/**
	 * 適当に例外をスロー
	 * @param id
	 * @return
	 * @throws IOException 
	 */
	@GetMapping(value = "type10/error")
	public void error2() throws IOException {
		throw new IOException();
	}
}
