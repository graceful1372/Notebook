package com.example.notebook.model;

public class Notes {

    public int note_id;
    public String titleNote;
    public String contextNote;
    public String dateNote;
    public int color;


    public Notes(String title , String  contextNote , String dateNote , int color) {
        this.titleNote = title;
        this.contextNote = contextNote;
        this.dateNote = dateNote;
        this.color= color;
    }



    public Notes(){

    }




    //setter


    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public void setTitleNote(String title) {
        this.titleNote = title;
    }

    public void setContextNote(String contexNote) {
        this.contextNote = contexNote;
    }

    public void setDateNote(String dateNote) {
        this.dateNote = dateNote;
    }

    public void setColor(int color) {
        this.color = color;
    }

    //Getter

    public int getNote_id() {
        return note_id;
    }

    public String getTitleNote() {
        return titleNote;
    }

    public String getContextNote() {
        return contextNote;
    }

    public String getDateNote() {
        return dateNote;
    }

    public int getColor() {
        return color;
    }
}
