package cpis.pkg222.project;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Cpis222Project {

    public static void main(String args[]) {
        
       
        Scanner input = new Scanner(System.in);
    
        System.out.print("frames: ");

      //  String[] temp = input.nextLine().split(" ");
        int pages[] = new int[10];

          for (int i = 0; i < pages.length; i++) {
            pages[i]=(int)(Math.random()*4+12);
        }
          //          //refrence string ozy numbers
//   7 0 1 2 0 3 0 4 2 3 0 3 2 
//   1 2 3 4 1 2 5 1 2 3 4 5
          
         

           // int pages[] = new int[12];
             
          //  for (int i = 0; i < pages.length; i++) {
             //   pages[i]=(int) (Math.random()*9);  
         
//}

        System.out.print("enter the Frames: ");
        int capacity = input.nextInt();
        System.out.println("choose 1 for FIFO and 2 for LRU");
        int choice=input.nextInt();
        if (choice==1) {
            FIFO(pages, pages.length, capacity);
        }
        else if (choice==2) {
             LRU(pages, pages.length, capacity);
        }
}

       
    

// Method  faults using FIFO 
    static int FIFO(int pages[], int numberOfPages, int Frames) {
        // To represent set of current pages. We use
        // an unordered_set so that we quickly check
        // if a page is present in set or not
        HashSet<Integer> set = new HashSet<>(Frames);

        // To store the pages in FIFO manner
        Queue<Integer> indexes = new LinkedList<>();

        // Start from initial page
        int page_faults = 0;
        for (int i = 0; i < numberOfPages; i++) {
            // Check if the set can hold more pages
            if (set.size() < Frames) {
                // Insert it into set if not present
                // already which represents page fault
                if (!set.contains(pages[i])) {
                    set.add(pages[i]);

                    // increment page fault
                    page_faults++;

                    // Push the current page into the queue
                    indexes.add(pages[i]);

                    System.out.print("inserting:" + pages[i] + " ");
                    System.out.print(set.toString());
                    System.out.println(" page faults: " + page_faults);

                } else {
                    System.out.print("inserting:" + pages[i] + " ");
                    System.out.print(" already found in the memory, so nothing will change ");

                    System.out.print(set.toString());
                    System.out.println(" page faults: " + page_faults);
                }
            } // If the set is full then need to perform FIFO
            // i.e. remove the first page of the queue from
            // set and queue both and insert the current page
            else {
                // Check if current page is not already
                // present in the set
                if (!set.contains(pages[i])) {
                    System.out.print("inserting:" + pages[i] + " ");
                    //Pop the first page from the queue
                    int val = indexes.peek();
                    System.out.print(" wasnt found so the " + val + " in will be removed and " + pages[i] + " will be added ");

                    indexes.poll();

                    // Remove the indexes page
                    set.remove(val);

                    // insert the current page
                    set.add(pages[i]);

                    // push the current page into
                    // the queue
                    indexes.add(pages[i]);

                    // Increment page faults
                    page_faults++;

                    System.out.print(set.toString());
                    System.out.println(" page faults: " + page_faults);

                } else {
                    System.out.print("inserting:" + pages[i] + " ");
                    System.out.print(" already found in the memory, so nothing will change ");

                    System.out.print(set.toString());
                    System.out.println(" page faults: " + page_faults);
                }
            }
        }

        return page_faults;
    }

    static int LRU(int pages[], int n, int Frames) {

        // To represent set of current pages. We use
        // an unordered_set so that we quickly check
        // if a page is present in set or not
        HashSet<Integer> s = new HashSet<>(Frames);

        // To store least recently used indexes
        // of pages.
        HashMap<Integer, Integer> indexes = new HashMap<>();

        // Start from initial page
        int page_faults = 0;
        for (int i = 0; i < n; i++) {
            // Check if the set can hold more pages
            if (s.size() < Frames) {
                // Insert it into set if not present
                // already which represents page fault
                if (!s.contains(pages[i])) {

                    s.add(pages[i]);

                    // increment page fault
                    page_faults++;
                    System.out.print("inserting: " + pages[i] + ". memory isnt full ");
                    System.out.print(s);
                    System.out.println(" page faults: " + page_faults);
                }

                // Store the recently used index of
                // each page
                indexes.put(pages[i], i);
            } // If the set is full then need to perform lru
            // i.e. remove the least recently used page
            // and insert the current page
            else {
                // Check if current page is not already
                // present in the set
                if (!s.contains(pages[i])) {
                    // Find the least recently used pages
                    // that is present in the set
                    int lru = Integer.MAX_VALUE, val = Integer.MIN_VALUE;

                    Iterator<Integer> itr = s.iterator();

                    while (itr.hasNext()) {
                        int temp = itr.next();
                        if (indexes.get(temp) < lru) {

                            lru = indexes.get(temp);
                            val = temp;

                        }
                    }

                    // Remove the indexes page
                    s.remove(val);
                    //remove lru from hashmap
                    indexes.remove(val);
                    // insert the current page
                    s.add(pages[i]);

                    // Increment page faults
                    page_faults++;
                    System.out.print("inserting: " + pages[i] + ". not "
                            + "found in memory ");
                    System.out.print(s);
                    System.out.println(" page faults: " + page_faults);

                }

                // Update the current page index
                indexes.put(pages[i], i);
                System.out.print("inserting: " + pages[i] + ".  "
                        + "found in memory ");
                System.out.print(s);
                System.out.println(" page faults: " + page_faults);
            }
        }

        return page_faults;
    }
}
