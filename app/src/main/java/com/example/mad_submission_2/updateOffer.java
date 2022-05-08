package com.example.mad_submission_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_submission_2.Database.DBhandler;

import java.util.List;

public class updateOffer extends AppCompatActivity {

    EditText offerName, startAndEndDate, discount, previousPrice, currentPrice;
    TextView updateAndDeleteOffer;
    Button search, update, delete;
    RadioButton birthdayOffer, anniversaryOffer;
    String offerType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_offer);
        updateAndDeleteOffer=findViewById(R.id.updateanddeleteoffers);
        offerName= findViewById(R.id.offername);
        startAndEndDate = findViewById(R.id.startandenddte);
        discount = findViewById(R.id.disco);
        previousPrice = findViewById(R.id.prvsprice);
        currentPrice=findViewById(R.id.curentprice);
        search= findViewById(R.id.search);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        birthdayOffer = findViewById(R.id.birthdyoff);
        anniversaryOffer = findViewById(R.id.anniversryoff);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBhandler dbHandler = new DBhandler(getApplicationContext());
                List user = dbHandler.readAllInfo(offerName.getText().toString());
                if (user.isEmpty()) {
                    Toast.makeText(updateOffer.this, "No Offer Available", Toast.LENGTH_SHORT).show();
                    offerName.setText(null);
                } else {
                    Toast.makeText(updateOffer.this, "Offer Found. Offer Name : " + user.get(0).toString(),
                            Toast.LENGTH_SHORT).show();
                    offerName.setText(user.get(0).toString());
                    startAndEndDate.setText(user.get(1).toString());
                    discount.setText(user.get(2).toString());
                    previousPrice.setText(user.get(3).toString());
                    currentPrice.setText(user.get(4).toString());
                    if (user.get(5).toString().equals("Birthday Offer")) {
                        birthdayOffer.setChecked(true);

                    } else {
                        anniversaryOffer.setChecked(true);
                    }
                }
            }
        });

        //Update Function
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ( birthdayOffer.isChecked()) {

                    offerType = "Birthday Offer";
                } else {

                    offerType = "Anniversary Offer";
                }
                DBhandler dbhandler = new DBhandler(getApplicationContext());
                Boolean status = dbhandler.updateInfo(offerName.getText().toString(), startAndEndDate.getText().toString(),
                        discount.getText().toString(), previousPrice.getText().toString(), currentPrice.getText().toString(), offerType);
                if (status) {

                    Toast.makeText(updateOffer.this, "Offer Updated", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(updateOffer.this, "Offer Update Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

//Delete Function

        delete .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBhandler dbHandler = new DBhandler(getApplicationContext());
                dbHandler.deleteInfo(offerName.getText().toString());
                Toast.makeText(updateOffer.this, "Offer Cancelled!!", Toast.LENGTH_SHORT).show();

                offerName.setText(null);
                startAndEndDate.setText(null);
                discount.setText(null);
                previousPrice.setText(null);
                currentPrice.setText(null);
                birthdayOffer.setChecked(false);
                anniversaryOffer.setChecked(false);

            }
        });


    }
}