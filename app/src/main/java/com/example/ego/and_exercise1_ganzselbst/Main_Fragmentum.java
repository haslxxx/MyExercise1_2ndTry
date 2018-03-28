package com.example.ego.and_exercise1_ganzselbst;

//import android.app.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;



/**
 * Created by ego on 22.03.2018.
 */
/*
A Fragment is a piece of an application's user interface or behavior that can be placed in an Activity.
Interaction with fragments is done through FragmentManager, which can be obtained via Activity.getFragmentManager() and Fragment.getFragmentManager().
 */

public class Main_Fragmentum extends Fragment {

    // Methode 1 ... erzeugt das bildchen das wir im xml für das Fragment definiert haben
    // FIXME (2.1) ?? wer ruft das auf; was könne die übergebenen parameter und deren Klassen;
    @Override
    public View onCreateView (LayoutInflater inflatorius, ViewGroup contanarius, Bundle mySavedInstanceState_1 ) {
        return inflatorius.inflate(R.layout.my_fragment_main, contanarius,false);
    }

    //Methode 2 ... Macht wohl die ganze arbeit innerhalb des Fragments
    // FIXME (2.2) ?? wer ruft's auf; was können die übergeben parameter
    private EditText textEingabe;
    private Button activityAufruf;
    private Button fragmentAufruf;

    // FALSCH : Key für die datenübergabe im Intent zwischen DIESEM Fragment und der per Button aufgerufenen Activity /Fragment
    // RICHTIG: Key den wir im "onSaveInstanceState verwenden um im Bundle den wert der texteingabe zu sichern; in onViewCreated wird er dann zur wiederherstellung verwendet
    private static final String MESSAGE_REBUILD_KEY = "blubb";  // inhalt irrelevant; dient nur der erstellung eines schlüssel-strings


