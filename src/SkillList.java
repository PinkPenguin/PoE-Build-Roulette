
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SkillList {

	// Should probably be a stack in the end, natural pop/push stuff, probably not
	// though
	public static ArrayList<SkillGem> skillList = new ArrayList<>();

	public SkillList() {

	}

	public static SkillGem getRandomSkill() {

		Random random = new Random();
		SkillGem gem = skillList.get(random.nextInt(skillList.size() - 1));

		return gem;
	}

	public static ArrayList<SkillGem> generateRandomGemList(boolean includeVaal, boolean includeItems) {

		ArrayList<SkillGem> gemList = new ArrayList<SkillGem>();

		for (SkillGem gem : skillList) {
			if (!gem.isExcluded()) {
				if (gem.isVaal() && includeVaal) {
					gemList.add(gem);
				}	
				else if (gem.isItem() && includeItems) {
					gemList.add(gem);					
				}
				else if (!gem.isVaal() && !gem.isItem()){
					gemList.add(gem);
				}
			}
		}

		Collections.shuffle(gemList);

		return gemList;
	}

}
