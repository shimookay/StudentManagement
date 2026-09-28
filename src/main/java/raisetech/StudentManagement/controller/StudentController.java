package raisetech.StudentManagement.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import raisetech.StudentManagement.controller.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourses;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.service.StudentService;

@Controller
public class StudentController {

  private StudentService service;
  private StudentConverter converter;

  @Autowired
  public StudentController(StudentService service, StudentConverter converter) {
    this.service = service;
    this.converter = converter;
  }

  @GetMapping("/studentList")
  public String getStudentList(Model model) {
    // リクエストの加工処理、入力チェック等
    List<Student> students = service.searchStudentList();
    List<StudentCourses> studentCourses = service.searchStudentCoursesList();

    // テンプレートエンジンの中に書かれている変数(?)の名前
    model.addAttribute("studentList", converter.convertStudentDetails(students, studentCourses));
    // テンプレートエンジンのファイル名
    return "studentList";
  }

  @GetMapping("/studentCoursesList")
  public List<StudentCourses> getStudentCoursesList() {
    // リクエストの加工処理、入力チェック等
    return service.searchStudentCoursesList();
  }

  @GetMapping("/newStudent")
  public String newStudent(Model model) {
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudentsCourses(Arrays.asList(new StudentCourses()));
    model.addAttribute("studentDetail", studentDetail);
    return "registerStudent";
  }

  @PostMapping("/registerStudent")
  public String registerStudent(@ModelAttribute StudentDetail studentDetail, BindingResult result) {
    if(result.hasErrors()) {
      return "registerStudent";
    }
    // System.out.println(studentDetail.getStudent().getName() + "さんが新規受講生として登録されました。");

    service.registerStudent(studentDetail);
    // ①新規受講生情報を登録する処理を実装する。
    // 　→ 登録された受講生情報が、結果としてstudentListとして検索画面に出てくるように実装する。
    // ②コース情報も一緒に登録できるように実装する。登録するコース情報は１つで良い。

        return "redirect:/studentList";
  }
}
