package br.verumapps.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.widget.Toast;

public class AndroidProjectUtil
{
	Context c;
	static String project_path;
	public AndroidProjectUtil(final Context c){
		this.c = c;
		
		project_path = (FileUtil.getExternalStorageDir() + new PathUtil().defaultPath);
	}
    public void create(String name, String package_name){
		
		String[] ary = package_name.split("[.]");
	
	    /* Deleting cache files */
		FileUtil.deleteFile(project_path + "/.cache");
		FileUtil.makeDir(project_path + "/.cache");
		/* Create basic android project directories */
		FileUtil.makeDir(project_path + name);
		FileUtil.makeDir(project_path + name + "/app");
		FileUtil.makeDir(project_path + name + "/app/src");
		FileUtil.makeDir(project_path + name + "/app/src/main");
		FileUtil.makeDir(project_path + name + "/app/src/main/java");
		
		
		try{
			/* Copy AndroidManifest Template to the right place */
			copyAssets("AndroidManifest.xml", (project_path + name+ "/app/src/main/"));
			/* Creating MainActivity */
			String p_r = project_path + name + "/app/src/main/java/";
		
			for (int i = 0; i < ary.length; i++) {
				p_r = p_r + ary[i] + "/";
				FileUtil.makeDir(p_r);
			}
		    copyAssets("MainActivity.java", p_r);
			/* res template */
			copyAssets("res.zip",FileUtil.getExternalStorageDir() + new PathUtil().defaultPath + "/.cache/");
			Decompress.unzip(new File(FileUtil.getExternalStorageDir() +  new PathUtil().defaultPath + "/.cache/res.zip"),new File(project_path + name + "/app/src/main/"));
		
			
			/* Replace com.packagename with package_name */
			String manifest_path = project_path + name+ "/app/src/main/" + "AndroidManifest.xml";
			FileUtil.writeFile(p_r+"MainActivity.java",FileUtil.readFile(p_r+"MainActivity.java").replace("com.packagename",package_name));
			FileUtil.writeFile(manifest_path,FileUtil.readFile(manifest_path).replace("com.packagename",package_name));
			
			}catch(Exception e){
				/* Log errors */
				FileUtil.writeFile(project_path + "/.cache" + "/error.log", e.toString());
		}
		
		}

	private void copyAssets(String file, String path)
	{
		AssetManager assetManager = c.getAssets();
		String[] files = null;
		InputStream in = null;
		OutputStream out = null;
		String filename = file;
		try
		{
            in = assetManager.open("app_template/" + filename);
            out = new FileOutputStream(path + filename);
            copyFile(in, out);
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
		}
		catch(IOException e)
		{
			/* Log errors */
			FileUtil.writeFile(project_path + "/.cache" + "/error4.log", e.toString());
			
		}      
	}

	private void copyFile(InputStream in, OutputStream out) throws IOException
	{
		byte[] buffer = new byte[1024];
		int read;
		while((read = in.read(buffer)) != -1)
		{
            out.write(buffer, 0, read);
		}
	}

	
	
} 
