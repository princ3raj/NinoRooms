//package nino.rooms.pgcompany.adapters;
//
//import android.net.Uri;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.RequestOptions;
//
//
//import java.util.List;
//
//import nino.rooms.pgcompany.R;
//import nino.rooms.pgcompany.SearchResultActivity;
//import nino.rooms.pgcompany.model.NinoRooms;
//
//public class NinoRoomsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//
//
//    private static final int RECIPE_TYPE = 1;
//
//    private List<NinoRooms> mNinoRooms;
//
//   private OnRoomsListListener mOnRoomsListListener;
//
//
//    public NinoRoomsAdapter(OnRoomsListListener mOnRoomsListListener) {
//        this.mOnRoomsListListener = mOnRoomsListListener;
//    }
//
//
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//
//
//        View view = null;
//        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rooms_item_view, viewGroup, false);
//        return new NinoRoomsViewHolder(view, mOnRoomsListListener);
//
//    }
//
//
//
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
//
//        int itemViewType = getItemViewType(i);
//
//            RequestOptions requestOptions = new RequestOptions()
//                    .placeholder(R.drawable.ic_launcher_background);
//
//            Glide.with(viewHolder.itemView.getContext())
//                    .setDefaultRequestOptions(requestOptions)
//                    .load(mNinoRooms.get(i).getPg_image())
//                    .into(((NinoRoomsViewHolder)viewHolder).pg_image);
//
//            ((NinoRoomsViewHolder)viewHolder).pg_name.setText(mNinoRooms.get(i).getPg_name());
//            ((NinoRoomsViewHolder)viewHolder).location.setText(mNinoRooms.get(i).getLocation());
//            ((NinoRoomsViewHolder)viewHolder).details.setText(mNinoRooms.get(i).getDetails());
//            ((NinoRoomsViewHolder)viewHolder).ac_price.setText(mNinoRooms.get(i).getAc_prices());
//
//        }
//
//
//
//    @Override
//    public int getItemViewType(int position) {
//
//            return RECIPE_TYPE;
//
//    }
//
////    public void setQueryExhausted(){
////        hideLoading();
////        Recipe exhaustedRecipe = new Recipe();
////        exhaustedRecipe.setTitle("EXHAUSTED...");
////        mRecipes.add(exhaustedRecipe);
////        notifyDataSetChanged();
////    }
//
////    private void hideLoading(){
////        if(isLoading()){
////            for(Recipe recipe: mRecipes){
////                if(recipe.getTitle().equals("LOADING...")){
////                    mRecipes.remove(recipe);
////                }
////            }
////            notifyDataSetChanged();
////        }
////    }
//
////    public void displayLoading(){
////        if(!isLoading()){
////            Recipe recipe = new Recipe();
////            recipe.setTitle("LOADING...");
////            List<Recipe> loadingList = new ArrayList<>();
////            loadingList.add(recipe);
////            mRecipes = loadingList;
////            notifyDataSetChanged();
////        }
////    }
//
////    private boolean isLoading(){
////        if(mRecipes != null){
////            if(mRecipes.size() > 0){
////                if(mRecipes.get(mRecipes.size() - 1).getTitle().equals("LOADING...")){
////                    return true;
////                }
////            }
////        }
////        return false;
////    }
//
////    public void displaySearchCategories(){
////        List<Recipe> categories = new ArrayList<>();
////        for(int i = 0; i< Constants.DEFAULT_SEARCH_CATEGORIES.length; i++){
////            Recipe recipe = new Recipe();
////            recipe.setTitle(Constants.DEFAULT_SEARCH_CATEGORIES[i]);
////            recipe.setImage_url(Constants.DEFAULT_SEARCH_CATEGORY_IMAGES[i]);
////            recipe.setSocial_rank(-1);
////            categories.add(recipe);
////        }
////        mRecipes = categories;
////        notifyDataSetChanged();
////    }
//
//    @Override
//    public int getItemCount() {
//        if(mNinoRooms != null){
//            return mNinoRooms.size();
//        }
//        return 0;
//    }
//
//    public void setNinoRooms(List<NinoRooms> rooms){
//        mNinoRooms = rooms;
//        notifyDataSetChanged();
//    }
//
//    public NinoRooms getSelectedRecipe(int position){
//        if(mNinoRooms != null){
//            if(mNinoRooms.size() > 0){
//                return mNinoRooms.get(position);
//            }
//        }
//        return null;
//    }
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
