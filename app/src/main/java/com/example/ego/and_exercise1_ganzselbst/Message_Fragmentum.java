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

/**
 * Created by ego on 27.03.2018.
 */

public class Message_Fragmentum extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflatorius, ViewGroup contanarius, Bundle mySavedInstanceState_1) {
        return inflatorius.inflate(R.layout.my_message_screen, contanarius, false);
    }


    @Override
    public void onViewCreated(View myFragmentView, Bundle mySavedInstanceState_2) {
        super.onViewCreated(myFragmentView, mySavedInstanceState_2);
        // TODO (5.1) Messagtext holen aus dem Intent


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //TODO (5.2) messagetext sichern
        //String textZuUebergeben = textEingabe.getText().toString();
        //outState.putString(MESSAGE_KEY, textZuUebergeben);
    }

}