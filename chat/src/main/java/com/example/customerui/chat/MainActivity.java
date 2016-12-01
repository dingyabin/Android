package com.example.customerui.chat;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.customerui.chat.adapter.MessageAdapter;
import com.example.customerui.chat.bean.Msg;
import com.example.customerui.chat.utils.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity implements View.OnClickListener {

    private List<Msg> list;

    private Button send;

    private EditText input;

    private ListView listView;

    private MessageAdapter messageAdapter;

    private Handler handler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        messageAdapter = new MessageAdapter(MainActivity.this, R.layout.msg_item, list);
        listView = (ListView) findViewById(R.id.chat_list_view);
        send = (Button) findViewById(R.id.send_button);
        handler=new Handler();
        input = (EditText) findViewById(R.id.edit_view);
        listView.setAdapter(messageAdapter);
        send.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_button:
                String msg = send();
                if(!"".equals(msg)){
                    delayRecieve(msg);
                }
                break;
        }
    }




    private String send() {
        String conent = input.getText().toString();
        if (!"".equals(conent)) {
            messageAdapter.add(new Msg(conent, Msg.SEND,new Date()));
            listView.setSelection(messageAdapter.getCount());
            input.setText("");
        }
        return conent;
    }




    private void delayRecieve(final String msg) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        get(msg);
                    }
                });
            }
        }, 1000);
    }



    private void get(String msg) {
        messageAdapter.add(new Msg(StringUtils.reverse(msg), Msg.RECIVE,new Date()));
        listView.setSelection(messageAdapter.getCount());
    }





    private void init() {
        list = new ArrayList<>();
        list.add(new Msg("hello!", Msg.RECIVE ,new Date()));
        list.add(new Msg("你是谁", Msg.SEND ,new Date()));
        list.add(new Msg("我是你大爷啊", Msg.RECIVE ,new Date()));
        list.add(new Msg("滚蛋", Msg.SEND ,new Date()));
        list.add(new Msg("你不信吗吃葡萄不吐葡萄皮,吃葡萄不吐葡萄皮,吃葡萄不吐葡萄皮,吃葡萄不吐葡萄皮,吃葡萄不吐葡萄皮", Msg.RECIVE ,new Date()));
        list.add(new Msg("不信", Msg.SEND ,new Date()));
        list.add(new Msg("吃葡萄不吐葡萄皮，不吃葡萄倒吐葡萄皮", Msg.RECIVE ,new Date()));
        list.add(new Msg("张殿洋是傻逼", Msg.SEND,new Date()));
        list.add(new Msg("对啊", Msg.RECIVE,new Date()));
        list.add(new Msg("哈哈哈哈", Msg.SEND,new Date()));
        list.add(new Msg("哈哈哈哈哈", Msg.RECIVE,new Date()));
    }
}
