package com.example.mattwilfert.dailyweightlog;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.mattwilfert.dailyweightlog.Constants.WEIGHT_COLUMN;
import static com.example.mattwilfert.dailyweightlog.Constants.DATE_COLUMN;



public class ShowLog extends ActionBarActivity {

    private WeightsDataSource datasource;
    List<Weight> all_weights;
    public final static String EXTRA_MESSAGE = "com.example.mattwilfert.dailyweightlog.MESSAGE";

    private ArrayList<HashMap<String, String>> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_show_log);

        ListView listView = (ListView)findViewById(R.id.listView);
        populateList();
        ListViewAdapter adapter = new ListViewAdapter(this, list);
        listView.setAdapter(adapter);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void populateList(){
        list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> temp;

        datasource = new WeightsDataSource(this);
        datasource.open();
        all_weights = datasource.getAllWeights();

        Toast.makeText(getApplicationContext(), all_weights.get(0).getDate(), Toast.LENGTH_SHORT).show();

        for(int i=0; i < all_weights.size(); i++){
            temp = new HashMap<String, String>();

            temp.put(WEIGHT_COLUMN, String.valueOf(all_weights.get(i).getWeight()));
            temp.put(DATE_COLUMN, all_weights.get(i).getDate());
            list.add(temp);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_show_log, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id){
            case R.id.action_add_weight:
                openAddWeight();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openAddWeight(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(EXTRA_MESSAGE, "Welcome.");
        startActivity(intent);
    }




}
