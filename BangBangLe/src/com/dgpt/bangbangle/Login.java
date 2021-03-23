package com.dgpt.bangbangle;


import Web.WebServiceGet;
import android.app.Activity;
import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {
	private Button btn_login;
	private Button btn_findpwd;
	private Button btn_register;
	private ImageButton btn_back;
	private TextView username;
	private TextView password;
	private ProgressDialog dialog;
    private String infoString;
    public static String info;
    private ConnectivityManager cm ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login=(Button)findViewById(R.id.btn_login);
        username=(TextView)findViewById(R.id.et_username);
        password=(TextView)findViewById(R.id.et_pwd);
        btn_login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				login();
			}
		});
        btn_findpwd=(Button)findViewById(R.id.btn_findpwd);
		btn_findpwd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent Intent=new Intent(Login.this,Findpwd.class);
				startActivity(Intent);	
			}
		});
		btn_register=(Button)findViewById(R.id.btn_register);
		btn_register.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent Intent=new Intent(Login.this,Register.class);
					startActivity(Intent);
					finish();
				}
			});
		btn_back=(ImageButton)findViewById(R.id.btn_back);
		btn_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent Intent=new Intent(Login.this,Main.class);
				startActivity(Intent);
				finish();
			}
		});
	}
	 @Override
	    //��׿��д���ؼ��¼�
	    public boolean onKeyDown(int keyCode, KeyEvent event) {
	        if(keyCode==KeyEvent.KEYCODE_BACK){
				Intent Intent=new Intent(Login.this,Main.class);
				startActivity(Intent);
				finish();
	        }
	        return true;
	    }

	protected void login() {
		// TODO Auto-generated method stub
		dialog = new ProgressDialog(Login.this);
        dialog.setTitle("���ڵ�¼");
        dialog.setMessage("���Ժ�");
        dialog.setCancelable(false);//���ÿ���ͨ��back��ȡ��
        dialog.show();

        //�������̣߳��ֱ����Get��Post��������
        new Thread(new MyThread()).start();
	}
	 public class MyThread implements Runnable{
	        @Override
	        public void run() {
	            infoString = WebServiceGet.executeHttpGet(username.getText().toString(),password.getText().toString(),"LogLet");//��ȡ���������ص�����
	 
	            //����UI��ʹ��runOnUiThread()����
	            showResponse(infoString);
	        }
	    }
	 private void showResponse(final String response){
	        runOnUiThread(new Runnable() {
	            //����UI
	            @Override
	            public void run() {
	                if(response.equals("false")){
	                    Toast.makeText(Login.this,"�û������������", Toast.LENGTH_SHORT).show();
	                }else {
	                	Toast.makeText(Login.this,"��¼�ɹ�", Toast.LENGTH_SHORT).show();
	                    info=response;
	                    if(!(info.equals(null))){
	                    info+="/"+password.getText().toString();
	                    
                        Intent intent = new Intent(Login.this,Main.class);
                        
                        
                        startActivity(intent);
                        finish();
	                    }else{
	                    	Toast.makeText(Login.this,"�����쳣", Toast.LENGTH_SHORT).show();
	                    }
	                }
	                
	                dialog.dismiss();
	            }
	        });
	    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

public class BorderTextView extends EditText {
		
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint paint = new Paint();
		// ���߿���Ϊ��ɫ
		paint.setColor(android.graphics.Color.BLACK);
		paint.setStrokeWidth(1);
		// ��TextView��4����
		canvas.drawLine(0, 0, this.getWidth() - 1, 0, paint);
		canvas.drawLine(0, 0, 0, this.getHeight() - 1, paint);
		canvas.drawLine(this.getWidth() - 1, 0, this.getWidth() - 1,
				this.getHeight() - 1, paint);
		canvas.drawLine(0, this.getHeight() - 1, this.getWidth() - 1,
				this.getHeight() - 1, paint);
		setBackgroundColor(Color.TRANSPARENT);				
	}
 
	private Paint mPaint;
 
		public BorderTextView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			mPaint = new Paint();
			mPaint.setStyle(Paint.Style.STROKE);
			mPaint.setColor(Color.GREEN);
			
	      setBackgroundColor(Color.TRANSPARENT);//͸������
	 
		}
	 
		public BorderTextView(Context context, AttributeSet attrs) {
			super(context, attrs);
			// TODO Auto-generated constructor stub
			mPaint = new Paint();
			mPaint.setStyle(Paint.Style.STROKE);
			mPaint.setColor(Color.GRAY); 
			setBackgroundColor(Color.TRANSPARENT);//����͸��
		}
	}
}
