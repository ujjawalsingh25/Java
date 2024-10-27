// ___________________________________________________________________
// equalsIgnoreCase     -> Line 93 -->> to check if Strings equals avoiding cases (like 'SoNy' = 'sony')
// ___________________________________________________________________
// scanner.nextLine();  -> Line 77 -->> 
// When you use scanner.nextLine(), it reads the entire line of text, including the newline character (\n) at the end.
// Other methods like nextInt() and nextBoolean() read only the number or boolean, but they don’t consume the newline character that follows. This leftover newline can cause issues in subsequent inputs.
// Cause of InputMismatchException:
// If you call nextInt() or nextBoolean() right after nextLine(), the newline character (\n) left in the buffer can disrupt the input.
// This mismatch happens because nextInt() expects an integer, but it encounters the newline (\n), leading to an InputMismatchException.
// Solution:
// To avoid this, add an extra nextLine() after calling nextInt() or nextBoolean(). This clears the leftover newline character from the buffer, allowing your program to continue reading correctly.
// ___________________________________________________________________
// Example: 
// String name = scanner.nextLine(); // Reads a line (with newline)
// int age = scanner.nextInt();       // Reads an integer
// scanner.nextLine();                // Clears the newline after integer input
// ________________________________________________________________________________________________________

// boAt Baseline
// boAt
// 1220
// true
// Over Ear Wired
// boAt
// 549
// true
// In Ear with Mic
// JBL
// 450
// true
// Buds 2 Neo
// Realme
// 500
// true
// -------  OUTPUT -------
// boAt
// 549

import java.util.Scanner;

class HeadSets
{   
    private String headsetName;
    private String brand;
    private int price;
    private boolean available;

    public HeadSets(String headsetName, String brand, int price, boolean available) {
        this.headsetName = headsetName;
        this.brand = brand;
        this.price = price;
        this.available = available;
    }

    public String getHeadsetName() {
        return headsetName;
    }
    public void setHeadsetName(String headsetName) {
        this.headsetName = headsetName;
    }
    
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }

}

class I_Classes
{    
    public static int findTotalPriceForGivenBrand(HeadSets[] headSets, String brand) {
        int totalPrice = 0;
        for(HeadSets headsetIter : headSets) {
            if(headsetIter.getBrand().equalsIgnoreCase(brand)) {
                totalPrice += headsetIter.getPrice();
            }
        }
        return totalPrice > 0 ? totalPrice : 0;
    }
    
    public static HeadSets findAvailableHeadsetWithSecondMinPrice(HeadSets[] headSets) {
        HeadSets mostLowest = null;
        HeadSets secondLowest = null;
        for(HeadSets headSetsIter : headSets) {
            if(headSetsIter.isAvailable()) {
                if(mostLowest == null || headSetsIter.getPrice() < mostLowest.getPrice()) {
                    secondLowest = mostLowest;
                    mostLowest = headSetsIter;
                } 
                else if(secondLowest == null || headSetsIter.getPrice() < secondLowest.getPrice()) {
                    secondLowest = headSetsIter;
                }
            }
        }   
        return secondLowest;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HeadSets[] headset = new HeadSets[4];
    
        for(int i=0;i<4;i++) {
            String headsetName = scanner.nextLine();
            String brand = scanner.nextLine();
            int price = scanner.nextInt();
            boolean available = scanner.nextBoolean();
            scanner.nextLine();
            headset[i] = new HeadSets(headsetName, brand, price, available);
        }
    
        System.out.print("Enter Brand Name to Search: ");
        String brandToSearch = scanner.nextLine();

        int totalPrice = findTotalPriceForGivenBrand(headset, brandToSearch);
        if(totalPrice > 0)  System.out.println(totalPrice);
        else   System.out.println("No Headsets available with the given brand");
    
        HeadSets secondMinPrice = findAvailableHeadsetWithSecondMinPrice(headset);
        if(secondMinPrice != null)  {
            System.out.println(secondMinPrice.getHeadsetName());
            System.out.println(secondMinPrice.getPrice());
        } else {
            System.out.println("No Headsets Available");
        }
    
        scanner.close();
    }   
}