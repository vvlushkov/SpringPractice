package com.practicing.springpractice.service.impl;

import com.practicing.springpractice.dto.ClubDto;
import com.practicing.springpractice.mapper.ClubMapper;
import com.practicing.springpractice.model.Club;
import com.practicing.springpractice.repository.ClubRepository;
import com.practicing.springpractice.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import static com.practicing.springpractice.mapper.ClubMapper.mapToClub;
import static com.practicing.springpractice.mapper.ClubMapper.mapToClubDto;

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
        return clubs.stream().map(ClubMapper::mapToClubDto).toList();
    }

    @Override
    public Club saveClub(ClubDto clubDto) {
        Club club = mapToClub(clubDto);
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

    @Override
    public void delete(Long clubId) {
        clubRepository.deleteById(clubId);
    }

    @Override
    public List<ClubDto> searchClubs(String query) {
        List<Club> clubs = clubRepository.searchClubs(query);
        return clubs.stream().map(ClubMapper::mapToClubDto).toList();
    }
}
