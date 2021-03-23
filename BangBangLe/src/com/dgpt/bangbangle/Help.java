package com.dgpt.bangbangle;

import Web.WebServicePost;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Help extends Activity {
	private TextView title;
	private ImageButton btn_back;
	private EditText problem;
	private EditText et_name;
	private EditText et_number;
	private Button btn_hand;
	private ProgressDialog dialog;
	public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); 
			setContentView(R.layout.activity_help);
	        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebtn);
	        title=(TextView)findViewById(R.id.title);
	        title.setText("投诉和建议");
	        problem=(EditText)findViewById(R.id.problem);
	        et_name=(EditText)findViewById(R.id.et_name);
	        et_number=(EditText)findViewById(R.id.et_phone);
	        btn_hand=(Button)findViewById(R.id.btn_hand);
	        btn_back=(ImageButton)findViewById(R.id.imageButton1);
	        btn_back.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					finish();
				}
			});
	        btn_hand.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Boolean checkphone =et_number.getText().toString().matches(REGEX_MOBILE);
					Boolean checkname=et_name.getText().toString().equals("");
					Boolean checkproblem=problem.getText().toString().equals("");
					if(checkphone==true&&checkname==false&&checkproblem==false){
					dialog = new ProgressDialog(Help.this);
	                dialog.setTitle("正在提交中");
	                dialog.setMessage("请稍后");
	                dialog.show();
	                new Thread(new HelpThread()).start();
					problem.setText("");
					et_name.setText("");
					et_number.setText("");
					}else if(checkphone==false){
						Toast.makeText(Help.this, "手机号格式错误",0).show();
					}else{
						Toast.makeText(Help.this, "您提交的信息不能为空",0).show();
					}
				}
			});
	        
	}
	 public class HelpThread implements Runnable{
	        @Override
	        public void run() {
	 
	            //获取服务器返回数据
	            String HelpRet = WebServicePost.executeHttpPost(problem.getText().toString(), et_name.getText().toString()+"/"+ et_number.getText().toString(),"HelpRet");;
	 
	            //更新UI，界面处理
	            showReq(HelpRet);
	        }
	    }
	    private void showReq(final String RegRet){
	        runOnUiThread(new Runnable() {
	            @Override
	            public void run() {
	                if(RegRet.equals("true")){
	                	Toast.makeText(Help.this,"提交成功", Toast.LENGTH_SHORT).show();
	                }else {
	                	Toast.makeText(Help.this,"提交失败", Toast.LENGTH_SHORT).show();
	                }
	                
	                dialog.dismiss();
	            }
	        });
	    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.help, menu);
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
