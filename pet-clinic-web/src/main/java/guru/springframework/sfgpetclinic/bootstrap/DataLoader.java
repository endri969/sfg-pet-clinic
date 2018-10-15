package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialitiesService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {


    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialitiesService specialitiesService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialitiesService specialitiesService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialitiesService = specialitiesService;
    }

    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();
        if(count==0)
            loadData();

    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");

        PetType petDogSaved = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Dog");

        PetType petCatSaved = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");

        Speciality savedRadiology = specialitiesService.save(radiology);
        Speciality savedSurgery = specialitiesService.save(surgery);
        Speciality savedDentistry = specialitiesService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Glenanne");
        owner1.setAddress("123 Riccc");
        owner1.setCity("Miami");
        owner1.setTelephone("12312312344");

        Pet michaelPet = new Pet();
        michaelPet.setPetType(petDogSaved);
        michaelPet.setOwner(owner1);
        michaelPet.setBirthDate(LocalDate.now());
        michaelPet.setName("Lara");

        owner1.getPets().add(michaelPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Valharra");
        owner2.setAddress("123 Riccc");
        owner2.setCity("Miami");
        owner2.setTelephone("12312312344");

        Pet fionasPet = new Pet();
        fionasPet.setPetType(petCatSaved);
        fionasPet.setOwner(owner2);
        fionasPet.setBirthDate(LocalDate.now());
        fionasPet.setName("Kiki");

        owner2.getPets().add(fionasPet);

        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Ace");
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Rick");
        vet2.setLastName("Sanches");
        vet2.getSpecialities().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded vets...");
    }
}
