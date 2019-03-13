package com.chao.reptile.util;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReptileUtil
{

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