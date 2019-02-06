package uy.com.geocom.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;

import uy.com.geocom.dto.MetadataNotificationDTO;
import uy.com.geocom.dto.MetadataNotificationEntityDTO;
import uy.com.geocom.dto.NotificationDTO;
import uy.com.geocom.dto.NotificationQueryDTO;
import uy.com.geocom.dto.NotificationQueueDTO;
import uy.com.geocom.dto.NotificationResponseDTO;
import uy.com.geocom.exception.GeocomRESTException;
import uy.com.geocom.model.Channel;
import uy.com.geocom.model.Local;
import uy.com.geocom.model.Notification;
import uy.com.geocom.model.NotificationChannel;
import uy.com.geocom.model.NotificationLocal;
import uy.com.geocom.repository.ChannelRepository;
import uy.com.geocom.repository.LocalRepository;
import uy.com.geocom.repository.NotificationChannelRepository;
import uy.com.geocom.repository.NotificationLocalRepository;
import uy.com.geocom.repository.NotificationRepository;
import uy.com.geocom.util.Constants;
import uy.com.geocom.util.Util;

@Service
public class NotificationServiceImpl implements NotificationService{

	@Autowired
	NotificationRepository notificationRepository;

	@Autowired
	NotificationLocalRepository notificationLocalRepository;

	@Autowired
	ChannelRepository channelRepository;

	@Autowired
	LocalRepository localRepository;

	@Autowired
	NotificationChannelRepository notificationChannelRepository;

	@Autowired
	JmsTemplate jmsTemplate;

	
	@Override
	public Notification save(NotificationDTO dto) throws GeocomRESTException{
		Notification notification = convertNotificationFromNotificationDTO(dto);
		List<Local> locals = new ArrayList<>();
		for (Integer id : dto.getLocalIds()) {
			Optional<Local> local = this.localRepository.findById(id);
			if (!local.isPresent()) {
				throw new GeocomRESTException(HttpStatus.NOT_FOUND, "No existe el local con id "+id);
			}
			locals.add(local.get());
		}
		List<Channel> channels = new ArrayList<>();
		for (Integer id : dto.getChannelIds()) {
			Optional<Channel> channel = this.channelRepository.findById(id);
			if (!channel.isPresent()) {
				throw new GeocomRESTException(HttpStatus.NOT_FOUND, "No existe el canal con id "+id);
			}
			channels.add(channel.get());
		}

		Notification notificationSave = this.notificationRepository.save(notification);
		if (locals != null || !locals.isEmpty()) {
			locals.stream().forEach(local -> {
				NotificationLocal notificationLocal = new NotificationLocal();
				notificationLocal.setLocal(local);
				notificationLocal.setNotification(notificationSave);
				this.notificationLocalRepository.save(notificationLocal);

			});
		}

		if (channels != null || !channels.isEmpty()) {
			channels.stream().forEach(channel -> {
				NotificationChannel notificationChannel = new NotificationChannel();
				notificationChannel.setChannel(channel);
				notificationChannel.setNotification(notificationSave);
				this.notificationChannelRepository.save(notificationChannel);

			});
		}
		this.sendNotificationJMS(locals, channels, notificationSave.getNotificationId(), dto);

		return notificationSave;
	}
	
	@Override
	public Notification update(UUID id, NotificationDTO dto) throws GeocomRESTException {	
		Optional<Notification> notification = this.notificationRepository.findById(id);
		if (!notification.isPresent()) {
			throw new GeocomRESTException(HttpStatus.NOT_FOUND, "No existe la notificación con id "+id);
		}
		notification.get().setTitle(dto.getTitle());
		notification.get().setMessage(dto.getMessage());
		notification.get().setEnabled(dto.isEnabled());
		notification.get().setRead(dto.isRead());
		notification.get().setValidity(Util.stringToDateTime(dto.getValidity()));
		notification.get().setUser(dto.getUser());
		notification.get().setUpdated(new Date());
		Notification notificationUpdate = this.notificationRepository.save(notification.get());

		List<Local> locals = new ArrayList<>();
		for (Integer localId : dto.getLocalIds()) {
			Optional<Local> local = this.localRepository.findById(localId);
			if (!local.isPresent()) {
				throw new GeocomRESTException(HttpStatus.NOT_FOUND, "No existe el local con id "+localId);
			}
			locals.add(local.get());
		}

		List<Channel> channels = new ArrayList<>();
		for (Integer channelId : dto.getChannelIds()) {
			Optional<Channel> channel = this.channelRepository.findById(channelId);
			if (!channel.isPresent()) {
				throw new GeocomRESTException(HttpStatus.NOT_FOUND, "No existe el canal con id "+channelId);
			}
			channels.add(channel.get());
		}
		List<NotificationLocal> notificationLocals = new ArrayList<>();
		for (Local local: locals) {
			NotificationLocal notificationLocal = null;
			notificationLocal = this.notificationLocalRepository.findByLocalAndNotification(local, notification.get());
			if (notificationLocal != null) {
				notificationLocals.add(notificationLocal);	
			} else {
				notificationLocal = new NotificationLocal();
				notificationLocal.setLocal(local);
				notificationLocal.setNotification(notification.get());
				notificationLocals.add(notificationLocal);
			}
		}
		List<NotificationChannel> notificationChannels = new ArrayList<>();
		for (Channel channel: channels) {
			NotificationChannel notificationChannel = null;
			notificationChannel = this.notificationChannelRepository.findByChannelAndNotification(channel, notification.get());
			if (notificationChannel != null) {
				notificationChannels.add(notificationChannel);
			} else {
				notificationChannel = new NotificationChannel();
				notificationChannel.setChannel(channel);
				notificationChannel.setNotification(notification.get());
				notificationChannels.add(notificationChannel);
			}
		}
		this.notificationChannelRepository.deleteAll(notification.get().getNotificationChannelList());
		this.notificationLocalRepository.deleteAll(notification.get().getNotificationLocalList());
		this.notificationLocalRepository.saveAll(notificationLocals);
		this.notificationChannelRepository.saveAll(notificationChannels);
		this.sendNotificationJMS(locals, channels, notificationUpdate.getNotificationId(), dto);
	
		return notificationUpdate;
	}

