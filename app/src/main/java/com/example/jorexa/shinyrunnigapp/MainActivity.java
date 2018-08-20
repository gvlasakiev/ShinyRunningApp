package com.example.jorexa.shinyrunnigapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Parcelable;
import android.support.v4.app.ListFragment;
import android.widget.TextView;

import com.example.jorexa.shinyrunnigapp.models.Workout;
import com.example.jorexa.shinyrunnigapp.repositories.FirebaseRepository;
import com.example.jorexa.shinyrunnigapp.repositories.base.Repository;
import com.example.jorexa.shinyrunnigapp.uiutils.Navigator;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;

public class MainActivity extends Activity  implements Navigator{

    private FirebaseFirestore mDb;
    private Repository<Workout> mWorkoutsRepository;
    private TextView hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDb = FirebaseFirestore.getInstance();
        //hello = findViewById(R.id.hello);

        //ListWorkouts listWorkouts = ListWorkouts.newInstance();
        //listWorkouts.setNavigator(this);

        PaceFragment pace = new PaceFragment();

        //Intent intent = new Intent(this, WorkoutsViewActivity.class);

        //startActivity(intent);

        AddWorkout addWorkout = new AddWorkout();

        this.getFragmentManager()
                .beginTransaction()
                .replace(R.id.listWorkouts, pace).commit();


        mWorkoutsRepository = new FirebaseRepository<>(Workout.class);

    }

    @Override
    public void navigateWith(Workout workout) {
        Intent intent = new Intent(this, WorkoutsListActivity.class);

        intent.putExtra("workoutName", workout.name);
        intent.putExtra("workoutDistance", workout.distance);
        intent.putExtra("workoutTime", workout.time);
        intent.putExtra("workoutDate", workout.date.toString());

        startActivity(intent);
    }

}
