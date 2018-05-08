package cn.boommanpro.http.helper.http;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtil {

    private static int SUCCESS_CODE = 200;

    public static String getUrlData(String link) {
        HttpURLConnection httpUrlConnection = null;
        try {
            URL url = new URL(link);
            URLConnection urlconnection = url.openConnection();
            httpUrlConnection = (HttpURLConnection) urlconnection;
            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setDoInput(true);
            httpUrlConnection.setInstanceFollowRedirects(false);
            httpUrlConnection.setRequestProperty("Connection", "keep-alive");
            httpUrlConnection.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36");
            httpUrlConnection.setRequestMethod("GET");
            httpUrlConnection.connect();
            if (httpUrlConnection.getResponseCode() == SUCCESS_CODE) {
                return getResult(httpUrlConnection);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (httpUrlConnection != null) {
                httpUrlConnection.disconnect();
            }

        }
        return null;
    }

    public static String getUrlDataAppenNewLine(String link) {
        HttpURLConnection httpUrlConnection = null;
        try {
            URL url = new URL(link);
            URLConnection urlconnection = url.openConnection();
            httpUrlConnection = (HttpURLConnection) urlconnection;
            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setDoInput(true);
            httpUrlConnection.setInstanceFollowRedirects(false);
            httpUrlConnection.setRequestProperty("Connection", "keep-alive");
            httpUrlConnection.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36");
            httpUrlConnection.setRequestMethod("GET");
            httpUrlConnection.connect();
            if (httpUrlConnection.getResponseCode() == SUCCESS_CODE) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream(), "utf-8"));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line + "\n");
                }
                return result.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (httpUrlConnection != null) {
                httpUrlConnection.disconnect();
            }

        }
        return null;
    }

    private static String getResult(HttpURLConnection httpUrlConnection) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream(), "utf-8"));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }


}
