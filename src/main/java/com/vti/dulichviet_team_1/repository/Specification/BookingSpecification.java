package com.vti.dulichviet_team_1.Repository.specification;

import com.vti.dulichviet_team_1.modal.entity.Booking;
import com.vti.dulichviet_team_1.request.BookingSearchRequest;
import org.springframework.data.jpa.domain.Specification;

public class BookingSpecification {

    public static Specification<Booking> buildCondition(BookingSearchRequest request) {
        return Specification.where(buildCondition(request))
                .and(buildConditionEmail(request))
                .and(buildConditionUserName(request))
                .and(buildConditionFullName(request))
                .and(buildConditionPhone(request))
                .and(buildConditionStatus(request));
    }


    public static Specification<Booking> buildConditionEmail(BookingSearchRequest request) {
        if (request.getAccountId().getEmail() != null && !"".equals(request.getAccountId().getEmail())) {
            return (root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("email"), "%" + request.getAccountId().getEmail() + "%");
            };
        } else {
            return null;
        }
    }


    public static Specification<Booking> buildConditionUserName(BookingSearchRequest request) {
        if (request.getAccountId().getUsername() != null && !"".equals(request.getAccountId().getUsername())) {
            return (root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("username"), "%" + request.getAccountId().getUsername() + "%");
            };
        } else {
            return null;
        }
    }


    public static Specification<Booking> buildConditionFullName(BookingSearchRequest request) {
        if (request.getAccountId().getFullName() != null && !"".equals(request.getAccountId().getFullName())) {
            return (root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("fullName"), "%" + request.getAccountId().getFullName() + "%");
            };
        } else {
            return null;
        }
    }


    public static Specification<Booking> buildConditionPhone(BookingSearchRequest request) {
        if (request.getAccountId().getPhone() != null && !"".equals(request.getAccountId().getPhone())) {
            return (root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("phone"), "%" + request.getAccountId().getPhone() + "%");
            };
        } else {
            return null;
        }
    }


    public static Specification<Booking> buildConditionStatus(BookingSearchRequest request) {
        if (request.getStatus() != null && request.getStatus().size() > 0) {
            return (root, query, criteriaBuilder) -> {
                return root.get("status").in(request.getStatus());
            };
        } else {
            return null;
        }
    }

}
