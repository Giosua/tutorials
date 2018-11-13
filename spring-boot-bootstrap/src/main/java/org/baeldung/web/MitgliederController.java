package org.baeldung.web;

import org.baeldung.persistence.model.Book;
import org.baeldung.persistence.model.Mitglied;
import org.baeldung.persistence.repo.BookRepository;
import org.baeldung.persistence.repo.MitgliederRepository;
import org.baeldung.web.exception.BookIdMismatchException;
import org.baeldung.web.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mitglieder")
public class MitgliederController {

    @Autowired
    private MitgliederRepository mitgliederRepository;

    @GetMapping
    public Iterable<Mitglied> findAll() {
        return mitgliederRepository.findAll();
    }

    @GetMapping("/title/{bookTitle}")
	public List<Mitglied> findByTitle(@PathVariable String vorname) {
        return mitgliederRepository.findByVorname(vorname);
    }

    @GetMapping("/{id}")
    public Mitglied findOne(@PathVariable long id) {
        return mitgliederRepository.findById(id)
          .orElseThrow(BookNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mitglied create(@RequestBody Mitglied mitglied) {
        Mitglied mitglied1 = mitgliederRepository.save(mitglied);
        return mitglied1;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        mitgliederRepository.findById(id)
          .orElseThrow(BookNotFoundException::new);
        mitgliederRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Mitglied updateBook(@RequestBody Mitglied book, @PathVariable long id) {
        if (book.getId() != id) {
            throw new BookIdMismatchException();
        }
        mitgliederRepository.findById(id)
          .orElseThrow(BookNotFoundException::new);
        return mitgliederRepository.save(book);
    }
}
