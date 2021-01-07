import java.util.*;

public class CommandEnum {

  public void hej(){
    System.out.println("Hej");
  }

  public void da() {
    System.out.println("da");
  }


  public static void main(String[] args){

    CommandEnum ce = new CommandEnum();
    
    int x = 1;
    int y = 0;

    // if(x==y);
    // ce.hej();
    // ce.da();

    // Det som är mellan % och d är det som formaterar värdet
    System.out.printf("%03d", 1); // här skrivs 001 efter som 03 betyder tre nollar står är ursprunget





}
}
