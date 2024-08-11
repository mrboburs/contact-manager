import java.util.Scanner;

public class Main {
  static   Contact [] contactArray= new  Contact[2];
    static int currentIndex=0;

    public static void main(String[] args) {

        boolean b=true;
        while (b){
            menu();

            int n= getMenuNumber();

            switch (n){
                case 1 :
                    Contact contact=addContact();
                    addToArray(contact);
                    break;
                case 2 :
                 printContact();
                    break;
                case 3 :
                    String query=searchQuery();
                    search(query);
                    break;
                case 4 :
                    String phone=deleteContactQuery();
                    deleteContactFromArray(phone);
                    break;
                case 0 :
                    b=false;
                    break;
                default:
                    System.out.println("choose correct number");
            }
        }

        System.out.println("that is end");
    }
    public  static boolean isPhoneExist(String phone){
        for (Contact contact :contactArray){

               if (contact!=null && contact.phone.equals(phone) ){
                   return true;
            }
        }
        return  false;
    }
    public static String deleteContactQuery(){
        System.out.print("Enter query: ");
        Scanner scanner =new Scanner(System.in);
        return  scanner.next();
    }
    public static void deleteContactFromArray(String phone){
        for (int i=0;i<contactArray.length;i++){
            if (contactArray[i]!=null && contactArray[i].phone.equals(phone)){
                contactArray[i]=null;
                System.out.println("contact deleted!!!");
                break;
            }
        }
    }
    public static void search(String query){
        query=query.toLowerCase();
        for (Contact c:contactArray){
            if (c==null) {
                continue;
            }
            if (c.name.toLowerCase().contains(query)
                        ||c.phone.contains(query)){
                    System.out.println("---- "+c.name+"  -  "+c.phone+"  ----");
                }

        }
    }

     public  static  String searchQuery(){
        System.out.print("Enter query: ");
        Scanner scanner =new Scanner(System.in);
       return  scanner.next();

   }

     public static void printContact(){
    for (Contact c: contactArray){
        if (c!=null){
            System.out.println(c.name+"  "+c.phone);
        }

    }
}

     public static boolean isValidContact(Contact contact){
        if (contact.name==null || contact.name.trim().length()<2){
            System.out.println("invalid contact name");
            return  false;
        }
        if (contact.phone==null || contact.phone.trim().length()<2){
            System.out.println("invalid contact phone");
            return  false;
        }
        char [] phoneArr=contact.phone.toCharArray();
        for (char c:phoneArr){
            if (c<'0'||c>'9'){
                System.out.println("invalid contact phone");
                return  false;
            }
        }
        return  true;
    }

     public  static  void addToArray(Contact contact){
         if (!isValidContact(contact)) {
             return;
         }
         if (isPhoneExist(contact.phone)){
             System.out.println("phone number exists  ");
             return;
         }

             if (currentIndex==contactArray.length){
                 Contact[ ]newArray= new Contact [contactArray.length*2];
                 for (int i=0;i<contactArray.length;i++){
                     newArray[i]=contactArray[i];

                 }
                 contactArray=newArray;
             }
             contactArray[currentIndex]=contact;
             currentIndex++;
             System.out.println("contact added");

}

     public static  Contact addContact(){
        Scanner scanner=new Scanner(System.in);

        System.out.print("Enter name: ");
        String name=scanner.next();

        System.out.print("Enter phone: ");
        String phone=scanner.next();

        Contact contact=new Contact();
        contact.name=name;
        contact.phone=phone;

        return  contact;

     }

     public static void menu(){
        System.out.println("** Menu **");
        System.out.println("1. Add Contact");
        System.out.println("2. Add List");
        System.out.println("3. Search");
        System.out.println("4. Delete Contact");
        System.out.println("0. Exit");


    }

     public  static  int getMenuNumber(){
        System.out.print("Choose Menu: ");
        Scanner scanner=new Scanner(System.in);
        return scanner.nextInt();

    }
}