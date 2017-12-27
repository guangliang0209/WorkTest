package wechat.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Created by fengguangliang on 2017/4/25.
 */
public class CommonUtil {

    /**
     * @param str: 参数
     * @author : fengguangliang
     * @description : 对http get请求参数中特殊字符进行转义
     * @date : 2017/4/25
     */
    public static String transcoding(String str) {
        if (isNull(str)) {
            return null;
        } else {
            String codeStr = str.replaceAll("%", "\\%25").replaceAll(" ", "\\%20").replaceAll("#", "\\%23").replaceAll("/", "\\%2F")
                    .replaceAll("\\?", "\\%3F").replaceAll("&", "\\%26").replaceAll("=", "\\%3D")
                    .replaceAll("\\+", "\\%2B").replaceAll("\\|", "\\%124");
            return codeStr;
        }
    }

    /**
     * @param target:
     * @author : fengguangliang
     * @description : 判断参数是否为null或者""
     * @date : 2017/4/25
     */
    public static boolean isNull(Object target) {
        return target == null || "".equals(target.toString().trim());
    }

    /**
     * @param url:   请求地址
     * @param param: 请求参数
     * @author : fengguangliang
     * @description : 发起https get请求并获取结果
     * @date : 2017/4/24
     */
    public static String doGet(String url, String param) throws Exception{
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = "";
            if (!CommonUtil.isNull(param)) {
                urlNameString = url + "?" + param;
            } else {
                urlNameString = url + "";
            }
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            throw new Exception(e);
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }


    /**
     * @param requestUrl 请求地址
     * @param param      提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     * @dscription:发起https post请求并获取结果
     */
    public static String doPost(String requestUrl, String param) throws Exception {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(requestUrl);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            throw new Exception(e);
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

}
