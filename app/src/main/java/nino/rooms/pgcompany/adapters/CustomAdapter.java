package nino.rooms.pgcompany.adapters;


import android.content.Context;
import android.content.Intent;
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
    public static final String EXTRA_TEXT = "position";
    private   List<NinoRooms> mNinoRooms;
    private Context context;

    public CustomAdapter(Context context,List<NinoRooms> ninoRooms){
        this.context = context;
        this.mNinoRooms = ninoRooms;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        private TextView pg_name;
        private TextView location;
        private TextView details;
        private TextView ac_prices;
        private ImageView pg_image;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            pg_name = mView.findViewById(R.id.pg_name);
            location = mView.findViewById(R.id.location);
            details = mView.findViewById(R.id.details);
            pg_image = mView.findViewById(R.id.pg_image);
            ac_prices=mView.findViewById(R.id.ac_price);
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rooms_item_view, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position) {
        holder.pg_name.setText(mNinoRooms.get(position).getPg_name());
        holder.location.setText(mNinoRooms.get(position).getLocation());
        holder.details.setText(mNinoRooms.get(position).getDetails());
        holder.ac_prices.setText(mNinoRooms.get(position).getAc_prices());

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(mNinoRooms.get(position).getPg_image())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.pg_image);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RoomDetailsActivity.class);
                intent.putExtra(Intent.EXTRA_TEXT, "" + position);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public  int getItemCount() {
        return mNinoRooms.size();
    }





}
