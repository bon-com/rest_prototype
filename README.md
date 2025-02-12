# rest_prototype
Spring MVCで作成する、RestAPIについてメモ

### ◆インデックス
 - type1：REST APIにて基本的なGET通信を行う方法
 - type2：REST APIにて基本的なPOST通信を行う方法
 - type3：REST APIにて基本的なPUT通信を行う方法
 - type4：REST APIにて基本的なDELETE通信を行う方法
 - type5：REST APIにて基本的なリソース検索を行う方法
 
### ◆補足
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