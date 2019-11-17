package br.verumapps.aapide.ui;

import android.Manifest;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import br.verumapps.aapide.R;
import br.verumapps.utils.FileUtil;
import br.verumapps.utils.PathUtil;
import com.google.android.material.snackbar.Snackbar;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
        initializeView();
        requestPermission();
		recycleView();
		handler.post(refreshers);
		
    }
	private SwipeRefreshLayout swipeRefreshLayout;
	private RecyclerView recyclerView;
	private RecyclerviewAdapter recyclerviewAdapter;
	private List<User> userList;
	
private void recycleView(){
	userList = new ArrayList<>();
	swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.SwipeRefreshLayout);
	swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_light);
	swipeRefreshLayout.setOnRefreshListener(refreshListener);

	recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
	recyclerView.setLayoutManager(new LinearLayoutManager(this));

	recyclerviewAdapter = new RecyclerviewAdapter();
	recyclerView.setAdapter(recyclerviewAdapter);
	
	
}
	private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {

		@Override
		public void onRefresh() {
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

        
    }
    String path = FileUtil.getExternalStorageDir() + pu.defaultPath;
    private void loadProjects ()
    {
		cl = findViewById(R.id.cl);
		
		try{
			userList = new ArrayList<>();
			User user;
			File dir = new File(path);
			if (!dir.exists() || dir.isFile()) return;

			File[] listFiles = dir.listFiles();
			if (listFiles == null || listFiles.length <= 0) return;


			for (File file : listFiles)
			{
				if(pu.ifIsAndroidProject(file.getAbsolutePath())){
                   
					user = new User(file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("/")+1),file.getAbsolutePath(),"");
					userList.add(user);
				}
			}
			recyclerviewAdapter.setUserList(userList);
		}catch(Exception e){
			Snackbar.make(cl, e.getMessage(), Snackbar.LENGTH_INDEFINITE).show();
		}
		
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
    }private Runnable refreshers = new Runnable() {

		@Override
		public void run() {
			swipeRefreshLayout.setRefreshing(true);
			loadProjects();
			swipeRefreshLayout.setRefreshing(false);
			
		 handler.postDelayed(refreshers, 100000);
		}
	};

    
}
