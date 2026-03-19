For Question #3, I also used ChatGPT. Below is the complete transcript: 

now question #3. let's start with part 1:

Question 3 - Refactoring to Use a Design Pattern (30 points)
The following class calculates final prices for customer purchases. Although the code works, the design makes the system difficult to extend and maintain. Study the code carefully and answer the questions that follow.
Provided Class

package org.howard.edu.lsp.midterm.strategy;

public class PriceCalculator {

    public double calculatePrice(String customerType, double price) {

        double finalPrice = price;

        if (customerType.equals("REGULAR")) {
            finalPrice = price;
        }

        if (customerType.equals("MEMBER")) {
            finalPrice = price * 0.90;
        }

        if (customerType.equals("VIP")) {
            finalPrice = price * 0.80;
        }

        if (customerType.equals("HOLIDAY")) {
            finalPrice = price * 0.85;
        }

        return finalPrice;
    }
}

Part 1 - Design Evaluation (8 points)
Evaluate the design of the PriceCalculator class. Explain why the current implementation may lead to maintenance or extensibility problems as the system evolves.
Your answer must be written in a Markdown file named: design_evaluation.md
Place the file in the package:
org.howard.edu.lsp.midterm.strategy.doc
Part 2 - Refactoring Using a Design Pattern (10 points)
Refactor the provided design to use the Strategy Pattern discussed during lecture.
Your refactored implementation must be placed in the package:
org.howard.edu.lsp.midterm.strategy
Each class must be placed in its own .java file. Your implementation should demonstrate the use of the Strategy design pattern to support different discount behaviors.
All classes and public methods must include Javadoc comments.
Part 3 - Driver Program (5 points)
Create a Driver class that demonstrates that your Strategy Pattern implementation works correctly.
Your Driver class must be placed in the package:
org.howard.edu.lsp.midterm.strategy
Your driver must demonstrate the following cases using a purchase price of 100.0:
• REGULAR customer
• MEMBER customer
• VIP customer
• HOLIDAY customer
Your driver must produce output in the following format:
REGULAR: 100.0
MEMBER: 90.0
VIP: 80.0
HOLIDAY: 85.0
The output must be produced by executing your Strategy-based implementation, not by hardcoding the results.
Part 4 - Development Log (5 points)
Create a Markdown file named development_log_q3.md
Place the file in the package:
org.howard.edu.lsp.midterm.strategy.doc
This file must document any external resources used while completing this question, including AI tools, internet searches, documentation, lecture notes, or textbooks.
For each resource used, include what you searched or asked and how the information helped you complete the problem. If no external assistance was used, clearly state that in the file.
Grading Rubric (30 points)
Component	Points
Design evaluation (design_evaluation.md)	8
Correct refactoring using Strategy Pattern	10
Driver execution and required output	5
Javadoc documentation in classes and public methods	2
Development log documenting AI/internet use	5

Thought for a second

Here is a polished Part 1 design evaluation you can use for design_evaluation.md:

The PriceCalculator class has a design problem because it uses a chain of if statements to handle different customer types inside one method. This makes the class difficult to extend, since adding a new customer type requires modifying the existing method instead of adding new behavior in a separate place. As the number of pricing rules grows, the method will become larger, harder to read, and more error-prone.

The current design also mixes all discount logic into a single class, which creates tight coupling between the calculator and every customer type. This violates good object-oriented design because the class is doing too much and does not isolate each pricing behavior. A better approach would be to separate the discount behaviors into different classes so that each pricing rule can vary independently. That would improve maintainability, make the system easier to test, and allow new pricing strategies to be added without changing existing code.

If you want, I can do Part 2 next and lay out the Strategy Pattern classes in a way that matches the required package and driver output.

let's do part 2
Thought for a couple of seconds

Here is a Strategy Pattern refactor that fits the requirements and keeps each class in its own file.

PriceStrategy.java
package org.howard.edu.lsp.midterm.strategy;

/**
 * Represents a pricing strategy for calculating a final purchase price.
 *
 * @author Your Name
 */
public interface PriceStrategy {

    /**
     * Calculates the final price for the given base price.
     *
     * @param price the original purchase price
     * @return the final calculated price
     */
    double calculatePrice(double price);
}
RegularPriceStrategy.java
package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for regular customers.
 *
 * @author Your Name
 */
public class RegularPriceStrategy implements PriceStrategy {

    /**
     * Calculates the final price for a regular customer.
     *
     * @param price the original purchase price
     * @return the final calculated price
     */
    @Override
    public double calculatePrice(double price) {
        return price;
    }
}
MemberPriceStrategy.java
package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for member customers.
 *
 * @author Your Name
 */
public class MemberPriceStrategy implements PriceStrategy {

    /**
     * Calculates the final price for a member customer.
     *
     * @param price the original purchase price
     * @return the final calculated price
     */
    @Override
    public double calculatePrice(double price) {
        return price * 0.90;
    }
}
VIPPriceStrategy.java
package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for VIP customers.
 *
 * @author Your Name
 */
public class VIPPriceStrategy implements PriceStrategy {

    /**
     * Calculates the final price for a VIP customer.
     *
     * @param price the original purchase price
     * @return the final calculated price
     */
    @Override
    public double calculatePrice(double price) {
        return price * 0.80;
    }
}
HolidayPriceStrategy.java
package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for holiday customers.
 *
 * @author Your Name
 */
public class HolidayPriceStrategy implements PriceStrategy {

    /**
     * Calculates the final price for a holiday customer.
     *
     * @param price the original purchase price
     * @return the final calculated price
     */
    @Override
    public double calculatePrice(double price) {
        return price * 0.85;
    }
}
PriceCalculator.java
package org.howard.edu.lsp.midterm.strategy;

