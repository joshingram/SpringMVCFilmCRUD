package com.skilldistillery.film.entities;

import java.util.Objects;

public class Inventory {

		private int id;
		private int filmId;
		private int storeId;
		private String mediaCondition;

		public Inventory() {
		}

		public Inventory(int id, int filmId, int storeId, String mediaCondition) {
			super();
			this.id = id;
			this.filmId = filmId;
			this.storeId = storeId;
			this.mediaCondition = mediaCondition;
		}
		
		@Override
		public String toString() {
			return "Inventory ID#: " + id + ", at store#: " + storeId + ", condition: "
					+ mediaCondition;
		}

		public int getId() {
			return id;
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

