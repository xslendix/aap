package br.verumapps.utils;

import br.verumapps.aapide.R;
import android.content.Context;

public class ThemeManager
{
    private Context ctx;
    
    public ThemeManager(Context context) {
        ctx = context;
    }
    
    public void setActivityTheme(String name) {
        int id;
        
        switch (name) {
            case "Strawberry": id = R.style.strawberry; break;
            case "Light": id = R.style.light; break;
            default: id = R.style.Theme; break;
        }
        
        ctx.setTheme(id);
    }
}
