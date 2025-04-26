package com.nt.repository;
import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
    Page<Event> findByNameContainingIgnoreCaseOrDate(String name, LocalDate date, Pageable pageable);
//    Page<Event> findByName(String name);
}