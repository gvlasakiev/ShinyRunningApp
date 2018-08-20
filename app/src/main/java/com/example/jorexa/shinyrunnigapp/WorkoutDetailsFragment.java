package com.example.jorexa.shinyrunnigapp;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutDetailsFragment extends Fragment {


    private static String workoutName;
    private static String workoutDistance;
    private static String workoutTime;
    private static String workoutDate;

    private TextView tv_workoutName;
    private TextView tv_workoutDistance;
    private TextView tv_workoutDate;
    private TextView tv_workoutTime;

    public WorkoutDetailsFragment() {
        // Required empty public constructor
    }

    public static WorkoutDetailsFragment newInstance(String name, String distance, String time, String date) {
        workoutName = name;
        workoutDistance = distance;
        workoutTime = time;
        workoutDate = date;

        return new WorkoutDetailsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_workout_details, container, false);

       tv_workoutName = view.findViewById(R.id.workoutName);
       tv_workoutDistance = view.findViewById(R.id.workoutDistance);
       tv_workoutDate = view.findViewById(R.id.workoutDate);
       tv_workoutTime = view.findViewById(R.id.workoutTime);

        tv_workoutName.setText("Name: "+workoutName);
        tv_workoutDistance.setText("Distance: "+workoutDistance);
        tv_workoutDate.setText("Date: "+workoutDate);
        tv_workoutTime.setText("Time: "+workoutTime);

       return view;
    }

}
