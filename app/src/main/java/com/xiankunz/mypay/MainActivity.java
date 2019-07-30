package com.xiankunz.mypay;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiankunz.mypay.com.xiankunz.mypay.model.CardInfo;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    // TODO: check credit card number when ccn edit text just lost focus
    Button next_btn;
    EditText ccn_et;
    TextView bank_tx;
    EditText cvv_et;
    ImageView cvv_img;
    DatePicker date_dp;
    Activity mContext;
    EditText firstName_et;
    EditText lastName_et;
    ImageView cvv_big_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        next_btn = (Button) findViewById(R.id.btn_next);
        ccn_et = (EditText) findViewById(R.id.et_credit_card_number);
        cvv_et = (EditText) findViewById(R.id.et_cvv);
        firstName_et = (EditText) findViewById(R.id.et_first_name);
        lastName_et = (EditText) findViewById(R.id.et_last_name);
        date_dp = (DatePicker) findViewById(R.id.date_dp) ;

        cvv_big_img = (ImageView) findViewById(R.id.img_cvv_big);
        cvv_img = (ImageView) findViewById(R.id.img_ccv);
        bank_tx = (TextView) findViewById(R.id.et_ccn_bank);

        cvv_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cvv_big_img.getVisibility() == View.GONE) {
                    cvv_big_img.setVisibility(View.VISIBLE);
                } else {
                    cvv_big_img.setVisibility(View.GONE);
                }
            }
        });

        cvv_big_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cvv_big_img.setVisibility(View.GONE);
            }
        });

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        alertDialogBuilder.setPositiveButton("OK", null);

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardType cardType = CreditCardUtil.checkCCN(ccn_et.getText().toString().trim());
                if (cardType == CardType.invalid) {
                    alertDialogBuilder.setMessage("Illegal credit card number!");
                    AlertDialog illegalCCNDialog = alertDialogBuilder.create();
                    illegalCCNDialog.show();
                    return;
                }
                int cvvLen = cvv_et.getText().toString().trim().length();
                if (!(cvvLen == 3 || (cvvLen == 4 && cardType == CardType.AmericanExpress))) {
                    alertDialogBuilder.setMessage("Illegal cvv number");
                    AlertDialog illegalCVVDialog = alertDialogBuilder.create();
                    illegalCVVDialog.show();
                    return;
                }
                if (firstName_et.getText().toString().trim().isEmpty()) {
                    alertDialogBuilder.setMessage("Please input First name");
                    AlertDialog illegalCVVDialog = alertDialogBuilder.create();
                    illegalCVVDialog.show();
                    return;
                }
                if (lastName_et.getText().toString().trim().isEmpty()) {
                    alertDialogBuilder.setMessage("Please input last name");
                    AlertDialog illegalCVVDialog = alertDialogBuilder.create();
                    illegalCVVDialog.show();
                    return;
                }
                CardInfo cardInfo = new CardInfo();
                cardInfo.setCreditCardNumber(ccn_et.getText().toString().trim());
                cardInfo.setCvv(cvv_et.getText().toString().trim());
                // date
                Date date = new Date(date_dp.getYear(), date_dp.getMonth(), date_dp.getDayOfMonth());
                cardInfo.setDate(date);

                cardInfo.setFirstName(firstName_et.getText().toString().trim());
                cardInfo.setLastName(lastName_et.getText().toString().trim());

                // TODO: submit card info
                // alert dialog instead temporary
                alertDialogBuilder.setMessage("Submit card info, Done!");
                AlertDialog illegalCVVDialog = alertDialogBuilder.create();
                illegalCVVDialog.show();
            }
        });
    }
}
