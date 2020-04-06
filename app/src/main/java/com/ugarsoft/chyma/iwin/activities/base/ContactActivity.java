package com.ugarsoft.chyma.iwin.activities.base;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ugarsoft.chyma.iwin.R;
import com.ugarsoft.chyma.iwin.models.Contact;
import com.ugarsoft.chyma.iwin.models.EPay;
import com.ugarsoft.chyma.iwin.utils.DBService;
import com.ugarsoft.chyma.iwin.utils.FontUtil;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends BaseActivity {

    private DBService dbService;
    private RecyclerView recyclerView;
    private List<Contact> contactList = new ArrayList<>();
    private ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        setUpNavigationBar();
        loadContacts();
        setUpViews();

    }

    private void setUpViews() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        contactAdapter = new ContactAdapter(this, contactList);
        recyclerView.setAdapter(contactAdapter);
    }

    void loadContacts() {
        dbService = DBService.getInstance(this);
        contactList.clear();
        if (contactList.isEmpty()) {
            try {
                Dao<Contact, Long> dao = dbService.getDao(Contact.class);
                List<Contact> list = dao.queryForAll();
                for (Contact con : list){
                    contactList.add(con);
                    Log.i("TAG", con.getName());
                }
               contactAdapter.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class ContactAdapter extends RecyclerView.Adapter<TaskViewHolder> {
        Context context;
        List<Contact> contacts;
        DBService dbService;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM, yyy HH:mm");

        public ContactAdapter(Context context, List<Contact> contactList) {
            this.context = context;
            this.contacts = contactList;
            dbService = DBService.getInstance(context);
        }

        @Override
        public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.list_item_contact, null);
            return new TaskViewHolder(context, view);
        }

        @Override
        public void onBindViewHolder(TaskViewHolder holder, int position) {
            Contact contact = contacts.get(position);
            int image = getContactImageCodeFromEPay(contact);
            if (image != 0 ) {
                holder.contactLogo.setImageResource(image);
            } else {
                holder.contactLogo.setImageResource(R.drawable.ic_menu_news);
            }

            holder.contactTitleTextView.setText(contact.getName());
            holder.contactDescTextView.setText(contact.getDesc());
            holder.contactCard.setTag(contact);
            holder.contactCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Contact con = (Contact) view.getTag();
                    String name = splitContactName(con);
                    showWebViewDialog(view, name, con);

                }
            });
        }
        private  int getContactImageCodeFromEPay(Contact con){
            int i = 0;
            try {
                Dao<EPay, Long> dao = dbService.getDao(EPay.class);
                QueryBuilder<EPay, Long> qb = dao.queryBuilder();
                qb.where().eq("code", con.getCode());
                i = qb.query().get(0).getImageCode();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return  i;
        }

        private String splitContactName(Contact con){
            String[] str =  con.getName().split("\\s+");
            return str[0];
        }
        @Override
        public int getItemCount() {
            return contacts.size();
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

            FontUtil.setDefaultTypeFace(context, contactDescTextView);
            FontUtil.setDefaultTypeFace(context, contactTitleTextView, FontUtil.FontType.ROBOTO_MEDIUM);
            contactLogo = (ImageView) itemView.findViewById(R.id.stateLogo);
            contactCard =(CardView) itemView.findViewById(R.id.contactCard);

        }
    }

    @Override
    public int getMenuItemType() {
        return NAVIGATION_MENU_ITEM_CONTACT;
    }

    private void showWebViewDialog(View view, String title, Contact contact){
        view = LayoutInflater.from(ContactActivity.this).inflate(R.layout.dialog_web_view, null);


        final WebView webView = (WebView) view.findViewById(R.id.dialog_webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadData(contact.getContent(), "text/html", "UTF-8");

        AlertDialog.Builder builder = new AlertDialog.Builder(ContactActivity.this);
        builder.setView(view);
        builder.setTitle(title);
        builder.setIcon(contact.getImageCode());
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();


            }
        });
        builder.show();

    }
}
