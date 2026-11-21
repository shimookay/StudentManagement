package raisetech.StudentManagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.service.StudentService;

/**
 * 受講生の検索・登録・更新などを行うREST APIとして受け付けるController
 */
@RestController
public class StudentController {

  /** 受講生サービス */
  private StudentService service;

  /**
   * コンストラクタ
   * @param service   受講生サービス
   */
  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  /**
   * 受講生詳細の一覧検索
   * 全件検索を行うので、条件検索は行わない
   *
   * @return 受講生詳細一覧（全件）
   */
  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList() {
    return service.searchStudentList();
  }


  /**
   * 受講生詳細の検索
   * studentIDに紐づく任意の受講生情報を取得
   *
   * @param studentId 受講生ID
   * @return          受講生
   */
  @GetMapping("/student/{studentId}")
  public StudentDetail getStudent(@PathVariable String studentId) {
    return service.searchStudent(studentId);
  }

  /**
   * 受講生詳細の登録
   *
   * @param studentDetail 受講生詳細
   * @return              実行結果
   */
  @PostMapping("/registerStudent")
  public ResponseEntity<StudentDetail> registerStudent(@RequestBody StudentDetail studentDetail) {
    StudentDetail responseStudentDetail = service.registerStudent(studentDetail);
    return ResponseEntity.ok(responseStudentDetail);
  }

  /**
   * 受講生詳細の更新
   * キャンセルフラグの更新もここで行う（論理削除）
   *
   * @param studentDetail 受講生詳細
   * @return              実効結果
   *
   * 『 Post:押し込む、 Put:全体を入れ替える、 Patch:小さな一部を入れ替える 』
   */
  @PutMapping("/updateStudent")
  public ResponseEntity<String> updateStudent(@RequestBody StudentDetail studentDetail) {
    service.updateStudent(studentDetail);
    return ResponseEntity.ok("更新処理が成功しました。");
  }
}
