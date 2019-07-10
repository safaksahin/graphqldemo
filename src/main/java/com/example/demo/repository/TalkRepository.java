package com.example.demo.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Talk;

@Repository
public interface TalkRepository extends JpaRepository<Talk, Long> {
	public Collection<Talk> findAllTalksBySpeakerId(long id);
	public Collection<Talk> findAllTalksByAttendeeId(long id); 
}
