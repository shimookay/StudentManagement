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
 * 受講生テーブルと受講生コース情報テーブルと紐づくリポジトリ
 */
@Mapper
public interface StudentRepository {

  /**
   * 受講生の全件検索
   *
   * @return 受講生一覧（全件）
   */
  @Select("SELECT * FROM students")
  List<Student> search();

  /**
   * 受講生の検索
   *
   * @param studentId 受講生ID
   * @return          受講生
   */
  @Select("SELECT * FROM students WHERE student_id=#{studentId}")
  Student searchStudent(String studentId);

  /**
   * 受講生コース情報の全件検索
   *
   * @return 受講生のコース情報（全件）
   */
  @Select("SELECT * FROM students_courses")
  List<StudentCourses> searchStudentsCoursesList();

  /**
   * 受講生IDに紐づく受講生コース情報を検索
   *
   * @param studentId 受講生ID
   * @return          受講生IDに紐づく受講生コース情報
   */
  @Select("SELECT * FROM students_courses WHERE student_id=#{studentId}")
  List<StudentCourses> searchStudentsCourses(String studentId);

  @Insert(
    "INSERT INTO students(name, ruby, nickname, email, address, phone, age, gender, remark, is_deleted)"
      + "VALUES(#{name}, #{ruby}, #{nickname}, #{email}, #{address}, #{phone}, #{age}, #{gender}, #{remark}, false)")
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
