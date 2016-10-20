package com.example.adilclient;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private Button getData;
	
	private EditText name,author;
		
	private Song songBinder;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 getData=(Button)findViewById(R.id.getData);
	    name=(EditText)findViewById(R.id.name);
	      author=(EditText)findViewById(R.id.author);
	        final Intent intent=new Intent();
	        intent.setAction("com.example.adilclient.AIDLServer");
	        bindService(intent, conn, Service.BIND_AUTO_CREATE);
	        getData.setOnClickListener(new OnClickListener() {			
				@Override
				public void onClick(View v) {
					try{
						name.setText(songBinder.getName());
						author.setText(songBinder.getAuthor());
					}catch(Exception ex){
						ex.printStackTrace();
					}
					
				}
	    });
     
	}
	private ServiceConnection conn=new ServiceConnection() {		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			songBinder=null;
			
		}
		@Override
		public void onServiceDisconnected(ComponentName name) {
			//songBinder=Song.Stub.asInterface(service);
	 }
	};
	protected void onDestroy() {
		super.onDestroy();
		unbindService(conn);
	};
    

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
