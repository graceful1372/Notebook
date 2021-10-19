package com.example.notebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.notebook.model.Notes;

public class NoteWrite extends AppCompatActivity {
    EditText title, contextNote;
    TextView txtDate;
    Button btn_back_save, btn_pink, btn_yellow, btn_cyan, btn_gary, btn_color;
    MyDatabase dbw;
    Animation Open, Close, rotateBackward, rotateForward;
    boolean isopen = true;


    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


        setContentView(R.layout.note_enter);


        title = findViewById(R.id.enter_title);
        contextNote = findViewById(R.id.enter_context);
        txtDate = findViewById(R.id.enter_dateTime);
        btn_back_save = findViewById(R.id.btn_backSave);
        btn_pink = findViewById(R.id.btn_pink);
        btn_yellow = findViewById(R.id.btn_yellow);
        btn_cyan = findViewById(R.id.btn_cyan);
        btn_gary = findViewById(R.id.btn_gary);
        btn_color = findViewById(R.id.btn_color);

        CalendarTool calendarTool = new CalendarTool();
        String date = calendarTool.getIranianYear() + "/" + calendarTool.getIranianMonth() + "/" + calendarTool.getIranianDay();
        dbw = new MyDatabase(getBaseContext());
        txtDate.setText(date);
        final Intent intent = new Intent(this, MainActivity.class);

        final int colorCyan = getResources().getColor(R.color.cyan_background_note);
        final int colorYellow = getResources().getColor(R.color.yellow_background_note);
        final int colorGary = getResources().getColor(R.color.gary_background_note);
        final int colorWhite = getResources().getColor(R.color.white_background_note);
        final int colorPink = getResources().getColor(R.color.pink_background_note);

//        animation button color
        Open = AnimationUtils.loadAnimation(this, R.anim.button_open);
        Close = AnimationUtils.loadAnimation(this, R.anim.button_close);
        rotateBackward = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);
        rotateForward = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
        btn_color.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                animation();
            }
        });


        btn_back_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (title.getText().toString().length() > 0) {

                    dbw.addNote(new Notes(
                            title.getText().toString(),
                            contextNote.getText().toString(),
                            txtDate.getText().toString(),
                            colorWhite));

                    Notes notes = new Notes();
                    int id = notes.getNote_id();
                    Toast.makeText(getApplicationContext(),id, Toast.LENGTH_SHORT).show();


                    finish();
                    overridePendingTransition(1, 2);


//
                    startActivity( intent);


                }
                else {

                    finish();

                    startActivity(intent);
                }


            }
        });

        btn_gary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                contextNote.setBackgroundColor(colorGary);
                if (title.getText().toString().length() > 0) {

                    dbw.addNote(new Notes(title.getText().toString(),
                            contextNote.getText().toString(),
                            txtDate.getText().toString(),
                            colorGary));

                    startActivity(intent);

                } else {
//
                    startActivity(intent);
                }
            }
        });

        btn_pink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (title.getText().toString().length() > 0) {

                    dbw.addNote(new Notes(title.getText().toString(),
                            contextNote.getText().toString(),
                            txtDate.getText().toString(),
                            colorPink));

                    startActivity(intent);

                } else {

                    startActivity(intent);
                }
            }
        });
        btn_cyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (title.getText().toString().length() > 0) {

                    dbw.addNote(new Notes(title.getText().toString(),
                            contextNote.getText().toString(),
                            txtDate.getText().toString(),
                            colorCyan));

                    startActivity(intent);

                } else {

                    startActivity(intent);
                }
            }
        });
        btn_yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (title.getText().toString().length() > 0) {

                    dbw.addNote(new Notes(title.getText().toString(),
                            contextNote.getText().toString(),
                            txtDate.getText().toString(),
                            colorYellow));

                    startActivity(intent);

                } else {

                    startActivity(intent);
                }
            }
        });


    }

    private void animation() {
        if (isopen) {
            btn_color.startAnimation(rotateForward);
            btn_yellow.startAnimation(Open);
            btn_cyan.startAnimation(Open);
            btn_pink.startAnimation(Open);
            btn_gary.startAnimation(Open);
            btn_yellow.setClickable(true);
            btn_cyan.setClickable(true);
            btn_pink.setClickable(true);
            btn_gary.setClickable(true);
            isopen = false;
        } else {
            btn_color.startAnimation(rotateBackward);
            btn_yellow.startAnimation(Close);
            btn_cyan.startAnimation(Close);
            btn_pink.startAnimation(Close);
            btn_gary.startAnimation(Close);
            btn_yellow.setClickable(true);
            btn_cyan.setClickable(true);
            btn_pink.setClickable(true);
            btn_gary.setClickable(true);
            isopen = true;

        }


    }


}

