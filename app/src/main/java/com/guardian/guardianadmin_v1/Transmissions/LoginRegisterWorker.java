package com.guardian.guardianadmin_v1.Transmissions;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Thread.sleep;

import com.guardian.guardianadmin_v1.R;
import com.guardian.guardianadmin_v1.SignInActivity;
import com.guardian.guardianadmin_v1.SignUpActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class LoginRegisterWorker extends AsyncTask<String,Void,String> {
    Context context;
    public LoginRegisterWorker(Context ctx){
        context = ctx;
    }
    @Override
    protected String doInBackground(String... strings) {
        String type = strings[0];
        String register_url = "https://www.guardianapp.ir/register747admin380.php" ;
        String login_url = "https://www.guardianapp.ir/login_admin_111.php";
        if(type.equals("register")){
            try {
                String username = strings[1];
                String password = strings[2];
                String phoneNum = strings[3];

                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")
                        +"&"+URLEncoder.encode("number","UTF-8")+"="+URLEncoder.encode(phoneNum,"UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));

                String result="";
                String line="";

                while((line = bufferedReader.readLine()) != null){
                    result += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                SignUpActivity.setRegisterResult(result);
                System.err.println(result);

                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (type.equals("login")){
            try {
                String username = strings[1];
                String password = strings[2];

                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));

                String result="";
                String line="";

                while((line = bufferedReader.readLine()) != null){
                    result += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                SignInActivity.setLoginResult(result);
                System.err.println(result);

                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onPostExecute(String result) {
        //toast = Toast.makeText(context,result,Toast.LENGTH_LONG);
        //toast.show();
//        try {
//            sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        if(result.startsWith("Success")){
            //bayad baade register e moafagh login  e moafagh ham dashte bashim
        }

        if( context instanceof SignUpActivity ) {
            TextView messageText = ((Activity)context).findViewById(R.id.messageTextSignUp);
            if(result.contains("register complete")) {
                messageText.setText("لطفا چند لحظه منتظر بمانید.");
                messageText.setTextColor(context.getResources().getColor(R.color.colorPositiveError));
            } else if(result.contains("invalid characters were detected")) {
                messageText.setText("لطفا از کاراکتر های غیر مجاز مثل '*' و 'فاصله' استفاده نکنید.");
                messageText.setTextColor(context.getResources().getColor(R.color.colorNegativeError));
            } else if(result.contains("username is invalid")) {
                messageText.setText("شماره تلفن همراه وارد شده صحیح نمی باشد.");
                messageText.setTextColor(context.getResources().getColor(R.color.colorNegativeError));
            } else if(result.contains("passwords must be at least")) {
                messageText.setText("رمز عبور باید حداقل ۸ کاراکتر و شامل عدد و حروف انگلیسی باشد.");
                messageText.setTextColor(context.getResources().getColor(R.color.colorNegativeError));
            } else if(result.contains("number is in use")) {
                messageText.setText("شماره تلفن همراه وارد شده تکراری می باشد.");
                messageText.setTextColor(context.getResources().getColor(R.color.colorNegativeError));
            } else {
                messageText.setText("سرور پاسخگو نمی باشد؛ لطفا چند دقیقه دیگر تلاش کنید.");
                messageText.setTextColor(context.getResources().getColor(R.color.colorNegativeError));
            }

        } else {
            TextView messageText = ((Activity)context).findViewById(R.id.messageTextSignIn);


            if(result.contains("login complete")) {
                messageText.setText("لطفا چند لحظه منتظر بمانید.");
                messageText.setTextColor(context.getResources().getColor(R.color.colorPositiveError));
            } else if(result.contains("invalid characters were detected")) {
                messageText.setText("لطفا از کاراکتر های غیر مجاز مثل '*' و 'فاصله' استفاده نکنید.");
                messageText.setTextColor(context.getResources().getColor(R.color.colorNegativeError));
            } else if(result.contains("login failed")) {
                messageText.setText("نام کاربری یا رمز عبور صحیح نمی باشد.");
                messageText.setTextColor(context.getResources().getColor(R.color.colorNegativeError));
            } else {
                messageText.setText("سرور پاسخگو نمی باشد؛ لطفا چند دقیقه دیگر تلاش کنید.");
                messageText.setTextColor(context.getResources().getColor(R.color.colorNegativeError));
            }
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}