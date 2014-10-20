package com.example.checkout;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;



public class CheckoutMainActivity extends ActionBarActivity {
	ArrayList<Items> itemList = new ArrayList<Items>();;
	GridView ItemGrid;
	CustomGridViewAdapter customGridAdapter;
	double Total;
	ArrayList<Items> cartItems = new ArrayList<Items>();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_main); 
        Total = 0.00;
        String stringdouble= Double.toString(Total);
        EditText totalText = (EditText) findViewById(R.id.total);
        totalText.setText(stringdouble);
        
        itemList.add(new Items("A man's arm",1.99));
        itemList.add(new Items("Wolverine",9999.00));
        itemList.add(new Items("A Bit of String",2.34));
        itemList.add(new Items("FaceSucker",56.34));
        itemList.add(new Items("Your Mother",0.00));
        itemList.add(new Items("Armpit muncher",25.67));
        itemList.add(new Items("Whoore",1.00));
        itemList.add(new Items("A night at the Roxbury",300.00));
        itemList.add(new Items("Sucka",3.50));
        itemList.add(new Items("Morgan Freeman",0.01));
        
        ItemGrid = (GridView)findViewById(R.id.itemGrid);
        customGridAdapter = new CustomGridViewAdapter(this, R.layout.row_grid, itemList);
        ItemGrid.setAdapter(customGridAdapter);
        

        ItemGrid.setOnItemClickListener(new OnItemClickListener() {
        	@Override
			public void onItemClick(AdapterView<?> parent, View v,
				int position, long id) {
				calcTotal(position);
				addList(position);
			}
		});
        
        
        
    } 
    
    public void calcTotal(int i)
    {
    	double tempPrice = itemList.get(i).getPrice();
		Total += tempPrice;
		String stringdouble= Double.toString(Total);
		EditText totalText = (EditText) findViewById(R.id.total);
		totalText.setText(stringdouble);
    }
    public void addList(int i)
    {
    	String tempStr =itemList.get(i).getItem();
    	double tempD = itemList.get(i).getPrice();
    	cartItems.add(new Items(tempStr, tempD));
    	ListView cList = (ListView)findViewById(R.id.cartList);
        customGridAdapter = new CustomGridViewAdapter(this, R.layout.row_grid, cartItems);
        cList.setAdapter(customGridAdapter);
    	
    	
    	cList.setOnItemClickListener(new OnItemClickListener() {
    		public void onItemClick(AdapterView<?> parent, View v, int position, long id)
    		{
    			exterminateItem(v, position);
    		}
    	});
    	
    }
    public void exterminateItem(View v, int position)
    {
    	ListView cList = (ListView)findViewById(R.id.cartList);
    	double tempD = cartItems.get(position).getPrice();
    	Total -= tempD;
		String stringdouble= Double.toString(Total);
		EditText totalText = (EditText) findViewById(R.id.total);
		totalText.setText(stringdouble);
    	cartItems.remove(position);
    	customGridAdapter = new CustomGridViewAdapter(this, R.layout.row_grid, cartItems);
        cList.setAdapter(customGridAdapter);

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
