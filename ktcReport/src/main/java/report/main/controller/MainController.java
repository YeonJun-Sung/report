package report.main.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;
import report.main.service.MainService;

@Log4j
@Controller
@RequestMapping("/main/*")
public class MainController {
	
	@Inject
	private MainService mainService;
	
	@RequestMapping("/index")
	public void index(HttpServletRequest req, HttpSession session, Model model) {
		try {
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/excelUpload")
	public void excelUpload(HttpServletRequest req, HttpSession session, Model model) {
		try {
			List<Map<String, Object>> list = mainService.getKeyList();
			
			model.addAttribute("keyList", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/chart")
	public void chart(HttpServletRequest req, HttpSession session, Model model) {
		try {
			int idx = Integer.parseInt(req.getParameter("idx"));
			String date = req.getParameter("date");
			String preDate = req.getParameter("preDate");
			
			List<String> title = mainService.getTitleName(idx);
			List<Map<String, Object>> list = mainService.getChartData(idx, date, preDate);

			model.addAttribute("list", list);
			model.addAttribute("title", title);
			model.addAttribute("idx", idx);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}