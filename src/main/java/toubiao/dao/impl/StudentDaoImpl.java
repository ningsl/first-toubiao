package toubiao.dao.impl;

import org.springframework.stereotype.Repository;

import toubiao.dao.BaseDaoI;
import toubiao.dao.StudentDaoI;
import toubiao.model.Student;
@Repository
public class StudentDaoImpl extends BaseDaoImpl<Student> implements StudentDaoI {

}
