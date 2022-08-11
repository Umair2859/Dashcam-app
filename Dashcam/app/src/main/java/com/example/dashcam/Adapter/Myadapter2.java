package com.example.dashcam.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dashcam.R;
import com.example.dashcam.model.videos;
import com.example.dashcam.videoplayer;

import java.io.File;
import java.util.ArrayList;

public class Myadapter2 extends RecyclerView.Adapter<Myadapter2.viewholder> {
    Context context;
    ArrayList<videos> arrayList;


    public Myadapter2(Context context, ArrayList<videos> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, @SuppressLint("RecyclerView") int position) {

        videos v = arrayList.get(position);
        holder.mb.setText(String.valueOf(v.getSize()));
        holder.title.setText(String.valueOf(v.getDate()));

        Glide.with(holder.thumbnail.getContext()).load(v.getUri()).into(holder.thumbnail);
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(arrayList.get(position).getUri());
                Uri uri = Uri.parse(String.valueOf(file));
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                shareIntent.setType("videos/*");
                context.startActivity(shareIntent);



            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(v.getRootView().getContext());
                View dialogView= LayoutInflater.from(v.getRootView().getContext()).inflate(R.layout.deletecustom,null);
                TextView textView;
                TextView T2;
                textView=dialogView.findViewById(R.id.pos);
                T2=dialogView.findViewById(R.id.neg);
                builder.setView(dialogView);
                builder.setCancelable(true);
                final AlertDialog show = builder.show();
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        File file = new File(arrayList.get(position).getUri());
                        file.delete();
                        arrayList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, arrayList.size());
                        notifyDataSetChanged();
                        show.dismiss();

                    }
                });
                T2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       show.dismiss();
                    }
                });




            }


        });

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context.getApplicationContext(), arrayList.get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, videoplayer.class);
                intent.putExtra("title", arrayList.get(position).getName());
                context.startActivity(intent);


            }


        });


    }

    private int getFile_size(int file_size) {
        return file_size;
    }


    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.videoslist, parent, false);

        return new viewholder(v);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class viewholder extends RecyclerView.ViewHolder {
        TextView title,mb;
        ImageView thumbnail;
        LinearLayout ll;
        ImageView delete, share;
        Dialog mdialog1;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.vidname);
            thumbnail = itemView.findViewById(R.id.thumb);
            ll = itemView.findViewById(R.id.linear);
            delete = itemView.findViewById(R.id.dlt);
            share = itemView.findViewById(R.id.shr);
            mb=itemView.findViewById(R.id.sizee);


        }
    }
}



