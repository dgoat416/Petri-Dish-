import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Point;

public class Main 
{

	/**
	 * Populates the container with the contents of the input file
	 * @param patternHolder = the 2d arrayList to hold the contents of the file
	 * @return the 2d arrayList with all the contents of the file
	 */
	public static void populateArrList(ArrayList<ArrayList<Character>> patternHolder)
	{
		File inFile = new File("input.txt");
		String temp = "";
		int row = 0, 
				col = 0;
		ArrayList<Point> shape = new ArrayList<Point>();

		//ArrayList<ArrayList<Character>> patternHolder = new ArrayList<ArrayList<Character>>();

		try
		{
			Scanner read = new Scanner(inFile);
			while (read.hasNext())
			{

				temp = read.nextLine();

				// populate 2D arrayList
				for (col = 0; col < temp.length(); col++)
				{
					// add new row
					if (col == 0)
						patternHolder.add(new ArrayList<Character>());

					// populate the columns of the rows
					patternHolder.get(row).add(col, temp.charAt(col));

					// DONT NEED CUZ PLAN ON REPLACING THE * WITH LETTERS
					// INSTEAD OF RECOPYING
					//					// gets the points that make up the shape
					//					if (temp.charAt(col) != 32)
					//						shape.add(new Point(row, col));

				}

				row++;

			}
		} 
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Identify the shapes by matching the input with all the 
	 * permutations of the previous shapes 
	 * (if no previous shapes, then return a new character)
	 * @param alphabet = the array of letters in the alphabet
	 * @param a = the letter we are at in the alphabet array
	 * @return the character that the shape should be in the
	 * 				  output file
	 */
	public static char identifyShape(char[] alphabet, int a)
	{
		// if we are on the first shape 
		if (alphabet[a] == 'a')
			return 'a';

		// else we have identified previous shapes previously
		// so generate all those permutations and match against 
		// current shape


		// if no matches from above
		a++;
		return alphabet[a];

	}


	/**
	 * Determines if two characters are adjacent 
	 * @param list = 2d array that holds the shapes
	 * @param row = the row of one of the characters
	 * @param col = the col of one of the characters
	 * @param row2 = another row of one of the characters
	 * @param col2 = another col of one of the characters
	 * @return 
	 * 				= true if the two chars are adjacent
	 * 				= false otherwise
	 */
	public static boolean isAdjacent(ArrayList<ArrayList<Character>> list, int row, int col, int row2, int col2)
	{
		
		// 8 situations
		if (row >  0 && col > 0)
		{
			if (row2 == row - 1 && col2 == col - 1)
				if (list.get(row - 1).get(col - 1) == '*')
					return true;
		}

		if (col > 0)
		{
			if (row2 == row && col2 == col - 1)
				if (list.get(row).get(col - 1) == '*')
					return true;
		}

		if (row < list.size() && col > 0)
		{
			if (row2 == row + 1 && col2 == col - 1)
				if (list.get(row + 1).get(col - 1) == '*')
					return true;
		}

		if (row > 0)
		{
			if (row2 == row - 1 && col2 == col)
				if (list.get(row - 1).get(col) == '*')
					return true;
		}

		if (row < list.size())
		{
			if (row2 == row + 1 && col2 == col)
				if (list.get(row + 1).get(col) == '*')
					return true;
		}

		if (row > 0 && col < list.get(row).size() )
		{
			if (row2 == row - 1 && col2 == col + 1)
				if (list.get(row - 1).get(col + 1) == '*')
					return true;
		}

		if (col < list.get(row).size())
		{
			if (row2 == row && col2 == col + 1)
				if (list.get(row).get(col + 1) == '*')
					return true;
		}

		if (row < list.size() && col < list.get(row).size())
		{
			if (row2 == row + 1 && col2 == col + 1)
				if (list.get(row + 1).get(col + 1) == '*')
					return true;
		}

		return false;

		//		// 8 situations
		//		if (row >  0 && col > 0)
		//			{
		//				if (list.get(row - 1).get(col - 1) == '*')
		//					return true;
		//			}
		//		
		//		if (col > 0)
		//		{
		//			if (list.get(row).get(col - 1) == '*')
		//				return true;
		//		}
		//		
		//		if (row < list.size() && col > 0)
		//		{
		//			if (list.get(row + 1).get(col - 1) == '*')
		//					return true;
		//		}
		//		
		//		if (row > 0)
		//		{
		//			if (list.get(row - 1).get(col) == '*')
		//				return true;
		//		}
		//		
		//		if (row < list.size())
		//		{
		//			if (list.get(row + 1).get(col) == '*')
		//				return true;
		//		}
		//		
		//		if (row > 0 && col < list.get(row).size() )
		//		{
		//			if (list.get(row - 1).get(col + 1) == '*')
		//				return true;
		//		}
		//		
		//		if (col < list.get(row).size())
		//		{
		//			if (list.get(row).get(col + 1) == '*')
		//				return true;
		//		}
		//		
		//		if (row < list.size() && col < list.get(row).size())
		//		{
		//			if (list.get(row + 1).get(col + 1) == '*')
		//				return true;
		//		}
		//		
		//		return false;
	}

/**
 * Finds the next adjacent character in the current shape based off the given params
 * @param list = the 2d array that holds the shapes
 * @param row = the row of the character already known to be in the shape
 * @param col = the col of the character already known to be in the shape
 * @return
 * 				= (row, col) of the adjacent character in the current shape
 * 				= null if no adjacent characters in the current shape
 */
	public static Point findNextAdjacent(ArrayList<ArrayList<Character>> list, int row, int col)
	{
		// 8 situations
		if (row >  0 && col > 0)
		{
			if (isAdjacent(list, row, col, row - 1, col - 1) == true 
					&& Character.isLetter(list.get(row - 1).get(col - 1)) == false)
				return new Point(row - 1, col - 1);
		}

		if (col > 0)
		{
			if (isAdjacent(list, row, col, row, col - 1) == true
					&& Character.isLetter(list.get(row).get(col - 1)) == false) 
				return new Point(row, col - 1);
		}

		if (row < list.size() && col > 0)
		{
			if (isAdjacent(list, row, col, row + 1, col - 1) == true
					&& Character.isLetter(list.get(row + 1).get(col - 1)) == false) 
				return new Point(row + 1, col - 1);
		}

		if (row > 0)
		{
			if (isAdjacent(list, row, col, row - 1, col) == true
					&& Character.isLetter(list.get(row - 1).get(col)) == false)
				return new Point(row - 1, col);
		}

		if (row < list.size())
		{
			if (isAdjacent(list, row, col, row + 1, col) == true
					&& Character.isLetter(list.get(row + 1).get(col)) == false)
				return new Point(row + 1, col);
		}

		if (row > 0 && col < list.get(row).size() )
		{
			if (isAdjacent(list, row, col, row - 1, col + 1) == true
					&& Character.isLetter(list.get(row - 1).get(col + 1)) == false)
				return new Point(row - 1, col + 1);
		}

		if (col < list.get(row).size())
		{
			if (isAdjacent(list, row, col, row, col + 1) == true 
					&& Character.isLetter(list.get(row).get(col + 1)) == false)
				return new Point(row, col + 1);
		}

		if (row < list.size() && col < list.get(row).size())
		{
			if (isAdjacent(list, row, col, row + 1, col + 1) == true 
					&& Character.isLetter(list.get(row + 1).get(col + 1)) == false)
				return new Point(row + 1, col + 1);
		}

		return null;
	}


	/**
	 * Change the asterisks of the shape defined in list by the coordinates
	 * (row, col) to a letter  
	 * @param list = the 2d array that holds the contents of the input file
	 * @param row = the x value of the point in the 2d array
	 * @param col = the y value of the point in the 2d array
	 * @param c = the character to change the asterisk to
	 */
	public static void defineShape(ArrayList<ArrayList<Character>> list, int row, int col, char c)
	{
		// recursion to find every adjacent point and then use the letters to 
		// define the shape and when you recurse all the way up choose another letter 
		// and skip to the next place with an asterisk
		
		if (list.get(row).get(col) == '*')
			list.get(row).set(col, c); 
		
		// returns the coordinates of the next adjacent spot
		Point temp = findNextAdjacent(list, row, col);
		
		// no more adjacent spots (base case)
		if (temp == null)
			return;
		
		// (general case)
			defineShape(list, temp.x, temp.y, c);
		
	}


	/**
	 * Generate all 8 permutations of the shape that we have found
	 * @return the arrayList containing all the permutations
	 */
	public static ArrayList<ArrayList<Character>> generatePermutations(ArrayList<ArrayList<Character>> oneShape)
	{
		ArrayList<ArrayList<ArrayList<Character>>> permutations 
		=	new ArrayList<ArrayList<ArrayList<Character>>>();

		//		// get points from the parameter
		//		ArrayList<Point>
		//		
		//		// normal 
		//		permutations.add(oneShape);
		//		
		//		// mirror x
		//		
		//		
		//		// mirror y
		//		
		//		// mirror x and y
		//		
		//		// normal 90
		//		
		//		// mirror x 90
		//		
		//		// mirror y 90
		//		
		//		// mirror x and y 90
		//		
		//		permutation 
		//		permutations.add()

		return null;
	}

	/**
	 * Write to output file
	 * @param decodedShapes = the arrayList that holds what should be 
	 * added to the input file
	 */
	public static void writeToFile(ArrayList<ArrayList<Character>> decodedShapes)
	{
		// write the correct letter to the shapes
		File outFile = new File("output.txt");

		try
		{
			PrintWriter write = new PrintWriter(outFile);

			for (int i = 0; i < decodedShapes.size(); i++)
			{
				for (int j = 0; j < decodedShapes.get(i).size(); j++)
				{
					write.print(decodedShapes.get(i).get(j));
				}
				write.printf("%n"); 
			}

			// save changes
			write.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("output.txt file not found");
			System.exit(0);
		}

	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		ArrayList<ArrayList<Character>> encodedShapes = new ArrayList<ArrayList<Character>>();
		//ArrayList<ArrayList<Character>> decodedShapes = new ArrayList<ArrayList<Character>>();
		char[] alphaPattern = new char[26];
		int letter = 0;

		// populates the char array with the alphabet chars
		for (char ch = 'a'; ch <= 'z'; ++ch)
			alphaPattern[ch - 'a'] = ch;

		// write the file contents to an Array List
		populateArrList(encodedShapes);

		// shape algorithm
		for (int iRow = 0; iRow < encodedShapes.size(); iRow++)
			for (int iCol = 0; iCol < encodedShapes.get(iRow).size(); iCol++)
			{
				if (encodedShapes.get(iRow).get(iCol) == '*')
				{
					// check all chars around this and if they are all
					// spaces and asterisks then use a new character
					// after finishing all the shapes go back and check for
					// rotations and mirror images from the shapes that we now have

					// OPTION 2
					// check all chars of the point you are at and if it is adjacent to 
					// a letter or an asterisk then change the current char to 
					// the letter it should be (either the letter that it is adjacent to 
					// or a new letter) 
					//  then either
					// 1. do this and when you find a non adjacent area mark it with 
					// an x to define the shape and then everything within the x's
					// represent one shape then get rid of the x's and look for a new shape
					// or
					// 2. continue doing what is before the and until you fill out the whole shape
					// and then start back at the end of the first line you were at
					// when you encountered the shape
					//encodedShapes.get(iRow).set(iCol, alphaPattern[0]);
					
					defineShape(encodedShapes, iRow, iCol, alphaPattern[letter]);
					letter++;
				//	encodedShapes.get(iRow).set(iCol, '*');

				}					
			}


		// which letter to use
		//identifyShape(alphaPattern, letter);



		// write to output file
		// at this point encodedShapes is decoded
		writeToFile(encodedShapes);


	}
}



///// write the correct letter to the shapes
//File outFile = new File("output.txt");
//
//try
//{
//	PrintWriter write = new PrintWriter(outFile);
//
//	for (int i = 0; i < encodedShapes.size(); i++)
//	{
//		for (int j = 0; j < encodedShapes.get(i).size(); j++)
//		{
//			write.print(encodedShapes.get(i).get(j));
//		}
//		write.printf("%n"); 
//	}
//	
//	// save changes
//	write.close();
//}
//catch (FileNotFoundException e)
//{
//	System.out.println("output.txt file not found");
//	System.exit(0);
//}


