package com.Mahesh;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    ArrayList<Model> Data;
    Context context;

    public Adapter(ArrayList<Model> Data, Context context) {
        this.Data = Data;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        Model temp = Data.get(position);
        holder.tv1.setText(temp.getName());
        holder.tv2.setText(temp.getContact());
        holder.tv3.setText(temp.getPassword());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, temp.getName()+ " will be updated ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,UpdateActivity.class);
                intent.putExtra("user",temp);
                context.startActivity(intent);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Confirmation !!!");
                builder.setMessage("Are you sure want to delete  " +temp.getName() + " ?");
                builder.setIcon(android.R.drawable.ic_menu_delete);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbHelper = new DBHelper(context);
                        int result = dbHelper.DeleteUser(temp.getId());
                        if (result>0){
                            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                            Data.remove(temp);
                            notifyDataSetChanged();
                        }else {
                            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("No",null);
                builder.setCancelable(false);
                builder.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return Data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv1, tv2, tv3;
        ImageView edit, delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);
            tv3 = itemView.findViewById(R.id.tv3);
            edit = itemView.findViewById(R.id.edit_icon);
            delete = itemView.findViewById(R.id.delete_icon);
        }
    }
}
