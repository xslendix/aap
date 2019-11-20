package br.verumapps.aapide.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import br.verumapps.aapide.R;
import br.verumapps.aapide.ui.TextEditorActivity;
import br.verumapps.utils.ThemeManager;
public class FileExplorer extends AppCompatActivity
{
    
    
    RecyclerView mRecyclerView;

	Toolbar toolbar;
    
    ThemeManager tm = new ThemeManager(FileExplorer.this);
    
	private String default_path;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_explorer);
		
		toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.file_explorer_menu);
        default_path = getIntent().getStringExtra("path");
        
    }
	public void deleteProject(){
		
	}
	public void backupProject(){
		
	}
	@Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        // Handle item selection
        switch (item.getItemId())
        {
            case R.id.delete_project:
                deleteProject();
            case R.id.backup:
                backupProject();
            default:
                return super.onOptionsItemSelected(item);
        }
      
		
    }
	
		

    
    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        getMenuInflater().inflate(R.menu.file_explorer_menu, menu);
        return true;
    }
    
}
