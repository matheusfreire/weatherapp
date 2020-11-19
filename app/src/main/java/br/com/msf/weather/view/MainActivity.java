package br.com.msf.weather.view;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.test.espresso.IdlingResource;

import org.jetbrains.annotations.NotNull;

import br.com.msf.weather.R;
import br.com.msf.weather.databinding.ActivityMainBinding;
import br.com.msf.weather.viewmodel.WeatherViewModel;

public class MainActivity extends AppCompatActivity {

    private SimpleIdlingResource mIdlingResource;

    private ActivityMainBinding viewDataBinding;

    private WeatherViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewDataBinding.txtEditLong.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                boolean isValid = validateInputs();
                if(isValid){
                    if (mIdlingResource != null) {
                        mIdlingResource.setIdleState(false);
                    }
                    hideKeyboard();
                    viewDataBinding.progressBar.setVisibility(View.VISIBLE);
                    viewModel.requestWeather(getDoubleFromText(viewDataBinding.txtEditLati.getText()), getDoubleFromText(viewDataBinding.txtEditLong.getText()));
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
                mIdlingResource.setIdleState(true);
            }
            viewDataBinding.progressBar.setVisibility(View.GONE);
            viewDataBinding.txtViewError.setVisibility(weather == null ? View.VISIBLE : View.GONE);
            viewDataBinding.scrollInfo.setVisibility(weather != null ? View.VISIBLE : View.GONE);
            viewDataBinding.setWeather(weather);
        });
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(viewDataBinding.txtInputLong.getWindowToken(), 0);
    }

    private boolean validateInputs() {
        if(viewDataBinding.txtEditLati.getText() == null || isTextEmpty(viewDataBinding.txtEditLati.getText())){
            viewDataBinding.txtInputLati.setError(getString(R.string.provide_value));
            return false;
        }
        viewDataBinding.txtInputLati.setError(null);
        if(viewDataBinding.txtEditLong.getText() == null || isTextEmpty(viewDataBinding.txtEditLong.getText())){
            viewDataBinding.txtInputLong.setError(getString(R.string.provide_value));
            return false;
        }
        viewDataBinding.txtInputLong.setError(null);
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