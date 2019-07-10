package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Speaker;
import com.example.demo.entity.Talk;
import com.example.demo.repository.SpeakerRepository;

import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class SpeakerService {

    @Autowired
    private SpeakerRepository speakerRepository;

    @GraphQLQuery
    public List<Speaker> findAllSpeaker() {
        return speakerRepository.findAll();
    }
    @GraphQLQuery
    public List<Speaker> findAllSpeakersForTalk(Talk talk) {
        List<Speaker> st = speakerRepository.findAllByTalkId(talk.getId()).stream().collect(Collectors.toList());

        return st.stream()
                .map(e -> speakerRepository.findById(e.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
    
    @GraphQLMutation
    public Speaker saveSpeaker(Speaker speaker) {
        return speakerRepository.save(speaker);
    }
}
