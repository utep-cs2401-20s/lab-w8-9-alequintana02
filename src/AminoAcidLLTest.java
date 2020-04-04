import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AminoAcidLLTest {
    @Test
    public static void test1(){
        //testing to be sure my code results in the same as the numbers in readme
        String str = "CCGUUGGCACUGUUG";
        isSorted is = new isSorted();
        is.isSorted(str);
        assertTrue(true);
    }

    @Test
    public static AminoAcidLL sort(){
        String str = "CCGUUGGCACUGUUG";

        char [] expect= {"A","L","L","L","P"};
    }
    @Test
    public int aminoAcidCompare() {

    }

    @Test
    public int codonCompare(){

    }

    @Test
    public char[] aminoAcidList(){

    }

    @Test
    public int[] aminoAcidCounts(){

    }

    @Test
    public static AminoAcidLL createFromRNASequence(String inSequence) {

    }


}//closes AALL Tester class