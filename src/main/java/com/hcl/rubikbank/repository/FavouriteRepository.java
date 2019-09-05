package com.hcl.rubikbank.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.rubikbank.entity.Favourite;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, Integer> {

	Page<Favourite> findAll(Pageable pageable);

}
