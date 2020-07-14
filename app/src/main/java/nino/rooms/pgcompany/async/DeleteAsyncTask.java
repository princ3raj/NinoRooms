package nino.rooms.pgcompany.async;


import android.os.AsyncTask;

import nino.rooms.pgcompany.model.NinoRooms;
import nino.rooms.pgcompany.persistence.HistoryDao;

public class DeleteAsyncTask extends AsyncTask<NinoRooms, Void, Void> {

    private final HistoryDao mHistoryDao;

    public DeleteAsyncTask(HistoryDao historyDao) {
        mHistoryDao = historyDao;
    }

    @Override
    protected Void doInBackground(NinoRooms... ninoRooms) {
        mHistoryDao.deleteHistory(ninoRooms);
        return null;
    }
}