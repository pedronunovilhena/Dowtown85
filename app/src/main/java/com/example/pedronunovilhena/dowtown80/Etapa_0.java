package com.example.pedronunovilhena.dowtown80;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Etapa_0 extends AppCompatActivity {
    public int etapa_nr=1;
    public String actor="Fixx";

    private Toolbar toolbar;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_etapa_0);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    public void submitAnswer(View v){
        EditText text = (EditText)findViewById(R.id.editText);
        TextView mission = (TextView)findViewById(R.id.missionTxt);


        String value = text.getText().toString();

        switch (etapa_nr){
            case 1:
                if(value.equalsIgnoreCase(getString(R.string.answer1))) {
                    Toast.makeText(getApplicationContext(), "Resposta certa", Toast.LENGTH_SHORT).show();
                    mission.setText(R.string.mission2);
                    etapa_nr++;
                }
                else{
                    Toast.makeText(getApplicationContext(), "Resposta Errada", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if(value.equalsIgnoreCase(getString(R.string.answer2))) {
                    Toast.makeText(getApplicationContext(), "Resposta certa", Toast.LENGTH_SHORT).show();
                    mission.setText(R.string.mission3);
                    etapa_nr++;
                }
                else{
                    Toast.makeText(getApplicationContext(), "Resposta Errada", Toast.LENGTH_SHORT).show();
                }
                break;
            case 3:
                if(value.equalsIgnoreCase(getString(R.string.answer3))) {
                    Toast.makeText(getApplicationContext(), "Resposta certa", Toast.LENGTH_SHORT).show();
                    mission.setText(R.string.mission4);
                    etapa_nr++;
                }
                else{
                    Toast.makeText(getApplicationContext(), "Resposta Errada", Toast.LENGTH_SHORT).show();
                }
            case 10:
                Log.d("OLA","OLA");

        }

    }

    public void showMap(View v){
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("actor",actor);
        intent.putExtra("etapa_nr",etapa_nr);
        startActivity(intent);

    }

    public void showHelp (View v){
        Toast.makeText(getApplicationContext(), "Nothing to do yet.", Toast.LENGTH_SHORT).show();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_etapa_0, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_etapa_0, container, false);
            return rootView;
        }
    }
}
