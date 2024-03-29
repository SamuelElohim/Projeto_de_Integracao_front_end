package service;

import com.google.gson.Gson;
import model.AbstractDtoObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public abstract class HttpRestClient {

    private static final String BASEURL = "http://localhost:8080/api/";

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
        String fullURL = BASEURL.concat(endUrl);

        return getHTTPRequest(fullURL);
    }

    public static String getDatabaseJSON(String endUrl, String filter) {
        String fullURL = String.format("%s/%s/%s", BASEURL, endUrl, filter.replaceAll(" ", "%20"));

        return getHTTPRequest(fullURL);
    }

    public static <T extends AbstractDtoObject> List<T> getDatabaseList(Class<T[]> dtoClass, String endURL) {
        Gson gson = new Gson();
        return Arrays.asList(gson.fromJson(getDatabaseJSON(endURL), dtoClass));
    }

}
