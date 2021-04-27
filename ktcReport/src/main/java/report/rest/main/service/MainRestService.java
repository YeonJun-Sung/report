package report.rest.main.service;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

public interface MainRestService {
	Map<String, Object> getSheetInfo(String key) throws Exception;

	void saveData(Map<String, Object> param) throws Exception;
}
