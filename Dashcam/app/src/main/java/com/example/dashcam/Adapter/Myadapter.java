package com.example.dashcam.Adapter;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dashcam.BuildConfig;
import com.example.dashcam.Imageviewer;
import com.example.dashcam.R;
import com.example.dashcam.model.snaps;
import com.example.dashcam.videoplayer;

import java.io.File;
import java.io.OutputStream;
import java.net.URLConnection;
import java.util.ArrayList;

public class Myadapter extends RecyclerView.Adapter<Myadapter.viewholder> {
    Context context;
    ArrayList<snaps> arrayList;

    public Myadapter(Context context, ArrayList<snaps> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.snapslist, parent, false);

        return new viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, @SuppressLint("RecyclerView") int position) {

        snaps s = arrayList.get(position);
        holder.txt.setText(String.valueOf(s.getDate()));
        holder.mem.setText(String.valueOf(s.getSize()));


        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Imageviewer.class);
                intent.putExtra("picname", arrayList.get(position).getName());
                context.startActivity(intent);

            }


        });

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(arrayList.get(position).getUri());
                Drawable mDrawable = holder.img.getDrawable();
                Bitmap mBitmap = ((BitmapDrawable)mDrawable).getBitmap();
                Uri uri = Uri.parse(String.valueOf(file));
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                shareIntent.setType("image/*");
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
        Glide.with(holder.img.getContext()).load(s.getUri()).into(holder.img);


    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView txt, txts, mem;
        ImageView img, delete, share;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.imgname);
            img = itemView.findViewById(R.id.img);
            delete = itemView.findViewById(R.id.dt);
            share = itemView.findViewById(R.id.sh);
            mem=itemView.findViewById(R.id.size);

        }
    }


}