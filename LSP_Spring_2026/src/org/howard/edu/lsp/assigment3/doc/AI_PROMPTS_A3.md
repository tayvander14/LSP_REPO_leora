

You said:
i have a project to complete. below are the assignment details. i will provide you with my code from assignment 2. i need to make ti more object-oriented. to begin, i know i want to use more classes. help me to brainstorm how to make it more object oriented. don't give me any code yet. i just want ideas.

CSCI 363 – Assignment 3: Object-Oriented Redesign of Your ETL Pipeline
Due: Friday, February 20th, 11:59PM 
Purpose
In Assignment 2, you built an ETL pipeline in Java. In this assignment, you will redesign your solution to be more object-oriented. The goal is to practice object-oriented decomposition, not perfection. You must also use a generative AI assistant to help with the redesign and explain how you used or adapted its suggestions.
Learning Goals
• Apply object-oriented decomposition to requirements.
• Practice basic OO concepts: object, class, encapsulation, inheritance, polymorphism.
• Use generative AI as a helper and reflect on your choices.
• Strengthen attention to coding standards and documentation.
The Task
1. Start with your Assignment 2 solution.
2. Use a generative AI assistant to brainstorm ways to make it more object-oriented.
3. Redesign your solution into multiple classes with clearer responsibilities.
4. Your program must still meet the exact same requirements as Assignment 2: same inputs, same outputs, same transformations, same error handling, and same relative paths.
Reflection (Required)
Write 1–2 pages that compare your Assignment 2 and Assignment 3 implementations. Be specific:
• What is different about the design?
• How is Assignment 3 more object-oriented?
• Which OO ideas (object, class, encapsulation, inheritance, polymorphism) did you use?
• Explain how you tested to confirm Assignment 3 works the same as Assignment 2.
Deliverables
1. Source Code for Assignment 3:
   - Must be under the package org.howard.edu.lsp.assignment3.
   - One public class per file.
   - Include Javadocs for each class and all public methods. (You may use your AI assistant to help generate Javadocs, but you must review and edit them for accuracy.)
   - Code must follow the coding standards discussed in lecture.

2. Output File: data/transformed_products.csv.

3. Reflection: src/org/howard/edu/lsp/assignment3/doc/REFLECTION_A3.md.

4. AI Transcript File: src/org/howard/edu/lsp/assignment3/doc/AI_PROMPTS_A3.md.
   - Include all prompts you asked the AI responses.
Your project should look like this (filenames below are examples/placeholders — use your own class names):
Note: package for assignment3 is an additional package to assignment1 and assignment2.  You are only creating a new package.
project-root/
├── src/
│   └── org/
│       └── howard/
│           └── edu/
│               └── lsp/
│                   └── assignment3/
│                       ├── <YourClass1>.java
│                       ├── <YourClass2>.java
│                       ├── <YourClass3>.java
│                       └── doc/
│                           ├── REFLECTION.md
│                           └── AI_PROMPTS.md
├── data/
│   ├── products.csv
│   └── transformed_products.csv
Grading Rubric (100 pts)
• Correctness (same outputs/behavior as A2) – 30 pts
• Object-Oriented Decomposition – 30 pts
• Reflection (comparison of A2 vs A3, clarity, specificity) – 25 pts
• AI Transcript File – 10 pts
• Code Style & Documentation – 5 pts
  (Correct package, Javadocs included, lecture coding standards followed)
Submission Checklist
☐ All A3 source files are under src/org/howard/edu/lsp/assignment3/.
☐ data/transformed_products.csv is produced and correct.
☐ Package name is exactly org.howard.edu.lsp.assignment3.
☐ One public class per file; code follows lecture coding standards.
☐ Javadocs included for all classes and public methods (reviewed for accuracy).
☐ src/org/howard/edu/lsp/assignment3/doc/REFLECTION_A3.md compares A2 vs A3.
☐ src/org/howard/edu/lsp/assignment3/doc/AI_PROMPTS_A3.md lists several prompts + excerpts.
☐ Program verified with missing input and empty input cases.
☐ Submission includes source code and the generated output file.

ChatGPT said:
Great — this is exactly the right way to start. Let’s brainstorm design ideas before touching code.

Since your Assignment 2 was an ETL pipeline, you likely had something like:

Read CSV

Parse rows

Transform data (e.g., clean, compute fields, filter, etc.)

Write transformed CSV

Handle errors

In many A2 solutions, all of that ends up in:

One large main method, or

A few static helper methods in one class

Now we want to decompose responsibilities into objects.

Step 1: Think in Terms of Responsibilities
Instead of thinking:

“What steps does my program do?”

Think:

“What objects exist in this domain, and what is each responsible for?”

Your pipeline has at least these conceptual parts:

Input source

Raw record

Transformation logic

Output destination

Error handling

Orchestration of the pipeline

Each of those can become a class.

Big Design Idea #1: Separate the ETL Stages into Classes
Instead of one big class, create something like:

1. Product (Domain Object)
Represents one row of data.

Responsibilities:

Store product fields (id, name, price, category, etc.)

Encapsulate product-related logic

Possibly include transformation logic (like discount calculation)

Why this is OO:

Each row becomes an object.

You move from “arrays of strings” to meaningful objects.

You use encapsulation (private fields + getters/setters).

2. ProductExtractor
Responsible ONLY for:

Reading the CSV file

Converting lines into Product objects

It should NOT:

Transform data

Write output

Apply business rules

That separation shows clear responsibility boundaries.

3. ProductTransformer
Responsible ONLY for:

Applying business rules

Cleaning data

Calculating derived values

Handling filtering rules

This is where transformation logic moves out of main.

You could:

Accept a Product

Return a transformed Product

Or modify the object directly (depending on your design preference).

4. ProductLoader
Responsible ONLY for:

Writing products to transformed_products.csv

No reading.
No transformation.
Just output formatting and file writing.

5. ETLPipeline (Orchestrator)
Responsible for:

Coordinating extractor → transformer → loader

Handling overall execution flow

This replaces a giant main.

main should just:

Instantiate the pipeline

Call something like run()

That’s clean OO separation.

Big Design Idea #2: Introduce Abstractions (Interfaces)
If you want to show stronger OO design (especially polymorphism), you could introduce interfaces.

Example ideas (conceptual only):

Extractor<T>

Transformer<T>

Loader<T>

Then your product classes implement them:

ProductCSVExtractor implements Extractor<Product>

ProductTransformer implements Transformer<Product>

ProductCSVLoader implements Loader<Product>

Why this is good:

Demonstrates polymorphism.

Shows design for extensibility.

Makes your reflection stronger.

Even if you only implement one version now, it shows professional-level thinking.

Big Design Idea #3: Encapsulation Improvements
Ask yourself:

In A2, did you:

Pass around raw strings?

Use public variables?

Expose file paths everywhere?

Mix validation logic everywhere?

In A3, you can:

Make all fields private

Provide controlled getters/seters

Hide parsing logic inside Product

Hide validation inside transformation class

Example conceptual improvement:
Instead of:

String[] columns = line.split(",");
double price = Double.parseDouble(columns[3]);
You could:

Have Product.fromCSV(line) (factory-style idea)

