package biz.codefuture.svgviewer;

import android.net.Uri;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HistoryActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_history);
		
		// populate listView with history data
		String[] values = new String[] { 
				"http://openclipart.org/people/stefg1971/distillation.svg", 
				"http://openclipart.org/people/stefg1971/distillation_column.svg", 
				"WindowsMobile", 
				"Blackberry", 
				"WebOS", 
				"Ubuntu", 
				"Windows7",
				"Max OS X",
				"Linux", 
				"OS/2",
				"WebOS", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
				"WebOS", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
				"WebOS", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
				"WebOS", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
		        };
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	        android.R.layout.simple_list_item_1, values);
	    setListAdapter(adapter);
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
