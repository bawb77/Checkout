package com.example.checkout;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class CheckoutMainActivity extends ActionBarActivity {
	private ArrayList<Items> itemList;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_main);
         for(int i=0; i != 10; i++)
         {
        	 itemList.add(new Items());
         }
        itemList.get(0).setItem("A man's arm",1.99);
        itemList.get(1).setItem("Wolverine",9999.99);
        itemList.get(2).setItem("A Bit of String",2.34);
        itemList.get(3).setItem("FaceSucker",56.34);
        itemList.get(4).setItem("Your Mother",0.00);
        itemList.get(5).setItem("Armpit muncher",25.67);
        itemList.get(6).setItem("Whoore",1.00);
        itemList.get(7).setItem("A night at the Roxbury",300.00);
        itemList.get(8).setItem("Sucka",3.50);
        itemList.get(9).setItem("Morgan Freeman",0.01);
        
        
        
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
