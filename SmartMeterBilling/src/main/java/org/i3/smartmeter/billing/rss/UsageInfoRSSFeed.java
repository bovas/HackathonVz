package org.i3.smartmeter.billing.rss;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.i3.smartmeter.billing.domain.UsageLineDO;
import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Content;
import com.sun.syndication.feed.rss.Item;

public class UsageInfoRSSFeed extends AbstractRssFeedView {

	@Override
	protected List<Item> buildFeedItems(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<UsageLineDO> listContent = (List<UsageLineDO>) model.get("feedContent");
		List<Item> items = new ArrayList<Item>(listContent.size());
 
		for(UsageLineDO tempContent : listContent ){
 
			Item item = new Item();
 
			String contentValue="Smart Meter Id : " + tempContent.getSmartMeterID() + "\n"  +
								"Time Duration : " + tempContent.getStartTime() + " - " +tempContent.getEndTime() 
								+"\n Reading Start : " + tempContent.getReadingStart() +" \n" 
								+"]n Reading End : "+tempContent.getReadingEnd();
					
			Content content = new Content();
			content.setType(Content.HTML);
			content.setValue(contentValue);
			item.setContent(content);
 
			item.setTitle("Usage Line Information");
			item.setLink("http://www.tneb.com/billing/consumer/Index.jsp");
			item.setPubDate(new Date());
 
			items.add(item);
		}
 
		return items;
	}

	@Override
	protected void buildFeedMetadata(Map<String, Object> model, Channel feed,
			HttpServletRequest request) {
		feed.setTitle("Smart Meter - Usage Information");
		feed.setDescription("");
		feed.setLink("");
		super.buildFeedMetadata(model, feed, request);
	}
}
