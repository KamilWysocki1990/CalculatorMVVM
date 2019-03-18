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

    String temporaryString;

    public ObservableField<String> updateTheViewLine;

    public ObservableField<String> getUpdateTheViewLine() {
        return updateTheViewLine;
    }


    private SupportedOperation supportedOperation;

    public void init(){
        supportedOperation = new SupportedOperation();
        updateTheViewLine = new ObservableField<String>();
    }

    public CalcViewModel() {
        updateTheViewLine = new ObservableField<String>();
    }

    public void onClickCalc(char symbol){
        if(updateTheViewLine.get()!=null)
            updateTheViewLine.set(updateTheViewLine.get()+String.valueOf(symbol));
        else{
            updateTheViewLine.set(String.valueOf(symbol));
        }
    }

    public void onClickBack(){
        if(updateTheViewLine.get()!=null)
           temporaryString = updateTheViewLine.get();
        temporaryString = temporaryString.replace(temporaryString.substring(temporaryString.length()-1),"");
            updateTheViewLine.set(temporaryString);
        }
    }



