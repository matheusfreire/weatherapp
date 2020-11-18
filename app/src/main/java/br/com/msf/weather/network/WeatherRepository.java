package br.com.msf.weather.network;

import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import br.com.msf.weather.BuildConfig;
import br.com.msf.weather.model.Weather;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class WeatherRepository{

    private final OkHttpClient client = new OkHttpClient().newBuilder()
                                    .connectTimeout(10, TimeUnit.SECONDS)
                                    .readTimeout(20, TimeUnit.SECONDS)
                                    .build();

    public void requestWeather(double latitude, double longitude, MutableLiveData<Weather> weatherMutableLiveData) {

        String LONG_LATI_QUERY = "%1$,.6f,%2$,.6f";
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("https")
                .host(BuildConfig.BASE_URL)
                .addPathSegment(BuildConfig.FORECAST)
                .addPathSegment(BuildConfig.KEY)
                .addPathSegment(String.format(LONG_LATI_QUERY, latitude, longitude))
                .build();

        Request request = new Request.Builder().url(httpUrl).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                weatherMutableLiveData.postValue(null);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }
                    String body = responseBody.string(); // can only call string() once or you'll get an IllegalStateException
                    JSONObject jsonObj = null;
                    try {
                        jsonObj = new JSONObject(body);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Weather weather = Weather.fromJson(jsonObj);
                    weatherMutableLiveData.postValue(weather);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
