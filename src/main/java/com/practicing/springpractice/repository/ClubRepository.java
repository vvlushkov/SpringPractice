package com.practicing.springpractice.repository;

import com.practicing.springpractice.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club, Long> {
    Optional<Club> findByPhotoUrl(String url);
}
