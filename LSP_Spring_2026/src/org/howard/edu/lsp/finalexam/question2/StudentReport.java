package org.howard.edu.lsp.finalexam.question2;

/**
 * Concrete implementation of {@link Report} for student-specific reports.
 *
 * <p>Provides student name and GPA data, loaded in {@link #loadData()}, and
 * formats them according to the required output structure defined by the
 * template method in the parent class.
 */
public class StudentReport extends Report {

    /** The student's full name. */
    private String studentName;

    /** The student's grade point average. */
    private double gpa;

    /**
     * Loads student data.
     * Sets {@code studentName} and {@code gpa} for use in formatting methods.
     */
    @Override
    protected void loadData() {
        studentName = "John Doe";
        gpa = 3.8;
    }

    /**
     * Formats and prints the student report header.
     */
    @Override
    protected void formatHeader() {
        System.out.println("=== HEADER ===");
        System.out.println("Student Report");
        System.out.println();
    }

    /**
     * Formats and prints the student report body, displaying name and GPA.
     */
    @Override
    protected void formatBody() {
        System.out.println("=== BODY ===");
        System.out.println("Student Name: " + studentName);
        System.out.println("GPA: " + gpa);
        System.out.println();
    }

    /**
     * Formats and prints the student report footer.
     */
    @Override
    protected void formatFooter() {
        System.out.println("=== FOOTER ===");
        System.out.println("End of Student Report");
        System.out.println();
    }
}