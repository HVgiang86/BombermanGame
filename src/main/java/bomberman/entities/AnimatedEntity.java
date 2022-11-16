package bomberman.entities;

public abstract class AnimatedEntity extends Entity {
	protected int animate = 0; // luu lai hieu ung cua thuc the
	protected final int MAX_Animate = 7500;

	protected void animate() {
		if (animate < MAX_Animate)
			animate++;
		else
			animate = 0;
	}

}
