
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Run {

	public static void main(String[] args) {

		File file = new File("src/resources/skillgems.txt");
		try {
			Scanner scan = new Scanner(file);
			
			while (scan.hasNextLine()) {
				SkillGem gem = new SkillGem();
				
				/* Put together the name of the skill */
				String name = new String();
				while(!scan.hasNextInt()) {
					name = name + " " + scan.next();
				}
				gem.setName(name);
				
				/* Get excluded status */
				// TODO:Could also set this value in the constructor in the SkillGem class by default
				gem.setExcluded(false);
				// TODO:Could be cleaner to change this to boolean rather than int
				if(scan.nextInt() == 1) gem.setExcluded(true);
				
				/* See if skill is the Vaal version or not */
				// TODO:Same as above
				gem.setVaal(false);
				if(scan.nextInt() == 1) gem.setVaal(true);
				
				gem.setItem(false);
				if(scan.nextInt() == 1) gem.setItem(true);
				
				SkillList.skillList.add(gem);
			}
			
			scan.close();
			
			GUI gui = new GUI(args);
			
		} catch (FileNotFoundException e) {
			System.out.println("skillgems file not found!");
		}
	}
}
