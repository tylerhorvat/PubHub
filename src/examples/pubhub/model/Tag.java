package examples.pubhub.model;

public class Tag {
	
	private String tagName;

	public Tag() {
		tagName = null;
	}
	
	public Tag(String tag) {
		this.tagName = tag;
	}
	
	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

}
