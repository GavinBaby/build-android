package com.anuode.build_andriod;

import com.anuode.build_andriod.thrift.buildSvc;
import com.anuode.common.app.ApplicationBase;
import com.anuode.common.net.thrift.TAsyncHttpClientManager;
import java.util.HashMap;
import java.util.Map;
public class Build extends ApplicationBase {

    //begin 全局变量
    public static final String HOST = "http://101.201.150.144:3000/build";
    public static final String FILEHOST = "http://192.168.1.104/";
    public static final Map cache = new HashMap<>();
    //end 全局变量


    public  static buildSvc.AsyncClient getClient(){
        buildSvc.AsyncClient client = TAsyncHttpClientManager.getClient(buildSvc.AsyncClient.class);
        assert client != null;
        return client;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onInitAppParams(AppParams params) {
        super.onInitAppParams(params);
//        ShareManager.initShareManager(this,"1105111804","3369609695","wx788c110c0e5d7695");
        params.setVersionUpdateCheckUrl( FILEHOST+"release/appversion");
    }


    @Override
    protected void onInitComponents() {
        super.onInitComponents();
        TAsyncHttpClientManager.createClient(HOST,buildSvc.AsyncClient.Factory.class);
    }

    @Override
    protected void onInitUIComponents() {
        super.onInitUIComponents();
//        addMainTabPage(new MainActivity.TabPageInfo("首页", IndexFragment.class, "Index",R.drawable.app__boot_index_activity_selector));
//        addMainTabPage(new MainActivity.TabPageInfo("酒仓", WineFragment.class, "Wine",R.drawable.app__boot__wine_activity_selector));
//        addMainTabPage(new MainActivity.TabPageInfo("我的", MyFragment.class, "My",R.drawable.app__boot__my_activity_selector));
    }

//    public final void addMainTabPage(MainActivity.TabPageInfo pageInfo) {
//        MainActivity.addTabPage(pageInfo);
//    }

    public static boolean isLogin(){
        if(Build.cache.containsKey("userInfo")){
            return true;
        }
        return false;
    }
}
