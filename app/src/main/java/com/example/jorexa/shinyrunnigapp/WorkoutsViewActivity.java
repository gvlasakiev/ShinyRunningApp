package com.example.jorexa.shinyrunnigapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

import com.example.jorexa.shinyrunnigapp.models.Workout;
import com.example.jorexa.shinyrunnigapp.repositories.base.Repository;
import com.example.jorexa.shinyrunnigapp.uiutils.Navigator;
import com.google.firebase.firestore.FirebaseFirestore;

public class WorkoutsViewActivity extends Activity implements Navigator {

    private FirebaseFirestore mDb;
    private Repository<Workout> mWorkoutsRepository;
    private TextView hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDb = FirebaseFirestore.getInstance();
        //hello = findViewById(R.id.hello);

        ListWorkouts listWorkouts = ListWorkouts.newInstance();
        listWorkouts.setNavigator(this);

        //AddWorkout addWorkout = new AddWorkout();

        this.getFragmentManager()
                .beginTransaction()
                .replace(R.id.listWorkouts, listWorkouts).commit();


        //mWorkoutsRepository = new FirebaseRepository<>(Workout.class);

        //mWorkoutsRepository.getAll( workouts -> {
        //for (Workout activity: workouts) {
        // hello.setText(activity.date.toString());
        // int b = 5;

        //    }
        //});

    }

    @Override
    public void navigateWith(Workout workout) {
        Intent intent = new Intent(this, WorkoutsListActivity.class);

        intent.putExtra("workoutName", (Parcelable) workout);

        startActivity(intent);
    }
}
