package com.example.notebook.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notebook.MyDatabase;
import com.example.notebook.R;
import com.example.notebook.model.Notes;

import java.util.ArrayList;
import java.util.List;

public class AdapterNote extends RecyclerView.Adapter<AdapterNote.ViewHodler> {
    List<Notes> list;
    Context context;
    private MyDatabase db;

    public AdapterNote(ArrayList<Notes> list, Context context) {
        this.list = list;
        this.context = context;
        db = new MyDatabase(context);
    }


    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_item_note, parent, false);

        return new ViewHodler(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler holder, final int position) {
        final Notes note = list.get(position);

        holder.title.setText(note.getTitleNote());
        holder.contextNote.setText(note.getContextNote());
        holder.time.setText(note.getDateNote());

        // This section is to show the background ringtone saved in Add Note Activity .
        holder.item_note.setBackgroundColor(note.getColor());


        holder.item_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle("حذف متن... ؟ ");
                dialog.setNegativeButton("تایید", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.deleteNote(note.getNote_id());
                        list.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeRemoved(position, list.size());

                    }
                }).show();






            }
        });
//method update
       /* Intent intent = new Intent(AdapterNote.this.context, NoteUpdate.class);
        intent.putExtra("id", note.getNote_id());
        intent.putExtra("title", note.getTitleNote());
        intent.putExtra("time", note.getDateNote());
        intent.putExtra("note", note.getContextNote());
        context.startActivity(intent);*/

//        delete note comment

//

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodler extends RecyclerView.ViewHolder {

        TextView title, contextNote, time;
        LinearLayout item_note;

        public ViewHodler(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txt_item_note_header);
            contextNote = itemView.findViewById(R.id.txt_item_note_text);
            time = itemView.findViewById(R.id.txt_item_note_time);
            item_note = itemView.findViewById(R.id.layout_note_item);

        }
    }
}
