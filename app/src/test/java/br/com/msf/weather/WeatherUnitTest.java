package br.com.msf.weather;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import br.com.msf.weather.model.Currently;
import br.com.msf.weather.model.Weather;

public class WeatherUnitTest {

    @Test
    public void test_convertJson() throws JSONException {
        String jsonResult = "{\"latitude\":59.36,\"longitude\":18.07,\"timezone\":\"Europe/Stockholm\",\"currently\":{\"time\":1605740991,\"summary\":\"Overcast\",\"icon\":\"cloudy\",\"precipIntensity\":0.001,\"precipProbability\":0.06,\"precipType\":\"rain\",\"temperature\":50.44,\"apparentTemperature\":50.51,\"dewPoint\":44.85,\"humidity\":0.81,\"pressure\":998.7,\"windSpeed\":17.5,\"windGust\":38.27,\"windBearing\":214,\"cloudCover\":1,\"uvIndex\":0,\"visibility\":10,\"ozone\":260.7}}";
        JSONObject jsonObject = new JSONObject(jsonResult);
        Weather weather = Weather.fromJson(jsonObject);
        Assert.assertNotNull(weather);
        Assert.assertNotNull(weather.getCurrently());
    }

    @Test
    public void test_ExceptionThrown() {
        String jsonResult = "{\n" +
                "    \"latitude\": 59.3310373,\n" +
                "    \"longitude\": 18.0706638,\n" +
                "    \"timezone\": \"Europe/Stockholm\",\n" +
                "    \"currently\": {\n" +
                "        \"time\": 1537882620,\n" +
                "        \"summary\": \"Clear\",\n" +
                "        \"icon\": \"clear-day\",\n" +
                "        \"precipIntensity\": 0,\n" +
                "        \"precipProbability\": 0,\n" +
                "        \"temperature\": 40.46,\n" +
                "        \"apparentTemperature\": 33.75,\n" +
                "        \"dewPoint\": 29.59,\n" +
                "        \"humidity\": 0.65,\n" +
                "        \"pressure\": 1025.41,\n" +
                "        \"windSpeed\": 11.15,\n" +
                "        \"windGust\": 21.55,\n" +
                "        \"windBearing\": 295,\n" +
                "        \"cloudCover\": 0.03,\n" +
                "        \"uvIndex\": 0,\n" +
                "        \"visibility\": 8.32,\n" +
                "        \"ozone\": 321.6\n" +
                "   ";
        Assert.assertThrows(JSONException.class, () -> new JSONObject(jsonResult));
    }

    @Test
    public void test_convertJsonOkWeather() throws JSONException {
        String jsonResult = "{\"latitude\":59.36,\"longitude\":18.07,\"timezone\":\"Europe/Stockholm\",\"currently\":{\"time\":1605740991,\"summary\":\"Overcast\",\"icon\":\"cloudy\",\"precipIntensity\":0.001,\"precipProbability\":0.06,\"precipType\":\"rain\",\"temperature\":50.44,\"apparentTemperature\":50.51,\"dewPoint\":44.85,\"humidity\":0.81,\"pressure\":998.7,\"windSpeed\":17.5,\"windGust\":38.27,\"windBearing\":214,\"cloudCover\":1,\"uvIndex\":0,\"visibility\":10,\"ozone\":260.7}}";
        JSONObject jsonObject = new JSONObject(jsonResult);
        Weather weather = Weather.fromJson(jsonObject);
        Assert.assertEquals(59.36, weather.getLatitude(), 0.5);
        Assert.assertEquals(18.07, weather.getLongitude(), 0.5);
        Assert.assertEquals("Europe/Stockholm", weather.getTimezone());
    }

    @Test
    public void test_convertJsonOkCurrently() throws JSONException {
        String jsonResult = "{\"latitude\":59.36,\"longitude\":18.07,\"timezone\":\"Europe/Stockholm\",\"currently\":{\"time\":1605740991,\"summary\":\"Overcast\",\"icon\":\"cloudy\",\"precipIntensity\":0.001,\"precipProbability\":0.06," +
                "\"precipType\":\"rain\",\"temperature\":50.44,\"apparentTemperature\":50.51,\"dewPoint\":44.85,\"humidity\":0.81,\"pressure\":998.7,\"windSpeed\":17.5,\"windGust\":38.27,\"windBearing\":214,\"cloudCover\":1," +
                "\"uvIndex\":0,\"visibility\":10,\"ozone\":260.7}}";
        JSONObject jsonObject = new JSONObject(jsonResult);
        Weather weather = Weather.fromJson(jsonObject);
        Currently currently = weather.getCurrently();
        Assert.assertNotNull(currently);
        Assert.assertEquals(1605740991, currently.getTime());
        Assert.assertEquals("Overcast", currently.getSummary());
        Assert.assertEquals("cloudy", currently.getIcon());
        Assert.assertEquals(0.001, currently.getPrecipIntensity().doubleValue(), 0.001);
        Assert.assertEquals(0.06, currently.getPrecipProbability().doubleValue(), 0.01);
        Assert.assertEquals("rain", currently.getPrecipType());
        Assert.assertEquals(50.44, currently.getTemperature().doubleValue(), 0.10);
        Assert.assertEquals(50.51, currently.getApparentTemperature().doubleValue(), 0.10);
        Assert.assertEquals(44.85, currently.getDewPoint().doubleValue(), 0.5);
        Assert.assertEquals(0.81, currently.getHumidity().doubleValue(), 0.1);
        Assert.assertEquals(998.7, currently.getPressure().doubleValue(), 10);
        Assert.assertEquals(17.5, currently.getWindSpeed().doubleValue(), 0.5);
        Assert.assertEquals(38.27, currently.getWindGust().doubleValue(), 0.5);
        Assert.assertEquals(214, currently.getWindBearing().doubleValue(), 10);
        Assert.assertEquals(1, currently.getCloudCover().doubleValue(), 0);
        Assert.assertEquals(0, currently.getUvIndex());
        Assert.assertEquals(10, currently.getVisibility().doubleValue(), 1);
        Assert.assertEquals(260.7, currently.getOzone().doubleValue(), 10);
    }


}
