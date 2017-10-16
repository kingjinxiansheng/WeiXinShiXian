package com.example.lenovo.weixinshixian;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import android.text.TextUtils;

/**
 * ������ת��Ϊƴ��
 * 
 * @author Administrator
 * 
 */
public class PinYinUtils {

	// �� ��
	public static String getPinYin(String text) {
		if (TextUtils.isEmpty(text))
			return null;
		String pinYin = "";
		HanyuPinyinOutputFormat fomart = new HanyuPinyinOutputFormat();
		fomart.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		fomart.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

		// 1.PinYin4jͬһʱ��ֻ��ת��һ�����ֻ����ַ�
		char[] charArr = text.trim().toCharArray();
		for (int i = 0; i < charArr.length; i++) {

			// 2.�������ַ��ǿո� �ͼ�����һ��ѭ��
			if (Character.isWhitespace(charArr[i]))
				continue;

			// 3.�жϸ��ַ��Ƿ��Ǻ���= һ��char�ַ�1���ֽ�(-127----127)
			if (charArr[i] > 127) {

				try {
					String[] strArr = PinyinHelper.toHanyuPinyinStringArray(
							charArr[i], fomart);
 
					 
					if (strArr!=null&&strArr.length>0&&strArr[0] != null) {

						pinYin += strArr[0];
					}

				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}

			} else {
				// ��ǰ�ַ������Ǻ��ֻ�����ĸ֮��������ַ�
				pinYin += charArr[i];
			}

		}

		return pinYin;
	}
}
