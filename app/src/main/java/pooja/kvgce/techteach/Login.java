package pooja.kvgce.techteach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Login extends AppCompatActivity {
    String mname,pass;
    SQLiteDatabase db;
    EditText e1,e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db=openOrCreateDatabase("TECHTEACH",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS user(name VARCHAR);");
        e1=(EditText)findViewById(R.id.login_emailid);
        e2=(EditText)findViewById(R.id.login_password);
    }
    public void Login(View v)
    {
        e1=(EditText)findViewById(R.id.login_emailid);
        e2=(EditText)findViewById(R.id.login_password);
        mname=e1.getText().toString();
        pass=e2.getText().toString();
        new MyAsyncTask().execute("https://techteachingkvgce.000webhostapp.com/loginvalidate.php?mname="+mname+"&pass="+pass);
    }

    public class MyAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            // TODO Auto-generated method stub
            return GET(urls[0]);
        }


        @Override
        protected void onPostExecute(String resp) {
            // TODO Auto-generated method stub
            //p1.setVisibility(View.GONE);

            //Toast.makeText(getBaseContext(), resp, Toast.LENGTH_LONG).show();
            if(resp.contains("success"))
            {
                db.execSQL("INSERT INTO user VALUES('"+mname+"');");
                Intent in=new Intent(Login.this,Dashboard.class);
                startActivity(in);

            }
            else
            {
                Toast.makeText(getBaseContext(), "Wrong Username or Password", Toast.LENGTH_LONG).show();
            }


        }

        private String GET(String url) {
            // TODO Auto-generated method stub
            InputStream inputStream = null;
            String resp = "";
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
                inputStream = httpResponse.getEntity().getContent();
                if (inputStream != null)
                    resp = convert(inputStream);
                else
                    resp = "nor work";
            } catch (Exception e) {

            }


            return resp;
        }

        private String convert(InputStream inputStream) throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String resp = "";
            String line = "";
            while ((line = reader.readLine()) != null)
                resp += line;


            return resp;


        }
    }

}