package com.example.seazle.repository;

import com.example.seazle.domain.Join;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JoinRepository extends JpaRepository<Join, Long> {


    Optional<List<Join>> findAllByGatherId(Long gatherId);
}
