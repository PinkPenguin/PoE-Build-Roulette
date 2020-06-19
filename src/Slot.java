
import javafx.scene.control.Label;

public class Slot {
	
	private SkillGem gem;
	private Label label = new Label();

	public Slot (SkillGem gem) {
		this.gem = gem;
		this.label.setText(gem.getName()); 
	}
	
	
}
