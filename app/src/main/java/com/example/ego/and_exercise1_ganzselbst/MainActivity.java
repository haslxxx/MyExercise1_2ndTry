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
import android.support.annotation.Nullable;
// RESOLVED (1.0) ?? was tut das nullable  --> lässt zu, daß etwas NULL werden darf
// FIXME (1.0) ?? wie bestimmt man, wann man .nullable oder @nullable einsetzt (habs mal weggelassen, .. nix ist passiert)

// FIXME (1.01) ?? Wie kommt man darauf dass es diese AppCompatActivity gibt und warum verwenden wir nicht nur Activity
// " Class for activities that use the 'support library action bar features'  ?? tun wir das
public class MainActivity extends AppCompatActivity {

    // FIXME (1.1) ?? hier kommen wir rein wenn der session manager die app aufruft oder ??
    // FIXME ?? Wieso "protected"
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // FIXME ?? Was tut das (ich nehme an es ist der aufruf eines constructors)
        super.onCreate(savedInstanceState);     //onCreate -->
        setContentView(R.layout.activity_main);  //Set the activity content from a layout resource. The resource will be inflated, adding all top-level views to the activity

        /* da wir ja gleich weiterwollen zum Fragment, ohne irgendeine anzeige in der activity_main --> ein fragment machen
        *   !! Kommen wir hier das erste mal her (i.e. nach dem aufruf der app, dann ist sicher nichts am stack.
        *   !! Wird die Activity jedoch unterbrochen vom system oder sonstwie (?? beim zurückkehren aus einer nächsten Activity)
        *  FIXME (1.2ff) ?? ..dann ist was am stack und ..tja, aber dann würden wir das Fragment in diesem fall nicht mehr erzeugen ..wieso läuft es dann ??
        */
        /*
        Restore your activity state  --> In diesem Fall NICHT, aber Fragment neu anlegen
        When your activity is recreated after it was previously destroyed, you can recover your saved state from the Bundle that the system passes to your activity.
        Both the onCreate() and onRestoreInstanceState() callback methods receive the same Bundle that contains the instance state information.
        Because the onCreate() method is called whether the system is creating a new instance of your activity or recreating a previous one,
        you must check whether the state Bundle is null before you attempt to read it.
        If it is null, then the system is creating a new instance of the activity, instead of restoring a previous one that was destroyed.
         */
        if (savedInstanceState == null){
            Fragment myMainFragment = new Main_Fragmentum(); //wir machen mal ein fragmentobjekt
            getSupportFragmentManager().beginTransaction().add(R.id.my_empty_container, myMainFragment).commit();
            // RESOLVED (1.3)??  wieso brauchen wir den leeren xml-container von der MainActivity  um  das MainFragment zu adden ??
            // --> Er muß ja wo hinein, und hier haben wir nur den container des main-Fragment, sonst gibt's hier nix !

        }
    }
}
