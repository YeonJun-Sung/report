package report.rest.main.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.log4j.Log4j;
import report.common.utils.ExcelUpload;
import report.rest.main.service.MainRestService;

@Log4j
@Controller
@RequestMapping("/rest/main/*")
public class MainRestController {
	
	@Inject
	private MainRestService mainRestService;

	/*
	 *	Method		: POST
	 *	Role		: excel 파일 upload
	 *	Table		: none
	 *	Parameter	: filename
	 *	Return		: ExcelVO List
	 */
	@PostMapping("/excelUpload/{key}/{date}")
	public ResponseEntity<List<Map<String, Object>>> excelUpload(@PathVariable String key, @PathVariable String date, MultipartHttpServletRequest request) throws Exception {
		try {
			MultipartFile file = null;
			Iterator<String> iterator = request.getFileNames();
			if (iterator.hasNext()) {
				file = request.getFile(iterator.next());
			}
			Map<String, Object> info = mainRestService.getSheetInfo(key);
			int sheetNum = Integer.parseInt(info.get("sheetNum").toString());
			int col = Integer.parseInt(info.get("col").toString());
			int colMax = col + 1;
			if(key != null && key.equals("LangZone"))
				colMax += 1;
			log.info("key / date > " + key + " / " + date);

			List<Map<String, Object>> list = ExcelUpload.uploadExcelFile(file, sheetNum, colMax);
			
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("key", key);
			param.put("date", date);
			param.put("list", list);
			param.put("col", col);
			mainRestService.saveData(param);
			
			return new ResponseEntity<List<Map<String, Object>>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Map<String, Object>>>(HttpStatus.BAD_REQUEST);
		}
	}
}