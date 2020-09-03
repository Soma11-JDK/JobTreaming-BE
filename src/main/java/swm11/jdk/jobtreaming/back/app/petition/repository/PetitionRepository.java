package swm11.jdk.jobtreaming.back.app.petition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import swm11.jdk.jobtreaming.back.app.petition.model.Petition;

import java.util.List;

public interface PetitionRepository extends JpaRepository <Petition, Long> {

    @Query(nativeQuery = true, value = "select p from Petition p inner join petition_likes pl on p.id = pl.id group by p.id order by count(*) desc limit 10")
    List<Petition> findTop6ByLikes();

}
