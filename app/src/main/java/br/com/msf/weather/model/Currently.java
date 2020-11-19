package br.com.msf.weather.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

public class Currently implements Parcelable {

    private int time;
    private String summary;
    private String icon;
    private BigDecimal precipIntensity;
    private BigDecimal precipProbability;
    private BigDecimal temperature;
    private BigDecimal apparentTemperature;
    private BigDecimal dewPoint;
    private BigDecimal humidity;
    private BigDecimal pressure;
    private BigDecimal windSpeed;
    private BigDecimal windGust;
    private BigDecimal windBearing;
    private BigDecimal cloudCover;
    private int uvIndex;
    private BigDecimal visibility;
    private BigDecimal ozone;
    private String precipType;



    public Currently() {
    }

    protected Currently(Parcel in) {
        time = in.readInt();
        summary = in.readString();
        icon = in.readString();
        uvIndex = in.readInt();
        precipType = in.readString();
    }

    public static final Creator<Currently> CREATOR = new Creator<Currently>() {
        @Override
        public Currently createFromParcel(Parcel in) {
            return new Currently(in);
        }

        @Override
        public Currently[] newArray(int size) {
            return new Currently[size];
        }
    };

    private static boolean hasItem(JSONObject jsonObject, String key){
        return jsonObject.has(key);
    }

    public static Currently fromJson(JSONObject jsonObject) throws JSONException {
        Currently currently = new Currently();
        if(hasItem(jsonObject, "apparentTemperature")){
            currently.setApparentTemperature(BigDecimal.valueOf(jsonObject.getDouble("apparentTemperature")));
        }
        if(hasItem(jsonObject, "time")){
            currently.setTime(jsonObject.getInt("time"));
        }
        if(hasItem(jsonObject, "summary")){
            currently.setSummary(jsonObject.getString("summary"));
        }
        if(hasItem(jsonObject, "icon")){
            currently.setIcon(jsonObject.getString("icon"));
        }
        if(hasItem(jsonObject, "precipIntensity")){
            currently.setPrecipIntensity(BigDecimal.valueOf(jsonObject.getDouble("precipIntensity")));
        }
        if(hasItem(jsonObject, "precipProbability")){
            currently.setPrecipProbability(BigDecimal.valueOf(jsonObject.getDouble("precipProbability")));
        }
        if(hasItem(jsonObject, "temperature")){
            currently.setTemperature(BigDecimal.valueOf(jsonObject.getDouble("temperature")));
        }
        if(hasItem(jsonObject, "dewPoint")){
            currently.setDewPoint(BigDecimal.valueOf(jsonObject.getDouble("dewPoint")));
        }
        if(hasItem(jsonObject, "humidity")){
            currently.setHumidity(BigDecimal.valueOf(jsonObject.getDouble("humidity")));
        }
        if(hasItem(jsonObject, "pressure")){
            currently.setPressure(BigDecimal.valueOf(jsonObject.getDouble("pressure")));
        }
        if(hasItem(jsonObject, "windSpeed")){
            currently.setWindSpeed(BigDecimal.valueOf(jsonObject.getDouble("windSpeed")));
        }
        if(hasItem(jsonObject, "windGust")) {
            currently.setWindGust(BigDecimal.valueOf(jsonObject.getDouble("windGust")));
        }
        if(hasItem(jsonObject, "windBearing")){
            currently.setWindBearing(BigDecimal.valueOf(jsonObject.getDouble("windBearing")));
        }
        if(hasItem(jsonObject, "cloudCover")){
            currently.setCloudCover(BigDecimal.valueOf(jsonObject.getDouble("cloudCover")));
        }
        if(hasItem(jsonObject, "uvIndex")){
            currently.setUvIndex(jsonObject.getInt("uvIndex"));
        }
        if(hasItem(jsonObject, "visibility")){
            currently.setVisibility(BigDecimal.valueOf(jsonObject.getDouble("visibility")));
        }
        if(hasItem(jsonObject, "ozone")){
            currently.setOzone(BigDecimal.valueOf(jsonObject.getDouble("ozone")));
        }
        if(hasItem(jsonObject, "precipType")){
            currently.setPrecipType(jsonObject.getString("precipType"));
        }
        return currently;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(time);
        dest.writeValue(summary);
        dest.writeValue(icon);
        dest.writeValue(precipIntensity);
        dest.writeValue(precipProbability);
        dest.writeValue(temperature);
        dest.writeValue(apparentTemperature);
        dest.writeValue(dewPoint);
        dest.writeValue(humidity);
        dest.writeValue(pressure);
        dest.writeValue(windSpeed);
        dest.writeValue(windGust);
        dest.writeValue(windBearing);
        dest.writeValue(cloudCover);
        dest.writeValue(uvIndex);
        dest.writeValue(visibility);
        dest.writeValue(ozone);
        dest.writeValue(precipType);
    }

    public int describeContents() {
        return 0;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public BigDecimal getPrecipIntensity() {
        return precipIntensity;
    }

    public void setPrecipIntensity(BigDecimal precipIntensity) {
        this.precipIntensity = precipIntensity;
    }

    public BigDecimal getPrecipProbability() {
        return precipProbability;
    }

    public void setPrecipProbability(BigDecimal precipProbability) {
        this.precipProbability = precipProbability;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public BigDecimal getApparentTemperature() {
        return apparentTemperature;
    }

    public void setApparentTemperature(BigDecimal apparentTemperature) {
        this.apparentTemperature = apparentTemperature;
    }

    public BigDecimal getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(BigDecimal dewPoint) {
        this.dewPoint = dewPoint;
    }

    public BigDecimal getHumidity() {
        return humidity;
    }

    public void setHumidity(BigDecimal humidity) {
        this.humidity = humidity;
    }

    public BigDecimal getPressure() {
        return pressure;
    }

    public void setPressure(BigDecimal pressure) {
        this.pressure = pressure;
    }

    public BigDecimal getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(BigDecimal windSpeed) {
        this.windSpeed = windSpeed;
    }

    public BigDecimal getWindGust() {
        return windGust;
    }

    public void setWindGust(BigDecimal windGust) {
        this.windGust = windGust;
    }

    public BigDecimal getWindBearing() {
        return windBearing;
    }

    public void setWindBearing(BigDecimal windBearing) {
        this.windBearing = windBearing;
    }

    public BigDecimal getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(BigDecimal cloudCover) {
        this.cloudCover = cloudCover;
    }

    public int getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(int uvIndex) {
        this.uvIndex = uvIndex;
    }

    public String getPrecipType() {
        return precipType;
    }

    public void setPrecipType(String precipType) {
        this.precipType = precipType;
    }

    public BigDecimal getVisibility() {
        return visibility;
    }

    public void setVisibility(BigDecimal visibility) {
        this.visibility = visibility;
    }

    public BigDecimal getOzone() {
        return ozone;
    }

    public void setOzone(BigDecimal ozone) {
        this.ozone = ozone;
    }

}