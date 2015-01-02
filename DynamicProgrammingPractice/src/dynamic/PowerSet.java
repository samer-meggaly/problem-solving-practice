package dynamic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PowerSet <T> {
	
	Set<T> originalSet;
	Set<Set<T>> powerSet;
	
	public PowerSet(HashSet<T> aGivenSet) {
		originalSet = aGivenSet;
		powerSet = new HashSet<Set<T>>();
	}
	
	public Set<Set<T>> myPowerSet() {
		List<T> theElements = new ArrayList<T>(originalSet);
		int n = originalSet.size();	//The cardinality of the given set.
		long carOfPowerSet = (long) Math.pow(2, n);
		//Loop until all the set of the power set are generated,
		// starting from 0 to 2^n-1.
		for (int i = 0; i < carOfPowerSet; i++) {
			String byteStr = Integer.toBinaryString(i);
			Set<T> aNewSubset = new HashSet<T>();
			int j = 0;
			//The max length of this byte string is n.
			while (j < byteStr.length()) {
				//If the bit has 1 so the corresponding element 
				// should be added to this subset.
				if (byteStr.charAt(j) == '1') {
					aNewSubset.add(theElements.get((byteStr.length() - 1) - j));
				}
				j++;	//Check the next bit.
			}	//End of while loop
			powerSet.add(aNewSubset);
		}	//End of for loop.
		return powerSet;
	}
	
	//Stack overflow implementation
	public static <T> Set<Set<T>> powerSet(Set<T> originalSet) {
	    Set<Set<T>> sets = new HashSet<Set<T>>();
	    if (originalSet.isEmpty()) {
	        sets.add(new HashSet<T>());
	        return sets;
	    }
	    List<T> list = new ArrayList<T>(originalSet);
	    T head = list.get(0);
	    Set<T> rest = new HashSet<T>(list.subList(1, list.size())); 
	    for (Set<T> set : powerSet(rest)) {
	        Set<T> newSet = new HashSet<T>();
	        newSet.add(head);
	        newSet.addAll(set);
	        sets.add(newSet);
	        sets.add(set);
	    }           
	    return sets;
	}
	
	public static void main(String[] args) {
		Set<Integer> myTestSet = new HashSet<Integer>();
		for (int i = 0; i < 5; i++) {
			myTestSet.add(i + 1);
		}
		PowerSet<Integer> mySet = 
			new PowerSet<Integer>((HashSet<Integer>) myTestSet);
		//Testing using my implementation.
//		mySet.myPowerSet();
//		System.out.println("The cardinality of the PowerSet = " + mySet.powerSet.size());
//		for (int i = 0; i < 6; i++) {
//			for (Set<Integer> s : mySet.powerSet) {
//				if (s.size() == i) {
//					System.out.println(s);
//				}
//			}
//		}
		
		//Testing using Stack overflow implementation.
		for (Set<Integer> s : powerSet(mySet.originalSet)) {
			System.out.println(s);
		}
		int size = 10;
		for(int i = 0; i < (1 << size); i++)
			System.out.println(i);
	}

}