    @Override
    public void onViewCreated (View myFragmentView, Bundle mySavedInstanceState_2) {
        super.onViewCreated(myFragmentView, mySavedInstanceState_2);

        //Referenzen auf die elemente im layout holen
        // FIXME (2.3) ?? warum sieht das in einem Fragment anders aus als in einer Activity (ohne getView() ) (siehe Message_activity)
        textEingabe    = getView().findViewById(R.id.textField);
        activityAufruf = getView().findViewById(R.id.button_open_activity);
        fragmentAufruf = getView().findViewById(R.id.button_open_fragment);


        //Wert des textEingabe-feldes holen falls das Fragment schon mal gelaufen ist
        /*
        Restore your activity state
        When your activity is recreated after it was previously destroyed, you can recover your saved state from the Bundle that the system passes to your activity.
        Both the onCreate() and onRestoreInstanceState() callback methods receive the same Bundle that contains the instance state information.
        Because the onCreate() method is called whether the system is creating a new instance of your activity or recreating a previous one,
        you must check whether the state Bundle is null before you attempt to read it.
        If it is null, then the system is creating a new instance of the activity, instead of restoring a previous one that was destroyed.
         */
        // Wiederherstellen gesicherter Daten
        if (mySavedInstanceState_2 != null) {
            String myGoodOldMessage = mySavedInstanceState_2.getString(MESSAGE_REBUILD_KEY); // Aus dem Bundle den String mit dem Key "MESSAGE_KEY" holen
            textEingabe.setText(myGoodOldMessage);
        }


        //Event-listener erzeugen für die beiden Buttons
        // -- 1. der Button der die Activity aufrufen soll
        activityAufruf.setOnClickListener(new View.OnClickListener() { // DAS ist alles standard
            @Override
            public void onClick(View v) {  //eingebettete funktion !! ??
                String textZuUebergeben = textEingabe.getText().toString();  //Wir holen den eingegebenen text aus der view

                // ####### Die THEORIE zum INTENT ###########
                /* An Intent is an object that provides runtime binding between separate components, such as two activities.
                The Intent represents an app’s "intent to do something." You can use intents for a wide variety of tasks,
                but in this case my intent starts another activity.

                An Intent provides a facility for performing late runtime binding between the code in different applications.
                Its most significant use is in the launching of activities, where it can be thought of as the glue between activities.
                It is basically a passive data structure holding an abstract description of an action to be performed.

                The Intent constructor takes two parameters:
                -- A Context as its first parameter  v.getContext()  --> quasi "woher kommen wir"
                -- The Class of the app component to which the system should deliver the Intent (in this case, the activity that should be started).
                */

                // ######## Erster Versuch --> FALSCH  #########
                // FIXME (2.4) FALSCH(1) --> Intent myIntent = new Intent(v.getContext(), Message_Activity.class); //Message_Activity.class --> Dorthin wollen wir
                // FIXME --> ??  weil das Intent in der KLasse erzeugt werden muss, wo die Ziel Activity wohnt , oder könnte man das Pferd auch von der Aufruferseite aufzäumen ?

                // FIXME FALSCH(1) --> myIntent.putExtra(MESSAGE_KEY, textZuUebergeben); // Methode um daten "hinüberzuschicken in die aufgerufene Activity
                //putExtra's gibt's wie sand am meer .. für alle eventualitäten; hier vermutlich putExtra(String name, String value)
                //das gegenstück auf "der anderen seite" ist getExtra()  :-)


                // ######## Zweiter Versuch #########
                Intent myNewIntent = Message_Activity.myGetIntent(v.getContext(),textZuUebergeben); // intent mit hilfe der Metode aus Message_Activity erzeugen

                startActivity(myNewIntent); // Darum geht's uns ja eigentlich ;-)  .. uff.. UND DAS GEHT NICHT EINFACHER ??
            } // end of embedded function
        });



        //  -- 2. der Button der das Fragment aufrufen soll
        fragmentAufruf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textZuUebergeben = textEingabe.getText().toString();  //Wir holen den eingegebenen text aus der view
// ####### B. Wir erzeugen mithilfe der methode die in Message_Fragemntum zur verfügung gestellt wird das Fragment, um den Text dort hineinstopfen zu können
                Fragment myNewMessageFragment = Message_Fragmentum.myGetFragment(textZuUebergeben);

                getFragmentManager()                            // Fragments werden immer mit dem getFragmentManager erzeugt
                        .beginTransaction()
                        // FIXME (2.5) Wir ersetzen hier die aktuelle View (??) gegen das neuen Fragment (oder deren View ?? )
                        .replace(R.id.my_empty_container,       // FIXME (2.4) ?? eigentlich sind wir doch im  "my_fragment_main" siehe onCreateView, wieso also DAS
//                                Message_Fragmentum.getFragment(textZuUebergeben))  // FIXME (2.5)  Das mit dem Intent geht im Fragment scheinbar nicht :-(
// ####### A. Ohne die Methode zum erzeugen des Fragments (siehe Message_Fragmentum)
//                                    new Message_Fragmentum())   // So wäre alles fein, wenn wir nicht diesen blöden string aus dem eingabefeld in Main_Fragmentum übermitteln müssten
// ####### B. MIT der Methode
                                      myNewMessageFragment)
                        .addToBackStack(null)
                        .commit();

            }
        });

    }


    // TODO (2.6) wir kümmern unds um die daten für den fall das das system das Fragment Stopp't
    /*
    There are a few scenarios in which your activity is destroyed due to normal app behavior, such as when the user presses the Back button
    or your activity signals its own destruction by calling the finish() method.
    The system may also destroy the process containing your activity to recover memory if the activity is in the Stopped state
    and hasn't been used in a long time, or if the foreground activity requires more resources.

    As your activity begins to stop, the system calls the onSaveInstanceState() method so your activity can save state information with a collection of key-value pairs.
    The default implementation of this method saves transient information about the state of the activity's view hierarchy, such as the text in an EditText widget
    or the scroll position of a ListView widget.
    Your app should implement the onSaveInstanceState() callback after the onPause() method, and before onStop(). Do not implement this callback in onPause().

    The saved data that the system uses to restore the previous state is called the instance state and is a collection of key-value pairs stored in a Bundle object.

    Caution: You must always call the superclass implementation of onSaveInstanceState() so the default implementation can save the state of the view hierarchy.
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String textZuUebergeben = textEingabe.getText().toString();
        outState.putString(MESSAGE_REBUILD_KEY, textZuUebergeben);
    }

}

// FIXME (my_fragment_main.xml)  ?? Jeglicher versuch den editText mit einer Viewgroup wie Textlayout, TextinputLayout zu klammern
// FIXME .. führt unweigerlich zu einer fehlermeldung , weil er diese Klassen nicht findet  --> Frag mal das INTERNET ... Das Forum schweigt sowieso

// FIXME (my_fragmen_main.xml) ?? die texte für Buttons aus dem strings.xml werde in UPPERCASE ausgegeben ?? wieso ?? was dagegen tun