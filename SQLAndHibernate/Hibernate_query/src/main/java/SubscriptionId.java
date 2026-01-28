import jakarta.persistence.Column;

import java.io.Serializable;

public class SubscriptionId implements Serializable {
    @Column(name = "student_id")
    private int studentId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubscriptionId that = (SubscriptionId) o;

        if (studentId != that.studentId) return false;
        return courseId == that.courseId;
    }

    @Override
    public int hashCode() {
        int result = studentId;
        result = 31 * result + courseId;
        return result;
    }
}
