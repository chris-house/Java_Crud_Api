package com.example.demo.note;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class NoteCommandLineRunner implements CommandLineRunner {

    private final NoteRepository repository;

    public NoteCommandLineRunner(NoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        //defaulting one note
        Stream.of("Hi, I am a default Note!").forEach(name ->
                repository.save(new Note(name))
        );
        repository.findAll().forEach(System.out::println);
    }
}