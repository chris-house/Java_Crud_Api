package com.example.demo.note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
interface NoteRepository extends JpaRepository<Note, Long> {
}