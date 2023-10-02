package eventorganizer;
import java.util.Calendar;

/**
 * The Date class is the primary class that stores the inputted date
 * the class contains 10 methods
 * Date(String date){}:
 * This method takes the string input of the date (ex:6/12/2023) and converts it into an int
 * isValid();
 * This method checks if the date is a valid date
 * checkIfDateIsCorrect();
 * This method determines if the date is correct according to it being a leap year
 * checkDate();
 * this method checks if the date is a valid date ensuring it is at least 6 months away
 * it also determines that the inputted date is not a past date
 * date();
 * contsructor for the year, month, day
 * getYear(), getMonth(), getDay()
 * getter methods for year, month and day
 * equals()
 * check if the date is equal to the object date
 * compareTo()
 * compares the private date to the Date date
 * @author Judah Farkas, David Rahabi
 */
public class Date implements Comparable<Date> { // add comparable method
    private int year;
    private int month;
    private int day;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    /** Constructor that splits the given date from string into ints
     * input: 6/12/23
     * month = 6
     * day = 12
     * year= 2023
     * @param date string with the date
     */
    Date(String date){
        String [] splitD = date.split("/");
        this.month = Integer.parseInt(splitD[0]);
        this.day = Integer.parseInt(splitD[1]);
        this.year = Integer.parseInt(splitD[2]);
    }

    /** Checks if date is a valid calendar date
     *
     * @return true, if date is invalid return false
     */
    public boolean isValid() {
        if(!checkIfDateIsCorrect() || !checkDate(this.month, this.day, this.year) ){
            return false;
        }
        return true;
    }

    /** An extension to the isValid method, checks if the date is valid date as well as a leap year
     * @return true if date is valid, false if invalid date or leap year
     */
    public boolean checkIfDateIsCorrect(){
        if (this.year > 1900) {
            switch (this.month) {
                case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                    if (this.day >= 1 && this.day <= 31) {
                        break;
                    }
                    System.out.println(this.month+"/"+this.day+"/"+this.year+": Invalid Calendar Date!");
                    return false;
                case 4: case 6: case 9: case 11:
                    if (this.day >= 1 && this.day <= 30) {
                        break;
                    }
                    System.out.println(this.month+"/"+this.day+"/"+this.year+": Invalid Calendar Date!");
                    return false;
                case 2:
                    if (this.year % QUADRENNIAL == 0) {
                        if (this.year % CENTENNIAL == 0) {
                            if (this.year % QUATERCENTENNIAL == 0) {
                                if (this.day <= 29 && this.day >= 1) {
                                }
                            } else if (this.day <= 28 && this.day >= 1){
                            }
                        } else if (this.day <= 29 && this.day >= 1){
                        }
                    } else if (this.day <= 28 && this.day >= 1){
                    }else{
                        System.out.println(this.month+"/"+this.day+"/"+this.year+": Invalid Calendar Date!");
                        return false;
                    }
            }
        }
        return true;
    }

    /** Checks if the Date is a valid date that isnt in the past or 6 months away
     *
     * @param month month
     * @param day day
     * @param year year
     * @return true if valid, false if not
     */
    public boolean checkDate(int month, int day, int year){
        if((this.month>12) || (this.day>31) || this.month<1 || this.day<1){
            System.out.println(this.month+"/"+this.day+"/"+this.year+": Invalid Calendar Date!");
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        if(this.year < calendar.get(Calendar.YEAR) || (this.year==calendar.get(Calendar.YEAR) && this.month < calendar.get(Calendar.MONTH)+1) ||
                (this.year==calendar.get(Calendar.YEAR) && this.month < calendar.get(Calendar.MONTH)+1 && this.day < calendar.get(Calendar.DAY_OF_MONTH))){
            System.out.println(this.month+"/"+this.day+"/"+this.year+": Event date must be a future date!");
            return false;
        }
        calendar.add(calendar.MONTH, 6);
        if(year>calendar.get(Calendar.YEAR) || (year==calendar.get(Calendar.YEAR) && month>calendar.get(Calendar.MONTH)+1) || (year==calendar.get(Calendar.YEAR) && month==calendar.get(Calendar.MONTH)+1 && day>calendar.get(Calendar.DAY_OF_MONTH))){
            System.out.println(this.month+"/"+this.day+"/"+this.year+": Event date must be within 6 months!");
            return false;
        }
        return true;
    }

    /** Constructor for the date class
     *
     * @param month month
     * @param day day
     * @param year year
     */
    Date(int month, int day, int year){
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /** Getter method for the year
     *
     * @return year
     */
    public int getYear() {
        return year;
    }

    /** Getter method for month
     *
     * @return month
     */
    public int getMonth() {
        return month;
    }

    /** Getter method for day
     *
     * @return day
     */
    public int getDay() {
        return day;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Date) {
            Date date = (Date) obj;

            return date.year == this.year && date.month == this.month && date.day == this.day;

        }
        return false;
    }

    @Override
    public int compareTo(Date date) {
        if (this.year > date.year)
            return 1; // compare years first
        else if (date.year > this.year)
            return -1;
        else {// if they are equal
            if (this.month > date.month)
                return 1; // compare months
            else if (date.month > this.month)
                return -1;
            else { // if months are equal
                if (this.day > date.day)
                    return 1; // compare days
                else if (date.day > this.day)
                    return -1;
                else
                    return 0; // if days are equal
            }
        }
        /*
         * NEED TO CHECK IS THIS IS OK TO DO/ THIS IS HOW WE ARE SUPPOSED TO DO THIS
         */
    }

    /** test cases
     * @param args
     */
    public static  void main(String[] args){
        testDaysInFeb_NonLeap();
        testDaysInFeb_Leap();
    }

    /** Test Case #1 */
    private static void testDaysInFeb_NonLeap(){
        Date date = new Date(2, 29, 2011);
        boolean expectedOutput = false;
        boolean actualOutput = date.isValid();
        System.out.println("**Test case #1: # of days in Feb. in a non-leap year is 28");
        testResult(date, expectedOutput, actualOutput);
    }
    /** Test Case 2*/
    public static void testDaysInFeb_Leap(){
        Date date = new Date(2, 29, 2012);
        boolean expectedOutput = true;
        boolean actualOutput = date.isValid();
        System.out.println("**Test case #2: # of days in Feb. in a non-leap year is 29");
        testResult(date, expectedOutput, actualOutput);
    }



    public static void testResult(Date date, boolean expectedOutput, boolean actualOutput){
        if (expectedOutput == actualOutput){
            System.out.println(true);
        }
        else {
            System.out.println(false);
        }
    }
}
