package raisetech.StudentManagement.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.exception.TestException;
import io.swagger.v3.oas.annotations.Operation;
import raisetech.StudentManagement.service.StudentService;

/**
 * 受講生の検索・登録・更新などを行うREST APIとして受け付けるController
 */
@Validated
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

  //**
  // * 例外発生の確認で使用する入力部分
  // *
  // */
  //@Operation(summary = "例外処理確認用", description = "例外処理を確認します")
  //@GetMapping("/studentList")
  //public List<StudentDetail> getStudentLists() throws TestException {
  //  throw new TestException(
  //      "現在このAPIは利用できません。URLは”studentList”ではなく、”students”を利用して下さい。");
  //}

  /**
   * 受講生詳細の一覧検索
   * 全件検索を行うので、条件検索は行わない
   *
   * @return 受講生詳細一覧（全件）
   */
  @Operation(summary = "一覧検索", description ="受講生の一覧を検索します")
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
  @Operation(summary = "受講生詳細検索", description ="受講生IDに紐づく受講生情報を取得します")
  @GetMapping("/student/{studentId}")
  public StudentDetail getStudent(
      @PathVariable @NotBlank @Size(min=1, max=3) @Pattern(regexp = "^\\d+$") String studentId) {
    return service.searchStudent(studentId);
  }

  /**
   * 受講生詳細の登録
   *
   * @param studentDetail 受講生詳細
   * @return              実行結果
   */
  @Operation(summary = "受講生登録", description ="受講生を登録します")
  @PostMapping("/registerStudent")
  public ResponseEntity<StudentDetail> registerStudent(
      @RequestBody @Valid StudentDetail studentDetail) {
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
  @Operation(summary = "受講生詳細更新", description ="受講生情報を更新します（キャンセルフラグによる論理削除も行います）")
  @PutMapping("/updateStudent")
  public ResponseEntity<String> updateStudent(@RequestBody @Valid StudentDetail studentDetail) {
    service.updateStudent(studentDetail);
    return ResponseEntity.ok("更新処理が成功しました。");
  }
}
