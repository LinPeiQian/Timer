package com.ImagesChange;

import com.ImagesChange.ChangeImageIn.TimersCallback;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ImagesChangeActivity extends Activity implements TimersCallback{
	private int i =0;
	private int[] tupian = {
			R.drawable.t1,
			R.drawable.t2,
			R.drawable.t3,
			};
	private EditText et ;
	private Button zhixing ;
	private Button timeout;
	private Button stop;
	private ImageView imageView;
	private String num ;
	private View view;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        et = (EditText)findViewById(R.id.et);
        zhixing = (Button)findViewById(R.id.zhixing);
        timeout = (Button)findViewById(R.id.timeout);
        stop = (Button)findViewById(R.id.stop);
        imageView = (ImageView)findViewById(R.id.image);
        final ChangeImageIn changeImageIn = new ChangeImageIn();
        zhixing.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!et.getText().toString().equals("") ){
				if(view == stop){
				ChangeImageIn changeImageIn = new ChangeImageIn();
				num = et.getText().toString();
				changeImageIn.ChangeImage(Long.valueOf(num), ImagesChangeActivity.this);
				}else{
					num = et.getText().toString();
					changeImageIn.ChangeImage(Long.valueOf(num), ImagesChangeActivity.this);
				}
				view = v;
			}
			}
		});
        
        timeout.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(view != null && view != stop){
				if(timeout.getText().equals("ÔİÍ£")){
					timeout.setText("¿ªÊ¼");
					try {
						changeImageIn.timeout();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					timeout.setText("ÔİÍ£");
					changeImageIn.ChangeImage(Long.valueOf(num), ImagesChangeActivity.this);
				}
				//ChangeImageIn changeImageIn = new ChangeImageIn();
				view = v;
				}
				
			}
		});
        
        stop.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//ChangeImageIn changeImageIn = new ChangeImageIn();
				changeImageIn.stop();
				imageView.setImageDrawable(null);
				view = v;
			}
		});
    }
    final Handler handler = new Handler( ) {
		public void handleMessage(Message msg) {
		switch (msg.what) {
		case 1:
			imageView.setImageResource(tupian[i]);
			i ++;
			if(i >= 3){
				i = 0;
			}
		break;
		}
		
		};
    };
	public void updateImage() {
		// TODO Auto-generated method stub
		Message message = new Message( );
		message.what = 1;
		handler.sendMessage(message);
	}
}