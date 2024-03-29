package com.guardian.guardianadmin_v1.Transmissions;

import android.content.Context;
import android.media.session.MediaSession;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.Toast;


import androidx.annotation.RequiresApi;

import com.guardian.guardianadmin_v1.MainActivity;
import com.guardian.guardianadmin_v1.MainUserActivity;
import com.guardian.guardianadmin_v1.SignInActivity;

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
import java.time.LocalDateTime;

import static java.lang.Thread.sleep;

public class SingleUserDated extends AsyncTask<String, Void, String> {
    private static String ans = "asd";
    private static String[] data = new String[15];

    public SingleUserDated() {
    }

    @Override
    protected String doInBackground(String... strings) {

        String login_url = "https://www.guardianapp.ir/get_user_det_2.php";

        try {
            String token = strings[0];
            String number = strings[1];
            String date_i = strings[2];
            String date_f = strings[3];

            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            String post_data = URLEncoder.encode("token","UTF-8")+"="+URLEncoder.encode(token,"UTF-8")+"&"+URLEncoder.encode("number","UTF-8")+"="+URLEncoder.encode(number,"UTF-8")
                    +"&"+URLEncoder.encode("date_i","UTF-8")+"="+URLEncoder.encode(date_i,"UTF-8")+"&"+URLEncoder.encode("date_f","UTF-8")+"="+URLEncoder.encode(date_f,"UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

            String result = "";
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            System.err.println("aaaaaaaaaaaaaaaaaaakirkirkiraaaaaaaaaaaaaaaaaaa");
            System.err.println(result);
            ans = result;
            data = ans.split(" ");
            MainUserActivity.updateUserData(ans.split(" "));
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onPostExecute(String result) {

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void getUserDetailed(String token, String number, LocalDateTime d){
        SingleUserDated singleUserWorker = new SingleUserDated();
        singleUserWorker.execute(token,number,d.toString(),d.plusDays(1).toString());
    }

    public static String[] getData() {
        return data;
    }
}
