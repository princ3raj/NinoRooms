package nino.rooms.pgcompany.async;

import android.os.AsyncTask;

import nino.rooms.pgcompany.model.NinoRooms;
import nino.rooms.pgcompany.persistence.HistoryDao;

public class InsertAsyncTask extends AsyncTask<NinoRooms, Void, Void> {

    private HistoryDao mHistoryDao;

    public InsertAsyncTask(HistoryDao dao) {
        mHistoryDao = dao;
    }

    @Override
    protected Void doInBackground(NinoRooms... historyModels) {
        mHistoryDao.insertHistory(historyModels);
        return null;

    }
}
