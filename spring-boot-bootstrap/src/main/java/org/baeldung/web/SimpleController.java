package org.baeldung.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.baeldung.persistence.model.Mitglied;
import org.baeldung.persistence.repo.MitgliederRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SimpleController {

	@Autowired
	private MitgliederRepository mitgliederRepository;

	@Value("${spring.application.name}")
	String appName;

	@RequestMapping("/")
	public String homePage(Model model) {
		model.addAttribute("appName", appName);
		return "home";
	}

	@RequestMapping("/home2")
	public String homePage2(Model model) {
		model.addAttribute("appName", appName);
		return "home2";
	}

	@RequestMapping("/giosua")
	public String hellogiosua(Model model) {
		model.addAttribute("appName", new Date().toString());
		return "giosua";
	}

	@RequestMapping("/uhcbernost")
	public String hellocustumer(Model model) {
		model.addAttribute("today", new Date());
		model.addAttribute("mitglieder", mitgliederRepository.findAll());
		return "uhcbernost";
	}
	
	@GetMapping("/neuesMitglied")
	public String zeigeNeuesMitgliedForm(Model model) {
		model.addAttribute("mitglied", new Mitglied());
//		model.addAttribute("mitglieder", mitgliederRepository.findAll());
		return "neuesMitglied";
	}
	
	@PostMapping("/neuesMitglied")
	public String speichereNeuesMitglied(@ModelAttribute Mitglied mitglied) {
		mitgliederRepository.save(mitglied);
		return "uhcbernost";
	}
}