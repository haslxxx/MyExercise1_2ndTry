/* MainActivity.java

Hier geht's rein in unsere app

Um das alles verstehen zu können sollte man als erstes mal DAS lesen -->
https://developer.android.com/guide/components/activities/activity-lifecycle.html

 */

package com.example.ego.and_exercise1_ganzselbst;
// @todo  test der features
// FIXME  test der features
// TODO  test der features
// XXX  test der features

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment; // Damit geht's dann plötzlich mit dem gertSupportFragmentManager
import android.support.annotation.Nullable;  // FIXME (1.0) ?? was tut das nullable


// FIXME (1.01 ?? Wie kommt man darauf dass es diese AppCompatActivity gibt und warum verwenden wir nicht nur Activity
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // FIXME (1.1) ?? hier kommen wir rein wenn der session manager die app aufruft oder ??
        super.onCreate(savedInstanceState);     //onCreate -->
        setContentView(R.layout.activity_main);  //Set the activity content from a layout resource. The resource will be inflated, adding all top-level views to the activity

        // da wir ja gleich weiterwollen zum Fragment, ohne irgendeine anzeige in der activity_main --> ein fragment machen
        /* FIXME (1.2) ?? wenn noch kein  fragment am stack ?? oder so ... eigentlich weiß ich da jetzt nicht warum ich's tu
        *   !! Kommen wir hier das erste mal her (i.e. nach dem aufruf der app, dann ist sicher nichts am stack.
        *   !! Wird die Activity jedoch unterbrochen vom system oder sonstwie (?? beim zurückkehren aus einer nächsten Activity)
        *  FIXME (1.2ff) ?? .... dann ist was am stack und .... tja, aber dann würden wir das Fragment in diesem beispiel nicht mehr erzeugen .. hmmm ??
        */
        if (savedInstanceState == null){
            Fragment myMainFragment = new Main_Fragmentum(); //wir machen mal ein fragmentobjekt
            getSupportFragmentManager().beginTransaction().add(R.id.my_empty_container, myMainFragment).commit();
            // FIXME (1.3)??  wieso brauchen wir den leeren xml-container von der MainActivity  um  das MainFragment zu adden ??
// die variante aus dem refernzsource           getSupportFragmentManager().beginTransaction().add(R.id.empty_container, new Main_Fragmentum()).commit();

        }
    }
}
