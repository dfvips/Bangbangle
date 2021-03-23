package com.dgpt.bangbangle;

import java.util.HashMap;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class FoodShow extends Activity {
	private ImageButton btn_back;
	private WebView webview;
	private ProgressBar pg1;
	private TextView title;
	private View mErrorView;
	private Button btn_reflash;
	private Boolean loadError=false;
    private ListView browseListView;
    private BrowseListAdapter qualityListAdapter;
    private View profile_browse;
	private ListView quality_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); 
        setContentView(R.layout.activity_food_show);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebtn);
        title=(TextView)findViewById(R.id.title);
        title.setText("广东美食");
        init();
        profile_browse=findViewById(R.id.profile_browse);
        quality_list=(ListView)findViewById(R.id.quality_list);
        quality_list.setCacheColorHint(0); 
        quality_list.setDivider(null);
        btn_back=(ImageButton)findViewById(R.id.imageButton1);
        btn_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
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
        browseListView = (ListView) findViewById(R.id.quality_list);
        View footview = LayoutInflater.from(this).inflate(
                R.layout.list_no_more, null);

        browseListView.addFooterView(footview);
        ImageView good_detail_up = (ImageView)findViewById(R.id.good_detail_up);
        good_detail_up.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                if (browseListView != null) {
                    browseListView.setSelection(0);
                }
            }
        });
        qualityListAdapter = new BrowseListAdapter(this);
        browseListView.setAdapter(qualityListAdapter);
//        browseListView.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position,
//					long id) {
//				// TODO Auto-generated method stub
//			
//				Toast.makeText(FoodShow.this, position, 0).show();
//			}
//		});
    }

    private class BrowseListAdapter extends BaseAdapter {
        private Context mContext;

        public BrowseListAdapter(Context context) {
            this.mContext = context;
        }
        @Override
		public int getCount() {
                return texts.length;
        }

        @Override
		public Object getItem(int position) {
            return null;
        }

        @Override
		public long getItemId(int position) {
            return position;
        }

        @Override
		public View getView(final int position, View convertView, ViewGroup parent) {
            // 优化ListView
            ItemViewCache viewCache;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(
                        R.layout.myfoodliststyle, null);
                viewCache = new ItemViewCache();
                viewCache.mTextView = (TextView) convertView
                        .findViewById(R.id.quality_name);
                viewCache.mPriceView = (TextView) convertView
                        .findViewById(R.id.quality_price);
                viewCache.mPhotoView = (ImageView) convertView
                        .findViewById(R.id.quality_photo);
                viewCache.mPrice2View = (TextView) convertView
                        .findViewById(R.id.quality_price_original);
                viewCache.mPrice2View.getPaint().setAntiAlias(true);
                viewCache.mPrice2View.getPaint().setFlags(
                        Paint.STRIKE_THRU_TEXT_FLAG);
                viewCache.btn_add = (TextView) convertView
                        .findViewById(R.id.btn_add);
                viewCache.mTextView.setText(texts[position]);
                viewCache.mPriceView.setText(texts1[position]);
                viewCache.mPhotoView.setImageResource(photo[position]);
                viewCache.mPrice2View.setText(texts2[position]); 
                convertView.setTag(viewCache);
            } else {
                viewCache = (ItemViewCache) convertView.getTag();
            }
            	viewCache.btn_add.setClickable(true);

            	viewCache.btn_add.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						if(Main.btn_login.getText().toString().equals("登录")){
							Toast.makeText(FoodShow.this, "请登录后再添加购物车!", 0).show();
						}
						else{
						Toast.makeText(FoodShow.this, "您成功把一份"+texts[position]+"添加到购物车", 0).show();

						Log.e("111", texts[position]+":"+texts1[position]);
						
//						"肠粉(蛋肠)",
//			            "蚝烙", "糯米鸡",
//			            "猪杂汤河粉", "砂锅粥", "肠粉(叉烧)" };
						int o=10000;
						for(int i=0;i<Main.goodsList.size();i++){
							if(Main.goodsList.get(i).get("name").equals(texts[position])){
								o=i;
								
							}
						}
						
						if(Main.goodsList.size()!=0&&o!=10000){
//							Main.goodsList.get(i).get("name").equals(texts[position])
								Main.goodsList.get(o).put("count", Integer.parseInt(Main.goodsList.get(o).get("count"))+1+"");
						}else{
							HashMap<String,String> map=new HashMap<>();
				            map.put("id",(new Random().nextInt(10000)%(10000-2900+2900) + 2900)+"");
				            map.put("name",texts[position]);
				            map.put("type","20");
				            map.put("price",texts1[position]);
				            map.put("count","1");
				            
				            Main.goodsList.add(map);
						}
			            
						}
			            
			            
			            
					} 
				});
            return convertView;
        }
    }

    private static class ItemViewCache {
        public TextView mTextView;
        public TextView mPriceView;
        public ImageView mPhotoView;
        public TextView mPrice2View;
        public TextView btn_add;
    }

    // 展示的文字
    private String[] texts = new String[] { "肠粉(蛋肠)",
            "蚝烙", "糯米鸡",
            "猪杂汤河粉", "砂锅粥", "肠粉(叉烧)" };
    private String[] texts1 = new String[] { "7.00", "12.00", "8.00",
            "10.00", "15.00", "8.00"};
    private int[] photo = new int[] { R.drawable.food01,
            R.drawable.food02, R.drawable.food03,
            R.drawable.food04, R.drawable.food05,
            R.drawable.food06, R.drawable.food01,
            R.drawable.food02 };
    private String[] texts2 = new String[] { "￥10.00", "￥15.00", "￥16.00",
            "￥15.00", "￥30.00", "￥16.00"};
   
    private void init() {  
        // TODO 自动生成的方法存根  
    	webview = (WebView) findViewById(R.id.webView);
        webview.loadUrl("http://132.232.228.178/foodshow.html");
        pg1=(ProgressBar) findViewById(R.id.progressBar1);  
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
                    webview.setVisibility(View.GONE);
                    profile_browse.setVisibility(View.VISIBLE);
                }


            }
        });  
        WebSettings seting=webview.getSettings();  
        seting.setJavaScriptEnabled(true);//设置webview支持javascript脚本  
        webview.setWebChromeClient(new WebChromeClient(){  
            @Override  
            public void onProgressChanged(WebView view, int newProgress) {  
                // TODO 自动生成的方法存根  
                  
                if(newProgress==100){  
                    pg1.setVisibility(View.GONE);//加载完网页进度条消失  
                }  
                else{  
                    pg1.setVisibility(View.VISIBLE);//开始加载网页时显示进度条  
                    pg1.setProgress(newProgress);//设置进度值  
                }  
                  
            }  
        });  
    }  

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.food_show, menu);
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