	@Override
	public MetadataNotificationDTO findAll(List<Integer> locals, List<Integer> channels, Boolean enabled, Integer page, Integer max, String order, String field) {		
		return this.notificationLocalRepository.findBylocalOrChannel(locals, channels, enabled, page, max, order, field);
	}

	public Notification convertNotificationFromNotificationDTO(NotificationDTO dto) throws GeocomRESTException{	
		Notification notification = new Notification();
		notification.setTitle(dto.getTitle());
		notification.setMessage(dto.getMessage());
		notification.setEnabled(dto.isEnabled());
		notification.setRead(dto.isRead());
		notification.setUser(dto.getUser());
		notification.setCreated(new Date());
		notification.setValidity(Util.stringToDateTime(dto.getValidity()));		
		return notification;

	}

	public NotificationQueueDTO convertNotificationQueueFromNotificationDTO(NotificationDTO dto) {
		NotificationQueueDTO notificationQueue = new NotificationQueueDTO();
		notificationQueue.setTitle(dto.getTitle());
		notificationQueue.setMessage(dto.getMessage());
		notificationQueue.setEnabled(dto.isEnabled());
		notificationQueue.setRead(dto.isRead());
		notificationQueue.setUser(dto.getUser());
		notificationQueue.setValidity(Util.stringToLocalDateTime(dto.getValidity()));	
		return notificationQueue;
	}
	
	public void sendNotificationJMS(List<Local> locals, List<Channel> channels, UUID id, NotificationDTO dto) throws GeocomRESTException {
		List<String> channelsName = channels.stream()
				.map(channel -> channel.getChannel())
				.collect(Collectors.toList());
		StringBuilder strChannels = new StringBuilder();
		channelsName.stream().forEach(channel -> {
			strChannels.append("|" + channel + "|");
		});

		List<Integer> localsNumber = locals.stream()
				.map(local -> local.getLocal())
				.collect(Collectors.toList());
		StringBuilder strLocals = new StringBuilder();
		localsNumber.stream().forEach(local -> {
			strLocals.append("|" + local.toString() + "|");
		});

		NotificationQueueDTO notificationQueue = convertNotificationQueueFromNotificationDTO(dto);
		notificationQueue.setId(id);
		notificationQueue.setChannels(channelsName);
		notificationQueue.setLocals(localsNumber);
		try {
			jmsTemplate.convertAndSend("notification.queue", notificationQueue, new MessagePostProcessor() {
				@Override
				public Message postProcessMessage(Message message) throws JMSException {
					message.setStringProperty("_channel", strChannels.toString());
					message.setStringProperty("_destination", strLocals.toString());
					return message;
				}
			});
		} catch (Exception e) {
			throw new GeocomRESTException(HttpStatus.INTERNAL_SERVER_ERROR, "No pudo conectar con provedor jms");
		}
	}

	@Override
	public NotificationResponseDTO findById(UUID id) throws GeocomRESTException {
		Notification notification = this.notificationRepository.findNotificationById(id);
		if (notification == null ) {
			throw new GeocomRESTException(HttpStatus.NOT_FOUND, "No existe la notificación con id "+id);
		}

		Set<NotificationLocal> notificationLocals = notification.getNotificationLocalList();
		List<Integer> localIds = notificationLocals.stream()
				.map(x -> x.getLocal().getLocalId())
				.collect(Collectors.toList());
		Set<NotificationChannel> notificationChannels = notification.getNotificationChannelList();
		List<Integer> channelIds = notificationChannels.stream()
				.map(x -> x.getChannel().getChannelId())
				.collect(Collectors.toList());

		NotificationResponseDTO response = new NotificationResponseDTO();
		response.setTitle(notification.getTitle());
		response.setMessage(notification.getMessage());
		response.setEnabled(notification.getEnabled());
		response.setRead(notification.getRead());
		response.setUser(notification.getUser());
		response.setValidity(Util.dateTimeToString(notification.getValidity()));
		response.setLocalIds(localIds);
		response.setChannelIds(channelIds);

		return response;
	}

	@Override
	public MetadataNotificationEntityDTO findNotificationWithQuery(Integer page, Integer max, String order, String field) {
		Page<Notification> notificationPage = this.notificationRepository.findNotificationWithQuery(PageRequest.of(page, max, Sort.by(order.equals(Constants.ORDER_ASC) ? Sort.Direction.ASC : Sort.Direction.DESC, field)));
		MetadataNotificationEntityDTO metadata = new MetadataNotificationEntityDTO(notificationPage.getTotalElements(), notificationPage.getContent());
		return metadata;
	}

}
