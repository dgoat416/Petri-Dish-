import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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

				}

				row++;

			}

			// close the scanner
			read.close();
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
	 * Compares two points
	 * @param p = another point object for comparison
	 */
	public static int compareTo(Point a, Point b)
	{
		// TODO Auto-generated method stub
		if (a.x == b.x)
		{
			if (a.y == b.y)
				return 0;
			else if (a.y < b.y)
				return -1;
			else if (a.y > b.y)	
				return 1;
		}
		else if (a.x < b.x )
		{
			return -1;
			//			if (iPoint.y == p.y)
			//				return -1;
			//			else if (iPoint.y < p.y)
			//				return -1;
			//			else if (iPoint.y > p.y)	
			//				return 1;
		}

		else if (a.x > b.x)
			return 1;

		// error
		return -999;
	}

	public static void grabShape(ArrayList<ArrayList<Character>> list,  ArrayList<Point> points, int row, int col, char c)
	{
		// 8 situations
		if (row >  0 && col > 0)
		{
			if (isAdjacent(list, row, col, row - 1, col - 1) == true 
					&& Character.isLetter(list.get(row - 1).get(col - 1)) == false)
			{
				// store the point 
				if (points.isEmpty() == true || compareTo(points.get(0), new Point(row - 1, col - 1)) == - 1 
						||	compareTo(points.get(0), new Point(row - 1, col - 1)) == 0
						|| points.isEmpty() == true)
					points.add(new Point(row - 1, col - 1));

				else if (compareTo(points.get(0), new Point(row - 1, col - 1)) == 1)
					points.add(0, new Point(row -1, col - 1));

				list.get(row - 1).set(col - 1, c); 
				grabShape(list, points, row -1, col - 1, c);
			}
		}

		if (col > 0)
		{			
			if (isAdjacent(list, row, col, row, col - 1) == true
					&& Character.isLetter(list.get(row).get(col - 1)) == false) 
			{
				// store the point 
				if (points.isEmpty() == true || compareTo(points.get(0), new Point(row, col - 1)) == - 1 
						||	compareTo(points.get(0), new Point(row, col - 1)) == 0
						|| points.isEmpty() == true)
					points.add(new Point(row, col - 1));

				else if (compareTo(points.get(0), new Point(row, col - 1)) == 1)
					points.add(0, new Point(row, col - 1));

				list.get(row).set(col - 1, c); 
				grabShape(list, points, row, col - 1, c);
			}
		}

		if (row < list.size() && col > 0)
		{
			if (isAdjacent(list, row, col, row + 1, col - 1) == true
					&& Character.isLetter(list.get(row + 1).get(col - 1)) == false) 
			{
				// store the point 
				if (points.isEmpty() == true || compareTo(points.get(0), new Point(row + 1, col - 1)) == - 1 
						||	compareTo(points.get(0), new Point(row + 1, col - 1)) == 0
						|| points.isEmpty() == true)
					points.add(new Point(row + 1, col - 1));

				else if (compareTo(points.get(0), new Point(row + 1, col - 1)) == 1)
					points.add(0, new Point(row + 1, col - 1));

				list.get(row + 1).set(col - 1, c); 
				grabShape(list, points, row + 1, col - 1, c);
			}
		}

		if (row > 0)
		{
			if (isAdjacent(list, row, col, row - 1, col) == true
					&& Character.isLetter(list.get(row - 1).get(col)) == false)
			{
				// store the point 
				if (points.isEmpty() == true || compareTo(points.get(0), new Point(row - 1, col)) == - 1 
						||	compareTo(points.get(0), new Point(row - 1, col)) == 0
						|| points.isEmpty() == true)
					points.add(new Point(row - 1, col));

				else if (compareTo(points.get(0), new Point(row - 1, col)) == 1)
					points.add(0, new Point(row - 1, col));

				list.get(row - 1).set(col, c); 
				grabShape(list, points, row - 1, col, c);
			}
		}

		if (row < list.size())
		{
			if (isAdjacent(list, row, col, row + 1, col) == true
					&& Character.isLetter(list.get(row + 1).get(col)) == false)
			{
				// store the point 
				if (points.isEmpty() == true || compareTo(points.get(0), new Point(row + 1, col)) == - 1 
						||	compareTo(points.get(0), new Point(row + 1, col)) == 0
						|| points.isEmpty() == true)
					points.add(new Point(row + 1, col));

				else if (compareTo(points.get(0), new Point(row + 1, col)) == 1)
					points.add(0, new Point(row + 1, col));

				list.get(row + 1).set(col, c); 
				grabShape(list, points, row + 1, col, c);
			}
		}

		if (row > 0 && col < list.get(row).size() )
		{
			if (isAdjacent(list, row, col, row - 1, col + 1) == true
					&& Character.isLetter(list.get(row - 1).get(col + 1)) == false)
			{
				// store the point 
				if (points.isEmpty() == true || compareTo(points.get(0), new Point(row - 1, col + 1)) == - 1 
						||	compareTo(points.get(0), new Point(row - 1, col + 1)) == 0
						|| points.isEmpty() == true)
					points.add(new Point(row - 1, col + 1));

				else if (compareTo(points.get(0), new Point(row - 1, col + 1)) == 1)
					points.add(0, new Point(row - 1, col + 1));

				list.get(row - 1).set(col + 1, c); 
				grabShape(list, points, row - 1, col + 1, c);
			}
		}

		if (col < list.get(row).size())
		{
			if (isAdjacent(list, row, col, row, col + 1) == true 
					&& Character.isLetter(list.get(row).get(col + 1)) == false)
			{				
				// store the point 
				if (points.isEmpty() == true || compareTo(points.get(0), new Point(row, col + 1)) == - 1 
						||	compareTo(points.get(0), new Point(row, col + 1)) == 0
						|| points.isEmpty() == true)
					points.add(new Point(row, col + 1));

				else if (compareTo(points.get(0), new Point(row, col + 1)) == 1)
					points.add(0, new Point(row, col + 1));

				list.get(row).set(col + 1, c); 
				grabShape(list, points, row, col + 1, c);
			}
		}

		if (row < list.size() && col < list.get(row).size())
		{
			if (isAdjacent(list, row, col, row + 1, col + 1) == true 
					&& Character.isLetter(list.get(row + 1).get(col + 1)) == false)
			{
				// store the point 
				if (points.isEmpty() == true || compareTo(points.get(0), new Point(row + 1, col + 1)) == - 1 
						||	compareTo(points.get(0), new Point(row + 1, col + 1)) == 0
						|| points.isEmpty() == true)
					points.add(new Point(row + 1, col + 1));

				else if (compareTo(points.get(0), new Point(row + 1, col + 1)) == 1)
					points.add(0, new Point(row + 1, col + 1));

				list.get(row + 1).set(col + 1, c); 
				grabShape(list, points, row + 1, col + 1, c);
			}
		}

	}

