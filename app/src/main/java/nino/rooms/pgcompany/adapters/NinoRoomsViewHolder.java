//package nino.rooms.pgcompany.adapters;
//
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.widget.AppCompatImageView;
//import androidx.recyclerview.widget.RecyclerView;
//
//import nino.rooms.pgcompany.R;
//
//public class NinoRoomsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//
//    TextView pg_name,ac_price,location,details;
//    ImageView pg_image;
//    private OnRoomsListListener mOnRoomsListListener;
//
//    public NinoRoomsViewHolder(@NonNull View itemView, OnRoomsListListener onRoomsListListener) {
//        super(itemView);
//
//        this.mOnRoomsListListener = onRoomsListListener;
//
//        pg_name = itemView.findViewById(R.id.pg_name);
//        ac_price = itemView.findViewById(R.id.ac_price);
//        location = itemView.findViewById(R.id.location);
//        details = itemView.findViewById(R.id.details);
//        pg_image=itemView.findViewById(R.id.pg_image);
//
//
//        itemView.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View v) {
//        mOnRoomsListListener.onRoomsClick(getAdapterPosition());
//    }
//}