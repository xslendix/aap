package br.verumapps.aapide.ui;

import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import br.verumapps.aapide.R;
import br.verumapps.utils.GalaxUtils;
import java.util.function.ToDoubleFunction;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

public class UIEditor extends AppCompatActivity
{
    //(ygor) pls dont change anything here thx
    
    //Views
    private Toolbar toolbar;
    private LinearLayout background;
    private LinearLayout screenPreview;
    
    //Components and Classes
    private GalaxUtils gu;
    
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uieditor);
        
        initializeViews();
        initializeLogic();
    }
    
    private void initializeViews(){
        toolbar = findViewById(R.id.my_toolbar);
        background = findViewById(R.id.background);
        screenPreview = findViewById(R.id.screen_preview);
        gu = new GalaxUtils();
    }
    
    private void initializeLogic(){
        ViewTreeObserver vto = background.getViewTreeObserver(); 
        vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() { 
                @Override 
                public void onGlobalLayout() {
                    if (background.getMeasuredHeight() > 0){
                        background.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        viewToPhoneScreenSize(screenPreview, background);
                    }
                } 
            });
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.ui_editor_menu);
    }
    
    private void viewToPhoneScreenSize(LinearLayout view, LinearLayout parent){
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
        double scale = 0.9;
        double screenHeight = gu.getDisplayHeightPixels(this);
        double screenWidth = gu.getDisplayWidthPixels(this);
        double parentHeight = parent.getMeasuredHeight();
        double width = (parentHeight / screenHeight) * screenWidth;
        width = width * scale;
        double height = parentHeight * scale;
        params.height = (int)height;
        params.width = (int)width; 
        view.setLayoutParams(params);
    }
    
    // Menu
    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        getMenuInflater().inflate(R.menu.ui_editor_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        // Handle item selection
        switch (item.getItemId())
        {
            case R.id.action_undo:
                
                break;
            case R.id.action_redo:
                
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }
    
}
