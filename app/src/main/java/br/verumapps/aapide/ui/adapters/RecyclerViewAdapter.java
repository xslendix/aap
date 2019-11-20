package br.verumapps.aapide.ui.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import br.verumapps.aapide.R;
import br.verumapps.aapide.manager.ProjectInfo;
import java.util.List;
import br.verumapps.aapide.ui.*;
import android.widget.Toast;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ProjectListVH>
{

    private List<ProjectInfo> projectList;

    public void setProjectList (List<ProjectInfo> projectList)
    {
        this.projectList = projectList;
        notifyDataSetChanged();
    }

    //private final OnClickListener mOnClickListener = new MyOnClickListener();

    @Override
    public RecyclerViewAdapter.ProjectListVH onCreateViewHolder (ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_project, parent, false);
        return new ProjectListVH(view);
    }

    @Override
    public void onBindViewHolder (RecyclerViewAdapter.ProjectListVH holder, int position)
    {
        holder.tvTitle.setText(projectList.get(position).getTitle());
        holder.tvDesc.setText(projectList.get(position).getDesc());
    }

    @Override
    public int getItemCount ()
    {
        if (projectList != null)
        {
            return projectList.size();
        }
        return 0;
    }

    public class ProjectListVH extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
    {

        private TextView tvTitle;
        private TextView tvDesc;
        private ImageView icon;
        private LinearLayout body;

        public ProjectListVH (View itemView)
        {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.title);
            tvDesc = itemView.findViewById(R.id.description);
            icon = itemView.findViewById(R.id.icon);
            body = itemView.findViewById(R.id.body);
            itemView.setOnClickListener(this);
			itemView.setOnLongClickListener(this);
        }
        @Override
        public void onClick (View v)
        {

			Intent i = new Intent();
			i.setClass(v.getContext(), FileExplorer.class);
			i.putExtra("path",projectList.get(getAdapterPosition()).getDesc());
			v.getContext().startActivity(i);

        }
		@Override
        public boolean onLongClick (View v)
        {

			Toast.makeText(v.getContext(),projectList.get(getAdapterPosition()).getDesc(), 0).show();
			
           return true;

        }
    }
}
