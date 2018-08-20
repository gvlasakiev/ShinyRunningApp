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

        if (!name.isEmpty() && !distance.isEmpty() && !time.isEmpty()) {

        Workout activity = new Workout(name, distance, time);
        workoutAdd.setEnabled(false);

        mDb.collection("workouts").add(activity);

        Toast.makeText(getContext(), "Successful recording!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(
                getContext(),
                WorkoutsViewActivity.class
        );
        startActivity(intent);

        } else {
            Toast.makeText(getContext(), "Fill in all fields!", Toast.LENGTH_SHORT).show();
        }
    }
}
