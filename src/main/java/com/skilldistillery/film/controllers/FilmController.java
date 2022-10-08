package com.skilldistillery.film.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.film.data.FilmDAO;
import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {
	
	private Map<String, Integer> langMap = new HashMap<>();
	private Map<String, Integer> catMap = new HashMap<>();
	
	//category map initialization
	{
		catMap.put("Action", 1);
		catMap.put("Animation", 2);
		catMap.put("Children", 3);
		catMap.put("Classics", 4);
		catMap.put("Comedy", 5);
		catMap.put("Documentary", 6);
		catMap.put("Drama", 7);
		catMap.put("Family", 8);
		catMap.put("Foreign", 9);
		catMap.put("Games", 10);
		catMap.put("Horror", 11);
		catMap.put("Music", 12);
		catMap.put("New", 13);
		catMap.put("Sci-Fi", 14);
		catMap.put("Sports", 15);
		catMap.put("Travel", 16);
	}
	//languageMap population
	{
		langMap.put("English", 1);
		langMap.put("Italian", 2);
		langMap.put("Japanese", 3);
		langMap.put("Mandarin", 4);
		langMap.put("French", 5);
		langMap.put("German", 6);
	}
	
	@Autowired
	private FilmDAO filmDAO;
	
	@RequestMapping(path = "showFilm.do", method = RequestMethod.GET, params = "filmId")
	public ModelAndView showFilm(Integer filmId) {
		ModelAndView mv = new ModelAndView();
		
		List<Film> films = new ArrayList<>();
		films.add(filmDAO.getFilmById(filmId));
		
		if(films.get(0) == null) {
			films.remove(0);
		}
		
		mv.addObject("filmSearch", true);
		mv.addObject("films", films);
		mv.setViewName("Film");
		
		return mv;
	}

	@RequestMapping(path="showFilm.do", method = RequestMethod.GET, params = "keyword")
	public ModelAndView showFilm(String keyword) {
		ModelAndView mv = new ModelAndView();
		
		List<Film> films = new ArrayList<>();
		films.addAll(filmDAO.findFilmByKeyword(keyword));
		
		mv.addObject("filmSearch", true);
		mv.addObject("films", films);
		mv.setViewName("Film");
		return mv;
	}

	@RequestMapping(path="addFilm.do", method=RequestMethod.POST)
	public ModelAndView addFilm(String title, String description, String year, String rentalDuration, String rentalRate, String length, String replacementCost, String rating, 
			String specialFeatures, String plainLanguage, String category, RedirectAttributes redir) {
		ModelAndView mv = new ModelAndView();
		List<Film> films = new ArrayList<>();
		
		int rentalDurationInt, lengthInt;
		double rentalRateDouble, replacementCostDouble;
		
		try {
			rentalDurationInt = Integer.parseInt(rentalDuration);
		} catch (NumberFormatException e) {
			rentalDurationInt = 3;
		}
		
		try {
			rentalRateDouble = Double.parseDouble(rentalRate);
		} catch (NumberFormatException e) {
			rentalRateDouble = 4.99;
		}
		
		try {
			lengthInt =  Integer.parseInt(length);
		} catch (NumberFormatException e) {
			lengthInt = 0;
		}
		
		try {
			replacementCostDouble = Double.parseDouble(replacementCost);
		} catch (NumberFormatException e) {
			replacementCostDouble = 19.99;
		}
		
		Film newFilm = new Film(title, description, year, rentalDurationInt, rentalRateDouble, lengthInt, replacementCostDouble, rating, specialFeatures, plainLanguage, category);
		
		newFilm.setLanguageId(langMap.get(plainLanguage));
		
		//add to database and give it an id
		films.add(newFilm = filmDAO.createFilm(newFilm));
		
		boolean filmAdded = false;
		if(newFilm != null) {
			filmAdded = true;
		}
		
		boolean filmSearch = false;
		
		redir.addFlashAttribute("films", films);
		redir.addFlashAttribute("filmAdded", filmAdded);
		redir.addFlashAttribute("filmSearch", filmSearch);
		
		mv.setViewName("redirect:filmAdded.do");
		
		return mv;
	}
	
	@RequestMapping(path="filmAdded.do", method=RequestMethod.GET)
	public ModelAndView filmAdded(ArrayList<Film> films, boolean filmAdded, boolean filmSearch) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Film");
		return mv;
	}
	
	@RequestMapping(path="updateFilm.do", method=RequestMethod.GET)
	public ModelAndView updateFilm(int filmId) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject(filmDAO.getFilmById(filmId));
		mv.setViewName("updateFilmForm");
		return mv;
	}
	
	@RequestMapping(path="updateFilm.do", method=RequestMethod.POST)
	public ModelAndView updateFilm(String title, String description, String year, String rentalDuration, String rentalRate, String length, String replacementCost, String rating, 
			String specialFeatures, String plainLanguage, String category, String filmId, RedirectAttributes redir) {
		ModelAndView mv = new ModelAndView();
		List<Film> films = new ArrayList<>();
		
		int rentalDurationInt, lengthInt;
		double rentalRateDouble, replacementCostDouble;
		
		try {
			rentalDurationInt = Integer.parseInt(rentalDuration);
		} catch (NumberFormatException e) {
			rentalDurationInt = 3;
		}
		
		try {
			rentalRateDouble = Double.parseDouble(rentalRate);
		} catch (NumberFormatException e) {
			rentalRateDouble = 4.99;
		}
		
		try {
			lengthInt =  Integer.parseInt(length);
		} catch (NumberFormatException e) {
			lengthInt = 0;
		}
		
		try {
			replacementCostDouble = Double.parseDouble(replacementCost);
		} catch (NumberFormatException e) {
			replacementCostDouble = 19.99;
		}
		
		Film newFilm = new Film(title, description, year, rentalDurationInt, rentalRateDouble, lengthInt, replacementCostDouble, rating, specialFeatures, plainLanguage, category);
		newFilm.setId(Integer.parseInt(filmId));
		newFilm.setLanguageId(langMap.get(plainLanguage));
		
		films.add(filmDAO.updateFilm(newFilm));
		

		boolean filmAdded = false;
		if(films.get(0) != null) {
			filmAdded = true;
		}
		
		redir.addFlashAttribute("films", films);
		redir.addFlashAttribute("filmAdded", filmAdded);
		redir.addFlashAttribute("filmSearch", false);
		
		mv.setViewName("redirect:filmUpdated.do");
		
		return mv;
	}
	
	@RequestMapping(path="filmUpdated.do", method=RequestMethod.GET)
	public ModelAndView filmUpdated(ArrayList<Film> films, boolean filmAdded, boolean successfullyDeleted) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Film");
		return mv;
	}
	
	@RequestMapping(path="removeFilm.do", method=RequestMethod.GET)
	public ModelAndView filmDelete(String filmId, RedirectAttributes redir) {
		ModelAndView mv = new ModelAndView();
		
		boolean removed = filmDAO.deleteFilm(Integer.parseInt(filmId));
		redir.addFlashAttribute("removed", removed);
		redir.addFlashAttribute("filmSearch", false);
		
		mv.setViewName("redirect:filmRemoved.do");
		return mv;
	}
	
	@RequestMapping(path="filmRemoved.do", method=RequestMethod.GET)
	public ModelAndView filmRemoved(boolean removed) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("filmRemoved");
		return mv;
	}
	
	//Overloaded for actor ID #
	@RequestMapping(path = "searchActorId.do", method = RequestMethod.GET, params = "actorId")
	public ModelAndView showActor(Integer actorId) {
		ModelAndView mv = new ModelAndView();
		
		List<Actor> actors = new ArrayList<>();
		actors.add(filmDAO.findActorById(actorId));
		
		mv.addObject("actors", actors);
		mv.setViewName("Actor");
		
		return mv;
	}
	//Overloaded for keyword
	@RequestMapping(path = "searchActorName.do", method = RequestMethod.GET, params = "keyword")
	public ModelAndView showActor(String keyword) {
		ModelAndView mv = new ModelAndView();
		
		List<Actor> actors = new ArrayList<>();
		actors = filmDAO.searchActorByKeyword(keyword);
		
		mv.addObject("actors", actors);
		mv.setViewName("Actor");
		
		return mv;
	}
	@RequestMapping(path = "createActor.do", method = RequestMethod.POST)
	public ModelAndView createActor(String firstName, String lastName) {
		ModelAndView mv = new ModelAndView();
		List<Actor> actors = new ArrayList<>(); 
		
		Actor actor = new Actor(firstName, lastName);

		actors.add(filmDAO.createActor(actor));
		
		mv.addObject("actors", actors);
		mv.setViewName("Actor");
		
		return mv;
	}
	@RequestMapping(path = "removeActor.do", method = RequestMethod.GET)
	public ModelAndView removeActor(String actorId){
		ModelAndView mv = new ModelAndView();
		
		boolean isDeleted = filmDAO.deleteActor(Integer.parseInt(actorId));
		
		mv.addObject("isDeleted", isDeleted);
		mv.setViewName("actorRemoved");
		
		return mv;
	}
	
}
