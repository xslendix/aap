package br.verumapps.utils;

import java.io.File;

public class PathUtil
{
    public String defaultPath = "/.AAProjects/";

    public  boolean isAndroidProject (String path)
    {
		File p = new File(path + "/AndroidManifest.xml");
		File x = new File(path + "/res/");

		if (p.exists() && x.exists()) return true;


        if (true)
        {
            File dir = new File(path + "/app");

            if (!dir.exists() || dir.isFile()) return false;

            File[] listFiles = dir.listFiles();
            if (listFiles == null || listFiles.length <= 0) return false;

            for (File file : listFiles)
            {
                if (file.getAbsolutePath().contains("src"))
                {
                    return true;
                }
            }
            return false;
        }
        else
        {
            return false;
        }
    }
    public  String getAssetsFolder (String path)
    {
		File x = new File(path + "/assets/");

		if (x.exists()) return path + "/assets/";
        File dir = new File(path + "/app/src/main/assets/");
		if (dir.exists()) return path + "/app/src/main/assets/";
		return "false";
    }

    public  String getResFolder (String path)
    {

        File x = new File(path + "/res/");

		if (x.exists()) return path + "/res/";
        File dir = new File(path + "/app/src/main/res/");
		if (dir.exists()) return path + "/app/src/main/res/";
		return "false";
    }
    public  String getJavaFolder (String path)
    {
        File x = new File(path + "/java/");

		if (x.exists()) return path + "/java/";
        File dir = new File(path + "/app/src/main/java/");
		if (dir.exists()) return path + "/app/src/main/java/";
		return "false";
    }
}
