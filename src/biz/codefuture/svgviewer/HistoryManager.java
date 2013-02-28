package biz.codefuture.svgviewer;
import android.app.*;
import android.content.*;
import android.net.*;
import android.util.*;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.util.*;
import org.json.*;

public class HistoryManager
{
	File files_dir;
	Context ctx;
	ArrayList<String> history_list = new ArrayList<String>();   
  String file_name = "history.json";
  String file_name_path;

	public HistoryManager(File files_dir, Context ctx) {
		 this.files_dir = files_dir;
		 this.ctx = ctx;

		try {
			File dir = this.files_dir;
			File file = new File(dir, file_name);
			if(!file.exists()) {
				createHistoryJSON(file);
			}

			FileInputStream stream = new FileInputStream(file);
			Log.v("history file", file.toString());
			String jString = null;
			try {
				FileChannel fc = stream.getChannel();
				MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
				/* Instead of using default, pass in a decoder. */
				jString = Charset.defaultCharset().decode(bb).toString();
			} finally {
				stream.close();
			}
			Log.v("history check", jString);
			JSONObject jObject = new JSONObject(jString); 
			JSONArray jsonArray = jObject.getJSONArray("history"); 
			if (jsonArray != null) { 
				for (int i=0;i<jsonArray.length();i++) { 
					this.history_list.add(jsonArray.get(i).toString()); 
				} 
			}
		} catch (Exception e) {e.printStackTrace();}
		 // Log.v("history manager context", context.toString());
	}

	private void createHistoryJSON(File file) {
		try {
		file.createNewFile();
		// TODO work out proper file creation with empty JSON array
		String empty_history = "{history: [" + "'file:///storage/sdcard0/svg/it-crowd.svg'" +
			"]}";
		Log.v("history init", empty_history);
		Log.v("history file.name", file.getName());
		FileOutputStream fos = this.ctx.openFileOutput(file_name_path, Context.MODE_PRIVATE);
		fos.write(empty_history.getBytes());
		fos.close();
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public ArrayList<String> getList() {
		return this.history_list;
	}
	
	public boolean addSvg(Uri path) {
		boolean added = true;
		
		this.history_list.add(path.toString());
		persistJSON();
    
		return added;
	}
	
  private void persistJSON() {
    
    // convert this.history_list to JSON and write to history.json
    try {
      // TODO work out proper file creation with empty JSON array
      JSONArray jsArray = new JSONArray(this.history_list);
      JSONObject jsObj = new JSONObject();
      jsObj.put("history", jsArray);
      String history_json = jsObj.toString();
       
      Log.v("persisJSON", history_json);
      FileOutputStream fos = this.ctx.openFileOutput(file_name_path, Context.MODE_PRIVATE);
      fos.write(history_json.getBytes());
      fos.close();
    } catch (Exception e) {e.printStackTrace();}
    
    }
}
