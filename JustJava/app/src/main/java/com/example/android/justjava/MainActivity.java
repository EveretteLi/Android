/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */
package com.example.android.justjava;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static android.R.attr.duration;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    //the user's name
    private EditText userName;
    //quantity of coffee
    private TextView quantity;
    //total price
    private TextView total;
    //send order
    private Button order;
    //the number shows on the quantity
    private int coffee;
    //current total price for coffee
    private  float totalCoffeePrice;
    //topping price
    private float topping;
    //cream
    private CheckBox cream;
    //chocolate
    private  CheckBox choco;
    //base coffee price
    private final float cup = 4.79f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText) findViewById(R.id.name_reader);
        cream = (CheckBox) findViewById(R.id.cream_topping);
        choco = (CheckBox) findViewById(R.id.choco_topping);
        quantity = (TextView) findViewById(R.id.quantity_view);
        total = (TextView) findViewById(R.id.summary_view);
        order = (Button) findViewById(R.id.order);
        coffee = 0;
        totalCoffeePrice = 0;
        topping = 0;

    }

    //add topping price into total based on cases
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            //check cream
            case R.id.cream_topping:
                //add cream for $0.5
                if (checked)
                    topping += 0.5f;
                else
                    if(topping > 0)
                        topping -= 0.5f;
                break;
            //check chocolate
            case R.id.choco_topping:
                //add chocolate for $0.5
                if (checked)
                    topping += 0.6f;
                else
                    if(topping > 0)
                        topping -= 0.6f;
                break;
        }
        order.setText(userName.getText()+"'s ORDER");
        if(totalCoffeePrice != 0)
            total.setText("$"+(totalCoffeePrice+topping));
    }

    //add the coffee quantity with the same topping state
    public void sendMessage(View v) {
        //get the button
        Button temp = (Button) v;
        //see case
        switch(v.getId()) {
            //adding
            case R.id.button_add:
                //add 1 to coffee
                if(coffee > -1 && coffee < 3)
                    coffee += 1;
                else
                    Toast.makeText(getApplicationContext(), "max numbers of coffee Σ(´ⅴ｀lll)",
                            Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_minus:
                //minus
                if(coffee > 0)
                    coffee -= 1;
                else
                    Toast.makeText(getApplicationContext(), "thanks for your coffee (′·ω·`)",
                            Toast.LENGTH_SHORT).show();
                break;
        }
        order.setText(userName.getText()+"'s ORDER\n");
        totalCoffeePrice = cup*coffee;
        quantity.setText(""+coffee);
        total.setText("$" + (totalCoffeePrice+topping));
    }

    @Override
    public String toString() {
        String sum = "Name: "+userName.getText()+"\n";
        if(cream.isChecked())
            sum += "Topping: Cream\n";
        else if(choco.isChecked())
            sum += "Topping: Chocolate\n";
        sum += "Coffee: "+coffee+"\n";
        sum += "Total: "+total.getText()+"\n";
        sum += "THANK YOU !!";
        return sum;
    }

    //create a email and send it
    public void finish(View v) {
        //if the coffee is 0 show toast and return
        if(coffee == 0){
            Toast.makeText(getApplicationContext(),"Can't order 0 coffee :(",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        //create a send action
        Intent i = new Intent(Intent.ACTION_SEND);
        //set font
        i.setType("message/rfc822");
        //subject of an email
        i.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order Receipt");
        //put the information
        i.putExtra(Intent.EXTRA_TEXT   , this.toString());
        //try send the message by mail app in this phone
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext(), "There are no email clients installed.",
                    Toast.LENGTH_SHORT).show();
        }
        //clean the UI
        userName.setText("");
        cream.setChecked(false);
        choco.setChecked(false);
        coffee = 0;
        totalCoffeePrice = 0;
        topping = 0;
        quantity.setText(""+coffee);
        total.setText("$" + (totalCoffeePrice+topping));
        order.setText("ORDER");

    }
}
