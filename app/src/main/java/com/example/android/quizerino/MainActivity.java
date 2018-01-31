package com.example.android.quizerino;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    /**
    Button nextButton = (Button) findViewById(R.id.buttonNext);
    Button previousButton = (Button) findViewById(R.id.buttonPrevious);
    Button submitButton = (Button) findViewById(R.id.buttonSubmit);
    Button startButton = (Button) findViewById(R.id.buttonStart);
    Button restartButton = (Button) findViewById(R.id.buttonRestart);
    **/

    int quizNumber = 0;

    String final_answer = "";
    String total_score = "";

    String[][] myQuestions = {{"1","2","What is the capital of California?","A. San Francisco","B. Sacramento","C. Los Angeles","D. San Diego"},
            {"1","1","What is the capital of Oregon?","A. Salem","B. Portland","C. Eugene","D. Beaverton"},
            {"1","3","What is the capital of Texas?","A. Houston","B. Dallas","C. Austin","D. San Antonio"},
            {"1","4","What is the capital of Florida?","A. Miami","B. Orlando","C. Tampa","D. Tallahassee"},
            {"1","2","What is the capital of Minnesota","A. Minneapolis","B. Saint Paul","C. Minnetonka","D. Duluth"},
            {"2","texas","Name the biggest state in the US:"},
            {"2","new york","Where's the statue of liberty located?"},
            {"3","ab","Which cities never snowed?","San Francisco","Honolulu","Denver","New York"},
            {"2","rhode island","Name the smallest state in the US:"},
            {"3","bc","Which cities are the hottest?","San Francisco","Phoenix","Tucson","New York"}};

    String[] userAnswer = {"0","0","0","0","0","","","","",""};

    /**
     * Uncomment below if need to debug with the correct answer
     * @param savedInstanceState
     */
    //String[] userAnswer = {"2","1","3","4","2","Texas ","New York","ab","Rhode Island","bc"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Switched from main activity to the question layout
     */
    public void startButton(View v){
        setContentView(R.layout.question_layout1);
        Log.i("layoutfiredoff","" + myQuestions[quizNumber][0]);

        setShowQuiz(Integer.parseInt(myQuestions[quizNumber][0]),quizNumber,false,true);
    }

    /**
     * Switched from result page to activity main (the home page)
     */
    public void restartButton(View v){
        Log.i("quizNumber", "" + quizNumber);

        clearAnswer();
        setContentView(R.layout.activity_main);
    }

    /**
     * This populates the quiz and the answers for each of the questions
     * @param quizType shows the type of quiz: multiple choice, fill in or check boxes
     * @param quizNumber it's the quiz number based on the array number
     * @param prevClickAble is to set the previous button be clickable or not
     * @param nextClickAble is to set the next button be clickable or not
     */
    public void setShowQuiz(int quizType, int quizNumber, boolean prevClickAble, boolean nextClickAble){

        if (quizType == 1){
            setContentView(R.layout.question_layout1);
            TextView question = findViewById(R.id.textQuestion);
            RadioButton answer_a = (RadioButton) findViewById(R.id.radio_a);
            RadioButton answer_b = (RadioButton) findViewById(R.id.radio_b);
            RadioButton answer_c = (RadioButton) findViewById(R.id.radio_c);
            RadioButton answer_d = (RadioButton) findViewById(R.id.radio_d);
            Button nextButton = (Button) findViewById(R.id.buttonNext1);
            Button prevButton = (Button) findViewById(R.id.buttonPrevious1);

            question.setText(myQuestions[quizNumber][2]);
            answer_a.setText(myQuestions[quizNumber][3]);
            answer_b.setText(myQuestions[quizNumber][4]);
            answer_c.setText(myQuestions[quizNumber][5]);
            answer_d.setText(myQuestions[quizNumber][6]);
            prevButton.setClickable(prevClickAble);
            nextButton.setClickable(nextClickAble);
            setSavedAnswer(getUserAnswer());
        }
        else if (quizType == 2){
            setContentView(R.layout.question_layout2);
            EditText answer = (EditText) findViewById(R.id.layout2_answer);
            TextView question = (TextView) findViewById(R.id.layout2_question);

            question.setText(myQuestions[quizNumber][2]);
            Button nextButton = (Button) findViewById(R.id.buttonNext2);
            Button prevButton = (Button) findViewById(R.id.buttonPrevious2);
            prevButton.setClickable(prevClickAble);
            nextButton.setClickable(nextClickAble);
            setSavedAnswer(getUserAnswer());
        }

        else if (quizType == 3){
            setContentView(R.layout.question_layout3);
            TextView question = (TextView) findViewById(R.id.layout3_question);
            CheckBox checkA = (CheckBox) findViewById(R.id.check_a);
            CheckBox checkB = (CheckBox) findViewById(R.id.check_b);
            CheckBox checkC = (CheckBox) findViewById(R.id.check_c);
            CheckBox checkD = (CheckBox) findViewById(R.id.check_d);
            Button nextButton = (Button) findViewById(R.id.buttonNext3);
            Button prevButton = (Button) findViewById(R.id.buttonPrevious3);

            question.setText(myQuestions[quizNumber][2]);
            checkA.setText(myQuestions[quizNumber][3]);
            checkB.setText(myQuestions[quizNumber][4]);
            checkC.setText(myQuestions[quizNumber][5]);
            checkD.setText(myQuestions[quizNumber][6]);
            prevButton.setClickable(prevClickAble);
            nextButton.setClickable(nextClickAble);
            setSavedAnswer(getUserAnswer());
        }
    }

    /**
     * Method to check if the next button should be available or not
     * @param v
     */
    public void nextQuiz(View v){
        if (myQuestions[quizNumber][0] == "2"){
            EditText et_answer = (EditText) findViewById(R.id.layout2_answer);
            setSavedAnswer(et_answer.getText().toString());
        }
        if (quizNumber == myQuestions.length - 2){
            quizNumber += 1;
            setShowQuiz(Integer.parseInt(myQuestions[quizNumber][0]),quizNumber,true,false);
        }
        else {
            quizNumber += 1;
            setShowQuiz(Integer.parseInt(myQuestions[quizNumber][0]),quizNumber,true,true);
        }
    }

    /**
     * Method to check if the prev button should be available or not
     * @param v
     */
    public void previousQuiz(View v){
        Log.i("prevQ","" + myQuestions[quizNumber][0]);
        if (myQuestions[quizNumber][0] == "2"){
            EditText et_answer = (EditText) findViewById(R.id.layout2_answer);
            setSavedAnswer(et_answer.getText().toString());
            Log.i("prevQ",et_answer.getText().toString());
        }
        if (quizNumber == 1){
            quizNumber -= 1;
            setShowQuiz(Integer.parseInt(myQuestions[quizNumber][0]),quizNumber,false,true);
        }
        else {
            quizNumber -= 1;
            setShowQuiz(Integer.parseInt(myQuestions[quizNumber][0]),quizNumber,true,true);
        }
    }

    /**
     * This is triggered when a user select an answer or fill it in
     * It will store the selected/filled in answer to the user answer array
     * @param v
     */
    public void setUserAnswer(View v){
        if (Integer.parseInt(myQuestions[quizNumber][0]) == 1){
            RadioButton answer_a = (RadioButton) findViewById(R.id.radio_a);
            RadioButton answer_b = (RadioButton) findViewById(R.id.radio_b);
            RadioButton answer_c = (RadioButton) findViewById(R.id.radio_c);
            RadioButton answer_d = (RadioButton) findViewById(R.id.radio_d);
            RadioGroup group = (RadioGroup) findViewById(R.id.radio_group);

            if (answer_a.isChecked() == true){
                setSavedAnswer("1");
            }
            else if (answer_b.isChecked() == true){
                setSavedAnswer("2");
            }
            else if (answer_c.isChecked() == true){
                setSavedAnswer("3");
            }
            else if (answer_d.isChecked() == true){
                setSavedAnswer("4");
            }
        }

        else if(Integer.parseInt(myQuestions[quizNumber][0]) == 2){
            EditText answer = (EditText) findViewById(R.id.layout2_answer);
            String userAnswer = answer.getText().toString();
            setSavedAnswer(userAnswer);
        }

        else if(Integer.parseInt(myQuestions[quizNumber][0]) == 3){
            String answerString = "";

            CheckBox checkA = (CheckBox) findViewById(R.id.check_a);
            CheckBox checkB = (CheckBox) findViewById(R.id.check_b);
            CheckBox checkC = (CheckBox) findViewById(R.id.check_c);
            CheckBox checkD = (CheckBox) findViewById(R.id.check_d);

            if (checkA.isChecked() == true){
                answerString += "a";
            }
            if (checkB.isChecked() == true){
                answerString += "b";
            }
            if (checkC.isChecked() == true){
                answerString += "c";
            }
            if (checkD.isChecked() == true){
                answerString += "d";
            }

            setSavedAnswer(answerString);
        }


    }

    /**
     * this will trigger when setShowQuiz method is trying to fill in the quiz information
     * and its answer
     * @return the user's answer
     */
    public String getUserAnswer(){
        String userAnswered = userAnswer[quizNumber];
        return userAnswered;
    }

    public void setSavedAnswer(String answer){

        if (Integer.parseInt(myQuestions[quizNumber][0]) == 1){
            RadioButton answer_a = (RadioButton) findViewById(R.id.radio_a);
            RadioButton answer_b = (RadioButton) findViewById(R.id.radio_b);
            RadioButton answer_c = (RadioButton) findViewById(R.id.radio_c);
            RadioButton answer_d = (RadioButton) findViewById(R.id.radio_d);
            RadioGroup group = (RadioGroup) findViewById(R.id.radio_group);

            switch (answer){
                case "0":
                    group.clearCheck();
                    Log.i("switch", "0 fired");
                    break;
                case "1":
                    answer_a.setChecked(true);
                    Log.i("switch", "1 fired");
                    userAnswer[quizNumber] = "1";
                    break;
                case "2":
                    answer_b.setChecked(true);
                    Log.i("switch", "2 fired");
                    userAnswer[quizNumber] = "2";
                    break;
                case "3":
                    answer_c.setChecked(true);
                    Log.i("switch", "3 fired");
                    userAnswer[quizNumber] = "3";
                    break;
                case "4":
                    answer_d.setChecked(true);
                    Log.i("switch", "4 fired");
                    userAnswer[quizNumber] = "4";
                    break;
            }
        }

        else if(Integer.parseInt(myQuestions[quizNumber][0]) == 2){
            EditText savedAnswer = (EditText) findViewById(R.id.layout2_answer);
            savedAnswer.setText(answer);
            Log.i("answer", "" + answer + "for number " + quizNumber);
            userAnswer[quizNumber] = answer;
        }

        else if(Integer.parseInt(myQuestions[quizNumber][0]) == 3){
            CheckBox checkA = (CheckBox) findViewById(R.id.check_a);
            CheckBox checkB = (CheckBox) findViewById(R.id.check_b);
            CheckBox checkC = (CheckBox) findViewById(R.id.check_c);
            CheckBox checkD = (CheckBox) findViewById(R.id.check_d);

            Log.i("Check","" + answer);
            userAnswer[quizNumber] = answer;

            if ( userAnswer[quizNumber].indexOf('a') != -1){
                checkA.setChecked(true);
                Log.i("checkBox", "A is fired");
            }
            if ( userAnswer[quizNumber].indexOf('b') != -1){
                checkB.setChecked(true);
                Log.i("checkBox", "B is fired");
            }
            if ( userAnswer[quizNumber].indexOf('c') != -1){
                checkC.setChecked(true);
                Log.i("checkBox", "C is fired");
            }
            if ( userAnswer[quizNumber].indexOf('d') != -1){
                checkD.setChecked(true);
                Log.i("checkBox", "D is fired");
            }
        }

    }

    /**
     * display the total score at the result_page.xml
     */
    public void displayScore(){

        TextView scoreSheet = (TextView) findViewById(R.id.scoreSheet);
        TextView scoreTotal = (TextView) findViewById(R.id.totalScore);
        scoreSheet.setText(final_answer);
        scoreTotal.setText(total_score + "/10");
    }

    /**
     * display the answers if it was correct or not on result_page.xml
     * @param v
     */
    public void displayAnswerList(View v){

        final_answer = setAnswer().toString();
        setContentView(R.layout.result_page);
        quizScorer();
        displayScore();

    }

    /**
     * This checks if the user's answer is correct or not comparing to the answer array
     * @return the message to display the answers, used on displayAnswerList()
     */
    public String setAnswer(){
        String messaged = "";

        for(int i = 0; i < userAnswer.length; i++){

            String userAnswered = userAnswer[i].toString().toLowerCase().trim();
            String actualAnswer = myQuestions[i][1].toLowerCase().trim();

            if (i == 0){
                if (userAnswered.equals(actualAnswer) == true){
                    messaged += "Number " + (i+1) + " is CORRECT!";
                }
                else {
                    messaged += "Number " + (i+1) + " is WRONG!";
                }

            }
            else{
                if (userAnswered.equals(actualAnswer) == true){
                    messaged += "\nNumber " + (i+1) + " is CORRECT!";
                }
                else {
                    messaged += "\nNumber " + (i+1) + " is WRONG!";
                }
            }
        }
        return messaged;
    }

    /**
     * Counts the score
     */
    public void quizScorer(){

        int scoreTotal = 0;

        for(int i = 0; i <userAnswer.length; i++){

            String userAnswered = userAnswer[i].toString().toLowerCase().trim();
            String actualAnswer = myQuestions[i][1].toLowerCase().trim();

            if (userAnswered.equals(actualAnswer) == true){
                scoreTotal += 1;
                Log.i("compare the answer","" + userAnswer[i] + " vs " + myQuestions[i][1] + " is TRUE");
            }
            else {
                Log.i("compare the answer","" + userAnswer[i] + " vs " + myQuestions[i][1] + " is FALSE");
            }
        }
        total_score = Integer.toString(scoreTotal);
    }


    /**
     * This triggers a reset to the resetAnswer array when the user press the Reset Button
     */
    public void clearAnswer(){
        quizNumber = 0;
        String[] resetAnswer = {"0","0","0","0","0","","","","",""}; //setArray().toArray(new String[setArray().size()]);
        userAnswer = resetAnswer;
    }

    /**
     * Code below is to set the array dynamically depending on the total amount of questions
     */
//    public ArrayList<String> setArray(){
//        ArrayList<String> arrList = new ArrayList<String>();
//        String zero = "0";
//        String empty = "";
//
//        for(int i = 0; i < myQuestions.length; i++){
//            switch (myQuestions[i][0]){
//                case "1":
//                    arrList.add(zero);
//                case "2":
//                    arrList.add(empty);
//                case "3":
//                    arrList.add(empty);
//            }
//        }
//        return arrList;
//    }
}
