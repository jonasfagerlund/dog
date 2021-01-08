public class Format {
  public static void main(String[] args){

    int i = 511;
    double d = 0.01;

    /**
     * Innan punkten preciserar jag hur hela utskriften utgår i detta fallet 0000000000.
     * Efter punkten bestämmer jag hur många decimalen som skall vara med.
     */
    System.out.printf("%011.03f \n", d); // 0000000,010

    System.out.printf("%05d \n", i); // utgår från 5 nollor och fyller upp dem. 
    System.out.printf("%3d", i); // utgår från 3 blanka steg och fyller upp dem.
    // fylls det inte upp innehåller de alltid minst det som står innan 5 nollor respektive 3 blankslag.

  }
}
