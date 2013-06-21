package com.app.meibo.statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.meibo.finace.expenditure.service.ExpenditureManager;
import com.app.permission.utils.web.JsonUtils;
import com.sqds.spring.annotation.GlobalAutowired;

/**
 * 收支统计
 * 
 * @author gefangshuai
 * @email gefangshuai@163.com
 * @createdata 2013-1-12 下午8:50:12
 */
@Controller
@RequestMapping("/statistics/expenditure/*.html")
@GlobalAutowired
public class ExpenditureStatisticsController {
	private ExpenditureManager expenditureManager;

	@RequestMapping
	public void index() {

	}

	/**
	 * 年统计
	 */
	@RequestMapping
	public void years() {

	}

	/**
	 * 月统计
	 * 
	 * @param year
	 * @param model
	 */
	@RequestMapping
	public void months(int year, ModelMap model) {
		model.addAttribute("year", year);
	}

	/**
	 * 统计年
	 * 
	 * @param response
	 * @param model
	 * @param year
	 */
	@RequestMapping
	public void getYearData(HttpServletResponse response, ModelMap model) {
		Map<String, Double> inData = expenditureManager.getYearData(1);
		Map<String, Double> outData = expenditureManager.getYearData(0);
		Set<String> set = inData.keySet();
		List<Double> inMoney = new ArrayList<Double>();
		List<Double> outMoney = new ArrayList<Double>();
		for (String string : set) {
			inMoney.add(inData.get(string));
			outMoney.add(outData.get(string));
		}
		model.put("year", set);
		model.put("ins", inMoney);
		model.put("out", outMoney);
		JsonUtils.writeJson(response, model);
	}

	/**
	 * 统计某年所有月份
	 * 
	 * @param response
	 * @param model
	 * @param year
	 */
	@RequestMapping
	public void getMonthData(HttpServletResponse response, ModelMap model, int year) {
		double[] inData = expenditureManager.getMonthData(year, 1);
		double[] outData = expenditureManager.getMonthData(year, 0);
		model.put("ins", inData);
		model.put("out", outData);
		JsonUtils.writeJson(response, model);
	}
}
