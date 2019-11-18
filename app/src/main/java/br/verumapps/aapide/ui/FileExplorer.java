package br.verumapps.aapide.ui;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import br.verumapps.aapide.R;
import br.verumapps.utils.FileUtil;
import br.verumapps.utils.ThemeManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
public class FileExplorer extends AppCompatActivity
{
    // slendi: pls no modifi
    
    RecyclerView mRecyclerView;

	Toolbar toolbar;
    
    ThemeManager tm = new ThemeManager(FileExplorer.this);
    
	//Views
	
	private HashMap<String, Object> map_file_tmp = new HashMap<>();
	private String path = "";
	private double folder = 0;
	private double file = 0;
	private double i = 0;
	private double vibe_check = 0;
	private HashMap<String, Object> map_adapter_tmp = new HashMap<>();
	private String path_before = "";
	private String ext_temp = "";
	private String default_path = "";

	private ArrayList<HashMap<String, Object>> file_list = new ArrayList<>();
	private ArrayList<String> string_analysis = new ArrayList<>();

	private Button button1;
	private ListView list;
	private ObjectAnimator oa = new ObjectAnimator();
	
    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
		//Radu, I "fixed" the context thing - 
        String theme = PreferenceManager.getDefaultSharedPreferences(this).getString("theme", "Light");
      tm.setActivityTheme(theme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_explorer);
		/*
		toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.text_editor_menu);
        */
        initialize();
        initializeLogic();
    }

    private void initialize()
    {
        button1 = (Button) findViewById(R.id.button1);
		list = (ListView) findViewById(R.id.list);

		button1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					_loadFileList(path);
				}
			});

		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
					final int _position = _param3;
					path_before = path;
					if (FileUtil.isDirectory(string_analysis.get((int)(_position)))) {
						path = string_analysis.get((int)(_position));
						_loadFileList(path);
					}
					else {

					}
				}
			});

		list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
				@Override
				public boolean onItemLongClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
					final int _position = _param3;

					return true;
				}
			});
	}
	private void initializeLogic() {
		list.setAdapter(new ListAdapter(file_list));
		folder = 0;
		file = 1;
		default_path = getIntent().getStringExtra("path");
		path = default_path;
		_loadFileList(path);
	}

	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);

		switch (_requestCode) {

			default:
				break;
		}
	}

	@Override
	public void onBackPressed() {
		if (!default_path.contains(path)) {
			java.io.File f = new java.io.File(path);
			path = f.getParent();
			_loadFileList(path);
		}
		else {
			finish();
		}
	}
	private void _addItem (final String _file_name, final String _extension, final String _file_path) {
		vibe_check = 0;
		ext_temp = _extension;
		if (FileUtil.isDirectory(_file_path)) {
			vibe_check = folder;
			ext_temp = "";
		}
		else {

		}
		if (FileUtil.isFile(_file_path)) {
			vibe_check = file;
			ext_temp = _extension;
		}
		map_file_tmp.put("name", _file_name);
		map_file_tmp.put("extension", ext_temp);
		map_file_tmp.put("path", _file_path);
		map_file_tmp.put("vibe_check", String.valueOf((long)(vibe_check)));
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("json", new Gson().toJson(map_file_tmp));
			file_list.add(_item);
		}

	}


	private void _loadFileList (final String _path) {
		file_list.clear();
		string_analysis.clear();
		i = 0;
		FileUtil.listDir(_path, string_analysis);
		Collections.sort(string_analysis);
		for(int _repeat12 = 0; _repeat12 < (int)(string_analysis.size()); _repeat12++) {
			if (Uri.parse(string_analysis.get((int)(i))).getLastPathSegment().substring((int)(0), (int)(2)).contains(".".trim())) {
				_addItem(Uri.parse(string_analysis.get((int)(i))).getLastPathSegment(), "", string_analysis.get((int)(i)));
			}
			else {
				if (Uri.parse(string_analysis.get((int)(i))).getLastPathSegment().contains(".".trim())) {
					_addItem(Uri.parse(string_analysis.get((int)(i))).getLastPathSegment().substring((int)(0), (int)(Uri.parse(string_analysis.get((int)(i))).getLastPathSegment().lastIndexOf("."))).concat(""), Uri.parse(string_analysis.get((int)(i))).getLastPathSegment().substring((int)(Uri.parse(string_analysis.get((int)(i))).getLastPathSegment().lastIndexOf(".")), (int)(Uri.parse(string_analysis.get((int)(i))).getLastPathSegment().length())).concat(" file"), string_analysis.get((int)(i)));
				}
				else {
					_addItem(Uri.parse(string_analysis.get((int)(i))).getLastPathSegment(), "", string_analysis.get((int)(i)));
				}
			}
			i++;
		}
		((BaseAdapter)list.getAdapter()).notifyDataSetChanged();
		list.setScaleX((float)(1.1d));
		oa.setTarget(list);
		oa.setPropertyName("scaleX");
		oa.setFloatValues((float)(1.0d));
		oa.setDuration((int)(300));
		oa.start();
	}


	public class ListAdapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public ListAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}

		@Override
		public int getCount() {
			return _data.size();
		}

		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}

		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _view, ViewGroup _viewGroup) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _v = _view;
			if (_v == null) {
				_v = _inflater.inflate(R.layout.layout_file, null);
			}

			final LinearLayout body = (LinearLayout) _v.findViewById(R.id.body);
			final ImageView icon = (ImageView) _v.findViewById(R.id.icon);
			final LinearLayout text_body = (LinearLayout) _v.findViewById(R.id.text_body);
			final TextView text_file = (TextView) _v.findViewById(R.id.text_file);
			final TextView text_description = (TextView) _v.findViewById(R.id.text_description);

			map_adapter_tmp.clear();
			map_adapter_tmp = new Gson().fromJson(_data.get((int)_position).get("json").toString(), new TypeToken<HashMap<String, Object>>(){}.getType());
			text_file.setText(map_adapter_tmp.get("name").toString());
			text_description.setText(map_adapter_tmp.get("extension").toString());
			if (map_adapter_tmp.get("vibe_check").toString().equals("0")) {
				icon.setImageResource(R.drawable.folder_icon);
			}
			else {
				icon.setImageResource(R.drawable.file_icon);
				if (map_adapter_tmp.get("extension").toString().contains("gradle")) {
					icon.setImageResource(R.drawable.gradle_icon);
				}
				else {
					if (map_adapter_tmp.get("extension").toString().contains("java")) {
						icon.setImageResource(R.drawable.java_icon);
					}
					else {
						if (map_adapter_tmp.get("extension").toString().contains("xml")) {
							icon.setImageResource(R.drawable.xml_icon);
						}
						else {
							if (map_adapter_tmp.get("extension").toString().contains("apk")) {
								icon.setImageResource(R.drawable.apk_icon);
							}
							else {
								if (map_adapter_tmp.get("extension").toString().contains("jar")) {
									icon.setImageResource(R.drawable.xml_icon);
								}
							}
						}
					}
				}
			}
			if (!(text_description.getText().toString().length() > 0)) {

			}

			return _v;
		}
	
	
    }
    
    private void initializeCode() 
    {
        
        
    }
    /*
    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        getMenuInflater().inflate(R.menu.text_editor_menu, menu);
        return true;
    }*/
    
}