/**
 * Creates a matrix from the points given as parameters
 * @param points
 * 						= an arrayList of points 
 * @return
 * 				= a 2d arrayList representing the points 
 */
	public static ArrayList<ArrayList<Character>> createMatrix(final ArrayList<Point> points)
	{
		int originX = points.get(0).x;
		int originY = points.get(0).y;
		int currX = 0;
		int currY = 0;

		ArrayList<ArrayList<Character>> newMatrix = new ArrayList<ArrayList<Character>>();

		for (int j = 0; j < points.size(); j++)
		{
			currX = Math.abs(points.get(j).x - originX);
			currY = Math.abs(points.get(j).y - originY);

			// add to matrix 
			while (newMatrix.size() - 1 < currX)
			{
				// need more rows so make more rows
				newMatrix.add(new ArrayList<Character>());
			}

			if (newMatrix.get(currX).size() - 1 < currY)
			{
				Character[] col = new Character[currY - newMatrix.get(currX).size()];
				Arrays.fill(col, ' ');
				newMatrix.get(currX).addAll(Arrays.asList(col));
			}

			// set the value of the point as an asterisk
			newMatrix.get(currX).add(currY, '*');
		}

		writeToConsole(newMatrix);
		System.out.printf("%n%n");

		return newMatrix;
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

		if (row + 1 < list.size() && col > 0)
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

		if (row + 1 < list.size())
		{
			if (row2 == row + 1 && col2 == col)
				if (list.get(row + 1).get(col) == '*')
					return true;
		}

		if (row > 0 && col + 1 < list.get(row).size() )
		{
			if (row2 == row - 1 && col2 == col + 1)
				if (list.get(row - 1).get(col + 1) == '*')
					return true;
		}

		if (col + 1 < list.get(row).size())
		{
			if (row2 == row && col2 == col + 1)
				if (list.get(row).get(col + 1) == '*')
					return true;
		}

		if (row + 1 < list.size() && col + 1 < list.get(row).size())
		{
			if (row2 == row + 1 && col2 == col + 1)
				if (list.get(row + 1).get(col + 1) == '*')
					return true;
		}

		return false;
	}

	/**
	 * Find all adjacent characters in the current shape based off the given params
	 * and redefine the shape as the parameter c instead of the asterisk
	 * @param list = the 2d array that holds the shapes
	 * @param row = the row of the character already known to be in the shape
	 * @param col = the col of the character already known to be in the shape
	 * @param c = the character we will be replacing the asterisks of adjacent 
	 * 						shapes with
	 */
	public static void findAllAdjacent(ArrayList<ArrayList<Character>> list, int row, int col, char c)
	{

		// 8 situations
		if (row >  0 && col > 0)
		{
			if (isAdjacent(list, row, col, row - 1, col - 1) == true 
					&& Character.isLetter(list.get(row - 1).get(col - 1)) == false)
			{
				list.get(row - 1).set(col - 1, c); 
				findAllAdjacent(list, row -1, col - 1, c);
			}
		}

		if (col > 0)
		{
			if (isAdjacent(list, row, col, row, col - 1) == true
					&& Character.isLetter(list.get(row).get(col - 1)) == false) 
			{
				list.get(row).set(col - 1, c); 
				findAllAdjacent(list, row, col - 1, c);
			}
		}

		if (row < list.size() && col > 0)
		{
			if (isAdjacent(list, row, col, row + 1, col - 1) == true
					&& Character.isLetter(list.get(row + 1).get(col - 1)) == false) 
			{
				list.get(row + 1).set(col - 1, c); 
				findAllAdjacent(list, row + 1, col - 1, c);
			}
		}

		if (row > 0)
		{
			if (isAdjacent(list, row, col, row - 1, col) == true
					&& Character.isLetter(list.get(row - 1).get(col)) == false)
			{
				list.get(row - 1).set(col, c); 
				findAllAdjacent(list, row - 1, col, c);
			}
		}

		if (row < list.size())
		{
			if (isAdjacent(list, row, col, row + 1, col) == true
					&& Character.isLetter(list.get(row + 1).get(col)) == false)
			{
				list.get(row + 1).set(col, c); 
				findAllAdjacent(list, row + 1, col, c);
			}
		}

		if (row > 0 && col < list.get(row).size() )
		{
			if (isAdjacent(list, row, col, row - 1, col + 1) == true
					&& Character.isLetter(list.get(row - 1).get(col + 1)) == false)
			{
				list.get(row - 1).set(col + 1, c); 
				findAllAdjacent(list, row - 1, col + 1, c);
			}
		}

		if (col < list.get(row).size())
		{
			if (isAdjacent(list, row, col, row, col + 1) == true 
					&& Character.isLetter(list.get(row).get(col + 1)) == false)
			{
				list.get(row).set(col + 1, c); 
				findAllAdjacent(list, row, col + 1, c);
			}
		}

		if (row < list.size() && col < list.get(row).size())
		{
			if (isAdjacent(list, row, col, row + 1, col + 1) == true 
					&& Character.isLetter(list.get(row + 1).get(col + 1)) == false)
			{
				list.get(row + 1).set(col + 1, c); 
				findAllAdjacent(list, row + 1, col + 1, c);
			}
		}
	}

	/**
	 * Rotate the shape (parameter) 90 degrees 
	 * @param oneShape
	 * 								= the 2D array representing a shape to be rotated 
	 * @return
	 * 				= the 90 degree rotation of oneShape
	 */
	public static ArrayList<ArrayList<Character>> rotate90Degrees(ArrayList<ArrayList<Character>> oneShape)
	{
		// rows of new matrix = columns of original matrix
		int rowsOfNewMatrix = oneShape.get(0).size(); 
		// cols of new matrix = rows of original matrix
		int colsOfNewMatrix = oneShape.size();  		   

		ArrayList<ArrayList<Character>> rotatedMatrix = new ArrayList<ArrayList<Character>>();


		for (int i = 0; i < colsOfNewMatrix; i++)
		{
			for(int j = 0; j < rowsOfNewMatrix; j++)
			{
				if (i == 0)
					rotatedMatrix.add(new ArrayList<Character>(Arrays.asList(new Character[colsOfNewMatrix])));
				// rotatedMatrix.get(j).add();
				char c = oneShape.get(i).get(j);
				rotatedMatrix.get(j).set(colsOfNewMatrix - (i + 1), c);
			}
		}


		return rotatedMatrix;
	}

	/**
	 * Generate all 8 permutations of the shape that we have found
	 * @param oneShape = the 2d array of one shape from the text file
	 * @return the arrayList containing all the permutations
	 */
	public static ArrayList<ArrayList<Character>> generatePermutations(ArrayList<ArrayList<Character>> oneShape)
	{
		ArrayList<ArrayList<ArrayList<Character>>> permutations 
		=	new ArrayList<ArrayList<ArrayList<Character>>>();

		ArrayList<ArrayList<Character>> temp = new ArrayList<ArrayList<Character>>();

		// normal 
		permutations.add(oneShape);
		// add 90 degree rotation of original
		permutations.add(rotate90Degrees(oneShape));

		// reset temp
		temp.clear();

		int imageRow = -1;
		// mirror x
		for (int row = oneShape.size() - 1; row >= 0; row--) 
		{
			// initialize row for image array
			imageRow++;
			temp.add(new ArrayList<Character>());

			for (int column = 0; column < oneShape.get(row).size(); column++)
			{
				// get element at current row and column
				char element = oneShape.get(row).get(column);

				// assign element to the image array
				temp.get(imageRow).add(column, element);

			}
		}

		// add mirror x and reset temp
		permutations.add((ArrayList<ArrayList<Character>>) temp.clone());

		// add 90 degree rotation of mirror x
		permutations.add(rotate90Degrees(temp));
		temp.clear();

		// mirror y
		for (int row = 0; row < oneShape.size(); row++) 
		{
			// initialize column for image array
			int imageColumn = 0;
			temp.add(new ArrayList<Character>());

			for (int column = oneShape.get(row).size() - 1; column >= 0; column--)
			{
				// get element at current row and column
				char element = oneShape.get(row).get(column);

				// assign element to the image array
				temp.get(row).add(imageColumn, element);

				// increment the image array column counter
				imageColumn++;
			}
		}

		// add mirror y and reset temp
		permutations.add((ArrayList<ArrayList<Character>>) temp.clone());

		// add 90 degree rotation of mirror y
		permutations.add(rotate90Degrees(temp));
		temp.clear();


		// mirror x and y
		int imageRow2 = -1;
		for (int row = oneShape.size() - 1; row >= 0; row--) 
		{
			// initialize column for image array
			int imageColumn = 0;
			imageRow2++;

			temp.add(new ArrayList<Character>());

			for (int column = oneShape.get(row).size() - 1; column >= 0; column--)
			{
				// get element at current row and column
				char element = oneShape.get(row).get(column);

				// assign element to the image array
				temp.get(imageRow2).add(imageColumn, element);

				// increment the image array column counter
				imageColumn++;
			}
		}

		// add mirror x,y and reset temp
		permutations.add((ArrayList<ArrayList<Character>>) temp.clone());

		// add 90 degree rotation of mirror x,y
		permutations.add(rotate90Degrees(temp));
		temp.clear();

		// test
		for (int i = 0; i < permutations.size(); i++)
		{
			writeToConsole(permutations.get(i));
			System.out.printf("%n%n"); 
		}

		return temp;
	}


	public boolean isSameShape(ArrayList<ArrayList<Character>> oneShape)
	{
		return false;
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

	// TEST METHOD
	public static void writeToConsole(ArrayList<ArrayList<Character>> decodedShapes)
	{
		// write the correct letter to the shapes


		for (int i = 0; i < decodedShapes.size(); i++)
		{
			for (int j = 0; j < decodedShapes.get(i).size(); j++)
			{
				System.out.print(decodedShapes.get(i).get(j));
			}
			System.out.printf("%n"); 
		}

	}

	/**
	 * Main method to run the program
	 * @param args = necessary arguments to run the program
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		ArrayList<ArrayList<Character>> encodedShapes = new ArrayList<ArrayList<Character>>();
		char[] alphaPattern = new char[26];
		int letter = 0;
		ArrayList<Point> points = new ArrayList<Point>();
		ArrayList<ArrayList<Character>> decodedShapeList = new ArrayList<ArrayList<Character>>();

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
					//findAllAdjacent(encodedShapes, iRow, iCol, alphaPattern[letter]);
					// beginning of testing grabShape does same as findAllAdjacent except it 
					// saves the points of the shapes
					grabShape(encodedShapes, points, iRow, iCol, alphaPattern[letter]);
					decodedShapeList.addAll(createMatrix(points));
					// update letter for shape and reset the adjacent points
					letter++;

					// reset the letter to 'a' because this is before we
					// check for the permutations of the shapes
					if (letter > 25)
						letter = 0;

					// reset points
					points.clear();
				}					
			}

		//		ArrayList<ArrayList<Character>> oneShape = new ArrayList<ArrayList<Character>>();
		//
		//
		//		for (int i = 0; i < 3; i++)
		//			oneShape.add(encodedShapes.get(i));
		//
		//		generatePermutations(oneShape);

		// write to output file
		// at this point encodedShapes is decoded
		writeToFile(encodedShapes);


	}
}

