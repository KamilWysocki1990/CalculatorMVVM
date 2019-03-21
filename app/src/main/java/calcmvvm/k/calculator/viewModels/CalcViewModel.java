package calcmvvm.k.calculator.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.databinding.BaseObservable;
import android.databinding.Observable;
import android.databinding.ObservableChar;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.view.View;

import calcmvvm.k.calculator.model.SupportedOperation;

public class CalcViewModel extends ViewModel {

    public ObservableField<String> updateTheViewLine;

    public ObservableField<String> getUpdateTheViewLine() {
        return updateTheViewLine;
    }

    private SupportedOperation supportedOperation;

    public void init() {
        supportedOperation = new SupportedOperation();
        updateTheViewLine = new ObservableField<>();
    }

    public CalcViewModel() {
        updateTheViewLine = new ObservableField<>();
    }

    public void onClickCalc(char symbol) {
        if (updateTheViewLine.get() != null)
            updateTheViewLine.set(updateTheViewLine.get() + String.valueOf(symbol));
        else {
            updateTheViewLine.set(String.valueOf(symbol));
        }
    }


    public void onClickBack() {
        if (updateTheViewLine.get() != null) {
            String temporaryString = updateTheViewLine.get();
            if (temporaryString.length() > 0)
                temporaryString = updateTheViewLine.get().substring(0, updateTheViewLine.get().length() - 1);

            updateTheViewLine.set(temporaryString);
            temporaryString = "";
        }
    }

    public void onClickAC() {
        updateTheViewLine.set("");
    }

    public void onClickGetResult(){
        updateTheViewLine.set(supportedOperation.makeCalculation(updateTheViewLine.get()));
    }




}