Let Product handle parsing

That improves cohesion.

Big Design Idea #4: Improve Error Handling via Objects
Instead of:

try/catch blocks scattered everywhere

You could:

Create a custom exception like DataValidationException

Let transformer throw it

Let pipeline handle it

This demonstrates:

Encapsulation

Separation of concerns

Clean error responsibility

Big Design Idea #5: Make the Design Testable
Ask:
Can I test transformation without reading a file?

In A2, maybe not.

In A3:

You should be able to create a Product object manually

Pass it to ProductTransformer

Check result

That shows better object design.

Big Design Idea #6: Consider Inheritance (If Appropriate)
Only use inheritance if it makes sense.

Examples (optional ideas):

Option A: Abstract Record
If your ETL could theoretically process different types of records:

abstract class Record

class Product extends Record

This may be overkill unless it fits naturally.

Option B: Base Transformer
abstract class Transformer<T>

class ProductTransformer extends Transformer<Product>

This better demonstrates inheritance without forcing it unnaturally.

Remember: don’t add inheritance just to “check a box.”

Big Design Idea #7: Reduce Static Everything
If A2 was very static-heavy:

Move toward instance-based design.

Create objects and call methods on them.

Avoid static utility dumping grounds.

OO is about objects interacting.

A Strong, Clean A3 Structure Might Look Like:
You might end up with:

Product.java
ProductExtractor.java
ProductTransformer.java
ProductLoader.java
ETLPipeline.java
Assignment3Driver.java (contains main)
That would be 6 classes — very reasonable.

Questions to Ask Yourself Before We Go Further
To guide the redesign well, think about:

In A2, where is most of your logic located?

Do you already have helper methods?

Are you currently using objects at all, or mostly primitives?

Is transformation logic mixed with file I/O?

When you send your Assignment 2 code, we can:

Identify poor cohesion

Identify tight coupling

Decide what to extract into classes

Decide where polymorphism makes sense

Avoid unnecessary complexity

If you'd like, next we can:

Sketch a rough class responsibility diagram, OR

Analyze your A2 code and map it to an improved OO structure.

Your instinct to “use more classes” is correct — but the key is better responsibility boundaries, not just more files.


You said:
here is my assignment 2 code:
package org.howard.edu.lsp.assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ETLPipeline {

    public static void main(String[] args) {

    	// Define the path of the input file in the data/ directory
        String inputPath = "data/products.csv";
        // Define the path of the output file in the data/ directory
        String outputPath = "data/transformed_products.csv";

        // Initialize variables for later use
        int rowsRead = 0;
        int rowsWritten = 0;
        int rowsSkipped = 0;

        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
        	// Read a CSV file named products.csv from the data/ directory
            reader = new BufferedReader(new FileReader(inputPath));
            // Create a new CSV file to write the transformed data
            writer = new BufferedWriter(new FileWriter(outputPath));

            // Always write a header row in the correct output column order: ProductID, Name, Price, Category, PriceRange
            writer.write("ProductID,Name,Price,Category,PriceRange");
            writer.newLine();

            // Read in the first row (header) and do not transform it
            String line = reader.readLine();
            // if there is no header row, print a summary 
            if (line == null) {
                printSummary(rowsRead, rowsWritten, rowsSkipped, outputPath);
                return;
            }

            // Reads a full line from the CSV
            while ((line = reader.readLine()) != null) {
                // Counts number of rows read (non-header lines encountered, including bad ones)
            	rowsRead++;

            	// Skips and counts any skipped that are blank
                if (line.trim().isEmpty()) {
                    rowsSkipped++;
                    continue;
                }

                // Counts any rows skipped that do not contain exactly four comma-separated fields
                String[] parts = line.split(",");
                if (parts.length != 4) {
                    rowsSkipped++;
                    continue;
                }

                try {
                	// Trims whitespace around each field before processing
                    int productId = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim().toUpperCase();
                    BigDecimal price = new BigDecimal(parts[2].trim());
                    String category = parts[3].trim();

                    // If the category is "Electronics", apply a 10% discount to the price
                    boolean wasElectronics = category.equals("Electronics");

                    if (wasElectronics) {
                        price = price.multiply(new BigDecimal("0.9"));
                    }

                    // Prices must be rounded to exactly two decimal places using round-half-up.
                    price = price.setScale(2, RoundingMode.HALF_UP);

                    // If the final rounded price is strictly greater than $500.00 AND the original category was "Electronics", change the category to "Premium Electronics".
                    if (wasElectronics && price.compareTo(new BigDecimal("500.00")) > 0) {
                        category = "Premium Electronics";
                    }

                    // Update field PriceRange based on the final rounded price
                    String priceRange = determinePriceRange(price);

                    // Write the transformed data to data/transformed_products.csv
                    writer.write(productId + "," +
                                 name + "," +
                                 price.toString() + "," +
                                 category + "," +
                                 priceRange);
                    writer.newLine();

                    // Counts any rows transformed (written to output)
                    rowsWritten++;

                } catch (Exception e) {
                	// Counts any rows skipped that have a ProductID or Price that cannot be parsed
                    rowsSkipped++;
                }
            }

            // At the end of execution, print a summary
            printSummary(rowsRead, rowsWritten, rowsSkipped, outputPath);

        } catch (IOException e) {
        	// The file data/products.csv does not exist; your program must print a clear error and exit cleanly.
        	System.out.println("Error: Unable to read input file " + inputPath + ".");
        } finally {
        	// Ensure files are closed
            try {
                if (reader != null) reader.close();
                if (writer != null) writer.close();
            } catch (IOException e) {
                // Ignore
            }
        }
    }

    /*
     * Determine PriceRange based on the final rounded price: 
     * -  <= $10.00 → Low
     * -  > $10.00 and <= $100.00 → Medium
     * - > $100.00 and <= $500.00 → High
     * - > $500.00 → Premium
     */    private static String determinePriceRange(BigDecimal price) {
        if (price.compareTo(new BigDecimal("10.00")) <= 0) {
            return "Low";
        } else if (price.compareTo(new BigDecimal("100.00")) <= 0) {
            return "Medium";
        } else if (price.compareTo(new BigDecimal("500.00")) <= 0) {
            return "High";
        } else {
            return "Premium";
        }
    }

    /*
     * At the end of execution, print a summary that includes: 
     * - Number of rows read (non-header lines encountered, including bad ones)
     * - Number of rows transformed (written to output)
     * - Number of rows skipped
     * - Output file path written 
     */
    
    private static void printSummary(int read, int written, int skipped, String outputPath) {
        System.out.println("Rows read: " + read);
        System.out.println("Rows transformed: " + written);
        System.out.println("Rows skipped: " + skipped);
        System.out.println("Output written to: " + outputPath);
    }
}

ChatGPT said:
Excellent — this is a very clean A2 solution. That actually makes your redesign easier because the logic is already correct and well-structured. Now we just need to separate responsibilities into proper objects.

Let’s analyze what you currently have and then brainstorm how to redesign it.

🔎 What’s Happening in Your A2 Design?
Right now, one class (ETLPipeline) does everything:

It handles:
File I/O (reading + writing)

Parsing CSV

Data validation

