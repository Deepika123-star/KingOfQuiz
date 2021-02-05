package com.smartwebart.kingofquiz.room;

import android.content.Context;

import androidx.room.Room;

import com.smartwebart.kingofquiz.utils.Urls;


public class DatabaseClient {private Context context;
    private static DatabaseClient mInstance;

    private AppDatabase appDatabase;

    private DatabaseClient(Context context)
    {
        //creating the app database with Room database builder
        //Clicmasterdb is the name of the database
        this.context=context;
        appDatabase= Room.databaseBuilder(context,AppDatabase.class, Urls.UPNISHAD_DB).allowMainThreadQueries().fallbackToDestructiveMigration().build();

    }

    public static synchronized DatabaseClient getInstance(Context context){

        if(mInstance==null)
        {
            mInstance=new DatabaseClient(context);
        }
        return mInstance;
    }

    /*static final Migration MIGRATION_1_2=new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE LocationEntity "
                    +"ADD COLUMN Latitude Double,ADD COLUMN Longitude Double");
        }
    };*/

    public AppDatabase getAppDatabase()
    {
        return appDatabase;
    }

   /* static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
//            database.execSQL("CREATE TABLE IF NOT EXISTS `TaskEntity` (`id` INTEGER, PRIMARY KEY(`id`))");
            database.execSQL(
                    "CREATE TABLE TaskEntity (id TEXT, task_id TEXT, description TEXT, PRIMARY KEY(id))");

        }
    };*/

  /*  static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Book "
                    + " ADD COLUMN pub_year INTEGER");
        }
    };*/

}
