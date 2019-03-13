package com.chao.reptile.util;


import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class reptileUtil
{
    public static void main(String[] args) throws UnsupportedEncodingException {
        String pageUrl="https://www.661zh.com/pic/html28/{pageNumber}.html";

        getImage(pageUrl, 14520,500);

    }


    public static void getImage(String pageUrl, int beginPage, int totalSize)
    {
        ExecutorService executorService = Executors.newFixedThreadPool(20);

        for(int i = 0; i < totalSize; i++)
        {
            String currentPage = beginPage-i +"";

            String currentPageUrl = pageUrl.replace("{pageNumber}", currentPage);

            GetImageTask getImageTask = new GetImageTask(currentPageUrl);

            executorService.execute(getImageTask);
        }

    }

}