Business rules (discount logic, premium logic)

Price rounding

Price range classification

Error handling

Counting rows

Printing summary

That’s a lot of responsibility for one class.

In OO terms:

❌ Low cohesion

❌ High responsibility concentration

❌ Everything is static

❌ No domain object representing a Product

This is exactly what Assignment 3 wants you to improve.

🧠 Brainstorm: How to Make This More Object-Oriented
We’ll go step by step.

1️⃣ Create a Product Class (Core Domain Object)
Right now, a product is just:

int productId

String name

BigDecimal price

String category

String priceRange

But that information is temporary and scattered.

Instead:
Create a Product class that:

Responsibilities:
Stores product fields (private)

Ensures encapsulation

Possibly determines its own price range

Possibly applies transformations to itself

Why this is a big improvement:
Each CSV row becomes a real object.

You move from procedural to object modeling.

You introduce encapsulation.

Cleaner logic separation.

This is your most important OO improvement.

2️⃣ Separate ETL Into 3 Clear Classes
Right now ETL is mixed inside main.

You can break it into:

A. ProductExtractor
Responsible for:
Opening the file

Reading lines

Creating Product objects

Skipping invalid rows

It should NOT:

Apply discount logic

Write output

Determine price range

This improves cohesion dramatically.

B. ProductTransformer
This is where your business logic moves.

Responsible for:
Electronics 10% discount

Rounding

Premium category update

Determining price range

You already have transformation logic — it’s just inside main. Move it here.

Now you have:

File I/O separated

Business rules separated

That’s textbook OO design.

C. ProductLoader
Responsible for:
Writing header

Writing transformed products

Handling output file creation

It should NOT:

Know how discounts work

Know how parsing works

Just output responsibility.

3️⃣ Create an ETLPipeline Orchestrator Class
Right now main does all coordination.

Instead:

Assignment3Driver → contains only main

ETLPipeline → has a run() method

ETLPipeline would:
Call extractor

Loop through products

Call transformer

Pass results to loader

Track row counts

Print summary

Now:

Flow control is separated from implementation details.

Each class has one job.

4️⃣ Move determinePriceRange Into a Better Location
Right now it's a static helper.

Options:

Option A: Move it into ProductTransformer
Makes sense if transformation determines classification.

Option B: Move it into Product
This is even more object-oriented:

Instead of:

String priceRange = determinePriceRange(price);
You might conceptually have:

product.updatePriceRange();
That increases encapsulation.

This would be a strong OO improvement.

5️⃣ Improve Encapsulation
Right now everything is primitive and local.

In A3:

Make all Product fields private

No direct external field access

Provide controlled getters

Encapsulation = one of your required reflection concepts.

6️⃣ Consider Introducing an Interface (Optional But Strong)
If you want stronger OO points:

You could define:

interface Transformer<T>
Then:

class ProductTransformer implements Transformer<Product>
Now you demonstrate:

Polymorphism

Abstraction

Clean architecture thinking

Even if you only implement one transformer.

That would make your reflection much stronger.

7️⃣ Improve Error Handling with Better Separation
Currently:

Parsing errors

Format errors

Business logic errors

Are all caught in the same loop.

In A3, you could:

Let ProductExtractor handle malformed CSV

Let ProductTransformer assume valid objects

Keep responsibilities clean

This improves clarity.

8️⃣ Remove Static-Heavy Design
Your entire program is static right now.

In A3:

Only main should be static.

Everything else should be instance-based.

Objects should collaborate.

That’s real object orientation.

🧱 A Strong, Clean A3 Structure Could Look Like:
Product.java
ProductExtractor.java
ProductTransformer.java
ProductLoader.java
ETLPipeline.java
Assignment3Driver.java
That’s 6 classes.
Very clean.
Very reasonable.
Not over-engineered.

💡 What Will Change Conceptually?
Assignment 2:
Procedural pipeline in one class.

Assignment 3:
Objects collaborating:

Driver → ETLPipeline
ETLPipeline → Extractor → Product
ETLPipeline → Transformer → Product
ETLPipeline → Loader → Product
This shows:

Object collaboration

Encapsulation

Clear separation of concerns

Possible polymorphism

Improved maintainability

📝 Reflection Ideas (Already Emerging)
When you write your reflection, you’ll be able to say:

A2 was procedural and centralized.

A3 introduces domain modeling (Product).

Responsibilities are separated into focused classes.

Encapsulation protects product data.

Polymorphism used via Transformer interface (if you add it).

Testing improved because transformation can be tested independently.

🚀 Next Step
Before writing code, I recommend:

Decide:

Do you want just clean separation?

Or also include an interface for polymorphism?

Decide:

Should Product contain transformation logic?

Or should transformation stay in a separate class?

When you answer those two design choices, we can design your A3 structure cleanly and intentionally — without overcomplicating it.


You said:
can you please explain polymorphism?

ChatGPT said:
Absolutely — let’s break this down clearly and in a way that connects directly to your ETL assignment.

📌 What Is Polymorphism?
Polymorphism means “many forms.”

In object-oriented programming, it means:

You can treat different objects in the same way if they share a common interface or parent type.

In other words:

You write code that works with a general type, and different specific implementations can plug in without changing the code that uses them.

🧠 Simple Everyday Analogy
Think of a remote control.

The remote has a button called:

powerOn()
You can use the same remote interface to power on:

A TV

A DVD player

A Sound system

Each device implements powerOn() differently.

But the remote doesn’t care what specific device it is.

That’s polymorphism.

🔧 Technical Definition
Polymorphism usually happens through:

Interfaces

Inheritance (parent/child classes)

Method overriding

It allows:

One method call

Multiple possible behaviors

👇 Simple Java Example (Conceptual Only)
Imagine this interface:

interface Animal {
    void speak();
}
Now different classes implement it:

class Dog implements Animal
class Cat implements Animal
Each defines speak() differently.

Now you can do:

Animal a = new Dog();
Animal b = new Cat();

a.speak();  // Dog behavior
b.speak();  // Cat behavior
You’re calling the same method (speak()),
but the behavior changes depending on the object.

That’s polymorphism.

🎯 How This Applies to Your ETL Project
Let’s connect it directly to your assignment.

Right now you have transformation logic hard-coded inside one class.

But imagine this design:

interface Transformer<T> {
    T transform(T input);
}
Then you create:

class ProductTransformer implements Transformer<Product>
Now your pipeline doesn’t need to know what specific transformer it’s using.

It can just say:

Transformer<Product> transformer
Later, you could create:

DiscountOnlyTransformer

CategoryOnlyTransformer

PremiumUpgradeTransformer

And your pipeline would still work without changing.

That flexibility is polymorphism.

📦 Why Professors Like Polymorphism
Because it shows:

Abstraction

Extensibility

Decoupling

Clean architecture thinking

It prevents this:

if (type == 1) { ... }
else if (type == 2) { ... }
else if (type == 3) { ... }
Instead, behavior lives inside objects.

🔁 Another ETL-Specific Example
Imagine in the future you needed to process:

Products

Customers

Orders

Instead of writing 3 separate pipelines, you could have:

Extractor<T>
Transformer<T>
Loader<T>
Then:

