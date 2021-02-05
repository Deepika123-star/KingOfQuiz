package com.smartwebart.kingofquiz.quiz;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.smartwebart.kingofquiz.R;
import com.smartwebart.kingofquiz.model.QuestionModel;
import com.smartwebart.kingofquiz.room.DatabaseClient;
import com.smartwebart.kingofquiz.room.QueryDao;
import com.smartwebart.kingofquiz.viewmodel.TestViewModel;

import java.util.ArrayList;
import java.util.List;

public class QuestionAnswer_Activity extends AppCompatActivity implements View.OnClickListener {
    public ImageView forward, backward;
    public TextView skip_save_erase, language, question_no, timer, clear_filter;
    public CheckBox starred;
    //    public Chronometer timer;
    private TestViewModel mViewModel;
    public Fragment currentFragment;
    public DrawerLayout drawer;
    public AppCompatCheckBox chk_attemp, chk_unattmpt, chk_marked;
    private RecyclerView question_recycleview;
    private ImageView logo, power;
    private List<QuestionModel> topicList;
    private  int counter = 0;
    private UpdateFragmentUi listner;
    //    private String Selected_language = null;
    public QueryDao querydao;
    public static String answer_id;
    private RelativeLayout notificationView;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        mViewModel.setActivity(this);
        setContentView(R.layout.question_main);
        if (getIntent().hasExtra("topicList")) {
            topicList = (List<QuestionModel>) getIntent().getSerializableExtra("topicList");
        }
        init();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.quiz);
        mediaPlayer.start();
//        getTopicsObserver();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
        mediaPlayer.release();

    }

    private void init() {
      //  querydao = DatabaseClient.getInstance(QuestionAnswer_Activity.this).getAppDatabase().queryDao();
//        topicList=new ArrayList<>();
        forward = findViewById(R.id.forward);
        backward = findViewById(R.id.backward);
        skip_save_erase = findViewById(R.id.skip);
        timer = findViewById(R.id.timer);
        language = findViewById(R.id.language);
        starred = findViewById(R.id.starred);
        question_no = findViewById(R.id.question_no);
        drawer = findViewById(R.id.drawer_layout);
//        timer = findViewById(R.id.timer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        replaceFragment();
        onClickView();

       /* runOnUiThread(new Runnable() {
            @Override
            public void run() {
                move_forward();
            }
        });*/
       // counterDownTimer();
        //  setChecklistner();
        /*logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleLeftDrawer();
            }
        });*/

      /*  power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.showSubmitDialog(topicList, QuestionAnswer_Activity.this);
            }
        });*/


    }

    public void navigation_set(int adapterPosition) {
        counter = adapterPosition;
//        move_forward();
    }

    private void setChecklistner() {
        starred.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                //query

                if (isChecked) {
                    topicList.get(counter - 1).setMarked_review(true);
                } else {
                    topicList.get(counter - 1).setMarked_review(false);
                }

//                querydao.setmarked_review(topicList.get(Integer.parseInt(question_no.getText().toString())).getId(),true);

            }
        });

        /*chk_attemp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //query
                //ischecked_forall();
            }
        });
*/
      /*  chk_marked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //query
               // ischecked_forall();

//                querydao.getMarkedReview(true);
            }
        });
        chk_unattmpt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               // ischecked_forall();

            }
        });*/
    }


    private void counterDownTimer() {
        if (getIntent().hasExtra("max_time")) {
            long seconds = Long.parseLong(getIntent().getStringExtra("max_time"));
            long milisecon = (1000 * seconds);
            new CountDownTimer(milisecon, 1000) {
                public void onTick(long millisUntilFinished) {
                    int h = (int) (millisUntilFinished / 3600000);
                    int m = (int) (millisUntilFinished - h * 3600000) / 60000;
                    int s = (int) (millisUntilFinished - h * 3600000 - m * 60000) / 1000;
                    String hh = h < 10 ? "0" + h : h + "";
                    String mm = m < 10 ? "0" + m : m + "";
                    String ss = s < 10 ? "0" + s : s + "";
                    timer.setText("" + hh + ":" + mm + ":" + ss);
//                timer.setText("seconds remaining: " + millisUntilFinished / 1000);
                    //here you can have your logic to set text to edittext
                }

                public void onFinish() {
                    mViewModel.showTimeDialog(topicList, QuestionAnswer_Activity.this);
//                timer.setText("done!");
                }

            }.start();

        }

    }

    private void onClickView() {
        forward.setOnClickListener(this);
        backward.setOnClickListener(this);
        language.setOnClickListener(this);
        skip_save_erase.setOnClickListener(this);
    }


    public void move_forward() {

        if (topicList != null && counter < topicList.size()) {
            counter++;
//            question_no.setText("" + counter);
            listner.onDataUpdate(topicList.get(counter - 1), counter);
            set_value();
//            Log.d("Moveforward======", "" + counter);
        }


    }

    private void set_value() {
        if (topicList.get(counter - 1).getAttempted()) {
            if (setTextonskipButton()) {
                skip_save_erase.setText("SUBMIT");
            } else {
                skip_save_erase.setText("ERASE");
            }

        } else {
            skip_save_erase.setText("SKIP");
        }
    }

    public void move_backward() {
        if (topicList != null && counter > 1) {
            counter--;
            question_no.setText("" + counter);
            set_value();
            listner.onDataUpdate(topicList.get(counter - 1), counter);
            Log.d("MoveBackward======", "" + counter);
        } else {
            finish();
        }
    }

    private void startTest() {
      /*  timer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer cArg) {
                long time = SystemClock.elapsedRealtime() - cArg.getBase();
                int h = (int) (time / 3600000);
                int m = (int) (time - h * 3600000) / 60000;
                int s = (int) (time - h * 3600000 - m * 60000) / 1000;
                String hh = h < 10 ? "0" + h : h + "";
                String mm = m < 10 ? "0" + m : m + "";
                String ss = s < 10 ? "0" + s : s + "";
                cArg.setText(hh + ":" + mm + ":" + ss);
            }
        });
        timer.setBase(SystemClock.elapsedRealtime());
        timer.start();*/
    }


   /* private void toolbar_items(Toolbar toolbar) {
        logo = toolbar.findViewById(R.id.logo);
        power = toolbar.findViewById(R.id.power);
        notificationView = toolbar.findViewById(R.id.notificationView);
        notificationView.setVisibility(View.GONE);
        power.setVisibility(View.VISIBLE);
        chk_attemp = findViewById(R.id.chk_attemp);
        chk_unattmpt = findViewById(R.id.chk_unattmpt);
        chk_marked = findViewById(R.id.chk_marked);
        clear_filter = findViewById(R.id.clear_filter);
        question_recycleview = findViewById(R.id.question_recycleview);
        LinearLayoutManager mLayoutManagerCourse = new LinearLayoutManager(com.smartwebarts.upnishadacademy.exams.activity.QuestionAnswer_Activity.this);
        mLayoutManagerCourse.setOrientation(LinearLayoutManager.VERTICAL);
        question_recycleview.setLayoutManager(mLayoutManagerCourse);
        setValueBegining();

    }*/

    public boolean setTextonskipButton() {
        if (counter == topicList.size()) {
            return true;
        }
        return false;

    }

 /*   private void setValueBegining() {
        if (getIntent().hasExtra("language")) {
            Selected_language = getIntent().getStringExtra("language");
            if (Selected_language.equalsIgnoreCase("eng")) {
                language.setText(getResources().getString(R.string.aa_hindi_));
                getTopicsObserver(Selected_language);
                Selected_language = getIntent().getStringExtra("language");
            } else {
                language.setText(getResources().getString(R.string.aa_eng_));
                getTopicsObserver(Selected_language);
            }
        }
    }*/


    private void toggleLeftDrawer() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(GravityCompat.START);
        }
    }

    public void replaceFragment() {

        QuestionAnswer_Fragment fragment = new QuestionAnswer_Fragment();
        currentFragment = fragment;
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragment)
                .addToBackStack(null).commitAllowingStateLoss();
    }


    private void getTopicsObserver() {

//        Observer observer = new Observer() {
//            @Override
//            public void onChanged(@Nullable Object object) {
//                topicList = (List<QuestionModel>) object;
//                Log.d("Monita Observer=====", "" + topicList.size());
//                move_forward();
//               // setAdapter(topicList);
//            }
//        };
//        mViewModel.getQuestionfromLocalDb(QuestionAnswer_Activity.this).observe(QuestionAnswer_Activity.this, observer);
    }
