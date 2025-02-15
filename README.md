# rest_prototype
Spring MVCで作成する、RestAPIについてメモ

### ◆インデックス
 - type1：REST APIにて基本的なGET通信を行う方法
 - type2：REST APIにて基本的なPOST通信を行う方法
 - type3：REST APIにて基本的なPUT通信を行う方法
 - type4：REST APIにて基本的なDELETE通信を行う方法
 - type5：REST APIにて基本的なリソース検索を行う方法
 - type6：URIを組み立てる方法（UriComponentsBuilderを使用してURIを作成するケース）
 - type7：REST APIにて基本的な入力チェックを行う方法
 - type8：REST APIにて入力チェックエラーを共通処理にてハンドリングする方法（カスタムエラーメッセージも利用）
 - type9：REST APIにてアプリ例外を共通処理にてハンドリングする方法
 - type10：REST APIにてweb.xmlで例外ハンドリングする方法
 - type11：REST APIにて基本的なXMLファイル情報を返却する方法
 
### ◆補足
リクエストを実行するクライアントはChromeなどで拡張機能の【Talend API Tester】などを使用するとよい  
参考：https://chromewebstore.google.com/detail/talend-api-tester-free-ed/aejoelaoggembcahagimdiliamlcdmfm?hl=ja&pli=1


## type1  
MessageConverterのおかげで、JSONからオブジェクト、オブジェクトからJSONへと変換してくれる  
MessageConverterの実装であるJackson2ObjectMapperFactoryBeanをDIコンテナに管理させることで  
JSON⇔オブジェクトの変換をやってくれる  
@RequestBodyを使用することで、レスポンスのボディを直接書き込めて、オブジェクトを返却することで  
Jackson2ObjectMapperFactoryBeanがJSONに変換してリクエスト元に返却される

## type2  
@RestControllerをコントローラーに付与することで、すべてのメソッドの戻り値が
@ResponseBodyを付与したときと同じ、つまりレスポンスボディ部を直接返却することになる  
@RequestBodyを引数のオブジェクトに設定することで、POSTリクエストボディ部がJavaBeansに変換されて受け取れる  

## type3  
@PutMappingを使用してPUTリクエスト（更新）を受け取り、  
リソースを更新する  
PUT（更新）やDELETE（削除）では、リクエストは成功したがボディはないという意味の  
204ステータスを返却する

## type5  
クエリパラメータを受け取り、リソース検索する  
GETリクエストのクエリパラメータをオブジェクトとして受け取る場合、  
引数にオブジェクトを指定するだけでSpringが自動的にクエリパラメータをオブジェクトのフィールドにマッピングしてくれる

## type7  
Hibernate Validatorライブラリを導入してBean Validationを行う
入力チェックエラー時、メソッドの@RequestBodyに対して@Validを使用している場合、  
MethodArgumentNotValidExceptionがスローされる  
コントローラー特有の例外として例外ハンドリングを行い、Bean Validationのデフォルトエラーメッセージを表示する  
レスポンスイメージは以下

{
    "status": 400, // レスポンスステータス
    "errorTitle": "入力エラー", // エラー概要タイトル
    "errorMsg": "入力に誤りあり", // エラー概要メッセージ
    "errorCode": "EXX0001", // エラーコードサンプル
    "errors": [ // 個別入力チェック（Bean Validation）デフォルトエラーメッセージ
        {
            "target": "name",
            "msg": "null は許可されていません"
        },
        {
            "target": "hogeDate",
            "msg": "null は許可されていません"
        }
    ]
}

## type8  
@ControllerAdviceを使用して共通例外クラスを作成する  
ResponseEntityExceptionHandlerを継承して必要なメソッドをオーバーライドすることで  
REST APIの例外ハンドリングを補助してもらう  
オーバーライド可能なさまざまな例外ハンドリングメソッドが用意されている  
また、メッセージソースを使用することで、プロパティからカスタムエラーメッセージをも取得している

## type10  
REST APIにてweb.xmlで例外ハンドリングする方法  
web.xmlにて<error-page>タグを利用して、例外発生時にハンドリング方法を記載できる  
&lt;location&gt;タグにリダイレクト先を指定する  
&lt;exception-type&gt;を指定して例外クラスを指定したり、&lt;error-code&gt;を指定してHTTPステータスも指定可能

## type11  
REST APIにてXML情報をバイナリで返却する  
ResponseEntitiyのヘッダーに[Content-Disposition]を指定することで、  
取得先のダウンロードファイル名等に使用可能  
GETで返却するため、日本語等を指定すると文字化けてしまうため、URLエンコードして設定する  
使用する側はURLでコードしてファイル名を取得する