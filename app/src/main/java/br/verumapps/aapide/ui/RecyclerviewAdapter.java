package br.verumapps.aapide.ui;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import br.verumapps.aapide.R;
import br.verumapps.aapide.ui.RecyclerviewAdapter;
import java.util.List;


public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.MyViewHolder> {

    private List<User> userList;

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }
	//private final OnClickListener mOnClickListener = new MyOnClickListener();

	
    @Override
    public RecyclerviewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_project,parent,false);
      
		return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerviewAdapter.MyViewHolder holder, int position) {
        holder.tvTitle.setText(userList.get(position).getTitle());
		holder.tvDesc.setText(userList.get(position).getDesc());
		
    }

    @Override
    public int getItemCount() {
        if(userList != null){
            return userList.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
	implements View.OnClickListener {
        private TextView tvTitle;
		private TextView tvDesc;
		private ImageView icon;
		private LinearLayout body;
		
        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.title);
			
			tvDesc = (TextView) itemView.findViewById(R.id.description);
			icon = (ImageView) itemView.findViewById(R.id.icon);
			body = (LinearLayout) itemView.findViewById(R.id.body);
			itemView.setOnClickListener(this);
        }
		@Override
		public void onClick(View v) {
			Log.d("$$$", "Elemento " + getAdapterPosition() + " clicado.");
		}
    }
}
