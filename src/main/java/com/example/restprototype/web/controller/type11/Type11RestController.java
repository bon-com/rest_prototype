package com.example.restprototype.web.controller.type11;

import static com.example.restprototype.common.Constant.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST APIにて基本的なXMLファイル情報を返却する方法
 */
@RestController
public class Type11RestController {
    @Autowired
    private ResourceLoader resourceLoader;
    
	/**
	 * XML情報を返却する
	 * @throws IOException 
	 */
	@ResponseBody
	@GetMapping(value = "type11/xml")
	public ResponseEntity<byte[]> get() throws IOException {
		// クラスパスに配置したXML情報取得
		var xmlFile = resourceLoader.getResource("classpath:xml/personal-sample.xml");
		byte[] content = FileCopyUtils.copyToByteArray(xmlFile.getInputStream());

		// ファイル名
		String encodedFileName = URLEncoder.encode("登録情報.xml", StandardCharsets.UTF_8.toString());
		
        // Content-Dispositionヘッダーにエンコードしたファイル名を設定
		// コンテンツタイプにXMLを指定
        var headers = new HttpHeaders();
        headers.add(CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFileName + "\"");
        headers.add(CONTENT_TYPE, CONTENT_TYPE_XML);
        
        return ResponseEntity.ok()
                .headers(headers)
                .body(content);
	}
}
