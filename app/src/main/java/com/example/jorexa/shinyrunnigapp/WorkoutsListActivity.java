package com.example.jorexa.shinyrunnigapp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.jorexa.shinyrunnigapp.models.Workout;
import com.example.jorexa.shinyrunnigapp.uiutils.Navigator;

public class WorkoutsListActivity extends Activity {


    private TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts_list);

        Intent intent = getIntent();
        String workoutName = intent.getStringExtra("workoutName");
        String workoutDistance = intent.getStringExtra("workoutDistance");
        String workoutTime = intent.getStringExtra("workoutTime");
        String workoutDate = intent.getStringExtra("workoutDate");

        //Workout w = (Workout)intent.getSerializableExtra("workoutName");

        //info = findViewById(R.id.info);

        //info.setText(workoutDate+" =>"+workoutDistance+" =>"+workoutTime+" =>"+workoutName);

        WorkoutDetailsFragment detailWorkouts = WorkoutDetailsFragment
                .newInstance(workoutName,workoutDistance, workoutTime, workoutDate);

        //Intent intent = new Intent(this, WorkoutsViewActivity.class);

        //startActivity(intent);

        //AddWorkout addWorkout = new AddWorkout();

        this.getFragmentManager()
                .beginTransaction()
                .replace(R.id.listWorkouts, detailWorkouts).commit();

        //ListWorkouts listWorkouts = ListWorkouts.newInstance();
        //listWorkouts.setNavigate()
        //listWorkouts.setNavigator(this);


    }
}
