
package com.example.mad_submission_2;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.RadioButton;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.appcompat.app.AppCompatActivity;

        import com.example.mad_submission_2.Database.DBhandler;


public class offers extends AppCompatActivity {

    EditText offerName, startAndEndDate, discount, previousPrice, currentPrice;
    TextView addOffer;
    Button AddOffer;
    RadioButton birthdayOffer, anniversaryOffer;
    String offerType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);

        addOffer = findViewById(R.id.offe);
        offerName = findViewById(R.id.name);
        startAndEndDate = findViewById(R.id.startandenddate);
        discount = findViewById(R.id.discount);
        previousPrice = findViewById(R.id.previousprice);
        currentPrice = findViewById(R.id.currntpric);
        AddOffer = findViewById(R.id.button2);
        birthdayOffer = findViewById(R.id.birthdayoffer);
        anniversaryOffer = findViewById(R.id.anniversaryoffer);

        //ADD Functionnn

        AddOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (birthdayOffer.isChecked()) {

                    offerType = "Birthday Offer";
                } else {

                    offerType = "Anniversary Offer";
                }
                DBhandler dbhandler = new DBhandler(getApplicationContext());
                long newID = dbhandler.addInfo(offerName.getText().toString(), startAndEndDate.getText().toString(),
                        discount.getText().toString(), previousPrice.getText().toString(), currentPrice.getText().toString(), offerType);
                Toast.makeText(offers.this, "OfferAdded. Delivery ID: ", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), updateOffer.class);
                startActivity(i);
            }
        });

    }
}



