package com.safetynet.safetynetalerts.service;

import java.net.URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.safetynet.safetynetalerts.dao.IFirestationRepository;
import com.safetynet.safetynetalerts.exception.MissingEntityException;
import com.safetynet.safetynetalerts.model.Firestation;

@Service
public class FirestationService {
	
	private static final Logger logger = LogManager.getLogger("FirestationService");
	
	@Autowired
	private IFirestationRepository iFirestationRepository;
	
	public Firestation saveFirestation(Firestation firestation) throws Exception {
		Firestation savedfirestation = new Firestation();
		if(firestation != null) {
			savedfirestation = iFirestationRepository.saveFirestation(firestation);

		} else {
			throw new MissingEntityException();
		}
		return savedfirestation;
	}
	
	public Firestation updateFirestation(Firestation firestation) throws Exception {
		Firestation savedfirestation = new Firestation();
		if(firestation != null) {
			savedfirestation = iFirestationRepository.updateFirestation(firestation);

		} else {
			throw new MissingEntityException();
		}
		return savedfirestation;
	}
	
	
	public Firestation deleteFirestation(Firestation firestation) throws Exception {
		Firestation savedfirestation = new Firestation();
		if(firestation != null) {
			savedfirestation = iFirestationRepository.deleteFirestation(firestation);

		} else {
			throw new MissingEntityException();
		}
		return savedfirestation;
	}
	
}
