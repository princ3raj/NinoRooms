package nino.rooms.pgcompany.adapters;

import nino.rooms.pgcompany.RoomDetailsActivity;
import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class MainSliderAdapter extends SliderAdapter {

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder viewHolder) {
        switch (position) {
            case 0:
                viewHolder.bindImageSlide(RoomDetailsActivity.pg_image);
                break;
            case 1:
                viewHolder.bindImageSlide(RoomDetailsActivity.pg_image_two);
                break;
            case 2:
                viewHolder.bindImageSlide(RoomDetailsActivity.pg_image_three);
                break;
        }
    }
}
