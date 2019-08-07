package com.kool.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@Service
public class MyDatabaseService {


        @PersistenceContext
        private EntityManager entityManager;


        @Autowired
        private StudentRepository studentRepository;

        public long insert(Student student) {
            entityManager.persist(student);
            return student.getId();
        }

        public Student findStudent(long id) {
            return entityManager.find(Student.class, id);
        }

        public List<Student> findAllStudents() {
            System.out.println("Finding all students");
           Query query = entityManager.createNamedQuery(
                    "query_find_all_students", Student.class);
         //   List<Student> students = entityManager.createQuery("select s from Student s", Student.class).getResultList();

            return query.getResultList();
        }
        public Student findStudentById(long id) {
            Student student=null;

            Optional<Student> studentOpt=studentRepository.findById(id);
            studentOpt.ifPresent(student1 -> { System.out.println(student1.getStudentName());
                                                });
            if (studentOpt.isPresent()){
                student=studentOpt.get();
            }
            return student;
        }
        public Student saveStudent(Student student) {

            System.out.println(" ## Creating new student");
            if (student.getId() == -1 || student.getId() == 0) {
                student.setId(null);
            }
            studentRepository.save(student);
            return student;
       }
       public Student deleteStudentById(long id) {
        Student student = findStudentById(id);

        studentRepository.delete(student);

        return student;
       }

}
