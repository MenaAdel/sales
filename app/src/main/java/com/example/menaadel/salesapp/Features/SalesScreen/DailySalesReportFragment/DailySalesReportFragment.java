package com.example.menaadel.salesapp.Features.SalesScreen.DailySalesReportFragment;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.menaadel.salesapp.DataStorage.sqLite.DatabaseOperations;
import com.example.menaadel.salesapp.R;
import com.example.menaadel.salesapp.Utils.UtilsFunctions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MenaAdel on 6/13/2018.
 */

public class DailySalesReportFragment extends Fragment {

    @BindView(R.id.table_layout)
    TableLayout tableLayout;
    TextView dateView;
    TextView totalPrice;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.layout)
    RelativeLayout layout;

    DatabaseOperations db;
    TableRow.LayoutParams params;
    boolean isOdd = true;
    int sum = 0;
    int intPrice=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_daily_sales_report,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        db = new DatabaseOperations(getActivity());
        showTableData();
    }

    private void showTableData() {


        Cursor cr = db.SELECT(db);
        cr.moveToFirst();
        try {

            do {
                //create a new row to add
                TableRow row = new TableRow(getActivity());
                row.setWeightSum(1.0f);
                dateView = new TextView(getActivity());
                if (!cr.getString(4).equals("") || cr.getString(4) != null || !cr.isNull(4)) {
                    dateView.setText(cr.getString(4));
                    dateView.setGravity(Gravity.CENTER);
                    dateView.setTextColor(Color.BLACK);
                    params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.6f);
                    dateView.setLayoutParams(params);
                    dateView.setPadding(5, 5, 5, 5);
                    row.addView(dateView);
                }

                if (!cr.getString(3).equals("") || cr.getString(3) != null) {
                    totalPrice = new TextView(getActivity());
                    totalPrice.setText(cr.getString(3));
                    totalPrice.setGravity(Gravity.CENTER);
                    totalPrice.setTextColor(Color.BLACK);
                    params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.4f);
                    totalPrice.setLayoutParams(params);
                    totalPrice.setPadding(5, 5, 5, 5);
                    if (!cr.getString(3).equals("")) {
                        intPrice = Integer.parseInt(cr.getString(3));
                        sum += intPrice;
                        tvTotalPrice.setText(String.valueOf(sum));
                    }
                    row.addView(totalPrice);

                    if (isOdd) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            dateView.setBackground(getResources().getDrawable(R.drawable.table_background2));
                            totalPrice.setBackground(getResources().getDrawable(R.drawable.table_background2));
                        }
                        isOdd = false;
                    } else {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            dateView.setBackground(getResources().getDrawable(R.drawable.table_background));
                            totalPrice.setBackground(getResources().getDrawable(R.drawable.table_background));
                        }
                        isOdd = true;
                    }

                    tableLayout.addView(row);
                } else {
                    UtilsFunctions.showSnackbar(layout, getString(R.string.nodata), Color.BLACK);
                }
            } while (cr.moveToNext());
        }catch (IndexOutOfBoundsException e){
            e.fillInStackTrace();
        }
    }

    private void sendData(){
        Intent intent= new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT ,new String[]{getString(R.string.totalpriceofitems)});
        intent.putExtra(Intent.EXTRA_TEXT ,new String[]{tvTotalPrice.getText().toString()});
        try {
            startActivity(Intent.createChooser(intent ,getString(R.string.sendmal)));
        }catch (android.content.ActivityNotFoundException e){
            UtilsFunctions.showSnackbar(layout, getString(R.string.nomail), Color.BLACK);
        }
    }

    @OnClick({R.id.btn_save_and_email})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.btn_save_and_email:
                sendData();
                break;
            default:
                break;
        }
    }
}
