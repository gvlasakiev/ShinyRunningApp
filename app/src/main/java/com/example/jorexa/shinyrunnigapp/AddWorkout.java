package com.example.jorexa.shinyrunnigapp;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jorexa.shinyrunnigapp.models.Workout;
import com.example.jorexa.shinyrunnigapp.repositories.FirebaseRepository;
import com.example.jorexa.shinyrunnigapp.repositories.base.Repository;
import com.google.firebase.firestore.FirebaseFirestore;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddWorkout extends Fragment implements View.OnClickListener {

    private FirebaseFirestore mDb;
    private Repository<Workout> mWorkoutsRepository;

    private EditText workoutName;
    private EditText workoutTime;
    private EditText workoutDistance;
    private Button workoutAdd;



    public AddWorkout() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_workout, container, false);

        mWorkoutsRepository = new FirebaseRepository<>(Workout.class);

        mDb = FirebaseFirestore.getInstance();

        workoutName = view.findViewById(R.id.workoutName);
        workoutTime = view.findViewById(R.id.workoutTime);
        workoutDistance = view.findViewById(R.id.workoutDistance);
        workoutAdd = view.findViewById(R.id.workoutAddButton);

        workoutAdd.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {

        //Workout activity = new Workout(workoutName.getText(), workoutDistance.getText(), workoutTime.getText());

        String name = workoutName.getText().toString();
        String distance = workoutDistance.getText().toString();
        String time = workoutTime.getText().toString();

        Workout activity = new Workout(name, distance, time);
        workoutAdd.setEnabled(false);

//        Superhero hero = new Superhero("RapKiller", "Georgi Dimitrow");
        mDb.collection("workouts").add(activity);

        Toast.makeText(getContext(), workoutName.getText()+"=>"+workoutTime.getText()+"=>"+workoutDistance.getText(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(
                getContext(),
                WorkoutsViewActivity.class
                //Show activity from this type.
        );
        startActivity(intent);

    }
}
