/*
Diese Activity wird aufgerufen wenn im Main_Fragmentum der button mit der id "button_open_acticity" gedrückt wird
Seine einzige aufgabe besteht darin den wert im eingabefeld vom Main_Fragmentum (layout = my_fragment_main.xml) wieder anzuzeigen
 */

package com.example.ego.and_exercise1_ganzselbst;

// import android.app.Activity; DAS wäre die für "Activity" als Basisklasse gewesen, aber wir verwenden ja AppCompatActivity als Basis ....
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity; // DAS ist die für "App....Activity" als basisklasse
import android.widget.TextView;

/**
 * Created by ego on 26.03.2018.
 */

// TODO (4.1) Message activity  (also die 2te activity) im manifest eintragen
/* .. geht prinzipiell sooo -->
    <activity
        android:name=".SecondActivity"
        android:label="Second Activity"
        >
        <intent-filter >
            <action android:name="com.example.package_path.SecondActivity"/>
            <category android:name="android.intent.category.DEFAULT" />
        </intent-filter>
    </activity>
*/

public class Message_Activity extends AppCompatActivity {

    // Key für die übergabe des wertes in textEingabe durch den onClick listener in Main_Fragmentum
    private static final String EXTRA_MESSAGE_TRANSFER_KEY = "bupsi"; // inhalt irrelevant; dient nur der erstellung eines schlüssel-strings

    /* Methode 1 .. die zur verfügung gestellt wird, damit ein intent von außen erzeugt werden kann, in das der anzuzeigende string (myTextEinagbe) gestopft wird (puExtra)
       Wird aufgerufen im onClick zum starten DIESER(hier) activity
       Übergeben werden:
        -- Context des AUfrufers, also von wo wir kommen werden
        -- String um den's eigentlich geht
    */
    public static Intent myGetIntent(Context myContext, String myTextEingabe) {
        Intent myIntent = new Intent(myContext, Message_Activity.class); //
        myIntent.putExtra(EXTRA_MESSAGE_TRANSFER_KEY, myTextEingabe);
        return myIntent;
    }

    // Methode 2  Was beim erzeugen der Klasse passiert .. i.e. der constructor
    // FIXME (3.0) ?? Ist das eigentlich ein Konstruktor, ?? Wo wird "Message_Activity" instanziert
    @Override
    protected void onCreate(Bundle myOnsavedInstanceState_3) {
        super.onCreate(myOnsavedInstanceState_3);
        setContentView(R.layout.my_message_screen);       // Set activity content; inflate ressource; add top level Views to activity

        // TODO (3.1) Hier müsste man noch den connex zum feldinhalt aus dem Fragmentum herstellen
        TextView ausgabeView = findViewById(R.id.ausgabe_feld); //Ausgabe View erzeugen und referenz auf das textfeld herstellen

        // A. Feld befüllen ... zunächst HARDCODED, bis ich die übergabemethode (mit dem Intent)  verstanden habe
        // ausgabeView.setText("Ich bin eine ausgabe");

        // B. Feld befüllen .. nun mit Intent und allem drumherum
        String zurueckgeholterText = getIntent().getStringExtra(EXTRA_MESSAGE_TRANSFER_KEY);
        ausgabeView.setText(zurueckgeholterText);
    }



}
