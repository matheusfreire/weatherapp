package br.com.msf.weather.view;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.test.espresso.IdlingResource;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import br.com.msf.weather.R;
import br.com.msf.weather.viewmodel.WeatherViewModel;

public class MainActivity extends AppCompatActivity {

    private SimpleIdlingResource mIdlingResource;

    private WeatherViewModel viewModel;
    private FrameLayout frameLayoutProgress;
    private TextInputLayout inputLayoutLong;
    private TextInputLayout inputLayoutLati;
    private TextInputEditText txtLong;
    private TextInputEditText txtLati;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        frameLayoutProgress = findViewById(R.id.progressBar);
        txtLong = findViewById(R.id.txtEditLong);
        txtLati = findViewById(R.id.txtEditLati);
        inputLayoutLong = findViewById(R.id.txtInputLong);
        inputLayoutLati = findViewById(R.id.txtInputLati);
    }

    @Override
    protected void onResume() {
        super.onResume();
        txtLong.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                boolean isValid = validateInputs();
                if(isValid){
                    if (mIdlingResource != null) {
                        mIdlingResource.setIdleState(false);
                    }
                    hideKeyboard();
                    frameLayoutProgress.setVisibility(View.VISIBLE);
                    viewModel.requestWeather(getDoubleFromText(txtLati.getText()), getDoubleFromText(txtLong.getText()));
                    createObserver();
                }
                return isValid;
            }
            return false;
        });
    }

    private void createObserver(){
        viewModel.getWeatherLiveData().observe(this, weather -> {
            if (mIdlingResource != null) {
                mIdlingResource.setIdleState(false);
            }
            frameLayoutProgress.setVisibility(View.GONE);
            Log.d("MainActivity", weather.toString());
        });
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(txtLati.getWindowToken(), 0);
    }

    private boolean validateInputs() {
        if(txtLong.getText() == null || isTextEmpty(txtLong.getText())){
            inputLayoutLong.setError(getString(R.string.provide_value));
            return false;
        }
        inputLayoutLong.setError(null);
        if(txtLati.getText() == null || isTextEmpty(txtLati.getText())){
            inputLayoutLati.setError(getString(R.string.provide_value));
            return false;
        }
        inputLayoutLati.setError(null);
        return true;
    }

    private double getDoubleFromText(@NotNull Editable text){
        return Double.parseDouble(text.toString());
    }

    private boolean isTextEmpty(@NotNull Editable text){
        return text.toString().isEmpty();
    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }
}