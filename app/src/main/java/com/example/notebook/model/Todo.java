package com.example.notebook.model;

 public class Todo {
    public long todo_id;
    public String todo_name;
    private Boolean isCompleted;

    public Todo(String todo_name , Boolean isCompleted ) {
//        this.todo_id = todo_id;
        this.todo_name = todo_name;
        this.isCompleted = isCompleted;
    }

    public Todo(){}

    public long getTodo_id() {
        return todo_id;
    }

    public void setTodo_id(long todo_id) {
        this.todo_id = todo_id;
    }

    public String getTodo_name() {
        return todo_name;
    }

    public void setTodo_name(String todo_name) {
        this.todo_name = todo_name;
    }
    public final Boolean isCompleted (){
        return this.isCompleted;

    }
     public final void setCompleted(Boolean var1) {
         this.isCompleted = var1;
     }


 }
