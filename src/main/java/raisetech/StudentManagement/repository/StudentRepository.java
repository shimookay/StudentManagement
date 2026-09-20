package raisetech.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourses;

/**
 * 受講生情報を扱うリポジトリ。
 * 全件検索や単一条件での検索、コース情報の検索が行えるクラス。
 */
@Mapper
public interface StudentRepository {

  /**
   * 全件検索します。
   * @return 全件検索した受講生情報の一覧
   */

  // DB層（Javaの世界で扱う疑似的なDB）
  @Select("SELECT * FROM students")
  List<Student> search();

  @Select("SELECT * FROM students_courses")
  List<StudentCourses> searchStudentCourses();

  @Insert("""
    INSERT INTO students
    (student_id, name, ruby, nickname, email, address, phone, age, gender, remark, is_deleted)
    VALUES
    (#{studentId}, #{name}, #{ruby}, #{nickname}, #{email}, #{address}, #{phone}, #{age}, #{gender}, #{remark}, #{isDeleted})
    """)
  void registerStudent(Student student);
}
