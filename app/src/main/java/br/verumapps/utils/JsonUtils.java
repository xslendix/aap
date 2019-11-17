package br.verumapps.utils;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils
{
    public static String getString (String Json, String key)
    {
        try
        {
            JSONObject js = new JSONObject(Json);
            return js.getString(key);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return "Data (Json) null or not set";
        }
    }

    public static double getDouble (String Json, String key)
    {
        try
        {
            JSONObject js = new JSONObject(Json);
            return js.getDouble(key);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    public static int getInt (String Json, String key)
    {
        try
        {
            JSONObject js = new JSONObject(Json);
            return js.getInt(key);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean getBoolean (String Json, String key)
    {
        try
        {
            JSONObject js = new JSONObject(Json);
            return js.getBoolean(key);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
