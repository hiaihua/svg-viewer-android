package biz.codefuture.svgviewer;

import android.app.*;
import android.content.*;
import android.net.*;
import android.os.*;
import android.util.Log;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.OnItemLongClickListener;

import java.io.*;

public class HistoryActivity extends ListActivity {

	public HistoryManager history_manager;
	private boolean resumeHasRun = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// populate listView with history data
		File files_dir = getBaseContext().getFilesDir();
		history_manager = new HistoryManager(files_dir, (Context)this);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_list_item_1, history_manager.getList());
//			android.R.layout.simple_expandable_list_item_1, history_manager.getList());
	  	setListAdapter(adapter);
	  
	  	this.getListView().setLongClickable(true);
	  	this.getListView().setOnItemLongClickListener(new OnItemLongClickListener() {
	        public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id) {
	        //Do some
	        	Log.v("item long click", "allow item removal here");
	            return true;
	        }
	    });
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.v("onDestroy main app", "persistJSON here");
		history_manager.persistJSON();
	}
	
	@Override
	protected void onResume() {
	    super.onResume();
	    if (!resumeHasRun) {
	        resumeHasRun = true;
	        return;
	    }
		Log.v("onStart", "refresh list here - or does listadapter take care of that?");
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Uri URI_s = Uri.parse((String) getListView().getItemAtPosition(position));
		Intent detailIntent = new Intent(this, MainActivity.class);
		detailIntent.setData(URI_s);
		startActivity(detailIntent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.history, menu);
		return true;
	}

}
