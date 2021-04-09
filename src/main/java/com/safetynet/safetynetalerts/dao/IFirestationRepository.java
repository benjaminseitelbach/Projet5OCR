package com.safetynet.safetynetalerts.dao;

import com.safetynet.safetynetalerts.model.Firestation;

public interface IFirestationRepository {

	public Firestation saveFirestation(Firestation firestation);
	
	public Firestation updateFirestation(Firestation firestation);
	
	public Firestation deleteFirestation(Firestation firestation);
}
