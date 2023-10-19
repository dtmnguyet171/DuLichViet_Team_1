package com.vti.dulichviet_team_1.Repository;

import com.vti.dulichviet_team_1.modal.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends JpaRepository<Tour, Integer> , JpaSpecificationExecutor<Tour> {


}
