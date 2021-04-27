package report.main.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;
import report.main.dao.MainDAO;

@Service
@Log4j
public class MainServiceImpl implements MainService {
	@Inject
	private MainDAO mainDAO;

	@Override
	public List<Map<String, Object>> getKeyList() throws Exception {
		// TODO Auto-generated method stub
		return mainDAO.getKeyList();
	}

	@Override
	public List<String> getTitleName(int idx) throws Exception {
		// TODO Auto-generated method stub
		return mainDAO.getTitleName(idx);
	}

	@Override
	public List<Map<String, Object>> getChartData(int idx, String date, String preDate) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		if(idx == 1) {
			// 홈페이지 주요 지표
			/*
				RESULT 3순위 (누적 지표)
				지표명
					> 방문자 수
					> 신규 방문자 수
					> 일 평균 신규 방문자 수
					> 신규 방문 비율
					> 재방문 비율
					> PC 방문자 수
					> Mobile & Tablet 방문자 수
					> 페이지뷰(PV)
					> 평균 페이지뷰
				전월
				당월
				전월 대비
				전체 기간 평균
				전체 기간 누적
			*/
		}
		else if(idx == 2) {
			// 일별 방문자 수
			/*
				TABLE
				col0	세션

				
				RESULT
				일자
				방문자 수
			*/
			Map<String, Object> tempParam = new HashMap<String, Object>();
			tempParam.put("date", date);
			result = mainDAO.getVisitorMonth(tempParam);
		}
		else if(idx == 3) {
			// 어권별 사이트 유입 통계
			/*
				TABLE	>	언어
				col0	세션
				col1	새로운 세션
				col2	신규 방문자
				col3	이탈률
				col4	세션당 페이지수
				col5	평균 세션 시간
				col6	목표 전환율
				col7	목표 달성 횟수
				col8	목표값
				
				
				RESULT 3순위 (누적 지표)
				월별
				순위
				
				월별 언어별
				방문자 수
			*/
			// 국문
			Map<String, Object> tempParam = new HashMap<String, Object>();
			tempParam.put("lang", "ko");
			List<Map<String, Object>> tempList = mainDAO.getLangTotal(tempParam);
			Map<String, Object> tempData = new HashMap<String, Object>();
			tempData.put("year", tempList);
			tempData.put("lang", "ko");
			
			result.add(tempData);

			// 영문
			tempParam.put("lang", "en");
			tempList = mainDAO.getLangTotal(tempParam);
			tempData = new HashMap<String, Object>();
			tempData.put("year", tempList);
			tempData.put("lang", "en");
			
			result.add(tempData);

			// 일문
			tempParam.put("lang", "ja");
			tempList = mainDAO.getLangTotal(tempParam);
			tempData = new HashMap<String, Object>();
			tempData.put("year", tempList);
			tempData.put("lang", "ja");

			result.add(tempData);

			// 중문 - 간체
			tempParam.put("lang", "cn");
			tempList = mainDAO.getLangTotal(tempParam);
			tempData = new HashMap<String, Object>();
			tempData.put("year", tempList);
			tempData.put("lang", "cn");

			result.add(tempData);
			
			// 중문 - 번체
			tempParam.put("lang", "tw");
			tempList = mainDAO.getLangTotal(tempParam);
			tempData = new HashMap<String, Object>();
			tempData.put("year", tempList);
			tempData.put("lang", "tw");
			
			result.add(tempData);
		}
		else if(idx == 4) {
			// 어권별 사이트 유입
			/*
				TABLE	>	언어
				col0	세션
				col1	새로운 세션
				col2	신규 방문자
				col3	이탈률
				col4	세션당 페이지수
				col5	평균 세션 시간
				col6	목표 전환율
				col7	목표 달성 횟수
				col8	목표값
				
				
				RESULT
				언어
				전월
				당월
				전월 대비
				신규 방문자 수
				신규 방문 비율
				평균 페이지뷰
				평균 방문 시간
			*/
			// 국문
			Map<String, Object> tempParam = new HashMap<String, Object>();
			tempParam.put("date", date);
			tempParam.put("lang", "ko");
			Map<String, Object> tempData = mainDAO.getLangMonth(tempParam);
			
			tempParam.put("date", preDate);
			Map<String, Object> preTempData = mainDAO.getLangMonth(tempParam);
			int preSession = (int) Float.parseFloat(preTempData.get("currentSession").toString());
			int currentSession = (int) Float.parseFloat(tempData.get("currentSession").toString());
			float compareSession = ((float) currentSession - (float) preSession) / (float) preSession;
			
			tempData.put("preSession", preSession);
			tempData.put("compareSession", compareSession);
			
			result.add(tempData);

			// 영문
			tempParam.put("date", date);
			tempParam.put("lang", "en");
			tempData = mainDAO.getLangMonth(tempParam);
			
			tempParam.put("date", preDate);
			preTempData = mainDAO.getLangMonth(tempParam);
			preSession = (int) Float.parseFloat(preTempData.get("currentSession").toString());
			currentSession = (int) Float.parseFloat(tempData.get("currentSession").toString());
			compareSession = ((float) currentSession - (float) preSession) / (float) preSession;
			
			tempData.put("preSession", preSession);
			tempData.put("compareSession", compareSession);
			
			result.add(tempData);

			// 일문
			tempParam.put("date", date);
			tempParam.put("lang", "ja");
			tempData = mainDAO.getLangMonth(tempParam);
			
			tempParam.put("date", preDate);
			preTempData = mainDAO.getLangMonth(tempParam);
			preSession = (int) Float.parseFloat(preTempData.get("currentSession").toString());
			currentSession = (int) Float.parseFloat(tempData.get("currentSession").toString());
			compareSession = ((float) currentSession - (float) preSession) / (float) preSession;
			
			tempData.put("preSession", preSession);
			tempData.put("compareSession", compareSession);
			
			result.add(tempData);

			// 중문 - 간체
			tempParam.put("date", date);
			tempParam.put("lang", "cn");
			tempData = mainDAO.getLangMonth(tempParam);
			
			tempParam.put("date", preDate);
			preTempData = mainDAO.getLangMonth(tempParam);
			preSession = (int) Float.parseFloat(preTempData.get("currentSession").toString());
			currentSession = (int) Float.parseFloat(tempData.get("currentSession").toString());
			compareSession = ((float) currentSession - (float) preSession) / (float) preSession;
			
			tempData.put("preSession", preSession);
			tempData.put("compareSession", compareSession);
			
			result.add(tempData);
			
			// 중문 - 번체
			tempParam.put("date", date);
			tempParam.put("lang", "tw");
			tempData = mainDAO.getLangMonth(tempParam);
			
			tempParam.put("date", preDate);
			preTempData = mainDAO.getLangMonth(tempParam);
			preSession = (int) Float.parseFloat(preTempData.get("currentSession").toString());
			currentSession = (int) Float.parseFloat(tempData.get("currentSession").toString());
			compareSession = ((float) currentSession - (float) preSession) / (float) preSession;
			
			tempData.put("preSession", preSession);
			tempData.put("compareSession", compareSession);
			
			result.add(tempData);
			
			// list sort 필요
		}
		else if(idx == 5) {
			// 어권별 페이지뷰 추이
			/*
				TABLE	>	어권
				col0	페이지뷰 수
				col1	순 페이지뷰 수
				col2	평균 페이지에 머문 시간
				col3	방문수
				col4	이탈률
				col5	종료율
				col6	페이지 값
				
				
				RESULT (누적 지표)
				구분
				월별
				합계
			*/
			// 국문
			Map<String, Object> tempParam = new HashMap<String, Object>();
			tempParam.put("lang", "ko");
			List<Map<String, Object>> tempList = mainDAO.getLangZone(tempParam);
			Map<String, Object> tempTotal = mainDAO.getLangZoneTotal(tempParam);
			tempList.add(tempTotal);
			
			Map<String, Object> tempData = new HashMap<String, Object>();
			tempData.put("year", tempList);
			tempData.put("lang", "ko");
			
			result.add(tempData);

			// 영문
			tempParam.put("lang", "en");
			tempList = mainDAO.getLangZone(tempParam);
			tempTotal = mainDAO.getLangZoneTotal(tempParam);
			tempList.add(tempTotal);
			
			tempData = new HashMap<String, Object>();
			tempData.put("year", tempList);
			tempData.put("lang", "en");
			
			result.add(tempData);

			// 일문
			tempParam.put("lang", "ja");
			tempList = mainDAO.getLangZone(tempParam);
			tempTotal = mainDAO.getLangZoneTotal(tempParam);
			tempList.add(tempTotal);
			
			tempData = new HashMap<String, Object>();
			tempData.put("year", tempList);
			tempData.put("lang", "ja");

			result.add(tempData);

			// 중문 - 간체
			tempParam.put("lang", "cn");
			tempList = mainDAO.getLangZone(tempParam);
			tempTotal = mainDAO.getLangZoneTotal(tempParam);
			tempList.add(tempTotal);
			
			tempData = new HashMap<String, Object>();
			tempData.put("year", tempList);
			tempData.put("lang", "cn");

			result.add(tempData);
			
			// 중문 - 번체
			tempParam.put("lang", "tw");
			tempList = mainDAO.getLangZone(tempParam);
			tempTotal = mainDAO.getLangZoneTotal(tempParam);
			tempList.add(tempTotal);
			
			tempData = new HashMap<String, Object>();
			tempData.put("year", tempList);
			tempData.put("lang", "tw");
			
			result.add(tempData);
		}
		else if(idx == 6) {
			// 국가별 사이트 유입 통계
			/*
				TABLE
				col0	세션
				col1	새로운 세션
				col2	신규 방문자
				col3	이탈률
				col4	세션당 페이지수
				col5	평균 세션 시간
				col6	목표 전환율
				col7	목표 달성 횟수
				col8	목표값
				
				
				RESULT 월별 순위 (누적 지표)
				국가
				방문자 수
			*/
			result = mainDAO.getNationStack();
		}
		else if(idx == 7) {
			// 국가별 사이트 유입
			/*
				TABLE
				col0	세션
				col1	새로운 세션
				col2	신규 방문자
				col3	이탈률
				col4	세션당 페이지수
				col5	평균 세션 시간
				col6	목표 전환율
				col7	목표 달성 횟수
				col8	목표값
				
				
				RESULT
				국가
				전월
				당월
				전월 대비
				신규 방문자 수
				신규 방문 비율
				평균 페이지뷰
				평균 방문 시간
			*/
			List<Map<String, Object>> tempData = mainDAO.getNationMonth(date);
			
			for(int i = 0;i < tempData.size();i++) {
				Map<String, Object> tempMap = tempData.get(i);
				
				Map<String, Object> param = new HashMap<String, Object>();
				String dataName = (String) tempMap.get("dataName");
				param.put("dataName", dataName);
				param.put("date", preDate);

				int preSession = mainDAO.getPreNationMonth(param);
				int currentSession = (int) Float.parseFloat(tempMap.get("col0").toString());
				float compareSession = ((float) currentSession - (float) preSession) / (float) preSession;
				int newSession = (int) Float.parseFloat(tempMap.get("col2").toString());
				float newSessionRate = Float.parseFloat(tempMap.get("col1").toString());
				float avgSessionPageView = Float.parseFloat(tempMap.get("col4").toString());
				int avgSessionTime = (int) Float.parseFloat(tempMap.get("col5").toString());
				
				Map<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("dataName", dataName);
				resultMap.put("preSession", preSession);
				resultMap.put("currentSession", currentSession);
				resultMap.put("compareSession", compareSession);
				resultMap.put("newSession", newSession);
				resultMap.put("newSessionRate", newSessionRate);
				resultMap.put("avgSessionPageView", avgSessionPageView);
				resultMap.put("avgSessionTime", avgSessionTime);
				
				result.add(resultMap);
			}
		}
		else if(idx == 81) {
			// 디바이스별 사이트 유입
			/*
				TABLE
				col0	세션
				col1	새로운 세션
				col2	신규 방문자
				col3	이탈률
				col4	세션당 페이지수
				col5	평균 세션 시간
				col6	목표 전환율
				col7	목표 달성 횟수
				col8	목표값
				
				
				RESULT
				디바이스
				방문자 수
				신규 방문자 수
				신규 방문 비율
				평균 페이지뷰
				평균 방문 시간
			*/
			List<Map<String, Object>> tempData = mainDAO.getDevice(date);
			int sumCurrentSession = 0;
			int sumNewSession = 0;
			float sumNewSessionRate = 0;
			float sumAvgSessionPageView = 0;
			int sumAvgSessionTime = 0;
			
			for(int i = 0;i < tempData.size();i++) {
				Map<String, Object> tempMap = tempData.get(i);
				
				String dataName = (String) tempMap.get("dataName");
				int currentSession = (int) Float.parseFloat(tempMap.get("col0").toString());
				int newSession = (int) Float.parseFloat(tempMap.get("col2").toString());
				float newSessionRate = Float.parseFloat(tempMap.get("col1").toString());
				float avgSessionPageView = Float.parseFloat(tempMap.get("col4").toString());
				int avgSessionTime = (int) Float.parseFloat(tempMap.get("col5").toString());
				
				Map<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("dataName", dataName);
				resultMap.put("currentSession", currentSession);
				resultMap.put("newSession", newSession);
				resultMap.put("newSessionRate", newSessionRate);
				resultMap.put("avgSessionPageView", avgSessionPageView);
				resultMap.put("avgSessionTime", avgSessionTime);
				
				sumCurrentSession += currentSession;
				sumNewSession += newSession;
				sumNewSessionRate += newSessionRate;
				sumAvgSessionPageView += avgSessionPageView;
				sumAvgSessionTime += avgSessionTime;
				
				result.add(resultMap);
			}
			
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("dataName", "TOTAL");
			resultMap.put("currentSession", sumCurrentSession);
			resultMap.put("newSession", sumNewSession);
			resultMap.put("newSessionRate", sumNewSessionRate / tempData.size());
			resultMap.put("avgSessionPageView", sumAvgSessionPageView / tempData.size());
			resultMap.put("avgSessionTime", sumAvgSessionTime / tempData.size());
			
			result.add(resultMap);
		}
		else if(idx == 82) {
			// 운영체제별 사이트 유입
			/*
				TABLE
				col0	세션
				col1	새로운 세션
				col2	신규 방문자
				col3	이탈률
				col4	세션당 페이지수
				col5	평균 세션 시간
				col6	목표 전환율
				col7	목표 달성 횟수
				col8	목표값
				
				
				RESULT
				OS
				방문자 수
				신규 방문자 수
				신규 방문 비율
				평균 페이지뷰
				평균 방문 시간
			*/
			List<Map<String, Object>> tempData = mainDAO.getOs(date);
			int sumCurrentSession = 0;
			int sumNewSession = 0;
			float sumNewSessionRate = 0;
			float sumAvgSessionPageView = 0;
			int sumAvgSessionTime = 0;
			
			for(int i = 0;i < tempData.size();i++) {
				Map<String, Object> tempMap = tempData.get(i);
				
				String dataName = (String) tempMap.get("dataName");
				int currentSession = (int) Float.parseFloat(tempMap.get("col0").toString());
				int newSession = (int) Float.parseFloat(tempMap.get("col2").toString());
				float newSessionRate = Float.parseFloat(tempMap.get("col1").toString());
				float avgSessionPageView = Float.parseFloat(tempMap.get("col4").toString());
				int avgSessionTime = (int) Float.parseFloat(tempMap.get("col5").toString());
				
				Map<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("dataName", dataName);
				resultMap.put("currentSession", currentSession);
				resultMap.put("newSession", newSession);
				resultMap.put("newSessionRate", newSessionRate);
				resultMap.put("avgSessionPageView", avgSessionPageView);
				resultMap.put("avgSessionTime", avgSessionTime);
				
				sumCurrentSession += currentSession;
				sumNewSession += newSession;
				sumNewSessionRate += newSessionRate;
				sumAvgSessionPageView += avgSessionPageView;
				sumAvgSessionTime += avgSessionTime;
				
				result.add(resultMap);
			}
			
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("dataName", "TOTAL");
			resultMap.put("currentSession", sumCurrentSession);
			resultMap.put("newSession", sumNewSession);
			resultMap.put("newSessionRate", sumNewSessionRate / tempData.size());
			resultMap.put("avgSessionPageView", sumAvgSessionPageView / tempData.size());
			resultMap.put("avgSessionTime", sumAvgSessionTime / tempData.size());
			
			result.add(resultMap);
		}
		else if(idx == 9) {
			// 유입채널
			/*
				TABLE
				col0	세션
				col1	새로운 세션
				col2	신규 방문자
				col3	이탈률
				col4	세션당 페이지수
				col5	평균 세션 시간
				col6	목표 전환율
				col7	목표 달성 횟수
				col8	목표값
				
				
				RESULT
				유입채널
				전월
				당월
				전월 대비
				신규 방문자 수
				신규 방문 비율
				평균 페이지뷰
				평균 방문 시간
			*/
			List<Map<String, Object>> tempData = mainDAO.getInflow(date);
			
			for(int i = 0;i < tempData.size();i++) {
				Map<String, Object> tempMap = tempData.get(i);
				
				Map<String, Object> param = new HashMap<String, Object>();
				String dataName = (String) tempMap.get("dataName");
				param.put("dataName", dataName);
				param.put("date", preDate);

				int preSession = mainDAO.getPreInflow(param);
				int currentSession = (int) Float.parseFloat(tempMap.get("col0").toString());
				float compareSession = ((float) currentSession - (float) preSession) / (float) preSession;
				int newSession = (int) Float.parseFloat(tempMap.get("col2").toString());
				float newSessionRate = Float.parseFloat(tempMap.get("col1").toString());
				float avgSessionPageView = Float.parseFloat(tempMap.get("col4").toString());
				int avgSessionTime = (int) Float.parseFloat(tempMap.get("col5").toString());
				
				Map<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("dataName", dataName);
				resultMap.put("preSession", preSession);
				resultMap.put("currentSession", currentSession);
				resultMap.put("compareSession", compareSession);
				resultMap.put("newSession", newSession);
				resultMap.put("newSessionRate", newSessionRate);
				resultMap.put("avgSessionPageView", avgSessionPageView);
				resultMap.put("avgSessionTime", avgSessionTime);
				
				result.add(resultMap);
			}
		}
		else if(idx == 10) {
			// Organic Search
			/*
				TABLE
				col0	세션
				col1	새로운 세션
				col2	신규 방문자
				col3	이탈률
				col4	세션당 페이지수
				col5	평균 세션 시간
				col6	목표 전환율
				col7	목표 달성 횟수
				col8	목표값
				
				
				RESULT
				키워드
				방문자 수
				신규 방문자 수
				신규 방문 비율
				평균 페이지뷰
				평균 방문 시간
			*/
			List<Map<String, Object>> tempData = mainDAO.getOrganic(date);
			
			for(int i = 0;i < tempData.size();i++) {
				Map<String, Object> tempMap = tempData.get(i);
				
				String dataName = (String) tempMap.get("dataName");
				int currentSession = (int) Float.parseFloat(tempMap.get("col0").toString());
				int newSession = (int) Float.parseFloat(tempMap.get("col2").toString());
				float newSessionRate = Float.parseFloat(tempMap.get("col1").toString());
				float avgSessionPageView = Float.parseFloat(tempMap.get("col4").toString());
				int avgSessionTime = (int) Float.parseFloat(tempMap.get("col5").toString());
				
				Map<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("dataName", dataName);
				resultMap.put("currentSession", currentSession);
				resultMap.put("newSession", newSession);
				resultMap.put("newSessionRate", newSessionRate);
				resultMap.put("avgSessionPageView", avgSessionPageView);
				resultMap.put("avgSessionTime", avgSessionTime);
				
				result.add(resultMap);
			}
		}
		else if(idx == 11) {
			// Referral
			/*
				TABLE
				col0	세션
				col1	새로운 세션
				col2	신규 방문자
				col3	이탈률
				col4	세션당 페이지수
				col5	평균 세션 시간
				col6	목표 전환율
				col7	목표 달성 횟수
				col8	목표값
				
				
				RESULT
				외부 도메인
				전월
				당월
				전월 대비
				신규 방문자 수
				신규 방문 비율
				평균 페이지뷰
				평균 방문 시간
			*/
			List<Map<String, Object>> tempData = mainDAO.getReferral(date);
			
			for(int i = 0;i < tempData.size();i++) {
				Map<String, Object> tempMap = tempData.get(i);
				
				Map<String, Object> param = new HashMap<String, Object>();
				String dataName = (String) tempMap.get("dataName");
				param.put("dataName", dataName);
				param.put("date", preDate);

				int preSession = mainDAO.getPreReferral(param);
				int currentSession = (int) Float.parseFloat(tempMap.get("col0").toString());
				float compareSession = ((float) currentSession - (float) preSession) / (float) preSession;
				int newSession = (int) Float.parseFloat(tempMap.get("col2").toString());
				float newSessionRate = Float.parseFloat(tempMap.get("col1").toString());
				float avgSessionPageView = Float.parseFloat(tempMap.get("col4").toString());
				int avgSessionTime = (int) Float.parseFloat(tempMap.get("col5").toString());
				
				Map<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("dataName", dataName);
				resultMap.put("preSession", preSession);
				resultMap.put("currentSession", currentSession);
				resultMap.put("compareSession", compareSession);
				resultMap.put("newSession", newSession);
				resultMap.put("newSessionRate", newSessionRate);
				resultMap.put("avgSessionPageView", avgSessionPageView);
				resultMap.put("avgSessionTime", avgSessionTime);
				
				result.add(resultMap);
			}
		}
		else if(idx == 12) {
			// Social
			/*
				TABLE
				col0	세션
				col1	새로운 세션
				col2	신규 방문자
				col3	이탈률
				col4	세션당 페이지수
				col5	평균 세션 시간
				col6	목표 전환율
				col7	목표 달성 횟수
				col8	목표값
				
				
				RESULT
				소셜 네트워크
				전월
				당월
				전월 대비
				신규 방문자 수
				신규 방문 비율
				평균 페이지뷰
				평균 방문 시간
			*/
			List<Map<String, Object>> tempData = mainDAO.getSocial(date);
			
			for(int i = 0;i < tempData.size();i++) {
				Map<String, Object> tempMap = tempData.get(i);
				
				Map<String, Object> param = new HashMap<String, Object>();
				String dataName = (String) tempMap.get("dataName");
				param.put("dataName", dataName);
				param.put("date", preDate);
				
				int preSession = mainDAO.getPreSocial(param);
				int currentSession = (int) Float.parseFloat(tempMap.get("col0").toString());
				float compareSession = ((float) currentSession - (float) preSession) / (float) preSession;
				int newSession = (int) Float.parseFloat(tempMap.get("col2").toString());
				float newSessionRate = Float.parseFloat(tempMap.get("col1").toString());
				float avgSessionPageView = Float.parseFloat(tempMap.get("col4").toString());
				int avgSessionTime = (int) Float.parseFloat(tempMap.get("col5").toString());
				
				Map<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("dataName", dataName);
				resultMap.put("preSession", preSession);
				resultMap.put("currentSession", currentSession);
				resultMap.put("compareSession", compareSession);
				resultMap.put("newSession", newSession);
				resultMap.put("newSessionRate", newSessionRate);
				resultMap.put("avgSessionPageView", avgSessionPageView);
				resultMap.put("avgSessionTime", avgSessionTime);
				
				result.add(resultMap);
			}
		}
		else if(idx == 13) {
			// 홈페이지 인기 검색 키워드
			/*
				TABLE
				col0	전체 순 검색량
				
				RESULT
				키워드
				검색 횟수
			*/
		}
		else if(idx == 14) {
			// 페이지뷰
			/*
				TABLE
				col0	페이지뷰 수
				col1	순 페이지뷰 수
				col2	평균 페이지에 머문 시간
				col3	방문수
				col4	이탈률
				col5	종료율
				col6	페이지 값
				
				
				RESULT
				언어구분
				페이지명
				전월 페이지뷰
				당월 페이지뷰
				전월 대비
				순 페이지 뷰
				방문수
				평균 방문 시간
			*/
			List<Map<String, Object>> tempData = mainDAO.getPageView(date);
			
			for(int i = 0;i < tempData.size();i++) {
				Map<String, Object> tempMap = tempData.get(i);
				
				Map<String, Object> param = new HashMap<String, Object>();
				String dataName = (String) tempMap.get("dataName");
				param.put("dataName", dataName);
				param.put("date", preDate);
				
				String lang = "ko";
				if(dataName != null && (dataName.indexOf("/en/") != -1 || dataName.indexOf("lang=en") != -1)) {
					lang = "en";
				}
				else if(dataName != null && (dataName.indexOf("/cn/") != -1 || dataName.indexOf("lang=cn") != -1)) {
					lang = "cn";
				}
				else if(dataName != null && (dataName.indexOf("/tw/") != -1 || dataName.indexOf("lang=tw") != -1)) {
					lang = "tw";
				}
				else if(dataName != null && (dataName.indexOf("/jp/") != -1 || dataName.indexOf("lang=jp") != -1)) {
					lang = "jp";
				}
				
				int prePageView = mainDAO.getPrePageView(param);
				int currentPageView = (int) Float.parseFloat(tempMap.get("col0").toString());
				float comparePageView = ((float) currentPageView - (float) prePageView) / (float) prePageView;
				int naturalPageView = (int) Float.parseFloat(tempMap.get("col1").toString());
				int visitCount = (int) Float.parseFloat(tempMap.get("col3").toString());
				int visitAvgTime = (int) Float.parseFloat(tempMap.get("col2").toString());
				
				Map<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("lang", lang);
				resultMap.put("dataName", dataName);
				resultMap.put("prePageView", prePageView);
				resultMap.put("currentPageView", currentPageView);
				resultMap.put("comparePageView", comparePageView);
				resultMap.put("naturalPageView", naturalPageView);
				resultMap.put("visitCount", visitCount);
				resultMap.put("visitAvgTime", visitAvgTime);
				
				result.add(resultMap);
			}
		}
		
		return result;
	}
}
