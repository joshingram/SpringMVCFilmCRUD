package com.skilldistillery.film.entities;

import java.util.Objects;

public class Inventory {

		private int id;
		private int filmId;
		private int storeId;
		private String mediaCondition;
		private String customerFirstName;
		private String customerLastName;
		private String address;
		private String city;
		private String stateProvince;
		private int postalCode;
		private String countryCode;
		

		public Inventory() {
		}

		
		
		public Inventory(int id, int filmId, int storeId, String mediaCondition, String customerFirstName,
				String customerLastName, String address, String city, String stateProvince, int postalCode,
				String countryCode) {
			super();
			this.id = id;
			this.filmId = filmId;
			this.storeId = storeId;
			this.mediaCondition = mediaCondition;
			this.customerFirstName = customerFirstName;
			this.customerLastName = customerLastName;
			this.address = address;
			this.city = city;
			this.stateProvince = stateProvince;
			this.postalCode = postalCode;
			this.countryCode = countryCode;
		}



		@Override
		public String toString() {
			return "Inventory ID#: " + id + ", at store#: " + storeId + ", condition: "
					+ mediaCondition;
		}

		public int getId() {
			return id;
		}

		public String getCustomerFirstName() {
			return customerFirstName;
		}



		public void setCustomerFirstName(String customerFirstName) {
			this.customerFirstName = customerFirstName;
		}



		public String getCustomerLastName() {
			return customerLastName;
		}



		public void setCustomerLastName(String customerLastName) {
			this.customerLastName = customerLastName;
		}



		public String getAddress() {
			return address;
		}



		public void setAddress(String address) {
			this.address = address;
		}



		public String getCity() {
			return city;
		}



		public void setCity(String city) {
			this.city = city;
		}



		public String getStateProvince() {
			return stateProvince;
		}



		public void setStateProvince(String stateProvince) {
			this.stateProvince = stateProvince;
		}



		public int getPostalCode() {
			return postalCode;
		}



		public void setPostalCode(int postalCode) {
			this.postalCode = postalCode;
		}



		public String getCountryCode() {
			return countryCode;
		}



		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}



		public void setId(int id) {
			this.id = id;
		}

		public int getFilmId() {
			return filmId;
		}

		public void setFilmId(int filmId) {
			this.filmId = filmId;
		}

		public int getStoreId() {
			return storeId;
		}

		public void setStoreId(int storeId) {
			this.storeId = storeId;
		}

		public String getMediaCondition() {
			return mediaCondition;
		}

		public void setMediaCondition(String mediaCondition) {
			this.mediaCondition = mediaCondition;
		}

		@Override
		public int hashCode() {
			return Objects.hash(filmId, id, mediaCondition, storeId);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Inventory other = (Inventory) obj;
			return filmId == other.filmId && id == other.id && Objects.equals(mediaCondition, other.mediaCondition)
					&& storeId == other.storeId;
		}
		
		//test to get git to push

	}

