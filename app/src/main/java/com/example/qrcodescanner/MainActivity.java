package com.example.qrcodescanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MainActivity extends AppCompatActivity {
    Button scanButton,generateButton;
    EditText qrInput;
    ImageView qrcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanButton=findViewById(R.id.scanButton);
        generateButton=findViewById(R.id.generateButton);
        qrcode=findViewById(R.id.qrCode);
        qrInput=findViewById(R.id.qrInput);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data=qrInput.getText().toString();
                if(TextUtils.isEmpty(data)){
                    qrInput.setError("Value Required");
                }else{
                    QRGEncoder qrgEncoder=new QRGEncoder(data,null, QRGContents.Type.TEXT,400);
                    try {
                        Bitmap bitmap = qrgEncoder.getBitmap();
                        qrcode.setImageBitmap(bitmap);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ScanActivity.class));
            }
        });
    }
}