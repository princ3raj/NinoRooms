package nino.rooms.pgcompany.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import nino.rooms.pgcompany.adapters.HistoryRecyclerViewAdapter;
import nino.rooms.pgcompany.adapters.HistoryRecyclerViewAdapter.OnHistoryListener;
import nino.rooms.pgcompany.model.NinoRooms;
import nino.rooms.pgcompany.persistence.HistoryRepository;

public class HistoryFragment extends Fragment implements
        OnHistoryListener, View.OnClickListener {

    private Context mContext;
    private static final String TAG = "HistoryFragment";

    //progressBar
    ProgressDialog progressDialog;

    private ArrayList<NinoRooms> mHistories = new ArrayList<>();
    private RecyclerView mHistoryRecyclerView;
    private HistoryRecyclerViewAdapter mHistoryRecyclerViewAdapter;
    private HistoryRepository mHistoryRepository;
    private ItemTouchHelper.SimpleCallback itemTouchHelperCallBack = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            deleteHistory(mHistories.get(viewHolder.getAdapterPosition()));
        }

    };

    @Override
    public void onAttach(@NonNull Context context) {
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_history, container, false);

        // Hide the status bar.
        View decorView = getActivity().getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Loading....");
        progressDialog.show();


        mHistoryRepository = new HistoryRepository(getActivity());


        mHistoryRecyclerView = view.findViewById(R.id.history_recycler_view);

        InitRecyclerview();


        retrieveHistory();


        return view;
    }

    private void InitRecyclerview() {
        Log.d(TAG, "InitRecyclerview: called");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mHistoryRecyclerView.setLayoutManager(linearLayoutManager);
        new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(mHistoryRecyclerView);
        mHistoryRecyclerViewAdapter = new HistoryRecyclerViewAdapter(mHistories, mContext, this);
        mHistoryRecyclerView.setAdapter(mHistoryRecyclerViewAdapter);


    }

    private void retrieveHistory() {
        mHistoryRepository.retrieveHistory().observe(getViewLifecycleOwner(), new Observer<List<NinoRooms>>() {
            @Override
            public void onChanged(List<NinoRooms> historyModels) {


                if (mHistories.size() > 0) {
                    mHistories.clear();
                }

                if (historyModels != null) {

                    mHistories.addAll(historyModels);
                    Log.d(TAG, "onChanged: called" + mHistories.toString());


                }
                mHistoryRecyclerViewAdapter.notifyDataSetChanged();
                progressDialog.dismiss();

            }


        });
    }

    private void deleteHistory(NinoRooms historyModel) {
        Log.d(TAG, "deleteHistory: called");
        mHistories.remove(historyModel);
        mHistoryRecyclerViewAdapter.notifyDataSetChanged();
        mHistoryRepository.deleteHistory(historyModel);


    }

    @Override
    public void onClick(View v) {


    }

    @Override
    public void onHistoryClick(int position) {

        Log.d(TAG, "onNoteClick: " + position);

        Intent intent = new Intent(mContext, RoomDetailsActivity.class);

        Log.d(TAG, "onHistoryClick: called" + mHistories.toString());

        intent.putExtra("history", mHistories.get(position));
        Log.d(TAG, "onHistoryClick: " + mHistories.get(position));
        startActivity(intent);


    }
}


