
// Arrays.sort(books, Comparator.comparing(Book::getPrice));
// _________________________________________________________________________________

// int id = Integer.parseInt(scanner.nextLine().trim());
// String title = scanner.nextLine().trim();
// String author = scanner.nextLine().trim();
// double price = Double.parseDouble(scanner.nextLine().trim());
// __________________________________________________________________________________
// DecimalFormat df = new DecimalFormat("0.000");
// System.out.println(df.format(interest));
// ___________________________________________________________________________________
// wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww
// ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo
// lllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll
// zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz
// xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
// mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm


import java.util.*;

class Book {
    int id;
    String title;
    String author;
    Double price;
    
    public Book(int id, String author, String title, Double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    
    
}

public class I_Classes2 {
    public static Book[] sortBookByPrice(Book[] books) {
        Arrays.sort(books, Comparator.comparing(Book::getPrice));
        return books;
    } 
    
    public static void main(String args[] ) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Book[] books = new Book[4]; 
        for(int i=0;i<4;i++){
            int id = Integer.parseInt(scanner.nextLine().trim());
            String title = scanner.nextLine().trim();
            String author = scanner.nextLine().trim();
            double price = Double.parseDouble(scanner.nextLine().trim());
            books[i] = new Book(id, title, author, price);
        }
        
        Book[] sortedBooks = sortBookByPrice(books);
        for(Book bookIter : sortedBooks) {
            System.out.println(
                bookIter.getId() + " " +
                bookIter.getTitle()+ " " +
                bookIter.getAuthor() + " " +
                bookIter.getPrice()                
            );
        }
        scanner.close();
    }
}

// --------------------------------------------------------------------------------------------------

class Document {
    int id;
    String title;
    String folderName;
    int pages;
    
    public Document(int id, String title, String folderName, int pages) {
        this.id = id;
        this.title = title;
        this.folderName = folderName;
        this.pages = pages;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getFolderName() {
        return folderName;
    }
    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }
    
    public int getPages() {
        return pages;
    }
    public void setPages(int pages) {
        this.pages = pages;
    }
}

class MainSolution {
    public static Document[] docsWithOddPages(Document[] docs) {
        ArrayList<Document> ans = new ArrayList<>();
        for(Document docsIter : docs) {
            if(docsIter.getPages() % 2 != 0) {
                ans.add(docsIter);
            }   
        }
        ans.sort(Comparator.comparing(Document:: getId));
        return ans.toArray(new Document[0]);
    }
    
    public static void main(String args[]) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Document[] docs = new Document[4];
        for(int i=0;i<4;i++) {
            int id = Integer.parseInt(scanner.nextLine());
            String title = scanner.nextLine();
            String folderName = scanner.nextLine();
            int pages = Integer.parseInt(scanner.nextLine().trim());
            
            docs[i] = new Document(id, title, folderName, pages);
        }   
        
        Document[] oddPagesDocs = docsWithOddPages(docs);
        for(Document docsIter : oddPagesDocs) {
            System.out.println(
                docsIter.getId() + " " +
                docsIter.getTitle() + " " +
                docsIter.getFolderName() + " " +
                docsIter.getPages()
            );
        }   
        scanner.close();      
    }
}