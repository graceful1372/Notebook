package com.example.notebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.notebook.fragment.FragmentNotes;
import com.example.notebook.model.Notes;

public class NoteUpdate extends AppCompatActivity {
    EditText update_title, update_note;
    TextView time;
    private Button btn_back_save, btn_pink, btn_yellow, btn_cyan, btn_gary, btn_color;
    private Animation Open, Close, rotateBackward, rotateForward;
    private boolean isopen = true;
    MyDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_update);

        update_title = findViewById(R.id.update_title);
        update_note = findViewById(R.id.update_context);

        time = findViewById(R.id.update_dateTime);

        btn_back_save = findViewById(R.id.btn_update_backSave);
        btn_pink = findViewById(R.id.btn_update_pink);
        btn_yellow = findViewById(R.id.btn_update_yellow);
        btn_cyan = findViewById(R.id.btn_update_cyan);
        btn_gary = findViewById(R.id.btn_update_gary);
        btn_color = findViewById(R.id.btn_update_color);


        db = new MyDatabase(getBaseContext());


//get data from Adapter Note
        final String id =getIntent().getStringExtra("id");
//        Toast.makeText(getApplicationContext(), "do it with out id ", Toast.LENGTH_SHORT).show();
//        int id2 = 0;
//
//
////        ----------------what is that code ? and why used here ?
//
//        if (!TextUtils.isEmpty(id) && TextUtils.isDigitsOnly(id)) {
//            id2 = Integer.parseInt(id);
//        } else {
//            id2 = 0;
//        }
//
//        final int finalId = id2;
//        ---------------
        update_title.setText(getIntent().getStringExtra("title"));
        update_note.setText(getIntent().getStringExtra("note"));
        final String date = getIntent().getStringExtra("time");

        time.setText(date);


// intent to go activity
        final Intent intent = new Intent(this, FragmentNotes.class);


//color for save note
        final int colorCyan = getResources().getColor(R.color.cyan_background_note);
        final int colorYellow = getResources().getColor(R.color.yellow_background_note);
        final int colorGary = getResources().getColor(R.color.gary_background_note);
        final int colorWhite = getResources().getColor(R.color.white_background_note);
        final int colorPink = getResources().getColor(R.color.pink_background_note);

//animation button color
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





        // set OnClickListener for do act -----------------------------
        btn_back_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (update_title.getText().toString().length() > 0) {
//                    db.updateNote(new Notes(update_title.getText().toString(),
//                            update_note.getText().toString(),
//                            date,colorWhite));





                    db.updateNote(new Notes(
                            update_title.getText().toString(),
                            update_note.getText().toString(),
                            date, colorWhite),Integer.parseInt(id)
                    );


                }
                finish();
                startActivity(intent);


            }
        });

     /*   btn_gary.setOnClickListener(new View.OnClickListener() {
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
        });*/


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
