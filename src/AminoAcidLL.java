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
     int sum =0;
     for (int i=0; i<codons.length; i++){
         sum += codons[i];

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
  private int totalCount(){
    int sum = 0;
    for(int i=0;i<counts.length;i++){
        sum += counts[i];
    }
    return sum;
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
  public int aminoAcidCompare(AminoAcidLL inList) {
      if (!inList.isSorted()) {
          return -1;
      }
      int diff = 0;
      if (inList == null) {
          diff += totalCount();
          if (next != null) {
              diff += next.Compare(inList.next);
          } else if (aminoAcid == inList.aminoAcid) {
              diff = totalDiff(inList);
          }
      }
      if (next != null) {
          diff += next.compare(inList.next);
      }
      if (next == null && inList.next != null) {
          diff += compare(inList.next);
      } else if (next != null && aminoAcid < inList.aminoAcid) {
          diff += totalCount();
          if (next != null) {
              diff += next.compare(inList);
          }
      } else if (next == null || aminoAcid > inList.aminoAcid) {
          diff += inList.totalCount();
          if (inList.next != null) {
              diff += compare(inList.next);
          }
      }
      return diff;
  }

  /********************************************************************************************/
  /* Same as above, RECURSIVE TOO, but counts the codon usage differences
   * Must be sorted. */
  public int codonCompare(AminoAcidLL inList){
      if (!inList.isSorted()) {
          return -1;
      }
      int diff = 0;
      if (inList == null) {
          diff += totalCount();
          if (next != null) {
              diff += next.Compare(inList.next);
          } else if (aminoAcid == inList.aminoAcid) {
              diff = totalDiff(inList);
          }
      }
      if (next != null) {
          diff += next.compare(inList.next);
      }
      if (next == null && inList.next != null) {
          diff += compare(inList.next);
      } else if (next != null && aminoAcid < inList.aminoAcid) {
          diff += totalCount();
          if (next != null) {
              diff += next.compare(inList);
          }
      } else if (next == null || aminoAcid > inList.aminoAcid) {
          diff += inList.totalCount();
          if (inList.next != null) {
              diff += compare(inList.next);
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
          //now we have to populate the array
          for (int i = 0; i < array.length; i++) {
              for (int j = 0; j < array.length-1; j++) {

              }//closes for loop

          }//closes else
          return toReturn;
      }
  }

      /********************************************************************************************/
      /* Recursively returns the total counts of amino acids in the order that they are in in the linked list. */
      //is like AAList ^^^
      public int[] aminoAcidCounts () {
          if (next == null) {
              return new char[]{aminoAcid};
          } else {
              char[] array = next.aminoAcidList();
              int[] toReturn = new int[array.length + 1];
              //now we have to populate the array
              for (int i = 0; i < array.length; i++) {
                  for (int j = 0; j < array.length-1; j++) {

                  }//closes for loop

              }//closes else
              return toReturn;
          }
      }
          return new int[]{};
      }


      /********************************************************************************************/
      /* recursively determines if a linked list is sorted or not */
      //compares the aminoacids
      public boolean isSorted () {
          if (next==null) {
              return true;
          } else {
              return false;
          }
      }
  /********************************************************************************************/
  /* Static method for generating a linked list from an RNA sequence */
  public static AminoAcidLL createFromRNASequence(String inSequence) {
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
      for (int i=0; i < inList.length;i++){
          for (int j=i+1; j < inList.length;j++){
              if (inList[j] < inList[i]){
                  temp = array[i];
                  array[i]=array[j];
                  array[j=temp;
              return array;
              } else {
              return null;
              }
          }
      }
  }
}