package pl.delukesoft.csp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.delukesoft.csp.games.models.entities.Result;

public interface ResultRepository extends JpaRepository<Result, Long> {



}
