Heuristic 1:
Name: H3.1
Distribute system intelligence horizontally as uniformly as possible.
Explanation:
In lecture, this was explained as designing systems where high-level classes act as peers rather than having one "master" class controlling everything. This idea was reinforced through the heating system example: instead of concentrating all decision-making in the HeatFlowRegulator, behavior was distributed so that the Room class could determine if it needs heat. This improves readability because responsibilities are clearly spread across classes, and improves maintainability because changes to one part of the system do not require modifying a central controller.

Heuristic 2:
Name: H3.2
Do not create god classes/objects in your system.
Explanation:
In lecture, this was illustrated directly with the HeatFlowRegulator, which acted as an "omnipotent controller" by pulling low-level data (ActualTemp, DesiredTemp, Occupancy) from Room and making all decisions. This made the design harder to maintain because all logic was centralized. The improved design moved decision-making into the Room class (e.g., do_I_need_heat()), reducing the role of HeatFlowRegulator. This improves readability because logic is located where it naturally belongs, and improves maintainability because the system is less dependent on a single class.

Heuristic 3: 
Name: H3.3
Beware of classes that have many access methods defined in their public interface. Having many access methods implies that related data and behavior are not being kept in one place.
Explanation:
In lecture, this was shown in the initial design where Room had many "get" methods so that HeatFlowRegulator could access its internal data and perform computations. This increased coupling because other classes depended on Room’s internal structure. The improved design reduced the number of accessors by introducing a higher-level method (do_I_need_heat()), which encapsulates the logic inside Room. This improves readability by simplifying interactions between classes and improves maintainability by reducing dependency on internal data representation.