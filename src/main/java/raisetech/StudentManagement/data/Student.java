package raisetech.StudentManagement.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {

  private String studentId;
  private String name;
  private String ruby;
  private String nickname;
  private String email;
  private String address;
  private String phone;
  private int age;
  private String gender;

  // 以下の2項目をDBへ追加する。
  // 今のDBのStudent_Tableに追加して、且つこの2項目を画面に引っ張ってこられるようにObjectを変更したい。
  // StudentRepository内でSQLのテーブルへ項目を追加する際、ALTERTABLE コマンドを使って行う。
  // 削除フラグ；削除されているデータに関しては検索を実行しない。物理削除は行わず、論理削除をしたい。
  private String remark;
  private boolean isDeleted;

}
