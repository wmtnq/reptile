package com.chao.reptile.util.task;

import com.chao.reptile.util.StreamUtil;

public class GetVideoTask implements Runnable
{
    private String urlStr;

    public GetVideoTask(String urlStr)
    {
        this.urlStr = urlStr;
    }

    public void run()
    {
        String pageContent = StreamUtil.getPageContentByUrl(urlStr);

        if (pageContent == null) return;

        if (pageContent.contains("下载地址二：<a href=\"")) {

            int beginIndex = pageContent.indexOf("下载地址二：<a href=\"");

            pageContent = pageContent.substring(beginIndex + 15);

            int endIndex = pageContent.indexOf("\"");

            String urlName = pageContent.substring(0, endIndex);

            String fileName = urlName.substring(urlName.lastIndexOf("/") + 1);

            System.out.println("image address : " + urlName);

            StreamUtil.readByUrlAndWriteToDisk(urlName, fileName);
        }
    }
}
