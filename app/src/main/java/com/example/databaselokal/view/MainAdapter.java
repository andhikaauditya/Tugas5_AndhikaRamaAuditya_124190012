package com.example.databaselokal.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaselokal.R;
import com.example.databaselokal.entity.Dataperpus;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.viewHolder> {
    Context context;
    List<Dataperpus> list;
    MainContact.view mView;

    public MainAdapter(Context context, List<Dataperpus> list, MainContact.view mView) {
        this.context = context;
        this.list = list;
        this.mView = mView;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_perpus,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final Dataperpus item = list.get(position);
        holder.tv_itemnama.setText(item.getNamainstusi());
        holder.tv_itempengunjung.setText(item.getNamapengunjung());
        holder.tv_itemalamat.setText(item.getAlamat());
        holder.tv_itemid.setText(Integer.toString(item.getId()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView.editData(item);
            }
        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mView.deleteData(item);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tv_itemnama,tv_itempengunjung,tv_itemalamat,tv_itemid;
        CardView cardView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tv_itemnama = itemView.findViewById(R.id.tv_itemnama);
            tv_itempengunjung = itemView.findViewById(R.id.tv_itempengunjung);
            tv_itemalamat = itemView.findViewById(R.id.tv_itemalamat);
            tv_itemid = itemView.findViewById(R.id.tv_itemid);
            cardView = itemView.findViewById(R.id.cv_item);

        }
    }
}
