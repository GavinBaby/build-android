本项目主要包含通信和其它基础、共通功能模块 aaa

1. 初始化app
    确保在androidmainifest.xml中将application的name指定为com.anuode.common.app.ApplicationBase，或指向从该类集成的其他类
        
        <application
            android:allowBackup="true"
            android:name="com.anuode.common.app.ApplicationBase"
            android:icon="@mipmap/ic_launcher"
            android:label="@string/app_name"
            android:theme="@style/AppTheme" >

2. 添加thrift客户端服务，并通过异步方式进行RPC方法调用

        HelloSvc.AsyncClient client = TAsyncHttpClientManager.createClient("http://192.168.2.102:3000/hello"
                , HelloSvc.AsyncClient.Factory.class, HelloSvc.AsyncClient.class);
    
        client.hello_func(new User("123", "李四", 20), new TAsyncMethodResult<Result>() {
            @Override
            public void onResult(Exception e, Result result) {
    
            }
        });
    
3. 添加自签名证书

        SelfSignSslOkHttpStack.addCert(R.raw.kyfw_12306_cn, "123456", "kyfw.12306.cn");
        SelfSignSslOkHttpStack.addCert(R.raw.dev_www_wetrip_com, "123456", "dev.www.wetrip.com");
        
4. 使用普通的http json调用

        public class ExampleApplication extends ApplicationBase {
        
            @Override
            public void onCreate() {
                super.onCreate();
                ObjectMapper mapper = new ObjectMapper();
                try {
                    JsonNode data= mapper.readTree("{\"name\": \"张三\", \"age\":30}");
                    JsonHttpClient client = new JsonHttpClient("http://api.example.com");
                    client.post("users", data, new JsonHttpClient.OnCompletedCallback() {
                        @Override
                        public void onCompleted(boolean success, JsonNode objectData, String json) {
                            String name = objectData.asText();
                            int age = objectData.asInt();
                        }
                    });
                    User user = new User("李四", 22);
                    client.post("users", user, new JsonHttpClient.OnCompletedCallback() {
                        @Override
                        public void onCompleted(boolean success, JsonNode objectData, String json) {
                            String name = objectData.asText();
                            int age = objectData.asInt();
                        }
                    });
                    String json = mapper.writeValueAsString(user);
                    client.post("users", json, new JsonHttpClient.OnCompletedCallback() {
                        @Override
                        public void onCompleted(boolean success, JsonNode objectData, String json) {
                            String name = objectData.asText();
                            int age = objectData.asInt();
                        }
                    });
                   
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        
            public static class User {
                public String name;
                public int age;
        
                public User(String name, int age) {
                    this.name = name;
                    this.age = age;
                }
            }
        }