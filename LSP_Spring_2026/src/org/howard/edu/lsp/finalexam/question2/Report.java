package org.howard.edu.lsp.finalexam.question2;

/**
 * Abstract base class implementing the Template Method design pattern for report generation.
 *
 * <p>The fixed workflow (template method) is defined in {@link #generateReport()}, which
 * calls the following steps in order:
 * <ol>
 *   <li>{@link #loadData()} – load report-specific data</li>
 *   <li>{@link #formatHeader()} – format and print the report header</li>
 *   <li>{@link #formatBody()} – format and print the report body</li>
 *   <li>{@link #formatFooter()} – format and print the report footer</li>
 * </ol>
 * Subclasses must implement the abstract steps; the overall algorithm structure is fixed here.
 */
public abstract class Report {

    /**
     * Template method — defines the fixed report-generation workflow.
     * Subclasses may not override this method.
     */
    public final void generateReport() {
        loadData();
        formatHeader();
        formatBody();
        formatFooter();
    }

    /**
     * Loads report-specific data.
     * Subclasses must set all fields needed by formatHeader, formatBody, and formatFooter.
     */
    protected abstract void loadData();

    /**
     * Formats and prints the report header section.
     */
    protected abstract void formatHeader();

    /**
     * Formats and prints the report body section.
     */
    protected abstract void formatBody();

    /**
     * Formats and prints the report footer section.
     */
    protected abstract void formatFooter();
}