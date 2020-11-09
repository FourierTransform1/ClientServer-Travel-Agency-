import java.io.*;

public class RegistrationHandler {



    private int numTravellers;
    private int numDays;
    private String travelMode;
    private String meals;
    private int totalPrice;

    private String name;
    private String phoneNumber;
    private String address;
    private File receipt;



    public String menu(){

       String menu = "WELCOME TO  THE VACATION PLANNER!!! " +
                "\nOur prices are as follows:" +
                "\n\tThe travel cost by ferry per person is 600SEK." +
                "\n\tFor air travel,it will be 900SEK." +
                "\n\tAccommodation is 250SEK per person per night. " +
                "\n\tMeals cost 100SEK per person per day." +
                "\n[1] ENTER THE NUMBER OF TRAVELLERS:\n";

       return menu;

    }


    public void setNumTravellers(int numTravellers) {
        this.numTravellers = numTravellers;
        System.out.println("number of traveller:  " + numTravellers);
    }



    public void setModeOfTraver(String travelMode){
        this.travelMode = travelMode;
        System.out.println("the mode of travel: " + travelMode);
    }



    public void setNumDays(int numDays){
        this.numDays = numDays;
        System.out.println("the number of days: " + numDays);
    }



    public void setMeals (String meals){
        this.meals = meals;
        System.out.println("meals: " + meals);

    }



    public void setName(String name){
        this.name = name;
        System.out.println("name: " + name);
    }



    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
        System.out.println("phone number: " + phoneNumber);

    }



    public void setAddress(String address){
        this.address = address;
        System.out.println("address: " + address);
    }



    public String priceCalculator( ){

        if (travelMode.equalsIgnoreCase("ferry")){
            totalPrice = 600+250;
        }else if(travelMode.equalsIgnoreCase("air")){
            totalPrice = 900+250;
        }else {
            return "Price could not be calculated!";
        }



        if(meals.equalsIgnoreCase("yes")){
            totalPrice +=100; }

        totalPrice = (totalPrice*numTravellers*numDays);

        return Integer.toString(totalPrice);
    }



    public File printReceipt(){

        String fileName = String.format("%sReceipt" , name);
        receipt = new File(fileName);

        //File receipt = new File("/Users/ahmedmohamud/Desktop/"+ fileName + ".txt");
        PrintWriter writer;

        try {

            writer = new PrintWriter(new FileWriter(receipt));

            writer.println("Booking successful!");
            writer.println("Name: " + name);
            writer.println("Address: " + address);
            writer.println("Phone Number: " + phoneNumber);
            writer.println("Number of  days: " + numDays);
            writer.println("Number of people: " + numTravellers);
            writer.println("Mode of travel: " + travelMode.toLowerCase());
            writer.println("Meals included: " + meals.toLowerCase());
            writer.println("Total cost: " + totalPrice);
            writer.println("Happy  travels" + name + "!!!");
            writer.flush();
            writer.close();



        }catch (IOException ioe){
            System.out.println(ioe);
        }
        this.receipt = receipt;

        return receipt;
    }



    public int getNumTravellers() {
        return numTravellers;
    }

    public int getNumDays() {
        return numDays;
    }

    public String getTravelMode() {
        return travelMode;
    }

    public String getMeals() {
        return meals;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }
}







