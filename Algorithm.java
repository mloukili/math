package com.bluenimble.refactor;

import java.util.ArrayList;
import java.util.Arrays;

public class Algorithm {

	/*
	 * Proving that there is a magic number is the result of a combination of numbers where you subtract or add to each other 
	 * is equivalent to prove that there is a subset of these numbers where the sum is equals to (S+M)/2 where S is the sum of all numbers 
	 * and M is the magic number.
	 * Please take a look for the proof at https://github.com/mloukili/math
	 * 
	 * This valid only when the magic number is positive number, if not, we should add a new element to the list compensating the (- M + 1) 
	 * 
	 * First, you may notice that there will be a solution only and only if both S and M are odd or even
	 * 
	 * 
	 */
	public static boolean arithmeticBoggle (int magicNumber, ArrayList<Integer> numbers) {
		
		// if the magic number is negative, by adding -MagicNumber + 1
		if (magicNumber < 0) {
			numbers.add (1 - magicNumber);
			magicNumber = 1;
		}
		
		// if the series of numbers is empty
		if (magicNumber == 0) {
			// if the magic number is 0, should return true
			if (numbers == null || numbers.isEmpty ()) {
				return true;
			} else {
				return false;
			}
		}
		
		// set the magic number to (S + M) / 2
		double candidateMagicNumber = (numbers.stream ().mapToDouble (d -> d.doubleValue ()).sum () + magicNumber ) / 2;
		// if candidate Magic Number is not a natural number, we should return false, no solution
		if ((candidateMagicNumber - (int)candidateMagicNumber) != 0) {
			return false;
		}
		
		magicNumber = (int)candidateMagicNumber;
		
		System.out.println ("New magic number: " + magicNumber);
		
		int sum = numbers.get (0);
		int start = 0;

		for (int i = 1; i <= numbers.size (); i++) {
			// if sum exceeds the magicNumber, then remove the head elements
			while (sum > magicNumber && start < i - 1) {
				sum = sum - numbers.get (start);
				start++;
			}
			// if sum becomes equal to magicNumber, then return true
			if (sum == magicNumber) {
				return true;
			}
			// add this number to the sum
			if (i < numbers.size ()) {
				sum = sum + numbers.get (i);
			}
		}

		return false;
	}

	public static void main (String[] args) {
		boolean found = arithmeticBoggle (
			-42,
			new ArrayList<Integer> (Arrays.asList (1,13,57,17,19,35))
		);
		System.out.println ("Found: " + found);
	}
}