package com.example.zad1am;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_CURRENT_INDEX = "currentIndex";
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionTextView;
    private TextView counterCorrect;

    private static final String TAG = "ZAD1AM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            currentIndex = savedInstanceState.getInt(KEY_CURRENT_INDEX);
        }

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.question_text_view);
        counterCorrect = findViewById(R.id.counter_correct);

        trueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                checkCorrect(true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                checkCorrect(false);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex+1)%questions.length;
                setNextQuestion();
            }
        });
        setNextQuestion();

    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.d(TAG, "WYWOŁANA ZOSTAŁA METODA onSaveInstanceState");
        outState.putInt(KEY_CURRENT_INDEX,currentIndex);
    }

    private Question[] questions = new Question[]{
            new Question(R.string.q_cpp,false),
            new Question(R.string.q_java,true),
            new Question(R.string.q_python,false),
            new Question(R.string.q_csharp,false),
            new Question(R.string.q_js,false),
            new Question(R.string.q_php,true)
    };

    private int currentIndex = 0;

    public int correctCounter = 0;

    private void checkCorrect(boolean answer){
        boolean correctAnswer = questions[currentIndex].isTrueAnswer();
        int resultMessageId = 0;
        if(answer == correctAnswer){
            resultMessageId = R.string.correct_answer;
            if(correctCounter <6){correctCounter = correctCounter+1;}
            counterCorrect.setText(String.valueOf(correctCounter)+"/6 poprawnych odpowiedzi");
        }else{
            resultMessageId = R.string.incorrect_answer;
            counterCorrect.setText(String.valueOf(correctCounter)+"/6 poprawnych odpowiedzi");
        }
        Toast.makeText(this,resultMessageId,Toast.LENGTH_SHORT).show();
    }


    private void setNextQuestion() {
        questionTextView.setText(questions[currentIndex].getQuestionId());
    }


}