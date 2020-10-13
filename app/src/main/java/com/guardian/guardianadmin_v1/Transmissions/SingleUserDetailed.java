package com.guardian.guardianadmin_v1.Transmissions;

import android.content.Context;
import android.media.session.MediaSession;
import android.os.AsyncTask;
import android.widget.Toast;


import com.guardian.guardianadmin_v1.MainActivity;
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
import static java.lang.Thread.sleep;

public class SingleUserDetailed extends AsyncTask<String, Void, String> {
    private static String ans = "asd";

    public SingleUserDetailed() {
    }

    @Override
    protected String doInBackground(String... strings) {

        String login_url = "https://www.guardianapp.ir/get_user_det_2.php";

        try {
            String token = strings[0];
            String number = strings[1];

            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            String post_data = URLEncoder.encode("token","UTF-8")+"="+URLEncoder.encode(token,"UTF-8")+"&"+URLEncoder.encode("number","UTF-8")+"="+URLEncoder.encode(number,"UTF-8");
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

    public static String[] getUserDetailed(String token,String number){

        SingleUserWorker singleUserWorker = new SingleUserWorker();
        singleUserWorker.execute(token,number);

        if(ans.split(" ").length != 15){
            return null;
        }
        String[] result = ans.split(" ");
        String[] retVal = new String[4];
        retVal[0] = number;
        for(int i=1;i<13;i++){
            retVal[i] = result[i+2];
        }
        return retVal;
    }

}
