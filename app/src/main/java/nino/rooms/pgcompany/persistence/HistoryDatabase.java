package nino.rooms.pgcompany.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import nino.rooms.pgcompany.model.NinoRooms;


@Database(entities = {NinoRooms
        .class}, version = 2)
public abstract class HistoryDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "extra_features";

    private static HistoryDatabase instance;


    static HistoryDatabase getInstance(final Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    HistoryDatabase.class,
                    DATABASE_NAME).build();
        }

        return instance;


    }

    public abstract HistoryDao getHistoryDao();

}


