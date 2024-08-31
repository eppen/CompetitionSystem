package com.hyr.hubei.polytechnic.university.competition.system.utils;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 
 * @author huangyueran 2016年11月12日0:14:18
 * @category 答案xml格式创建 DOM4J
 * 
 */
public class XMLUtils {

	/**
	 * @category 类型A 选择填空
	 * @param out
	 * @param score
	 * @return
	 */
	public static String createTypeA(String[] out, Integer[] score) {
		Document document = DocumentHelper.createDocument();
		Element question = document.addElement("question");
		Element type = question.addElement("type");
		Element answer = question.addElement("answer");
		// 设置类型
		type.addText("1");

		// 答案
		Element result = answer.addElement("result");

		Element o = result.addElement("o");
		o.setText(out[0]);

		Element s = result.addElement("s");
		s.setText(score[0] + "");

		System.out.println(document.asXML());
		return document.asXML();
	}

	/**
	 * @category 类型B 程序设计
	 * @param in
	 * @param out
	 * @param score
	 * @return
	 */
	public static String createTypeB(String[] in, String[] out, Integer[] score) {
		Document document = DocumentHelper.createDocument();
		Element question = document.addElement("question");
		Element type = question.addElement("type");
		Element answer = question.addElement("answer");
		// 设置类型
		type.addText("2");

		// 答案
		for (int index = 0; index < score.length; index++) {
			Element result = answer.addElement("result");

			Element i = result.addElement("i");
			i.setText(in[index]);

			Element o = result.addElement("o");
			o.setText(out[index]);

			Element s = result.addElement("s");
			s.setText(score[index] + "");
		}

		System.out.println(document.asXML());
		return document.asXML();
	}

	/**
	 * @category 类型C 代码填空
	 * @param in
	 * @param score
	 * @return
	 */
	public static String createTypeC(String[] in, Integer[] score) {
		Document document = DocumentHelper.createDocument();
		Element question = document.addElement("question");
		Element type = question.addElement("type");
		Element answer = question.addElement("answer");
		// 设置类型
		type.addText("3");

		Element result = answer.addElement("result");
		// 循环插入答案
		for (int index = 0; index < in.length; index++) {
			Element i = result.addElement("i");
			i.setText(in[index]);
		}
		// 插入分数
		Element s = result.addElement("s");
		s.setText(score[0] + "");

		System.out.println(document.asXML());
		return document.asXML();
	}
}
