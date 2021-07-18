package com.dasser.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dasser.api.entity.Status;
import com.dasser.api.repository.StatusRepository;
import com.dasser.api.service.StatusService;

@Service
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusRepository statusRepository;
	
	@Override
	public List<Status> listAll() {
		return statusRepository.findAll();
	}

}
