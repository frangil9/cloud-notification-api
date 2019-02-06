package uy.com.geocom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uy.com.geocom.model.Local;

@Repository
public interface LocalRepository extends JpaRepository<Local, Integer>{

}
