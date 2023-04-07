package com.practicing.springpractice.service.impl;

import com.practicing.springpractice.dto.ClubDto;
import com.practicing.springpractice.model.Club;
import com.practicing.springpractice.repository.ClubRepository;
import com.practicing.springpractice.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubServiceImpl implements ClubService {

    private ClubRepository clubRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<ClubDto> findAllClubs() {
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream().map(this::mapToClubDto).toList();
    }

    @Override
    public Club saveClub(Club club) {
        return clubRepository.save(club);
    }

    @Override
    public ClubDto findClubById(Long clubId) {
        Club club = clubRepository.findById(clubId).orElseThrow();
        return mapToClubDto(club);
    }

    @Override
    public void updateClub(ClubDto clubDto) {
        Club club = mapToClub(clubDto);
        clubRepository.save(club);
    }

    private Club mapToClub(ClubDto clubDto) {
        return Club.builder()
                .id(clubDto.getId())
                .title(clubDto.getTitle())
                .photoUrl(clubDto.getPhotoUrl())
                .content(clubDto.getContent())
                .createdOn(clubDto.getCreatedOn())
                .updatedOn(clubDto.getUpdatedOn())
                .build();
    }

    private ClubDto mapToClubDto(Club club) {
        return ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .build();
    }
}
