package hr.fer.or.lab3.RestAPI.dto;

public class SlikaPom {
	
	
	private Object source;
	
	public SlikaPom() {
		
	}
	
	public SlikaPom(Object source) {
		super();
		
		this.source = source;
	}
	
	public Object getSource() {
		return source;
	}
	public void setSource(Object source) {
		this.source = source;
	}

}
