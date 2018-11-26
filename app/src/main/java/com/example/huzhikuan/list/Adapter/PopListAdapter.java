package com.example.huzhikuan.list.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.huzhikuan.list.R;

import java.util.List;

/**
 * Created by huzhikuan on 2018/5/14.
 */

public class PopListAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    public PopListAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null){
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.iten,null);
            holder.text = (TextView) view.findViewById(R.id.text);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.text.setText(list.get(i));
        return view;
    }
    class ViewHolder{
        TextView text;
    }
}
