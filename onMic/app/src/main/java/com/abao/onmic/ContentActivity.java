package com.abao.onmic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;

public class ContentActivity extends AppCompatActivity {

    private TextView userinfo;
    private Button firstRent, secondRent, thirdRent, fourthRent;
    private Dialog dialog;
    private CardView larger1, larger2, larger3, larger4;

    public static final String EXTRA_TEXT = "com.abao.onmic.EXTRA_TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        userinfo = findViewById(R.id.tvUser);
        firstRent = findViewById(R.id.btnFirstRent);
        secondRent = findViewById(R.id.btnSecondRent);
        thirdRent = findViewById(R.id.btnThirdRent);
        fourthRent = findViewById(R.id.btnFourthRent);
        larger1 = findViewById(R.id.cvLarger1);
        larger2 = findViewById(R.id.cvLarger2);
        larger3 = findViewById(R.id.cvLarger3);
        larger4 = findViewById(R.id.cvLarger4);
        dialog = new Dialog(this);

        larger1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.popupfirst);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        firstRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Passing the Item name in the next Window
                TextView itemName = findViewById(R.id.tvItemName1);
                String ITEMNAME = itemName.getText().toString();
                Intent intent = new Intent(ContentActivity.this, RentActivity.class);
                intent.putExtra(EXTRA_TEXT, ITEMNAME);
                startActivity(intent);
            }
        });

        larger2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.popupsecond);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        secondRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView itemName = findViewById(R.id.tvItemName2);
                String ITEMNAME = itemName.getText().toString();
                Intent intent = new Intent(ContentActivity.this, RentActivity.class);
                intent.putExtra(EXTRA_TEXT, ITEMNAME);
                startActivity(intent);
            }
        });

        larger3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.popupthird);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        thirdRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView itemName = findViewById(R.id.tvItemName3);
                String ITEMNAME = itemName.getText().toString();
                Intent intent = new Intent(ContentActivity.this, RentActivity.class);
                intent.putExtra(EXTRA_TEXT, ITEMNAME);
                startActivity(intent);
            }
        });

        larger4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.popupfourth);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        fourthRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView itemName = findViewById(R.id.tvItemName4);
                String ITEMNAME = itemName.getText().toString();
                Intent intent = new Intent(ContentActivity.this, RentActivity.class);
                intent.putExtra(EXTRA_TEXT, ITEMNAME);
                startActivity(intent);
            }
        });

        userinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContentActivity.this, UserInfo.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

    }

}