package br.verumapps.aapide.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import br.verumapps.aapide.R;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

public class UIEditor extends AppCompatActivity
{
    //(ygor) pls dont change anything here thx
    
    private Toolbar toolbar;
    
    private CoordinatorLayout cl;
    
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uieditor);
        
        initializeViews();
    }
    
    private void initializeViews(){
        toolbar = findViewById(R.id.my_toolbar);
        cl = findViewById(R.id.cl);
    }
}
