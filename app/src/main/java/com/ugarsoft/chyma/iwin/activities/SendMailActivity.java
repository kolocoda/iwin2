package com.ugarsoft.chyma.iwin.activities;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ugarsoft.chyma.iwin.R;
import com.ugarsoft.chyma.iwin.models.AppUser;
import com.ugarsoft.chyma.iwin.models.MessagePriority;
import com.ugarsoft.chyma.iwin.utils.CompatUtil;
import com.ugarsoft.chyma.iwin.utils.DBQuery;
import com.ugarsoft.chyma.iwin.utils.KeyboardUtil;
import com.ugarsoft.chyma.iwin.utils.MessageUtil;
import com.ugarsoft.chyma.iwin.utils.Pref;
import com.ugarsoft.chyma.iwin.utils.SendMailTask;

import org.apache.commons.lang.WordUtils;

import java.util.Arrays;
import java.util.List;

public class SendMailActivity extends AppCompatActivity {

    private boolean shouldAnimate = true;
    private TextView priorityText;
    private EditText subjectText;
    private EditText messageText;
    private View mailer;

    private MessagePriority selectedPriority;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mail);
        setupViews();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        if (CompatUtil.hasLolipop() && shouldAnimate) {
            toolbar.setAlpha(0f);
            toolbar.animate().translationZBy(40f).alphaBy(1f).setDuration(1500).start();
        }

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.colorPrimary));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        priorityText = (TextView) findViewById(R.id.priorityText);
        subjectText = (EditText) findViewById(R.id.subjectText);
        messageText = (EditText) findViewById(R.id.MessageText);
        mailer = findViewById(R.id.mailer);

        KeyboardUtil.setupUI(mailer, SendMailActivity.this);

        priorityText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPriorityChooser();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_send_mail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_send) {
            sendMail();
        }
        return super.onOptionsItemSelected(item);
    }


    private void showPriorityChooser(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final List<MessagePriority> priorities = DBQuery.getAllMessagePriority(SendMailActivity.this);

        CharSequence[] sequences = new CharSequence[priorities.size()];
        for (int index = 0; index < sequences.length; index++) {
            sequences[index] = WordUtils.capitalize((priorities.get(index).getName()).toLowerCase());
        }
        builder.setTitle("Select Priority");
        builder.setSingleChoiceItems(sequences, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                selectedPriority = priorities.get(i);
                priorityText.setText("Priority: " +WordUtils.capitalize((selectedPriority.getName()).toLowerCase()));

            }
        });
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.show();
    }

    private void sendMail(){
        AppUser user = Pref.getUser(SendMailActivity.this);
        if(user != null) {
            /*Mail m = new Mail("cabafor@gmail.com", "bafosteck2");

            String[] toArr = {"cabafor@gmail.com"};
            m.set_to(toArr);
            m.set_from(user.getEmail());
            m.set_subject(subjectText.getText().toString());

            StringBuilder sb = new StringBuilder();
            sb.append("Ticket from user: " + user.getUsername() + "\n");
            sb.append("Message Priority: " + selectedPriority.getName() + "\n\n");
            sb.append(messageText.getText().toString());

            m.setBody(sb.toString());*/
            StringBuilder sb = new StringBuilder();
            sb.append("Ticket from user: " + user.getUsername() + "<br>");
            sb.append("Message Priority: " + selectedPriority.getName() + "<br><br>");
            sb.append(messageText.getText().toString());
            String[] toArr = {"admin@iwin.org.ng"};
            List<String> toEmailList = Arrays.asList(toArr);

            try {
              //  m.addAttachment("");

                /*if (m.send()) {
                    Toast.makeText(SendMailActivity.this, "Email was sent successfully.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(SendMailActivity.this, "Email was not sent.", Toast.LENGTH_LONG).show();
                }*/
                new SendMailTask(SendMailActivity.this).execute("iwinmobileapp@gmail.com", "iwin_admin"
                        , toEmailList, subjectText.getText().toString(), sb.toString());
                Toast.makeText(SendMailActivity.this, "Mail sent successfully.", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                //Toast.makeText(MailApp.this, "There was a problem sending the email.", Toast.LENGTH_LONG).show();
                Log.e("MailApp", "Could not send email", e);
            }
        }else {
            MessageUtil.showAlert(SendMailActivity.this, "Something went wrong, please try to logout and login again. Thank you.");
        }
    }
}
