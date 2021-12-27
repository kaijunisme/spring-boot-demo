package com.example.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.County;
import com.example.demo.enums.HttpState;
import com.example.demo.service.CountyService;
import com.example.demo.utils.JsonResult;

@RestController
@RequestMapping(value = "/api/county")
public class CountyRestController {

	@Autowired
	private CountyService countyService;
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public JsonResult<List<County>> list() {
		
		return new JsonResult<List<County>>(HttpState.SUCCESS.getState(), countyService.findAllCounty());
	}
	
}
