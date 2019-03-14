package com.chao.reptile.util;


import com.chao.reptile.util.task.GetImageTask;
import com.chao.reptile.util.task.GetVideoTask;

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

    public static void getVideo(String pageUrl, int beginPage, int totalSize)
    {
        ExecutorService executorService = Executors.newFixedThreadPool(15);

        for(int i = 0; i < totalSize; i++)
        {
            String currentPage = beginPage - i + "";

            String currentPageUrl = pageUrl.replace("{pageNumber}", currentPage);

            GetVideoTask getImageTask = new GetVideoTask(currentPageUrl);

            executorService.execute(getImageTask);
        }
    }

}