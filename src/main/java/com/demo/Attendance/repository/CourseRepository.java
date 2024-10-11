package com.demo.Attendance.repository;

import com.demo.Attendance.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {


    @Override
    Page<Course> findAll(Pageable pageable);

    @Query("select c from Course c where c.courseName LIKE %?1%")
    Page<Course> searchByCourseName(String courseName,Pageable pageable);

//    Page<Course> findByCourseNameContaining(String courseName, Pageable pageable);

}
