package com.example.mobilsorgular;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class Type2 extends AppCompatActivity {

    ListView listView;
    private FirebaseFirestore firebaseFirestore;
    EditText first_day_text,second_day_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type2);

        firebaseFirestore=FirebaseFirestore.getInstance();
        listView =findViewById(R.id.listView);
        first_day_text=findViewById(R.id.first_day_text);
        second_day_text=findViewById(R.id.second_day_text);
    }
    public void GetType2(View view){
        String first_day=first_day_text.getText().toString();
        String second_day=second_day_text.getText().toString();

        String first_date="2020-12-"+first_day+" 00:00:00";
        String second_date="2020-12-"+second_day+" 00:00:00";
        System.out.println("type2 tiklandi");
        ArrayList<YellowTripData> list=new ArrayList<YellowTripData>();



        CollectionReference tripdata = firebaseFirestore.collection("yellow_tripdata_2020-12");
        tripdata.whereGreaterThanOrEqualTo("tpep_pickup_datetime", first_date)
                .whereLessThanOrEqualTo("tpep_pickup_datetime", second_date).orderBy("tpep_pickup_datetime").addSnapshotListener(new EventListener<QuerySnapshot>() {

            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(value!=null){
                    for(DocumentSnapshot snapshot : value.getDocuments()){
                        Map<String, Object> data= snapshot.getData();

                        YellowTripData yellowTripData = new YellowTripData((String) data.get("VendorID"),
                                (String) data.get("tpep_pickup_datetime"),
                                (String) data.get("tpep_dropoff_datetime"),
                                (String) data.get("passenger_count"),
                                (String) data.get("trip_distance"),
                                (String) data.get("RatecodeID"),
                                (String) data.get("store_and_fwd_flag"),
                                (String) data.get("PULocationID"),
                                (String) data.get("DOLocationID"),
                                (String) data.get("payment_type"),
                                (String) data.get("fare_amount"),
                                (String) data.get("extra"),
                                (String) data.get("mta_tax"),
                                (String) data.get("tip_amount"),
                                (String) data.get("tolls_amount"),
                                (String) data.get("improvement_surcharge"),
                                (String) data.get("total_amount"),
                                (String) data.get("congestion_surcharge")
                        );
                        list.add(yellowTripData);
                        String trip_distance=(String) data.get("trip_distance");
                        String date=(String) data.get("tpep_pickup_datetime");
                        System.out.println(trip_distance);
                        System.out.println(date);
                    }
                    for (int i = 0; i < list.size(); i++) {

                        for (int j = list.size() - 1; j > i; j--) {
                            if (Double.parseDouble(list.get(i).trip_distance) > Double.parseDouble(list.get(j).trip_distance)) {

                                YellowTripData tmp = list.get(i);

                                list.set(i,list.get(j)) ;
                                list.set(j,tmp);

                            }

                        }

                    }
                    System.out.println("CAYLARRRRRRRRRRRRRRRRRRRRRR");
                    ArrayList<String> show_list=new ArrayList<String>();
                    for (int i = 0; i <5 ; i++) {
                        show_list.add("VendorID: "+list.get(i).VendorID+"\n"+
                                        "tpep_pickup_datetime: "+list.get(i).tpep_pickup_datetime+"\n"+
                                        "tpep_dropoff_datetime: "+list.get(i).tpep_dropoff_datetime+"\n"+
                                        "passenger_count: "+list.get(i).passenger_count+"\n"+
                                        "trip_distance: "+list.get(i).trip_distance+"\n"+
                                        "RatecodeID: "+list.get(i).RatecodeID+"\n"+
                                        "store_and_fwd_flag: "+list.get(i).store_and_fwd_flag+"\n"+
                                        "PULocationID: "+list.get(i).PULocationID+"\n"+
                                        "DOLocationID: "+list.get(i).DOLocationID+"\n"+
                                        "payment_type: "+list.get(i).payment_type+"\n"+
                                        "fare_amount: "+list.get(i).fare_amount+"\n"+
                                        "extra: "+list.get(i).extra+"\n"+
                                        "mta_tax: "+list.get(i).mta_tax+"\n"+
                                        "tip_amount: "+list.get(i).tip_amount+"\n"+
                                        "tolls_amount: "+list.get(i).tolls_amount+"\n"+
                                        "improvement_surcharge: "+list.get(i).improvement_surcharge+"\n"+
                                        "total_amount: "+list.get(i).total_amount+"\n"+
                                        "congestion_surcharge: "+list.get(i).congestion_surcharge+"\n");
                        System.out.println(list.get(i).trip_distance);
                        System.out.println(list.get(i).tpep_pickup_datetime);
                    }
                    ArrayAdapter arrayAdapter=new ArrayAdapter(Type2.this, android.R.layout.simple_list_item_1,show_list);
                    listView.setAdapter(arrayAdapter);
                }

            }
        });



    }
}