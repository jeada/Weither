package com.weather;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import org.json.JSONException;
import android.os.AsyncTask;
import java.util.ArrayList;


public class WeatherActivity extends ActionBarActivity {

    ListView listCities;

    String cityOttawa , cityOttawaTemperatures;
    String weatherOttawa , maxweatherOttawa, minweatherOttawa;
    String cityToronto, cityTorontoTemperatures;
    String weatherToronto , maxweatherToronto, minweatherToronto;
    String cityMontreal, cityMontrealTemperatures;
    String weatherMontreal , maxweatherMontreal, minweatherMontreal;
    String cityCalgary, cityCalgaryTemperatures;
    String weatherCalgary, maxweatherCalgary, minweatherCalgary;
    String cityVancouver, cityVancouverTemperatures;
    String weatherVancouver, maxweatherVancouver, minweatherVancouver;
    String cityHalifax, cityHalifaxTemperatures;
    String weatherHalifax, maxweatherHalifax, minweatherHalifax;
    int Nville = 6;
    int OTTAWA = 0;
    int TORONTO= 1;
    int MONTREAL = 2;
    int CALGARY = 3;
    int VANCOUVER = 4;
    int HALIFAX = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_weither);
         listCities = (ListView) findViewById(R.id.listCity);

         cityOttawa = "ottawa,CA";
         cityToronto = "Toronto,CA";
         cityMontreal = "Montreal,CA";
         cityCalgary = "Calgary,CA";
         cityVancouver = "Vancouver,CA";
         cityHalifax = "Halifax,CA";

        JSONWeatherTask task = new JSONWeatherTask();

        task.execute(new String[]{cityOttawa, cityToronto, cityMontreal, cityCalgary, cityVancouver, cityHalifax});

     }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_weither, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class JSONWeatherTask extends AsyncTask<String, Void, ArrayList<Weather>> {
        @Override

        protected ArrayList<Weather> doInBackground(String... params) {

            ArrayList<Weather>  weatherList = new ArrayList<Weather>();

            Weather weather;
            String data;

            for (int i=0; i< Nville; i++)
            {
                data = ((new WeatherHttpClient()).getWeatherData(params[i]));
                Log.d("My request: ",data);

                try
                {
                    weather = JSONWeatherParser.getWeather(data);
                    weatherList.add(weather);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            return weatherList;
        }

        @Override
        protected void onPostExecute(ArrayList<Weather> weatherList) {
            super.onPostExecute(weatherList);

            //String.valueOf(Math.round(weather.temperature.getTemp() - 273.15) + "ºC");

            weatherCalgary = String.valueOf(Math.round((weatherList.get(CALGARY).temperature.getTemp() - 273.15)) + "ºC");
            maxweatherCalgary = String.valueOf(Math.round((weatherList.get(CALGARY).temperature.getMaxTemp() - 273.15)) + "ºC");
            minweatherCalgary = String.valueOf(Math.round((weatherList.get(CALGARY).temperature.getMinTemp() - 273.15)) + "ºC");

            weatherHalifax = String.valueOf(Math.round((weatherList.get(HALIFAX).temperature.getTemp() - 273.15)) + "ºC");
            maxweatherHalifax = String.valueOf(Math.round((weatherList.get(HALIFAX).temperature.getMaxTemp() - 273.15)) + "ºC");
            minweatherHalifax = String.valueOf(Math.round((weatherList.get(HALIFAX).temperature.getMinTemp() - 273.15)) + "ºC");

            weatherMontreal = String.valueOf(Math.round((weatherList.get(MONTREAL).temperature.getTemp() - 273.15)) + "ºC");
            maxweatherMontreal = String.valueOf(Math.round((weatherList.get(MONTREAL).temperature.getMaxTemp() - 273.15)) + "ºC");
            minweatherMontreal = String.valueOf(Math.round((weatherList.get(MONTREAL).temperature.getMinTemp() - 273.15)) + "ºC");

            weatherToronto = String.valueOf(Math.round((weatherList.get(TORONTO).temperature.getTemp() - 273.15)) + "ºC");
            maxweatherToronto = String.valueOf(Math.round((weatherList.get(TORONTO).temperature.getMaxTemp() - 273.15)) + "ºC");
            minweatherToronto = String.valueOf(Math.round((weatherList.get(TORONTO).temperature.getMinTemp() - 273.15)) + "ºC");

            weatherOttawa = String.valueOf(Math.round((weatherList.get(OTTAWA).temperature.getTemp() - 273.15)) + "ºC");
            maxweatherOttawa = String.valueOf(Math.round((weatherList.get(OTTAWA).temperature.getMaxTemp() - 273.15)) + "ºC");
            minweatherOttawa = String.valueOf(Math.round((weatherList.get(OTTAWA).temperature.getMinTemp() - 273.15)) + "ºC");

            weatherVancouver = String.valueOf(Math.round((weatherList.get(VANCOUVER).temperature.getTemp() - 273.15)) + "ºC");
            maxweatherVancouver = String.valueOf(Math.round((weatherList.get(VANCOUVER).temperature.getMaxTemp() - 273.15)) + "ºC");
            minweatherVancouver = String.valueOf(Math.round((weatherList.get(VANCOUVER).temperature.getMinTemp() - 273.15)) + "ºC");


            cityCalgary = "Calgary";
            cityCalgaryTemperatures = "\n"+cityCalgary+"\nCurrent Temperature: "+weatherCalgary+
                          "\nMax Temperature: "+maxweatherCalgary+
                          "\nMin Temperature: "+minweatherCalgary+"\n";

            cityHalifax = "Halifax";
            cityHalifaxTemperatures="\n"+cityHalifax+"\nCurrent Temperature: "+weatherHalifax+
                        "\nMax Temperature: "+maxweatherHalifax+
                        "\nMin Temperature: "+minweatherHalifax+"\n";

            cityToronto="Toronto";
            cityTorontoTemperatures = "\n"+cityToronto+"\nCurrent Temperature: "+weatherToronto+
                        "\nMax Temperature: "+maxweatherToronto+
                        "\nMin Temperature: "+minweatherToronto+"\n";

            cityOttawa="Ottawa";
            cityOttawaTemperatures="\n"+cityOttawa+"\nCurrent Temperature: "+weatherOttawa+
                        "\nMax Temperature: "+maxweatherOttawa+
                        "\nMin Temperature: "+minweatherOttawa+"\n";

            cityVancouver="Vancouver";
            cityVancouverTemperatures="\n"+cityVancouver+"\nCurrent Temperature: "+weatherVancouver+
                          "\nMax Temperature: "+maxweatherVancouver+
                          "\nMin Temperature: "+minweatherVancouver+"\n";

            cityMontreal="Montreal";
            cityMontrealTemperatures="\n"+cityMontreal+"\nCurrent Temperature: "+weatherMontreal+
                         "\nMax Temperature: "+maxweatherMontreal+
                         "\nMin Temperature: "+minweatherMontreal+"\n";

            String[] cities = new String[]{ cityOttawaTemperatures, cityTorontoTemperatures,cityMontrealTemperatures,
                    cityCalgaryTemperatures,cityVancouverTemperatures,cityHalifaxTemperatures};

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(WeatherActivity.this,
                    android.R.layout.simple_list_item_1, cities);
            listCities.setAdapter(adapter);

        }
    }

}
