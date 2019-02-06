package uy.com.geocom.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import uy.com.geocom.exception.ErrorMessage;
import uy.com.geocom.dto.MetadataNotificationDTO;
import uy.com.geocom.dto.MetadataNotificationEntityDTO;
import uy.com.geocom.dto.NotificationDTO;
import uy.com.geocom.dto.NotificationFilterDTO;
import uy.com.geocom.dto.NotificationResponseDTO;
import uy.com.geocom.exception.GeocomRESTException;
import uy.com.geocom.model.Notification;
import uy.com.geocom.service.NotificationService;

@RestController
@Api(value="notifications", description="Notifications")
public class NotificationController {

	@Autowired
	NotificationService notificationService;
	
	
	@GetMapping("/notifications")
	@ApiOperation(value = "get all notifications", response = MetadataNotificationEntityDTO.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Success"),
	        @ApiResponse(code = 500, message = "Error Internal Server")
	})
	public ResponseEntity<MetadataNotificationEntityDTO> findNotificationWithQuery(@RequestParam("page") Integer page, 
			@RequestParam("max") Integer max, @RequestParam("order") String order, @RequestParam("field") String field) {
		MetadataNotificationEntityDTO notifications = this.notificationService.findNotificationWithQuery(page, max, order, field);
		return new ResponseEntity<MetadataNotificationEntityDTO>(notifications, HttpStatus.OK);
	}
	
	@PostMapping("/notifications/filter")
	@ApiOperation(value = "filter notifications by locals and channels", response = MetadataNotificationDTO.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Success"),
	        @ApiResponse(code = 500, message = "Error Internal Server")
	})
	public ResponseEntity<MetadataNotificationDTO> findAll(@RequestBody NotificationFilterDTO filter) {
		MetadataNotificationDTO notifications = this.notificationService.findAll(filter.getLocals(), filter.getChannels(), filter.getEnabled(), filter.getPage(), filter.getMax(), filter.getOrder(), filter.getField());
		return new ResponseEntity<MetadataNotificationDTO>(notifications, HttpStatus.OK);
	}
	
	@GetMapping("/notifications/{id}")
	@ApiOperation(value = "get notification",response = NotificationResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Not Authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Error Internal Server")
    })
	public ResponseEntity<NotificationResponseDTO> findById(@PathVariable("id") UUID id){
		NotificationResponseDTO notification = null;
		try {
			notification = this.notificationService.findById(id);
		} catch (GeocomRESTException ex) {
			return new ResponseEntity(new ErrorMessage(ex.getStatus().value(), ex.getMessage()), ex.getStatus());
		}
		return new ResponseEntity<NotificationResponseDTO>(notification, HttpStatus.OK); 
	}
	
	@PostMapping("/notifications")
	@ApiOperation(value = "save notifications",response = Notification.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Not Authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Error Internal Server")
    })
	public ResponseEntity<Notification> save(@Valid @RequestBody NotificationDTO dto){
		Notification notificationSave = null;
		try {
			notificationSave = this.notificationService.save(dto);
		} catch (GeocomRESTException ex) {
			return new ResponseEntity(new ErrorMessage(ex.getStatus().value(), ex.getMessage()), ex.getStatus());
		}
		return new ResponseEntity<Notification>(notificationSave, HttpStatus.OK); 
	}
	
	@PutMapping("/notifications/{id}")
	@ApiOperation(value = "update notifications",response = Notification.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Not Authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Error Internal Server")
    })
	public ResponseEntity<Notification> update(@PathVariable("id") UUID id, @Valid @RequestBody NotificationDTO dto){
		Notification notificationSave = null;
		try {
			notificationSave = this.notificationService.update(id, dto);
		} catch (GeocomRESTException ex) {
			return new ResponseEntity(new ErrorMessage(ex.getStatus().value(), ex.getMessage()), ex.getStatus());
		}
		return new ResponseEntity<Notification>(notificationSave, HttpStatus.OK); 
	}
	
}
