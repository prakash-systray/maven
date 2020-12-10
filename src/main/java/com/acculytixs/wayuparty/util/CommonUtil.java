package com.acculytixs.wayuparty.util;

import org.springframework.stereotype.Component;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities.EscapeMode;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Whitelist;

@Component
public class CommonUtil {

	public static String cleanContent(String content) {
		Whitelist allowedTags = Whitelist.simpleText(); 
		   // This whitelist allows only simple text formatting: b, em, i, strong, u. All other HTML (tags and attributes) will be removed.
		   allowedTags.addTags("a", "b", "blockquote", "br", "caption", "cite", "code", "col",
              "colgroup", "dd", "div", "dl", "dt", "em", "h1", "h2", "h3", "h4", "h5", "h6",
              "i", "img", "li", "ol", "p", "pre", "q", "small", "strike", "strong",
              "sub", "sup", "table", "tbody", "td", "tfoot", "th", "thead", "tr", "u",
              "ul","xml","br","font","span");
	        allowedTags.addAttributes("a", "href", "title");
	        allowedTags.addAttributes("blockquote", "cite");
	        allowedTags.addAttributes("col", "span", "width");
	        allowedTags.addAttributes("colgroup", "span", "width");
	        allowedTags.addAttributes("img", "align", "alt", "height", "src", "title", "width","style","class");
	        allowedTags.addAttributes("ol", "start", "type");
	        allowedTags.addAttributes("q", "cite");
	        allowedTags.addAttributes("table", "summary", "width");
	        allowedTags.addAttributes("td", "abbr", "axis", "colspan", "rowspan", "width");
	        allowedTags.addAttributes("th", "abbr", "axis", "colspan", "rowspan", "scope", "width");
	        allowedTags.addAttributes("ul", "type");
	       /* allowedTags.addProtocols("a", "href", "//", "#","ftp", "http", "https", "mailto");*/
	        
	        allowedTags.addProtocols("blockquote", "cite", "http", "https","style");
	        allowedTags.addProtocols("cite", "cite", "http", "https");
	        allowedTags.addProtocols("img", "src", "http", "https");
	        allowedTags.addProtocols("q", "cite", "http", "https");
	        allowedTags.addAttributes(":all","class","style","title");
 		Document dirty = Jsoup.parseBodyFragment(content, "");
 		Cleaner cleaner = new Cleaner(allowedTags);
 		Document clean = cleaner.clean(dirty);
 		clean.outputSettings().escapeMode(EscapeMode.xhtml); // Non fa l'escape dei caratteri utf-8
 		String safe = clean.body().html();
		    return safe;
	}
}
