package raisetech.StudentManagement.exception;

import jakarta.validation.ConstraintViolationException;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * アプリケーション全体の例外処理を行うクラス
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * 独自例外の処理
   */
  @ExceptionHandler(TestException.class)
  public ResponseEntity<String> handleTestException(TestException ex) {
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  /**
   * @Valid による入力チェックエラーの処理
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<String> handleValidationException(
      MethodArgumentNotValidException ex) {

    String errorMessage = ex.getBindingResult().getFieldErrors()
        .stream()
        .map(error -> error.getField() + ": " + error.getDefaultMessage())
        .collect(Collectors.joining(", "));

    return ResponseEntity.badRequest().body(errorMessage);
  }

  /**
   * @Validated による入力チェックエラーの処理
   */
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<String> handleConstraintViolationException(
      ConstraintViolationException ex) {

    String errorMessage = ex.getConstraintViolations()
        .stream()
        .map(error -> error.getPropertyPath() + ": " + error.getMessage())
        .collect(Collectors.joining(", "));

    return ResponseEntity.badRequest().body(errorMessage);
  }
}
