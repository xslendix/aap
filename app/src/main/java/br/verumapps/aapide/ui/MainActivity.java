package br.verumapps.aapide.ui;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import br.verumapps.aapide.R;
import br.verumapps.aapide.manager.User;
import br.verumapps.aapide.ui.adapters.RecyclerViewAdapter;
import br.verumapps.utils.FileUtil;
import br.verumapps.utils.PathUtil;
import br.verumapps.utils.ThemeManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import android.content.DialogInterface;

public class MainActivity extends AppCompatActivity
{

	/*

     OnClick RecycleView is in RecycleviewAdapter

     */
    FileUtil fu = new FileUtil();
    PathUtil pu = new PathUtil();
    Handler handler = new Handler();

    private CoordinatorLayout cl;

    private Toolbar toolbar;

    private AlertDialog.Builder dialog;

    //SharedPreferences sharedPref = getSharedPreferences("synced", Context.MODE_PRIVATE);
    ThemeManager tm = new ThemeManager(MainActivity.this);

    // Menu
    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        // Handle item selection
        switch (item.getItemId())
        {
            case R.id.openeditor:
                Intent i = new Intent();
                i.setClass(getBaseContext(), TextEditorActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
		//Fixed context thing 

        String theme = PreferenceManager.getDefaultSharedPreferences(this).getString("theme", "Light");
		tm.setActivityTheme(theme);

		//tm.setActivityTheme(theme);
        //setTheme(R.style.strawberry);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeView();
        requestPermission();
		recycleView();
		handler.post(refreshers);
    }

	private SwipeRefreshLayout swipeRefreshLayout;
	private RecyclerView recyclerView;
	private RecyclerViewAdapter recyclerviewAdapter;
	private List<User> userList;

    private void recycleView ()
    {

        userList = new ArrayList<>();
        swipeRefreshLayout = findViewById(R.id.SwipeRefreshLayout);
        swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_light);
        swipeRefreshLayout.setOnRefreshListener(refreshListener);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerviewAdapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(recyclerviewAdapter);
		/*
         recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
         DividerItemDecoration.VERTICAL));
         */
    }
	private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener()
    {
		@Override
		public void onRefresh ()
        {
			swipeRefreshLayout.setRefreshing(true);
			loadProjects();
			swipeRefreshLayout.setRefreshing(false);
		}
	};


    private void initializeView ()
    {
        dialog = new AlertDialog.Builder(this);

        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.menu);

		FloatingActionButton myFab = findViewById(R.id.fab); 
		myFab.setOnClickListener(new View.OnClickListener() { 
				public void onClick (View v)
                { 
					createProjectDialog(); 
				} 
			});

    }
    private void createProjectDialog ()
    {
	    final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
	    View dialogView = inflater.inflate(R.layout.layout_create_project, null);
        
        dialogBuilder.setTitle(getString(R.string.create_project_dialog_title));
        dialogBuilder.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.create_project_dialog_okbutton), new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick (DialogInterface p1, int p2)
                {
                    
                }
        });
        
        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }
    
    String path = FileUtil.getExternalStorageDir() + pu.defaultPath;

    private void loadProjects ()
    {
		cl = findViewById(R.id.cl);

		try
        {
			userList = new ArrayList<>();
			User user;
			File dir = new File(path);
			if (!dir.exists() || dir.isFile()) return;

			File[] listFiles = dir.listFiles();
			if (listFiles == null || listFiles.length <= 0) return;


			for (File file : listFiles)
			{
				if (pu.isAndroidProject(file.getAbsolutePath()))
                {
					user = new User(file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("/") + 1), file.getAbsolutePath(), "");
					userList.add(user);
				}
			}
			recyclerviewAdapter.setUserList(userList);
		}
        catch (Exception e)
        {
			Snackbar.make(cl, e.getMessage(), Snackbar.LENGTH_INDEFINITE).show();
		}
		runLayoutAnimation(recyclerView);

    }

    private void requestPermission ()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
            || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
        {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        }

    }

    @Override
    public void onRequestPermissionsResult (int requestCode, String[] permissions, int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000)
        {
        }
    }

    private Runnable refreshers = new Runnable() {

		@Override
		public void run ()
        {
			swipeRefreshLayout.setRefreshing(true);
			loadProjects();
			swipeRefreshLayout.setRefreshing(false);

            handler.postDelayed(refreshers, 100000);
		}
	};

    private int getTheme (String name)
    {
        switch (name)
        {
            case "Strawberry": return R.style.strawberry;
            case "Light": return R.style.light;
            default: return R.style.Theme;
        }
    }
	private void runLayoutAnimation (final RecyclerView recyclerView)
    {
		final Context context = recyclerView.getContext();
		final LayoutAnimationController controller =
            AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);

		recyclerView.setLayoutAnimation(controller);
		recyclerView.getAdapter().notifyDataSetChanged();
		recyclerView.scheduleLayoutAnimation();
	}
}