ProductExtractor
CustomerExtractor
OrderExtractor
Your pipeline would stay the same — only the implementation changes.

That’s polymorphism working through generics and interfaces.

📚 The 2 Types of Polymorphism You Should Know
1️⃣ Compile-Time Polymorphism
(Method overloading)

Same method name, different parameters.

Example:

calculatePrice(int quantity)
calculatePrice(int quantity, double discount)
Not super important for your assignment.

2️⃣ Runtime Polymorphism (Most Important)
(Method overriding via interfaces/inheritance)

This is what your professor wants you to understand.

The method that runs is determined at runtime based on the object type.

🎓 For Your Reflection
If you use polymorphism in A3, you could write something like:

In Assignment 3, I introduced polymorphism by defining a Transformer interface. The ETL pipeline interacts with the Transformer abstraction rather than a concrete implementation. This allows different transformation strategies to be substituted without modifying the pipeline logic.

That sounds strong and professional.

🧩 Important: Don’t Force It
Polymorphism is powerful — but don’t add it just to “check a box.”

For your assignment, it’s completely acceptable to:

Use a Product class

Separate Extractor / Transformer / Loader

Possibly add one interface

That’s enough.

Overengineering hurts grades.

🚦 Quick Check
To confirm you understand:

If I write:

Transformer<Product> transformer = new ProductTransformer();
Why is that polymorphism?

(Answer: because the variable type is the interface, but the object is a specific implementation.)

If you'd like, I can now:

Show you a very simple ETL-specific polymorphism example

Or help you decide whether you should include it in your redesign

Your A2 logic is solid — now we’re just upgrading the architecture.


You said:
i think i should include it in my redesign. 

ChatGPT said:
That’s a smart choice 👍 — including polymorphism will strengthen both your Object-Oriented Decomposition score and your Reflection section.

Now let’s think carefully about how to include it in a clean, natural way (not forced or overcomplicated).

🎯 Goal: Add Polymorphism Without Overengineering
You don’t need something huge.

You just need:

One meaningful interface

At least one class implementing it

Your pipeline using the abstraction (not the concrete class)

That alone demonstrates runtime polymorphism.

✅ The Cleanest Place to Use Polymorphism in Your Project
The best candidate in your design is:

👉 The Transformer
Why?

Because:

Transformation logic is behavior.

Behavior is exactly what polymorphism is good for.

It’s realistic that transformation rules could change.

🧠 Conceptual Design
You introduce an interface:

Transformer<T>
It defines something like:

T transform(T input);
Now you create:

ProductTransformer implements Transformer<Product>
Your ETLPipeline doesn’t know about ProductTransformer specifically.

It just knows:

Transformer<Product>
That’s polymorphism.

🔄 Why This Is Legitimate (Not Artificial)
Right now your pipeline is tightly coupled:

call specific transformation logic here
With polymorphism:

pipeline calls transform()
But it doesn’t care how transformation works.

Later, you could swap in:

NoDiscountTransformer

HolidayDiscountTransformer

DebugTransformer

Without touching pipeline code.

That’s real extensibility.

🏗 What Your A3 Architecture Might Look Like
Here’s a clean design incorporating polymorphism:

Product.java
Transformer.java              (interface)
ProductTransformer.java       (implements Transformer<Product>)
ProductExtractor.java
ProductLoader.java
ETLPipeline.java
Assignment3Driver.java
That’s 7 classes.
Still clean.
Still manageable.

🧩 How Polymorphism Appears in the Pipeline
Instead of:

ProductTransformer transformer = new ProductTransformer();
You would conceptually have:

Transformer<Product> transformer = new ProductTransformer();
That variable type being the interface is the key.

The pipeline interacts with the abstraction.

💡 Even Stronger (Optional)
If you wanted to demonstrate even deeper OO thinking, you could also create:

Extractor<T>
Loader<T>
But that may be overkill.

One interface (Transformer) is more than enough.

📝 What You’ll Be Able to Say in Your Reflection
You’ll be able to clearly write:

Assignment 2 used a procedural design.

Assignment 3 introduces polymorphism through a Transformer interface.

The ETLPipeline depends on an abstraction rather than a concrete implementation.

This reduces coupling and improves extensibility.

The transformation behavior can be changed without modifying the pipeline.

That directly answers the rubric.

⚠️ Important: Keep It Simple
Do NOT:

Create 5 unnecessary interfaces

Add abstract classes just to show inheritance

Make everything generic unless needed

Professors can tell when something is forced.

Clean and intentional is better.

🧠 Before We Move Forward
There are two design decisions you should think about:

1️⃣ Should transformation modify the Product object?
OR

2️⃣ Should it return a new transformed Product?
Both are valid — but they reflect different design philosophies.

Modifying = simpler

Returning new object = more functional / cleaner separation

For your assignment, modifying is completely acceptable.

If you’d like, next we can:

Design the full class responsibility breakdown for A3

Or map your current A2 code directly into this new structure step-by-step (without writing code yet)

You're making good architectural decisions here.


You said:
let's return a new object. 

ChatGPT said:
Excellent choice 👌 — returning a new object is the more object-oriented and cleaner design. It shows stronger separation of concerns and will make your reflection much better.

Let’s think through what that decision means architecturally.

🎯 What It Means to “Return a New Object”
Instead of:

Taking a Product and modifying its fields

You will:

Take an original Product

Create a new Product with transformed values

Return that new object

So conceptually:

Product transformed = transformer.transform(original);
Now:

original remains unchanged

transformed contains discounted price, updated category, price range, etc.

That’s cleaner and more professional.

🧠 Why This Is Stronger OO Design
1️⃣ Immutability Mindset
You’re treating the input as raw data.
Transformation produces new data.

This avoids:

Side effects

Hidden state changes

Hard-to-track bugs

2️⃣ Clear Separation of Responsibilities
Product = represents data

ProductTransformer = creates a new transformed version

Your Product class does NOT:

Apply discounts

Know about Electronics rules

Decide Premium logic

That behavior belongs in the transformer.

Very clean.

3️⃣ Easier Testing
You can:

Create a Product manually

Pass it into transform()

Check the returned Product

No file I/O required.

That’s strong design.

🏗 Updated A3 Architecture (With Your Decision)
Now your structure becomes very clean:

Product.java
Transformer.java
ProductTransformer.java
ProductExtractor.java
ProductLoader.java
ETLPipeline.java
Assignment3Driver.java
📦 Responsibility Breakdown (Finalized)
Let’s define them clearly.

1️⃣ Product (Domain Model)
Represents one product record.

Fields:

productId

name

price

category

priceRange

Responsibilities:

Store data

Provide getters

Possibly validate constructor inputs

No transformation logic

Important:
Make fields private.
Provide getters only (you may not even need setters).

2️⃣ Transformer<T> (Interface)
Defines behavior:

Conceptually:

T transform(T input);
This introduces:

Polymorphism

Abstraction

3️⃣ ProductTransformer
Implements:

Transformer<Product>
Responsibilities:

Apply 10% Electronics discount

Round to 2 decimal places

Update category to Premium Electronics

Determine price range

Return a NEW Product

It contains:

Your current discount logic

Your rounding logic

