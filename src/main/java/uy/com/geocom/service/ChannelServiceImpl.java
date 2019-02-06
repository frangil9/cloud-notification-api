package uy.com.geocom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uy.com.geocom.model.Channel;
import uy.com.geocom.repository.ChannelRepository;

@Service
public class ChannelServiceImpl implements ChannelService {

	@Autowired
	private ChannelRepository channelRepository;
	
	@Override
	public List<Channel> findAllChannels() {
		return this.channelRepository.findAll();
	}

	
}
