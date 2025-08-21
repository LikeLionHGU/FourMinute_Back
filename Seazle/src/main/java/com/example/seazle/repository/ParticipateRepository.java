package com.example.seazle.repository;

import com.example.seazle.domain.Participate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipateRepository extends JpaRepository<Participate, Long> {


    List<Participate> findAllByGatherId(Long gatherId);

    Optional<Participate> findByGatherIdAndUserId(Long gatherId, Long id);


}
