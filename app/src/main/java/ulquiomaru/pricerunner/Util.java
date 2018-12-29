package ulquiomaru.pricerunner;

import android.support.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class Util {

    final static String urlBarkodoku = "http://m.barkodoku.com/%s";

    static String HttpGet(String myUrl) throws IOException {
        String result;

        URL url = new URL(myUrl);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.connect();
        InputStream inputStream = conn.getInputStream();

        if (inputStream != null) result = convertInputStreamToString(inputStream);
        else result = "Did not work!";

        return result;
    }

    @NonNull
    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
        String line;
        StringBuilder sb = new StringBuilder();

        while ((line = bufferedReader.readLine()) != null)
            sb.append(line);

        inputStream.close();

        return sb.toString();
    }
}
