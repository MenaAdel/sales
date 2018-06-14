package com.example.menaadel.salesapp.Features.SalesScreen.SalesFragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.menaadel.salesapp.DataStorage.sqLite.DatabaseOperations;
import com.example.menaadel.salesapp.R;
import com.example.menaadel.salesapp.Utils.UtilsFunctions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MenaAdel on 6/13/2018.
 */

public class SalesScreenFragment extends Fragment {

    @BindView(R.id.ed_customer_email)
    EditText edCustomerEmail;
    @BindView(R.id.ed_item_type)
    EditText edItemType;
    @BindView(R.id.ed_qty)
    EditText edQty;
    @BindView(R.id.ed_price)
    EditText edPrice;
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.btn_save_and_email)
    Button btnSaveAndEmail;
    @BindView(R.id.rootLayout)
    RelativeLayout layout;
    TextView tvItem;
    TextView tvQty;
    TextView tvPrice;
    TableRow tableRow;
    @BindView(R.id.table_layout)
    TableLayout tableLayout;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;

    String sEmail = "";
    String sItemType = "";
    String sQty = "";
    String sPrice = "";

    boolean isOdd = true;
    TableRow.LayoutParams params;
    DatabaseOperations db;

    int tableSize = 0;
    int sum = 0;
    int intPrice=0;

    List<String> nItemType = new ArrayList<>();
    List<String> nQty= new ArrayList<>();
    List<String> nPrice = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sales_screen,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        db = new DatabaseOperations(getActivity());
    }

    private void addDetails(){
        sEmail = edCustomerEmail.getText().toString();
        sItemType = edItemType.getText().toString();
        sQty = edQty.getText().toString();
        sPrice = edPrice.getText().toString();
        setTableItemView();
    }

    private void setTableItemView(){
        //create a new row to add
        TableRow row = new TableRow(getActivity());
        tableSize ++;
        row.setWeightSum(1.0f);

        tvItem = new TextView(getActivity());
        tvItem.setText(sItemType);
        tvItem.setGravity(Gravity.CENTER);
        tvItem.setTextColor(Color.BLACK);
        params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.6f);
        tvItem.setLayoutParams(params);
        tvItem.setPadding(5,5,5,5);
        nItemType.add(sItemType);
        row.addView(tvItem);

        tvQty = new TextView(getActivity());
        tvQty.setText(sQty);
        tvQty.setGravity(Gravity.CENTER);
        tvQty.setTextColor(Color.BLACK);
        params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f);
        tvQty.setLayoutParams(params);
        tvQty.setPadding(5,5,5,5);
        nQty.add(sQty);
        row.addView(tvQty);

        tvPrice = new TextView(getActivity());
        tvPrice.setText(sPrice);
        tvPrice.setGravity(Gravity.CENTER);
        tvPrice.setTextColor(Color.BLACK);
        params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f);
        tvPrice.setLayoutParams(params);
        tvPrice.setPadding(5,5,5,5);
        nPrice.add(sPrice);
        if(!sPrice.equals("")) {
            intPrice = Integer.parseInt(sPrice);
            sum += intPrice;
            tvTotalPrice.setText(String.valueOf(sum));
        }
        row.addView(tvPrice);

        if(isOdd){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                tvItem.setBackground(getResources().getDrawable(R.drawable.table_background2));
                tvQty.setBackground(getResources().getDrawable(R.drawable.table_background2));
                tvPrice.setBackground(getResources().getDrawable(R.drawable.table_background2));
            }
            isOdd = false;
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                tvItem.setBackground(getResources().getDrawable(R.drawable.table_background));
                tvQty.setBackground(getResources().getDrawable(R.drawable.table_background));
                tvPrice.setBackground(getResources().getDrawable(R.drawable.table_background));
            }
            isOdd = true;
        }

        tableLayout.addView(row);

    }

    private void saveData(){
        for(int i=0 ;i<nItemType.size()||i<nPrice.size()||i<nQty.size(); i++){
            if(nItemType.size()>0 && nPrice.size()>0 &&nQty.size()>0){
                db.INSERT(nItemType.get(i),nQty.get(i),nPrice.get(i),tvTotalPrice.getText().toString());
            }else {
                Log.d("string",sPrice);
                UtilsFunctions.showSnackbar(layout, getString(R.string.emptydata), Color.BLACK);
            }
        }
        if(nItemType.size()>0 && nPrice.size()>0 &&nQty.size()>0){
            UtilsFunctions.showSnackbar(layout, getString(R.string.databaseinserted), Color.BLACK);
        }

    }

    private void saveAndSendData(){
        saveData();
        Intent intent= new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL ,new String[]{sEmail});
        intent.putExtra(Intent.EXTRA_SUBJECT ,new String[]{getString(R.string.totalpriceofitems)});
        intent.putExtra(Intent.EXTRA_TEXT ,new String[]{tvTotalPrice.getText().toString()});
        try {
            startActivity(Intent.createChooser(intent ,getString(R.string.sendmal)));
        }catch (android.content.ActivityNotFoundException e){
            UtilsFunctions.showSnackbar(layout, getString(R.string.nomail), Color.BLACK);
        }
    }

    @OnClick({R.id.btn_add ,R.id.btn_save ,R.id.btn_save_and_email})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.btn_add:
                addDetails();
                break;
            case R.id.btn_save:
                saveData();
                break;
            case R.id.btn_save_and_email:
                saveAndSendData();
                break;
            default:
                break;
        }
    }
}
