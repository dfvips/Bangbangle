package com.dgpt.bangbangle;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
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

public class EmsService extends Activity {
	private ImageButton btn_back;
	private TextView title;
	private TextView line01;
	private TextView line02;
	private View btn_line1;
	private View btn_line2;
	private EditText et_number;
	private EditText et_name;
	private EditText et_ems;
	private EditText et_phone;
	private EditText et_addr;
	private EditText et_tag;
	private EditText et_money;
	private View ems_msg;
	private View ems_list;
	private View ems_state;
	private Button btn_post;
	private Button btn_cancel;
	private TextView tv_list_num;
	private TextView tv_type;
	private TextView tv_state;
	public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); 
		setContentView(R.layout.activity_ems_service);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebtn);
        title=(TextView)findViewById(R.id.title);
        title.setText("快递服务");
        btn_back=(ImageButton)findViewById(R.id.imageButton1);
        btn_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

        ems_msg=(View)findViewById(R.id.ems_msg);
        ems_list=(View)findViewById(R.id.emslist);
        ems_state=(View)findViewById(R.id.ems_state);
        tv_list_num=(TextView)findViewById(R.id.tv_listnum);
        tv_type=(TextView)findViewById(R.id.tv_type);
        tv_state=(TextView)findViewById(R.id.tv_state);
    	et_number=(EditText)findViewById(R.id.et_number);
    	et_name=(EditText)findViewById(R.id.et_name);
    	et_ems=(EditText)findViewById(R.id.et_ems);
    	et_phone=(EditText)findViewById(R.id.et_phone);
    	et_addr=(EditText)findViewById(R.id.et_addr);
    	et_tag=(EditText)findViewById(R.id.et_tag);
    	et_money=(EditText)findViewById(R.id.et_money);
        
        
        line01=(TextView)findViewById(R.id.line01);
        line02=(TextView)findViewById(R.id.line02);
        line02.setBackgroundDrawable(getResources().getDrawable(R.drawable.dashlinehoop));
        btn_line1=(View)findViewById(R.id.btn_line1);
        btn_line1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 line02.setBackgroundDrawable(getResources().getDrawable(R.drawable.dashlinehoop));
				 line01.setBackgroundDrawable(getResources().getDrawable(R.drawable.myline));
				 ems_list.setVisibility(View.GONE);
				 ems_msg.setVisibility(View.VISIBLE);
			}
		});
        btn_line2=(View)findViewById(R.id.btn_line2);
        btn_line2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 line01.setBackgroundDrawable(getResources().getDrawable(R.drawable.dashlinehoop));
				 line02.setBackgroundDrawable(getResources().getDrawable(R.drawable.myline));
				 ems_msg.setVisibility(View.GONE);
				 ems_list.setVisibility(View.VISIBLE);
			}
		});
        btn_post=(Button)findViewById(R.id.btnpost);
        btn_cancel=(Button)findViewById(R.id.btn_cancel);
        btn_post.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Boolean checkphone =et_phone.getText().toString().matches(REGEX_MOBILE);
				Boolean checkname=et_name.getText().toString().trim().equals("");
				Boolean checkaddr=et_addr.getText().toString().trim().equals("");
				Boolean checkmoney=et_money.getText().toString().trim().equals("");
				Boolean checkaemsnum=et_number.getText().toString().trim().equals("");
				Boolean checkems=et_ems.getText().toString().trim().equals("");
				if(checkphone==true&&checkaddr==false&&checkname==false&&checkmoney==false&&checkaemsnum==false&&checkems==false){
				SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyyMMddhhmmss");       
				String  date=sDateFormat.format(new java.util.Date());    
				tv_list_num.setText(date);
				tv_type.setText(et_ems.getText().toString());
				tv_state.setText("等待中");
				ems_state.setVisibility(View.VISIBLE);
				Toast.makeText(EmsService.this, "发布成功", 0).show();
				et_number.setText("");
				et_name.setText("");
				et_ems.setText("");
				et_phone.setText("");
				et_addr.setText("");
				et_tag.setText("");
				et_money.setText("");
				}else if(checkphone==false){
					Toast.makeText(EmsService.this, "手机号格式不正确哦", 0).show();
				}else{
					Toast.makeText(EmsService.this, "请检查必填信息是否填写完整", 0).show();
				}
			}
		});
        btn_cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				et_number.setText("");
				et_name.setText("");
				et_ems.setText("");
				et_phone.setText("");
				et_addr.setText("");
				et_tag.setText("");
				et_money.setText("");
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ems, menu);
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
