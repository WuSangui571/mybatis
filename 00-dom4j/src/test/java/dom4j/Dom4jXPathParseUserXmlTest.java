package dom4j;


import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.util.List;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-16
 * @Description: dom4j 结合 XPath 解析 user.xml
 * @Version: 1.0
 */
public class Dom4jXPathParseUserXmlTest {
    @Test
    public void test() throws Exception {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(Dom4jXPathParseUserXmlTest.class.getClassLoader().getResource("user.xml"));
        Element passwordElement = (Element)document.selectSingleNode("/users/user/password");
        System.out.println("第一个user的password的值（绝对路径）：" + passwordElement.getText());

        Element salaryElement = (Element)passwordElement.selectSingleNode("../salary");
        System.out.println("第一个user的salary的值（相对路径）：" + salaryElement.getText());

        List<Node> idElements = document.selectNodes("//id");
        for (Node idElement : idElements) {
            Element i = (Element) idElement;
            System.out.println("<UNK>id<UNK> = " + i.getText());
        }

        Element node = (Element)document.selectSingleNode("//user[@id='U10001']");
        List<Element> elements = node.elements();

        for (Element element : elements) {
            System.out.println("<UNK>" + element.getName());
            System.out.println("<UNK>" + element.getText());
        }
    }
}
