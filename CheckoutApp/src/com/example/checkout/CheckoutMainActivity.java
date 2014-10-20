package com.example.checkout;

import java.text.DecimalFormat;
import java.util.ArrayList;
//imports
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;
public class CheckoutMainActivity extends ActionBarActivity {
	//array lists for managing items and inventory
	ArrayList<Items> itemList = new ArrayList<Items>();
	ArrayList<Items> cartItems = new ArrayList<Items>();
	// layout item instantiation
	GridView ItemGrid;
	CustomGridViewAdapter customGridAdapter;
	double Total;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_main);
        //set total to zero to start
        Total = 0.00;
        String stringdouble= Double.toString(Total);
        EditText totalText = (EditText) findViewById(R.id.total);
        totalText.setText(stringdouble);
        //starting Items for Grid
        itemList.add(new Items("A man's arm",1.99));
        itemList.add(new Items("Wolverine",97.00));
        itemList.add(new Items("A Bit of String",2.34));
        itemList.add(new Items("FaceSucker",56.34));
        itemList.add(new Items("Your Mother",0.00));
        itemList.add(new Items("Armpit muncher",25.67));
        itemList.add(new Items("Whoore",1.00));
        itemList.add(new Items("A night at the Roxbury",300.00));
        itemList.add(new Items("Sucka",3.50));
        itemList.add(new Items("Morgan Freeman",0.01));
        //link gridview to itemlist
        ItemGrid = (GridView)findViewById(R.id.itemGrid);
        customGridAdapter = new CustomGridViewAdapter(this, R.layout.row_grid, itemList);
        ItemGrid.setAdapter(customGridAdapter);
        //set onlclick listener for the gridview
        ItemGrid.setOnItemClickListener(new OnItemClickListener() {
        	@Override
			public void onItemClick(AdapterView<?> parent, View v,
				int position, long id) {
				addList(position);
				displayTotal(calcTotal());
			}
		});   
    } 
    //calculate price total for all objects in the checkout cart
    public double calcTotal()
    {
    	double TempTotal=0;
    	if(!cartItems.isEmpty())
    	{
    		for(Items tempI : cartItems)
	    	{
	    		TempTotal += tempI.getPrice();
	    	}
    	}
		return TempTotal;
    }
    //display the current total
    public void displayTotal (double d)
    {
		EditText totalText = (EditText) findViewById(R.id.total);
		DecimalFormat df = new DecimalFormat("#.##");
		totalText.setText(df.format(d));
    }
    //add items from the girdview list to the cart list
    public void addList(int i)
    {
    	cartItems.add(new Items((itemList.get(i).getItem()), (itemList.get(i).getPrice())));
    	updateCart();
    	ListView cList = (ListView)findViewById(R.id.cartList);
    	//set onclick listener for removal of items from the checkout cart
    	cList.setOnItemClickListener(new OnItemClickListener() {
    		public void onItemClick(AdapterView<?> parent, View v, int position, long id)
    		{
    			exterminateItem(v, position);
    		}
    	});	
    }
    //remove items from the checkout cart and update the totals
    public void exterminateItem(View v, int position)
    {
    	cartItems.remove(position);
    	updateCart();
        displayTotal(calcTotal());
    }
    //update the display of the cart with the current arraylist of objects added
    public void updateCart()
    {
    	ListView cList = (ListView)findViewById(R.id.cartList);
    	customGridAdapter = new CustomGridViewAdapter(this, R.layout.row_grid, cartItems);
        cList.setAdapter(customGridAdapter);
    }
    //toggle a 5% discount to the current total and recalculate
    public void Dis5(View v)
    { 
    	boolean on = ((ToggleButton) v).isChecked();
        if (on) {
        	displayTotal(calcTotal() - (calcTotal()*0.05));
        }
        else {
        	displayTotal(calcTotal());
        }
    }
  //toggle a 10% discount to the current total and recalculate
    public void Dis10(View v)
    {
    	boolean on = ((ToggleButton) v).isChecked();
        if (on) {
        	displayTotal(calcTotal() - (calcTotal()*0.10));
        }
        else {
        	displayTotal(calcTotal());
        }
    }
    //checkout
    public void checkOut(View v)
    {
    	//clear the checkout list and update totals
    	cartItems.clear();
    	updateCart();
    	displayTotal(calcTotal());
    	//launch alert dialog box for payment type
    	AlertDialog builder = new AlertDialog.Builder(CheckoutMainActivity.this).create();
    	builder.setTitle(R.string.payment);
    	builder.setButton(AlertDialog.BUTTON_POSITIVE, "Visa", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getBaseContext(), "Thanks for the Visa Bitch", Toast.LENGTH_LONG).show();
			}
		});
    	builder.setButton(AlertDialog.BUTTON_NEGATIVE, "Debit", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getBaseContext(), "Thanks for the Debit Bitch", Toast.LENGTH_LONG).show();
			}
		});
    	builder.setButton(AlertDialog.BUTTON_NEUTRAL, "Cash", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getBaseContext(), "Thanks for the Cash Bitch", Toast.LENGTH_LONG).show();
			}
		});
    	builder.show();
        
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.checkout_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}