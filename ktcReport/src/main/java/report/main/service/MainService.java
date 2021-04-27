package report.main.service;

import java.util.List;
import java.util.Map;

public interface MainService {
	List<Map<String, Object>> getKeyList() throws Exception;

	List<String> getTitleName(int idx) throws Exception;

	List<Map<String, Object>> getChartData(int idx, String date, String preDate) throws Exception;
}
