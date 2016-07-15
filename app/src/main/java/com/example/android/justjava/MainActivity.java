package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

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
        quantity = quantity + 1 ;
        displayQuantity(quantity);
    }
    public void decrement(View view) {
        quantity = quantity - 1 ;
        displayQuantity(quantity);
    }

    public void submitOrder(View view) {
        int price = calculatePrice(quantity);
        createOrderSummary(price);
    }

    private int calculatePrice(int quantity) {
        int price = quantity * 5;
        return price;
    }

    /**create order summary
     *
     * @param priceOfOrder price of order in total
     */
    public void createOrderSummary(int priceOfOrder){
        String message = "Name: Kaptain Kunal "+"\nQuantity: "+quantity+"\nTotal: "+priceOfOrder+"\nThank you!";
        displayMessage(message);
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