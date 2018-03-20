package com.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.ByteWheels;

@Repository
public interface ByteDao extends JpaRepository<ByteWheels, Long>, CrudRepository<ByteWheels, Long>{

}
