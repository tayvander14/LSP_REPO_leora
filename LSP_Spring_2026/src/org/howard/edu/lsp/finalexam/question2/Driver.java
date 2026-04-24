package org.howard.edu.lsp.finalexam.question2;

import java.util.ArrayList;
import java.util.List;

/**
 * Driver class demonstrating the Template Method pattern with polymorphism.
 *
 * <p>Creates a list of {@link Report} objects, adds both a {@link StudentReport}
 * and a {@link CourseReport}, then calls {@code generateReport()} on each,
 * demonstrating polymorphic dispatch through the shared abstract type.
 */
public class Driver {

    /**
     * Entry point.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        List<Report> reports = new ArrayList<>();
        reports.add(new StudentReport());
        reports.add(new CourseReport());

        for (Report report : reports) {
            report.generateReport();   // polymorphic call — correct subclass methods invoked
        }
    }
}