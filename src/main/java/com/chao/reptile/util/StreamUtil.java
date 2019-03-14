package com.chao.reptile.util;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class StreamUtil
{
	public static String getPageContentByUrl(String urlStr)
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

			return pageContent;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				if (inputStream != null) inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public static void readByUrlAndWriteToDisk(String strUrl,String fileName)
	{
		InputStream inStream = null;

		OutputStream outputStream = null;

		try
		{
			URL url = new URL(strUrl);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

			conn.setConnectTimeout(5 * 1000);

			inStream = new BufferedInputStream(conn.getInputStream());

			//写入磁盘
			String separator = System.getProperty("file.separator");
			String directory = System.getProperty("user.home") + separator + "Desktop" + separator + "download" + separator;

			File file = new File(directory + fileName);

			if (!file.getParentFile().exists())
			{
				file.getParentFile().mkdirs();
				System.out.println("create directory success");
			}

			outputStream = new BufferedOutputStream(new FileOutputStream(file));

			byte[] buffer = new byte[1024];

			int len = 0;

			while( (len=inStream.read(buffer)) != -1 )
			{
				outputStream.write(buffer, 0, len);
			}
			outputStream.flush();

			System.out.println("download success : " + fileName);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (inStream != null)
			{
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null)
			{
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
