package com.example.coinflip;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button btn_fej, btn_iras;
    private ImageView img_coin;
    private TextView txt_dobasok, txt_gyozelem, txt_vereseg;
    private int dobasok, gyozelem, vereseg;
    private Random random;
    private Toast customToast;
    private AlertDialog.Builder alertBuilder;
    private TextView textViewCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        ujJatek();
        btn_fej.setOnClickListener(view -> {
            boolean fejVagyIras;
            fejVagyIras = random.nextBoolean();
            //ha hamis akkor iras ha igaz akkor fej
            if(fejVagyIras){
                dobasok++;
                gyozelem++;
                img_coin.setImageResource(R.drawable.heads);
            } else {
                dobasok++;
                vereseg++;
                img_coin.setImageResource(R.drawable.tails);
            }
            txt_dobasok.setText("Dobasok: "+dobasok);
            txt_gyozelem.setText("Győzelem: "+gyozelem);
            txt_vereseg.setText("Vereseg: "+vereseg);
            if(dobasok == 5){
                if(gyozelem>vereseg){
                    alertBuilder.setTitle("Győzelem!");
                    alertBuilder.create();
                    alertBuilder.show();
                } else {
                    alertBuilder.setTitle("Vereség").create().show();
                }
            }
        });
        btn_iras.setOnClickListener(view -> {
            boolean fejVagyIras;
            fejVagyIras = random.nextBoolean();
            //ha hamis akkor iras ha igaz akkor fej
            if(!fejVagyIras){
                dobasok++;
                gyozelem++;
                img_coin.setImageResource(R.drawable.tails);
            } else {
                dobasok++;
                vereseg++;
                img_coin.setImageResource(R.drawable.heads);
            }
            txt_dobasok.setText("Dobasok: "+dobasok);
            txt_gyozelem.setText("Győzelem: "+gyozelem);
            txt_vereseg.setText("Vereseg: "+vereseg);
            if(dobasok == 5){
                if(gyozelem>vereseg){
                    alertBuilder.setTitle("Győzelem!");
                    alertBuilder.create();
                    alertBuilder.show();
                } else {
                    alertBuilder.setTitle("Vereség").create().show();
                }
            }
        });
    }

    private void ujJatek() {
        dobasok = 0;
        gyozelem = 0;
        vereseg = 0;
    }

    private void init(){
        btn_fej = findViewById(R.id.fej);
        btn_iras = findViewById(R.id.iras);
        img_coin = findViewById(R.id.coin);
        txt_dobasok = findViewById(R.id.dobasok);
        txt_gyozelem = findViewById(R.id.gyozelem);
        txt_vereseg = findViewById(R.id.vereseg);
        dobasok = 0;
        gyozelem = 0;
        vereseg = 0;
        random = new Random();
        customToast =new Toast(getApplicationContext());
        CreateCustomToast();
        alertBuilder = new AlertDialog.Builder(this);
        CreateAlertDialog();
    }
    private void CreateAlertDialog(){
        alertBuilder.setMessage("Szeretnél e új játékot?");
        alertBuilder.setNegativeButton("Iem", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {finish();}
        });
        alertBuilder.setPositiveButton("Igen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ujJatek();
                closeContextMenu();
            }
        });
        //kötelező választani
        alertBuilder.setCancelable(false);
    }

    private void CreateCustomToast(){
        customToast.setDuration(Toast.LENGTH_SHORT);
        View view =getLayoutInflater().inflate(R.layout.custom_toast, findViewById(R.id.custom_toast));
        textViewCustom =view.findViewById(R.id.textViewCustom);
        customToast.setView(view);
        customToast.setGravity(Gravity.CENTER, 0, 0);
    }
}