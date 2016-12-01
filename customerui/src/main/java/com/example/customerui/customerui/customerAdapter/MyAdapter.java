package com.example.customerui.customerui.customerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.customerui.customerui.R;
import com.example.customerui.customerui.bean.ListItemBean;

import java.util.List;

/**
 * Created by MrDing on 2016/11/26.
 */

public class MyAdapter extends ArrayAdapter<ListItemBean> {

    private int sourceId;

    public MyAdapter(Context context, int resource, List<ListItemBean> objects) {
        super(context, resource, objects);
        sourceId = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItemBean item = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(sourceId, parent, false);
            viewHolder=new ViewHolder();
            viewHolder.setImage((ImageView) convertView.findViewById(R.id.imageName));
            viewHolder.setText((TextView) convertView.findViewById(R.id.textDes));
            convertView.setTag(viewHolder);
        }
        viewHolder=(ViewHolder)convertView.getTag();
        ImageView image = viewHolder.getImage();
        TextView text = viewHolder.getText();
        image.setImageResource(item.getImageId());
        text.setText(item.getText());
        return convertView;
    }


    @Override
    public long getItemId(int position) {
        ListItemBean item = getItem(position);
        long id = (item.getImageId() + item.getText()).hashCode() + 0L;
        return id;
    }


    private class ViewHolder {

        private ImageView image;

        private TextView text;

        public ImageView getImage() {
            return image;
        }

        public void setImage(ImageView image) {
            this.image = image;
        }

        public TextView getText() {
            return text;
        }

        public void setText(TextView text) {
            this.text = text;
        }
    }


}
