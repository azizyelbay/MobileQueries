package com.example.mobilsorgular;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseFirestore=FirebaseFirestore.getInstance();
        listView=findViewById(R.id.listView);

    }
    public void Type1(View view){
        System.out.println("type1 tiklandi");
        ArrayList<String> show_list2=new ArrayList<String>();
        ArrayList<YellowTripData> list=new ArrayList<YellowTripData>();
        CollectionReference tripdata = firebaseFirestore.collection("yellow_tripdata_2020-12");
        tripdata.orderBy("trip_distance", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {

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
                            if (Double.parseDouble(list.get(i).trip_distance) < Double.parseDouble(list.get(j).trip_distance)) {

                                YellowTripData tmp = list.get(i);

                                list.set(i,list.get(j)) ;
                                list.set(j,tmp);

                            }

                        }

                    }
                    System.out.println("CAYLARRRRRRRRRRRRRRRRRRRRRR");
                    for (int i = 0; i <5 ; i++) {
                        show_list2.add("trip_distance: "+list.get(i).trip_distance+"\n"+
                                "tpep_pickup_datetime: "+list.get(i).tpep_pickup_datetime+"\n");

                        System.out.println(list.get(i).trip_distance);
                        System.out.println(list.get(i).tpep_pickup_datetime);
                    }
                    ArrayAdapter arrayAdapter=new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,show_list2);
                    listView.setAdapter(arrayAdapter);
                }

            }
        });

        /* CollectionReference tripdata = firebaseFirestore.collection("yellow_tripdata_2020-12");
        tripdata.orderBy("trip_distance", Query.Direction.DESCENDING).limit(5).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(value!=null){
                    for(DocumentSnapshot snapshot : value.getDocuments()){
                        Map<String, Object> data= snapshot.getData();
                        String trip_distance=(String) data.get("trip_distance");
                        String date=(String) data.get("tpep_pickup_datetime");
                        show_list2.add(trip_distance+"\n"+date);
                        System.out.println(trip_distance);
                        System.out.println(date);
                    }
                    ArrayAdapter arrayAdapter=new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,show_list2);
                    listView.setAdapter(arrayAdapter);
                }
            }
        });*/
        //System.out.println(tripdata.orderBy("trip_distance").limit(5));
    }
    public void Type2(View view){
        ArrayList<YellowTripData> list=new ArrayList<YellowTripData>();
        System.out.println("type2 tiklandi");
        String first_date="2020-12-05 00:00:00";
        String second_date="2020-12-10 00:00:00";

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
                    for (int i = 0; i <5 ; i++) {
                        System.out.println(list.get(i).trip_distance);
                        System.out.println(list.get(i).tpep_pickup_datetime);
                    }
                }

            }
        });

        Intent intent=new Intent(MainActivity.this,Type2.class);
        startActivity(intent);
        //System.out.println(tripdata.orderBy("trip_distance").limit(5));
    }
    public void Type3(View view){





        Intent intent=new Intent(MainActivity.this,Type3.class);
        startActivity(intent);
    }
}