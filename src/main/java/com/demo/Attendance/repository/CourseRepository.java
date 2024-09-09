package com.demo.Attendance.repository;

import com.demo.Attendance.model.Course;
import com.demo.Attendance.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

}
