package com.example.demo.note;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.ArrayList;


@RestController
public class NoteController {
    private NoteRepository repository;

    public NoteController(NoteRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/notes/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable(value = "id") Long noteId) {
        Note note = this.repository.findOne(noteId);
        if(note == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(note);
    }

    @PostMapping("/api/notes")
    public Note createNote(@Valid @RequestBody Note note) {
        return this.repository.save(note);
    }

    @PutMapping("/api/notes/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable(value = "id") Long noteId,
                                           @Valid @RequestBody Note noteDetails) {
        Note note = this.repository.findOne(noteId);
        if(note == null) {
            return ResponseEntity.notFound().build();
        }
        note.setBody(noteDetails.getBody());

        Note updatedNote = this.repository.save(note);
        return ResponseEntity.ok(updatedNote);
    }

    @DeleteMapping("/api/notes/{id}")
    public ResponseEntity<Note> deleteNote(@PathVariable(value = "id") Long noteId) {
        Note note = this.repository.findOne(noteId);
        if(note == null) {
            return ResponseEntity.notFound().build();
        }

        this.repository.delete(note);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/api/notes", method = RequestMethod.GET)
    @ResponseBody
    public Collection<Note> getNoteByQuery(@RequestParam(value = "query", required = false) String searchString) {

      if(searchString != null){       
        Collection<Note> collection = this.repository.findAll();
        return repository.findAll().stream()
                .filter(note -> note.getBody().contains(searchString))
                .collect(Collectors.toList());
      }
        
        Collection<Note> collection = this.repository.findAll();
        return collection;

    }
}