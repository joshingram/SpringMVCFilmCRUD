package com.skilldistillery.film.data;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;
import com.skilldistillery.film.entities.Inventory;

public interface FilmDAO {
	
	public Film getFilmById(int filmId);
	
	public Actor findActorById(int actorId);
	
	public List<Actor> findActorsByFilmId(int filmId);
	
	public List<Film> findFilmByKeyword(String keyword);
	
	public String getLanguage(int langID);
	
	public String getCategory(int category);
	
	public List<Inventory> getInventory(int filmId);
	
	public Actor createActor(Actor actor);
	
	public Film createFilm(Film film);
	
	public boolean deleteFilm(int filmId);
	
	public Film updateFilm(int filmId, Film film);
	
	public List<Actor> searchActorByKeyword(String keyword);
	
	public boolean addActorToFilm(Actor actor);

}
