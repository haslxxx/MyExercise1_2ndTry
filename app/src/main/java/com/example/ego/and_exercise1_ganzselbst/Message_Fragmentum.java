/*
Dieses Fragment wird aufgerufen wenn im Main_Fragmentum der button mit der id "button_open_fragment" gedrückt wird
Seine einzige aufgabe besteht darin den wert im eingabefeld vom Main_Fragmentum (layout = my_fragment_main.xml) wieder anzuzeigen
 */
package com.example.ego.and_exercise1_ganzselbst;

//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ego on 27.03.2018.
 */

public class Message_Fragmentum extends Fragment {

    private static final String EXTRA_MESSAGE_TO_TRANSFER_KEY = "peperl";

    // Das Fragment stelle hier eine Methode zur verfügung über die es dann selber erzeugt wird ... ein bisserl 3 mal um den hals und dann gekratzt ...
    public static Fragment getFragment(String textZuUebergeben) {
        Message_Fragmentum fragment = new Message_Fragmentum();

        // Um einem Fragment daten zu übergeben erzeugt man ein "Bundle" (AssoziativArray) in das man dann die daten stopft
        Bundle argsBundle = new Bundle();
        argsBundle.putString(EXTRA_MESSAGE_TO_TRANSFER_KEY, textZuUebergeben); // Methode um in ein Bundle was hineinzustopfen
        fragment.setArguments(argsBundle);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflatorius, ViewGroup contanarius, Bundle mySavedInstanceState_4) {
        return inflatorius.inflate(R.layout.my_message_screen, contanarius, false);
    }


    @Override
    public void onViewCreated(View myFragmentView, Bundle mySavedInstanceState_5) {
        super.onViewCreated(myFragmentView, mySavedInstanceState_5);
        // TODO (5.1) Messagtext holen aus dem Bundle

        String zurueckgeholterText = getArguments().getString(EXTRA_MESSAGE_TO_TRANSFER_KEY); //FIXME (5.2) ?? woher kommt die methode getArguments
        TextView myMessageTextView = getView().findViewById(R.id.ausgabe_feld); // FIXME (5.3) ?? woher kommt die methode getView
        myMessageTextView.setText(zurueckgeholterText);

    }

    // Für den fall, daß die app "stillgelegt" wird (onSTop, onPause .. oder so ??) -- > anzeigetext sichern
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //TODO (5.4) messagetext sichern
        //String textZuUebergeben = textEingabe.getText().toString();
        //outState.putString(MESSAGE_KEY, textZuUebergeben);
    }

}