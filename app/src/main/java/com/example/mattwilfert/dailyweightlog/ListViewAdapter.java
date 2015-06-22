package com.example.mattwilfert.dailyweightlog;

import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;

import static com.example.mattwilfert.dailyweightlog.Constants.WEIGHT_COLUMN;
import static com.example.mattwilfert.dailyweightlog.Constants.DATE_COLUMN;


/**
 * Created by MattWilfert on 4/4/15.
 */
public class ListViewAdapter extends BaseAdapter{
    public ArrayList<HashMap<String, String>> list;
    private WeightsDataSource datasource;
    Activity activity;
    List<Weight> all_weights;


    public ListViewAdapter(Activity activity, ArrayList<HashMap<String, String>> list, List<Weight> all_weights, WeightsDataSource datasource){
        super();
        this.activity=activity;
        this.list=list;
        this.datasource= datasource;
        this.all_weights = all_weights;
    }

    public int getCount(){
        return list.size();
    }

    public Object getItem(int position){
        return list.get(position);
    }

    public long getItemId(int position){
        return 0;
    }

    private class ViewHolder{
        TextView txtWeight;
        TextView txtDate;
    }

    public View getView(final int position, View convertView, ViewGroup parent){

        ViewHolder holder;

        LayoutInflater inflater = activity.getLayoutInflater();

        if(convertView == null){

            convertView=inflater.inflate(R.layout.column_row, null);
            holder = new ViewHolder();

            holder.txtWeight=(TextView) convertView.findViewById(R.id.all_weights_list);
            holder.txtDate=(TextView) convertView.findViewById(R.id.all_weights_date_list);
            Button deleteBtn = (Button)convertView.findViewById(R.id.delete_btn);

            deleteBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //do something
                    list.remove(position); //or some other task
                    datasource.deleteWeight(all_weights.get(position));
                    all_weights = datasource.getAllWeights();
                    notifyDataSetChanged();
                }
            });

            convertView.setTag(holder);
        }else{
            holder=(ViewHolder) convertView.getTag();
        }

        HashMap<String, String> map = list.get(position);
        holder.txtWeight.setText(map.get(WEIGHT_COLUMN));
        holder.txtDate.setText(map.get(DATE_COLUMN));

        return convertView;
    }



}
