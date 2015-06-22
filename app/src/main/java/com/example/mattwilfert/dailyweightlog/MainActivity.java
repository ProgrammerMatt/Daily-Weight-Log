package com.example.mattwilfert.dailyweightlog;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private WeightsDataSource datasource;
    public final static String EXTRA_MESSAGE = "com.example.mattwilfert.dailyweightlog.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id){
            case R.id.action_show_log:
                openShowLog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void submit_weight(View view){

//        ArrayAdapter<Weight> adapter = new ArrayAdapter<Weight>(this, android.R.layout.simple_expandable_list_item_1, values);
//        setListAdapter(adapter);
        boolean add_weight_success;
        double weight;
        Weight newWeight = new Weight();
        String date = "hi";
        datasource = new WeightsDataSource(this);
        datasource.open();

        Intent intent = new Intent(this, ShowLog.class);
        EditText editText = (EditText) findViewById(R.id.edit_weight);
        String message = editText.getText().toString();
        if(message.length() > 0){
            try{
                weight = Double.parseDouble(message);
                newWeight.setWeight(weight);
                newWeight.setDate(date);
                add_weight_success = true;
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }catch(Exception e1){
                Toast.makeText(getApplicationContext(), "Please enter a number (i.e. 163.2)", Toast.LENGTH_LONG).show();
                e1.printStackTrace();
                add_weight_success = false;
                weight = 0;
            }

            if(add_weight_success == true){
                //Toast.makeText(getApplicationContext(), date, Toast.LENGTH_SHORT).show();
                datasource.createWeight(weight, date);
            }
        }

    }

    public void openShowLog(){
        Intent intent = new Intent(this, ShowLog.class);
        intent.putExtra(EXTRA_MESSAGE, "No Weight Entered.");
        startActivity(intent);
    }

    

}
