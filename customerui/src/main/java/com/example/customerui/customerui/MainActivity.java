package com.example.customerui.customerui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.customerui.customerui.bean.ListItemBean;
import com.example.customerui.customerui.customerAdapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    private List<ListItemBean> list;

    private Toast toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        init();
        ListView listView = (ListView) findViewById(R.id.listView);
        MyAdapter myAdapter = new MyAdapter(MainActivity.this, R.layout.list_item, list);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        alert(list.get(position).getText());
    }


    private void alert(String msg) {
        if (toast == null) {
            toast = Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (toast != null) {
            toast.cancel();
        }
    }


    private void init() {
        list = new ArrayList<>();
        list.add(new ListItemBean(R.drawable.android, "你好，安卓1。"));
        list.add(new ListItemBean(R.drawable.android, "你好，安卓2。"));
        list.add(new ListItemBean(R.drawable.android, "你好，安卓3。"));
        list.add(new ListItemBean(R.drawable.android, "你好，安卓4。"));
        list.add(new ListItemBean(R.drawable.android, "你好，安卓5。"));
        list.add(new ListItemBean(R.drawable.android, "你好，安卓6。"));
        list.add(new ListItemBean(R.drawable.android, "你好，安卓7。"));
        list.add(new ListItemBean(R.drawable.android, "你好，安卓8。"));
        list.add(new ListItemBean(R.drawable.android, "你好，安卓9。"));
        list.add(new ListItemBean(R.drawable.android, "你好，安卓10。"));
        list.add(new ListItemBean(R.drawable.android, "你好，安卓11。"));
    }


}
