package com.dimaoprog.sportsconnectivity.profileViews;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.dimaoprog.sportsconnectivity.Converter;
import com.dimaoprog.sportsconnectivity.dbEntities.UserMeasurements;
import com.dimaoprog.sportsconnectivity.dbRepos.StatisticRepository;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import java.util.List;

public class MeasurementGraphViewModel extends AndroidViewModel {

    private StatisticRepository statisticRepo;
    private List<UserMeasurements> userMeasurements;
    private LineGraphSeries<DataPoint> series = new LineGraphSeries<>();

    public MeasurementGraphViewModel(@NonNull Application application) {
        super(application);
        statisticRepo = new StatisticRepository(application);
        userMeasurements = statisticRepo.getUserMeasurementsList();
    }

    public LineGraphSeries<DataPoint> getSeries() {
        return series;
    }

    public void setSeries(int spinnerPosition) {
        series.resetData(getDataPoints(spinnerPosition));
    }

    public DataPoint[] getDataPoints(int spinnerPosition) {
        DataPoint[] dataPoints = new DataPoint[userMeasurements.size()];
        for (int i = 0; i < userMeasurements.size(); i++) {
            dataPoints[i] = getNewDataPoint(i, spinnerPosition);
        }
        return dataPoints;
    }

    public DataPoint getNewDataPoint(int countInList, int spinnerPosition) {
        UserMeasurements tempMeasurement = userMeasurements.get(countInList);
        DataPoint newDataPoint = null;
        switch (spinnerPosition) {
            case 0:
                newDataPoint = new DataPoint(Converter.dateToDayInt(tempMeasurement.getDateOfMeasurement()), tempMeasurement.getWeightInKG());
                break;
            case 1:
                newDataPoint = new DataPoint(Converter.dateToDayInt(tempMeasurement.getDateOfMeasurement()), tempMeasurement.getWaistGirthInCM());
                break;
            case 2:
                newDataPoint = new DataPoint(Converter.dateToDayInt(tempMeasurement.getDateOfMeasurement()), tempMeasurement.getNeckGirthInCM());
                break;
            case 3:
                newDataPoint = new DataPoint(Converter.dateToDayInt(tempMeasurement.getDateOfMeasurement()), tempMeasurement.getHipGirthInCM());
                break;
            case 4:
                newDataPoint = new DataPoint(Converter.dateToDayInt(tempMeasurement.getDateOfMeasurement()), tempMeasurement.getBodyFat());
                break;
            case 5:
                newDataPoint = new DataPoint(Converter.dateToDayInt(tempMeasurement.getDateOfMeasurement()), tempMeasurement.getBmi());
                break;
            case 6:
                newDataPoint = new DataPoint(Converter.dateToDayInt(tempMeasurement.getDateOfMeasurement()), tempMeasurement.getHeightInCM());
                break;
        }
        return newDataPoint;
    }

    public List<UserMeasurements> getUserMeasurements() {
        return userMeasurements;
    }

    public void setUserMeasurements(List<UserMeasurements> userMeasurements) {
        this.userMeasurements = userMeasurements;
    }
}
