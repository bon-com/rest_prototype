package com.example.restprototype.web.controller.type12;

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
 * REST APIにて基本的なPDFファイル情報を返却する方法
 */
@RestController
public class Type12RestController {
	@Autowired
	private ResourceLoader resourceLoader;

	/**
	 * PDF情報を返却する
	 * @throws IOException 
	 */
	@ResponseBody
	@GetMapping(value = "type12/pdf")
	public ResponseEntity<byte[]> get() throws IOException {
		// クラスパスに配置したPDF情報取得
		var pdfFile = resourceLoader.getResource("classpath:pdf/sample.pdf");
		byte[] content = FileCopyUtils.copyToByteArray(pdfFile.getInputStream());

		// ファイル名
		String encodedFileName = URLEncoder.encode("PDFサンプル", StandardCharsets.UTF_8.toString());

		// Content-Dispositionヘッダーにエンコードしたファイル名を設定
		// コンテンツタイプにPDFを指定
		var headers = new HttpHeaders();
		headers.add(CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFileName + "\"");
		headers.add(CONTENT_TYPE, CONTENT_TYPE_PDF);

		return ResponseEntity.ok()
				.headers(headers)
				.body(content);
	}
}