Your determinePriceRange logic

This is where most of your A2 logic moves.

4️⃣ ProductExtractor
Responsibilities:

Open input file

Read lines

Validate format

Convert rows into Product objects

Track rowsRead and rowsSkipped (optional — or pipeline tracks)

It returns:

A list of valid Products
OR

Products one at a time (stream-style)

List is simpler.

5️⃣ ProductLoader
Responsibilities:

Write header

Write transformed products

Output CSV formatting

No transformation logic.

6️⃣ ETLPipeline
Responsibilities:

Coordinate everything

Call extractor

Loop through products

Call transformer

Send results to loader

Count rowsWritten

Print summary

This replaces your big main method.

7️⃣ Assignment3Driver
Contains only:

main()

Instantiates ETLPipeline

Calls run()

That’s it.

Very clean.

🔄 How Polymorphism Actually Works in Your Design
Inside ETLPipeline, you will conceptually have:

Transformer<Product> transformer = new ProductTransformer();
The pipeline only knows it’s using a Transformer.

If you later wrote:

Transformer<Product> transformer = new HolidayProductTransformer();
The pipeline code would not change.

That’s runtime polymorphism.

🧠 Why Returning a New Object Makes This Even Stronger
Because now:

The transformer behaves like a function.

It has no side effects.

It doesn’t mutate input.

It returns a new result.

This demonstrates thoughtful design — not just “splitting classes.”

Your professor will notice this in your reflection.

📘 Reflection Gold You’ll Be Able to Write
You can say:

In Assignment 2, transformation logic was embedded in procedural code.

In Assignment 3, I introduced a Transformer interface to demonstrate polymorphism.

The ETLPipeline depends on the Transformer abstraction rather than a concrete implementation.

The transform method returns a new Product object rather than modifying the original, improving separation of concerns and reducing side effects.

This makes the system more extensible and testable.

That hits:

Object

Class

Encapsulation

Polymorphism

Clean design

All rubric points.

🚦 Before We Move to Implementation
One more design decision:

Do you want:

Option A:
Extractor returns List<Product>

OR

Option B:
Extractor processes line-by-line and pipeline transforms immediately?

Option A is simpler and easier to reason about.
Option B is more streaming-based but slightly more complex.

For your assignment, I recommend Option A.

What would you like to do?


You said:
i agree; let's do option A and generate the code. 

ChatGPT said:
Perfect 👍 — that’s a clean, well-reasoned design.

Before I generate the full code, I’m going to:

✅ Confirm the final architecture

✅ Map each A2 responsibility to a class

✅ Ensure behavior remains identical (same inputs, outputs, summary behavior, and error handling)

Then generate the Assignment 3 code under
org.howard.edu.lsp.assignment3

✅ Final A3 Architecture (Confirmed)
We are building:

org.howard.edu.lsp.assignment3
│
├── Assignment3Driver.java
├── ETLPipeline.java
├── Product.java
├── Transformer.java
├── ProductTransformer.java
├── ProductExtractor.java
├── ProductLoader.java
Design Decisions Locked In:
✔ Product is a domain object

✔ Transformer interface demonstrates polymorphism

✔ ProductTransformer returns a NEW Product

✔ Extractor returns List<Product> (Option A)

✔ Loader writes header + transformed products

✔ Pipeline coordinates everything

✔ Same file paths

✔ Same error handling behavior

✔ Same summary output format

✔ Same rounding and transformation logic

⚠️ Important: Behavior Must Match A2 Exactly
We must preserve:

Same header:

ProductID,Name,Price,Category,PriceRange
Same Electronics discount rule

Same Premium rule

Same rounding

Same skipped row counting rules

Same summary print format

Same missing file error message

Same relative paths:

data/products.csv
data/transformed_products.csv
🧠 Implementation Strategy
We will:

Let ProductExtractor track:

rowsRead

rowsSkipped

Let ETLPipeline track:

rowsWritten

This keeps counting logic clean and equivalent to A2.

📦 Now Generating the Full Assignment 3 Code
Below is your complete implementation.

1️⃣ Assignment3Driver.java
package org.howard.edu.lsp.assignment3;

/**
 * Entry point for Assignment 3 ETL Pipeline.
 */
public class Assignment3Driver {

    /**
     * Main method that starts the ETL pipeline.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        ETLPipeline pipeline = new ETLPipeline();
        pipeline.run();
    }
}
2️⃣ ETLPipeline.java
package org.howard.edu.lsp.assignment3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Coordinates the Extract, Transform, Load process.
 */
public class ETLPipeline {

    private static final String INPUT_PATH = "data/products.csv";
    private static final String OUTPUT_PATH = "data/transformed_products.csv";

    /**
     * Executes the ETL pipeline.
     */
    public void run() {
        int rowsWritten = 0;

        ProductExtractor extractor = new ProductExtractor(INPUT_PATH);
        List<Product> products;

        try {
            products = extractor.extract();
        } catch (IOException e) {
            System.out.println("Error: Unable to read input file " + INPUT_PATH + ".");
            return;
        }

        Transformer<Product> transformer = new ProductTransformer();
        List<Product> transformedProducts = new ArrayList<>();

        for (Product product : products) {
            Product transformed = transformer.transform(product);
            transformedProducts.add(transformed);
            rowsWritten++;
        }

        ProductLoader loader = new ProductLoader(OUTPUT_PATH);

        try {
            loader.load(transformedProducts);
        } catch (IOException e) {
            System.out.println("Error: Unable to write output file " + OUTPUT_PATH + ".");
            return;
        }

        printSummary(extractor.getRowsRead(),
                     rowsWritten,
                     extractor.getRowsSkipped(),
                     OUTPUT_PATH);
    }

    /**
     * Prints execution summary.
     */
    private void printSummary(int read, int written, int skipped, String outputPath) {
        System.out.println("Rows read: " + read);
        System.out.println("Rows transformed: " + written);
        System.out.println("Rows skipped: " + skipped);
        System.out.println("Output written to: " + outputPath);
    }
}
3️⃣ Product.java
package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/**
 * Represents a product record.
 */
public class Product {

    private final int productId;
    private final String name;
    private final BigDecimal price;
    private final String category;
    private final String priceRange;

    /**
     * Constructs a Product.
     */
    public Product(int productId, String name, BigDecimal price, String category, String priceRange) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.priceRange = priceRange;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getPriceRange() {
        return priceRange;
    }
}
4️⃣ Transformer.java
package org.howard.edu.lsp.assignment3;

/**
 * Generic transformer interface demonstrating polymorphism.
 *
 * @param <T> the type being transformed
 */
public interface Transformer<T> {

    /**
     * Transforms an input object and returns a new transformed object.
     *
     * @param input the original object
     * @return transformed object
     */
    T transform(T input);
}
5️⃣ ProductTransformer.java
package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Applies transformation rules to Product objects.
 */
public class ProductTransformer implements Transformer<Product> {

