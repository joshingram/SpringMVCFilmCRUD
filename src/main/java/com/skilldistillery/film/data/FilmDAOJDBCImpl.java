package com.skilldistillery.film.data;

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
			// ...proof is left to reader

			String sql = "SELECT * FROM film WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);

			ResultSet filmResult = stmt.executeQuery();

			if (filmResult.next()) {
				film = new Film(); // Create the object
				// Here is our mapping of query columns to our object fields:
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
//			film.setPlainLanguage(getLanguage(filmResult.getInt("language_id")));
//			film.setActors(findActorsByFilmId(filmResult.getInt("id")));
//			film.setCategory(getCategory(filmResult.getInt("id")));
			}
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		// ...proof is left to reader

		String sql = "SELECT * FROM actor WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);

		ResultSet actorResult = stmt.executeQuery();

		if (actorResult.next()) {
			actor = new Actor(); // Create the object
			// Here is our mapping of query columns to our object fields:
			actor.setId(actorResult.getInt("id"));
			actor.setFirstName(actorResult.getString("first_name"));
			actor.setLastName(actorResult.getString("last_name"));
		}
		stmt.close();
		conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
				Film film = new Film(); // Create the object
				// Here is our mapping of query columns to our object fields:
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
	public String getLanguage(int langID){
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
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cat;
	}
	
	@Override
	public List<Inventory> getInventory(int filmId) {
		List<Inventory> inventories = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT inventory_item.id, inventory_item.film_id, inventory_item.store_id, inventory_item.media_condition, customer.first_name, customer.last_name, address.address, address.city, address.state_province, address.postal_code, address.country_code FROM inventory_item JOIN rental ON rental.id = inventory_item.id JOIN customer ON rental.customer_id = customer.id JOIN address ON customer.address_id = address.id WHERE film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Inventory inventory = new Inventory();
				inventory.setId(rs.getInt("inventory_item.id"));
				inventory.setFilmId(rs.getInt("inventory_item.film_id"));
				inventory.setStoreId(rs.getInt("inventory_item.store_id"));
				inventory.setMediaCondition(rs.getString("inventory_item.media_condition"));
				inventory.setCustomerFirstName(rs.getString("customer.first_name"));
				inventory.setCustomerLastName(rs.getString("customer.last_name"));
				inventory.setAddress(rs.getString("address.address"));
				inventory.setCity(rs.getString("address.city"));
				inventory.setStateProvince(rs.getString("address.state_province"));
				inventory.setPostalCode(rs.getInt("address.postal_code"));
				inventory.setCountryCode(rs.getString("address.country_code"));
				
				inventories.add(inventory);
			}
			rs.close();
			stmt.close();
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

			conn.setAutoCommit(false); // START TRANSACTION

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
			conn.commit(); // COMMIT TRANSACTION
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
	
	public boolean saveActor(Actor actor) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);

			conn.setAutoCommit(false); // START TRANSACTION

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
				conn.commit(); // COMMIT TRANSACTION
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} // ROLLBACK TRANSACTION ON ERROR
				catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;
	}
	
	public boolean deleteActor(Actor actor) {
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION

			String sql = "DELETE FROM film_actor WHERE actor_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actor.getId());
			int updateCount = stmt.executeUpdate();

			sql = "DELETE FROM actor WHERE id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actor.getId());
			updateCount = stmt.executeUpdate();

			conn.commit(); // COMMIT TRANSACTION
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

			conn.setAutoCommit(false); // START TRANSACTION

			String sql = "INSERT INTO film (title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features) VALUES (?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, film.getTitle());
			
			if (film.getDescription() != null) {
				stmt.setString(2, film.getDescription());
			}else { stmt.setString(2, null);}
			
			if (film.getYear() != null) {
			stmt.setString(3, film.getYear());
			}else { stmt.setString(3, null);}
			
			if (film.getLanguageId() != 0) {
			stmt.setInt(4, film.getLanguageId());
			}else { stmt.setInt(4, 1);}
			
			if (film.getRentalDuration() != 0) {
			stmt.setInt(5, film.getRentalDuration());
			}else { stmt.setInt(5, 1);}
			
			if (film.getRentalRate() != 0) {
			stmt.setDouble(6, film.getRentalRate());
			}else { stmt.setDouble(6, 1);}
			
			if (film.getLength() != 0) {
			stmt.setInt(7, film.getLength());
			}else { stmt.setInt(7, 1);}
			
			if (film.getReplacementCost() != 0) {
			stmt.setDouble(8, film.getReplacementCost());
			}else { stmt.setInt(8, 1);}
			
			if (film.getRating() != null) {
			stmt.setString(9, film.getRating());
			}else { stmt.setString(9, null);}
			
			if (film.getSpecialFeatures() != null) {
			stmt.setString(10, film.getSpecialFeatures());
			}else { stmt.setString(10, null);}
			
			

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
				conn.commit(); // COMMIT TRANSACTION
				conn.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				if (conn != null) {
					try {
						conn.rollback();
					} catch (SQLException sqle2) {
						System.err.println("Error trying to rollback");
					}
				}
				throw new RuntimeException("Error inserting actor " + film);
			}
			return film;
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

	@Override
	public Film updateFilm(int filmId, Film film) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);

			conn.setAutoCommit(false); // START TRANSACTION

			String sql = "UPDATE film SET title=?, description=?, release_year=?, language_id=?, rental_duration=?, rental_rate=?, length=?, replacement_cost=?, rating=?, special_features=? WHERE id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, film.getTitle());
			
			if (film.getDescription() != null) {
				stmt.setString(2, film.getDescription());
			}else { stmt.setString(2, null);}
			
			if (film.getYear() != null) {
			stmt.setString(3, film.getYear());
			}else { stmt.setString(3, null);}
			
			if (film.getLanguageId() != 0) {
			stmt.setInt(4, film.getLanguageId());
			}else { stmt.setInt(4, 1);}
			
			if (film.getRentalDuration() != 0) {
			stmt.setInt(5, film.getRentalDuration());
			}else { stmt.setInt(5, 1);}
			
			if (film.getRentalRate() != 0) {
			stmt.setDouble(6, film.getRentalRate());
			}else { stmt.setDouble(6, 1);}
			
			if (film.getLength() != 0) {
			stmt.setInt(7, film.getLength());
			}else { stmt.setInt(7, 1);}
			
			if (film.getReplacementCost() != 0) {
			stmt.setDouble(8, film.getReplacementCost());
			}else { stmt.setInt(8, 1);}
			
			if (film.getRating() != null) {
			stmt.setString(9, film.getRating());
			}else { stmt.setString(9, null);}
			
			if (film.getSpecialFeatures() != null) {
			stmt.setString(10, film.getSpecialFeatures());
			}else { stmt.setString(10, null);}
			
			stmt.setInt(11,film.getId());
			
			

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
				conn.commit(); // COMMIT TRANSACTION
				conn.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				if (conn != null) {
					try {
						conn.rollback();
					} catch (SQLException sqle2) {
						System.err.println("Error trying to rollback");
					}
				}
				throw new RuntimeException("Error inserting actor " + film);
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

}
