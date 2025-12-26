import java.io.File;                  // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner;             // Import the Scanner class to read text files
import java.util.regex.Pattern;
import java.util.ArrayList; // Import the ArrayList class


class Interpreter {
	/** Brainfuck Commands
	> | Move the pointer to the right
	< | Move the pointer to the left
	+ | Increment the memory cell at the pointer
	- | Decrement the memory cell at the pointer
	. | Output the character signified by the cell at the pointer
	, | Input a character and store it in the cell at the pointer
	[ | Jump past the matching ']' if the cell at the pointer is 0
	] | Jump back to the matching '[' if the cell at the pointer is nonzero
	*/ 

	public static void main(String[] args)
	{
		ArrayList<Byte> memory = new ArrayList<Byte>();
		for (int i = 0; i < 30000; i++){
			byte myByte = 0;
			memory.add(myByte);
		}
		int pointer = 0;
		int recursion_level = 0;
		int current_character = 0;
		int line_count = 0;
		File myFile = new File(args[0]);

		try(Scanner myReader = new Scanner(myFile)) {
			myReader.skip(Pattern.compile("[\\>\\<\\+\\-\\.\\,\\[\\]]+"));
			int char_count = 0;
			while (myReader.hasNextLine()) {
				String line = myReader.nextLine();
				char_count = 0;
				line_count++;
				for(char c: line.toCharArray()){
				// Do Stuff
					byte current = memory.get(pointer);
					char_count++;
					switch (c) {
						case '>': 
							System.out.print(">");
							pointer++;
						case '<': 
							System.out.print("<");
							if(pointer > 1){
								pointer--;
							}
						case '+':
							System.out.print("+");
							current = memory.get(pointer);
							memory.set(pointer, current++);
						case '-':
							System.out.print("-");
							current = memory.get(pointer);
							memory.set(pointer, current--);
						case '.':
							System.out.print(".");
							System.out.print(Byte.toString(current));
						case ',':
							System.out.print(",");
							
						case '[':
							System.out.print("[");
						case ']':
							System.out.print("]");
					}
				}
			}
		} catch (FileNotFoundException e){
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
	}
}