/*    Questions_NavigationAdapter mAdapter;
    private void setAdapter(List<QuestionModel> question_list) {
        mAdapter = new Questions_NavigationAdapter(question_list, QuestionAnswer_Activity.this);
        question_recycleview.setAdapter(mAdapter);
        for (int i = 0; i < question_list.size(); i++) {
            Log.d("setAdapter: ", "" + question_list.get(i).getAnswers().size());
        }
    }*/

    public synchronized void registerDataUpdateListener(UpdateFragmentUi listener) {
        this.listner = listener;
    }

    public synchronized void unregisterDataUpdateListener(UpdateFragmentUi listener) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.skip:
//                if(topicList.get(counter - 1).getAttempted()!=null&&!topicList.get(counter - 1).getAttempted())
                if (skip_save_erase.getText().toString().equalsIgnoreCase("SAVE & NEXT")) {
                    topicList.get(counter - 1).setAttempted(true);
                    topicList.get(counter - 1).setAnswer_id(answer_id);
                    move_forward();
                } else if (skip_save_erase.getText().toString().equalsIgnoreCase("ERASE")) {
                    topicList.get(counter - 1).setAttempted(false);
                    topicList.get(counter - 1).setAnswer_id(null);
                    listner.onUpdatreAdapter(topicList.get(counter - 1));
                    skip_save_erase.setText("SKIP");
                } else if (skip_save_erase.getText().toString().equalsIgnoreCase("SAVE")) {
                    topicList.get(counter - 1).setAttempted(true);
                    topicList.get(counter - 1).setAnswer_id(answer_id);
                    skip_save_erase.setText("SUBMIT");
                } else if (skip_save_erase.getText().toString().equalsIgnoreCase("SUBMIT")) {
//                    api call
                    ArrayList<QuestionModel> answer_submitted = new ArrayList<>();
                    for (int i = 0; i < topicList.size(); i++) {
                        Log.d("Sumitted answer=======", "" + topicList.get(i));
                        if (topicList.get(i).getAttempted()) {
                            answer_submitted.add(topicList.get(i));
                            Log.d("Sumitted answer=======", "" + topicList.get(i).getMarked_review() + "::::" + topicList.get(i).getTime_taken());
                          }
                    }
                    Log.d("Sumitted answer=======", answer_submitted.toString());
                    mViewModel.showSubmitDialog(topicList, QuestionAnswer_Activity.this);
                } else {
                    move_forward();
                }

                break;

            case R.id.backward:
                if (counter > 0) {
                    move_backward();
                }
                break;

            case R.id.forward:

                Log.e("TAG", "onClick: " + counter);
                if (counter < topicList.size()) {
                    move_forward();
                }
                break;


            default:
                break;

        }
    }

    @Override
    public void onBackPressed() {
        if (currentFragment instanceof QuestionAnswer_Fragment) {
            finish();
        } else {
            super.onBackPressed();
        }
    }
}
