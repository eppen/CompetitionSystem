package com.hyr.hubei.polytechnic.university.competition.system.utils;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import com.hyr.hubei.polytechnic.university.competition.system.domain.ResultB;

/**
 * 
 * @author hongliang.dinghl Dom4j 生成XML文档与解析XML文档
 */
public class ResolveXMLUtils {

	/**
	 * 解析xml文件 传入文件名
	 * 
	 * @param fileName
	 *            文件名
	 */
	public void parserXml(String fileName) {
		File inputXml = new File(fileName);
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(inputXml);
			Element employees = document.getRootElement();

			Iterator<Element> eleit = employees.elementIterator("type");

			while (eleit.hasNext()) {
				Element t1 = eleit.next();
				System.out.println(t1.getName() + ":" + t1.getStringValue());
			}

			for (Iterator i = employees.elementIterator(); i.hasNext();) {
				Element employee = (Element) i.next();
				for (Iterator j = employee.elementIterator(); j.hasNext();) {
					Element node = (Element) j.next();
					System.out.println(node.getName() + ":" + node.getStringValue());
					for (Iterator k = node.elementIterator(); k.hasNext();) {
						Element result = (Element) k.next();
						System.out.println(result.getName() + ":" + result.getStringValue());
					}
				}
			}

		} catch (DocumentException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 类型1的解析 结果填空
	 * 
	 * @param fileName
	 */
	public void parserXmlA(String fileName) {
		File inputXml = new File(fileName);
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(inputXml);
			Element employees = document.getRootElement();

			Iterator<Element> eleit = employees.elementIterator("type");

			while (eleit.hasNext()) {
				Element t1 = eleit.next();
				System.out.println(t1.getName() + ":" + t1.getStringValue());
			}

		} catch (DocumentException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 类型2的解析 程序设计
	 * 
	 * @param fileName
	 * @return
	 */
	public List<ResultB> parserXmlB(String xmlDoc) {
		StringReader read = new StringReader(xmlDoc);
		// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
		InputSource source = new InputSource(read);
		SAXReader saxReader = new SAXReader();

		// File inputXml = new File(fileName);
		// SAXReader saxReader = new SAXReader();

		List<ResultB> resultBList = new ArrayList<ResultB>(); // 返回的结果
		try {
			// Document document = saxReader.read(inputXml);
			Document document = saxReader.read(source);
			Element employees = document.getRootElement();

			Iterator<Element> answers = employees.elementIterator("answer");

			// 遍历answer结点
			while (answers.hasNext()) {
				Element answer = answers.next();
				// 遍历result结点
				Iterator<Element> results = answer.elementIterator("result");
				while (results.hasNext()) {
					Element result = results.next();
					Iterator<Element> i = result.elementIterator("i");
					Iterator<Element> o = result.elementIterator("o");
					Iterator<Element> s = result.elementIterator("s");
					Element ii = i.next();
					Element oo = o.next();
					Element ss = s.next();
					ResultB resultB = new ResultB();
					resultB.setI(ii.getStringValue());
					resultB.setO(oo.getStringValue());
					resultB.setS(Integer.parseInt(ss.getStringValue()));
					resultBList.add(resultB);
				}
			}

		} catch (DocumentException e) {
			System.out.println(e.getMessage());
		}
		return resultBList;
	}

	/**
	 * 类型3的解析 代码填空
	 * 
	 * @param fileName
	 */
	public void parserXmlC(String fileName) {
		File inputXml = new File(fileName);
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(inputXml);
			Element employees = document.getRootElement();

			Iterator<Element> eleit = employees.elementIterator("type");

			while (eleit.hasNext()) {
				Element t1 = eleit.next();
				System.out.println(t1.getName() + ":" + t1.getStringValue());
			}

		} catch (DocumentException e) {
			System.out.println(e.getMessage());
		}
	}

}