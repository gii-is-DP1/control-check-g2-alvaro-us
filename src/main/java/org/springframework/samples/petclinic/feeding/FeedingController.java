package org.springframework.samples.petclinic.feeding;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/feeding")
public class FeedingController {
    @Autowired
    private FeedingService fs;
    @Autowired
    private PetService ps;
    
    private final static String VIEW_CREATE_OR_UPDATE_FEEDING_FORM = "feedings/createOrUpdateFeedingForm";
    
    @GetMapping("/create")
    public String initCreactionForm(ModelMap modelMap) {
    	modelMap.addAttribute("feeding", new Feeding());
    	modelMap.addAttribute("feedingTypes", fs.getAllFeedingTypes());
    	modelMap.addAttribute("pets", ps.getAllPets());
    	return VIEW_CREATE_OR_UPDATE_FEEDING_FORM;
    	
    }
    
    @PostMapping("/create")
    public String processCreationForm(@Valid Feeding feeding, ModelMap modelMap, BindingResult bindingResult) throws UnfeasibleFeedingException {
    	if(bindingResult.hasErrors()) {
    		modelMap.addAttribute("feeding", feeding);
        	modelMap.addAttribute("feedingTypes", fs.getAllFeedingTypes());
        	modelMap.addAttribute("pets", ps.getAllPets());
        	return VIEW_CREATE_OR_UPDATE_FEEDING_FORM;
    	} else {
    		fs.save(feeding);
    		modelMap.addAttribute("message", "Feeding añadida correctamete");
    		return ("welcome");
    	}
    }
}
