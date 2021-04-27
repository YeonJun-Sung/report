package report.rest.main.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import report.common.dao.AbstractDAO;

@Repository("mainRestDAO")
public class MainRestDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	public Map<String, Object> getSheetInfo(String key) {
		// TODO Auto-generated method stub
		return (Map<String, Object>) selectOne("mainRest.getSheetInfo", key);
	}

	public void saveData(Map<String, Object> param) {
		// TODO Auto-generated method stub
		insert("mainRest.saveData", param);
	}
}
