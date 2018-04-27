package com.davidwmwangi.tasks.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.davidwmwangi.tasks.R;
import com.davidwmwangi.tasks.interfaces.OnEditTask;
import com.squareup.picasso.Picasso;


/**
 * Created by David W.Mwangi on 9/14/2017.
 */

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {



    static String[] fakeData=new String[]{
            "One",
            "Two",
            "Three",
            "Four",
            "Five",
            "Ah....ah...ah!"
    };

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i){
        //create a new view
        CardView v=(CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_task,parent,false);

        //wrap it in a ViewHolder
        return new ViewHolder(v);



    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder,final int i){
       final Context context= viewHolder.titleView.getContext();
        viewHolder.titleView.setText(fakeData[i]);
        //set the thumbnail image
        Picasso.with(context).load(getImageUrlForTask(i)).into(viewHolder.imageView);
        // Set the click action
        viewHolder.cardView.setOnClickListener(
        new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((OnEditTask) context).editTask(i);
            }
        });
        viewHolder.cardView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view){
              Toast.makeText(context,"Card View Number:" + i,Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount(){
        return fakeData.length;
    }
    public static String getImageUrlForTask(int taskId){
           try{
               return "http://lorempixel.com/600/400/cats/?fakeId=" + taskId;
           }catch(Exception e){
              return "No Pictures Loaded"+ taskId;


           }



    }
    static class ViewHolder extends RecyclerView.ViewHolder{
     CardView cardView;
        TextView titleView;
        ImageView imageView;

        public ViewHolder(CardView card){
            super(card);
            cardView=card;
            titleView=(TextView)card.findViewById(R.id.text1);
            imageView=(ImageView)card.findViewById(R.id.image);
        }

    }
}
