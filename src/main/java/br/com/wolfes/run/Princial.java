package br.com.wolfes.run;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlHeading3;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlParagraph;

public class Princial {

	private static final String URL = "https://www.concursosnobrasil.com.br/concursos/pi/";

	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		WebClient client = new WebClient();  
		client.getOptions().setCssEnabled(false);  
		client.getOptions().setJavaScriptEnabled(false);  

		String searchUrl = URL;
		HtmlPage page = client.getPage(searchUrl);

		List<HtmlElement> items = (List<HtmlElement>) page.getByXPath("//*[@id=\"conteudo\"]/table/tbody"); 

		for (DomNode domNode : items.get(0).getChildNodes()) {
			HtmlAnchor itemAnchor =  ((HtmlAnchor) domNode.getChildNodes().get(0).getChildNodes().get(0));

			String itemName = itemAnchor.asText();
			String itemUrl = itemAnchor.getHrefAttribute() ;

			String vagas = domNode.getChildNodes().get(1).getChildNodes().get(0).toString();

			System.out.println(itemName + " / "+ vagas);

			page = client.getPage(itemUrl);

			List<HtmlElement> item = (List<HtmlElement>) page.getByXPath("//*[@id=\"conteudo\"]/article/h1");

			System.out.println(item.get(0).getChildNodes().get(0));

			item = (List<HtmlElement>) page.getByXPath("//*[@id=\"conteudo\"]/article/h2");

			System.out.println(item.get(0).getChildNodes().get(0));

			item = (List<HtmlElement>) page.getByXPath("//*[@id=\"conteudo\"]/article/div[3]");

			for (int i = 0; i < item.get(0).getChildNodes().size(); i++) {
				DomNode domNode2 = item.get(0).getChildNodes().get(i);
				if(domNode2 instanceof HtmlParagraph)
					System.out.println(((HtmlParagraph) domNode2).asText());
				else if(domNode2 instanceof HtmlHeading3) {
					System.out.println(((HtmlHeading3) domNode2).asText());
				}

			}
			System.out.println("\n\n");

		}





	}


}
