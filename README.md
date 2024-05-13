# custom-conditional-authenticator

RH-SSO 7.6/Keycloak Legacy向けの `ConditionalAuthenticator` を実装したプラグイン (SPI)。
`matchCondition()` メソッド内でリクエストの検証や外部サイトの呼び出しなどの典型的な処理の例を実装している。

# 使い方

外部のIdentity Providerを設定済みで、その "Post Login Flow" に指定して使用する例を示す。

standalone.xmlでデバッグログをコンソールにも表示するようあらかじめ設定しておく。

```
        <subsystem xmlns="urn:jboss:domain:logging:8.0">
            <console-handler name="CONSOLE">
                <level name="ALL"/> <!-- ALLに変更 -->
                ...
            </console-handler>
            ...
            <!-- カテゴリを追加 -->
            <logger category="com.example">
                <level name="ALL"/>
            </logger>
            ...
        </subsystem>
```

プラグインをビルド・デプロイする。

```
mvn clean package wildfly:deploy
```

下記のような認証フローを作成する。
ここで "Condition - Custom Implementation" のみがこのプロジェクトで作成したプラグインになる。

![](images/authflow.png)

設定しているIdPの "Post Login Flow" に上記の認証フローを設定する。

試しにこのIdPを使ってログインしてみて、 `matchCondition()` メソッドの実行結果をログで確認する。

# 参考

- ユーザーの属性をチェックする `ConditionalAuthenticator` のデフォルト実装
  - [ConditionalUserAttributeValueFactory](https://github.com/keycloak/keycloak/blob/18.0.2/services/src/main/java/org/keycloak/authentication/authenticators/conditional/ConditionalUserAttributeValueFactory.java)
  - [ConditionalUserAttributeValue](https://github.com/keycloak/keycloak/blob/18.0.2/services/src/main/java/org/keycloak/authentication/authenticators/conditional/ConditionalUserAttributeValue.java)
