package com.example.nicolasdubus.lab1_3811637;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nicolasdubus on 2016-02-22.
 */
public class Diner {


    public double total;
    public EditText etName, etFirstOrder;
    public ImageButton ibAddOrder;
    public TextView tvName, tvSplitBill;
    public ArrayList<EditText> orderList;

    public Diner(EditText etName, EditText etFirstOrder, ImageButton ibAddOrder,
                 TextView tv1, TextView tv2) {
        this.total = 0.00;
        this.orderList = new ArrayList<>();
        this.etName = etName;
        this.etFirstOrder = etFirstOrder;
        this.ibAddOrder = ibAddOrder;
        this.tvName = tv1;
        this.tvSplitBill = tv2;
        this.orderList.add(etFirstOrder);
    }

    public void setName() {
        tvName.setText(etName.getText().toString());
    }

    public void newOrder(EditText newOrder) {
        orderList.add(newOrder);
    }

    public void updateTotal() {
        total = 0.0;
        Pattern p = Pattern.compile("[0-9]\\.[0-9][0-9]");
        for (int i = 0; i < orderList.size(); i++) {
            String text = orderList.get(i).getText().toString();
            Matcher m = p.matcher(text);
            if (m.find()) {
                total += Double.parseDouble(m.group(0));
            }
        }
        tvSplitBill.setText(String.format("$%,.2f", total));
    }
}
