package raisetech.StudentManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class StudentManagementApplication {

	private Map<String, String> studentMap = new HashMap<>();

	public StudentManagementApplication() {
		studentMap.put("Enami Koji", "37");
		studentMap.put("Suzuki Gemma", "20");
	}

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}

	@GetMapping("/studentInfo")
	public Map<String, String> getStudentInfo() {
		return studentMap;
	}

  @PostMapping("/studentInfo")
  public void setStudentInfo(String name, String age) {
    studentMap.put(name, age);
  }
}