package uy.com.geocom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uy.com.geocom.model.Channel;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Integer>{

}
