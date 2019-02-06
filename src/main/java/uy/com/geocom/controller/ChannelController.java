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
import uy.com.geocom.model.Channel;
import uy.com.geocom.service.ChannelService;

@RestController
@Api(value="channels", description="Channels")
public class ChannelController {

	@Autowired
	private ChannelService channelService;
	
	@ApiOperation(value = "get all channels", response = Channel[].class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Success"),
	        @ApiResponse(code = 500, message = "Error Internal Server")
	})
	@GetMapping("/channels")
	public ResponseEntity<List<Channel>> findAllChannels() {
		List<Channel> channels = this.channelService.findAllChannels(); 
		return new ResponseEntity<List<Channel>>(channels, HttpStatus.OK);
	}
}
