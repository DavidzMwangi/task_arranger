package com.davidwmwangi.tasks.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.davidwmwangi.tasks.R;
import com.davidwmwangi.tasks.TaskEditActivity;
import com.davidwmwangi.tasks.adapter.TaskListAdapter;
import com.squareup.picasso.Picasso;

/**
 * Created by David W. Mwangi on 30/09/2017.
 */

public class TaskEditFragment  extends Fragment {
    public static final String DEFAULT_FRAGMENT_TAG = "taskEditFragment";
    static final String TASK_ID = "taskId";

    //Views
    View rootView;
    EditText titleText;
    EditText notesText;
    ImageView imageView;

    long taskId;
    @Override
    public void  onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Bundle arguements=getArguments();
        if (arguements!=null){
            taskId=arguements.getLong(TaskEditActivity.EXTRA_TASKID,0L);
        }
        if (savedInstanceState!=null){
            taskId=savedInstanceState.getLong(TASK_ID);

        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        // This field may have changed while our activity was
        // running, so make sure we save it to our outState bundle so
        // we can restore it later in onCreate.
        outState.putLong(TASK_ID, taskId);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View v=inflater.inflate(R.layout.fragment_task_edit,container,false);
        rootView=v.getRootView();
        titleText=(EditText)v.findViewById(R.id.title);
        notesText=(EditText)v.findViewById(R.id.notes);
        imageView=(ImageView)v.findViewById(R.id.image);
        // Set the thumbnail image

        Picasso.with(getActivity())
                .load(TaskListAdapter.getImageUrlForTask((int)taskId))
                .into(imageView);
        return v;
    }
    public static  TaskEditFragment newInstance(long id){
        TaskEditFragment fragment=new TaskEditFragment();
        Bundle args=new Bundle();
        args.putLong(TaskEditActivity.EXTRA_TASKID,id);
        fragment.setArguments(args);
        return fragment;
    }
}
