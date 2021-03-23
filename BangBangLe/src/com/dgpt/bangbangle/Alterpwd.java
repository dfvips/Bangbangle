package com.dgpt.bangbangle;

import Web.WebServiceGet;
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

public class Alterpwd extends Activity {
	private Button btn_alterpwd;
	private ImageButton btn_back;
	private ProgressDialog dialog;
	private EditText et_orgpwd;
	private EditText et_newpwd;
	private String infoString;
	private String id;
	String info;
	String pwd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alterpwd);
		Intent intent = getIntent();
	    String []id_pwd=intent.getStringExtra("id").split("/");;
	    id=id_pwd[0];
	    pwd=id_pwd[1];
		btn_alterpwd=(Button)findViewById(R.id.btn_alterpwd);
		btn_alterpwd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Alter();
			}
		});
		et_orgpwd=(EditText)findViewById(R.id.et_orgpwd);
		et_newpwd=(EditText)findViewById(R.id.et_newpwd);
		btn_back=(ImageButton)findViewById(R.id.btn_back);
		btn_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	protected void Alter() {
		// TODO Auto-generated method stub
		Boolean check=et_orgpwd.getText().toString().equals(pwd);
		if(check==true){
		dialog = new ProgressDialog(Alterpwd.this);
        dialog.setTitle("������֤��");
        dialog.setMessage("���Ժ�");
        dialog.setCancelable(false);//���ÿ���ͨ��back��ȡ��
        dialog.show();
        //�������̣߳��ֱ����Get��Post��������
        new Thread(new MyThread()).start();
        }else{
        	Toast.makeText(Alterpwd.this, "ԭʼ���벻��ȷ", 0).show();
        }
	}
	 public class MyThread implements Runnable{
	        @Override
	        public void run() {
	            infoString = WebServiceGet.executeHttpGet(id,et_newpwd.getText().toString(),"AlterLet");//��ȡ���������ص�����
	 
	            //����UI��ʹ��runOnUiThread()����
	            showResponse(infoString);
	        }
	    }
	 private void showResponse(final String response){
	        runOnUiThread(new Runnable() {
	            //����UI
	            @Override
	            public void run() {
	                if(response.equals("true")){
	                    Toast.makeText(Alterpwd.this,"�޸ĳɹ�", Toast.LENGTH_SHORT).show();
	                    Intent intent = new Intent(Alterpwd.this,Login.class);
                        startActivity(intent);
                        finish();
	                }else {
	                    info=response;
	                    if(info.equals(null)){
	                    	Toast.makeText(Alterpwd.this,"�����쳣", Toast.LENGTH_SHORT).show();
	                    }else{
	                    	Toast.makeText(Alterpwd.this,"�޸�ʧ�ܣ�������", Toast.LENGTH_SHORT).show();
	                    }
	                }
	                
	                dialog.dismiss();
	            }
	        });
	    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alterpwd, menu);
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
