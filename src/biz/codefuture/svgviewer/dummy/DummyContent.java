package biz.codefuture.svgviewer.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import biz.codefuture.svgviewercore.*;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

	/**
	 * An array of sample (dummy) items.
	 */
	public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

	/**
	 * A map of sample (dummy) items, by ID.
	 */
	public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

	private static void addFeedItems(String feedUrl) {
		int itemCount = 10;
		for (int i = 0; i < itemCount; i +=1) {
			
		}
	}
	
	static {
		// read http://openclipart.org/rss/new.xml and get 
		addFeedItems("http://openclipart.org/rss/new.xml");
		addItem(new DummyItem("1", "http://openclipart.org/people/rejon/rejon_Supergirl.svg"));
		addItem(new DummyItem("2", "http://openclipart.org/people/rejon/rejon_Rejon_s_Head.svg"));
		addItem(new DummyItem("3", "http://openclipart.org/people/Andy/Andy_ant.svg"));
		addItem(new DummyItem("4", "http://openclipart.org/people/Andy/Andy_Tools_Hammer_Spanner.svg"));
		addItem(new DummyItem("5", "http://openclipart.org/people/Andy/Andy_Trash_Can.svg"));
		addItem(new DummyItem("6", "http://openclipart.org/people/rejon/rejon_Ant_Icon.svg"));
		addItem(new DummyItem("7", "http://openclipart.org/people/rejon/rejon_Nicu_s_crash_cchost_file.svg"));
		addItem(new DummyItem("8", "http://openclipart.org/people/rejon/rejon_House_Outlines.svg"));
		
	}

	private static void addItem(DummyItem item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.id, item);
	}

	/**
	 * A dummy item representing a piece of content.
	 */
	public static class DummyItem {
		public String id;
		public String content;

		public DummyItem(String id, String content) {
			this.id = id;
			this.content = content;
		}

		@Override
		public String toString() {
			return content;
		}
	}
}
