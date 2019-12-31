package com.bawei.wangchu12312;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.Contract;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;

import java.sql.RowId;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.edit_aa)
    EditText editAa;
    @BindView(R.id.btn_aa)
    Button btnAa;
    @BindView(R.id.img_aa)
    ImageView imgAa;
    @BindView(R.id.btn_qq)
    Button btnQq;
    @BindView(R.id.btn_wx)
    Button btnWx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        CodeUtils.init(this);
        imgAa.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CodeUtils.analyzeByImageView(imgAa, new CodeUtils.AnalyzeCallback() {
                    @Override
                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                        Toast.makeText(Main2Activity.this, "成功"+result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnalyzeFailed() {
                        Toast.makeText(Main2Activity.this, "失败", Toast.LENGTH_SHORT).show();
                    }
                });
                return true;
            }
        });
    }

    @OnClick({R.id.btn_aa, R.id.btn_qq,R.id.btn_wx})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_aa:
                String s = editAa.getText().toString();
                Bitmap image = CodeUtils.createImage(s, 400, 400, null);
                imgAa.setImageBitmap(image);
                break;
            case R.id.btn_qq:
                EventBus.getDefault().post("qq");
                break;
            case R.id.btn_wx:
                EventBus.getDefault().post("wx");
                break;
        }
    }
}
