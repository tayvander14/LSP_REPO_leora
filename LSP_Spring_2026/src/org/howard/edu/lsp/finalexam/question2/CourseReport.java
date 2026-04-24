package org.howard.edu.lsp.finalexam.question2;

/**
 * Concrete implementation of {@link Report} for course-specific reports.
 *
 * <p>Provides course name and enrollment data, loaded in {@link #loadData()}, and
 * formats them according to the required output structure defined by the
 * template method in the parent class.
 */
public class CourseReport extends Report {

    /** The name of the course. */
    private String courseName;

    /** The number of students enrolled in the course. */
    private int enrollment;

    /**
     * Loads course data.
     * Sets {@code courseName} and {@code enrollment} for use in formatting methods.
     */
    @Override
    protected void loadData() {
        courseName = "CSCI 363";
        enrollment = 45;
    }

    /**
     * Formats and prints the course report header.
     */
    @Override
    protected void formatHeader() {
        System.out.println("=== HEADER ===");
        System.out.println("Course Report");
        System.out.println();
    }

    /**
     * Formats and prints the course report body, displaying course name and enrollment.
     */
    @Override
    protected void formatBody() {
        System.out.println("=== BODY ===");
        System.out.println("Course: " + courseName);
        System.out.println("Enrollment: " + enrollment);
        System.out.println();
    }

    /**
     * Formats and prints the course report footer.
     */
    @Override
    protected void formatFooter() {
        System.out.println("=== FOOTER ===");
        System.out.println("End of Course Report");
        System.out.println();
    }
}