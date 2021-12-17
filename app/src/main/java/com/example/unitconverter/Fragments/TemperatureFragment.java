package com.example.unitconverter.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unitconverter.R;

public class TemperatureFragment extends Fragment {
    EditText valueTxtView;
    AutoCompleteTextView convertFromView;
    AutoCompleteTextView convertToView;
    TextView resultTxtView;
    Button calculateBtnView;
    TextView labelResultView;
    Float givenValue = 0f;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_temperature, container, false);

        valueTxtView = view.findViewById(R.id.valueTxt);
        convertFromView = view.findViewById(R.id.convertFrom);
        convertToView = view.findViewById(R.id.convertTo);
        resultTxtView = view.findViewById(R.id.resultTxt);
        calculateBtnView = view.findViewById(R.id.calculateBtn);
        labelResultView = view.findViewById(R.id.labelResult);


        String[] speedUnits = getResources().getStringArray(R.array.temperature_units);
        ArrayAdapter arrayAdapter = new ArrayAdapter(requireContext(), R.layout.dropdown_view, speedUnits);
        convertFromView.setAdapter(arrayAdapter);
        convertToView.setAdapter(arrayAdapter);


        calculateBtnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult(
                        convertFromView.getText().toString(),
                        convertToView.getText().toString(),
                        valueTxtView.getText().toString());
            }
        });

        setResultVisiblity(false);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        String[] speedUnits = getResources().getStringArray(R.array.temperature_units);
        ArrayAdapter arrayAdapter = new ArrayAdapter(requireContext(), R.layout.dropdown_view, speedUnits);
        convertFromView.setAdapter(arrayAdapter);
        convertToView.setAdapter(arrayAdapter);
    }

    private void calculateResult(String from, String to, String value) {


        if (validateInputs(from, to, value)) return;

        givenValue = Float.parseFloat(value);
        Float resultValue = 0f;


        if ((from + to).equalsIgnoreCase(getResources().getStringArray(R.array.temperature_units)[0] + getResources().getStringArray(R.array.temperature_units)[1]))
            resultValue = givenValue - 273.15f;
        else if ((from + to).equalsIgnoreCase(getResources().getStringArray(R.array.temperature_units)[0] + getResources().getStringArray(R.array.temperature_units)[2]))
            resultValue = (givenValue - 273.15f) * 9/5 + 32;


        else if ((from + to).equalsIgnoreCase(getResources().getStringArray(R.array.temperature_units)[1] + getResources().getStringArray(R.array.temperature_units)[0]))
            resultValue = givenValue + 273.15f;
        else if ((from + to).equalsIgnoreCase(getResources().getStringArray(R.array.temperature_units)[1] + getResources().getStringArray(R.array.temperature_units)[2]))
            resultValue = (givenValue * 9/5) + 32;


        else if ((from + to).equalsIgnoreCase(getResources().getStringArray(R.array.temperature_units)[2] + getResources().getStringArray(R.array.temperature_units)[0]))
            resultValue = (givenValue - 32) * 5/9 + 273.15f;
        else if ((from + to).equalsIgnoreCase(getResources().getStringArray(R.array.temperature_units)[2] + getResources().getStringArray(R.array.temperature_units)[1]))
            resultValue = (givenValue - 32) * 5/9;


        resultTxtView.setText(resultValue.toString());
        setResultVisiblity(true);

    }


    private Boolean validateInputs(String from, String to, String value) {

        Boolean isSame = false;
        Boolean isEmpty = false;

        if (from.equalsIgnoreCase(to)) {

            isSame = true;
            Toast.makeText(requireContext(), "Please choose different units to convert!", Toast.LENGTH_SHORT).show();
            setResultVisiblity(false);

        }

        if (TextUtils.isEmpty(valueTxtView.getText().toString())) {

            isEmpty = true;
            // Toast.makeText(requireContext(),"Please enter value to convert",Toast.LENGTH_SHORT).show()
            valueTxtView.setError("Enter value to convert!!");
            setResultVisiblity(false);

        }

        return isSame || isEmpty;


    }

    private void setResultVisiblity(Boolean visible) {
        if (!visible){
            resultTxtView.setVisibility(View.INVISIBLE);
            labelResultView.setVisibility(View.INVISIBLE);
        }else{
            resultTxtView.setVisibility(View.VISIBLE);
            labelResultView.setVisibility(View.VISIBLE);
        }
    }
}