package report.main.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import report.common.dao.AbstractDAO;

@Repository("mainDAO")
public class MainDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getKeyList() {
		// TODO Auto-generated method stub
		return (List<Map<String, Object>>) selectList("main.getKeyList", "");
	}

	@SuppressWarnings("unchecked")
	public List<String> getTitleName(int idx) {
		// TODO Auto-generated method stub
		return (List<String>) selectList("main.getTitleName", idx);
	}

	// 2 번
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getVisitorMonth(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return (List<Map<String, Object>>) selectList("main.getVisitorMonth", param);
	}

	// 3 번
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getLangTotal(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return (List<Map<String, Object>>) selectList("main.getLangTotal", param);
	}

	// 4 번
	@SuppressWarnings("unchecked")
	public Map<String, Object> getLangMonth(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return (Map<String, Object>) selectOne("main.getLangMonth", param);
	}

	// 5 번
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getLangZone(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return (List<Map<String, Object>>) selectList("main.getLangZone", param);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getLangZoneTotal(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return (Map<String, Object>) selectOne("main.getLangZoneTotal", param);
	}

	// 6 번
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getNationStack() {
		// TODO Auto-generated method stub
		List<String> dateList = (List<String>) selectList("main.getNationDateList", "");
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		for(int i = 0;i < dateList.size();i++) {
			Map<String, Object> tempMap = new HashMap<String, Object>();
			String date = dateList.get(i);
			tempMap.put("month", date);
			tempMap.put("rank", selectList("main.getNationRank", date));
			
			list.add(tempMap);
		}
		
		return list;
	}

	// 7 번
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getNationMonth(String date) {
		// TODO Auto-generated method stub
		return (List<Map<String, Object>>) selectList("main.getNationMonth", date);
	}

	public int getPreNationMonth(Map<String, Object> param) {
		// TODO Auto-generated method stub
		int count = (Integer) selectOne("main.getPreNationMonthChk", param);
		
		if(count != 0)
			return (Integer) selectOne("main.getPreNationMonth", param);
		else
			return 0;
	}

	// 8-1 번
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDevice(String date) {
		// TODO Auto-generated method stub
		return (List<Map<String, Object>>) selectList("main.getDevice", date);
	}

	// 8-2 번
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getOs(String date) {
		// TODO Auto-generated method stub
		return (List<Map<String, Object>>) selectList("main.getOs", date);
	}

	// 9 번
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getInflow(String date) {
		// TODO Auto-generated method stub
		return (List<Map<String, Object>>) selectList("main.getInflow", date);
	}

	public int getPreInflow(Map<String, Object> param) {
		// TODO Auto-generated method stub
		int count = (Integer) selectOne("main.getPreInflowChk", param);
		
		if(count != 0)
			return (Integer) selectOne("main.getPreInflow", param);
		else
			return 0;
	}

	// 10 번
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getOrganic(String date) {
		// TODO Auto-generated method stub
		return (List<Map<String, Object>>) selectList("main.getOrganic", date);
	}

	// 11 번
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getReferral(String date) {
		// TODO Auto-generated method stub
		return (List<Map<String, Object>>) selectList("main.getReferral", date);
	}

	public int getPreReferral(Map<String, Object> param) {
		// TODO Auto-generated method stub
		int count = (Integer) selectOne("main.getPreReferralChk", param);
		
		if(count != 0)
			return (Integer) selectOne("main.getPreReferral", param);
		else
			return 0;
	}

	// 12 번
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getSocial(String date) {
		// TODO Auto-generated method stub
		return (List<Map<String, Object>>) selectList("main.getSocial", date);
	}

	public int getPreSocial(Map<String, Object> param) {
		// TODO Auto-generated method stub
		int count = (Integer) selectOne("main.getPreSocialChk", param);
		
		if(count != 0)
			return (Integer) selectOne("main.getPreSocial", param);
		else
			return 0;
	}

	// 14 번
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getPageView(String date) {
		// TODO Auto-generated method stub
		return (List<Map<String, Object>>) selectList("main.getPageView", date);
	}

	public int getPrePageView(Map<String, Object> param) {
		// TODO Auto-generated method stub
		int count = (Integer) selectOne("main.getPrePageViewChk", param);
		
		if(count != 0)
			return (Integer) selectOne("main.getPrePageView", param);
		else
			return 0;
	}
}
