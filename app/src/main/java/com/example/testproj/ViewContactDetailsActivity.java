package com.example.testproj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ViewContactDetailsActivity extends AppCompatActivity {

    private EditText etName, etContact, etEmail;
    private Button btnUpdate, btnDelete;
    private int contactId;
    private AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact_details);

        etName = findViewById(R.id.etName);
        etContact = findViewById(R.id.etContact);
        etEmail = findViewById(R.id.etEmail);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        client = new AsyncHttpClient();

        Intent intent = getIntent();
        contactId = intent.getIntExtra("staff_id", -1);

        //TODO: call getContactDetails.php with the id as a parameter
        //TODO: set the text fields with the data retrieved

        RequestParams params = new RequestParams();
        params.add("id", String.valueOf(contactId));

        //View Contact Details
        //Using GET


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestParams params = new RequestParams();
                params.put("staff_id", String.valueOf(contactId));
                params.put("name", etName.getText().toString());
                params.put("contact", etContact.getText().toString());
                params.put("email", etEmail.getText().toString());

                client.get("https://smac-biz.000webhostapp.com/SmacBiz/updateStaff.php", params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            Log.i("JSON Results: ", response.toString());

                            Toast.makeText(getApplicationContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                            finish();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                });
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestParams params = new RequestParams();
                params.put("staff_id", String.valueOf(contactId));

                client.get("https://smac-biz.000webhostapp.com/SmacBiz/deleteStaff.php", params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            Log.i("JSON Results: ", response.toString());

                            Toast.makeText(getApplicationContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                            finish();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                });
            }
        });
    }
}



