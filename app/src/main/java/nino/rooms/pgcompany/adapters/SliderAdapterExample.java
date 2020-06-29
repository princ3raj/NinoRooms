package nino.rooms.pgcompany.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

import nino.rooms.pgcompany.R;
import nino.rooms.pgcompany.model.NinoRooms;

public class SliderAdapterExample extends
        SliderViewAdapter<SliderAdapterExample.SliderAdapterVH> {

    private static final String TAG = "SliderAdapterExample";

    private Context context;

    private int mainPosition;
    private List<NinoRooms> mSliderItems = new ArrayList<>();

    public SliderAdapterExample(Context context, int mainPosition) {
        this.context = context;
        this.mainPosition = mainPosition;
    }

    public void renewItems(List<NinoRooms> sliderItems) {
        this.mSliderItems = sliderItems;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        this.mSliderItems.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(NinoRooms sliderItem) {
        this.mSliderItems.add(sliderItem);
        notifyDataSetChanged();
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item_view, null);
        return new SliderAdapterVH(inflate);
    }


    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {

        NinoRooms sliderItem = mSliderItems.get(mainPosition);

//        viewHolder.textViewDescription.setText(sliderItem.getDescription());
//        viewHolder.textViewDescription.setTextSize(16);
//        viewHolder.textViewDescription.setTextColor(Color.WHITE);
        Glide.with(viewHolder.itemView)
                .load(sliderItem.getPg_image())
                .fitCenter()
                .into(viewHolder.imageViewBackground);


        Log.d(TAG, "onBindViewHolder: " + sliderItem.getPg_image());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "This is item in position " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mSliderItems.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;


        public SliderAdapterVH(View itemView) {
            super(itemView);
            this.itemView = itemView;
            imageViewBackground = itemView.findViewById(R.id.slider_image);

        }
    }

}