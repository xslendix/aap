package br.verumapps.aapide.ui;

import android.os.Bundle;
import android.view.Menu;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import br.verumapps.aapide.R;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TextEditorActivity extends AppCompatActivity
{
    // slendi: pls no modifi
    
    RecyclerView mRecyclerView;

	Toolbar toolbar;
    
    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texteditor);
		toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.text_editor_menu);
        
        initialize();
        initializeCode();
    }

    private void initialize()
    {
        
    }
    
    private void initializeCode() 
    {
        LinearLayoutManager layoutManager= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(layoutManager);
        
    }
    
    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        getMenuInflater().inflate(R.menu.text_editor_menu, menu);
        return true;
    }
    
}
