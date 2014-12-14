package org.ab.service.generator;

public class InvoiceParticipant {
	private final String name;
	private final String addressStreet;
	private final String addressCity;
	private final String nip;
	private final String regon;
	private final String phone;
	
	private InvoiceParticipant(String name, String addressStreet,
			String addressCity, String nip, String regon, String phone) {
		this.name = name;
		this.addressStreet = addressStreet;
		this.addressCity = addressCity;
		this.nip = nip;
		this.regon = regon;
		this.phone = phone;
	}
	
	public String getName() {
		return name;
	}
	public String getAddressStreet() {
		return addressStreet;
	}
	public String getAddressCity() {
		return addressCity;
	}
	public String getNip() {
		return nip;
	}
	public String getRegon() {
		return regon;
	}
	public String getPhone() {
		return phone;
	}
	
	public class Builder{
		private String name;
		private String addressStreet;
		private String addressCity;
		private String nip;
		private String regon;
		private String phone;
		
		public Builder withName(final String name){
			this.name = name;
			return this;
		}
		public Builder withAddressStreet(final String addressStreet){
			this.addressStreet = addressStreet;
			return this;
		}
		public Builder withAddressCity(final String addressCity){
			this.addressCity = addressCity;
			return this;
		}
		public Builder withNip(final String nip){
			this.nip = nip;
			return this;
		}
		public Builder withRegon(final String regon){
			this.regon = regon;
			return this;
		}
		public Builder withPhone(final String phone){
			this.phone = phone;
			return this;
		}
		public InvoiceParticipant build(){
			return new InvoiceParticipant(name, addressStreet, addressCity, nip, regon, phone);
		}
	}
}
