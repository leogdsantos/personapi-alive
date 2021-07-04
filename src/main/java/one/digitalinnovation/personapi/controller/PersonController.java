package one.digitalinnovation.personapi.controller;

import one.digitalinnovation.personapi.dto.reponse.MessageRsponseDTO;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

   private final PersonService personService;
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageRsponseDTO createPerson(@RequestBody @Validated PersonDTO personDTO){
        return personService.createPerson(personDTO);
    }
    @GetMapping
    public List<PersonDTO> listAll() {
       return personService.listAll();
    }
    @GetMapping ("/{id}")
    public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundException {
        return personService.findById(id);
    }
    @GetMapping ("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long Id) throws PersonNotFoundException {
        personService.delete(Id);
    }
    @PutMapping("/{id}")
    public MessageRsponseDTO update(@PathVariable Long Id, @RequestBody PersonDTO personDTO) throws PersonNotFoundException {
        return personService.updateById(Id, personDTO);
    }
}



