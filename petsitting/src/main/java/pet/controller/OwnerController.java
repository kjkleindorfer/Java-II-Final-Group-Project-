package pet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pet.beans.Owner;
import pet.beans.Pet;
import pet.repository.JobRepository;
import pet.repository.OwnerRepository;
import pet.repository.PetRepository;
import pet.repository.SitterRepository;

@Controller
public class OwnerController {

	@Autowired
	JobRepository jobRepo;
	
	@Autowired
	OwnerRepository ownerRepo;

	@Autowired
	SitterRepository sitterRepo;
	
	@Autowired
	PetRepository petRepo;
	
	@RequestMapping(value = "ownerHome")
	@GetMapping({ "/ownerHome" })
	public String viewOwner(Model model) {
		if (ownerRepo.findAll().isEmpty()) {
			return addNewOwner(model);
		}
		
		
		
		model.addAttribute("owners", ownerRepo.findAll());
		
		
		
		

		return "ownerHome";
	}

	@RequestMapping(value = "insertOwner")
	@GetMapping("/insertOwner")
	public String addNewOwner(Model model) {
		Owner owner = new Owner();

		model.addAttribute("newOwner", owner);
		return "insertOwner";
	}

	@GetMapping("/edit/1/{ownerId}")
	public String showUpdateOwner(@PathVariable("ownerId") long ownerId, Model model) {
		Owner owner = ownerRepo.findById(ownerId).orElse(null);

		System.out.println("OWNER TO EDIT: " + owner.toString());

		model.addAttribute("newOwner", owner);
		return "insertOwner";
	}

	@PostMapping("/update/1/{ownerId}")
	public String reviseOwner(Owner owner, Model model) {
	
		ownerRepo.save(owner);
		
		return "ownerHome";
	}

	@GetMapping("/delete/1/{ownerId}")
	public String deleteOwner(@PathVariable("ownerId") long ownerId, Model model) {
		Owner owner = ownerRepo.findById(ownerId).orElse(null);
		ownerRepo.delete(owner);

		return "ownerHome";
	}
}
