package com.example.notebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.example.notebook.model.Notes;
import com.example.notebook.model.Todo;

import java.util.ArrayList;

import static com.example.notebook.Const.COLUMN_NOTE_COLOR;
import static com.example.notebook.Const.COLUMN_NOTE_CONTEXT;
import static com.example.notebook.Const.COLUMN_NOTE_DATETIME;
import static com.example.notebook.Const.COLUMN_NOTE_ID;
import static com.example.notebook.Const.COLUMN_NOTE_TITLE;
import static com.example.notebook.Const.COLUMN_TODO_ID;
import static com.example.notebook.Const.COLUMN_TODO_NAME;
import static com.example.notebook.Const.DB_NAME;
import static com.example.notebook.Const.DB_VERSION;
import static com.example.notebook.Const.IS_COMPLETED;
import static com.example.notebook.Const.TABLE_NOTE;
import static com.example.notebook.Const.TABLE_TODO;


public class MyDatabase extends SQLiteOpenHelper {
    CalendarTool calendarTool = new CalendarTool();
//    String date = calendarTool.getIranianYear() + "/" + calendarTool.getIranianMonth() + "/" + calendarTool.getIranianDay();


    private final String CreateTableTodo =
            "CREATE TABLE " + TABLE_TODO + " (" +
                    COLUMN_TODO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                    IS_COMPLETED + " INTEGER," +
                    COLUMN_TODO_NAME + " TEXT) ;";


    private final String CreateTableNote =
            "CREATE TABLE " + TABLE_NOTE + " (" +
                    COLUMN_NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOTE_TITLE + " TEXT," +
                    COLUMN_NOTE_CONTEXT + " TEXT," +
                    COLUMN_NOTE_COLOR + " INTEGER," +
                    COLUMN_NOTE_DATETIME + " DATETIME) ";


    public MyDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(CreateTableTodo);
        Log.i("hamed", "onCreate: table todo created");
        db.execSQL(CreateTableNote);
        Log.i("hamed", "onCreate: table note created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }






//        ------------------------Todo table methods ---------------------------

    public void addToDo(Todo todo_object1) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

//        cv.put(COLUMN_TODO_ID, todo_object1.getTodo_id());
        cv.put(COLUMN_TODO_NAME, todo_object1.getTodo_name());
        cv.put(IS_COMPLETED, todo_object1.isCompleted());

//        long id = db.insert(TABLE_TODO, null, cv);
//        return id ;
        db.insert(TABLE_TODO, null, cv);
        db.close();
//        Log.i("hamed", "addToDo: insert do correct ");


    }

    public ArrayList<Todo> DB_getTodo() {
        ArrayList<Todo> res = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * from " + TABLE_TODO;


        Cursor queryResult = db.rawQuery(select, null);


        if (queryResult.moveToFirst()) {
            do {
                Todo model = new Todo();


                model.setTodo_id(queryResult.getInt(queryResult.getColumnIndex(COLUMN_TODO_ID)));
                /*model.setId_todo(Integer.parseInt(queryResult.getString(0)));
                model.setTodo_name(queryResult.getString(1));*/
                model.setTodo_name(queryResult.getString(queryResult.getColumnIndex(COLUMN_TODO_NAME)));
                model.setCompleted(queryResult.getInt(queryResult.getColumnIndex(IS_COMPLETED))==1);
                
            res.add(model);

            }
            while (queryResult.moveToNext());

        }
        return res;
    }

    public void deleteToDo(long id) {


        SQLiteDatabase db = this.getWritableDatabase();
        //correct 1
//        db.execSQL("DELETE FROM " + TABLE_TODO + " WHERE " + COLUMN_TODO_ID + "= " + id  );
//        db.close();
        //correct 2
        db.delete(TABLE_TODO, id + "=" + COLUMN_TODO_ID, null);


        // no such column
//        db.delete(TABLE_TODO, "id=" + id, null);


        db.close();
    }

    public void updateTodo(Todo todo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TODO_NAME, todo.getTodo_name());
        cv.put(COLUMN_TODO_ID, todo.getTodo_id());
        cv.put(IS_COMPLETED, todo.isCompleted());

        db.update(TABLE_TODO, cv, todo.getTodo_id() + " = " + COLUMN_TODO_ID, null);


    }

    public void updateCompleteStatus(Integer todoId) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor queryResult = db.rawQuery("SELECT * FROM " + TABLE_TODO + " WHERE " + COLUMN_TODO_ID + "=?" + todoId, null);
        if (queryResult.moveToFirst()) {
            do {
                Todo item = new Todo();
                item.setTodo_id(queryResult.getInt(queryResult.getColumnIndex(COLUMN_TODO_ID)));
                item.setTodo_name(queryResult.getString(queryResult.getColumnIndex(COLUMN_TODO_NAME)));
//                item.setCompleted(isCompleted);
                updateTodo(item);

            } while (queryResult.moveToNext());
        }
        queryResult.close();

    }


//    -------------------- Note table methods-----------------------------

    public void addNote(Notes notes) {

        SQLiteDatabase dbn = this.getWritableDatabase();
        ContentValues cb = new ContentValues();

        cb.put(COLUMN_NOTE_TITLE, notes.getTitleNote());
        cb.put(COLUMN_NOTE_CONTEXT, notes.getContextNote());
        cb.put(COLUMN_NOTE_DATETIME, notes.getDateNote());
        cb.put(COLUMN_NOTE_COLOR, notes.getColor());

        dbn.insert(TABLE_NOTE, null, cb);
        dbn.close();
        Log.i("hamed", "add to table note : insert do it  ");


    }

    public ArrayList<Notes> DB_getNote() {
        ArrayList<Notes> noteArray = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectNote = "SELECT * from " + TABLE_NOTE;
        Cursor readNoteCursor = db.rawQuery(selectNote, null);


        if (readNoteCursor.moveToFirst()) {
            do {
                Notes notes = new Notes();

                notes.setNote_id(readNoteCursor.getInt(readNoteCursor.getColumnIndex(COLUMN_NOTE_ID)));
                notes.setTitleNote(readNoteCursor.getString(readNoteCursor.getColumnIndex(COLUMN_NOTE_TITLE)));
                notes.setContextNote(readNoteCursor.getString(readNoteCursor.getColumnIndex(COLUMN_NOTE_CONTEXT)));
                notes.setDateNote(readNoteCursor.getString(readNoteCursor.getColumnIndex(COLUMN_NOTE_DATETIME)));
                notes.setColor(readNoteCursor.getInt(readNoteCursor.getColumnIndex(COLUMN_NOTE_COLOR)));

                noteArray.add(notes);

            } while (readNoteCursor.moveToNext());
        }
        return noteArray;

    }

    public void deleteNote(int id_note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NOTE, id_note + "=" + COLUMN_NOTE_ID, null);

        db.close();


    }

    public void updateNote(Notes notes , int id ) {

        SQLiteDatabase dbn = this.getWritableDatabase();
        ContentValues cb = new ContentValues();

        cb.put(COLUMN_NOTE_TITLE, notes.getTitleNote());
        cb.put(COLUMN_NOTE_CONTEXT, notes.getContextNote());
        cb.put(COLUMN_NOTE_DATETIME, notes.getDateNote());
        cb.put(COLUMN_NOTE_COLOR, notes.getColor());


//        -----
//        dbn.update(TABLE_TODO, cb, COLUMN_NOTE_ID + "=?", new String[]{String.valueOf(notes.getNote_id())});
//----


        dbn.update(TABLE_NOTE, cb, "id=" + id, null);
        dbn.close();

    }

}
