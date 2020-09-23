package com.guardian.guardianadmin_v1.Transmissions;

import android.content.Context;
import android.media.session.MediaSession;
import android.os.AsyncTask;
import android.widget.Toast;


import com.guardian.guardianadmin_v1.SignInActivity;
import com.guardian.guardianadmin_v1.UserModels.UserList;

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
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class AllPhoneGetter extends AsyncTask<String,Void,String> {

    private Toast toast;
    private static String ans="asd";

    public AllPhoneGetter() {
    }

    @Override
    protected String doInBackground(String... strings) {
        String type = strings[0];
        String login_url = "https://www.guardianapp.ir/get_all_nums.php";
        if (type.equals("check")) {
            try {
                String token = strings[1];

                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data = URLEncoder.encode("token", "UTF-8") + "=" + URLEncoder.encode(token, "UTF-8") ;

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
                System.err.println(result);
                ans = result;
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

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    public static void updateData(String token){
        AllPhoneGetter getter = new AllPhoneGetter();
        getter.execute("check",token);
        try{
            sleep(500);
        }catch (Exception e){

        }
        System.out.println(ans);
        String[] rawServerData = ans.split(" ");
        if(!ans.startsWith("Connected - ")){
            return;
        }
        ArrayList<String> numbers = new ArrayList<>();

        for(int i=2;i<rawServerData.length;i++){
            numbers.add(rawServerData[i]);
        }

        UserList.setAllPhoneNumbers(numbers);
    }


}
