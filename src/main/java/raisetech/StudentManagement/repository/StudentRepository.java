package raisetech.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
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

  @Select("SELECT * FROM students WHERE student_id=#{studentId}")
  Student searchStudent(String studentId);

  @Select("SELECT * FROM students_courses")
  List<StudentCourses> searchStudentsCoursesList();

  @Select("SELECT * FROM students_courses WHERE student_id=#{studentId}")
  List<StudentCourses> searchStudentsCourses(String studentId);

  @Insert("""
    INSERT INTO students
    (name, ruby, nickname, email, address, phone, age, gender, remark, is_deleted)
    VALUES
    (#{name}, #{ruby}, #{nickname}, #{email}, #{address}, #{phone}, #{age}, #{gender}, #{remark}, false)
    """)
    // student_id, #{studentId}, を削除
  @Options(useGeneratedKeys = true, keyProperty = "studentId")
  void registerStudent(Student student);

  @Insert("INSERT INTO students_courses(student_id, course_name, start_date, end_date) "
      + "VALUES(#{studentId}, #{courseName}, #{startDate}, #{endDate})")
  @Options(useGeneratedKeys = true, keyProperty = "courseId")
  void registerStudentsCourses(StudentCourses studentsCourses);


  @Update(
      "UPDATE students SET name=#{name}, ruby=#{ruby}, nickname=#{nickname}, email=#{email}, address=#{address}, "
          + "phone=#{phone}, age=#{age}, gender=#{gender}, remark=#{remark}, is_deleted=#{deleted} WHERE student_id=#{studentId}")
  void updateStudent(Student student);

  @Update("UPDATE students_courses SET course_name=#{courseName} WHERE course_id=#{courseId}")
  void updateStudentsCourses(StudentCourses studentsCourses);

}
