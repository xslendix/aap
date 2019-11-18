package br.verumapps.utils;

import br.verumapps.aapide.R;
import android.content.Context;
import android.widget.Toast;

public class ThemeManager
{
    private Context ctx;

    public ThemeManager (Context context)
    {
        ctx = context;
    }

    public void setActivityTheme (String name)
    {
        // i will fix later
        int id;

        switch (name)
        {
            case "Strawberry":
                id = R.style.strawberry;
                break;
            case "Light":
                id = R.style.light;
                break;
            default:
                id = R.style.Theme;
                break;
        }

        // for som reason it is null
        ctx.setTheme(id);
    }

    public int getDialogTheme (String name)
    {
        switch (name)
        {
            case "Strawberry":
                return R.style.StrawberryDialog;
            case "Light":
                return R.style.LightDialog;
            default:
                return R.style.DarkDialog;
        }
    }
}
