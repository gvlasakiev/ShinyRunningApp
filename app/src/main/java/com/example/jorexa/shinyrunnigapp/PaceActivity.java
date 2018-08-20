package com.example.jorexa.shinyrunnigapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jorexa.shinyrunnigapp.models.Workout;
import com.example.jorexa.shinyrunnigapp.repositories.base.Repository;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Locale;

public class PaceActivity extends Activity {

    private EditText mEditPaceMin;
    private EditText mEditPaceSec;
    private EditText mEditSpeed;
    private Button mClickPace;
    private Button mClickSpeed;
    private Button mClickDistance;
    private Button mButtonPace;
    private Button mBtnTime;

    private EditText mEditTimeHours;
    private EditText mEditTimeMinutes;
    private EditText mEditTimeSeconds;

    private EditText mEditPaceMinutes;
    private EditText mEditPaceSeconds;

    private EditText mEditDistance;
    private FirebaseFirestore mDb;
    private Repository<Workout> mSuperheroesRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDb = FirebaseFirestore.getInstance();

        mClickPace = findViewById(R.id.clickPace);
        mClickSpeed = findViewById(R.id.clickSpeed);

        mClickDistance = findViewById(R.id.buttonDistance);

        mEditPaceMin = findViewById(R.id.editPaceMin);
        mEditPaceSec = findViewById(R.id.editPaceSec);
        mEditSpeed = findViewById(R.id.editSpeed);

        mEditTimeHours = findViewById(R.id.editTimeHours);
        mEditTimeMinutes = findViewById(R.id.editTimeMinutes);
        mEditTimeSeconds = findViewById(R.id.editTimeSecond);

        mEditPaceMinutes = findViewById(R.id.editPaceMinutes);
        mEditPaceSeconds = findViewById(R.id.editPaceSecond);

        mEditDistance = findViewById(R.id.editDistanceKm);
        mButtonPace = findViewById(R.id.buttonPace);
        mBtnTime = findViewById(R.id.buttonTime);


        mSuperheroesRepository = new FirebaseRepository<>(Superhero.class);

        mSuperheroesRepository.getAll( superheroes -> {
            for (Superhero hero: superheroes) {
                mEditDistance.setText(hero.name);
            }
        });

        mBtnTime.setOnClickListener(
                (view) -> {
                    double paceMinutes = Double.parseDouble(mEditPaceMinutes.getText().toString());
                    double paceSeconds = Double.parseDouble(mEditPaceSeconds.getText().toString());

                    double distanceInMeters = Double.parseDouble(mEditDistance.getText().toString())*1000;
                    double paceToSpeed = 1000/(paceMinutes*60+paceSeconds);

                    double timeInHours = distanceInMeters/paceToSpeed/60/60;
                    String timeInHoursStr = String.format(Locale.US,"%05.2f", timeInHours);
                    String[] timeInHoursSplit = timeInHoursStr.split("\\.");

                    mEditTimeHours.setText(timeInHoursSplit[0]);

                    int timeInMinutes = (int)((distanceInMeters/paceToSpeed/60)%60);
                    //String timeInMinutesStr = String.format(Locale.US,"%05.2f", timeInMinutes);
                    //String[] timeInMinutesSplit = timeInMinutesStr.split("\\.");

                    int timeInSeconds = (int)((distanceInMeters/paceToSpeed)%60);
                    //String timeInSecondsStr = String.format(Locale.US,"%02.0f", timeInSeconds);
                    //String[] timeInSecondsSplit = timeInSecondsStr.split("\\.");

                    mEditTimeSeconds.setText(String.valueOf(timeInSeconds));
                    mEditTimeMinutes.setText(String.valueOf(timeInMinutes));


                    //Toast.makeText(MainActivity.this , timeInMinutes+" hours+min+sec", Toast.LENGTH_SHORT).show();
                }
        );

