下拉刷新

1. 加载此控件：

    <com.anuode.common.widget.pulltorefresh.RefreshableView
        android:id="@+id/refreshable_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">
        //ListView是要显示的数据
        <ListView
            android:id="@+id/list_view"
            android:layout_width="fill_parent"
            android:layout_height="fill_parent"
            android:scrollbars="none">

        </ListView>

    </com.anuode.common.widget.pulltorefresh.RefreshableView>

2. 初始化控件：

    refreshableView = (RefreshableView) findViewById(R.id.refreshable_view);
    listView = (ListView) findViewById(R.id.list_view);

3. 给listview设置数据

     String[] items = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L" };
     adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
     listView.setAdapter(adapter);

4. 下拉刷新的监听器，使用下拉刷新的地方应该注册此监听器来获取刷新回调。

      refreshableView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
                 @Override
                 public void onRefresh() {
                     try {
                         Thread.sleep(3000);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                     refreshableView.finishRefreshing();
                 }
             }, 0);