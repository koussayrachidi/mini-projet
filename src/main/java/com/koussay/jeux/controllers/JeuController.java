package com.koussay.jeux.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.koussay.jeux.entities.Jeu;
import com.koussay.jeux.service.JeuService;


@Controller
public class JeuController {
@Autowired
JeuService jeuService;
@RequestMapping("/showCreate")
public String showCreate()
{
return "createJeu";
}

@RequestMapping("/saveJeu")
public String saveJeu(@ModelAttribute("jeu") Jeu jeu,
                          @RequestParam("date") String date,
                          ModelMap modelMap) throws ParseException
{
//conversion de la date
 SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
 Date dateCreation = dateformat.parse(String.valueOf(date));
 jeu.setDateCreation(dateCreation);

Jeu saveJeu = jeuService.saveJeu(jeu);
String msg ="jeu enregistré avec Id "+saveJeu.getIdJeu();
modelMap.addAttribute("msg", msg);
return "createJeu";
}

@RequestMapping("/ListeJeux")
public String listeJeux(ModelMap modelMap,
		@RequestParam (name="page",defaultValue = "0") int page,
		@RequestParam (name="size", defaultValue = "3") int size)
{
	Page<Jeu> prods = jeuService.getAllJeuxParPage(page, size);
	modelMap.addAttribute("jeux", prods);
	modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
	modelMap.addAttribute("currentPage", page);
	modelMap.addAttribute("size", size);

	return "listeJeux";
}

@RequestMapping("/supprimerJeu")
public String supprimerJeu(@RequestParam("id") Long id,
 ModelMap modelMap,
 @RequestParam (name="page",defaultValue = "0") int page,
 @RequestParam (name="size", defaultValue = "2") int size)
{
	jeuService.deleteJeuById(id);
	Page<Jeu> prods = jeuService.getAllJeuxParPage(page,size);
	modelMap.addAttribute("jeux", prods);
	modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
	modelMap.addAttribute("currentPage", page);
	modelMap.addAttribute("size", size);
	return "listeJeux";

}

@RequestMapping("/modifierJeu")
public String editerJeu(@RequestParam("id") Long id,ModelMap modelMap)
{
Jeu p= jeuService.getJeu(id);
modelMap.addAttribute("jeu", p);
return "editerJeu";
}
@RequestMapping("/updateJeu")
public String updateJeu(@ModelAttribute("jeu") Jeu jeu,
@RequestParam("date") String date,
ModelMap modelMap) throws ParseException
{
//conversion de la date
 SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
 Date dateCreation = dateformat.parse(String.valueOf(date));
 jeu.setDateCreation(dateCreation);

 jeuService.updateJeu(jeu);
 List<Jeu> prods = jeuService.getAllJeux();
 modelMap.addAttribute("jeux", prods);
return "listeJeux";
}


}
