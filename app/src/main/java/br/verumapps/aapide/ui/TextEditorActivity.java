package br.verumapps.aapide.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import br.verumapps.aapide.R;

public class TextEditorActivity extends AppCompatActivity
{
    // slendi: pls no modifi
    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texteditor);

        //initializeView();
        //initializeCode();
    }

    //LinearLayoutManager layoutManager= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
    //mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    //mRecyclerView.setLayoutManager(layoutManager);

}
