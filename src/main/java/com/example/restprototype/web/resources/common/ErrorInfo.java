package com.example.restprototype.web.resources.common;

import lombok.Data;

/**
 * エラー情報クラス
 */
@Data
public class ErrorInfo {
	/** レスポンスステータス */
	private int status;
	/** エラー概要タイトル */
	private String errorTitle;
	/** エラー概要メッセージ */
	private String errorMsg;
	/** エラーコード（特に用意していないので、あくまでサンプル） */
	private String errorCode;
}
