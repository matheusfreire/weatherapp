package br.com.msf.weather.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import br.com.msf.weather.model.Weather;
import br.com.msf.weather.network.WeatherRepository;

public class WeatherViewModel extends ViewModel{


    {
        weatherRepository = new WeatherRepository();
    }

    private WeatherRepository weatherRepository;

    private LiveData<Weather> weatherLiveData;

    public LiveData<Weather> getWeatherLiveData() {
        return weatherLiveData;
    }

    public void requestWeather(double latitude, double longitude){
        weatherLiveData = weatherRepository.requestWeather(latitude, longitude);
    }

}
