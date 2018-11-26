package com.example.huzhikuan.list.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.huzhikuan.list.R;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>{
    private List<String> list;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    public MyRecyclerViewAdapter(List<String> list) {
        this.list = list;
    }
    public void  setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public void  setOnLongItemClickListener(OnItemLongClickListener onItemLongClickListener){
        this.onItemLongClickListener = onItemLongClickListener;
    }
    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_item,parent,false);
        MyRecyclerViewAdapter.ViewHolder viewHolder = new MyRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.name.setText(list.get(position));
        holder.name.setOnClickListener(new MyOnClickListener(position,list.get(position)));
        holder.name.setOnLongClickListener(new MyOnLongClickListener(position,list.get(position)));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
        }
    }
    //增加item
    public void addItem(){
        int data = list.size();
        list.add(data+"add");
        notifyDataSetChanged();
    }
    //删除item
    public void deleteItem(){
        list.remove(list.size()-1);
        notifyDataSetChanged();
    }
    //点击事件
    class MyOnClickListener implements View.OnClickListener{
        private int position;
        private String data;
        public MyOnClickListener(int position, String data) {
            this.position = position;
            this.data = data;
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v,position,data);
        }
    }
    //长按事件
    class MyOnLongClickListener implements View.OnLongClickListener{
        private int position;
        private String data;
        public MyOnLongClickListener(int position, String data) {
            this.position = position;
            this.data = data;
        }

        @Override
        public boolean onLongClick(View v) {
            onItemLongClickListener.onItemLongClick(v,position,data);
            return true;
        }
    }
    public interface OnItemClickListener{
        void onItemClick(View view,int positon,String data);
    }
    public interface OnItemLongClickListener{
        void onItemLongClick(View view,int positon,String data);
    }

}
