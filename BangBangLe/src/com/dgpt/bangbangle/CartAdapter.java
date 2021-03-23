package com.dgpt.bangbangle;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by 梦丶随心飞
 */

public class CartAdapter extends BaseAdapter {

    private Context context;
    public static  List<HashMap<String,String>> dataList;
    private ViewHolder vh;
    private Map<String,Integer> pitchOnMap;
    private RefreshPriceInterface refreshPriceInterface;
    private int x;

    public CartAdapter(Context context,List<HashMap<String,String>> list){
        this.context=context;
        this.dataList=list;
        


        
        
        pitchOnMap=new HashMap<>();
        for(int i=0;i<dataList.size();i++){
            pitchOnMap.put(dataList.get(i).get("id"),0);
        }
    }
    
    
    public View getView(final int position, View view, ViewGroup viewGroup) {
    	this.notifyDataSetChanged();
    	
        vh=new ViewHolder();
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.item,null);

            vh.checkBox=(CheckBox)view.findViewById(R.id.foodcheck);
            vh.icon=(ImageView)view.findViewById(R.id.pic_food);
            vh.name=(TextView)view.findViewById(R.id.foodname);
            vh.price=(TextView)view.findViewById(R.id.foodprice);
            vh.type=(TextView)view.findViewById(R.id.foodnum);
            vh.num=(TextView)view.findViewById(R.id.num);
            vh.reduce=(ImageView)view.findViewById(R.id.numdec);
            vh.add=(ImageView)view.findViewById(R.id.numadd);
            vh.pic = (ImageView)view.findViewById(R.id.pic_food);

            view.setTag(vh);
        }else {
            vh=(ViewHolder)view.getTag();
        }

        if(dataList.size()>0){

            if(pitchOnMap.get(dataList.get(position).get("id"))==0)vh.checkBox.setChecked(false);
            else vh.checkBox.setChecked(true);
            HashMap<String,String> map=dataList.get(position);
            if(map.get("name").equals("肠粉(蛋肠)")){
                vh.pic.setImageDrawable(view.getResources().getDrawable(R.drawable.food01));
        	}else if (map.get("name").equals("蚝烙")) {
                vh.pic.setImageDrawable(view.getResources().getDrawable(R.drawable.food02));
    		}else if (map.get("name").equals("糯米鸡")) {
                vh.pic.setImageDrawable(view.getResources().getDrawable(R.drawable.food03));
    		}else if (map.get("name").equals("猪杂汤河粉")) {
                vh.pic.setImageDrawable(view.getResources().getDrawable(R.drawable.food04));
    		}else if (map.get("name").equals("砂锅粥")) {
                vh.pic.setImageDrawable(view.getResources().getDrawable(R.drawable.food05));
    		}else if (map.get("name").equals("肠粉(叉烧)")) {
                vh.pic.setImageDrawable(view.getResources().getDrawable(R.drawable.food06));
    		}
            
            vh.name.setText(map.get("name"));
            vh.num.setText(map.get("count"));
            vh.type.setText(map.get("type"));
      
            vh.price.setText("￥ "+(Double.valueOf(map.get("price")) * Integer.valueOf(map.get("count"))));
            
            vh.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final int index=position;
                    if(((CheckBox)view).isChecked())pitchOnMap.put(dataList.get(index).get("id"),1);else pitchOnMap.put(dataList.get(index).get("id"),0);
                    refreshPriceInterface.refreshPrice(pitchOnMap);
                }
            });
            vh.reduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final int index=position;
                    dataList.get(index).put("count",(Integer.valueOf(dataList.get(index).get("count"))-1)+"");
                    if(Integer.valueOf(dataList.get(index).get("count"))<=0){
                        //可提示是否删除该商品,确定就remove,否则就保留1
                        String deID=dataList.get(index).get("id");
                        dataList.remove(index);
                        pitchOnMap.remove(deID);
                    }
                    notifyDataSetChanged();
                    refreshPriceInterface.refreshPrice(pitchOnMap);
                }
            });
            vh.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final int index=position;
                    dataList.get(index).put("count",(Integer.valueOf(dataList.get(index).get("count"))+1)+"");
                    if(Integer.valueOf(dataList.get(index).get("count"))>15){
                        //15为库存可选择上限
                        Toast.makeText(context,"已达库存上限~",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    notifyDataSetChanged();
                    refreshPriceInterface.refreshPrice(pitchOnMap);
                }
            });
        }

        return view;
    }

    public Map<String,Integer> getPitchOnMap(){
        return pitchOnMap;
    }
    public void setPitchOnMap(Map<String,Integer> pitchOnMap){
        this.pitchOnMap=new HashMap<>();
        this.pitchOnMap.putAll(pitchOnMap);
    }

    public interface RefreshPriceInterface{
        void refreshPrice(Map<String,Integer> pitchOnMap);
    }
    public void setRefreshPriceInterface(RefreshPriceInterface refreshPriceInterface){
        this.refreshPriceInterface=refreshPriceInterface;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getCount() {
        if (dataList != null) {
            return dataList.size();
        } else {
            return 0;
        }
    }

    class ViewHolder{
        CheckBox checkBox;
        ImageView icon,reduce,add,pic;
        TextView name,price,num,type;
    }

}
