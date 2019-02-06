package uy.com.geocom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import uy.com.geocom.model.Local;
import uy.com.geocom.service.LocalService;

@RestController
@Api(value="locals", description="Locals")
public class LocalController {
	
	@Autowired
	private LocalService localService;
	
	@ApiOperation(value = "get all locals", response = Local[].class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Success"),
	        @ApiResponse(code = 500, message = "Error Internal Server")
	})
	@GetMapping("/locals")
	public ResponseEntity<List<Local>> findAllLocals() {
		List<Local> locals = this.localService.findAllLocals();
		return new ResponseEntity<List<Local>>(locals, HttpStatus.OK);
	}

}
