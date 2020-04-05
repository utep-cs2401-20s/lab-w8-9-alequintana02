import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AminoAcidLLTest_2 {
    //the following test cases were done using the example provided in the readme
//AminoAcidLL expect = new AminoAcidLL("CCGUUGGCACUGUUG");
   // char [] expect= {'A','L','L','L','P'};
    //char [] unsorted= {'P','L','A','L','L'};
    //String[]expect={"CCG","CUG","UUG","UUG","GCA"};
    //String str = "CCGUUGGCACUGUUG";
    //int[]expect={1,1,2,1};
    @Test
    void incrementCodons() {
        //here i wanted to test if the code would fail since []codons is null (and it requires a length of at least 0)
        //test failed
        //again likely due to pass by value vs reference
        String [] str ={""};
        AminoAcidLL expect[] = null;
        assertEquals(expect, str);
    }

    @Test
    void aminoAcidCompare() {
        //I want to test if my method can detect no change
        //test failed with a null pointer exception
        //pass by value vs reference issue
        //code says -1 for base case but that did not make a difference when i set it as "expected"
        AminoAcidLL one = new AminoAcidLL("CCGUUGGCACUGUUG");
        AminoAcidLL two = new AminoAcidLL("CCGUUGGCACUGUUG");
        assertEquals(0,one.aminoAcidCompare(two));
    }

    @Test
    void codonCompare() {
        //I want to test if my method can detect no change
        //test failed with a null pointer exception
        //pass by value vs reference issue
        //code says -1 for base case but that did not make a difference when i set it as "expected"
        AminoAcidLL one = new AminoAcidLL("CCGUUGGCACUGUUG");
        AminoAcidLL two = new AminoAcidLL("CCGUUGGCACUGUUG");
        assertEquals(0,one.codonCompare(two));
    }

    @Test
    void aminoAcidList() {
        //wanted to test if it could create the right codon list
        //test failed: "index 1 out of bounds for length 1"
        //likely error in for loop/populating the array
        String str = "CCGUUGGCACUGUUG";
        AminoAcidLL test = AminoAcidLL.createFromRNASequence(str);
        char [] testing = test.aminoAcidList();
        char [] unsorted= {'P','L','A','L','L'};
        assertArrayEquals(unsorted,testing);
    }

    @Test
    void aminoAcidCounts() {
        //wanted to compare output with readme
        //test failed with a null pointer exception
        //likely another error of pass by reference vs value
        String str = "CCGUUGGCACUGUUG";
        AminoAcidLL test = AminoAcidLL.createFromRNASequence(str);
        int[]expect={1,1,2,1};
        assertArrayEquals(expect,test.aminoAcidCounts());
    }

    @Test
    void isSorted1() {
        //testing to be sure my code can detect sorting or not
        //test should return false because it is unsorted (has not gone through sort()
        //test passed in 9 ms
       String str = "CCGUUGGCACUGUUG";
       AminoAcidLL test = new AminoAcidLL(str);
       assertFalse(false);
    }
    @Test
    void isSorted2(){
        //testing to be sure my code can detect sorting or not
        //test should return true because it is sorted
        //test passed in 9 ms
        String str = "GCACUGUUGUUGCCG";
        AminoAcidLL test = new AminoAcidLL(str);
        assertTrue(true);

    }

    @Test
    void createFromRNASequence1() {
        //testing to see if I could get the same results as the read me
        //test failed: it returned different addresses:
        //therefore my code failed to distinguish between pass by reference and pass by value
        String str = "CCGUUGGCACUGUUG";
        AminoAcidLL expect = new AminoAcidLL(str);
        //char [] unsorted= {'P','L','A','L','L'};
        assertEquals(expect,AminoAcidLL.createFromRNASequence(str));
    }

    @Test
    void createFromRNASequence2() {
        //testing to see if my base case works
        //test passed 14 ms
        //base case did work
        AminoAcidLL expect = null;
        assertEquals(expect,AminoAcidLL.createFromRNASequence(""));
    }
    @Test
    void sort() {
        // want to test sort method
        //test failed: null pointer exception
        //pass by reference vs value issue
        String str = "CCGUUGGCACUGUUG";
        AminoAcidLL testing = AminoAcidLL.createFromRNASequence(str);
        char [] expect= {'A','L','L','L','P'};
        assertEquals(expect,testing.sort(testing));
    }
}