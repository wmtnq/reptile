package com.chao.reptile.MainTest;

import com.chao.reptile.util.ReptileUtil;


public class Run
{
    public static void main(String[] args)
    {
        /*
        //下载图片
        String pageUrl="https://www.661zh.com/pic/html28/{pageNumber}.html";

        ReptileUtil.getImage(pageUrl, 14520,2);
        */

        //下载视频
        String pageUrl = "https://www.206zh.com/vod/html2/{pageNumber}_down_0.html";

        ReptileUtil.getVideo(pageUrl,14614,1000);

    }
}
