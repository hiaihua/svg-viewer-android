package biz.codefuture.svgviewer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.net.Uri;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HistoryActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// populate listView with history data
        ArrayList<String> history_list = new ArrayList<String>();     
		
		try {
            File dir = getBaseContext().getFilesDir();
            String history_filename = "history.json";
            File file = new File(dir, history_filename);
            if(!file.exists()) {
            	file.createNewFile();
            	// TODO work out proper file creation with empty JSON array
            	String empty_history = "[{history: []}]";
            	FileOutputStream fos = openFileOutput(history_filename, Context.MODE_PRIVATE);
            	fos.write(empty_history.getBytes());
            	fos.close();
            }
		
            FileInputStream stream = new FileInputStream(file);
            String jString = null;
            try {
                FileChannel fc = stream.getChannel();
                MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
                /* Instead of using default, pass in a decoder. */
                jString = Charset.defaultCharset().decode(bb).toString();
              }
              finally {
                stream.close();
              }
            Log.v("history check", jString);
            JSONObject jObject = new JSONObject(jString); 
            JSONArray jsonArray = jObject.getJSONArray("history"); 
            if (jsonArray != null) { 
               for (int i=0;i<jsonArray.length();i++) { 
                history_list.add(jsonArray.get(i).toString()); 
            } 

            }
        } catch (Exception e) {e.printStackTrace();}
		
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	        android.R.layout.simple_list_item_1, history_list);
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
