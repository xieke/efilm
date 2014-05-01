package sand.ws.network;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tool.dao.BizObject;

public class JsonTool {
	public static BizObject toBizObj(String str) {
		BizObject biz = new BizObject("");
		try {
			JSONObject dataJson = new JSONObject(str);
			Iterator it = dataJson.keys();
			
			String ikey = "";
			while(it.hasNext()){
				ikey = (String)it.next();
				if(ikey.equals("datas")) biz.set(ikey, toBizObjectWithDatas(dataJson.get(ikey).toString()));
				else biz.set(ikey, dataJson.get(ikey));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return biz;
	}
	
	public static List<BizObject> toBizObjectWithDatas(String str){
		BizObject biz = null;
		List<BizObject> datas = new ArrayList<BizObject>();
		try {
			JSONArray array = new JSONArray(str);
			for(int i=0;i<array.length();i++){
				Iterator it = array.getJSONObject(i).keys();
				biz = new BizObject("");
				String ikey = "";
				while(it.hasNext()){
					ikey = (String)it.next();
					biz.set(ikey, array.getJSONObject(i).get(ikey));
				}
				datas.add(biz);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return datas;
	}
}
