package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.data.FilmDAO;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {
	
	@Autowired
	private FilmDAO filmDAO;
	
	@RequestMapping(path = "showFilm.do", method = RequestMethod.GET, params = "filmId")
	
	public ModelAndView showFilm(Integer filmId) {
		ModelAndView mv = new ModelAndView();
		Film film = filmDAO.getFilmById(filmId);
		mv.addObject("films", film);
		mv.setViewName("Film");
		return mv;
		
	}

	
	/**
	 * This is a test to make sure git is finally fixed
	 */
}
