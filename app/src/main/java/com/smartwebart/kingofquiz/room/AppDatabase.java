package com.smartwebart.kingofquiz.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {QuestionsEntity.class, AnswerEntity.class,SubmittedAnswerEntity.class},version=3)
public abstract class AppDatabase extends RoomDatabase {

    public abstract QueryDao queryDao();

}
