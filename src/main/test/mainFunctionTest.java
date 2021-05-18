package main.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import main.java.mainFunction;

/**
 *
 * @author JGM
 */
class mainFunctionTest {

	@Test
	void testMain() {

		mainFunction.main(null);
		InputStream stdin = System.in;
		try {
			System.setIn(new ByteArrayInputStream("2".getBytes()));
			Scanner scanner = new Scanner(System.in);
			System.out.println(scanner.nextLine());
		} finally {
			System.setIn(stdin);
		}
	}

}
