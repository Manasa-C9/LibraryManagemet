package com.planonsoftware;

import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManagement {
    static ArrayList<Book> books=new ArrayList<>();
    static ArrayList<User> users=new ArrayList<>();
    static Scanner sc=new Scanner(System.in);
    static User currentUser;
    
    public static void main(String[] args)
    { 
    	users.add(new User("admin", "admin123", "admin"));
    	users.add(new User("user1", "user123", "user"));
    	users.add(new User("author", "author123", "author"));
    	users.add(new User("Robin Sharma", "robin", "author"));

    	while(true) {
    		System.out.println("Library Management System:");
    		System.out.println("1.Login");
    		System.out.println("2.Exit");
    		System.out.println("Enter your choice: ");
    		int choice=sc.nextInt();
    		sc.nextLine();
    	switch(choice)
    	{
    	case 1:
    		login();
    		break;
    	case 2:
    		System.out.println("Exiting the System");
    		return;
    	default:
    		System.out.println("Invalid choice.please try again");
    	}
    	}
    }
    
    public static void login()
    {
    	System.out.println("Enter username :");
    	String username=sc.nextLine();
    	System.out.println("Enter password :");
    	String password=sc.nextLine();
    	for(User user:users)
    	{
    		if(user.username.equals(username)&&user.password.equals(password))
    		{
    			currentUser=user;
    		
    	         switch(user.role)
    	            {
    	              case "admin" :
    		                adminMenu();
    		                break;
    	              case "user":
    		                userMenu();
    		                break;
    	              case "author" :
    		               authorLogin(user.username);
    		               break;
    	
    	              default:
    		              System.out.println("Invalid role.please enter correct role .");
    	             }
    		             return;
    	         }
    	}
    	System.out.println("Incorrect Username and Password.");
    	
    }
    	public static void adminMenu()
    	{
    			do {
    				System.out.println("\nAdmin Menu :");
    				System.out.println("1. Add Book");
    				System.out.println("2. Delete Book");
    				System.out.println("3. View Book");
    				System.out.println("4.Logout");
    				System.out.println("Enter your choice: ");
    				int choice=sc.nextInt();
    				sc.nextLine();
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
    			 default:
    				 System.out.println("Invalid Choice Please Try again");
    				 
    			 }
    		
    			}while(true);
    			
    	}	
    	public static void userMenu()
    	{
    			do {
    				System.out.println("\nUser Menu :");
    				System.out.println("1. Buy Book");
    				System.out.println("2. Rent Book");
    				System.out.println("3. Search Book");
    				System.out.println("4.View Book");
    				System.out.println("5.Logout");
    				System.out.println("Enter your choice: ");
    				int choice=sc.nextInt();
    				sc.nextLine();
    			 switch(choice) {
    			 case 1:
    				 buyBook();
    				 break;
    			 case 2:
    				 rentBook();
    				 break;
    			 case 3:
    				 searchBooks();
    				 break;
    			 case 4:
    				 viewBooks();
    				 break;
    			 case 5:
    				 System.out.println("Logging out .");
    				 return;
    			 default:
    				 System.out.println("Invalid Choice Please Try again");
    				 
    			 }
    		
    			}while(true);
    			
    	}	
    	public static void authorLogin(String authorName) {
            currentUser=new User(authorName,"","author");
            authorMenu();
        }

        public static void authorMenu() {
            int choice;
            do {
                System.out.println("\nAuthor Menu:");
                System.out.println("1. Publish Book");
                System.out.println("2. View My Books");
                System.out.println("3. Logout");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                sc.nextLine();  // Consume newline

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
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (true);
        }

    	
    	public static void addBook() {
            System.out.print("Enter book title: ");
            String title = sc.nextLine();
            System.out.print("Enter book author: ");
            String author = sc.nextLine();
            System.out.print("Enter number of copies: ");
            int copies = sc.nextInt();
            sc.nextLine();

            books.add(new Book(title, author, copies));
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
    	   String title=sc.nextLine();
    	   for(Book book:books) {
    		   if(book.title.equalsIgnoreCase(title)) {
    			   System.out.println("Enter no of copies to buy : ");
    			   int copies=sc.nextInt();
    			   sc.nextLine();
    			   if(book.copiesAvailable>=copies) {
    				   book.copiesAvailable-=copies;
    				   System.out.println("Books bought Successfully");
    			   }
    				else{
    					System.out.println("Not enough copies Available.Only "+book.copiesAvailable+" copies available");
    				
    				}
    			   return;
    			   }
    		   }
    	   System.out.println("Book Not Found");
    	   }
       
    public static void rentBook()
    {
    	System.out.println("Enter title to rent :");
    	String title =sc.nextLine();
    	for(Book book:books) {
    		if(book.title.equalsIgnoreCase(title)) {
    			if(book.copiesAvailable>0)
    			{
    				book.copiesAvailable--;
    				System.out.println("book rented Successfully");
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
    	 System.out.println("Enter book title to search: ");
    	 String title=sc.nextLine();
    	 for(Book book:books) {
    		 if(book.title.equalsIgnoreCase(title)) {
    	    		System.out.println("Title : "+book.title+", Author : "+book.author+" ,No.ofCopies "+book.copiesAvailable);
    	    		return;
    		 }
    	 }
    	 System.out.println("Book Not Found");
     }
    
    public static void publishBook()
    {
    	System.out.println("Enter book title: ");
    	String title=sc.nextLine();
    	System.out.println("Enter no of copies to publish :");
    	int copies=sc.nextInt();
    	sc.nextLine();
    	books.add(new Book(title,currentUser.username,copies));
    	System.out.println("Books Published Successfully");
    }
    
    public static void viewMyBooks() {
    	boolean found=false;
    	System.out.println("\nList of books ");
    	for(Book book :books) {
    		if(book.author.equalsIgnoreCase(currentUser.username)) {
	    		System.out.println("Title : "+book.title+", Author : "+book.author+",No.ofCopies "+book.copiesAvailable);
	    		found=true;
    		}
    	}
    	if(!found)
    	{
    		System.out.println("No books found ");
    	}
    }
}