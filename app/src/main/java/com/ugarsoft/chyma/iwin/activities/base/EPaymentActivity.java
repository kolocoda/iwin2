package com.ugarsoft.chyma.iwin.activities.base;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ugarsoft.chyma.iwin.R;
import com.ugarsoft.chyma.iwin.activities.WebViewActivity;
import com.ugarsoft.chyma.iwin.models.EPay;
import com.ugarsoft.chyma.iwin.models.PaymentUrl;
import com.ugarsoft.chyma.iwin.utils.DBService;
import com.ugarsoft.chyma.iwin.utils.FontUtil;
import com.ugarsoft.chyma.iwin.utils.MessageUtil;
import com.ugarsoft.chyma.iwin.utils.WebServiceImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class EPaymentActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private List<EPay> payList = new ArrayList<>();
    private ContactAdapter contactAdapter;
    private DBService dbService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_epayment);
        setUpNavigationBar();
        setUpViews();
        loadAreas();
    }

    private void setUpViews() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        contactAdapter = new ContactAdapter(this, payList);
        recyclerView.setAdapter(contactAdapter);
    }
    private void loadAreas() {
        dbService = DBService.getInstance(this);
        payList.clear();
        if (payList.isEmpty()) {
            try {
                Dao<EPay, Long> dao = dbService.getDao(EPay.class);
                List<EPay> list = dao.queryForAll();
                for (EPay con : list){
                    payList.add(con);
                    Log.i("TAG", con.getArea());
                }
                contactAdapter.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getMenuItemType() {
        return NAVIGATION_MENU_ITEM_EPAYMENT_OPTIONS;
    }

    private class ContactAdapter extends RecyclerView.Adapter<TaskViewHolder> {
        Context context;
        List<EPay> ePayList;
        DBService dbService;

        public ContactAdapter(Context context, List<EPay> list) {
            this.context = context;
            this.ePayList = list;
            dbService = DBService.getInstance(context);
        }

        @Override
        public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.list_item_contact, null);
            return new TaskViewHolder(context, view);
        }

        @Override
        public void onBindViewHolder(TaskViewHolder holder, int position) {
            EPay contact = ePayList.get(position);
            int image = contact.getImageCode();
            if (image != 0 ) {
                holder.contactLogo.setImageResource(image);
            } else {
                holder.contactLogo.setImageResource(R.drawable.ic_menu_news);
            }

            holder.contactTitleTextView.setText(contact.getArea());
            holder.contactDescTextView.setText("");
            holder.contactCard.setTag(contact);
            holder.contactCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                   EPay pay = (EPay) view.getTag();
                    if(pay.isePayIsavailable()) {
                        showPaymentChannelDialog(pay);
                    }
                    else{
                        MessageUtil.showAlert(EPaymentActivity.this, "Sorry, this facility is not available for this DisCo at this time.");
                    }

                }
            });
        }

        private void showPaymentChannelDialog(EPay pay) {
            final String[] urlData = new String[1];
            final List<PaymentUrl> urls = getListofPayChannelByCode(pay);
            CharSequence[] items = getPaymentOptionsNameFromPaymentUrlList(urls);

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setPositiveButton(FontUtil.getCustomText(context, "OK"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    if(WebServiceImpl.deviceIsConnected(EPaymentActivity.this)) {
                        Intent intent = new Intent(EPaymentActivity.this, WebViewActivity.class);
                        intent.setData(Uri.parse(urlData[0]));
                        startActivity(intent);
                    } else{
                        MessageUtil.showAlert(EPaymentActivity.this, "Please check your internet connection and try again.");
                    }
                }
            });
            builder.setNegativeButton(FontUtil.getCustomText(context, "CANCEL"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.setTitle("Select Payment Channel");
            builder.setIcon(R.drawable.icon);
            builder.setSingleChoiceItems(items,-1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    urlData[0] = urls.get(i).getUrl();
                    Log.i("TAG", urlData[0]);
                }
            });
            builder.show();
        }

        private List<PaymentUrl> getListofPayChannelByCode(EPay epay){
            List<PaymentUrl> paymentUrls = new ArrayList<>();

            try{
                Dao<PaymentUrl, Long> dao = dbService.getDao(PaymentUrl.class);
                QueryBuilder<PaymentUrl, Long> qb = dao.queryBuilder();
                qb.where().eq("code", epay.getCode());
                paymentUrls.addAll(qb.query());

            } catch (Exception e){
                Log.i("TAG", "Cannot retrieve payment url");
                e.printStackTrace();
            }
            return  paymentUrls;
        }

        private CharSequence[] getPaymentOptionsNameFromPaymentUrlList(List<PaymentUrl> urls){
           CharSequence[] seq = new  CharSequence[urls.size()];
            int i = 0;
            for (PaymentUrl p : urls){
                seq[i] = p.getTitle();
                i++;
            }
            return seq;
        }

        @Override
        public int getItemCount() {
            return ePayList.size();
        }

    }

    private static class TaskViewHolder extends RecyclerView.ViewHolder {

        private TextView contactTitleTextView;
        private TextView contactDescTextView;
        private CardView contactCard;
        private ImageView contactLogo;

        public TaskViewHolder(Context context, View itemView) {
            super(itemView);
            contactTitleTextView = (TextView) itemView.findViewById(R.id.contactTitleTextView);
            contactDescTextView = (TextView) itemView.findViewById(R.id.contactDescTextView);
            contactCard = (CardView) itemView.findViewById(R.id.contactCard);

            FontUtil.setDefaultTypeFace(context, contactDescTextView);
            FontUtil.setDefaultTypeFace(context, contactTitleTextView, FontUtil.FontType.ROBOTO_MEDIUM);
            contactLogo = (ImageView) itemView.findViewById(R.id.stateLogo);

        }
    }
}
