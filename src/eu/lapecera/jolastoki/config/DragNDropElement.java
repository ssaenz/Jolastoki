package eu.lapecera.jolastoki.config;

public class DragNDropElement {

	private int viewId;
	private int targetId;
	private int okBackground;
	private int koBackground;
	private int normalBackground;
	
	public DragNDropElement (int viewId, int targetId, int okBackground, int koBackground, int normalBackground) {
		this.viewId = viewId;
		this.targetId = targetId;
		this.okBackground = okBackground;
		this.koBackground = koBackground;
		this.normalBackground = normalBackground;
	}
	
	public int getViewId() {
		return this.viewId;
	}
	
	public int getTargetId() {
		return this.targetId;
	}

	public int getOkBackground() {
		return this.okBackground;
	}
	
	public int getKoBackground() {
		return this.koBackground;
	}
	
	public int getNormalBackgournd () {
		return this.normalBackground;
	}

}
