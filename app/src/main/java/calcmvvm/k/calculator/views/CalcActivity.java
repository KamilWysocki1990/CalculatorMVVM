package calcmvvm.k.calculator.views;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import calcmvvm.k.calculator.R;
import calcmvvm.k.calculator.databinding.LayoutCalcActivityBinding;
import calcmvvm.k.calculator.viewModels.CalcViewModel;

public class CalcActivity extends AppCompatActivity {

    private CalcViewModel calcViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpBinding(savedInstanceState);
        calcViewModel.init();
    }

    private void setUpBinding(Bundle savedInstanceState){
        LayoutCalcActivityBinding activityBinding = DataBindingUtil.setContentView(this, R.layout.layout_calc_activity);
        calcViewModel = ViewModelProviders.of(this).get(CalcViewModel.class);
        if (savedInstanceState == null) {
            calcViewModel.init();
        }
        activityBinding.setCalcViewModel(calcViewModel);
       // setupButtonClick();


    }
}
