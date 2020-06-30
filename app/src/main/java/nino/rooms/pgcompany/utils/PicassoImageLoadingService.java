package nino.rooms.pgcompany.utils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import nino.rooms.pgcompany.R;
import ss.com.bannerslider.ImageLoadingService;

public class PicassoImageLoadingService implements ImageLoadingService {
    public Context context;

    public PicassoImageLoadingService(Context context) {
        this.context = context;
    }

    @Override
    public void loadImage(String url, ImageView imageView) {
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(url)
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(imageView);
    }

    @Override
    public void loadImage(int resource, ImageView imageView) {

    }

    @Override
    public void loadImage(String url, int placeHolder, int errorDrawable, ImageView imageView) {
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(url)
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(imageView);

    }
}
