package com.example.ego.and_exercise1_ganzselbst;

//import android.app.Fragment;  // .. kommt automatisch wenn man die Klasse "Fragment" wählt .. wird aber durch "support.V4" ersetzt .. s.U.
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
((d.h. ein Fragment müsste nicht zwangsläufig eine View haben .. i.e. das inflate aufgerufen werden)

Interaction with fragments is done through FragmentManager, which can be obtained via
Activity.getFragmentManager() and Fragment.getFragmentManager().
The Fragment class can be used many ways to achieve a wide variety of results. In its core, it represents
a particular operation or interface that is running within a larger Activity.
A Fragment is closely tied to the Activity it is in, and can not be used apart from one.
Though Fragment defines its own lifecycle, that lifecycle is dependent on its activity: if the activity is stopped,
no fragments inside of it can be started; when the activity is destroyed, all fragments will be destroyed.

All subclasses of Fragment must include a public no-argument constructor.
// FIXME (2.1) ?? wo ist dieser constructor
The framework will often re-instantiate a fragment class when needed, in particular during state restore,
and needs to be able to find this constructor to instantiate it. If the no-argument constructor is not available,
a runtime exception will occur in some cases during state restore.
 */

public class Main_Fragmentum extends Fragment {

    // Methode 1 onCreateView ... erzeugt das bildchen das wir im xml für das Fragment definiert haben
    // RESOLVED (2.1) ?? wer ruft das auf; was könne die übergebenen parameter und deren Klassen;
    /*
Called to have the fragment instantiate its user interface view.
This is optional, and non-graphical fragments can return null (which is the default implementation).
This will be called between onCreate(Bundle) and onActivityCreated(Bundle).
If you return a View from here, you will later be called in onDestroyView() when the view is being released.
Return: View 	Return the View for the fragment's UI, or null.

LayoutInflater: Instantiates a layout XML file into its corresponding View objects. It is never used directly.
Instead, use getLayoutInflater() or getSystemService(Class) to retrieve a standard LayoutInflater instance
that is already hooked up to the current context and correctly configured for the device you are running on.

inflate(int resource, ViewGroup root, boolean attachToRoot) Inflate a new view hierarchy from the specified xml resource.

     */
    @Override
    public View onCreateView (LayoutInflater inflatorius, ViewGroup contanarius, Bundle mySavedInstanceState_1 ) {
        View newView = inflatorius.inflate(R.layout.my_fragment_main, contanarius,false);
        return newView;
    }

    //Methode 2  onViewCreated ... Macht wohl die ganze arbeit innerhalb des Fragments
    // RESOLVED (2.2) ?? wer ruft's auf; was können die übergeben parameter   --> siehe text Unten
/*
You should inflate your layout in onCreateView but shouldn't initialize other views using findViewById in onCreateView.
Because sometimes view is not properly initialized. So always use findViewById in onViewCreated(when view is fully created)
and it also passes the view as parameter.
onViewCreated is a 'make sure that view is fully created'.
 */

    private EditText textEingabe;  // Lokale variablen für die views im layout (i.e. my_fragment_main)
    private Button activityAufruf;
    private Button fragmentAufruf;

    // !! Key den wir im "onSaveInstanceState" verwenden um im Bundle den wert der texteingabe zu sichern; in onViewCreated wird er dann zur wiederherstellung verwendet
    private static final String MESSAGE_REBUILD_KEY = "blubb";  // inhalt irrelevant; dient nur der erstellung eines schlüssel-strings
    @Override
    public void onViewCreated (View myCreatedFragmentView, Bundle mySavedInstanceState_2) {
        // FIXME Wozu das super ?
        super.onViewCreated(myCreatedFragmentView, mySavedInstanceState_2);

        //Referenzen auf die elemente im layout holen
        /* FIXME (2.3) ?? warum sieht das in einem Fragment anders aus als in einer Activity (ohne getView() ) (siehe Message_activity)
        // FIXMEd (2.3ff) woher kommt das getView() und worauf bezieht sich's; Warum braucht's keine parameter (siehe doku) --> super
                anyView = getView(int position, View convertView, ViewGroup parent)
                Get a View that displays the data at the specified position in the data set.  --> von einer parameterlosen variante steht nix in der doku !!
        */
        textEingabe    = super.getView().findViewById(R.id.textField);
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
            textEingabe.setText(myGoodOldMessage);  //pendent zu  'getText()' im  'onSaveInstanceState' (s.U.)
        }


        //Event-listener erzeugen für die beiden Buttons
        // -- 1. der Button der die Activity aufrufen soll
        activityAufruf.setOnClickListener(new View.OnClickListener() { // DAS ist alles standard
            @Override
            public void onClick(View v) {  //eingebettete funktion !! ; View V --> Die View in der der Button wohnt
                String textZuUebergeben = textEingabe.getText().toString();  //Wir holen den eingegebenen text aus der view

                // ####### Die THEORIE zum INTENT ###########
                /* An Intent is an object that provides runtime binding between separate components, such as two activities.
                The Intent represents an app’s "intent to do something." You can use intents for a wide variety of tasks.
                In diesem Fall startet mein Intent eine andere  activity (Message_Activity.java).

                An Intent provides a facility for performing late runtime binding between the code in different applications.
                Its most significant use is in the launching of activities, where it can be thought of as the glue between activities.
                It is basically a passive data structure holding an abstract description of an action to be performed.

                The Intent constructor takes two parameters:
                -- A Context as its first parameter  v.getContext()  --> quasi "woher kommen wir"
                -- The Class of the app component to which the system should deliver the Intent (in this case, the activity that should be started).
                */

                /* ######## Erster Versuch --> FALSCH  #########
                   FIXME (2.4) FALSCH(1) -->
                        Intent myIntent = new Intent(v.getContext(), Message_Activity.class); //Message_Activity.class --> Dorthin wollen wir
                        Falsch, weil das Intent in der KLasse erzeugt werden muss, wo die Ziel Activity wohnt,
                   FIXME --> ?? ..oder könnte man das Pferd auch von der Aufruferseite aufzäumen ?
                   FIXME FALSCH(1) --> myIntent.putExtra(MESSAGE_KEY, textZuUebergeben); // Methode um daten "hinüberzuschicken in die aufgerufene Activity
                        putExtra's gibt's wie sand am meer .. für alle eventualitäten; hier vermutlich putExtra(String name, String value)
                        das gegenstück auf "der anderen seite" ist getExtra()  :-)
                */

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

                getFragmentManager()                            // Fragments werden immer mit dem getFragmentManager erzeugt (siehe hintergründe ganz oben)
                        .beginTransaction()
                        // FIXMEd (2.5) Wir ersetzen hier die aktuelle View (?main_fragment gegen das neuen Fragment ()
                        .replace(R.id.my_empty_container,
                        // FIXMEd (2.4) ?? eigentlich sind wir doch im  "my_fragment_main" siehe onCreateView, wieso also DAS (my_empty_container) ... weil das fragment im empty fragment läuft (siehe activityMain)
//                                Message_Fragmentum.getFragment(textZuUebergeben))
                                  // FIXMEd (2.5)  Das mit dem Intent geht im Fragment scheinbar nicht  :-(   ?? Warum ! Fragments sind auf DIE app beschränkt. Activites könne global genutzt werden
                                //Fragents werden in der app gemanaged  activities vom system
// ####### A. Ohne die Methode zum erzeugen des Fragments (siehe Message_Fragmentum)
//                                    new Message_Fragmentum())   // So wäre alles fein, wenn wir nicht diesen blöden string aus dem eingabefeld in Main_Fragmentum übermitteln müssten
// ####### B. MIT der Methode (Message_Fragmentum.myGetFragment)
                                      myNewMessageFragment)
                        .addToBackStack(null)
                        .commit();

            }
        });

    }

    // Methode 3  onSaveInstanceState
    // TODOd (2.6) wir kümmern uns um die daten für den fall das das system das Fragment Stopp't
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

// FIXMEd (my_fragment_main.xml)  ?? Jeglicher versuch den editText mit einer Viewgroup wie Textlayout, TextinputLayout zu klammern
// FIXMEd .. führt unweigerlich zu einer fehlermeldung , weil er diese Klassen nicht findet  --> Frag mal das INTERNET ... Das Forum schweigt sowieso
// template verwenden (gradle scripts) .. da gibts imports (siehe angabe) dann funktioierts

// FIXMEd (my_fragmen_main.xml) ?? die texte für Buttons aus dem strings.xml werde in UPPERCASE ausgegeben ?? wieso ?? was dagegen tun
// vordefinierte attribute  ... mit textAllcaps true false kann es geändert werden  ... sieghe auch in seiner lösung den "style " parameter
