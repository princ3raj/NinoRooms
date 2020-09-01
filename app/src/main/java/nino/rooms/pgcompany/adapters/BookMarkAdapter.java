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


public class BookMarkAdapter extends RecyclerView.Adapter<BookMarkAdapter.ViewHolder> {

    private static final String TAG = "BookMarkAdapter";

    private final ArrayList<NinoRooms> mHistories;

    private final Context context;
    private final OnBookMarkListener mOnBookMarkListener;

    public BookMarkAdapter(ArrayList<NinoRooms> histories, Context context, OnBookMarkListener onBookMarkListener) {
        this.context = context;
        mHistories = histories;
        mOnBookMarkListener = onBookMarkListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_bookmark_item_layout, parent, false);
        return new ViewHolder(view, mOnBookMarkListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        try {


            holder.pg_name.setText(mHistories.get(position).getPg_name());
            holder.location.setText(mHistories.get(position).getAddress());

//
//            holder.ac_prices.setText(mHistories.get(position).getAc_prices());


            if (Integer.parseInt(mHistories.get(position).getNon_ac_prices()) == 0) {
                holder.non_ac_prices.setText("Not Avail.");

            } else {
                holder.non_ac_prices.setText(String.format("Rs. %s", mHistories.get(position).getNon_ac_prices()));


            }


            Picasso.Builder builder = new Picasso.Builder(context);
            builder.downloader(new OkHttp3Downloader(context));
            builder.build().load(mHistories.get(position).getPg_image())
                    .placeholder((R.drawable.placeholderimage))
                    .error(R.drawable.placeholderimage)
                    .into(holder.pg_image);


        } catch (NullPointerException e) {
            Log.e(TAG, "onBindViewHolder: NullPointerExcception" + e.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return mHistories.size();
    }


    public interface OnBookMarkListener {
        void onBookMarkClick(int position);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView non_ac_prices;
        final TextView pg_name;
        final TextView ac_prices;
        final TextView location;
        final ImageView pg_image;
        final OnBookMarkListener mOnBookMarkListener;


        public ViewHolder(@NonNull View itemView, OnBookMarkListener onBookMarkListener) {
            super(itemView);
            non_ac_prices = itemView.findViewById(R.id.non_ac_price);

            pg_name = itemView.findViewById(R.id.pg_name);
            pg_image = itemView.findViewById(R.id.pg_image);

            ac_prices = itemView.findViewById(R.id.ac_price);
            location = itemView.findViewById(R.id.location);
            mOnBookMarkListener = onBookMarkListener;


            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            mOnBookMarkListener.onBookMarkClick(getAdapterPosition());

        }


    }

}
