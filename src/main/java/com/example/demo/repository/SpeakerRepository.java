package com.example.demo.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Speaker;

@Repository
public interface SpeakerRepository extends JpaRepository<Speaker,Long> {
	public Collection<Speaker> findAllByTalkId(long id);
}
