package com.vti.dulichviet_team_1.Repository.specification;

import com.vti.dulichviet_team_1.modal.entity.Account;
import com.vti.dulichviet_team_1.request.AccountSearchRequest;
import org.springframework.data.jpa.domain.Specification;

public class AccountSpecification {
    public static Specification<Account> buildCondition(AccountSearchRequest request) {
        return Specification.where(buildConditionEmail(request))
                .and(buildConditionUserName(request))
                .and(buildConditionFullName(request))
                .and(buildConditionPhone(request));
    }


    public static Specification<Account> buildConditionEmail(AccountSearchRequest request) {
        if (request.getEmail() != null && !"".equals(request.getEmail())) {
            return (root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("email"), "%" + request.getEmail() + "%");
            };
        } else {
            return null;
        }
    }


    public static Specification<Account> buildConditionUserName(AccountSearchRequest request) {
        if (request.getUsername() != null && !"".equals(request.getUsername())) {
            return (root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("username"), "%" + request.getUsername() + "%");
            };
        } else {
            return null;
        }
    }


    public static Specification<Account> buildConditionFullName(AccountSearchRequest request) {
        if (request.getFullName() != null && !"".equals(request.getFullName())) {
            return (root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("fullName"), "%" + request.getFullName() + "%");
            };
        } else {
            return null;
        }
    }


    public static Specification<Account> buildConditionPhone(AccountSearchRequest request) {
        if (request.getPhone() != null && !"".equals(request.getPhone())) {
            return (root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("phone"), "%" + request.getPhone() + "%");
            };
        } else {
            return null;
        }
    }



}
