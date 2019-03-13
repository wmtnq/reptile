package com.chao.reptile.MainTest;

import com.chao.reptile.util.ReptileUtil;


public class Run
{
    public static void main(String[] args)
    {
        String pageUrl="https://www.661zh.com/pic/html28/{pageNumber}.html";

        ReptileUtil.getImage(pageUrl, 14520,500);

    }
}
