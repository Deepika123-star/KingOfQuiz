package com.smartwebart.kingofquiz.quiz;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smartwebart.kingofquiz.R;
import com.smartwebart.kingofquiz.model.QuestionModel;
import com.smartwebart.kingofquiz.viewmodel.TestViewModel;


public class QuestionAnswer_Fragment extends Fragment implements UpdateFragmentUi {
    private TestViewModel mViewModel;
    private AppCompatTextView questions,total_count;
    private String TAG = "QuestionAnswer_Fragment";
    private RecyclerView recyclerview_answer;
    private LinearLayoutManager mLayoutManagerCourse;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View main_view = inflater.inflate(R.layout.question_answer_fragment, container, false);
        Log.d(TAG, "onCreateView: ");
        return main_view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: ");
        init(view);

    }

    private void init(View view) {
        questions = view.findViewById(R.id.questions);
        total_count = view.findViewById(R.id.total_count);
        recyclerview_answer = view.findViewById(R.id.recyclerview_answer);
        mLayoutManagerCourse = new LinearLayoutManager(requireActivity());
        mLayoutManagerCourse.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview_answer.setLayoutManager(mLayoutManagerCourse);
        ((QuestionAnswer_Activity)getActivity()).move_forward();
      /*  if(getArguments().containsKey("title"))
        {
            questions.setText(getArguments().getString("title"));
        }*/

    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        ((QuestionAnswer_Activity) getActivity()).registerDataUpdateListener(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    private Answer_Adapter testAdapter;
    @Override
    public void onDataUpdate(QuestionModel questionModel, int counter) {
        Log.d("onDataUpdate",""+(Html.fromHtml(questionModel.getNameInEng()).toString()));

        questions.setText(Html.fromHtml(questionModel.getNameInEng()).toString());
        total_count.setText(String.valueOf(counter));
        testAdapter = new Answer_Adapter(requireActivity(),questionModel.getAnswers(),questionModel);
        recyclerview_answer.setAdapter(testAdapter);
    }
    @Override
    public void onUpdatreAdapter(QuestionModel questionModel) {
        testAdapter.notifyDataSetChanged(questionModel);
    }
    @Override
    public void onScoreReviewFragment(String selected_language) {
    }
}
