package com.smartwebart.kingofquiz.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.smartwebart.kingofquiz.model.AnswerModel;
import com.smartwebart.kingofquiz.model.QuestionModel;
import com.smartwebart.kingofquiz.model.SubmittedAnswerModel;

import java.util.List;

@Dao
public interface QueryDao {
    @Insert
    void insert(QuestionsEntity questionsEntity);

    @Delete
    void delete(QuestionsEntity questionsEntity);

    @Update
    void update(QuestionsEntity questionsEntity);

    @Insert
    void insert(AnswerEntity answerEntity);

    @Delete
    void delete(AnswerEntity answerEntity);

    @Update
    void update(AnswerEntity answerEntity);

    @Insert
    void insert(SubmittedAnswerEntity submittedAnswerEntity);

    @Delete
    void delete(SubmittedAnswerEntity submittedAnswerEntity);

    @Update
    void update(SubmittedAnswerEntity submittedAnswerEntity);

    @Query("SELECT  * FROM questionsEntity WHERE id = :qId")
    List<QuestionsEntity> getAllQuestionAnswerWith_QI(String qId);

    @Query("SELECT attempted FROM questionsEntity WHERE id= :taskId")
    Boolean getAttempted(String taskId);

    @Query("UPDATE  questionsEntity  SET attempted=:value, answer_id=:answerid WHERE id=:id")
    void setAttempted(String id, boolean value, String answerid);

    @Query("SELECT unattempted FROM questionsEntity WHERE id= :taskId")
    Boolean getUnattempted(String taskId);

    @Query("UPDATE  questionsEntity  SET unattempted=:value WHERE id=:id")
    void setUnattempted(String id, boolean value);

    @Query("SELECT marked_review FROM questionsEntity WHERE id= :taskId")
    Boolean getMarkedReview(String taskId);

    @Query("UPDATE  questionsEntity  SET marked_review=:value WHERE id=:id")
    void setmarked_review(String id, boolean value);

    @Query("SELECT  * FROM questionsEntity")
    List<QuestionModel> getAllQuestionAnswer();

    @Query("SELECT  * FROM submittedAnswerEntity")
    List<SubmittedAnswerModel> getAllSubmittedQuestionAnswer();

    @Query("SELECT  * FROM answerEntity WHERE questions_id= :q_id")
    List<AnswerModel> getAllAnswer(String q_id);

    @Query("DELETE FROM questionsEntity")
    void deleteQuestionTb();

    @Query("DELETE FROM answerentity")
    void deleteAnswerTb();

    @Query("DELETE FROM submittedAnswerEntity")
    void deleteSubmittedAnswerTb();




}
