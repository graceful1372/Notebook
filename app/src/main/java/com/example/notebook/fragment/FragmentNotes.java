package com.example.notebook.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.notebook.MyDatabase;
import com.example.notebook.R;
import com.example.notebook.NoteWrite;
import com.example.notebook.adapter.AdapterNote;
import com.example.notebook.model.Todo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class FragmentNotes extends Fragment {

    FloatingActionButton fab;
    private View v;
    private RecyclerView recyclerView;
    private ArrayList<Todo> list = new ArrayList<>();
    private MyDatabase db = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        v = inflater.inflate(R.layout.fragment_notes, container, false);

        db = new MyDatabase(getActivity());
        recyclerView = v.findViewById(R.id.rc_frg_note);
        fab = v.findViewById(R.id.fab_add_note);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NoteWrite.class);
                startActivity(intent);
            }
        });

        show();

        return v;
    }

    private void show() {
        AdapterNote adapterNote = new AdapterNote(db.DB_getNote(), getContext());
        StaggeredGridLayoutManager sg = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(sg);
        recyclerView.setAdapter(adapterNote);
        adapterNote.notifyDataSetChanged();
    }


}