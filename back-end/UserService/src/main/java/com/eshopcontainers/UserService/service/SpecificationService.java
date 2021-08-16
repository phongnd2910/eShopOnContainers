package com.eshopcontainers.UserService.service;

import com.eshopcontainers.UserService.model.TablePagingRequest;
import com.eshopcontainers.UserService.model.TableSearchRequest;
import com.eshopcontainers.UserService.model.TableSortingRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationService<T> {
    Specification<T> specificationBuilder(TableSearchRequest tableSearchRequest);

    Pageable getPage(TablePagingRequest tablepagingRequest, Sort sort);

    Sort getSort(TableSortingRequest tableSortingRequest);
}
