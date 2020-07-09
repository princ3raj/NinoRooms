package nino.rooms.pgcompany.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import nino.rooms.pgcompany.R;
import nino.rooms.pgcompany.RoomDetailsActivity;
import nino.rooms.pgcompany.adapters.BookMarkAdapter;
import nino.rooms.pgcompany.model.NinoRooms;
import nino.rooms.pgcompany.persistence.HistoryRepository;

public class BookmarkFragment extends Fragment implements
        BookMarkAdapter.OnBookMarkListener, View.OnClickListener {
    private static final String TAG = "BookmarkFragment";
    //    //progressBar
//    ProgressDialog progressDialog;
    private ImageView mEmptyView;

    private Context mContext;
    private ArrayList<NinoRooms> mHistories = new ArrayList<>();
    private RecyclerView mBookmarkRecyclerView;
    private BookMarkAdapter mBookMarkAdapter;
    private HistoryRepository mHistoryRepository;
    private ImageButton mFilterIcon;


    @Override
    public void onAttach(@NonNull Context context) {
        mContext = context;
        super.onAttach(context);
    }

    private ItemTouchHelper.SimpleCallback itemTouchHelperCallBack = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            deleteBookmark(mHistories.get(viewHolder.getAdapterPosition()));
        }

    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookmark, container, false);
        // Hide the status bar.
        View decorView = getActivity().getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        mHistoryRepository = new HistoryRepository(getActivity());
//        progressDialog = new ProgressDialog(mContext);
//        progressDialog.setMessage("Loading....");
//        progressDialog.show();
        mEmptyView = view.findViewById(R.id.empty_view);


        mBookmarkRecyclerView = view.findViewById(R.id.bookmark_recycler_view);
        mFilterIcon = view.findViewById(R.id.filter);
        mFilterIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                progressDialog.show();
                retrieveBookmark();


            }
        });

        InitRecyclerview();
        retrieveBookmark();

        return view;


    }

    private void InitRecyclerview() {
        Log.d(TAG, "InitRecyclerview: called");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mBookmarkRecyclerView.setLayoutManager(linearLayoutManager);
        new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(mBookmarkRecyclerView);
        mBookMarkAdapter = new BookMarkAdapter(mHistories, mContext, this);
        mBookmarkRecyclerView.setAdapter(mBookMarkAdapter);


    }

    private void retrieveBookmark() {
        mHistoryRepository.retrieveBookmark().observe(getViewLifecycleOwner(), new Observer<List<NinoRooms>>() {
            @Override
            public void onChanged(List<NinoRooms> historyModels) {


                if (mHistories.size() > 0) {
                    mHistories.clear();
                }

                if (historyModels != null) {

                    mHistories.addAll(historyModels);
                    Log.d(TAG, "onChanged: called" + mHistories.toString());


                }

                if (mBookMarkAdapter.getItemCount() == 0) {
                    mBookMarkAdapter.notifyDataSetChanged();
                    mEmptyView.setVisibility(View.VISIBLE);

                } else {
                    mEmptyView.setVisibility(View.GONE);
                    mBookMarkAdapter.notifyDataSetChanged();
                }


//                progressDialog.dismiss();

            }


        });
    }

    private void deleteBookmark(NinoRooms historyModel) {
        mHistories.remove(historyModel);
        mBookMarkAdapter.notifyDataSetChanged();
        mHistoryRepository.deleteHistory(historyModel);


    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onBookMarkClick(int position) {

        Log.d(TAG, "onNoteClick: " + position);

        Intent intent = new Intent(mContext, RoomDetailsActivity.class);

        Log.d(TAG, "onHistoryClick: called" + mHistories.toString());

        intent.putExtra("bookmark", mHistories.get(position));
        Log.d(TAG, "onHistoryClick: " + mHistories.get(position));
        startActivity(intent);

    }

}
