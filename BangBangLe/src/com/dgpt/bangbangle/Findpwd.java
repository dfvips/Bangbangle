package com.dgpt.bangbangle;

import com.dgpt.bangbangle.Alterpwd.MyThread;

import Web.WebServiceGet;
import Web.WebServicePost;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Findpwd extends Activity {
	private Button btn_findpwd;
	private ImageButton btn_back;
	private EditText et_name;
	private EditText et_email;
	private EditText et_phone;
	private EditText et_pwd;
	private EditText et_repwd;
	private ProgressDialog dialog;
	private String infoString;
	public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_findpwd);
		et_name=(EditText)findViewById(R.id.et_name);
		et_email=(EditText)findViewById(R.id.et_email);
		et_phone=(EditText)findViewById(R.id.et_phone);
		et_pwd=(EditText)findViewById(R.id.et_pwd);
		et_repwd=(EditText)findViewById(R.id.et_repwd);

		btn_findpwd=(Button)findViewById(R.id.btn_findpwd);
		btn_findpwd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Findpwd();
			}
		});
		btn_back=(ImageButton)findViewById(R.id.btn_back);
		btn_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	protected void Findpwd() {
		// TODO Auto-generated method stub
		Boolean checkemail = et_email.getText().toString().matches(REGEX_EMAIL);
        Boolean checkphone =et_phone.getText().toString().matches(REGEX_MOBILE);
        Boolean checkname =et_name.getText().toString().equals("");
        Boolean checkpwd =et_pwd.getText().toString().equals(et_repwd.getText().toString());
		if(checkemail ==true&&checkphone==true&&checkname==false&&checkpwd==true){
		dialog = new ProgressDialog(Findpwd.this);
        dialog.setTitle("正在验证中");
        dialog.setMessage("请稍后");
        dialog.setCancelable(false);//设置可以通过back键取消
        dialog.show();
        //设置子线程，分别进行Get和Post传输数据
        new Thread(new MyThread()).start();
        }else if(checkemail==false){
        	Toast.makeText(Findpwd.this, "邮箱格式不正确", 0).show();
        }else if(checkphone==false){
        	Toast.makeText(Findpwd.this, "手机号格式不正确", 0).show();
        }else{
        	Toast.makeText(Findpwd.this, "请填写完整正确的信息", 0).show();
        }
	}
	 public class MyThread implements Runnable{
	        @Override
	        public void run() {
	            infoString = WebServicePost.executeHttpPost(et_name.getText().toString()+"/"+
	        et_email.getText().toString(),et_phone.getText().toString()+"/"+et_pwd.getText().toString(),"FindLet");//获取服务器返回的数据
	 
	            //更新UI，使用runOnUiThread()方法
	            showResponse(infoString);
	        }
	    }
	 private void showResponse(final String response){
	        runOnUiThread(new Runnable() {
	            //更新UI
	            @Override
	            public void run() {
	                if(response.equals("true")){
	                    Toast.makeText(Findpwd.this,"找回成功", Toast.LENGTH_SHORT).show();
	                    Intent intent = new Intent(Findpwd.this,Login.class);
                        startActivity(intent);
                        finish();
	                }else {
	                    String info=response;
	                    if(info.equals(null)){
	                    	Toast.makeText(Findpwd.this,"网络异常", Toast.LENGTH_SHORT).show();
	                    }else{
	                    	Toast.makeText(Findpwd.this,"找回失败，请填写正确信息后重试", Toast.LENGTH_SHORT).show();
	                    }
	                }
	                
	                dialog.dismiss();
	            }
	        });
	    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.findpwd, menu);
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
}
