package Web;

import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
 
/**
 * ʹ��get������ȡHttp����������
 */
 
public class WebServiceGet {
 
    public static String executeHttpGet(String username,String password,String address){
        HttpURLConnection connection = null;
        InputStream in = null;
 
        try{
            String Url = "http://132.232.228.178:8080/HelloWeb/" + address;
            String path = Url + "?username=" + username + "&password=" + password;
            try {
                URL url = new URL(path);
                connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(10000);//�������ӳ�ʱ
                connection.setReadTimeout(8000);//�������ݳ�ʱ
 
                in = connection.getInputStream();
                return parseInfo(in);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //�����˳�ʱ�����ӹرձ���
            if(connection != null){
                connection.disconnect();
            }
            if(in != null){
                try{
                    in.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
 
    //�õ��ֽ������������ֽ�������ת��ΪString����
    public static String parseInfo(InputStream inputStream){
        BufferedReader reader = null;
        String line = "";
        StringBuilder response = new StringBuilder();
 
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            while((line = reader.readLine()) != null){
                response.append(line);
            }
            return response.toString();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(reader != null){
                try{
                    reader.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
