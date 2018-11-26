package com.example.huzhikuan.list.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huzhikuan on 2018/5/11.
 */

@SuppressLint("AppCompatCustomView")
public class EditTextView extends EditText {

    private String[] nameList;
    private List<String> popList = new ArrayList<>();
    private List<String[]> list = new ArrayList<>();
    private CharacterParser characterParser;
    private String tag = "EditTextView";
    public EditTextView(Context context) {
        super(context);
        init();
    }

    public EditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        characterParser = CharacterParser.getInstance();
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        if (text == null || text.toString().trim().length() == 0) {
            return;
        }
        popList.clear();
//        spellingSearch(text.toString());
        if (text.toString().trim().substring(0,1).getBytes().length >= 2){
            //汉字
            chineseSearch(text.toString());
        }else {
            //拼音
            if (!spellingAllSearch(text.toString().trim())){
                //如果全拼查找不到就按首字母查找
                spellingSearch(text.toString().trim());
            }
        }
        refreshList.callBack(popList);
    }
    //全拼查找
    private boolean spellingAllSearch(String str){
        str = str.toLowerCase();
        for (int i=0;i<list.size();i++){
            if (list.get(i)[0].startsWith(str)){
                popList.add(nameList[i]);
            }
        }
        Log.e(tag,"popList==全拼"+popList.toString());
        if (popList.size() != 0){
            return true;
        }
        return false;
    }
    //拼音简写首字母查找
    private void spellingSearch(String str){
        str = str.toLowerCase();
        String[] value = characterParser.getSelling(str.toString());
        for (int i=0;i<list.size();i++){
            if (value.length > list.get(i).length) {
                continue;
            }
            boolean isConten = true;
            for (int j=0;j<value.length;j++){
                if (!list.get(i)[j].startsWith(value[j])){
                    isConten = false;
                }
            }
            if (isConten){
                popList.add(nameList[i]);
            }
            isConten = true;
        }
        Log.e(tag,"popList==首字母"+popList.toString());

    }
    //中文查找
    private void chineseSearch(String str){
        for (int i=0;i<nameList.length;i++){
            if (nameList[i].startsWith(str)){
                popList.add(nameList[i]);
            }
        }
        Log.e(tag,"popList==中文"+popList.toString());
    }
    public void setList(String[] nameList){
        this.nameList = nameList;
        getspelling();
    }
    //汉字转为拼音
    private void getspelling() {
        if (nameList == null || nameList.length == 0)
            return;
        for (int i = 0; i < nameList.length; i++) {
            String[] value = characterParser.getSelling(nameList[i]);
            list.add(value);
        }
    }
    private RefreshList refreshList;
    public void setListener(RefreshList refreshList){
        this.refreshList = refreshList;
    }
    public interface RefreshList{
        void callBack(List<String> list);
    }
}
