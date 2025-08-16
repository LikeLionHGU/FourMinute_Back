package repository;

import domain.Gather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GatherRepository extends JpaRepository<Gather, Long> {

    List<Gather> findAllBySport(String sport);


}
