package com.abao.onmic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class RentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);

        Intent intent = getIntent();
        String itemName = intent.getStringExtra(ContentActivity.EXTRA_TEXT);

        TextView itemOrdered = findViewById(R.id.tvItemOrdered);

        itemOrdered.setText("  Item: " + itemName);
    }
}