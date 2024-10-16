class B__LogicalOperator
{
    public static void main(String args[]){
        int x = 5;
        int y = 3;

        // Both the cases are check irrespective of the fact that either of case can give the correct statements.
        System.out.println(x > y & x == y);
        System.out.println(x > y | x == y);
        
        // ___________________  Short Circuit  ____________________________________________________________________________
        // -->>  It checks the first case if it is false then for AND case it won't check second case and return false     |
        //    -->>      while for OR case if first case is TRUE then it won't check the other case                         |
        System.out.println(x > y && x == y);                                                                           //  |
        System.out.println(x > y || x == y);                                                                           //  |
        // ________________________________________________________________________________________________________________|
    }
}              