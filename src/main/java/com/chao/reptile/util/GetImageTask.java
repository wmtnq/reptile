package com.chao.reptile.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetImageTask implements Runnable
{
    private String urlStr;

    public GetImageTask(String urlStr)
    {
        this.urlStr = urlStr;
    }

    public void run()
    {
        URL url = null;
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpURLConnection urlConnection = null;

        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

            inputStream = urlConnection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));

            String pageContent;

            StringBuffer sb=new StringBuffer();

            while ((pageContent = reader.readLine()) != null)
            {
                sb.append(pageContent);
            }

            pageContent = sb.toString();

            while(pageContent.contains("<img src=\""))
            {

                int beginIndex = pageContent.indexOf("<img src=\"");

                pageContent = pageContent.substring(beginIndex + 10);

                int endIndex = pageContent.indexOf("\"/>");

                String urlName = pageContent.substring(0, endIndex);

                System.out.println("image address : " + urlName);

                byte[] btImg = StreamUtil.getImageFromNetByUrl(urlName);

                if (null != btImg && btImg.length > 0)
                {
                    String fileName = urlName.substring(urlName.lastIndexOf("/")+1);

                    StreamUtil.writeImageToDisk(btImg, fileName);

                    System.out.println("download success");

                } else {
                    System.out.println("download failed");
                }

                pageContent = pageContent.substring(pageContent.indexOf("\"/>"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
