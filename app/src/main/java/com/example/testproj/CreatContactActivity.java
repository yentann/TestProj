package com.example.testproj;

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

public class CreatContactActivity extends AppCompatActivity {

    private EditText etName, etContact, etEmail;
    private Button btnCreate;
    private AsyncHttpClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        etName = findViewById(R.id.etName);
        etContact = findViewById(R.id.etContact);
        etEmail = findViewById(R.id.etEmail);
        btnCreate = findViewById(R.id.btnCreate);
        client = new AsyncHttpClient();

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCreateOnClick(v);
            }
        });

    }//end onCreate


    //add
    private void btnCreateOnClick(View v) {
        RequestParams params = new RequestParams();
        params.add("name", etName.getText().toString());
        params.add("contact", etContact.getText().toString());
        params.add("email", etEmail.getText().toString());

        client.get("https://smac-biz.000webhostapp.com/SmacBiz/insertStaff.php", params, new JsonHttpResponseHandler() {

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
}