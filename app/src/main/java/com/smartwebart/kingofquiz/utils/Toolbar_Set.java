package com.smartwebart.kingofquiz.utils;

import android.app.Activity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smartwebart.kingofquiz.R;


public enum Toolbar_Set {

    INSTANCE;
/*

    public void setToolbar(final Activity activity) {
        ImageView back = activity.findViewById(R.id.back);
        ImageView chat_icon = activity.findViewById(R.id.chat_icon);
        ImageView refresh = activity.findViewById(R.id.refresh);

        TextView tv_name = activity.findViewById(R.id.tv_name);
        FrameLayout showCart = activity.findViewById(R.id.showCart);
        // ImageView imageView=activity.findViewById(R.id.showProduct);


        if (back != null) {
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onBackPressed();

                }
            });
        }


        chat_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                refresh(activity);

            }
        });


*/
/*
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh(activity);

            }
        });

        showCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCart(activity);

            }
        });

        getCartList(activity);*//*

    }

    public void setToolbar(final Activity activity, String name) {
        ImageView back = activity.findViewById(R.id.back);
        FrameLayout showCart = activity.findViewById(R.id.showCart);
        TextView tvName = activity.findViewById(R.id.tv_name);
        tvName.setText(name);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });

//        refresh.setOnClickListener(v -> refresh(activity));
       */
/* showCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               com.smartwebarts.upnishadacademy.utils.Toolbar_Set.this.showCart(activity);
            }
        });
        getCartList(activity);*//*

    }
*/

    /*public void getCartList(final Activity activity) {

        class GetTasks extends AsyncTask<Void, Void, ArrayList<Task>> {

            @Override
            protected ArrayList<Task> doInBackground(Void... voids) {
                List<Task> tasks= DatabaseClient.getmInstance(activity.getApplicationContext())
                        .getAppDatabase()
                        .taskDao()
                        .getAll();
                return new ArrayList<>(tasks);
            }

            @Override
            protected void onPostExecute(ArrayList<Task> tasks) {
                int size = tasks!=null?tasks.size():0;
                TextView cartItemsCount = activity.findViewById(R.id.cartItemsCount);
                if (cartItemsCount != null) {
                    cartItemsCount.setText(""+size);
                }
                TextView cart_badge = activity.findViewById(R.id.cart_badge);

                if (cart_badge !=null) {
                    cart_badge.setText(""+size);
                }

                try{

                    double sum = 0;
                    for (Task task: tasks) {
                        double price = Double.parseDouble("0"+(task.getCurrentprice()==null?"":task.getCurrentprice()));
                        double qty = Double.parseDouble("0"+task.getQuantity());
                        double total = price*qty;
                        sum+=total;
                    }

                    NumberFormat nf = NumberFormat.getInstance();
                    nf.setMaximumFractionDigits(2);
                    TextView utility = activity.findViewById(R.id.utility);

                    if (activity instanceof LoginActivity) {
                        if (utility != null) {
                            utility.setVisibility(View.GONE);
                        }
                        return;
                    }
                    if (utility!=null) {
                        utility.setText(activity.getString(R.string.currency) + " "+ nf.format(sum));
                        utility.setVisibility(View.VISIBLE);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
//                userWallet(activity);

            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }

    public void showCart(Activity activity){

    }

    public void refresh(Activity activity) {
        com.smartwebarts.upnishadacademy.utils.Toolbar_Set.INSTANCE.getCartList(activity);
    }


    public  void delete(final Activity activity, final Task task) {

        class GetTasks extends AsyncTask<Void, Void, ArrayList<Task>> {

            @Override
            protected ArrayList<Task> doInBackground(Void... voids) {
                        DatabaseClient.getmInstance(activity.getApplicationContext())
                        .getAppDatabase()
                        .taskDao()
                        .delete(task);
                return null;
            }

            @Override
            protected void onPostExecute(ArrayList<Task> tasks) {

            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }*/


//    public void userWallet(Activity activity) {
//
//        AppSharedPreferences preferences = new AppSharedPreferences(activity.getApplication());
//        if (preferences.getLoginUserLoginId() !=null ){
//            TextView utility = activity.findViewById(R.id.utility);
//            if (UtilMethods.INSTANCE.isNetworkAvialable(activity)) {
//                UtilMethods.INSTANCE.userWallet(activity, preferences.getLoginUserLoginId(), new mCallBackResponse() {
//                    @Override
//                    public void success(String from, String message) {
//
//                        AmountModel amountModel = new Gson().fromJson(message, AmountModel.class);
//
////                        if (utility!=null) {
////                            utility.setText(activity.getString(R.string.currency) + " "+ amountModel.getAmount());
////                            utility.setVisibility(View.VISIBLE);
////                        }
//
//                        if (activity instanceof WalletActivity) {
//                            TextView Wallet_Ammount = (TextView) activity.findViewById(R.id.wallet_ammount);
//                            Wallet_Ammount.setText(activity.getString(R.string.currency) + " "+ amountModel.getAmount());
//                        }
//                    }
//
//                    @Override
//                    public void fail(String from) {
//
//                    }
//                });
//            }
//        }
    //}
}

