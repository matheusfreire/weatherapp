package br.com.msf.weather.viewmodel;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import br.com.msf.weather.model.Currently;
import br.com.msf.weather.model.Weather;
import br.com.msf.weather.network.WeatherRepository;

public class WeatherViewModel extends ViewModel{

    private Weather weather;
    private final WeatherRepository weatherRepository  = new WeatherRepository();

    private LiveData<Weather> weatherLiveData;

    public LiveData<Weather> getWeatherLiveData() {
        return weatherLiveData;
    }

    public void requestWeather(double latitude, double longitude){
        weatherLiveData = weatherRepository.requestWeather(latitude, longitude);
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public Weather getWeather() {
        return weather;
    }

    public int getTzVisibility(){
        return weather == null || stringEmpty(weather.getTimezone())
                ? View.GONE
                : View.VISIBLE;
    }

    private boolean stringEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public int getSummaryVisibility() {
        return getCurrently() == null || stringEmpty(getCurrently().getSummary())
                ? View.GONE
                : View.VISIBLE;
    }

    public int getPrecipIntensityVisibility() {
        return getCurrently() == null || getCurrently().getPrecipIntensity() == null
                ? View.GONE
                : View.VISIBLE;
    }

    public int getPrecipProbabilityVisibility() {
        return getCurrently() == null || getCurrently().getPrecipProbability() == null
                ? View.GONE
                : View.VISIBLE;
    }

    public int getApparentTemperatureVisibility() {
        return getCurrently() == null || getCurrently().getApparentTemperature() == null
                ? View.GONE
                : View.VISIBLE;
    }

    public int getHumidityVisibility() {
        return getCurrently() == null || getCurrently().getHumidity() == null
                ? View.GONE
                : View.VISIBLE;
    }

    public int getPressureVisibility() {
        return getCurrently() == null || getCurrently().getPressure() == null
                ? View.GONE
                : View.VISIBLE;
    }

    public int getPrecipeTypeVisibility() {
        return getCurrently() == null || stringEmpty(getCurrently().getPrecipType())
                ? View.GONE
                : View.VISIBLE;
    }

    public Currently getCurrently() {
        if(weather == null){
            return null;
        }
        return weather.getCurrently();
    }
}
