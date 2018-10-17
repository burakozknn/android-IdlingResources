package com.gmail.burakozknn.espresso;

import android.os.AsyncTask;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CountingIdlingResource idlingResource = new CountingIdlingResource("DATA_LOADER");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );


    }

    @Override
    protected void onResume() {
        super.onResume();

        //((TextView)findViewById( R.id.main_text )).setText( R.string.data_loaded );

        /*
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep( 5000 );
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                ((TextView)findViewById( R.id.main_text )).setText( R.string.data_loaded );
            }
        }.execute();

        */

        idlingResource.increment();
        new Thread( new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep( 5000 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread( new Runnable() {
                    @Override
                    public void run() {
                        ((TextView) findViewById( R.id.main_text )).setText( R.string.data_loaded );
                        idlingResource.decrement();
                    }
                } );

            }
        } ).start();


    }
}
