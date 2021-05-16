package main;

import java.util.Scanner;

/**
*
* @author JGM
*/
public class mainFunction {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		boolean flag = true;
		int iterator = 0;
		while (flag || iterator > Constants.MAX_ITERATIONS) {
			Scanner in = new Scanner(System.in);
			System.out.println(Constants.INI_MENU_HEAD);
			System.out.println(Constants.INI_MENU_OPT_1);
			System.out.println(Constants.INI_MENU_OPT_2);
			try {
				Integer num = in.nextInt();
				if (null != num
						&& (num.equals(Constants.OPTION_ASK_QUESTION) || num.equals(Constants.OPTION_NEW_QUESTION))) {
					funcionality(num);
					flag = false;
				} else {
					iterator++;
					System.out.println(Constants.ERROR_MENU_OPTION);
				}
			} catch (Exception e) {
				iterator++;
				System.out.println(Constants.ERROR_MENU_OPTION);
			}
		}
		if (iterator > Constants.MAX_ITERATIONS) {
			System.out.println(Constants.ERROR_ITERATIONS_EXCEDED);
		}
		System.out.println(Constants.END);
	}

	private static void funcionality(Integer num) {
		System.out.println("functionality");

	}

}
