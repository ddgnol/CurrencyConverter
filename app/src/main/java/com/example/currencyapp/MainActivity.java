package com.example.currencyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String input = "0";
    float c1Val = 1, c2Val = 1; // lưu giá trị của đơn vị đầu vào và ra so với đồng VN
    String pattern = "###,###.###";
    DecimalFormat decimalFormat = new DecimalFormat(pattern);
    TextView txt1, txt2;
    Spinner spn1, spn2;
    String[] items = {"VND", "Dollar", "Euro", "British Pound", "Japanese Yen"};
    float[] val = {1, 23435, 25631, 29191, 216}; // gia tri cua cac don vi tien te so voi VND
    int[] idBtn = {R.id.btnCE, R.id.btnBs,
            R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.btn4, R.id.btn5, R.id.btn6,
            R.id.btn1, R.id.btn2, R.id.btn3,
            R.id.btn0, R.id.btnP
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("c1val init",""+c1Val);
        Log.d("c1val init",""+c1Val);
        setContentView(R.layout.activity_main);
        txt1 = findViewById(R.id.txtview1);
        txt2 = findViewById(R.id.txtview2);
        spn1 = findViewById(R.id.spn1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spn1.setAdapter(adapter);
        spn2 = findViewById(R.id.spn2);
        spn2.setAdapter(adapter);
        spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                c1Val = val[position]; //thay đổi giá trị của đầu vào dựa vào vị trí đơn vị tiền tương ứng trong mảng items và val.
                Log.d("c1val",""+c1Val);
                txt2.setText(""+decimalFormat.format(Float.parseFloat(input)*c1Val/c2Val));
                //txt2.setText(""+Float.parseFloat(input)*c1Val/c2Val); //tính toán giá trị đầu ra sau khi thay đổi đơn vị đầu vào
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spn2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                c2Val = val[position];
                //txt2.setText(""+Float.parseFloat(input)*c1Val/c2Val);
                txt2.setText(""+decimalFormat.format(Float.parseFloat(input)*c1Val/c2Val));
                Log.d("c2val",""+c2Val);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        for (int id : idBtn) {
            findViewById(id).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnCE:
                txt1.setText("0");
                txt2.setText("0");
                input = "0";
                break;
            case R.id.btnBs:
                if(input.length()==1||input.isEmpty()){
                    input = "0";
                    txt1.setText("0");
                    txt2.setText("0");
                }else {
                    input = input.substring(0,input.length()-1);
                    //txt1.setText(decimalFormat.format(Float.parseFloat(input)));
                    txt1.setText(input);
                    txt2.setText(decimalFormat.format(Float.parseFloat(input)*c1Val/c2Val));
                }
                break;
            case R.id.btnP:
                if(input.contains("."))
                    break;
                input+= ((Button) v).getText().toString();
                txt1.setText(input);
                txt2.setText(decimalFormat.format(Float.parseFloat(input)*c1Val/c2Val));
                break;
            default:
                if(input.equals("0"))
                    input = "";
                input+= ((Button) v).getText().toString();
                txt1.setText(decimalFormat.format(Float.parseFloat(input)));
                txt2.setText(decimalFormat.format(Float.parseFloat(input)*c1Val/c2Val));
                break;
        }
    }
}