    @Override
    public Product transform(Product input) {

        int productId = input.getProductId();
        String name = input.getName().toUpperCase();
        BigDecimal price = input.getPrice();
        String category = input.getCategory();

        boolean wasElectronics = category.equals("Electronics");

        if (wasElectronics) {
            price = price.multiply(new BigDecimal("0.9"));
        }

        price = price.setScale(2, RoundingMode.HALF_UP);

        if (wasElectronics && price.compareTo(new BigDecimal("500.00")) > 0) {
            category = "Premium Electronics";
        }

        String priceRange = determinePriceRange(price);

        return new Product(productId, name, price, category, priceRange);
    }

    private String determinePriceRange(BigDecimal price) {
        if (price.compareTo(new BigDecimal("10.00")) <= 0) {
            return "Low";
        } else if (price.compareTo(new BigDecimal("100.00")) <= 0) {
            return "Medium";
        } else if (price.compareTo(new BigDecimal("500.00")) <= 0) {
            return "High";
        } else {
            return "Premium";
        }
    }
}
6️⃣ ProductExtractor.java
package org.howard.edu.lsp.assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Extracts Product objects from a CSV file.
 */
public class ProductExtractor {

    private final String inputPath;
    private int rowsRead = 0;
    private int rowsSkipped = 0;

    public ProductExtractor(String inputPath) {
        this.inputPath = inputPath;
    }

    public List<Product> extract() throws IOException {
        List<Product> products = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath))) {

            String line = reader.readLine();
            if (line == null) {
                return products;
            }

            while ((line = reader.readLine()) != null) {
                rowsRead++;

                if (line.trim().isEmpty()) {
                    rowsSkipped++;
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length != 4) {
                    rowsSkipped++;
                    continue;
                }

                try {
                    int productId = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    BigDecimal price = new BigDecimal(parts[2].trim());
                    String category = parts[3].trim();

                    products.add(new Product(productId, name, price, category, null));

                } catch (Exception e) {
                    rowsSkipped++;
                }
            }
        }

        return products;
    }

    public int getRowsRead() {
        return rowsRead;
    }

    public int getRowsSkipped() {
        return rowsSkipped;
    }
}
7️⃣ ProductLoader.java
package org.howard.edu.lsp.assignment3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Loads transformed Product objects into a CSV file.
 */
public class ProductLoader {

    private final String outputPath;

    public ProductLoader(String outputPath) {
        this.outputPath = outputPath;
    }

    public void load(List<Product> products) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {

            writer.write("ProductID,Name,Price,Category,PriceRange");
            writer.newLine();

            for (Product product : products) {
                writer.write(product.getProductId() + "," +
                             product.getName() + "," +
                             product.getPrice().toString() + "," +
                             product.getCategory() + "," +
                             product.getPriceRange());
                writer.newLine();
            }
        }
    }
}
✅ You Now Have:
Encapsulation

Object modeling

Separation of concerns

Polymorphism

Immutability-style transformation

Clean orchestration

Same behavior as A2

If you'd like next, we can:

Write your REFLECTION_A3.md (strong, rubric-aligned)

Generate AI_PROMPTS_A3.md based on this conversation

Or walk through how to test equivalence with A2

You're in very good shape for a high score.


You said:
i should be able to run ETLPipeline.java. I shouldn't have a separate driver. 

ChatGPT said:
Good catch 👍 — you’re absolutely right to question that.

If your Assignment 2 had:

public class ETLPipeline {
    public static void main(String[] args)
then for Assignment 3 you should preserve that structure so it behaves the same way when run.

The assignment says:

Program must still meet the exact same requirements as Assignment 2: same inputs, same outputs, same transformations, same error handling, and same relative paths.

It does not require a separate driver class.

So yes — you should be able to run:

ETLPipeline.java
directly.

✅ Corrected Design Decision
We will:

❌ Remove Assignment3Driver

✅ Put main() inside ETLPipeline

✅ Keep ETLPipeline as orchestrator

✅ Keep all other classes the same

That keeps it clean and still fully object-oriented.

🎯 What ETLPipeline Should Look Like Conceptually
public class ETLPipeline {

    public static void main(String[] args) {
        ETLPipeline pipeline = new ETLPipeline();
        pipeline.run();
    }

    public void run() {
        ...
    }
}
This is actually better design:

main is minimal

Real logic lives in run()

Still runnable directly

Still fully OO

🔎 Why This Is Still Object-Oriented
Even though main is inside the same class:

The pipeline logic is not static

It creates objects

It uses the Transformer interface (polymorphism)

It coordinates collaborating objects

So you still demonstrate:

Encapsulation

Polymorphism

Separation of concerns

Object collaboration

You don’t lose any OO credit.

🧠 What Changes?
Only this:

Remove Assignment3Driver.java

Modify ETLPipeline.java to include:

public static void main(String[] args)
Everything else stays the same.

📌 Why This Is Cleaner for Submission
It keeps your structure closer to A2.

Your professor can:

Run ETLPipeline directly

Compare behavior easily

See redesign clearly

And it avoids unnecessary class creation.

If you’d like, I can now:

Provide the corrected ETLPipeline.java with main included

Or help you prepare your reflection section next

You're making good architectural decisions — this is exactly how strong A3 submissions are built.


You said:
ok, it works. what are javadocs?

ChatGPT said:
Great — glad it works 👏

Now let’s talk about Javadocs, because they matter for your grading.

📘 What Are Javadocs?
Javadocs are structured comments in Java that document classes and methods.

They:

Explain what a class does

Explain what a method does

Describe parameters and return values

Can be automatically converted into HTML documentation

They are not regular comments.

They use a special format that starts with:

/**
 * ...
 */
Notice the two asterisks.

That’s what makes it a Javadoc comment instead of a normal comment.

🧠 Why They Matter for Your Assignment
The rubric says:

Include Javadocs for each class and all public methods.

So you must document:

Every class

Every public constructor

Every public method

If a method is private, you do NOT need Javadoc (though it’s allowed).

📝 What Goes Inside a Javadoc?
There are special tags.

The most common ones:

Tag	Meaning
@param	Describes a method parameter
@return	Describes what the method returns
@throws	Describes exceptions thrown
@author	(Optional) Author name
@version	(Optional) Version number
📦 Example From Your Project
Let’s use your Transformer interface as an example.

Good Javadoc:
/**
 * Generic transformer interface demonstrating polymorphism.
 *
 * @param <T> the type being transformed
 */
public interface Transformer<T> {

