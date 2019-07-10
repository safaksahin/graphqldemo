package com.example.demo.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Attendee;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
	public Collection<Attendee> findAllByTalkId(long id);
}
