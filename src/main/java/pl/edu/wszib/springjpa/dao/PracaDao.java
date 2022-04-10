package pl.edu.wszib.springjpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.springjpa.model.Praca;
import pl.edu.wszib.springjpa.model.Praca;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface PracaDao extends JpaRepository<Praca, Long> {

    @Query(nativeQuery = true, value = "select * from dbo.praca where stanowisko like ?0")
    List<Praca> mojeSuperQuery2(String stanwisko);

    @Query("select p from Praca p where p.stanowisko like :stanowisko")
    List<Praca> mojeSuperQuery(@Param("stanowisko") String stanwisko);

    List<Praca> findAllByStanowiskoContains(String stanowisko);
    Praca findFirstByCreatedAtBefore(Instant time);
    List<Praca> findFirstByStanowiskoAndNazwaFirmyOrderByCreatedAtDesc(String stanowisko, String nazwaFirmy);
}
