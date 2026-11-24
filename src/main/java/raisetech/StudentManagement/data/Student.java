package raisetech.StudentManagement.data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {

  private String studentId;

  @NotBlank
  private String name;

  @NotBlank
  private String ruby;

  @NotBlank
  private String nickname;

  @NotBlank
  @Email
  private String email;

  @NotBlank
  private String address;

  private String phone;
  private String age;

  @NotBlank
  private String gender;

  // 以下の2項目をDBへ追加する。
  // 今のDBのStudent_Tableに追加して、且つこの2項目を画面に引っ張ってこられるようにObjectを変更したい。
  // StudentRepository内でSQLのテーブルへ項目を追加する際、ALTERTABLE コマンドを使って行う。
  // 削除フラグ；削除されているデータに関しては検索を実行しない。物理削除は行わず、論理削除をしたい。
  private String remark;
  private boolean isDeleted;

}
