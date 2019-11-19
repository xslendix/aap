package br.verumapps.utils;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AndroidProjectUtil
{
	Context c;
	public AndroidProjectUtil(final Context c){
		this.c = c;
	}
    public void create(String name, String package_name){
		String project_path;
		project_path = (FileUtil.getExternalStorageDir() + new PathUtil().defaultPath);
		FileUtil.makeDir(project_path + "/.cache");
		FileUtil.makeDir(project_path + name);
		FileUtil.makeDir(project_path + name + "/app");
		FileUtil.makeDir(project_path + name + "/app/src");
		FileUtil.makeDir(project_path + name + "/app/src/main");
		FileUtil.makeDir(project_path + name + "/app/src/main/java");
		String[] ary = package_name.split(".");
		try{
			copyFileFromAssets(c,"app_template/AndroidManifest.xml", (project_path + "/app/src/main"));
			String p_r = project_path + name + "/app/src/main/java/";
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < ary.length; i++) {
				p_r = p_r + ary[i] + "/";
				FileUtil.makeDir(p_r);
			}
		    copyFileFromAssets(c,"app_template/MainActivity.java", p_r);
			copyFileFromAssets(c,"app_template/res.zip",FileUtil.getExternalStorageDir() + "/.cache");
			Decompress.unzip(new File(FileUtil.getExternalStorageDir() + "/.cache/res.zip"),new File(project_path + name + "/app/src/main/rea"));
			
			}catch(Exception e){
			
		}
		}
	//ere is the clean version of the OP's answer.


	static public void copyFileFromAssets(Context context, String file, String dest) throws Exception 
	{
		InputStream in = null;
		OutputStream fout = null;
		int count = 0;

		try
		{
			in = context.getAssets().open(file);
			fout = new FileOutputStream(new File(dest));

			byte data[] = new byte[1024];
			while ((count = in.read(data, 0, 1024)) != -1)
			{
				fout.write(data, 0, count);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}   
		finally
		{
			if (in != null)
			{
				try {
					in.close();
				} catch (IOException e) 
				{
				}
			}
			if (fout != null)
			{
				try {
					fout.close();
				} catch (IOException e) {
				}
			}
		}
	}
	
}