        mButtonPace.setOnClickListener(
                (view) -> {
                    double timeHours = Double.parseDouble(mEditTimeHours.getText().toString());
                    double timeMinutes = Double.parseDouble(mEditTimeMinutes.getText().toString());
                    double timeSeconds = Double.parseDouble(mEditTimeSeconds.getText().toString());

                    double hoursInSeconds = (timeHours*60*60)+(timeMinutes*60)+timeSeconds;
                    double distanceInMeters = Double.parseDouble(mEditDistance.getText().toString())*1000;
                    double speedInMetersPerSeconds = distanceInMeters/hoursInSeconds;

                    double timeInSeconds = 1000/speedInMetersPerSeconds;

                    double timeInMinutes = timeInSeconds/60;
                    String proverka = String.format(Locale.US,"%05.2f", timeInMinutes);
                    String[] vall = proverka.split("\\.");
                    //test[1].charAt(0);
                    Double resss = Double.parseDouble("0."+vall[1])*60;
                    String proverka2 = String.format(Locale.US,"%02.0f", resss);

                    mEditPaceMinutes.setText(vall[0]);
                    mEditPaceSeconds.setText(proverka2);

                    Toast.makeText(MainActivity.this , speedInMetersPerSeconds+" sec ", Toast.LENGTH_SHORT).show();
                }
        );

        mClickDistance.setOnClickListener( (view) -> {
                    double timeHours = Double.parseDouble(mEditTimeHours.getText().toString());
                    double timeMinutes = Double.parseDouble(mEditTimeMinutes.getText().toString());
                    double timeSeconds = Double.parseDouble(mEditTimeSeconds.getText().toString());

                    double paceMinutes = Double.parseDouble(mEditPaceMinutes.getText().toString());
                    double paceSeconds = Double.parseDouble(mEditPaceSeconds.getText().toString());

                    double paceToSpeed = 1000/(paceMinutes*60+paceSeconds);
                    double hoursInSeconds = timeHours*60*60+timeMinutes*60+timeSeconds;
                    double res = paceToSpeed*hoursInSeconds/1000;
                    String distanceInKm = String.format(Locale.US,"%.2f", res);
                    //(1000/240)*3600/1000

                    mEditDistance.setText(distanceInKm);

                    //Toast.makeText(MainActivity.this , distanceInKm+" km", Toast.LENGTH_SHORT).show();
                }
        );

        mClickPace.setOnClickListener( (view) -> {
                    double paceInMin = Double.parseDouble(mEditPaceMin.getText().toString());
                    double paceInSec = Double.parseDouble(mEditPaceSec.getText().toString());
                    //String paceToSpeed = (1000/(Integer.parseInt(mEditPaceMin.getText().toString())*60+mEditPaceSec.getText()))*3.6+"";
                    double paceToSpeed = 1000/(paceInMin*60+paceInSec)*3.6;
                    String printPaceToSpeed = String.format(Locale.US,"%.2f", paceToSpeed);
                    mEditSpeed.setText(String.valueOf(printPaceToSpeed));
                }
        );

        mClickSpeed.setOnClickListener( (View view) -> {
                    double speedInKmh = Double.parseDouble(mEditSpeed.getText().toString());
                    double speedToPaceInMin = (1000/(speedInKmh/3.6))/60;
                    double speedToPaceInSec = (speedToPaceInMin - (int)speedToPaceInMin)*60;
                    String printSpeedToPaceInMin = speedToPaceInMin+"";
                    String printSpeedToPaceInSec = speedToPaceInSec+"";
                    String testSecond = String.format(Locale.US,"%04.1f", speedToPaceInSec);
                    int rrr = (int)Double.parseDouble(testSecond);


                    if (rrr == 60) {
                        printSpeedToPaceInMin = String.format("%.0f", speedToPaceInMin);
                        printSpeedToPaceInSec = "00";
                    } else {
                        int rrr2 = (int)speedToPaceInMin;
                        printSpeedToPaceInMin = rrr2+"";
                        printSpeedToPaceInSec = testSecond;
                    }


                    //String speedToPace = 1000/(Double.parseDouble(mEditSpeed.getText().toString())/3.6)/60+"";
                    mEditPaceMin.setText(printSpeedToPaceInMin);
                    mEditPaceSec.setText(printSpeedToPaceInSec);
                }
        );
    }
}
