package org.ab.service.generator;

public class InvoiceParticipant {
	public static class Builder{
		private String name;
		private String addressStreet;
		private String addressCity;
		private String nip;
		private String regon;
		private String phone;

		public InvoiceParticipant build(){
			return new InvoiceParticipant(name, addressStreet, addressCity, nip, regon, phone);
		}
		public Builder withAddressCity(final String addressCity){
			this.addressCity = addressCity;
			return this;
		}
		public Builder withAddressStreet(final String addressStreet){
			this.addressStreet = addressStreet;
			return this;
		}
		public Builder withName(final String name){
			this.name = name;
			return this;
		}
		public Builder withNip(final String nip){
			this.nip = nip;
			return this;
		}
		public Builder withPhone(final String phone){
			this.phone = phone;
			return this;
		}
		public Builder withRegon(final String regon){
			this.regon = regon;
			return this;
		}
	}
	private String name;
	private String addressStreet;
	private String addressCity;
	private String nip;
	private String regon;
	private String phone;

	public InvoiceParticipant(){}

	private InvoiceParticipant(final String name, final String addressStreet,
			final String addressCity, final String nip, final String regon, final String phone) {
		this.name = name;
		this.addressStreet = addressStreet;
		this.addressCity = addressCity;
		this.nip = nip;
		this.regon = regon;
		this.phone = phone;
	}
	public String getAddressCity() {
		return addressCity;
	}
	public String getAddressStreet() {
		return addressStreet;
	}
	public String getName() {
		return name;
	}
	public String getNip() {
		return nip;
	}
	public String getPhone() {
		return phone;
	}

	public String getRegon() {
		return regon;
	}

	public void setAddressCity(final String addressCity) {
		this.addressCity = addressCity;
	}

	public void setAddressStreet(final String addressStreet) {
		this.addressStreet = addressStreet;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setNip(final String nip) {
		this.nip = nip;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public void setRegon(final String regon) {
		this.regon = regon;
	}
}
