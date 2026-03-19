Class: Order
Responsibilities: Store the order data such as customer name, email, item, and price. 
Collaborators: None

Class: OrderProcessor
Responsibilities: Coordinates the overall order-processing workflow. Calls the appropriate helper classes to calculate the final total, print the receipt, save the order, send the confirmation email, and log activity. 
Collaborators: Order, PricingService, ReceiptPrinter, OrderRepository, EmailService, ActivityLogger

Class: PricingService
Responsibilities: Calculate tax, apply discounts, and compute the final total for an order. 
Collaborators: Order

Class: ReceiptPrinter
Responsibilities: Generate and display or return a receipt for the processed order
Collaborators: Order

Class: OrderRepository
Responsibilities: Save order information to storage
Collaborators: Order

Class: EmailService
Responsibilities: Send confirmation emails to customers after an order is processed
Collaborators: Order

Class: ActivityLogger
Responsibilities: Record that an order has been processed and log relevant timestamps or status messages
Collaborators: Order