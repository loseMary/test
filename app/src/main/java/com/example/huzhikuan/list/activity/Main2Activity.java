package com.example.huzhikuan.list.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.huzhikuan.list.Adapter.PopListAdapter;
import com.example.huzhikuan.list.R;
import com.example.huzhikuan.list.Utils.CharacterParser;
import com.example.huzhikuan.list.Utils.EditTextView;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements EditTextView.RefreshList{
    private String[] nameList = { "大纱布", "小纱布", "小肤垫", "纱带","花生酱", "花生米", "纱子", "棉签", "橡皮筋", "缝针", "线团", "带线单头针", "带线双头针",
            "套管", "钢丝" };
    private EditTextView editText;
    private List<String> currentList;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editText = (EditTextView) findViewById(R.id.editText);
        editText.setList(nameList);
        editText.setListener(this);

    }
    private PopupWindow popupWindow;
    private ListView listView;
    private PopListAdapter adapter;
    private void initView() {
        if (view == null){
            view = LayoutInflater.from(this).inflate(R.layout.pop_layout,null);
            popupWindow = new PopupWindow(this);
            popupWindow.setContentView(view);
//            popupWindow.setOutsideTouchable(true);
            listView = (ListView) view.findViewById(R.id.listView);
            adapter = new PopListAdapter(this,currentList);
            listView.setAdapter(adapter);

        }
    }

    @Override
    public void callBack(List<String> list) {
        currentList = list;
        if (view == null || popupWindow == null || listView == null || adapter == null){
            initView();
        }
        if (!popupWindow.isShowing()){
            popupWindow.showAsDropDown(editText);
        }
        adapter.notifyDataSetChanged();
    }
}
