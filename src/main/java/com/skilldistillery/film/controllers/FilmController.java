package com.skilldistillery.film.controllers;

import java.util.ArrayList;
import java.util.List;

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
		
		List<Film> films = new ArrayList<>();
		films.add(filmDAO.getFilmById(filmId));
		
		mv.addObject("films", films);
		mv.setViewName("Film");
		
		return mv;
	}

	@RequestMapping(path="showFilm.do", method = RequestMethod.GET, params = "keyword")
	public ModelAndView showFilm(String keyword) {
		ModelAndView mv = new ModelAndView();
		
		List<Film> films = new ArrayList<>();
		films.addAll(filmDAO.findFilmByKeyword(keyword));
		
		mv.addObject("films", films);
		mv.setViewName("Film");
		return mv;
	}

	@RequestMapping(path="addFilm.do", method=RequestMethod.POST)
	public ModelAndView addFilm(String title, String description, String year) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("year", year);
		mv.setViewName("addFilm");
		return mv;
	}
}
