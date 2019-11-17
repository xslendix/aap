package br.verumapps.utils;

import java.io.File;

public class PathUtil
{
    public String defaultPath = "/.AAProjects/";

    public  boolean ifIsAndroidProject (String path)
    {
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
        return path;
    }

    public  String getResFolder (String path)
    {
        return path;
    }
    public  String getJavaFolder (String path)
    {
        return path;
    }
}
