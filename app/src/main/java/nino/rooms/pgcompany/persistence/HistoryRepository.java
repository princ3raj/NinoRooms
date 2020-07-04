package nino.rooms.pgcompany.persistence;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import nino.rooms.pgcompany.async.DeleteAsyncTask;
import nino.rooms.pgcompany.async.InsertAsyncTask;
import nino.rooms.pgcompany.model.NinoRooms;


public class HistoryRepository {

    private HistoryDatabase mHistoryDatabase;

    public HistoryRepository(Context context) {
        mHistoryDatabase = HistoryDatabase.getInstance(context);

    }

    public void insertHistory(NinoRooms historyModel) {
        new InsertAsyncTask(mHistoryDatabase.getHistoryDao()).execute(historyModel);
    }

    public void deleteHistory(NinoRooms historyModel) {
        new DeleteAsyncTask(mHistoryDatabase.getHistoryDao()).execute(historyModel);

    }

    public LiveData<List<NinoRooms>> retrieveHistory() {

        return mHistoryDatabase.getHistoryDao().retrieveHistory("history");
    }

    public LiveData<List<NinoRooms>> retrieveBookmark() {

        return mHistoryDatabase.getHistoryDao().retrieveBookMark("bookmark");
    }


}
