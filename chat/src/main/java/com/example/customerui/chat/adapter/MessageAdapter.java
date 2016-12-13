package com.example.customerui.chat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.customerui.chat.R;
import com.example.customerui.chat.bean.Msg;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by MrDing on 2016/11/27.
 */

public class MessageAdapter extends ArrayAdapter<Msg> {

    private int resourceId;

    private  LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日 HH:mm");


    public MessageAdapter(Context context, int resource, List<Msg> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Msg msg = getItem(position);
        View itemView;
        ViewHolder viewHolder;
        if (convertView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.setLeftLayout((LinearLayout) itemView.findViewById(R.id.left_layout));
            viewHolder.setRightLayout((LinearLayout) itemView.findViewById(R.id.right_layout));
            viewHolder.setTime((TextView) itemView.findViewById(R.id.time));
            viewHolder.setLeftMsg((TextView) viewHolder.getLeftLayout().findViewById(R.id.left_msg_content));
            viewHolder.setRightMsg((TextView) viewHolder.getRightLayout().findViewById(R.id.right_msg_content));
            itemView.setTag(viewHolder);
        } else {
            itemView = convertView;
            viewHolder = (ViewHolder) itemView.getTag();
            viewHolder.getLeftMsg().setLayoutParams(params);
            viewHolder.getRightMsg().setLayoutParams(params);
        }
//        若是接受的消息，则在左面的LinearLayout设置消息，右面的LinearLayout隐藏
        if (msg.getType() == Msg.RECIVE) {
            viewHolder.getLeftMsg().setText(msg.getContent());
            viewHolder.getLeftLayout().setVisibility(View.VISIBLE);
            viewHolder.getRightLayout().setVisibility(View.GONE);
        }
//        若是发送的消息，则在右面的LinearLayout设置消息，左面的LinearLayout隐藏
        else if (msg.getType() == Msg.SEND) {
            viewHolder.getRightMsg().setText(msg.getContent());
            viewHolder.getLeftLayout().setVisibility(View.GONE);
            viewHolder.getRightLayout().setVisibility(View.VISIBLE);
        }
//        判断消息之间的时间间隔，若是间隔小于一分钟则不显示时间
        if (position == 0 ||
                getItem(position).getTime().getTime() - getItem(position - 1).getTime().getTime() > 60 * 1000) {
            viewHolder.getTime().setText(simpleDateFormat.format(msg.getTime()));
            viewHolder.getTime().setVisibility(View.VISIBLE);
        } else {
            viewHolder.getTime().setVisibility(View.GONE);
        }
        return itemView;
    }


    private class ViewHolder {

        private LinearLayout leftLayout;

        private LinearLayout rightLayout;

        private TextView leftMsg;

        private TextView rightMsg;

        private TextView time;

        public LinearLayout getLeftLayout() {
            return leftLayout;
        }

        public void setLeftLayout(LinearLayout leftLayout) {
            this.leftLayout = leftLayout;
        }

        public LinearLayout getRightLayout() {
            return rightLayout;
        }

        public void setRightLayout(LinearLayout rightLayout) {
            this.rightLayout = rightLayout;
        }

        public TextView getLeftMsg() {
            return leftMsg;
        }

        public void setLeftMsg(TextView leftMsg) {
            this.leftMsg = leftMsg;
        }

        public TextView getRightMsg() {
            return rightMsg;
        }

        public void setRightMsg(TextView rightMsg) {
            this.rightMsg = rightMsg;
        }

        public TextView getTime() {
            return time;
        }

        public void setTime(TextView time) {
            this.time = time;
        }
    }


}
