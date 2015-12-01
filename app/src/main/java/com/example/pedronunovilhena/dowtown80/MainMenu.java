package com.example.pedronunovilhena.dowtown80;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class MainMenu extends Activity implements OnClickListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

     /*   ////////////////////////////////////////////////////////////////////
        //////// GAME MENU  /////////////////////////////////////////////////
        Button playBtn = (Button) findViewById(R.id.playBtn);
        playBtn.setOnClickListener(this);

        Button rulesBtn = (Button) findViewById(R.id.rulesBtn);
        rulesBtn.setOnClickListener(this); */

    }

    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.playBtn :
                //once logged in, load the main page
                Log.d("LOGIN", "User has started the game");


                break;

            case R.id.rulesBtn :
                Log.d("RULES","QUER VER AS REGRAS");

                break;

        }

    }
}