package com.futech.smartbirth;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Data.class}, version = 1)
public abstract class PerkembanganRoomDatabase extends RoomDatabase {

    public abstract PerkembanganDao perkembanganDao();

    private static volatile PerkembanganRoomDatabase INSTANCE;

    static PerkembanganRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PerkembanganRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PerkembanganRoomDatabase.class, "perkembangan_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final PerkembanganDao mDao;

        PopulateDbAsync(PerkembanganRoomDatabase db) {
            mDao = db.perkembanganDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            Data data = new Data(90);
            mDao.insert(data);
            data = new Data(100);
            mDao.insert(data);
            return null;
        }
    }
}
