package com.instrumentation.monitoring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/monitoring")
public class MonitoringController {
	@Autowired
	private MonitoringService monitoringService;


	@GetMapping("/metrics")
	public String metricPage(Model model) {
		model.addAttribute("metrics", monitoringService.getMetrics());

		return "monitoring";
	}
}
