package br.com.msf.weather.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Weather implements Parcelable {

    private double latitude;
    private double longitude;
    private String timezone;
    private Currently currently;

    public final static Parcelable.Creator<Weather> CREATOR = new Creator<Weather>() {
        @SuppressWarnings({
                "unchecked"
        })
        public Weather createFromParcel(Parcel in) {
            return new Weather(in);
        }

        public Weather[] newArray(int size) {
            return (new Weather[size]);
        }

    };

    protected Weather(Parcel in) {
        this.latitude = ((double) in.readValue((double.class.getClassLoader())));
        this.longitude = ((double) in.readValue((double.class.getClassLoader())));
        this.timezone = ((String) in.readValue((String.class.getClassLoader())));
        this.currently = ((Currently) in.readValue((Currently.class.getClassLoader())));
    }

    public Weather() {
    }

    public static Weather fromJson(JSONObject jsonObj) throws JSONException {
        Weather weather = new Weather();
        if(jsonObj.has("latitude")){
            weather.setLatitude(jsonObj.getDouble("latitude"));
        }
        if(jsonObj.has("longitude")){
            weather.setLongitude(jsonObj.getDouble("longitude"));
        }
        if(jsonObj.has("timezone")){
            weather.setTimezone(jsonObj.getString("timezone"));
        }
        if(jsonObj.has("currently")){
            weather.setCurrently(Currently.fromJson(jsonObj.getJSONObject("currently")));
        }
        return weather;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Currently getCurrently() {
        return currently;
    }

    public void setCurrently(Currently currently) {
        this.currently = currently;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(latitude);
        dest.writeValue(longitude);
        dest.writeValue(timezone);
        dest.writeValue(currently);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", timezone='" + timezone + '\'' +
                ", currently=" + currently +
                '}';
    }
}