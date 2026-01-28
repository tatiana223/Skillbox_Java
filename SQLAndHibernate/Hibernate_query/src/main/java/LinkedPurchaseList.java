import jakarta.persistence.*;

@Entity
@Table(name = "LinkedPurchaseList")
@IdClass(LinkedPurchaseList.class)
public class LinkedPurchaseList {
    @Id
    @Column(name = "student_id")
    private int studentId;
    @Id
    @Column(name = "course_id")
    private int courseId;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
