package com.smartwebart.kingofquiz.retrofit;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.gson.Gson;
import com.smartwebart.kingofquiz.R;
import com.smartwebart.kingofquiz.model.NewSeriesModel;
import com.smartwebart.kingofquiz.model.QuestionModel;
import com.smartwebart.kingofquiz.model.SubjectModel;
import com.smartwebart.kingofquiz.model.SubmitTestModel;
import com.smartwebart.kingofquiz.model.Total_Score_Model;
import com.smartwebart.kingofquiz.quiz.OTPModel;

import java.io.File;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public enum UtilMethods {
    INSTANCE;
    public static String NAME = "";
    public static String EMAIL = "";
    public static String IMAGE = "";
    public static Boolean isVerfied = false;
    public static final String dateformat = "yyyy-MM-dd";
    public static final SimpleDateFormat format = new SimpleDateFormat(dateformat);
    public static final String user_table = "user";
    public static final String vehicles_table = "vehicles";
    public static final String vehicle_make_table = "vehicle_make";
    public static final String service_requests_table = "service_requests";
    public static final String sservices_task_table = "services_task";
    public static final String transaction_table = "admin_vendor_transaction";
    public static final String tb_name = "user";
    public static final String api_status = "pending";
    public static final String youtube_apiKey = "AIzaSyBnuiEhKcNQHYmN4dDTdsETEzhTAtmyk7o";
    public static final String youtuber_ChannelId = "UC4qz5w2M-Xmju7WC9ynqRtw";
    //    public static final String youtuber_ChannelId = "UCT1FDqisLJQvu4STfSr9F_g";
    public static final String youtuber_pageToken = "";
    public static  String subject_id = "";

    public boolean isNetworkAvialable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public void internetNotAvailableMessage(Context context) {
        SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
        dialog.setContentText("Internet Not Available");
        dialog.show();
    }


    public void shareApp(Context contx) {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
            String shareMessage = "\nLet me recommend you this application\n\n";
//            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            contx.startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch (Exception e) {
            //e.toString();
        }
    }

    public boolean isValidMobile(String mobile) {

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String mobilePattern = "^(?:0091|\\\\+91|0)[7-9][0-9]{9}$";
        String mobileSecPattern = "[7-9][0-9]{9}$";

        if (mobile.matches(mobilePattern) || mobile.matches(mobileSecPattern)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidEmail(String email) {

        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    private Dialog getProgressDialog(Context context) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.default_progress_dialog);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        ProgressBar progressBar = (ProgressBar) dialog.findViewById(R.id.progress);
        DoubleBounce doubleBounce = new DoubleBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);
        return dialog;
    }

    public void getTopicApi(final Context context,final mCallBackResponse callBackResponse) {

        if (UtilMethods.INSTANCE.isNetworkAvialable(context))
        {
            final Dialog dialog = getProgressDialog(context);
            dialog.show();

            try {
                EndPointInterface git = APIClient.getClient().create(EndPointInterface.class);
                Call <Total_Score_Model> call = git.getTopic(tb_name);
                call.enqueue(new Callback<Total_Score_Model>() {
                    @Override
                    public void onResponse(Call<Total_Score_Model> call, Response<Total_Score_Model> response) {
                        dialog.dismiss();
                        String strResponse = new Gson().toJson(response.body());
                        Log.e("strResponse",strResponse);
                        if (response.body()!=null) {
                            if (response.body()!=null /*&& response.body().getStatus().equalsIgnoreCase("success")*/) {
                                callBackResponse.success("", strResponse);
                            }
                           /* else {
                                callBackResponse.fail(response.body().getMessage());
                            }*/
                        } else {
                            callBackResponse.fail("Failed");
                        }
                    }

                    @Override
                    public void onFailure(Call<Total_Score_Model> call, Throwable t) {
                        callBackResponse.fail(t.getMessage());
                        dialog.dismiss();
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                callBackResponse.fail(e.getMessage());
                dialog.dismiss();
            }
        } else {
            UtilMethods.INSTANCE.internetNotAvailableMessage(context);
        }

    }


/*

    public void getSubjectCallApi(final Context context, final mCallBackResponse callBackResponse) {

        if (UtilMethods.INSTANCE.isNetworkAvialable(context))
        {
            final Dialog dialog = getProgressDialog(context);
            dialog.show();

            try {
                EndPointInterface git = APIClient.getClient().create(EndPointInterface.class);
                Call<List<SubjectModel>> call = git.getSubjects(tb_name);
                call.enqueue(new Callback<List<SubjectModel>>() {
                    @Override
                    public void onResponse(Call<List<SubjectModel>> call, Response<List<SubjectModel>> response) {
                        dialog.dismiss();
                        String strResponse = new Gson().toJson(response.body());
                        Log.e("strResponse",strResponse);
                        if (response.body()!=null) {
                            if (response.body()!=null */
/*&& response.body().getStatus().equalsIgnoreCase("success")*//*
) {
                                callBackResponse.success("", strResponse);
                            }
                           */
/* else {
                                callBackResponse.fail(response.body().getMessage());
                            }*//*

                        } else {
                            callBackResponse.fail("Failed");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<SubjectModel>> call, Throwable t) {
                        callBackResponse.fail(t.getMessage());
                        dialog.dismiss();
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                callBackResponse.fail(e.getMessage());
                dialog.dismiss();
            }
        } else {
            UtilMethods.INSTANCE.internetNotAvailableMessage(context);
        }

    }
*/


    public void getSubjects(final Context context, final mCallBackResponse callBackResponse) {

        if (UtilMethods.INSTANCE.isNetworkAvialable(context))
        {
            final Dialog dialog = getProgressDialog(context);
          //  dialog.show();

            try {
                EndPointInterface git = APIClient.getClient().create(EndPointInterface.class);
                Call<List<NewSeriesModel>> call = git.getCourses("subjects");
                call.enqueue(new Callback<List<NewSeriesModel>>() {
                    @Override
                    public void onResponse(Call<List<NewSeriesModel>> call, Response<List<NewSeriesModel>> response) {
                     /*  if (dialog.isShowing()){
                           dialog.dismiss();
                       }*/

                        String strResponse = new Gson().toJson(response.body());
                        Log.e("Deepika",strResponse);
                        if (response.body()!=null) {
                            if (response.body()!=null /*&& response.body().getStatus().equalsIgnoreCase("success")*/) {
                                callBackResponse.success("", strResponse);
                               // dialog.dismiss();
                            }
                           /* else {
                                callBackResponse.fail(response.body().getMessage());
                            }*/
                        } else {
                            callBackResponse.fail("Failed");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<NewSeriesModel>> call, Throwable t) {
                        callBackResponse.fail(t.getMessage());
                        if (dialog.isShowing()){
                            dialog.dismiss();
                        }
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                callBackResponse.fail(e.getMessage());
                if (dialog.isShowing()){
                    dialog.dismiss();
                }
            }
        } else {
            UtilMethods.INSTANCE.internetNotAvailableMessage(context);
        }

    }


    public void getQuestionApi(final Context context, String topic_id, String level, final mCallBackResponse callBackResponse) {

        if (UtilMethods.INSTANCE.isNetworkAvialable(context))
        {
            final Dialog dialog = getProgressDialog(context);
            dialog.show();

            try {
                EndPointInterface git = APIClient.getClient().create(EndPointInterface.class);
                Call<List<QuestionModel>> call = git.getQuestions(level,topic_id);
                call.enqueue(new Callback<List<QuestionModel>>() {
                    @Override
                    public void onResponse(Call<List<QuestionModel>> call, Response<List<QuestionModel>> response) {
                        dialog.dismiss();
                        String strResponse = new Gson().toJson(response.body());
                        Log.e("strResponse",strResponse);
                        if (response.body()!=null) {
                            if (response.body()!=null /*&& response.body().getStatus().equalsIgnoreCase("success")*/) {
                                callBackResponse.success("", strResponse);
                            }
                           /* else {
                                callBackResponse.fail(response.body().getMessage());
                            }*/
                        } else {
                            callBackResponse.fail("Failed");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<QuestionModel>> call, Throwable t) {
                        callBackResponse.fail(t.getMessage());
                        dialog.dismiss();
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                callBackResponse.fail(e.getMessage());
                dialog.dismiss();
            }
        } else {
            UtilMethods.INSTANCE.internetNotAvailableMessage(context);
        }

    }


    public void testSubmitted(final Context context, SubmitTestModel question_attempted, final mCallBackResponse callBackResponse) {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.default_progress_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        ProgressBar progressBar = (ProgressBar)dialog.findViewById(R.id.progress);
        DoubleBounce doubleBounce = new DoubleBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);
        dialog.show();

        try {
            EndPointInterface git = APIClient.getClient().create(EndPointInterface.class);
            Call<OTPModel> call = git.saveAnswer(new Gson().toJson(question_attempted));
            call.enqueue(new Callback<OTPModel>() {
                @Override
                public void onResponse(Call<OTPModel> call, Response<OTPModel> response) {
                    dialog.dismiss();
                    String strResponse = new Gson().toJson(response.body());
                    Log.e("strResponse",strResponse);
                    if (response.body()!=null) {
                        callBackResponse.success("",strResponse);
                        /*if (response.body()!=null *//*&& response.body().getStatus().equalsIgnoreCase("success")*//*) {
                            callBackResponse.success(response.body().getMessage(), strResponse);
                        }
                        else {
                            callBackResponse.fail(response.body().getMessage());
                        }*/
                    } else {
                        callBackResponse.fail("Invalid UserId or Password");
                    }
                }

                @Override
                public void onFailure(Call<OTPModel> call, Throwable t) {
                    callBackResponse.fail(t.getMessage());
                    dialog.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            callBackResponse.fail(e.getMessage());
            dialog.dismiss();
        }

    }



}


