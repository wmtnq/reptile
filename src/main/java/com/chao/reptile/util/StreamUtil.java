package com.chao.reptile.util;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class StreamUtil
{
	 private String urlStr;

	 public StreamUtil(String url)
	 {
	 	this.urlStr = url;
	 }


	    
	public static byte[] getImageFromNetByUrl(String strUrl)
	{
		try
		{
			URL url = new URL(strUrl);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

			conn.setConnectTimeout(5 * 1000);

			InputStream inStream = conn.getInputStream();

			byte[] btImg = readInputStream(inStream);

			return btImg;

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return null;
	}


	private static byte[] readInputStream(InputStream inStream) throws IOException
	{
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while( (len=inStream.read(buffer)) != -1 )
		{
			outStream.write(buffer, 0, len);
		}
		inStream.close();

		return outStream.toByteArray();
	}


	public static void writeImageToDisk(byte[] img, String fileName)
	{
		try {
			String separator = System.getProperty("file.separator");
			String directory = System.getProperty("user.home") + separator + "Desktop" + separator + "img" + separator;

			File file = new File(directory + fileName);

			if (!file.getParentFile().exists())
			{
				file.getParentFile().mkdirs();
				System.out.println("create directory success");
			}
			FileOutputStream fops = new FileOutputStream(file);
			fops.write(img);
			fops.flush();
			fops.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
