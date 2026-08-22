package raisetech.StudentManagement.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourses;
import raisetech.StudentManagement.service.StudentService;

@RestController
public class StudentController {

  private StudentService service;

  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  @GetMapping("/studentList")
  public List<Student> getStudentList() {
    // リクエストの加工処理、入力チェック等
    return service.searchStudentList();
  }

  @GetMapping("/studentCoursesList")
  public List<StudentCourses> getStudentCoursesList() {
    // リクエストの加工処理、入力チェック等
    return service.searchStudentCoursesList();
  }
}