    /**
     * Transforms an input object and returns a new transformed object.
     *
     * @param input the original object
     * @return a new transformed object
     */
    T transform(T input);
}
That’s proper documentation.

📦 Example for a Class
For Product:

/**
 * Represents a product record in the ETL pipeline.
 * Encapsulates product data fields.
 */
public class Product {
Short. Clear. Professional.

📦 Example for a Public Method
/**
 * Executes the ETL pipeline process:
 * extraction, transformation, and loading.
 */
public void run() {
⚠️ What NOT To Do
Bad Javadoc:

/**
 * This method sets the name.
 */
public void setName(String name)
That just repeats the obvious.

Good Javadoc explains purpose, not the literal code.

🎓 Why Professors Care
Javadocs demonstrate:

Professional coding standards

Maintainability

Clear communication

Understanding of public APIs

Since Assignment 3 focuses on OO design and documentation, this matters.

🧠 In Your Specific Case
You need Javadocs for:

Classes:
ETLPipeline

Product

ProductExtractor

ProductLoader

ProductTransformer

Transformer

Public methods:
main()

run()

extract()

load()

transform()

constructors

getters (yes, technically they’re public)

Yes — even getters need documentation.

💡 How Detailed Should They Be?
Not huge paragraphs.

Just:

1–3 sentence class description

1–2 sentence method description

Proper @param and @return

Clean and accurate.

📌 Important: Review AI-Generated Javadocs
The assignment says:

You may use your AI assistant to help generate Javadocs, but you must review and edit them for accuracy.

So if I help you generate them:

Read them

Adjust wording

Make sure they match your code

That protects you academically.

If you'd like next, I can:

Generate polished Javadocs for all your Assignment 3 classes

Or help you write your REFLECTION_A3.md (which is worth 25 points)

You're very close to a polished submission now.


You said:
help me generate the javadocs please

ChatGPT said:
Perfect 👍 — I’ll generate clean, professional, rubric-appropriate Javadocs for each of your Assignment 3 classes.

These will be:

Clear but not overly long

Accurate to your design

Professional tone

Suitable for a 300-level CS course

Easy for you to review and slightly personalize

You should still read through and adjust wording slightly so it sounds like you.

✅ 1️⃣ ETLPipeline.java Javadocs
Place this above your class declaration:

/**
 * Coordinates the Extract-Transform-Load (ETL) process for product data.
 * 
 * The pipeline reads product records from an input CSV file,
 * applies transformation rules, and writes the transformed
 * records to an output CSV file. It also prints a summary
 * of rows processed.
 */
For main():

/**
 * Entry point of the ETL application.
 *
 * @param args command-line arguments (not used)
 */
public static void main(String[] args)
For run():

/**
 * Executes the full ETL process:
 * extraction, transformation, loading, and summary reporting.
 */
public void run()
For printSummary():

/**
 * Prints a summary of ETL processing results.
 *
 * @param read number of rows read from the input file
 * @param written number of rows successfully transformed and written
 * @param skipped number of rows skipped due to validation or parsing errors
 * @param outputPath the path of the output file
 */
private void printSummary(int read, int written, int skipped, String outputPath)
✅ 2️⃣ Product.java Javadocs
Above class:

/**
 * Represents a product record in the ETL pipeline.
 * 
 * This class encapsulates product attributes including
 * product ID, name, price, category, and price range.
 * Instances of this class are immutable once created.
 */
Constructor:

/**
 * Constructs a Product object.
 *
 * @param productId the unique identifier of the product
 * @param name the name of the product
 * @param price the product price
 * @param category the product category
 * @param priceRange the price range classification
 */
public Product(int productId, String name, BigDecimal price, String category, String priceRange)
For each getter:

Example:

/**
 * Returns the product ID.
 *
 * @return the product ID
 */
public int getProductId()
/**
 * Returns the product name.
 *
 * @return the product name
 */
public String getName()
/**
 * Returns the product price.
 *
 * @return the product price
 */
public BigDecimal getPrice()
/**
 * Returns the product category.
 *
 * @return the product category
 */
public String getCategory()
/**
 * Returns the product price range classification.
 *
 * @return the product price range
 */
public String getPriceRange()
✅ 3️⃣ Transformer.java Javadocs
Above interface:

/**
 * A generic interface that defines transformation behavior.
 *
 * Implementations of this interface apply specific
 * transformation logic to objects of type T.
 *
 * @param <T> the type of object being transformed
 */
For method:

/**
 * Transforms the given input object and returns a new transformed object.
 *
 * @param input the original object to transform
 * @return a new transformed object
 */
T transform(T input);
✅ 4️⃣ ProductTransformer.java Javadocs
Above class:

/**
 * Applies business transformation rules to Product objects.
 *
 * Transformation rules include:
 * - Converting product names to uppercase
 * - Applying a 10% discount to Electronics
 * - Rounding prices to two decimal places
 * - Updating category to "Premium Electronics" when applicable
 * - Determining the appropriate price range classification
 */
For transform():

/**
 * Applies transformation rules to a Product and returns a new transformed Product.
 *
 * @param input the original Product
 * @return a new Product containing transformed values
 */
@Override
public Product transform(Product input)
For determinePriceRange():

/**
 * Determines the price range classification based on price.
 *
 * @param price the rounded product price
 * @return the corresponding price range (Low, Medium, High, or Premium)
 */
private String determinePriceRange(BigDecimal price)
✅ 5️⃣ ProductExtractor.java Javadocs
Above class:

/**
 * Extracts Product objects from a CSV input file.
 *
 * This class reads product data from the specified file,
 * validates rows, and converts valid rows into Product objects.
 * It also tracks the number of rows read and skipped.
 */
Constructor:

/**
 * Constructs a ProductExtractor with the given input file path.
 *
 * @param inputPath the path to the input CSV file
 */
public ProductExtractor(String inputPath)
extract():

/**
 * Reads the input file and converts valid rows into Product objects.
 *
 * Rows that are blank, malformed, or contain invalid data
 * are skipped and counted.
 *
 * @return a list of valid Product objects
 * @throws IOException if the input file cannot be read
 */
public List<Product> extract() throws IOException
getRowsRead():

/**
 * Returns the number of rows read from the input file
 * (excluding the header).
 *
 * @return number of rows read
 */
public int getRowsRead()
getRowsSkipped():

/**
 * Returns the number of rows skipped due to errors or invalid format.
 *
 * @return number of rows skipped
 */
public int getRowsSkipped()
✅ 6️⃣ ProductLoader.java Javadocs
Above class:

/**
 * Loads transformed Product objects into a CSV output file.
 *
 * This class writes a header row followed by
 * transformed product records.
 */
Constructor:

/**
 * Constructs a ProductLoader with the specified output file path.
 *
 * @param outputPath the path to the output CSV file
 */
public ProductLoader(String outputPath)
load():

/**
 * Writes a list of Product objects to the output CSV file.
 *
 * @param products the list of transformed products
 * @throws IOException if the output file cannot be written
 */
public void load(List<Product> products) throws IOException
✅ What You’ve Now Covered
You now properly document:

All classes

All public constructors

All public methods

Polymorphic interface

ETL orchestration

That fully satisfies the rubric requirement.

If you'd like next, we can:

Write your REFLECTION_A3.md (worth 25 points)

Generate your AI_PROMPTS_A3.md file

Do a final submission checklist review

You're very close to a polished, high-scoring submission.


You said:
ok, here is the assignment again. i will work on the reflection myself. am i missing anything? i already tested the code and it works. 

CSCI 363 – Assignment 3: Object-Oriented Redesign of Your ETL Pipeline
Due: Friday, February 20th, 11:59PM 
Purpose
In Assignment 2, you built an ETL pipeline in Java. In this assignment, you will redesign your solution to be more object-oriented. The goal is to practice object-oriented decomposition, not perfection. You must also use a generative AI assistant to help with the redesign and explain how you used or adapted its suggestions.
Learning Goals
• Apply object-oriented decomposition to requirements.
• Practice basic OO concepts: object, class, encapsulation, inheritance, polymorphism.
• Use generative AI as a helper and reflect on your choices.
• Strengthen attention to coding standards and documentation.
The Task
1. Start with your Assignment 2 solution.
2. Use a generative AI assistant to brainstorm ways to make it more object-oriented.
3. Redesign your solution into multiple classes with clearer responsibilities.
4. Your program must still meet the exact same requirements as Assignment 2: same inputs, same outputs, same transformations, same error handling, and same relative paths.
Reflection (Required)
Write 1–2 pages that compare your Assignment 2 and Assignment 3 implementations. Be specific:
• What is different about the design?
• How is Assignment 3 more object-oriented?
• Which OO ideas (object, class, encapsulation, inheritance, polymorphism) did you use?
• Explain how you tested to confirm Assignment 3 works the same as Assignment 2.
Deliverables
1. Source Code for Assignment 3:
   - Must be under the package org.howard.edu.lsp.assignment3.
   - One public class per file.
   - Include Javadocs for each class and all public methods. (You may use your AI assistant to help generate Javadocs, but you must review and edit them for accuracy.)
   - Code must follow the coding standards discussed in lecture.

2. Output File: data/transformed_products.csv.

3. Reflection: src/org/howard/edu/lsp/assignment3/doc/REFLECTION_A3.md.

4. AI Transcript File: src/org/howard/edu/lsp/assignment3/doc/AI_PROMPTS_A3.md.
   - Include all prompts you asked the AI responses.
Your project should look like this (filenames below are examples/placeholders — use your own class names):
Note: package for assignment3 is an additional package to assignment1 and assignment2.  You are only creating a new package.
project-root/
├── src/
│   └── org/
│       └── howard/
│           └── edu/
│               └── lsp/
│                   └── assignment3/
│                       ├── <YourClass1>.java
│                       ├── <YourClass2>.java
│                       ├── <YourClass3>.java
│                       └── doc/
│                           ├── REFLECTION.md
│                           └── AI_PROMPTS.md
├── data/
│   ├── products.csv
│   └── transformed_products.csv
Grading Rubric (100 pts)
• Correctness (same outputs/behavior as A2) – 30 pts
• Object-Oriented Decomposition – 30 pts
• Reflection (comparison of A2 vs A3, clarity, specificity) – 25 pts
• AI Transcript File – 10 pts
• Code Style & Documentation – 5 pts
  (Correct package, Javadocs included, lecture coding standards followed)
Submission Checklist
☐ All A3 source files are under src/org/howard/edu/lsp/assignment3/.
☐ data/transformed_products.csv is produced and correct.
☐ Package name is exactly org.howard.edu.lsp.assignment3.
☐ One public class per file; code follows lecture coding standards.
☐ Javadocs included for all classes and public methods (reviewed for accuracy).
☐ src/org/howard/edu/lsp/assignment3/doc/REFLECTION_A3.md compares A2 vs A3.
☐ src/org/howard/edu/lsp/assignment3/doc/AI_PROMPTS_A3.md lists several prompts + excerpts.
☐ Program verified with missing input and empty input cases.
☐ Submission includes source code and the generated output file.

ChatGPT said:
You’re very close. If the code runs and the output matches A2, you’ve already cleared the hardest part (Correctness – 30 pts).

Let’s do a careful checklist review against the rubric so you don’t lose easy points.

✅ 1. Correctness (30 pts)
You said:

I already tested the code and it works.

Make sure you specifically verified:

✔ Same output file content
Compare A2 and A3 transformed_products.csv

They should be identical byte-for-byte

✔ Missing input file case
Delete or rename data/products.csv and confirm it prints:

Error: Unable to read input file data/products.csv.
And exits cleanly.

✔ Empty file case
Create an empty products.csv with only header or no content.
Confirm:

Summary still prints

No crash

Output file still created properly

If you tested all three, you’re solid here.

✅ 2. Object-Oriented Decomposition (30 pts)
You have:

Product (domain object)

ProductExtractor

ProductTransformer

ProductLoader

ETLPipeline

Transformer<T> interface (polymorphism)

You are clearly demonstrating:

Separation of concerns

Encapsulation (private fields + getters)

Polymorphism (Transformer interface)

Object collaboration

Removal of static-heavy procedural design

This is strong.

You’re not over-engineered.
You’re not under-designed.
This is exactly the level they want.

⚠️ 3. Documentation (5 pts) — Double Check This
Make sure:

✔ Every class has Javadoc
✔ Every public constructor has Javadoc
✔ Every public method has Javadoc
✔ All @param and @return tags are accurate
✔ No copy-paste mismatches
✔ No outdated comments from A2

This is easy to lose points on.

⚠️ 4. Package & Structure Check
Confirm:

src/org/howard/edu/lsp/assignment3/
Every class file must start with:

package org.howard.edu.lsp.assignment3;
Spelling must be EXACT.

No extra spaces.
No capitalization differences.

⚠️ 5. One Public Class Per File
Make sure:

No file has two public classes

Interface Transformer is in its own file

All filenames match class names exactly

⚠️ 6. Required Documentation Files
Make sure these exist exactly:

src/org/howard/edu/lsp/assignment3/doc/REFLECTION_A3.md
src/org/howard/edu/lsp/assignment3/doc/AI_PROMPTS_A3.md
Watch the filename carefully:

The assignment says:

REFLECTION_A3.md
AI_PROMPTS_A3.md
Earlier examples showed slightly different names (REFLECTION.md).
Use the exact required names from the checklist.

That’s an easy place to lose points.

⚠️ 7. AI_PROMPTS_A3.md Must Include:
Several prompts

AI responses (or excerpts)

Not just a sentence saying “I used AI”

Include:

Brainstorming OO ideas

Polymorphism explanation

Design decisions

Javadoc generation help

This conversation is perfect content for that.

⚠️ 8. Reflection Must Explicitly Mention:
Make sure your reflection clearly uses these words:

Object

Class

Encapsulation

Polymorphism

Inheritance (you can mention generics/interface hierarchy here)

Even if inheritance is minimal, you can mention:

The Transformer interface demonstrates inheritance through implementation and enables polymorphism.

That satisfies the learning goal requirement.

⚠️ 9. Coding Standards
Quick visual scan:

Proper indentation

No unused imports

No overly long lines

Constants defined clearly

Clean variable naming

No commented-out code blocks

Small style mistakes can cost 5 points.

🎯 Final Quick Self-Audit
Answer yes to all:

Does ETLPipeline contain main?

Does program produce identical output to A2?

Are all public methods documented?

Is the package exactly correct?

Are reflection and AI transcript in correct folder?

Did I test missing input and empty input?

Did I review AI-generated comments for accuracy?

If yes across the board, you’re in excellent shape.

💬 Honest Assessment
Based on what you've built:

Correctness: Strong

OO Decomposition: Strong

Polymorphism: Clearly demonstrated

Encapsulation: Clear

Documentation: Solid if reviewed

Risk areas: filenames + documentation completeness

You’re positioned for a high A if everything is packaged correctly.

If you'd like, I can:

Do a final “pretend I’m the grader” evaluation

Help you outline reflection structure (without writing it for you)

Help you structure AI_PROMPTS_A3.md cleanly

You're at the polish stage now — which is a good place to be.
