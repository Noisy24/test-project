package com.example.testproject;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.List;
import java.util.Map;

public class StatsWorkout {

    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    public void initialize() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Объём тренировок");

        for (Workout workout : WorkoutStorage.getAllWorkouts()) {
            double volume = workout.getExercises().stream()
                    .mapToDouble(ex -> ex.getSets() * ex.getReps() * ex.getWeight())
                    .sum();

            series.getData().add(new XYChart.Data<>(workout.getDate().toString(), volume));
        }

        lineChart.getData().add(series);
    }
}