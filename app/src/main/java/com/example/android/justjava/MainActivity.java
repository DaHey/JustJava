package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * hahhaha
     * This method is called when the order button is clicked.
     */
    int quantity = 0;
    public void increment(View view) {
        if(quantity == 100){
            Toast.makeText(MainActivity.this, "You cannot have more than 100 cups of coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1 ;
        displayQuantity(quantity);
    }
    public void decrement(View view) {
        if(quantity == 1){
            Toast.makeText(MainActivity.this, "You cannot have less than 1 cups of coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1 ;
        displayQuantity(quantity);
    }

    public void submitOrder(View view) {
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.checkbox_whippedCream);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.checkbox_chocolate);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        int price = calculatePrice(quantity, hasWhippedCream, hasChocolate);
        String pricemessage = createOrderSummary(price, hasWhippedCream, hasChocolate, name);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for "+name);
        intent.putExtra(Intent.EXTRA_TEXT, pricemessage);
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }

        displayMessage(pricemessage);
    }

    private int calculatePrice(int quantity, boolean addWhippedCream, boolean addChocolate) {
        int price = quantity * 5;
        if(addWhippedCream){
            price += 1;
        }
        if(addChocolate){
            price += 2;
        }
        return price;
    }

    /**create order summary
     *
     * @param priceOfOrder price of order in total
     */
    public String createOrderSummary(int priceOfOrder, boolean addWhippedCream, boolean addChocolate
    , String name){
        String message = "Name: "+name+"\nadd whipped cream? "+addWhippedCream+
                "\nadd chocolate? "+addChocolate+ "\nQuantity: "+quantity+
                "\nTotal: "+priceOfOrder+"\nThank you!";
        return message;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }


}