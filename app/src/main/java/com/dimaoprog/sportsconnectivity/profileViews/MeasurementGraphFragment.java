package com.dimaoprog.sportsconnectivity.profileViews;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.databinding.FragmentMeasurementGraphBinding;

public class MeasurementGraphFragment extends Fragment {

    private MeasurementGraphViewModel mgViewModel;
    private FragmentMeasurementGraphBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mgViewModel = ViewModelProviders.of(this).get(MeasurementGraphViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_measurement_graph, container, false);
        setupSpinner();
        binding.graphMeasurement.addSeries(mgViewModel.getSeries());
        return binding.getRoot();
    }

    private void setupSpinner() {
        String[] measurementsList = getResources().getStringArray(R.array.measurements_name);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, measurementsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerMeasurements.setAdapter(adapter);
        binding.spinnerMeasurements.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mgViewModel.setSeries(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
