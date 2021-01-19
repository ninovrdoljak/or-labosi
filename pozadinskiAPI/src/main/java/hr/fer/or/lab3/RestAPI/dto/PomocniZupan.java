package hr.fer.or.lab3.RestAPI.dto;

import ioinformarics.oss.jackson.module.jsonld.annotation.JsonldId;
import ioinformarics.oss.jackson.module.jsonld.annotation.JsonldProperty;
import ioinformarics.oss.jackson.module.jsonld.annotation.JsonldType;


public class PomocniZupan {
	private String _type;
	private String name;
	
	public PomocniZupan(boolean t, String name) {
		if (t) {
			this._type = "Person";
			this.name = name;
		} else {
			this._type = "City";
			this.name = name;
		}
	}

	public String get_type() {
		return _type;
	}

	public void set_type(String _type) {
		this._type = _type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	
}
