package com.practicing.springpractice.service;

import com.practicing.springpractice.dto.ClubDto;
import com.practicing.springpractice.model.Club;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();
    Club saveClub(Club club);

    ClubDto findClubById(Long clubId);

    void updateClub(ClubDto club);
}
