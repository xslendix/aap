package br.verumapps.aapide.ui;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import br.verumapps.aapide.R;
import br.verumapps.utils.FileUtil;
import br.verumapps.utils.PathUtil;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
{

    FileUtil fu = new FileUtil();
    PathUtil pu = new PathUtil();

    private CoordinatorLayout cl;

    private Toolbar toolbar;

    private ListView lv1;

    private ArrayList<HashMap<String, Object>> projects = new ArrayList<>();
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
        initializeCode();
        vxlosh();
    }

    private void vxlosh ()
    {
        cl = findViewById(R.id.cl);
        Snackbar.make(cl, "Test", Snackbar.LENGTH_INDEFINITE).show();
    }

    private void initializeView ()
    {
        lv1 = findViewById(R.id.listview1);

        dialog = new AlertDialog.Builder(this);

        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.menu);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick (AdapterView<?> param1, View param2, int index, long param4)
                {
                    Intent i = new Intent();
                    i.setClass(getBaseContext(), TextEditorActivity.class);
                    startActivity(i);
                }
            });

        lv1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick (AdapterView<?> param1, View param2, int index, long param4)
                {
                    dialog.setMessage(getString(R.string.project_options_desc) + " \"" + (projects.get(index).get("title").toString()) + "\".");
                    dialog.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick (DialogInterface dialog, int which)
                            {

                            }
                        });

                    dialog.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick (DialogInterface dialog, int which)
                            {

                            }
                        });

                    dialog.setNeutralButton("-_-", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick (DialogInterface dialog, int which)
                            {

                            }
                        });

                    dialog.create().show();

                    return true;
                }
            });
    }

    private void loadProjects ()
    {
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("title", "Bruh Moment");
        tmp.put("desc", "Bruh Moment is best app");
        tmp.put("pkgname", "com.bruh.moment");
        tmp.put("projectname", "BruhMoment");

        projects.add(tmp);
        lv1.setAdapter(new LV1Adapter(projects));
    }

    private void initializeCode ()
    {
        /*
         HashMap<String, Object> tmp = new HashMap<>();
         tmp.put("title", "Bruh Moment");
         tmp.put("desc", "Bruh Moment is best app");
         tmp.put("pkgname", "com.bruh.moment");
         tmp.put("projectname", "BruhMoment");

         projects.add(tmp);
         */
        lv1.setAdapter(new LV1Adapter(projects));
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
            || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
        {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        }
        else
        {
            //initializeLogic();
            loadProjects();
        }
    }

    @Override
    public void onRequestPermissionsResult (int requestCode, String[] permissions, int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000)
        {
            //initializeLogic();
            loadProjects();
        }
    }

    private class LV1Adapter extends BaseAdapter
    {
        ArrayList<HashMap<String, Object>> data;

        public LV1Adapter (ArrayList<HashMap<String, Object>> arr)
        {
            data = arr;
        }

        @Override
        public int getCount ()
        {
            return data.size();
        }

        @Override
        public HashMap<String, Object> getItem (int index)
        {
            return data.get(index);
        }

        @Override
        public long getItemId (int index)
        {
            return index;
        }

        @Override
        public View getView (int index, View view, ViewGroup vg)
        {
            LayoutInflater lf = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View v = view;
            if (v == null)
            {
                v = lf.inflate(R.layout.layout_project, null);
            }

            final ImageView icon = v.findViewById(R.id.icon);
            final TextView title = v.findViewById(R.id.title);
            final TextView desc = v.findViewById(R.id.description);

            title.setText(data.get(index).get("title").toString());
            desc.setText(data.get(index).get("desc").toString());
            return v;
        }
    }
}
