package uy.com.geocom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uy.com.geocom.model.Local;
import uy.com.geocom.repository.LocalRepository;

@Service
public class LocalServiceImpl implements LocalService{

	@Autowired
	private LocalRepository localRepository;
	
	@Override
	public List<Local> findAllLocals() {
		return this.localRepository.findAll();
	}

}
