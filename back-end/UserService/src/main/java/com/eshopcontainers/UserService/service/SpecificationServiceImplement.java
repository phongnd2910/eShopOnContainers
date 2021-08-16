package com.eshopcontainers.UserService.service;


import com.eshopcontainers.UserService.model.ColumnSearchRequest;
import com.eshopcontainers.UserService.model.TablePagingRequest;
import com.eshopcontainers.UserService.model.TableSearchRequest;
import com.eshopcontainers.UserService.model.TableSortingRequest;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SpecificationServiceImplement<T> implements SpecificationService {

    @Override
    public Specification<T> specificationBuilder(TableSearchRequest tableSearchRequest) {
        List<ColumnSearchRequest> toSpecs = CollectionUtils
                .emptyIfNull(tableSearchRequest.getColumnSearchRequests()).stream()
                .filter(item -> StringUtils.isNotBlank(item.getTerm()) || item.getTermDate() != null
                        || item.getList() != null)
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(toSpecs)) {
            return Specification.where(null);
        }
        Specification<T> result = specificationBuilder(toSpecs.get(0));
        for (int i = 1; i < toSpecs.size(); i++) {
            ColumnSearchRequest rq = toSpecs.get(i);
            Specification<T> spec = specificationBuilder(rq);
            Specification<T> where = ObjectUtils
                    .defaultIfNull(Specification.where(result), Specification.where(null));
            result = rq.getIsOrTerm() ? where.or(spec) : where.and(spec);
        }
        return result;
    }

    @Override
    public Pageable getPage(TablePagingRequest tablepagingRequest, Sort sort) {
        return tablepagingRequest == null ?
                PageRequest.of(0, Integer.MAX_VALUE, sort) : // unpage with sort
                PageRequest.of(tablepagingRequest.getPage(), tablepagingRequest.getPageSize(), sort);
    }

    @Override
    public Sort getSort(TableSortingRequest tableSortingRequest) {
        if (tableSortingRequest == null || StringUtils.isBlank(tableSortingRequest.getColumnName())
                || tableSortingRequest.getDirection() == null) {
            return Sort.unsorted();
        }
        return Sort.by(Sort.Direction.fromString(tableSortingRequest.getDirection().name()),
                tableSortingRequest.getColumnName());
    }

    public Specification<T> specificationBuilder(ColumnSearchRequest columnSearchRequest) {
        return (root, query, builder) -> {
            String columnName = columnSearchRequest.getColumnName();
            String term = columnSearchRequest.getTerm();
            Date termDate = columnSearchRequest.getTermDate();
            if (StringUtils.isEmpty(term) && Objects.nonNull(termDate)) {
                Expression<Date> path = root.get(columnName);
                List<Date> list = (List<Date>) columnSearchRequest.getList();
                return getPredicate(columnSearchRequest, builder, list, termDate, path);
            }
            Expression<String> path = root.get(columnName);
            List<String> list = (List<String>) columnSearchRequest.getList();
            return getPredicate(columnSearchRequest, builder, list, term, path);
        };

    }

    private Predicate getPredicate(ColumnSearchRequest columnSearchRequest,
                                   CriteriaBuilder builder, List<String> list, String term, Expression<String> path) {
        switch (columnSearchRequest.getOperation()) {
            case EQUAL:
                return builder.equal(path, term);
            case NOT_EQUAL:
                return builder.notEqual(path, term);
            case LIKE:
                return builder.like(path, "%" + term + "%");
            case LESS_THAN:
                return builder.lessThan(path, term);
            case GREATER_THAN:
                return builder.greaterThan(path, term);
            case LESS_THAN_OR_EQUAL_TO:
                return builder.lessThanOrEqualTo(path, term);
            case IN:
                CriteriaBuilder.In<String> inClause = builder.in(path);
                list.forEach(inClause::value);
                return inClause;
            default:
                return builder.greaterThanOrEqualTo(path, term);
        }
    }

    private Predicate getPredicate(ColumnSearchRequest columnSearchRequest,
                                   CriteriaBuilder builder, List<Date> list, Date term, Expression<Date> path) {
        switch (columnSearchRequest.getOperation()) {
            case EQUAL:
                return builder.equal(path, term);
            case NOT_EQUAL:
                return builder.notEqual(path, term);
            case LIKE:
                throw new IllegalArgumentException("Not support");
            case LESS_THAN:
                return builder.lessThan(path, term);
            case GREATER_THAN:
                return builder.greaterThan(path, term);
            case LESS_THAN_OR_EQUAL_TO:
                return builder.lessThanOrEqualTo(path, term);
            case IN:
                CriteriaBuilder.In<Date> inClause = builder.in(path);
                list.forEach(inClause::value);
                return inClause;
            default:
                return builder.greaterThanOrEqualTo(path, term);
        }
    }
}