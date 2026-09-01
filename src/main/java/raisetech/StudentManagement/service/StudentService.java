package raisetech.StudentManagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourses;
import raisetech.StudentManagement.repository.StudentRepository;

@Service
public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public List<Student> searchStudentList() {
    return repository.search();

//    // DBから受講生を全件検索処理
//    List<Student> studentList = repository.search();
//
//    // 絞り込み検索で年齢が30代の人のみを抽出する
//    List<Student> filteredStudentList = studentList.stream()
//        .filter(student -> student.getAge() >= 30 && student.getAge() < 40)
//        .toList();
//
//    // 抽出したリストをコントローラへ返す
//    return filteredStudentList;
  }

  public List<StudentCourses> searchStudentCoursesList() {
    return repository.searchStudentCourses();

//    // DBからコース情報を全件検索
//    List<StudentCourses> studentCoursesList = repository.searchStudentCourses();
//
//    // 絞り込み検索で「Java基礎」コース情報のみを抽出する
//    List<StudentCourses> filteredStudentCoursesList = studentCoursesList.stream()
//        .filter(studentCourses -> studentCourses.getCourseName().equals("Java基礎"))
//        .toList();
//
//    // 抽出したリストをコントローラへ返す
//    return filteredStudentCoursesList;
  }
}
