package br.verumapps.utils;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

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
            return null;
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
    
    public static JSONArray getArray (String Json, String key)
    {
        try
        {
            JSONObject js = new JSONObject(Json);
            return js.getJSONArray(key);
        } catch (JSONException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public static JSONObject getJSONObject (String Json, String key)
    {
        try
        {
            JSONObject js = new JSONObject(Json);
            return js.getJSONObject(key);
        } catch (JSONException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
