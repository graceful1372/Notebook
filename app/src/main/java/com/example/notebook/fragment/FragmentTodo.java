package com.example.notebook.fragment;

import android.content.DialogInterface;
import android.os.Bundle;


import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;


import com.example.notebook.MyDatabase;
import com.example.notebook.R;
import com.example.notebook.adapter.AdapterTodo;
import com.example.notebook.model.Todo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FragmentTodo extends Fragment {
    View v;
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private ArrayList<Todo> list = new ArrayList<>();
    private MyDatabase db = null;
    private AlertDialog.Builder dialog;

    long todoid = -1;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_todo, container, false);
        fab = v.findViewById(R.id.fab_add_todo);
        recyclerView = v.findViewById(R.id.rc_frg_todo);





        db = new MyDatabase(getActivity());
        LoadDataInListView();


        dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle("Add Todo");



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                View view = getLayoutInflater().inflate(R.layout.dialog_todo, null);
                final EditText editText_todoName = view.findViewById(R.id.dialog_editText);
                dialog.setView(view);

                dialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (editText_todoName.getText().toString().length() > 0) {

                            Todo todo = new Todo();
                            todo.setTodo_name(editText_todoName.getText().toString());
                            todo.setTodo_id(todoid);
                            todo.setCompleted(false);
                            db.addToDo(todo);
//                            Toast.makeText(getContext(), i, Toast.LENGTH_SHORT).show();





//                            db.addToDo(new Todo(editText_todoName.getText().toString()));

                            LoadDataInListView();


                        }
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog.show();

            }
        });




        return v;


    }


    private void LoadDataInListView() {
        list.clear();

        AdapterTodo adapterTodo = new AdapterTodo(db.DB_getTodo(), getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapterTodo);
        adapterTodo.notifyDataSetChanged();


    }


}