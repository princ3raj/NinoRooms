package nino.rooms.pgcompany.adapters;


import android.content.Context;
import android.content.Intent;
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

import java.util.List;

import nino.rooms.pgcompany.R;
import nino.rooms.pgcompany.RoomDetailsActivity;
import nino.rooms.pgcompany.model.NinoRooms;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private static final String TAG = "CustomAdapter";
    private final List<NinoRooms> mNinoRooms;
    private final Context context;
    private static final String EXTRA_TEXT = "position";

    public CustomAdapter(Context context, List<NinoRooms> ninoRooms) {
        this.context = context;
        this.mNinoRooms = ninoRooms;
        Log.d(TAG, "CustomAdapter:called " + ninoRooms);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position) {
        holder.pg_name.setText(mNinoRooms.get(position).getPg_name());
        holder.address.setText(mNinoRooms.get(position).getAddress());
        holder.pg_type.setText(mNinoRooms.get(position).getPg_type());
        if (Integer.parseInt(mNinoRooms.get(position).getAc_prices()) == 0) {
            holder.ac_prices.setText("Not Avail.");

        } else {
            holder.ac_prices.setText(String.format("Ac:%s", mNinoRooms.get(position).getAc_prices()));

        }


        if (Integer.parseInt(mNinoRooms.get(position).getNon_ac_prices()) == 0) {
            holder.non_ac_prices.setText("NA");

        } else {
            holder.non_ac_prices.setText(String.format("Non-Ac:%s", mNinoRooms.get(position).getNon_ac_prices()));

        }


        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(mNinoRooms.get(position).getPg_image())
                .placeholder((R.drawable.placeholderimage))
                .error(R.drawable.placeholderimage)
                .into(holder.pg_image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RoomDetailsActivity.class);
                intent.putExtra("NinoRooms", mNinoRooms.get(position));
                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rooms_item_view, parent, false);
        return new CustomViewHolder(view);
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        private final TextView pg_name;
        private final TextView address;
        private final TextView pg_type;
        private final TextView ac_prices;
        private final TextView non_ac_prices;
        private final ImageView pg_image;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            pg_name = mView.findViewById(R.id.pg_name);
            address = mView.findViewById(R.id.address);
            pg_type = mView.findViewById(R.id.pg_type);
            pg_image = mView.findViewById(R.id.pg_image);
            ac_prices = mView.findViewById(R.id.ac_price);
            non_ac_prices = mView.findViewById(R.id.non_ac_price);
        }
    }

    @Override
    public int getItemCount() {
        return mNinoRooms.size();
    }





}
