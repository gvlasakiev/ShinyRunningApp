package com.example.jorexa.shinyrunnigapp;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jorexa.shinyrunnigapp.models.Workout;
import com.example.jorexa.shinyrunnigapp.repositories.FirebaseRepository;
import com.example.jorexa.shinyrunnigapp.repositories.base.Repository;
import com.example.jorexa.shinyrunnigapp.uiutils.Navigator;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListWorkouts extends Fragment implements AdapterView.OnItemClickListener {

    private FirebaseFirestore mDb;
    private Repository<Workout> mWorkoutsRepository;

    private ListView mWorkoutsView;
    private ArrayAdapter<String> mWorkoutsAdapter;
    private Navigator mNavigator;

    public static ArrayList<Workout> AllWorkouts;

    public ListWorkouts() {
        // Required empty public constructor
    }

    //Fektori methods

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_workouts, container, false);

        mWorkoutsView = view.findViewById(R.id.lv_workouts);

        mWorkoutsAdapter = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_list_item_1
        );


        mWorkoutsRepository = new FirebaseRepository<>(Workout.class);

        AllWorkouts = new ArrayList<>();

        mWorkoutsRepository.getAll( workouts -> {
            int id = 0;
        for (Workout activity: workouts) {
            AllWorkouts.add(activity);
            String[] splitDate = activity.date.toString().split(" ");
            String viewDate = splitDate[2]+" "+splitDate[1]+" "+splitDate[5];
            mWorkoutsAdapter.add((id+1)+". "+activity.name+" ("+viewDate+")");
            id++;
            }
        });

        mWorkoutsView.setAdapter(mWorkoutsAdapter);

        mWorkoutsView.setOnItemClickListener(this);

        TextView myView = view.findViewById(R.id.myview);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String workout = mWorkoutsAdapter.getItem(i);
        mNavigator.navigateWith(AllWorkouts.get(i));
    }

    public static ListWorkouts newInstance() {
        return new ListWorkouts();
    }

    public void setNavigator(Navigator navigator) {
        mNavigator = navigator;
    }

}