package com.vti.dulichviet_team_1.service.impl;


import com.vti.dulichviet_team_1.modal.entity.TourStatus;
import com.vti.dulichviet_team_1.repository.TourRepository;
import com.vti.dulichviet_team_1.modal.dto.TourRequestCreateDto;
import com.vti.dulichviet_team_1.modal.dto.TourRequestUpdateDto;
import com.vti.dulichviet_team_1.modal.dto.ViewListRequestDto;
import com.vti.dulichviet_team_1.modal.entity.Tour;
import com.vti.dulichviet_team_1.service.ITourService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TourService implements com.vti.dulichviet_team_1.service.ITourService {

    @Autowired
    private final TourRepository tourRepository;

    @Override
    public List<Tour> getAllTour() {
        return tourRepository.findAll();

    }

    @Override
    public Tour createTour(TourRequestCreateDto tourRequestCreateDto) {
        List<Tour> tourList = new ArrayList<>();
        Tour tour = new Tour();
        BeanUtils.copyProperties(tourRequestCreateDto, tour);
        tourList.add(tour);
        return tourRepository.save(tour);
    }

    @Override
    public Tour updateTour(TourRequestUpdateDto tourRequestUpdateDto) {

        Tour tour = new Tour();
        BeanUtils.copyProperties(tourRequestUpdateDto, tour);
        if (tour.getMaxGuestSize() == 0){
          tour.setStatus(TourStatus.UNAVAILABLE);
        } else {
          tour.setStatus(TourStatus.AVAILABLE);
        }
        return tourRepository.save(tour);

    }

    @Override
    public void deleteTour(int id) {
        tourRepository.deleteById(id);
    }

    @Override
    public Tour viewDetailTour(int id) {
        Optional<Tour> optionalTour = tourRepository.findById(id);
        if (optionalTour.isPresent()) {
            return optionalTour.get();
        } else {
            return null;
        }

    }

    //    TÌM KIẾM NÂNG CAO KẾT HỢP PHÂN TRANG
    @Override
    public Page<Tour> viewListTour(ViewListRequestDto viewListRequestDto) {
        PageRequest pageRequest = null;

        if ("DESC".equals(viewListRequestDto.getSortType())) {
            pageRequest = PageRequest.of(viewListRequestDto.getPage() - 1, viewListRequestDto.getPage_size(), Sort.by(viewListRequestDto.getSortField()).descending());

        } else {
            pageRequest = PageRequest.of(viewListRequestDto.getPage() - 1, viewListRequestDto.getPage_size(), Sort.by(viewListRequestDto.getSortField()).ascending());
        }
        Specification<Tour> condition = TourSpecification.buildCondition(viewListRequestDto);
        System.out.println(condition);
        return tourRepository.findAll(condition, pageRequest);
    }
}
