package com.society.maintenance.repository;


import com.society.maintenance.entity.Notice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface NoticeRepository 
        extends JpaRepository<Notice, Long> {
List<Notice> findTop5ByOrderByCreatedAtDesc();

    List<Notice> findAllByOrderByCreatedAtDesc();


}