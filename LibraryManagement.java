package com.planonsoftware;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LibraryManagement {
    static ArrayList<Book> books=new ArrayList<>();
    static ArrayList<User> users=new ArrayList<>();
    static Scanner sc=new Scanner(System.in);
    static User currentUser;
    
    public static void main(String[] args)
    { 
    	users.add(new User("admin", "admin123", "admin"));
    	users.add(new User("admin1", "admin123", "admi"));
    	users.add(new User("user1", "user123", "user"));
    	users.add(new User("author", "author123", "author"));
    	users.add(new User("Robin Sharma", "robin", "author"));

    	while(true) {
    		System.out.println("Library Management System:");
    		System.out.println("1.Login");
    		System.out.println("2.Exit");
    		int choice=getValidChoice(1,2);
    	switch(choice)
    	{
    	case 1:
    		login();
    		break;
    	case 2:
    		System.out.println("Exiting the System");
    		return;
    	}
    	}
    }
    
    public static int getValidChoice(int min,int max)
    {
    	int choice;
    	while(true)
    	{
    		System.out.println("Enter your choice ");
    		try {
    			choice=sc.nextInt();
    			sc.nextLine();
    			if(choice>=min && choice<=max)
    			{
    				break;
    			}
    			else {
    				System.out.println("Invalid Choice please enter a number between "+min +" and "+max);
    			}
    			
    		}
    		catch(InputMismatchException e)
    		{
    			System.out.println("Invalid input.please enter valid number ");
    			sc.next();
    		}
    	}
    	return choice;
    }
    public static void login()
    {
    	while(true)
    	{
    	System.out.println("Enter username :");
    	String username=sc.nextLine();
    	System.out.println("Enter password :");
    	String password=sc.nextLine();
    	boolean loginSuccessful=false;
    	for(User user:users)
    	{
    		if(user.username.equals(username)&&user.password.equals(password))
    		{
    			currentUser=user;
    		    loginSuccessful=true;
    	         switch(currentUser.role)
    	            {
    	              case "admin" :
    		                adminMenu();
    		                break;
    	              case "user":
    		                userMenu();
    		                break;
    	              case "author" :
    		               authorLogin(currentUser.username);
    		               break;

    	              default:
    		              System.out.println("Invalid role.please enter correct role .");
    	             }
    		             return;
    	         }
    	}
    	if(!loginSuccessful)
    	{
    	System.out.println("Incorrect Username and Password. Do you want to try again (yes/no) ");
    	String response=sc.nextLine();
    	if(!response.equalsIgnoreCase("yes"))
    	{
    		break;
    	}
    }
    	}
    	}
    
    //If user has entered wrong choice, display error and ask for input again
  
    	public static void adminMenu()
    	{
    			while(true){
    				System.out.println("\nAdmin Menu :");
    				System.out.println("1. Add Book");
    				System.out.println("2. Delete Book");
    				System.out.println("3. View Book");
    				System.out.println("4.Logout");
    				int choice=getValidChoice(1,4);
    			 switch(choice) {
    			 case 1:
    				 addBook();
    				 break;
    			 case 2:
    				 deleteBook();
    				 break;
    			 case 3:
    				 viewBooks();
    				 break;
    			 case 4:
    				 System.out.println("Logging out .");
    				 return;
    			 }
    		
    			}
    			
    	}	
    	public static void userMenu()
    	{
    			while(true) {
    				System.out.println("\nUser Menu :");
    				System.out.println("1. Buy Book");
    				System.out.println("2. Rent Book");
    				System.out.println("3. Return Book");
    				System.out.println("4. Search Book");
                    System.out.println("5. View Books");
    				System.out.println("6.Add Balance");    				
    				System.out.println("7.View Balance");
    				System.out.println("8.Logout");
    				int choice=getValidChoice(1,8);
    			 switch(choice) {
    			 case 1:
    				 buyBook();
    				 break;
    			 case 2:
    				 rentBook();
    				 break;
    			 case 3:
    				 returnBook();
    				 break;
    			 case 4:
    				 searchBooks();
    				 break;
    			 case 5:
    				 viewBooks();
    				 break;
    			 case 6:
    				 addBalance();
    				 break;
    			 case 7:
    				 viewBalance();
    				 break;
    			 case 8:
    				 System.out.println("Logging out .");
    				 return;
    				 
    			 }
    		
    			}
    			
    	}	
    	public static void authorLogin(String authorName) {
            currentUser=new User(authorName,"","author");
            authorMenu();
        }

        public static void authorMenu() {
            int choice;
            while (true) {
                System.out.println("\nAuthor Menu:");
                System.out.println("1. Publish Book");
                System.out.println("2. View My Books");
                System.out.println("3. Logout");
                System.out.print("Enter your choice: ");
                 choice=getValidChoice(1,3);

                switch (choice) {
                    case 1:
                        publishBook();
                        break;
                    case 2:
                        viewMyBooks();
                        break;
                    case 3:
                        System.out.println("Logging out.");
                        return;
                    
                }
            } 
        }

    	
    	public static void addBook() {
            System.out.print("Enter book title: ");
            String title = sc.nextLine();
            System.out.print("Enter book author: ");
            String author = sc.nextLine();
            int copies;
            while(true) {
            System.out.print("Enter number of copies: ");
            copies = sc.nextInt();
            if(copies>0) {
            	break;
            }
            else {
            	System.out.println("Number of copies must be greater than zero. Please enter valid number ");
                 }
            }
            System.out.print("Enter Book Price: ");
            double price  = sc.nextDouble();
            sc.nextLine();
          
            books.add(new Book(title, author, copies,price));
            System.out.println("Book added successfully.");
            
    	
    	}
    	public static void deleteBook() {
    		System.out.println("Enter book title to delete: ");
    		String title=sc.nextLine();
    		for(Book book:books)
    		{
    			if(book.title.equalsIgnoreCase(title)) {
    				books.remove(book);
    				System.out.println("Book Deleted Successfully.");
    				return;
    			}
    		}
    		System.out.println("Book Not Found");
    	}
    	
    	public static void buyBook() {
    	    System.out.println("Enter book title to buy: ");
    	    String title = sc.nextLine();
    	    for (Book book : books) {
    	        if (book.title.equalsIgnoreCase(title)) {
    	            System.out.println("Enter no of copies to buy: ");
    	            int copies = sc.nextInt();
    	            sc.nextLine();
    	            if (book.copiesAvailable >= copies) {
    	                double cost = copies * book.price;
    	                System.out.println("Total cost: " + cost + " rupees. Do you want to proceed with the purchase? (yes/no)");
    	                String confirmation = sc.nextLine();
    	                if (confirmation.equalsIgnoreCase("yes")) {
    	                    boolean paymentSuccessful = false;
    	                    while (!paymentSuccessful) {
    	                        System.out.println("Choose a payment option: ");
    	                        System.out.println("1. Wallet");
    	                        System.out.println("2. Debit Card");
    	                        System.out.println("3. Credit Card");
    	                        System.out.println("4. UPI");
    	                        int paymentChoice = getValidChoice(1, 4);

    	                        switch (paymentChoice) {
    	                            case 1:
    	                                if (currentUser.getBalance() >= cost) {
    	                                    currentUser.deductBalance(cost);
    	                                    book.copiesAvailable -= copies;
    	                                    System.out.println("Books bought successfully. Total cost: " + cost + " rupees");
    	                                    paymentSuccessful = true;
    	                                } else {
    	                                    System.out.println("Insufficient balance in wallet. Choose another payment method or add balance.");
    	                                    System.out.println("1. Add Balance");
    	                                    System.out.println("2. Choose Another Payment Method");
    	                                    int walletChoice = getValidChoice(1, 2);
    	                                    if (walletChoice == 1) {
    	                                        addBalance();
    	                                        if (currentUser.getBalance() >= cost) {
    	                                            currentUser.deductBalance(cost);
    	                                            book.copiesAvailable -= copies;
    	                                            System.out.println("Books bought successfully. Total cost: " + cost + " rupees");
    	                                            paymentSuccessful = true;
    	                                        }
    	                                    }
    	                                    if (walletChoice == 2) {
    	                                        continue;
    	                                    }
    	                                }
    	                                break;
    	                            case 2:
    	                                paymentSuccessful = payWithDebitCard(cost);
    	                                if (paymentSuccessful) {
    	                                    book.copiesAvailable -= copies;
    	                                }
    	                                break;
    	                            case 3:
    	                                paymentSuccessful = payWithCreditCard(cost);
    	                                if (paymentSuccessful) {
    	                                    book.copiesAvailable -= copies;
    	                                }
    	                                break;
    	                            case 4:
    	                                paymentSuccessful = payWithUPI(cost);
    	                                if (paymentSuccessful) {
    	                                    book.copiesAvailable -= copies;
    	                                }
    	                                break;
    	                        }

    	                        if (!paymentSuccessful && paymentChoice != 1) {
    	                            System.out.println("Payment Failed. Do you want to try again with another method? (yes/no)");
    	                            String response = sc.nextLine();
    	                            if (!response.equalsIgnoreCase("yes")) {
    	                                System.out.println("Purchase process cancelled due to unsuccessful payment.");
    	                                return;
    	                            }
    	                        }
    	                    }
    	                } else {
    	                    System.out.println("Purchase cancelled.");
    	                }
    	            } else {
    	                System.out.println("Not enough copies available.");
    	            }
    	            return;
    	        }
    	    }
    	    System.out.println("Book not found.");
    	}

    public static void rentBook()
    {
    	System.out.println("Enter title to rent :");
    	String title =sc.nextLine();
    	for(Book book:books) {
    		if(book.title.equalsIgnoreCase(title)) {
    			if(book.copiesAvailable>0)
    			{
    				System.out.println("Note : The Book Should be returned within 14 days. A late fee of 2 rupees per day will be charged if returned after the due date.");
    				System.out.println("Do you want to proceed with renting this book?(yes/no)");
    				String confirmation=sc.nextLine();
    				if(confirmation.equalsIgnoreCase("yes"))
    				{
    				book.copiesAvailable--;
    				book.borrowDate=LocalDate.of(2024,6,18);
    				//book.borrowDate=LocalDate.now();
    				book.isRented=true;
    				System.out.println("Book rented Successfully");
    				}
    				else
    				{
    					System.out.println("Book rental cancelled");
    				}
    			}	
    			else
    			{
    				System.out.println("No Copies Available");
    			}
    			return;
    		}
    	}
    	System.out.println("Book Not Found");
    }
    
    public static void returnBook() {
        System.out.println("Enter book title to return: ");
        String title = sc.nextLine();
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)) {
                if (book.isRented) {
                    double lateFee = book.returnBook();
                    if (lateFee > 0) {
                        System.out.println("Late fee for returning the book: " + lateFee + " rupees");
                        boolean paymentSuccessful = false;
                        while (!paymentSuccessful) {
                            System.out.println("Choose a payment option: ");
                            System.out.println("1. Wallet");
                            System.out.println("2. Debit Card");
                            System.out.println("3. Credit Card");
                            System.out.println("4. UPI");
                            int choice = getValidChoice(1, 4);

                            switch (choice) {
                                case 1:
                                    if (currentUser.getBalance() >= lateFee) {
                                        currentUser.deductBalance(lateFee);
                                        System.out.println("Payment Successful");
                                        paymentSuccessful = true;
                                    } else {
                                        System.out.println("Insufficient balance in wallet. Choose another payment method or add balance.");
                                        System.out.println("1. Add Balance");
                                        System.out.println("2. Choose Another Payment Method");
                                        int walletChoice = getValidChoice(1, 2);
                                        if (walletChoice == 1) {
                                            addBalance();
                                            if (currentUser.getBalance() >= lateFee) {
                                                currentUser.deductBalance(lateFee);
                                                System.out.println("Payment Successful");
                                                paymentSuccessful = true;
                                            }
                                        }
                                        if (walletChoice == 2) {
                                            continue;
                                        }
                                    }
                                    break;
                                case 2:
                                    paymentSuccessful = payWithDebitCard(lateFee);
                                    break;
                                case 3:
                                    paymentSuccessful = payWithCreditCard(lateFee);
                                    break;
                                case 4:
                                    paymentSuccessful = payWithUPI(lateFee);
                                    break;
                            }

                            if (!paymentSuccessful && choice != 1) {
                                System.out.println("Payment Failed. Do you want to try again with another method? (yes/no)");
                                String response = sc.nextLine();
                                if (!response.equalsIgnoreCase("yes")) {
                                    System.out.println("Return process cancelled due to unsuccessful payment.");
                                    return;
                                }
                            }
                        }
                    }
                    book.copiesAvailable++;
                    System.out.println("Book returned successfully. Late fee: " + lateFee + " rupees");
                } else {
                    System.out.println("Book is not rented");
                }
                return;
            }
        }
        System.out.println("Book not found");
    }
    public static void addBalance() {
    	System.out.println("Enter amount to add to balance ");
    	double amount=sc.nextDouble();
    	sc.nextLine();
    	currentUser.addBalance(amount);
    	System.out.println("Balance added Successfully . New Balance : "+currentUser.getBalance());
    }
    
    public static void viewBalance()
    { 
    	System.out.println("Current Balance : "+currentUser.getBalance());
    }
    
    public static void viewBooks() {
    	if(books.isEmpty()) {
    		System.out.println("no Books Available");
    		return;
    	}
    	System.out.println("\nList of Books:");
    	for(Book book:books) {
    		System.out.println("Title : "+book.title+", Author : "+book.author+",No.ofCopies "+book.copiesAvailable);
    	}
    }
  
     public static void searchBooks() {
    	 while(true)
    	 {
    	 boolean found=false;
    	 System.out.println("Enter book title to search: ");
    	 String title=sc.nextLine();
    	 for(Book book:books) {
    		 if(book.title.equalsIgnoreCase(title)) {
    	    		System.out.println("Title : "+book.title+", Author : "+book.author+" ,No.ofCopies "+book.copiesAvailable+" , Price :"+book.price+" rupees");
    	    		found=true;
    		 }
    	 }
    	 if(found)
    	 {
    		 break;
    	 }
    	 else
    	 {
    		 System.out.println("Book Not Found. Do you want to search again? (yes/no)");
    		 String response=sc.nextLine();
    		 if(!response.equalsIgnoreCase("yes"))
    		     break;
    	 }
    	
     }
     }
    public static void publishBook()
    {
    	System.out.println("Enter book title: ");
    	String title=sc.nextLine();
    	System.out.println("Enter no of copies to publish :");
    	int copies=sc.nextInt();
    	System.out.println("Enter book price:");
    	double price=sc.nextDouble();
    	sc.nextLine();
    	books.add(new Book(title,currentUser.username,copies,price));
    	System.out.println("Books Published Successfully");
    }
    
    public static void viewMyBooks() {
    	boolean found=false;
    	System.out.println("\nList of books ");
    	for(Book book :books) {
    		if(book.author.equalsIgnoreCase(currentUser.username)) {
	    		System.out.println("Title : "+book.title+", Author : "+book.author+",No.ofCopies "+book.copiesAvailable +" , Price :"+book.price+" rupees");
	    		found=true;
    		}
    	}
    	if(!found)
    	{
    		System.out.println("No books found ");
    	}
    }
    
  public static boolean payWithDebitCard(double cost)
  {
	  System.out.println("Enter Debit Card number :");
	  String cardNumber=sc.nextLine();
	  
	  System.out.println("Enter CVV:");
	  String cvv=sc.nextLine();
	  
	  System.out.println("Enter Expiry Month (MM) :");
	  String expiryMonth=sc.nextLine();

	  System.out.println("Enter Expiry Year (MM) :");
	  String expiryYear=sc.nextLine();

	  boolean isValid=validateCard(cardNumber,cvv,expiryMonth,expiryYear);
	  if(isValid)
	  {
		  System.out.println("Debit card payment successful.Total cost: "+cost+" rupees");
		  return true;
	  }
	  else
	  {
		  System.out.println("Invalid card details.Payment Failed");
		  return false;
	  }
  }
  public static boolean payWithCreditCard(double cost)
  {
	  System.out.println("Enter Credit Card number :");
	  String cardNumber=sc.nextLine();
	  
	  System.out.println("Enter CVV:");
	  String cvv=sc.nextLine();
	  
	  System.out.println("Enter Expiry Month (MM) :");
	  String expiryMonth=sc.nextLine();

	  System.out.println("Enter Expiry Year (MM) :");
	  String expiryYear=sc.nextLine();

	  boolean isValid=validateCard(cardNumber,cvv,expiryMonth,expiryYear);
	  if(isValid)
	  {
		  System.out.println("Credit card payment successful.Total cost: "+cost+" rupees");
		  return true;
	  }
	  else
	  {
		  System.out.println("Invalid card details.Payment Failed");
		  return false;
	  }
  }
  public static boolean payWithUPI(double cost)
  {
	  System.out.println("Enter UPI ID :");
	  String upiId=sc.nextLine();
	  
	  boolean isValid=validateUPI(upiId);
	  if(isValid)
	  {
		  System.out.println("UPI  payment successful.Total cost: "+cost+" rupees");
		  return true;
	  }
	  else
	  {
		  System.out.println("Invalid UPI ID .Payment Failed");
		  return false;
	  }
	  
  }
  
  private static boolean validateCard(String cardNumber,String cvv,String expiryMonth,String expiryYear)
  {
	  if(cardNumber.length()==16 && cardNumber.matches("\\d+"))
	  {
		  if (cvv.length()==3 && cvv.matches("\\d+"))
		  {
			  if(expiryMonth.matches("\\d{2}") && Integer.parseInt(expiryMonth)>=1 && Integer.parseInt(expiryMonth)<=12)
			  {
				  int currentYear =LocalDate.now().getYear()%100;
				  int expiryYearInt=Integer.parseInt(expiryYear);
				  if(expiryYear.matches("\\d{2}") && expiryYearInt>=currentYear && expiryYearInt<=currentYear+10)
				  {
					  return true;
				  }
			  }
		  }
	  }
	  return false;
  }
  public static boolean validateUPI(String upiId) {
	  return !upiId.isEmpty() && upiId.matches("[\\w\\d@.-_]+")&& upiId.length()<=20;
  }
}
