package com.example.mobilsorgular;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class Type3 extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    EditText day_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type3);
        firebaseFirestore= FirebaseFirestore.getInstance();
        day_text=findViewById(R.id.day_text);
    }
    public void Show(View view){

        ArrayList<YellowTripData> list=new ArrayList<YellowTripData>();
        String day_string=day_text.getText().toString();

        String first_date="2020-12-"+day_string+" 00:00:00";

        int gun=Integer.parseInt(day_string)+1;
        System.out.println("SA"+gun);
        String second_date;
        if(gun>9){
            second_date="2020-12-"+String.valueOf(gun)+" 00:00:00";
        }
        else{
            second_date="2020-12-"+"0"+String.valueOf(gun)+" 00:00:00";
        }

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
                            if (Double.parseDouble(list.get(i).trip_distance) < Double.parseDouble(list.get(j).trip_distance)) {

                                YellowTripData tmp = list.get(i);

                                list.set(i,list.get(j)) ;
                                list.set(j,tmp);

                            }

                        }

                    }
                    System.out.println("CAYLARRRRRRRRRRRRRRRRRRRRRR");

                    System.out.println(list.get(0).trip_distance);
                    System.out.println(list.get(0).PULocationID);
                    System.out.println(list.get(0).DOLocationID);
                    System.out.println(list.get(0).tpep_pickup_datetime);

                    CollectionReference zone_lookup = firebaseFirestore.collection("taxi+_zone_lookup");
                    zone_lookup.whereEqualTo("LocationID",Integer.parseInt(list.get(0).PULocationID)).addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                            if(value!=null){
                                for(DocumentSnapshot snapshot : value.getDocuments()){
                                    Map<String, Object> data= snapshot.getData();

                                    Locations.pu_location_name=(String) data.get("Borough")+" "+(String) data.get("Zone");
                                    System.out.println(Locations.pu_location_name);
                                }

                            }
                        }
                    });

                    zone_lookup.whereEqualTo("LocationID",Integer.parseInt(list.get(0).DOLocationID)).addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                            if(value!=null){
                                for(DocumentSnapshot snapshot : value.getDocuments()){
                                    Map<String, Object> data= snapshot.getData();

                                    Locations.do_location_name=(String) data.get("Borough")+" "+(String) data.get("Zone");
                                    System.out.println(Locations.do_location_name);
                                }
                                Toast.makeText(Type3.this,Locations.pu_location_name+"\n"+
                                        Locations.do_location_name+"\n"+
                                        list.get(0).trip_distance+"\n"+
                                list.get(0).PULocationID+"\n"+
                                list.get(0).DOLocationID+"\n"+
                                list.get(0).tpep_pickup_datetime,Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(Type3.this,MapsActivity.class);
                                startActivity(intent);
                            }
                        }
                    });

                }

            }
        });








    }
}