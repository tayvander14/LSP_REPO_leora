The Template Method pattern is implemented in the abstract `Report` class 
through the `generateReport()` method, which is declared `final` to enforce 
a fixed workflow that subclasses cannot override. The four steps — `loadData()`, 
`formatHeader()`, `formatBody()`, and `formatFooter()` — are declared abstract, 
forcing each concrete subclass (`StudentReport` and `CourseReport`) to provide 
its own implementation. This allows each report type to customize its specific 
content while the overall algorithm structure remains locked in the parent class. 
Polymorphism is demonstrated in the driver by storing both report types in a 
`List<Report>` and calling `generateReport()` uniformly, letting Java's dynamic 
dispatch invoke the correct subclass methods at runtime.