package com.example.restprototype.common;

/**
 * 定数クラス
 */
public class Constant {
	/** 入力チェックエラータイトル */
	public static final String ERR_TITLE_VALIDATION = "入力エラー";
	/** リソースエラータイトル */
	public static final String ERR_TITLE_ABOUT_RESOURCE = "リソースエラー";
	/** 入力チェックエラーメッセージ */
	public static final String ERR_MSG_VALIDATION = "入力に誤りあり";
	/** リソースなしメッセージテンプレート */
	public static final String ERR_MSG_NOT_FOUND_TEMPLATE = "ID： %s のリソースがありませんでした。";
	/** エラーコードサンプル */
	public static final String ERR_CD_SAMPLE = "EXX0001";
	/** 処理続行不能エラータイトル */
	public static final String ERR_TITLE_INTERNAL_SERVER = "処理続行不能エラー";
	/** 処理続行不能エラーメッセージ */
	public static final String ERR_MSG_INTERNAL_SERVER = "予期せぬエラーが発生";
	/** リクエスト不正エラータイトル */
	public static final String ERR_TITLE_BAD_REQUEST = "リクエスト不正エラー";
	/** リクエスト不正エラーメッセージ */
	public static final String ERR_MSG_BAD_REQUEST = "リクエストが正しくない";
	/** エラーステータス取得用 */
	public static final String ERROR_STATUS_CODE = "javax.servlet.error.status_code";
	
}
