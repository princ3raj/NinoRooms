package nino.rooms.pgcompany.fragments;

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


        mHistoryRepository = new HistoryRepository(getActivity());


        mHistoryRecyclerView = view.findViewById(R.id.history_recycler_view);

        InitRecyclerview();


        retrieveHistory();


        return view;
    }

    private void InitRecyclerview() {
        Log.d(TAG, "InitRecyclerview: called");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mHistoryRecyclerView.setLayoutManager(linearLayoutManager);
        new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(mHistoryRecyclerView);
        mHistoryRecyclerViewAdapter = new HistoryRecyclerViewAdapter(mHistories, getContext(), this);
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
//                    mHistoryRecyclerViewAdapter = new HistoryRecyclerViewAdapter(mHistories, getContext());
//                    mHistoryRecyclerView.setAdapter(mHistoryRecyclerViewAdapter);


                }
                mHistoryRecyclerViewAdapter.notifyDataSetChanged();

            }


        });
    }

    private void deleteHistory(NinoRooms historyModel) {
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

        Intent intent = new Intent(getActivity(), RoomDetailsActivity.class);

        intent.putExtra("selected_item", mHistories.get(position));
        startActivity(intent);

    }
}


