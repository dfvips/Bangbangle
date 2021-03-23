package com.dgpt.bangbangle;

import Web.WebServicePost;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Register extends Activity {
	private Button btn_register;
	private ImageButton btn_back;
	private ProgressDialog dialog;
	private String infoString;
	String info;
	private EditText et_username;
	private EditText et_userpwd;
	private EditText et_userrepwd;
	private EditText et_useremail;
	private EditText et_userphone;
	public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{3,16}$";
	public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		et_username=(EditText)findViewById(R.id.et_username);
		et_userpwd=(EditText)findViewById(R.id.et_userpwd);
		et_userrepwd=(EditText)findViewById(R.id.et_userrepwd);
		et_useremail=(EditText)findViewById(R.id.et_useremail);
		et_userphone=(EditText)findViewById(R.id.et_userphone);
		
		btn_register=(Button)findViewById(R.id.btn_register);
		btn_register.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					  Boolean checkemail = et_useremail.getText().toString().matches(REGEX_EMAIL);
				        Boolean checkphone =et_userphone.getText().toString().matches(REGEX_MOBILE);
				        Boolean checkpwd=et_userpwd.getText().toString().matches(REGEX_PASSWORD);
				        Boolean checkrepwd=et_userrepwd.getText().toString().equals(et_userpwd.getText().toString());
				        if(checkemail==true&&checkphone==true&&checkpwd==true&&checkrepwd==true){
				        	dialog = new ProgressDialog(Register.this);
			                dialog.setTitle("正在验证中");
			                dialog.setMessage("请稍后");
			                dialog.show();
			                new Thread(new RegThread()).start();
				        }else if(checkemail==false){
				        	Toast.makeText(Register.this, "邮箱格式不正确哦", 0).show();
				        }else if(checkphone==false){
				        	Toast.makeText(Register.this, "手机号格式不正确哦", 0).show();
				        }else if(checkpwd==false){
				        	Toast.makeText(Register.this, "密码格式不符合要求", 0).show();
				        }else if(checkrepwd==false){
				        	Toast.makeText(Register.this, "两次输入密码不一致", 0).show();
				        }else{
				        	Toast.makeText(Register.this, "请检查您的信息都填写正确再注册", 0).show();
				        }
					
				}
			});
		btn_back=(ImageButton)findViewById(R.id.btn_back);
		btn_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent Intent=new Intent(Register.this,Login.class);
				startActivity(Intent);
				finish();
			}
		});
	}
	 @Override
	    //安卓重写返回键事件
	    public boolean onKeyDown(int keyCode, KeyEvent event) {
	        if(keyCode==KeyEvent.KEYCODE_BACK){
				Intent Intent=new Intent(Register.this,Login.class);
				startActivity(Intent);
				finish();
	        }
	        return true;
	    }

	 public class RegThread implements Runnable{
	        @Override
	        public void run() {
	 
	            //获取服务器返回数据
	            String RegRet = WebServicePost.executeHttpPost(et_username.getText().toString()+"/"+et_userpwd.getText().toString(),et_useremail.getText().toString()+"/"+et_userphone.getText().toString(),"RegLet");
	 
	            //更新UI，界面处理
	            showReq(RegRet);
	        }
	    }
	    private void showReq(final String RegRet){
	        runOnUiThread(new Runnable() {
	            @Override
	            public void run() {
	                if(RegRet.equals("true")){
	                	Toast.makeText(Register.this,"注册成功", Toast.LENGTH_SHORT).show();
	                    info=RegRet;
	                    if(!(info.equals(null))){
                        Intent intent = new Intent(Register.this,Login.class);
                        startActivity(intent);
                        finish();
	                    }else{
	                    	Toast.makeText(Register.this,"网络异常", Toast.LENGTH_SHORT).show();
	                    }
	                    
	                }else {
	                	Toast.makeText(Register.this,"注册失败", Toast.LENGTH_SHORT).show();
	                }
	                
	                dialog.dismiss();
	            }
	        });
	    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
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
