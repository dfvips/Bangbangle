package com.dgpt.bangbangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class Main extends Activity implements CartAdapter.RefreshPriceInterface ,View.OnClickListener {
	private ImageButton btn_back;
	private TextView title;
	public static TextView btn_login;
	private ImageButton btn_buyfood;
	private WebView webview;
	private View mErrorView;
	private Button btn_reflash;
	private Boolean loadError=false;
	private ImageButton AboutMySelf;
	private ImageButton home;
	private ImageButton buy;
	private ImageButton shop;
	private ImageButton run;
	private ImageButton repair;
	private ImageButton loseandfound;
	private ImageButton peoplehelp;
	private ImageButton wait;
	private View layouthome;
	private View aboutMe;
	public static View shopView;
	private TextView tv_home;
	private TextView tv_shop;
	private TextView tv_buy;
	private TextView tv_run;
	private TextView tv_my;
	private ImageButton help;
	private ImageButton emsservice;
	public static String userrealname;
	private String username;
	private String useremail;
	private String userphone;
	private TextView tv_username;
	private TextView tv_useremail;
	private TextView tv_userphone;
	private TextView btn_logout;
	private TextView btn_repwd;
	private View unachieve;
	String pwd;
	String id_and_pwd;
	private ListView listView;
    private CheckBox cb_check_all;
    private TextView tv_total_price;
    private TextView tv_delete;
    private TextView tv_go_to_pay;
    private CartAdapter adapter;
    private double totalPrice = 0.00;
    private int totalCount = 0;
    public static Main m;
    
    public static  List<HashMap<String,String>> goodsList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		goodsList=new ArrayList<>();
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); 
        setContentView(R.layout.activity_main);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebtn);  
        title=(TextView)findViewById(R.id.title);
        title.setText("东职帮帮乐");
        
        
        btn_repwd=(TextView)findViewById(R.id.btn_repwd);
        btn_login=(TextView)findViewById(R.id.btn_login);
        	
        if(Login.info!=null){
        	String []arr=Login.info.split("/");
        	btn_login.setText(arr[1]+",欢迎您");
        	username=arr[1];
        	useremail=arr[2];
        	userphone=arr[3];
        	pwd=arr[4];
        	id_and_pwd=arr[0]+"/"+arr[4];
        }else{
        	btn_login.setText("登录");
        }
        tv_username=(TextView)findViewById(R.id.tv_username);
        tv_useremail=(TextView)findViewById(R.id.tv_user_email);
        tv_userphone=(TextView)findViewById(R.id.tv_userphone);
        tv_username.setText(username);
        tv_useremail.setText(useremail);
        tv_userphone.setText(userphone);
        unachieve=findViewById(R.id.unachieve);
        btn_repwd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 Intent intent = new Intent(Main.this,Alterpwd.class);
                 intent.putExtra("id",id_and_pwd);
                 startActivity(intent);
			}
		});
        
        btn_back=(ImageButton)findViewById(R.id.imageButton1);
        btn_back.setImageDrawable(getResources().getDrawable(R.drawable.nocolor));
        init();
        layouthome=findViewById(R.id.layouthome);
        aboutMe=findViewById(R.id.aboutMe);
        tv_home=(TextView)findViewById(R.id.tv_home);
        tv_shop=(TextView)findViewById(R.id.tv_shop);
        tv_run=(TextView)findViewById(R.id.tv_run);
        tv_buy=(TextView)findViewById(R.id.tv_buy);
        tv_my=(TextView)findViewById(R.id.tv_my);
    	repair=(ImageButton)findViewById(R.id.repair);
    	loseandfound=(ImageButton)findViewById(R.id.loseandfound);
        peoplehelp=(ImageButton)findViewById(R.id.peoplehelp);
        wait=(ImageButton)findViewById(R.id.wait);
        repair=btnlistener(repair);
        loseandfound=btnlistener(loseandfound);
        peoplehelp=btnlistener(peoplehelp);
        wait.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(Main.this, "敬请期待", 0).show();
			}
		});
        

        btn_login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(btn_login.getText().equals("登录")){
				Intent Intent=new Intent(Main.this,Login.class);
				startActivity(Intent);
				finish();
				}
			}
		});
        btn_logout=(TextView)findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				aboutMe.setVisibility(View.GONE);
				title.setText("东职帮帮乐");
				initButton();
				home.setImageDrawable(getResources().getDrawable(R.drawable.home_new));
				tv_home.setTextColor(Color.rgb(237, 125, 49));
				layouthome.setVisibility(View.VISIBLE);
				btn_login.setText("登录");
				Login.info=null;
			}
		});
        btn_buyfood=(ImageButton)findViewById(R.id.btn_buyfood);
        btn_buyfood.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent Intent=new Intent(Main.this,FoodShow.class);
				startActivity(Intent);	
			}
		});
        btn_reflash=(Button)findViewById(R.id.btn_reflash);
        btn_reflash.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				loadError=false;
				init();	
			}
		});
        shopView=findViewById(R.id.shopactivity);
        AboutMySelf=(ImageButton)findViewById(R.id.my);
        AboutMySelf.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!(btn_login.getText().equals("登录"))){
				shopView.setVisibility(View.GONE);
				layouthome.setVisibility(View.GONE);
				title.setText("个人中心");
				initButton();
				AboutMySelf.setImageDrawable(getResources().getDrawable(R.drawable.my_new));
				tv_my.setTextColor(Color.rgb(237, 125, 49));    
				aboutMe.setVisibility(View.VISIBLE);
				}else{
					Toast.makeText(Main.this, "您还没有登录哦！", 0).show();
				}
			}
		});
        home=(ImageButton)findViewById(R.id.home);
        home.setImageDrawable(getResources().getDrawable(R.drawable.home_new));
	    tv_home.setTextColor(Color.rgb(237, 125, 49));    
        home.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				shopView.setVisibility(View.GONE);
				aboutMe.setVisibility(View.GONE);
				unachieve.setVisibility(View.GONE);
				title.setText("东职帮帮乐");
				initButton();
				home.setImageDrawable(getResources().getDrawable(R.drawable.home_new));
				tv_home.setTextColor(Color.rgb(237, 125, 49));
				layouthome.setVisibility(View.VISIBLE);
			}
		});
        buy=(ImageButton)findViewById(R.id.buy);
        buy.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(btn_login.getText().toString().equals("登录")){
					Toast.makeText(Main.this, "您还没有登录哦！", 0).show();
				}else{
				shopView.setVisibility(View.GONE);
				aboutMe.setVisibility(View.GONE);
				layouthome.setVisibility(View.GONE);
				title.setText("外卖订单");
				initButton();
				buy.setImageDrawable(getResources().getDrawable(R.drawable.buy_new));
				tv_buy.setTextColor(Color.rgb(237, 125, 49));
				unachieve.setVisibility(View.VISIBLE);
				}
			}
		});
        shop=(ImageButton)findViewById(R.id.shop);
        
        shop.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(btn_login.getText().toString().equals("登录")){
					Toast.makeText(Main.this, "您还没有登录哦！", 0).show();
				}else{
				aboutMe.setVisibility(View.GONE);
				unachieve.setVisibility(View.GONE);
				layouthome.setVisibility(View.GONE);
				title.setText("购物车");
				initButton();
				shop.setImageDrawable(getResources().getDrawable(R.drawable.shop_new));
				tv_shop.setTextColor(Color.rgb(237, 125, 49));
				shopView.setVisibility(View.VISIBLE);
				
				initlistview(); 
				}
			}
		});
        run=(ImageButton)findViewById(R.id.run);
        run.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(btn_login.getText().toString().equals("登录")){
					Toast.makeText(Main.this, "您还没有登录哦！", 0).show();
				}else{
				shopView.setVisibility(View.GONE);
				aboutMe.setVisibility(View.GONE);
				layouthome.setVisibility(View.GONE);
				title.setText("跑腿订单");
				initButton();
				run.setImageDrawable(getResources().getDrawable(R.drawable.run_new));
				tv_run.setTextColor(Color.rgb(237, 125, 49));
				unachieve.setVisibility(View.VISIBLE);
				}
			}
		});
        help=(ImageButton)findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(Main.this,Help.class);
				startActivity(intent);
			}
		});
        emsservice=(ImageButton)findViewById(R.id.emsservice);
        emsservice.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(btn_login.getText().toString().equals("登录")){
					Toast.makeText(Main.this, "您还没有登录哦", 0).show();
				}else{
				Intent intent=new Intent(Main.this,EmsService.class);
				startActivity(intent);
				}
			}
		});
	}
    public ImageButton btnlistener(ImageButton btn){
    	btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(btn_login.getText().toString().equals("登录")){
					Toast.makeText(Main.this, "您还没有登录哦！", 0).show();
				}
				else{
					Toast.makeText(Main.this, "该功能暂未实现！", 0).show();
				}
			}
		});
		return btn;
    }
	protected void initButton() {
		// TODO Auto-generated method stub
		AboutMySelf.setImageDrawable(getResources().getDrawable(R.drawable.my));
		home.setImageDrawable(getResources().getDrawable(R.drawable.home));
		run.setImageDrawable(getResources().getDrawable(R.drawable.run));
		shop.setImageDrawable(getResources().getDrawable(R.drawable.shop));
		buy.setImageDrawable(getResources().getDrawable(R.drawable.buy));
		tv_home.setTextColor(Color.rgb(0,0,8));
		tv_shop.setTextColor(Color.rgb(0,0,8));
		tv_run.setTextColor(Color.rgb(0,0,8));
		tv_buy.setTextColor(Color.rgb(0,0,8));
		tv_my.setTextColor(Color.rgb(0,0,8));
	}

	protected void init() {
		// TODO Auto-generated method stub
    	webview = (WebView) findViewById(R.id.webView);
        webview.loadUrl("http://132.232.228.178/food.html");
        webview.getSettings().setJavaScriptEnabled(true);
        mErrorView=findViewById(R.id.ErrorView);  
        webview.setWebViewClient(new WebViewClient(){  
            //覆写shouldOverrideUrlLoading实现内部显示网页  
            @Override  
            public boolean shouldOverrideUrlLoading(WebView view, String url) {  
                // TODO 自动生成的方法存根  
                view.loadUrl(url);  
                return true;  
            }  
            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {

                super.onReceivedError(view, errorCode, description, failingUrl);
                view.setVisibility(View.GONE);
                mErrorView.setVisibility(View.VISIBLE);
                loadError = true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (loadError != true) {
                    mErrorView.setVisibility(View.GONE);
                    webview.setVisibility(View.VISIBLE);
                }
            }
        });  
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	 private void priceControl(Map<String, Integer> pitchOnMap){
	        totalCount = 0;
	        totalPrice = 0.00;
	        for(int i=0;i<goodsList.size();i++){
	            if(pitchOnMap.get(goodsList.get(i).get("id"))==1){
	                totalCount=totalCount+Integer.valueOf(goodsList.get(i).get("count"));
	                double goodsPrice=Integer.valueOf(goodsList.get(i).get("count"))*Double.valueOf(goodsList.get(i).get("price"));
	                totalPrice=totalPrice+goodsPrice;
	            }
	        }
	        tv_total_price.setText("￥ "+totalPrice);
	        tv_go_to_pay.setText("付款("+totalCount+")");
	    }

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			 switch (view.getId()){
	         case R.id.checkall:
	             AllTheSelected();
	             break;
	         case R.id.btn_sum:
	             if(totalCount<=0){
	                 Toast.makeText(this,"请选择要付款的商品~",Toast.LENGTH_SHORT).show();
	                 return;
	             }
	             Intent intent=new Intent(Main.this,Paysuccess.class);
	             intent.setClass(Main.this,Paysuccess.class);//从当前activity跳转到另一个activity
	             Main.this.startActivity(intent);  
	             Toast.makeText(this,"付款成功",Toast.LENGTH_SHORT).show();
	             checkDelete(adapter.getPitchOnMap());
	             cb_check_all.setChecked(false);
	             shopView.setVisibility(View.GONE);
				 aboutMe.setVisibility(View.GONE);
				 unachieve.setVisibility(View.GONE);
				 title.setText("东职帮帮乐");
				 initButton();
				 home.setImageDrawable(getResources().getDrawable(R.drawable.home_new));
				 tv_home.setTextColor(Color.rgb(237, 125, 49));
				 layouthome.setVisibility(View.VISIBLE);
	             break;
	         case R.id.btn_delete:
	             if(totalCount<=0){
	                 Toast.makeText(this,"请选择要删除的商品~",Toast.LENGTH_SHORT).show();
	                 return;
	             }
	             checkDelete(adapter.getPitchOnMap());
	             break;
	     }
		}

		@Override
		public void refreshPrice(Map<String, Integer> pitchOnMap) {
			// TODO Auto-generated method stub
			priceControl(pitchOnMap);
		}

	    private void checkDelete(Map<String,Integer> map){
	        List<HashMap<String,String>> waitDeleteList=new ArrayList<>();
	        Map<String,Integer> waitDeleteMap =new HashMap<>();
	        for(int i=0;i<goodsList.size();i++){
	            if(map.get(goodsList.get(i).get("id"))==1){
	                waitDeleteList.add(goodsList.get(i));
	                waitDeleteMap.put(goodsList.get(i).get("id"),map.get(goodsList.get(i).get("id")));
	            }
	        }
	        goodsList.removeAll(waitDeleteList);
	        map.remove(waitDeleteMap);
	        priceControl(map);
	        adapter.notifyDataSetChanged();
	        
	        
	    }
	   

	    
	    private void AllTheSelected(){
	        Map<String,Integer> map=adapter.getPitchOnMap();
	        boolean isCheck=false;
	        boolean isUnCheck=false;
	        Iterator iter = map.entrySet().iterator();
	        while (iter.hasNext()) {
	            Map.Entry entry = (Map.Entry) iter.next();
	            if(Integer.valueOf(entry.getValue().toString())==1)isCheck=true;
	            else isUnCheck=true;
	        }
	        if(isCheck==true&&isUnCheck==false){//已经全选,做反选
	            for(int i=0;i<goodsList.size();i++){
	                map.put(goodsList.get(i).get("id"),0);
	            }
	            cb_check_all.setChecked(false);
	        }else if(isCheck==true && isUnCheck==true){//部分选择,做全选
	            for(int i=0;i<goodsList.size();i++){
	                map.put(goodsList.get(i).get("id"),1);
	            }
	            cb_check_all.setChecked(true);
	        }else if(isCheck==false && isUnCheck==true){//一个没选,做全选
	            for(int i=0;i<goodsList.size();i++){
	                map.put(goodsList.get(i).get("id"),1);
	            }
	            cb_check_all.setChecked(true);
	        }
	        priceControl(map);
	        adapter.setPitchOnMap(map);
	        adapter.notifyDataSetChanged();
	    }

	    private void initView(){
	        listView = (ListView) findViewById(R.id.shopcarlist);
	        cb_check_all = (CheckBox) findViewById(R.id.checkall);
	        tv_total_price = (TextView) findViewById(R.id.tv_allprice);
	        tv_delete = (TextView) findViewById(R.id.btn_delete);
	        tv_go_to_pay = (TextView) findViewById(R.id.btn_sum);
	        tv_go_to_pay.setOnClickListener(this);
	        tv_delete.setOnClickListener(this);
	        cb_check_all.setOnClickListener(this);
	        
	        adapter=new CartAdapter(this,goodsList);
	        adapter.setRefreshPriceInterface(this);
	        listView.setAdapter(adapter);
	        adapter.notifyDataSetChanged();
	        
	        
	    }

	    
	    

	    private void initlistview(){
	    	
	    	
	    	

	        
	        
//	            HashMap<String,String> map=new HashMap<>();
//	            map.put("id",(new Random().nextInt(10000)%(10000-2900+2900) + 2900)+"");
//	            map.put("name","肠粉(蛋肠)");
//	            map.put("type","20");
//	            map.put("price",(new Random().nextInt(100)%(100-29+29) + 29)+"");
//	            map.put("count",(new Random().nextInt(10)%(10-1+1) + 1)+"");
//	            goodsList.add(map);
//	            
//	            HashMap<String,String> map1=new HashMap<>();
//	            map1.put("id",(new Random().nextInt(10000)%(10000-2900+2900) + 2900)+"");
//	            map1.put("name","蚝烙");
//	            map1.put("type",(20)+"");
//	            map1.put("price",(new Random().nextInt(100)%(100-29+29) + 29)+"");
//	            map1.put("count",(new Random().nextInt(10)%(10-1+1) + 1)+"");
//	            goodsList.add(map1);
//	            
//	            HashMap<String,String> map2=new HashMap<>();
//	            map2.put("id",(new Random().nextInt(10000)%(10000-2900+2900) + 2900)+"");
//	            map2.put("name","糯米鸡");
//	            map2.put("type",(20)+"");
//	            map2.put("price",(new Random().nextInt(100)%(100-29+29) + 29)+"");
//	            map2.put("count",(new Random().nextInt(10)%(10-1+1) + 1)+"");
//	            goodsList.add(map2);
//
//	            HashMap<String,String> map3=new HashMap<>();
//	            map3.put("id",(new Random().nextInt(10000)%(10000-2900+2900) + 2900)+"");
//	            map3.put("name","猪杂汤河粉");
//	            map3.put("type",(20)+"");
//	            map3.put("price",(new Random().nextInt(100)%(100-29+29) + 29)+"");
//	            map3.put("count",(new Random().nextInt(10)%(10-1+1) + 1)+"");
//	            goodsList.add(map3);
//	            
//	            HashMap<String,String> map4=new HashMap<>();
//	            map4.put("id",(new Random().nextInt(10000)%(10000-2900+2900) + 2900)+"");
//	            map4.put("name","砂锅粥");
//	            map4.put("type",(20)+"");
//	            map4.put("price",(new Random().nextInt(100)%(100-29+29) + 29)+"");
//	            map4.put("count",(new Random().nextInt(10)%(10-1+1) + 1)+"");
//	            goodsList.add(map4);
//	            
//	            HashMap<String,String> map5=new HashMap<>();
//	            map5.put("id",(new Random().nextInt(10000)%(10000-2900+2900) + 2900)+"");
//	            map5.put("name","肠粉(叉烧)");
//	            map5.put("type",(20)+"");
//	            map5.put("price",(new Random().nextInt(100)%(100-29+29) + 29)+"");
//	            map5.put("count",(new Random().nextInt(10)%(10-1+1) + 1)+"");
//	            goodsList.add(map5);
//	            
	            
	        initView();
	    }
}
