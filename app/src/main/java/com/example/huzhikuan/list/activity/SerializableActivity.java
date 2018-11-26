package com.example.huzhikuan.list.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.huzhikuan.list.R;
import com.example.huzhikuan.list.bean.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SerializableActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView user;
    private Button serial,unserial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serializable);
        user = findViewById(R.id.user);
        serial = findViewById(R.id.serial);
        unserial = findViewById(R.id.unserial);
        serial.setOnClickListener(this);
        unserial.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.serial:
                try{
                    User user = new User(10,"huzhikuan",true);
                    File file = new File("cache.txt");
                    if (!file.exists()){
                        file.createNewFile();
                    }
                    ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("cache.txt"));
                    outputStream.writeObject(user);
                    outputStream.close();

                }catch (Exception e){
                    e.printStackTrace();
                }

                break;
            case R.id.unserial:

                break;
        }
    }
}
