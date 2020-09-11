package com.github.johnkeno.gadsleadersboard;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.github.johnkeno.gadsleadersboard.network.main.APIPostClient;
import com.github.johnkeno.gadsleadersboard.network.main.PostData;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class SubmitActivity extends AppCompatActivity {

    private EditText mViewFirstName;
    private EditText mViewLastName;
    private EditText mViewEmail;
    private EditText mViewGitHubLink;
    private View mDialogVerify;
    private AlertDialog mAlertDialog;
    private Button mViewSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit_activity);
        mViewFirstName = findViewById(R.id.edit_first_name);
        mViewLastName = findViewById(R.id.edit_last_name);
        mViewEmail = findViewById(R.id.edit_email);
        mViewGitHubLink = findViewById(R.id.edit_github_link);
        mViewSubmitButton = findViewById(R.id.button_submit);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeKeyboard();

                contentViewState(mViewFirstName,mViewLastName,mViewEmail,
                        mViewGitHubLink, mViewSubmitButton,INVISIBLE);


                AlertDialog.Builder builder = new AlertDialog.Builder(SubmitActivity.this);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                mDialogVerify = LayoutInflater.from(view.getContext())
                        .inflate(R.layout.dialog_verify, viewGroup, false);
                builder.setCancelable(false);
                builder.setView(mDialogVerify);

                mDialogVerify. findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mAlertDialog.dismiss();
                    }
                });
                mDialogVerify.findViewById(R.id.button_verify).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Sending data",Toast.LENGTH_SHORT).show();
                        executeFeedbackForm(
                                mViewFirstName.getText().toString(),
                                mViewLastName.getText().toString(),
                                mViewEmail.getText().toString(),
                                mViewGitHubLink.getText().toString()
                        );

                        mAlertDialog.dismiss();
                    }
                });

                mAlertDialog = builder.create();
                mAlertDialog.show();
                mAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        contentViewState(mViewFirstName,mViewLastName,mViewEmail,
                                mViewGitHubLink,mViewSubmitButton,VISIBLE);
                    }
                });
            }
        });

    }

    private void closeKeyboard() {
        View view = getCurrentFocus();
        if (view != null){
            InputMethodManager imn = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imn.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

    private void executeFeedbackForm
            (String firstName, String lastName, String Email, String gitHubLink) {

         Call<PostData> postData = APIPostClient.sAPIPostService().submitData(
                        firstName,
                        lastName,
                        Email,
                        gitHubLink);
        //noinspection NullableProblems
        postData.enqueue(new Callback<PostData>() {
            @SuppressWarnings("NullableProblems")
            @Override
            public void onResponse(Call<PostData> call, Response<PostData> response) {
                Toast.makeText(getApplicationContext(),
                        "Error code: "+response.code(),Toast.LENGTH_SHORT).show();
                showResponsDialog(R.layout.dialog_not_successful);

            }

            @SuppressWarnings("NullableProblems")
            @Override
            public void onFailure(Call<PostData> call, Throwable t) {
                if (t.getLocalizedMessage().equals("Use JsonReader.setLenient(true)" +
                        " to accept malformed JSON at line 1 column 1 path $")  ){
                    Toast.makeText(getApplicationContext(),
                            "Data sent",Toast.LENGTH_LONG).show();
                    showResponsDialog(R.layout.dialog_successful);
                }
                else {

                    Toast.makeText(getApplicationContext(),
                            "Check internet connection",Toast.LENGTH_LONG).show();
                    showResponsDialog(R.layout.dialog_not_successful);
                }

            }
        });

    }


    private void showResponsDialog(int messageDialog) {
        contentViewState(mViewFirstName,mViewLastName,mViewEmail,
                mViewGitHubLink, mViewSubmitButton, INVISIBLE);

        AlertDialog.Builder builder = new AlertDialog.Builder(SubmitActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogSuccess = LayoutInflater.from(viewGroup.getContext())
                .inflate(messageDialog, viewGroup, false);
        builder.setCancelable(true);
        builder.setView(dialogSuccess);
        AlertDialog success = builder.create();
        success.show();
        success.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                contentViewState(mViewFirstName,mViewLastName,mViewEmail,
                        mViewGitHubLink, mViewSubmitButton, VISIBLE);
            }
        });
        success.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                contentViewState(mViewFirstName,mViewLastName,mViewEmail,
                        mViewGitHubLink, mViewSubmitButton, VISIBLE);
            }
        });

    }

    public static void contentViewState(
            TextView viewFirstName, TextView viewLastName, TextView viewEmail,
            TextView viewGitHubLink, Button viewSubmitButton, int visible) {
        viewFirstName.setVisibility(visible);
        viewLastName.setVisibility(visible);
        viewEmail.setVisibility(visible);
        viewGitHubLink.setVisibility(visible);
        viewSubmitButton.setVisibility(visible);
    }
}