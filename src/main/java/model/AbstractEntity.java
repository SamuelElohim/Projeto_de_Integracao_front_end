package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class AbstractEntity {

    static String getHTTPRequest(String requestURL) {
        String json = "";
        try {
            URL url = new URL(requestURL);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            if (conexao.getResponseCode() != 200){
                throw new RuntimeException("HTTP error code : " + conexao.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conexao.getInputStream())));
            String output;

            while ((output = br.readLine()) != null){
                json += output;
            }
            conexao.disconnect();

        } catch (Exception e) {
            System.out.println(e);
        }
        return json;
    }

    public static String getDatabaseJSON(String endUrl) {
        String baseURL = "http://localhost:8080/api/";
        String fullURL = baseURL.concat(endUrl);

        return getHTTPRequest(fullURL);

    }

    public static String getDatabaseJSON(String endUrl, String filter) {
        String baseURL = "http://localhost:8080/api";
        String fullURL = String.format("%s/%s/%s", baseURL, endUrl, filter.replaceAll(" ", "%20"));

        return getHTTPRequest(fullURL);
    }


}
