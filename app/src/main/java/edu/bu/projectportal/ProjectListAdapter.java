package edu.bu.projectportal;

import android.content.Intent;
import android.net.sip.SipSession;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.bu.projectportal.database.ProjectPortalDBContract;
import edu.bu.projectportal.database.ProjectPortalDBHelper;

public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ProjectListViewHolder>{
    private List<Project> projects;
    private Listener listener;

    interface Listener {
        abstract void onClick(int id, int position);
    }

    public ProjectListAdapter(List<Project> projects){this.projects = projects;}

    @Override
    public ProjectListViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.project_list_item, parent,
                        false);
        return new ProjectListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProjectListViewHolder viewHolder, final int position){
        viewHolder.projTitleView.setText(projects.get(position).getTitle());
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener = (Listener)view.getContext();
                if (listener != null) {
                    listener.onClick(projects.get(position).getId(),
                            projects.get((position +1)% projects.size()).getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }
    //ViewHolder inner class
    public static class ProjectListViewHolder extends RecyclerView.ViewHolder {
        private TextView projTitleView;
        private CardView cardView;
        public ProjectListViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView;
            projTitleView = (TextView) itemView.findViewById(R.id.projListTitleTextViewId);
        }
    }
}
