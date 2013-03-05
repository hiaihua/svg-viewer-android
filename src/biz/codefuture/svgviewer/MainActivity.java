package biz.codefuture.svgviewer;

import java.io.File;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
//import android.util.Log;
import biz.codefuture.svgviewer.HistoryManager;

public class MainActivity extends Activity {
	
//	private static final String TAG = "svg-viewer-main-activity";
	public HistoryManager history_manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		File files_dir = getBaseContext().getFilesDir();
		final HistoryManager history_manager = new HistoryManager(files_dir, (Context)this);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		Intent intent = getIntent();
	    Uri data = intent.getData();
	    //Log.v(TAG, "data=" + data);
	    


	    if (data != null)  {	
	    	// TODO should we handle file vs web uris here? // data.toString().indexOf("file") > -1) {	
	    	final String data_s  = data.toString();
	    	WebView webview = (WebView) findViewById(R.id.webView1);
	    	webview.loadUrl(data_s);
	    	webview.getSettings().setBuiltInZoomControls(true);
	    	webview.getSettings().setDisplayZoomControls(false);
	    	webview.getSettings().setUseWideViewPort(true);
	    	//webview.zoomOut();
		    webview.setWebViewClient(new WebViewClient() {
	    	   public void onPageFinished(WebView view, String url) {
	    	        // do your stuff here
					history_manager.addSvg(Uri.parse(data_s));
	    	   }
	    	});
				
	    }
	}


	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	            // go to previous screen when app icon in action bar is clicked
	            Intent intent = new Intent(this, HistoryActivity.class);
	            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            startActivity(intent);
	            return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
}
