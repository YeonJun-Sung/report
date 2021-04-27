package report.rest.main.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;
import report.rest.main.dao.MainRestDAO;

@Service
@Log4j
public class MainRestServiceImpl implements MainRestService {
	@Inject
	private MainRestDAO mainRestDAO;

	@Override
	public Map<String, Object> getSheetInfo(String key) throws Exception {
		// TODO Auto-generated method stub
		return mainRestDAO.getSheetInfo(key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void saveData(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = (List<Map<String, Object>>) param.get("list");
		
		String key = (String) param.get("key");
		int index = (key != null && key.equals("LangZone"))? 2 : 1;
		int col = (Integer) param.get("col");
		
		List<String> colName = new ArrayList<String>();
		for(int i = 0;i < col;i++) {
			colName.add("col" + Integer.toString(i));
		}
		param.put("colName", colName);
		
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		
		for(int i = 0;i < list.size();i++) {
			Map<String, Object> temp = list.get(i);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("dataName", temp.get("0"));
			if(key != null && key.equals("LangZone"))
				map.put("dataSubName", temp.get("1"));
			
			List<String> colData = new ArrayList<String>();
			for(int j = index;j < col + index;j++) {
				colData.add((String) temp.get(Integer.toString(j)));
			}
			map.put("colData", colData);
			
			dataList.add(map);
		}
		param.put("list", dataList);
		
		mainRestDAO.saveData(param);
	}
}