/**
 * Context class that uses a pricing strategy to calculate a final price.
 *
 * @author Your Name
 */
public class PriceCalculator {

    private PriceStrategy strategy;

    /**
     * Constructs a PriceCalculator with the given pricing strategy.
     *
     * @param strategy the pricing strategy to use
     */
    public PriceCalculator(PriceStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Sets the pricing strategy to use.
     *
     * @param strategy the new pricing strategy
     */
    public void setStrategy(PriceStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Calculates the final price using the current strategy.
     *
     * @param price the original purchase price
     * @return the final calculated price
     */
    public double calculatePrice(double price) {
        return strategy.calculatePrice(price);
    }
}

This is the core Strategy Pattern:

PriceStrategy = the common interface

RegularPriceStrategy, MemberPriceStrategy, VIPPriceStrategy, HolidayPriceStrategy = interchangeable behaviors

PriceCalculator = the context that uses one strategy at a time

Next, I can help with Part 3: the Driver so it prints exactly:
REGULAR: 100.0
MEMBER: 90.0
VIP: 80.0
HOLIDAY: 85.0

i want to use classes, not an interface. 
Thought for a second

Yes — you can use classes only and still keep the Strategy Pattern structure by making a small abstract base class instead of an interface.

Here is a version that fits that requirement.

PriceStrategy.java
package org.howard.edu.lsp.midterm.strategy;

/**
 * Represents a pricing strategy for calculating a final purchase price.
 *
 * @author Your Name
 */
public abstract class PriceStrategy {

    /**
     * Calculates the final price for the given base price.
     *
     * @param price the original purchase price
     * @return the final calculated price
     */
    public abstract double calculatePrice(double price);
}
RegularPriceStrategy.java
package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for regular customers.
 *
 * @author Your Name
 */
public class RegularPriceStrategy extends PriceStrategy {

    /**
     * Calculates the final price for a regular customer.
     *
     * @param price the original purchase price
     * @return the final calculated price
     */
    @Override
    public double calculatePrice(double price) {
        return price;
    }
}
MemberPriceStrategy.java
package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for member customers.
 *
 * @author Your Name
 */
public class MemberPriceStrategy extends PriceStrategy {

    /**
     * Calculates the final price for a member customer.
     *
     * @param price the original purchase price
     * @return the final calculated price
     */
    @Override
    public double calculatePrice(double price) {
        return price * 0.90;
    }
}
VIPPriceStrategy.java
package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for VIP customers.
 *
 * @author Your Name
 */
public class VIPPriceStrategy extends PriceStrategy {

    /**
     * Calculates the final price for a VIP customer.
     *
     * @param price the original purchase price
     * @return the final calculated price
     */
    @Override
    public double calculatePrice(double price) {
        return price * 0.80;
    }
}
HolidayPriceStrategy.java
package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for holiday customers.
 *
 * @author Your Name
 */
public class HolidayPriceStrategy extends PriceStrategy {

    /**
     * Calculates the final price for a holiday customer.
     *
     * @param price the original purchase price
     * @return the final calculated price
     */
    @Override
    public double calculatePrice(double price) {
        return price * 0.85;
    }
}
PriceCalculator.java
package org.howard.edu.lsp.midterm.strategy;

/**
 * Context class that uses a pricing strategy to calculate a final price.
 *
 * @author Your Name
 */
public class PriceCalculator {

    private PriceStrategy strategy;

    /**
     * Constructs a PriceCalculator with the given pricing strategy.
     *
     * @param strategy the pricing strategy to use
     */
    public PriceCalculator(PriceStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Sets the pricing strategy to use.
     *
     * @param strategy the new pricing strategy
     */
    public void setStrategy(PriceStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Calculates the final price using the current strategy.
     *
     * @param price the original purchase price
     * @return the final calculated price
     */
    public double calculatePrice(double price) {
        return strategy.calculatePrice(price);
    }
}

This still gives you the Strategy Pattern:

PriceStrategy is the common base type

each customer type has its own class

PriceCalculator uses whichever strategy object is set

Next, I can help with Part 3: the driver using this class-based version.

now part 3

Here is Part 3: Driver that works with your class-based Strategy Pattern and produces the exact required output.

package org.howard.edu.lsp.midterm.strategy;

/**
 * Driver class to demonstrate the Strategy Pattern for price calculation.
 *
 * @author Your Name
 */
public class Driver {

    /**
     * Main method to test different pricing strategies.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        double price = 100.0;

        PriceCalculator calculator;

        // REGULAR
        calculator = new PriceCalculator(new RegularPriceStrategy());
        System.out.println("REGULAR: " + calculator.calculatePrice(price));

        // MEMBER
        calculator.setStrategy(new MemberPriceStrategy());
        System.out.println("MEMBER: " + calculator.calculatePrice(price));

        // VIP
        calculator.setStrategy(new VIPPriceStrategy());
        System.out.println("VIP: " + calculator.calculatePrice(price));

        // HOLIDAY
        calculator.setStrategy(new HolidayPriceStrategy());
        System.out.println("HOLIDAY: " + calculator.calculatePrice(price));
    }
}
Expected Output
REGULAR: 100.0
MEMBER: 90.0
VIP: 80.0
HOLIDAY: 85.0

This satisfies all requirements:

uses Strategy Pattern (not hardcoded)

demonstrates all four customer types

uses 100.0 as specified

matches output format exactly

If you want, we can quickly finish Part 4 (development log)—that one is easy points.