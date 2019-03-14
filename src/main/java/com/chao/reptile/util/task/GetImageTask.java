package com.chao.reptile.util.task;

import com.chao.reptile.util.StreamUtil;

public class GetImageTask implements Runnable
{
    private String urlStr;

    public GetImageTask(String urlStr)
    {
        this.urlStr = urlStr;
    }

    public void run()
    {
        String pageContent = StreamUtil.getPageContentByUrl(urlStr);


        if (pageContent == null) return;

        while(pageContent.contains("<img src=\""))
        {

            int beginIndex = pageContent.indexOf("<img src=\"");

            pageContent = pageContent.substring(beginIndex + 10);

            int endIndex = pageContent.indexOf("\"");

            String urlName = pageContent.substring(0, endIndex);

            System.out.println("image address : " + urlName);

            String fileName = urlName.substring(urlName.lastIndexOf("/")+1);

            StreamUtil.readByUrlAndWriteToDisk(urlName,fileName);

            pageContent = pageContent.substring(pageContent.indexOf("\"/>"));
        }


    }
}
