package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Attendee;
import com.example.demo.entity.Speaker;
import com.example.demo.entity.Talk;
import com.example.demo.repository.AttendeeRepository;
import com.example.demo.repository.SpeakerRepository;
import com.example.demo.repository.TalkRepository;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class TalkService {

    @Resource
    private TalkRepository talkRepository;
    @Resource
    private SpeakerRepository speakerRepository;
    @Resource
    private AttendeeRepository attendeeRepository;

    @GraphQLQuery
    public List<Talk> findAllTalk() {
        return talkRepository.findAll();
    }

    @GraphQLQuery
    public List<Talk> findAllTalksBySpeaker(Speaker speaker) {
        List<Talk> st = talkRepository.findAllTalksBySpeakerId(speaker.getId()).stream().collect(Collectors.toList());

        return st.stream()
                .map(e -> talkRepository.findById(e.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    @GraphQLQuery
    public List<Talk> findAllTAlksByAttendee(Attendee attendee) {
        List<Talk> st = talkRepository.findAllTalksByAttendeeId(attendee.getId()).stream().collect(Collectors.toList());

        return st.stream()
                .map(e -> talkRepository.findById(e.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

    }
}
