package com.example.nicolasdubus.lab1_3811637;

import android.app.Activity;
import android.content.DialogInterface;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.*;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;

import java.util.ArrayList;


public class MainActivity extends Activity implements OnClickListener, OnFocusChangeListener {

    private TableLayout mainTable;
    private ImageButton addDinerButton, addButton1, roundButton, fortuneButton;
    private EditText firstCustomer, amount1of1;
    private TextView textSplit1, textSplit1Dollar;
    private ArrayList<Diner> dinerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainTable = (TableLayout)findViewById(R.id.mainTable);

        addDinerButton = (ImageButton)findViewById(R.id.addDinerButton);
//        addButton1 = (ImageButton)findViewById(R.id.addButton1);
//        roundButton = (ImageButton)findViewById(R.id.roundButton);
//        fortuneButton = (ImageButton)findViewById(R.id.fortuneButton);

        firstCustomer = (EditText)findViewById(R.id.firstCustomer);
        amount1of1 = (EditText)findViewById(R.id.amount1of1);
        addButton1 = (ImageButton)findViewById(R.id.addButton1);
        textSplit1 = (TextView)findViewById(R.id.textSplit1);
        textSplit1Dollar = (TextView)findViewById(R.id.textSplit1Dollar);

        addDinerButton.setOnClickListener(this);
        addButton1.setOnClickListener(this);
        firstCustomer.setOnFocusChangeListener(this);
//        addButton1.setOnClickListener(this);
//        roundButton.setOnClickListener(this);
//        fortuneButton.setOnClickListener(this);

        dinerList = new ArrayList<>();
        Diner firstDiner = new Diner(firstCustomer, amount1of1, addButton1, textSplit1, textSplit1Dollar);
        dinerList.add(firstDiner);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        if (v == addDinerButton) {
            TableRow row1 = new TableRow(this);
            EditText et1 = new EditText(this);
            et1.setOnFocusChangeListener(this);
            et1.setText("Customer");
            et1.setSelectAllOnFocus(true);
            et1.setInputType(firstCustomer.getInputType());
            et1.setGravity(firstCustomer.getGravity());
            et1.setLayoutParams(firstCustomer.getLayoutParams());
            et1.setWidth(firstCustomer.getWidth());

            EditText et2 = new EditText(this);
            et2.setText("$0.00");
            et2.setSelectAllOnFocus(true);
            et2.setInputType(amount1of1.getInputType());
            et2.setGravity(amount1of1.getGravity());
            et2.setLayoutParams(amount1of1.getLayoutParams());
            et2.setWidth(amount1of1.getWidth());
            et2.requestFocus();

            ImageButton ib = new ImageButton(this);
            ib.setImageResource(R.drawable.buy_48);
            ib.setOnClickListener(this);
            row1.addView(et1);
            row1.addView(et2);
            row1.addView(ib);

            int rowIndex = 1;
            for (int i = 0; i < dinerList.size(); i++) {
                rowIndex += dinerList.get(i).orderList.size();

            }
            mainTable.addView(row1, rowIndex);

            TableRow row2 = new TableRow(this);

            TextView tv1 = new TextView(this);
            TextView tv2 = new TextView(this);

            tv1.setText(et1.getText().toString());
            tv1.setGravity(textSplit1.getGravity());

            tv2.setText(et2.getText().toString());
            tv2.setGravity(textSplit1Dollar.getGravity());
            row2.addView(tv1);
            row2.addView(tv2);

            mainTable.addView(row2, rowIndex + 11 + dinerList.size());

            Diner diner = new Diner(et1, et2, ib, tv1, tv2);
            dinerList.add(diner);

        } else {
            for (int i = 0; i < dinerList.size(); i++) {
                if (v == dinerList.get(i).ibAddOrder) {
                    TableRow row3 = new TableRow(this);
                    EditText emptyEditText = new EditText(this);
                    emptyEditText.setVisibility(4);

                    EditText newOrder = new EditText(this);
                    newOrder.setText("$0.00");
                    newOrder.setSelectAllOnFocus(true);
                    newOrder.setInputType(amount1of1.getInputType());
                    newOrder.setGravity(amount1of1.getGravity());
                    newOrder.setLayoutParams(amount1of1.getLayoutParams());
                    newOrder.setWidth(amount1of1.getWidth());
                    newOrder.requestFocus();

                    row3.addView(emptyEditText);
                    row3.addView(newOrder);
                    int rowIndex2 = 1;
                    for (int j = 0; j <= i; j++) {
                        rowIndex2 += dinerList.get(j).orderList.size();
                    }
                    mainTable.addView(row3, rowIndex2);
                    dinerList.get(i).newOrder(newOrder);
                }
            }
        }
    }

    public void onFocusChange(View v, boolean hasFocus) {
        for (int i = 0; i < dinerList.size(); i++) {
            if (v == dinerList.get(i).etName && !hasFocus) {
                dinerList.get(i).setName();
            }
        }
    }
}
