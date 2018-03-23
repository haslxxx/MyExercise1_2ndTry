package com.example.ego.and_exercise1_ganzselbst;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;



import android.support.annotation.Nullable;
// TODO (1.0) was tut das nullable

// import com.example.ego.and_exercise1_ganzselbst.R; // der referenzlösung nachgebaut .. bringt aber auch nix

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // TODO (1.1)hier kommen wir rein wenn der session manager die app aufruft ?? oder
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // da wir ja gleich weiterwollen zum Fragment, ohne irgendeine anzeige in der activity_main --> ein fragment machen
        // TODO (1.2) wenn noch kein  fragment am stack ?? oder so ... eigentlich weiß ich da jetzt nicht warum ich's tu
        if (savedInstanceState == null){
//            Fragment myMainFragment = new Main_Fragmentum(); //wir machen mal ein fragmentobjekt
//            getSupportFragmentManager().beginTransaction().add(R.id.leerer_container, myMainFragment).commit();
            getSupportFragmentManager().beginTransaction().add(R.id.empty_container, new Main_Fragmentum()).commit();
            // TODO (1.3  finde den FEHLER .. aber wie ??
            /*
            :app:compileDebugJavaWithJavac
C:\Users\ego\GIT-repositories\github\ANDExercise1ganzSelbst_1\app\src\main\java\com\example\ego\and_exercise1_ganzselbst\MainActivity.java:26: error: no suitable method found for add(int,Main_Fragmentum)
            getSupportFragmentManager().beginTransaction().add(R.id.empty_container, new Main_Fragmentum()).commit();
                                                          ^
    method FragmentTransaction.add(Fragment,String) is not applicable
      (argument mismatch; int cannot be converted to Fragment)
    method FragmentTransaction.add(int,Fragment) is not applicable
      (argument mismatch; Main_Fragmentum cannot be converted to Fragment)
Note: Some messages have been simplified; recompile with -Xdiags:verbose to get full output
1 error

 FAILED

             */
        }
    }
}
