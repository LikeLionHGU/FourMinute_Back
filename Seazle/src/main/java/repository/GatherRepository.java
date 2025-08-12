package repository;

import domain.Gather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GatherRepository extends JpaRepository<Gather, Long> {

}
