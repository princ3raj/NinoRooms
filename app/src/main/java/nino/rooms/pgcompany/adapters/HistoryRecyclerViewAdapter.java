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

    private final ArrayList<NinoRooms> mHistories;

    private final Context context;
    private final OnHistoryListener mOnHistoryListener;

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


            //i have used address in place of location, otherwise i had to
            //made changes everywhere

            holder.pg_name.setText(mHistories.get(position).getPg_name());
            holder.pg_type.setText(mHistories.get(position).getPg_type());
            holder.address.setText(mHistories.get(position).getAddress());

            if (Integer.parseInt(mHistories.get(position).getAc_prices()) == 0) {
                holder.ac_prices.setText("Not Avail.");

            } else {
                holder.ac_prices.setText(String.format("Ac:%s", mHistories.get(position).getAc_prices()));

            }


            if (Integer.parseInt(mHistories.get(position).getNon_ac_prices()) == 0) {
                holder.non_ac_prices.setText("NA");

            } else {
                holder.non_ac_prices.setText(String.format("Non-Ac:%s", mHistories.get(position).getNon_ac_prices()));

            }


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


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView ac_prices;
        final TextView non_ac_prices;
        final TextView pg_name;
        final TextView address;
        final TextView pg_type;
        final ImageView pg_image;
        final OnHistoryListener mOnHistoryListener;


        public ViewHolder(@NonNull View itemView, OnHistoryListener onHistoryListener) {
            super(itemView);
            ac_prices = itemView.findViewById(R.id.ac_price);
            non_ac_prices = itemView.findViewById(R.id.non_ac_price);
            pg_name = itemView.findViewById(R.id.pg_name);
            pg_image = itemView.findViewById(R.id.pg_image);
            address = itemView.findViewById(R.id.address);
            pg_type = itemView.findViewById(R.id.pg_type);
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
