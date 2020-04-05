class AminoAcidLL {
  char aminoAcid;
  String[] codons;
  int[] counts;
  AminoAcidLL next;

  AminoAcidLL() {}
  /********************************************************************************************/
  /* Creates a new node, with a given amino acid/codon
   * pair and increments the codon counter for that codon.
   * NOTE: Does not check for repeats!! */
  public AminoAcidLL(String inCodon) {
    aminoAcid = AminoAcidResources.getAminoAcidFromCodon(inCodon);
    codons = AminoAcidResources.getCodonListForAminoAcid(aminoAcid);
    incrementCodons(inCodon);
    next=null;
    //counts should be the same size as codons
  }

  //Michelle recommends to write a helped method
  public void incrementCodons(String inCodon){
     for (int i=0; i < codons.length; i++){
         if (codons[i] == inCodon){
             counts[i]+=1;
         }
     }
}

  /********************************************************************************************/
  /* Recursive method that increments the count for a specific codon:
   * If it should be at this node, increments it and stops, 
   * if not passes the task to the next node. 
   * If there is no next node, add a new node to the list that would contain the codon. 
   */
  private void addCodon(String c){
    //need a base case
    if(aminoAcid==AminoAcidResources.getAminoAcidFromCodon(c)) {
      incrementCodons(c);
    } if(next!=null) {
        next.addCodon(c);
    } else {
        next = new AminoAcidLL(c);
    }
 }//closes addCodon


  /********************************************************************************************/
  /* Shortcut to find the total number of instances of this amino acid */
  //returns sum of difference over whole list
  private int totalCount(){
    int sum = 0;
    for(int i=0;i<counts.length;i++){
        sum += counts[i];
    }
    return Math.abs(sum);
  }

  /********************************************************************************************/
  /* helper method for finding the list difference on two matching nodes
  *  must be matching, but this is not tracked */
  private int totalDiff(AminoAcidLL inList){
    return Math.abs(totalCount() - inList.totalCount());
  }


  /********************************************************************************************/
  /* helper method for finding the list difference on two matching nodes
  *  must be matching, but this is not tracked */
  private int codonDiff(AminoAcidLL inList){
    int diff = 0;
    for(int i=0; i<codons.length; i++){
      diff += Math.abs(counts[i] - inList.counts[i]);
    }
    return diff;
  }

  /********************************************************************************************/
  /* Recursive method that finds the differences in **Amino Acid** counts. 
   * the list *must* be sorted to use this method */
  /*********the code below (and for codonCompare) was done with the help of Cynthia during office hours////////****/
  public int aminoAcidCompare(AminoAcidLL inList) {
      if (!inList.isSorted()|| (next==null && inList == null)) {
          return -1;
      }
      int diff = 0;
      next.aminoAcidCompare(inList);
      aminoAcidCompare(inList.next);
      if (inList == null) {
          diff += totalCount();
          if (next != null) {
              diff += next.aminoAcidCompare(inList.next);
          } else if (aminoAcid == inList.aminoAcid) {
              diff = totalDiff(inList);
          }
      }
      if (next != null) {
          diff += next.aminoAcidCompare(inList.next);
      }
      if (next == null && inList.next != null) {
          diff += aminoAcidCompare(inList.next);
      } else if (next != null && aminoAcid < inList.aminoAcid) {
          diff += totalCount();
          if (next != null) {
              diff += next.aminoAcidCompare(inList);
          }
      } else if (next == null || aminoAcid > inList.aminoAcid) {
          diff += inList.totalCount();
          if (inList.next != null) {
              diff += aminoAcidCompare(inList.next);
          }
      }
      return diff;
  }

  /********************************************************************************************/
  /* Same as above, RECURSIVE TOO, but counts the codon usage differences
   * Must be sorted. */
  /******the code below (and for aaCompare) was done with the help of Cynthia during office hours**********/
  public int codonCompare(AminoAcidLL inList){
      if (!inList.isSorted()|| (next==null && inList == null)) {
          return -1;
      }
      int diff = 0;
      if (inList == null) {
          diff += totalCount();
          if (next != null) {
              diff += next.codonCompare(inList.next);
          } else if (aminoAcid == inList.aminoAcid) {
              diff = totalDiff(inList);
          }
      }
      if (next != null) {
          diff += next.codonCompare(inList.next);
      }
      if (next == null && inList.next != null) {
          diff += codonCompare(inList.next);
      } else if (next != null && aminoAcid < inList.aminoAcid) {
          diff += totalCount();
          if (next != null) {
              diff += next.codonCompare(inList);
          }
      } else if (next == null || aminoAcid > inList.aminoAcid) {
          diff += inList.totalCount();
          if (inList.next != null) {
              diff += codonCompare(inList.next);
          }
      }

      return diff;
  }

  /********************************************************************************************/
  /* Recursively returns the total list of amino acids in the order that they are in in the linked list. */
  public char[] aminoAcidList() { //combination of loops and recursion
      if (next == null) {
          return new char[]{aminoAcid};
      } else {
          char[] array = next.aminoAcidList();
          char[] toReturn = new char[array.length + 1];
          array[0] = aminoAcid;
          //now we have to populate the array
          for (int i = 0; i < array.length; i++) {
              array[i+1] = toReturn[i];
          }//closes for loop
          return toReturn;
      }
  }

      /********************************************************************************************/
      /* Recursively returns the total counts of amino acids in the order that they are in in the linked list. */
      //is like AAList ^^^ but uses INT ARRAYS
      public int[] aminoAcidCounts() {
          if (next == null) {
              return new int[]{totalCount()};
          } else {
              int[] array = next.aminoAcidCounts();
              int[] toReturn = new int[array.length + 1];
              array[0] = totalCount();
              //now we have to populate the array
              for (int i = 0; i < array.length; i++) {
                  array[i+=1] = toReturn[i];
              }//closes for loop
              return toReturn;
          }
      }

      /********************************************************************************************/
      /* recursively determines if a linked list is sorted or not */
      //compares the aminoacids
      public boolean isSorted () {
          if (next==null) {
              return true;
          }
          //} else {//if (aminoAcid > next.aminoAcid){
              return false;
      }
  /********************************************************************************************/
  /* Static method for generating a linked list from an RNA sequence */
  public static AminoAcidLL createFromRNASequence(String inSequence) {
      if (inSequence.length()==0){
          return null;
      }
      AminoAcidLL list = new AminoAcidLL();
      for (int i = 0; i < inSequence.length(); i += 3) {//because you want to separate the string into segments of 3
          String str = inSequence.substring(i, i += 3);
          list.addCodon(str);
      }
      return list;
  }

  /********************************************************************************************/
  /* sorts a list by amino acid character*///does not have to be recursive!
      //use bubble, selection, or insert sort
      //we will need a temp
  public static AminoAcidLL sort(AminoAcidLL inList){
      AminoAcidLL toSort = new AminoAcidLL();
      if (toSort.aminoAcid > toSort.next.aminoAcid){
          //the following swaps the values
          char temp = toSort.aminoAcid;
          toSort.aminoAcid = toSort.next.aminoAcid;
          toSort.next.aminoAcid = temp;
          toSort = toSort.next;
      } if (toSort.next == null && inList.isSorted()){
          toSort = inList;
      }else {
          toSort = toSort.next;
          if (toSort.next == null && inList.isSorted()){
              toSort = inList;
          }
      }
    return inList;
  }
}//closes AALL class