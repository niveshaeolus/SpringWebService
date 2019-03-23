package hello;

import javax.xml.bind.annotation.XmlRootElement;

//@JsonIgnoreProperties(ignoreUnknown=true)
@XmlRootElement
public class ChecMultipleMethods {
	public String testString;
	public Content content;
	public class Content{
		public String status;
	}
}
