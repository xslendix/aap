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
import br.verumapps.aapide.manager.User;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>
{

    private List<User> userList;

    public void setUserList (List<User> userList)
    {
        this.userList = userList;
        notifyDataSetChanged();
    }

    //private final OnClickListener mOnClickListener = new MyOnClickListener();

    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_project, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder (RecyclerViewAdapter.MyViewHolder holder, int position)
    {
        holder.tvTitle.setText(userList.get(position).getTitle());
        holder.tvDesc.setText(userList.get(position).getDesc());
    }

    @Override
    public int getItemCount ()
    {
        if (userList != null)
        {
            return userList.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        private TextView tvTitle;
        private TextView tvDesc;
        private ImageView icon;
        private LinearLayout body;

        public MyViewHolder (View itemView)
        {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.title);
            tvDesc = itemView.findViewById(R.id.description);
            icon = itemView.findViewById(R.id.icon);
            body = itemView.findViewById(R.id.body);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick (View v)
        {
            Intent intent = new Intent();
			intent.setClass(v.getContext(), br.verumapps.aapide.ui.FileExplorer.class);
			intent.putExtra("path", userList.get(getAdapterPosition()).getDesc());
			v.getContext().startActivity(intent);


        }
    }
}
