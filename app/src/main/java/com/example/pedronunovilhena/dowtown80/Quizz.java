package com.example.pedronunovilhena.dowtown80;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class Quizz extends FragmentActivity {
    public int answer_number=1;
    public int player_number=1;

    Player Player1 = new Player();
    Player Player2 =new Player();
    Player Player3 =new Player();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);
        Start_Questions();
    }

    public void Start_Questions (){
            // fix, o fogg e o passepartout
            //set the question text from current question
            String question = "Qual das seguintes personagens gosta mais ?";
            TextView qText = (TextView) findViewById(R.id.question);
            qText.setText(question);

            TextView option1 = (TextView) findViewById(R.id.answer1);
            option1.setText("Fix");

            TextView option2 = (TextView) findViewById(R.id.answer2);
            option2.setText("Fogg");

            TextView option3 = (TextView) findViewById(R.id.answer3);
            option3.setText("Passepartout");
    }

    public void getSelectedAnswer(View v){
        int answer=getTextAnswer();

        //Toast.makeText(getApplicationContext(), answer, Toast.LENGTH_SHORT).show();
        if(answer==1){
            switch (player_number) {
                case 1:
                    Player1.Fix++;
                    break;
                case 2:
                    Player1.Fogg++;
                    break;
                case 3:
                    Player1.Passepartout++;
                    break;
            }
        }
        else if(answer==2) {//
            switch (player_number) {
                case 1:
                    Player2.Fix++;
                    break;
                case 2:
                    Player2.Fogg++;
                    break;
                case 3:
                    Player2.Passepartout++;
                    break;
            }
        }
        else if(answer==3){
                switch (player_number) {
                    case 1:
                        Player3.Fix++;
                        break;
                    case 2:
                        Player3.Fogg++;
                        break;
                    case 3:
                        Player3.Passepartout++;
                        break;
                }

        }

        // Se é o ultimo jogador mostra os resultados, se não passa ao next player
         if(answer_number==3){
            if(player_number==3){
             CalculateResults();

            }

            else{
                Toast.makeText(getApplicationContext(), "Next Player", Toast.LENGTH_SHORT).show();
                player_number++;
                answer_number=1;
            }

        }
        else{
             answer_number++;
         }
        Start_Questions();

    }

    private void CalculateResults() {

        int fix = 0;
        int passepartout = 0;
        int fogg = 0;
        int maxPlayer1 = Math.max(Player1.Fix, Math.max(Player1.Fogg, Player1.Passepartout));
        int maxPlayer2 = Math.max(Player2.Fix, Math.max(Player2.Fogg, Player2.Passepartout));
        int maxPlayer3 = Math.max(Player3.Fix, Math.max(Player3.Fogg, Player2.Passepartout));

        /////PLAYER1//////////
        if (maxPlayer1 == Player1.Fix)
            fix = 1;
        else if (maxPlayer1 == Player1.Fogg)
            fogg = 1;
        else if (maxPlayer1 == Player1.Passepartout)
            passepartout = 1;

       ////PLAYER2//////////
       if(maxPlayer2==Player2.Fix)
       {
           if(fix==0){
               fix=2;}
           else{
               if(Player1.Fix>=Player2.Fix){
                   if(Player2.Passepartout>=Player2.Fogg){
                       passepartout=2;
                   }
                   else{
                       fogg=2;
                   }
               }
               else{
                   if(Player1.Passepartout>=Player1.Fogg){
                       passepartout=1;
                       fix=2;
                   }
                   else{
                       fogg=1;
                       fix=2;
                   }

               }
           }
       }

       else if(maxPlayer2==Player2.Fogg)
       {
           if(fogg==0){
               fogg=2;}
           else{
               if(Player1.Fogg>=Player2.Fogg){
                   if(Player2.Passepartout>Player2.Fix){
                       passepartout=2;
                   }
                   else{
                       fix=2;
                   }
               }
               else{
                   if(Player1.Passepartout>Player1.Fix){
                       passepartout=1;
                       fogg=2;
                   }
                   else{
                       fix=1;
                       fogg=2;
                   }
               }
           }
       }

       else if(maxPlayer2==Player2.Passepartout)
       {
           if(passepartout==0){
               passepartout=2;}
           else{
               if(Player1.Passepartout>=Player2.Passepartout){
                   if(Player2.Fogg>Player2.Fix){
                       fogg=2;
                   }
                   else{
                       fix=2;
                   }
               }
               else{
                   if(Player1.Fogg>Player1.Fix){
                       fogg=1;
                       passepartout=2;
                   }
                   else{
                       fix=1;
                       passepartout=2;
                   }
               }
           }
       }

    ///PLayer3 aldrabado só para dar certo///
     if(fix==0){
         fix=3;
     }
     if(fogg==0){
         fogg=3;
     }
     if(passepartout==0){
         passepartout=3;
     }


        String texto="Fogg é o Player"+fogg+";Passepartout é o Player"+passepartout+";Fix é o player"+fix;
        Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_LONG).show();

    }

    public int getTextAnswer() { // c1= Fix / C2= Forg / c3= Passepartout
        RadioButton c1 = (RadioButton)findViewById(R.id.answer1);
        RadioButton c2 = (RadioButton)findViewById(R.id.answer2);
        RadioButton c3 = (RadioButton)findViewById(R.id.answer3);

        if (c1.isChecked())
        {
            return 1;
        }
        if (c2.isChecked())
        {
            return 2;
        }
        if (c3.isChecked())
        {
            return 3;
        }
        else {
            return 0;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quizz, menu);
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
}
