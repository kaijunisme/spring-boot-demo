package com.example.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.District;
import com.example.demo.enums.HttpState;
import com.example.demo.service.DistrictService;
import com.example.demo.utils.JsonResult;

@RestController
@RequestMapping(value = "/api/district")
public class DistrictRestController {

	@Autowired
	private DistrictService districtService;
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public JsonResult<List<District>> list(@RequestParam(value = "c_id") String c_id) {
		
		return new JsonResult<List<District>>(HttpState.SUCCESS.getState(), districtService.findDistrictByC_id(c_id));
	}
	
}
