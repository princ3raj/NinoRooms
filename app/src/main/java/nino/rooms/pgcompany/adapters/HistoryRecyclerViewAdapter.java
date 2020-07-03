package nino.rooms.pgcompany.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import nino.rooms.pgcompany.R;
import nino.rooms.pgcompany.model.NinoRooms;


public class HistoryRecyclerViewAdapter extends RecyclerView.Adapter<HistoryRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "HistoryRecyclerViewAdap";

    private ArrayList<NinoRooms> mHistories = new ArrayList<>();

    private Context context;
    private OnHistoryListener mOnHistoryListener;

    public HistoryRecyclerViewAdapter(ArrayList<NinoRooms> histories, Context context, OnHistoryListener onHistoryListener) {
        this.context = context;
        mHistories = histories;
        mOnHistoryListener = onHistoryListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item_view, parent, false);
        return new ViewHolder(view, mOnHistoryListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        try {


            holder.pg_name.setText(mHistories.get(position).getPg_name());
            holder.location.setText(mHistories.get(position).getLocation());
            holder.details.setText(mHistories.get(position).getDetails());
            holder.ac_prices.setText(mHistories.get(position).getAc_prices());

            Picasso.Builder builder = new Picasso.Builder(context);
            builder.downloader(new OkHttp3Downloader(context));
            builder.build().load(mHistories.get(position).getPg_image())
                    .placeholder((R.drawable.ic_launcher_background))
                    .error(R.drawable.ic_launcher_background)
                    .into(holder.pg_image);


        } catch (NullPointerException e) {
            Log.e(TAG, "onBindViewHolder: NullPointerExcception" + e.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return mHistories.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView ac_prices;
        TextView pg_name;
        TextView details;
        TextView location;
        ImageView pg_image;
        OnHistoryListener mOnHistoryListener;


        public ViewHolder(@NonNull View itemView, OnHistoryListener onHistoryListener) {
            super(itemView);
            ac_prices = itemView.findViewById(R.id.ac_price);
            pg_name = itemView.findViewById(R.id.pg_name);
            pg_image = itemView.findViewById(R.id.pg_image);
            details = itemView.findViewById(R.id.details);
            location = itemView.findViewById(R.id.location);
            mOnHistoryListener = onHistoryListener;


            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            mOnHistoryListener.onHistoryClick(getAdapterPosition());

        }


    }

    public interface OnHistoryListener {
        void onHistoryClick(int position);

    }

}