package edu.kvcc.cis298.cis298inclass1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    //variables
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    //Variable for the question string
    private TextView mQuestionTextView;

    //The questions that will be used. It is an array of type Question, that contains 5 Q's.
    //It has a hard coded array. In most apps, you would want your data to come from somewhere
    //else (database/internet), without hardcoding.
    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_lansing, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };

    private int mCurrentIndex = 0;

    //Private methods that will be used inside the OnCreate.
    //I wrote these.

    private void updateQuestion() {
        //Get the Question instance stored at the mCurrentIndex of the QuestionBank array.
        //Then call the getTextResId method (property) to return the integer value that
        //points to the string resource in strings.xml that we want to use.
        int question = mQuestionBank[mCurrentIndex].getTextResId();

        //Assign the integer for the string resource to the textview so that the question
        //text will display.
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){
        //create a boolean to represent the action answer of the current question we're on.
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        //declare an integer that will be a pointer to the string resource that will be used
        //for the toast message.
        int messageResId = 0;

        //Compare the actual answer to the answer that was passed into this method. If they
        //match, the message is correct, else it is incorrect. Assign the R in value to the
        //messageResId
        if (userPressedTrue == answerIsTrue){
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }

        //Make a toast, and use the messageResId for the message to show.
        //Use the Toast class to print a message to the screen that will fade out after
        //the duration listed as LENGTH_SHORT
        //This method requires 3 parameters:
        //1 Context, which usually be Activity.this,
        //2 Message, which will usually be a string from strings .xml
        //3 Length, which will be one of the predefined constants
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //This is the setup method for the app, provided by google
        //This is called when the app launches
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //BEGIN CODE I WROTE:-------------------------------------------------------------------

        //Get a 'handle' to the textview in the layout
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        //Fetch the widget control from the view, then cast and assign it to the class variable
        //we set up
        mTrueButton = (Button) findViewById(R.id.true_button);
        //Now that I have a 'handle' to the view widget, I can
        //Setup an OnClickListener for the widget
        //This OnClickListener uses an anonymous inner class.
        //We are passing what we want to have happen onClick.
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Call the checkAnswer method and pass true. It will take in the bool value
                //that was selected, and do the work of determining if the answer is correct.
                //Either way, it will Toast the message to the screen.
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Call the checkAnswer method and pass false.
                checkAnswer(false);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex +1) % mQuestionBank.length;
                //This method is declared above. It does the work of changing questions in the array.
                updateQuestion();
            }

        });

        //This method is declared above. It does the work of changing questions in the array.
        updateQuestion();
    }

    //These are methods that we did not write, but google provided.
    //If we get to using menus, we will need them. They can be ignored for now.

    //Begin unneeded google methods -------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
