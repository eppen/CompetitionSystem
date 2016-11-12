package com.hyr.hubei.polytechnic.university.competition.system.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class XML
{
	private static List<String> inputList; // 输入
	private static List<String> ouputList; // 输出
	private static List<Integer> scoreList; // 分数

	static
	{
		inputList = new ArrayList<String>();
		// inputList.add("")
		ouputList = new ArrayList<String>();
		scoreList = new ArrayList<Integer>();
		// TYPE A
		// ouputList.add("5050");
		// scoreList.add(100);
		// TYPE B
		inputList.add("2");
		ouputList.add("5");
		scoreList.add(20);
		inputList.add("5");
		ouputList.add("8");
		scoreList.add(20);
		inputList.add("100");
		ouputList.add("534");
		scoreList.add(20);
		inputList.add("24");
		ouputList.add("56");
		scoreList.add(20);
		inputList.add("23");
		ouputList.add("88");
		scoreList.add(20);
		// 填充数据
	}

	public static void createTypeA(List<String> out, List<Integer> score)
	{
		Document document = DocumentHelper.createDocument();
		Element question = document.addElement("question");
		Element type = question.addElement("type");
		Element answer = question.addElement("answer");
		// 设置类型
		type.addText("1");

		// 答案
		Element result = answer.addElement("result");

		Element o = result.addElement("o");
		o.setText(out.get(0));

		Element s = result.addElement("s");
		s.setText(score.get(0) + "");

		System.out.println(document.asXML());

	}

	public static void createTypeB(List<String> in, List<String> out,
			List<Integer> score)
	{
		Document document = DocumentHelper.createDocument();
		Element question = document.addElement("question");
		Element type = question.addElement("type");
		Element answer = question.addElement("answer");
		// 设置类型
		type.addText("2");

		// 答案

		for (int index = 0; index < score.size(); index++)
		{
			Element result = answer.addElement("result");

			Element i = result.addElement("i");
			i.setText(in.get(index));

			Element o = result.addElement("o");
			o.setText(out.get(index));

			Element s = result.addElement("s");
			s.setText(score.get(index) + "");
		}

		System.out.println(document.asXML());
	}

	public static void createTypeC(List<String> in, List<Integer> score)
	{
		Document document = DocumentHelper.createDocument();
		Element question = document.addElement("question");
		Element type = question.addElement("type");
		Element answer = question.addElement("answer");
		// 设置类型
		type.addText("3");

		Element result = answer.addElement("result");
		// 循环插入答案
		for (int index = 0; index < in.size(); index++)
		{
			Element i = result.addElement("i");
			i.setText(in.get(index));
		}
		// 插入分数
		Element s = result.addElement("s");
		s.setText(score.get(0) + "");

		System.out.println(document.asXML());

	}

	public static void main(String[] args) throws IOException
	{
		// createTypeA(ouputList, scoreList);
//		createTypeB(inputList, ouputList, scoreList);
		createTypeC(inputList, scoreList);
	}

	protected void flag() throws IOException
	{
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("root");
		Element element1 = root.addElement("hyr").addAttribute("h", "h")
				.addAttribute("y", "y").addAttribute("r", "r")
				.addAttribute("我爱你", "520").addText("卫生纸啊").addElement("鼠标垫");
		element1.addElement("ul").addElement("li").addElement("li")
				.addElement("li");

		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter xmlWriter = new XMLWriter(new FileWriter("HYR.create.xml"),
				format);
		// 生成的xml字符串
		System.out.println(document.asXML());
		xmlWriter.write(document);
		xmlWriter.flush();
		xmlWriter.close();
	}
}
