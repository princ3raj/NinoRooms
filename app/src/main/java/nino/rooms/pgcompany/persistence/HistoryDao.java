package nino.rooms.pgcompany.persistence;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import nino.rooms.pgcompany.model.NinoRooms;


@Dao
public interface HistoryDao {


    @Query("SELECT *FROM history WHERE history LIKE:history ORDER BY uid DESC LIMIT 20")
    LiveData<List<NinoRooms>> retrieveHistory(String history);


    @Query("SELECT * FROM history WHERE bookmark LIKE:bookmark ORDER BY uid DESC LIMIT 20")
    LiveData<List<NinoRooms>> retrieveBookMark(String bookmark);


    @Insert
    void insertHistory(NinoRooms... historyModels);


    @Delete
    void deleteHistory(NinoRooms... historyModels);
}
