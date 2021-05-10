package com.example.mobilsorgular;

public class YellowTripData {
    public String VendorID;
    public String tpep_pickup_datetime;
    public String  tpep_dropoff_datetime;
    public String  passenger_count;
    public String trip_distance;
    public String RatecodeID;
    public String  store_and_fwd_flag;
    public String  PULocationID;
    public String  DOLocationID;
    public String  payment_type;
    public String  fare_amount;
    public String  extra;
    public String  mta_tax;
    public String  tip_amount;
    public String  tolls_amount;
    public String  improvement_surcharge;
    public String  total_amount;
    public String  congestion_surcharge;

    public YellowTripData(String vendorID, String tpep_pickup_datetime, String tpep_dropoff_datetime, String passenger_count, String trip_distance, String ratecodeID, String store_and_fwd_flag, String PULocationID, String DOLocationID, String payment_type, String fare_amount, String extra, String mta_tax, String tip_amount, String tolls_amount, String improvement_surcharge, String total_amount, String congestion_surcharge) {
        VendorID = vendorID;
        this.tpep_pickup_datetime = tpep_pickup_datetime;
        this.tpep_dropoff_datetime = tpep_dropoff_datetime;
        this.passenger_count = passenger_count;
        this.trip_distance = trip_distance;
        RatecodeID = ratecodeID;
        this.store_and_fwd_flag = store_and_fwd_flag;
        this.PULocationID = PULocationID;
        this.DOLocationID = DOLocationID;
        this.payment_type = payment_type;
        this.fare_amount = fare_amount;
        this.extra = extra;
        this.mta_tax = mta_tax;
        this.tip_amount = tip_amount;
        this.tolls_amount = tolls_amount;
        this.improvement_surcharge = improvement_surcharge;
        this.total_amount = total_amount;
        this.congestion_surcharge = congestion_surcharge;
    }
}
