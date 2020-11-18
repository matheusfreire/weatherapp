package br.com.msf.weather.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import br.com.msf.weather.model.Weather;
import br.com.msf.weather.network.WeatherRepository;

public class WeatherViewModel extends ViewModel{

    private WeatherRepository weatherRepository;

    private final MutableLiveData<Weather> weatherMutableLiveData = new MutableLiveData<>();
    private LiveData<Weather> weatherLiveData = weatherMutableLiveData;

    public LiveData<Weather> getWeatherLiveData() {
        return weatherLiveData;
    }

    public void requestWeather(double latitude, double longitude){
        weatherRepository = new WeatherRepository();
        weatherRepository.requestWeather(latitude, longitude, weatherMutableLiveData);
    }

}
