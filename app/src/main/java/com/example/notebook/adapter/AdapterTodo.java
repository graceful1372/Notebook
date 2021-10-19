package com.example.notebook.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notebook.MyDatabase;
import com.example.notebook.R;
import com.example.notebook.model.Todo;

import java.util.ArrayList;
import java.util.List;


public class AdapterTodo extends RecyclerView.Adapter<AdapterTodo.ViewHolder> {
    List<Todo> list;
    Context context;
    private MyDatabase myDatabase;
    //    static SparseBooleanArray checkboxArray= new SparseBooleanArray();
    static SharedPreferences setting;


    public AdapterTodo(ArrayList<Todo> list, Context context) {
        this.list = list;
        this.context = context;
        myDatabase = new MyDatabase(context);
        setting = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());


    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_item_todo, parent, false);


        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final Todo todo = list.get(position);


        holder.tv_item.setText(todo.getTodo_name());
        holder.checkBox.setChecked(setting.getBoolean("check", false));
        holder.checkBox.setChecked(list.get(position).isCompleted());
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.get(position).setCompleted(!list.get(position).isCompleted());
                myDatabase.updateTodo(list.get(position));
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                myDatabase.deleteToDo(todo.getTodo_id());
                list.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeRemoved(position, list.size());


            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    //کلاسی برایی مقدار دهی کردن متغییر ها و ویجت ها
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_item;
        CheckBox checkBox;
//        ImageView iv_delete;


        public ViewHolder(View itemView) {
            super(itemView);
            tv_item = itemView.findViewById(R.id.tv_item);
            checkBox = itemView.findViewById(R.id.checkBox);
//            iv_delete = itemView.findViewById(R.id.iv_delete);


        }


    }
}


