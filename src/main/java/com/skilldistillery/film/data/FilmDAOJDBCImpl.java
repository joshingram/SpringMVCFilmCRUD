package com.skilldistillery.film.data;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;
import com.skilldistillery.film.entities.Inventory;

@Component
public class FilmDAOJDBCImpl implements FilmDAO {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private String user = "student";
	private String pass = "student";

	@Override
	public Film getFilmById(int filmId) {
		Film film = null;
		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, user, pass);

			String sql = "SELECT * FROM film WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);

			ResultSet filmResult = stmt.executeQuery();

			if (filmResult.next()) {
				film = new Film();
				film.setId(filmResult.getInt("id"));
				film.setTitle(filmResult.getString("title"));
				film.setDescription(filmResult.getString("description"));
				film.setYear(filmResult.getString("release_year"));
				film.setLanguageId(filmResult.getInt("language_id"));
				film.setRentalDuration(filmResult.getInt("rental_duration"));
				film.setRentalRate(filmResult.getDouble("rental_rate"));
				film.setLength(filmResult.getInt("length"));
				film.setReplacementCost(filmResult.getDouble("length"));
				film.setRating(filmResult.getString("rating"));
				film.setSpecialFeatures(filmResult.getString("special_features"));
				film.setPlainLanguage(getLanguage(filmResult.getInt("language_id")));
				film.setActors(findActorsByFilmId(filmResult.getInt("id")));
				film.setCategory(getCategory(filmResult.getInt("id")));
			}
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT * FROM actor WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);

			ResultSet actorResult = stmt.executeQuery();

			if (actorResult.next()) {
				actor = new Actor();
				actor.setId(actorResult.getInt("id"));
				actor.setFirstName(actorResult.getString("first_name"));
				actor.setLastName(actorResult.getString("last_name"));
			}
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT actor.id, actor.first_name, actor.last_name FROM actor JOIN film_actor ON actor.id = film_actor.actor_id JOIN film ON film.id = film_actor.film_id  WHERE film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Actor actor = new Actor();
				actor.setId(rs.getInt("id"));
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
				actors.add(actor);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}

	@Override
	public List<Actor> searchActorByKeyword(String keyword) {
		List<Actor> actors = new ArrayList<>();
		
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT id, first_name, last_name FROM actor WHERE first_name LIKE ? OR last_name LIKE ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");

			ResultSet actorsResult = stmt.executeQuery();

			while (actorsResult.next()) {
				Actor actor = new Actor(); 
			
				actor.setId(actorsResult.getInt("id"));
				actor.setFirstName(actorsResult.getString("first_name"));
				actor.setLastName(actorsResult.getString("last_name"));
				actors.add(actor);
			}
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}

	@Override
	public List<Film> findFilmByKeyword(String keyword) {
		List<Film> films = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT * FROM film WHERE title LIKE ? OR description LIKE ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Film film = new Film(); 
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setYear(rs.getString("release_year"));
				film.setLanguageId(rs.getInt("language_id"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setRentalRate(rs.getDouble("rental_rate"));
				film.setLength(rs.getInt("length"));
				film.setReplacementCost(rs.getDouble("length"));
				film.setRating(rs.getString("rating"));
				film.setSpecialFeatures(rs.getString("special_features"));
				film.setPlainLanguage(getLanguage(rs.getInt("language_id")));
				film.setActors(findActorsByFilmId(rs.getInt("id")));
				film.setCategory(getCategory(rs.getInt("id")));
				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	@Override
	public String getLanguage(int langID) {
		String plainLanguage = null;
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT name FROM language WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, langID);

			ResultSet langResult = stmt.executeQuery();

			if (langResult.next()) {
				plainLanguage = (langResult.getString("name"));

			}
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return plainLanguage;
	}

	@Override
	public String getCategory(int category) {
		String cat = null;
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT category.name FROM category JOIN film_category ON category.id = film_category.category_id JOIN film ON film.id = film_category.film_id WHERE film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, category);

			ResultSet catResult = stmt.executeQuery();

			if (catResult.next()) {
				cat = (catResult.getString("name"));

			}
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cat;
	}

	@Override
	public List<Inventory> getInventory(int filmId) {
		List<Inventory> inventories = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			//String sql = "SELECT inventory_item.id, inventory_item.film_id, inventory_item.store_id, inventory_item.media_condition, customer.first_name, customer.last_name, address.address, address.city, address.state_province, address.postal_code, address.country_code FROM inventory_item JOIN rental ON rental.id = inventory_item.id JOIN customer ON rental.customer_id = customer.id JOIN address ON customer.address_id = address.id WHERE film_id = ?";
			String sql = "SELECT * FROM inventory_item WHERE film_id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Inventory inventory = new Inventory();
				inventory.setId(rs.getInt("id"));
				inventory.setFilmId(rs.getInt("film_id"));
				inventory.setStoreId(rs.getInt("store_id"));
				inventory.setMediaCondition(rs.getString("media_condition"));
//				inventory.setCustomerFirstName(rs.getString("customer.first_name"));
//				inventory.setCustomerLastName(rs.getString("customer.last_name"));
//				inventory.setAddress(rs.getString("address.address"));
//				inventory.setCity(rs.getString("address.city"));
//				inventory.setStateProvince(rs.getString("address.state_province"));
//				inventory.setPostalCode(rs.getInt("address.postal_code"));
//				inventory.setCountryCode(rs.getString("address.country_code"));

				inventories.add(inventory);
			}
			rs.close();
			stmt.close();
			
			for(Inventory i : inventories) {
				sql = "SELECT rent.inventory_id, cust.first_name, cust.last_name, addr.address, addr.city, addr.state_province, addr.postal_code, addr.country_code, rent.return_date FROM rental rent JOIN customer cust ON rent.customer_id = cust.id JOIN address addr ON cust.address_id = addr.id WHERE rent.inventory_id = ? AND rent.return_date IS NULL;";
				stmt = conn.prepareStatement(sql);
				
				stmt.setInt(1, i.getId());
				
				rs = stmt.executeQuery();
				while(rs.next()) {
					i.setCustomerFirstName(rs.getString("cust.first_name"));
					i.setCustomerLastName(rs.getString("cust.last_name"));
					i.setAddress(rs.getString("addr.address"));
					i.setCity(rs.getString("addr.city"));
					i.setStateProvince(rs.getString("addr.state_province"));
					i.setPostalCode(rs.getInt("addr.postal_code"));
					i.setCountryCode(rs.getString("addr.country_code"));
				}
				
				rs.close();
				stmt.close();
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inventories;
	}

	@Override
	public Actor createActor(Actor actor) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);
			String sql = "INSERT INTO actor (first_name, last_name) VALUES (?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, actor.getFirstName());
			stmt.setString(2, actor.getLastName());

			int updateCount = stmt.executeUpdate();

			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();

				if (keys.next()) {
					int newActorId = keys.getInt(1);

					actor.setId(newActorId);

					if (actor.getFilms() != null && actor.getFilms().size() > 0) {

						sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";

						stmt = conn.prepareStatement(sql);

						for (Film film : actor.getFilms()) {
							stmt.setInt(1, film.getId());
							stmt.setInt(2, newActorId);
							updateCount = stmt.executeUpdate();
						}
					}
				}
			} else {
				actor = null;
			}
			conn.commit(); 
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error inserting actor " + actor);
		}
		return actor;

	}
	@Override
	public boolean saveActor(Actor actor) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false); 
			String sql = "UPDATE actor SET first_name=?, last_name=? WHERE id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, actor.getFirstName());
			stmt.setString(2, actor.getLastName());
			stmt.setInt(3, actor.getId());

			int updateCount = stmt.executeUpdate();

			if (updateCount == 1) {
				// Replace actor's film list
				sql = "DELETE FROM film_actor WHERE actor_id = ?";

				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, actor.getId());
				updateCount = stmt.executeUpdate();

				sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";
				stmt = conn.prepareStatement(sql);

				for (Film film : actor.getFilms()) {
					stmt.setInt(1, film.getId());
					stmt.setInt(2, actor.getId());
					updateCount = stmt.executeUpdate();
				}
				conn.commit(); 
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} 
				catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;
	}
	@Override
	public boolean deleteActor(int actorId) {
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false); 
			String sql = "DELETE FROM film_actor WHERE actor_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			
			int updateCount = stmt.executeUpdate();

			sql = "DELETE FROM actor WHERE id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			updateCount = stmt.executeUpdate();

			conn.commit(); 
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;
	}
	
	//Overloaded method takes in Actor ID and Film ID
	@Override
	public boolean addActorToFilm(int actorId, int filmId) {
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false); 
			String sql = "INSERT INTO film_actor (actor_id, film_id) VALUES (?,?);";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			stmt.setInt(2, filmId);
			
			int updateCount = stmt.executeUpdate();

			conn.commit(); 
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;
	}
	
	//Overloaded method takes in Actor ID and Film Title
	@Override
	public boolean addActorToFilm(int actorId, String filmTitle) {
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false); 
			String sql = "INSERT INTO film_actor (actor_id, film_id) VALUES (8,(SELECT film_id FROM film WHERE title = \"?\"))";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			stmt.setString(2, filmTitle);
			
			int updateCount = stmt.executeUpdate();

			conn.commit(); 
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;
	}
	

	@Override
	public Film createFilm(Film film) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);
			String sql = "INSERT INTO film (title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features) VALUES (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, film.getTitle());

			if (film.getDescription() != null) {
				stmt.setString(2, film.getDescription());
			} else {
				stmt.setString(2, "");
			}

			try {
				stmt.setInt(3, Integer.parseInt(film.getYear()));
			} catch(NumberFormatException e) {
				stmt.setInt(3, 0);
			}

			if (film.getLanguageId() != 0) {
				stmt.setInt(4, film.getLanguageId());
			} else {
				stmt.setInt(4, 1);
			}

			if (film.getRentalDuration() != 0) {
				stmt.setInt(5, film.getRentalDuration());
			} else {
				stmt.setInt(5, 1);
			}

			if (film.getRentalRate() != 0) {
				stmt.setDouble(6, film.getRentalRate());
			} else {
				stmt.setDouble(6, 1);
			}

			if (film.getLength() != 0) {
				stmt.setInt(7, film.getLength());
			} else {
				stmt.setInt(7, 1);
			}

			if (film.getReplacementCost() != 0) {
				stmt.setDouble(8, film.getReplacementCost());
			} else {
				stmt.setInt(8, 1);
			}

			if (film.getRating() != null) {
				stmt.setString(9, film.getRating());
			} else {
				stmt.setString(9, "");
			}

			if (film.getSpecialFeatures() != null) {
				stmt.setString(10, film.getSpecialFeatures());
			} else {
				stmt.setString(10, "");
			}

			int updateCount = stmt.executeUpdate();

			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();

				if (keys.next()) {
					int newFilmId = keys.getInt(1);

					film.setId(newFilmId);

				} else {
					film = null;
				}
			}
			conn.commit();
			conn.close();
		} catch (SQLException sqle) {
			film = null;
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			//throw new RuntimeException("Error inserting actor " + film);
		}
		return film;
	}

	@Override
	public Film updateFilm(Film film) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false); 
			String sql = "UPDATE film SET title=?, description=?, release_year=?, language_id=?, rental_duration=?, rental_rate=?, length=?, replacement_cost=?, rating=?, special_features=? WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, film.getTitle());

			if (film.getDescription() != null) {
				stmt.setString(2, film.getDescription());
			} else {
				stmt.setString(2, "");
			}

			try {
				stmt.setInt(3, Integer.parseInt(film.getYear()));
			} catch(NumberFormatException e) {
				stmt.setInt(3, 0);
			}

			if (film.getLanguageId() != 0) {
				stmt.setInt(4, film.getLanguageId());
			} else {
				stmt.setInt(4, 1);
			}

			if (film.getRentalDuration() != 0) {
				stmt.setInt(5, film.getRentalDuration());
			} else {
				stmt.setInt(5, 3);
			}

			if (film.getRentalRate() != 0) {
				stmt.setDouble(6, film.getRentalRate());
			} else {
				stmt.setDouble(6, 4.99);
			}

			if (film.getLength() != 0) {
				stmt.setInt(7, film.getLength());
			} else {
				stmt.setInt(7, 1);
			}

			if (film.getReplacementCost() != 0) {
				stmt.setDouble(8, film.getReplacementCost());
			} else {
				stmt.setDouble(8, 19.99);
			}

			if (film.getRating() != null) {
				stmt.setString(9, film.getRating());
			} else {
				stmt.setString(9, "");
			}

			if (film.getSpecialFeatures() != null) {
				stmt.setString(10, film.getSpecialFeatures());
			} else {
				stmt.setString(10, "");
			}

			stmt.setInt(11, film.getId());

			conn.commit(); // COMMIT TRANSACTION
			conn.close();
		} catch (SQLException sqle) {
			film = null;
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			//throw new RuntimeException("Error inserting actor " + film);
		}
		return film;
	}

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Error loading MySQL Driver");
			throw new RuntimeException("Unable to load MySQL Driver class");
		}
	}

	@Override
	public boolean deleteFilm(int filmId) {
		boolean deleted = false;
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "DELETE FROM film WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			
			int updateCount = stmt.executeUpdate();
			
			if (updateCount == 1) {
				deleted = true;
			}
			stmt.close();
			conn.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return deleted;
	}

}
