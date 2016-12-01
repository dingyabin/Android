package com.example.mrding.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.mrding.myapplication.R.id.current;

/**
 * 拨号器
 */
public class MainActivity extends Activity {

    private EditText input;

    private Toast toast;

    private ProgressBar progress;

    private Handler handler;

    private TextView currentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        Button toCall=(Button)findViewById(R.id.bt_call);
        Button openBrower=(Button)findViewById(R.id.bt_brower);
        Button btProgress=(Button)findViewById(R.id.bt_progress);
        input=(EditText)findViewById(R.id.et_input);
        progress = (ProgressBar)findViewById(R.id.pbar);
        currentView=(TextView)findViewById(current);
        handler=new Handler();

        toCall.setOnClickListener(click);
        openBrower.setOnClickListener(click);
        btProgress.setOnClickListener(click);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    int current=progress.getProgress();
                    if(current==progress.getMax()){
                        break;
                    }
                    for(int i=0;i<100000000;i++){
                    }
                    updateProgress(current+1);
                }
            }
        }).start();

    }

    /**
     * 注册监听事件
     */
    private View.OnClickListener click=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.bt_call:
                    clickCallButton();
                    break;
                case R.id.bt_brower:
                    clickBrowerButton();
                    break;
                case R.id.bt_progress:
                    clickProgress();
                    break;
            }
        }
    };

    /**
     * 打电话按钮事件
     */
    private void clickCallButton(){
        final String number=input.getText().toString();
        if(number.trim().equals("")){
            alert(R.string.empty_alert);
            return;
        }else if(!number.matches("^[0-9]*$")){
            alert(R.string.not_number);
            return;
        }else{
            Intent callIntent=new Intent().setAction(Intent.ACTION_CALL).setData(Uri.parse("tel:"+number));
            startActivity(callIntent);
        }
    }

    /**
     * 浏览器按钮事件
     */
    private void clickBrowerButton(){
        Intent browerIntent=new Intent();
        browerIntent.setAction(Intent.ACTION_VIEW);
        browerIntent.setData(Uri.parse("http://www.baidu.com"));
        startActivity(browerIntent);
    }



    /**
     * 进度条开关
     */
    private void clickProgress(){
        if(progress.getVisibility()==View.VISIBLE){
            progress.setVisibility(View.GONE);
            currentView.setVisibility(View.GONE);
        }else{
            progress.setVisibility(View.VISIBLE);
            currentView.setVisibility(View.VISIBLE);
        }
    }


    /**
     * 更新进度条
     * @param percent
     */
    private void updateProgress(final int percent){
        handler.post(new Runnable() {
            @Override
            public void run() {
                progress.setProgress(percent);
                currentView.setText("当前进度:"+progress.getProgress()+"%");
                if(percent==progress.getMax()){
                    currentView.setTextColor(getResources().getColor(R.color.green));
                }
            }
        });
    }




    /**
     * 弹出窗口
     * @param messageId:消息id
     */
    private void alert(int messageId){
        if(toast==null){
            toast= Toast.makeText(MainActivity.this,messageId,Toast.LENGTH_SHORT);
            //居中显示
            toast.setGravity(Gravity.CENTER,0,0);
            //定义一个imageView显示图片
            ImageView imageView=new ImageView(MainActivity.this);
            imageView.setImageResource(R.mipmap.ic_launcher);
            //获得toast的布局
            LinearLayout layout = (LinearLayout)toast.getView();
            //设置布局方向
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setGravity(Gravity.CENTER);
            //把上面的imageView添加到布局中第一的位置
            layout.addView(imageView,0);
            toast.show();
        }else{
            toast.setText(messageId);
            toast.show();
        }
    }

    /**
     * 由可见变为不可见时触发
     */
    @Override
    protected void onStop() {
        super.onStop();
        if(toast!=null){
            toast.cancel();
        }
    }

}
