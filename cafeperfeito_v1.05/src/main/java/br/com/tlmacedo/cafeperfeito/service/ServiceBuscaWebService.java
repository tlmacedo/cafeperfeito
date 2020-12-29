package br.com.tlmacedo.cafeperfeito.service;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.concurrent.TimeoutException;

public class ServiceBuscaWebService {
    public static Object wsJsonObjectWebServiceVO;
    public static String strRetURL;
    public static BufferedReader bufferedReader;
    public static StringBuilder stringBuilder;
    public static String linhaRetorno = null;

    public static HttpURLConnection urlConnection;

    public static JSONObject jsonObject;

    public static JSONObject getJsonObjectWebService(String strUrl) {
        jsonObject = null;
        try {
            wsJsonObjectWebServiceVO = new URL(strUrl).openStream();
            jsonObject = new JSONObject(getStringBuilder(wsJsonObjectWebServiceVO).toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return jsonObject;
    }

    public static String getObjectWebService(String strUrl) {
        strRetURL = null;
        try {
            wsJsonObjectWebServiceVO = new URL(strUrl).openStream();
            return strRetURL = getStringBuilder(wsJsonObjectWebServiceVO).toString();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println(String.format("getObjectWebService.strRetURL: [%s]", strRetURL));
        return strRetURL;
    }

    public static JSONObject getJsonObjectHttpUrlConnection(String strUrl, String token, String compl) {
        jsonObject = null;
        try {
            urlConnection = (HttpURLConnection) new URL(strUrl).openConnection();
            urlConnection.setConnectTimeout(30000);
            urlConnection.setReadTimeout(30000);
            if (strUrl.contains("cosmos")) {
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("X-Cosmos-Token", token);
            } else {
                urlConnection.setRequestProperty("Authorization", "Bearer " + token);
            }
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                wsJsonObjectWebServiceVO = urlConnection.getInputStream();
                jsonObject = new JSONObject(getStringBuilder(wsJsonObjectWebServiceVO).toString());
            } else {
                System.out.println("urlConnection.getResponseCode(1): [" + urlConnection.getResponseCode() + "]");
            }
        } catch (SocketTimeoutException ex) {
            try {
                urlConnection = (HttpURLConnection) new URL(strUrl + compl).openConnection();
                urlConnection.setConnectTimeout(40000);
                urlConnection.setReadTimeout(40000);
                if (strUrl.contains("cosmos")) {
                    urlConnection.setRequestProperty("Content-Type", "application/json");
                    urlConnection.setRequestProperty("X-Cosmos-Token", token);
                }
                urlConnection.connect();
                wsJsonObjectWebServiceVO = urlConnection.getInputStream();
                if (urlConnection.getResponseCode() == 200) {
                    jsonObject = new JSONObject(getStringBuilder(wsJsonObjectWebServiceVO));
                }
            } catch (Exception ex1) {
                if (!(ex1 instanceof TimeoutException))
                    ex1.printStackTrace();
            }
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return jsonObject;
    }

    private static StringBuilder getStringBuilder(Object retorno) {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader((InputStream) retorno, "UTF-8"));
//            System.out.printf("\n\n%s\n\n", bufferedReader.toString());
            stringBuilder = new StringBuilder();
            while ((linhaRetorno = bufferedReader.readLine()) != null) {
//                System.out.printf("ServiceBuscaWebService.getStringBuilder ===>> %s\n", linhaRetorno);
//                System.out.printf("%s\n", linhaRetorno);
                stringBuilder.append(linhaRetorno);
            }
        } catch (Exception ex) {
        }
        return stringBuilder;
    }

}
