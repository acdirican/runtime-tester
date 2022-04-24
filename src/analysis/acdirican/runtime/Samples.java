package analysis.acdirican.runtime;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Iterator;

/**
 * 
 * @author Ahmet Cengizhan Dirican
 * @version 1.0
 *
 */

public class Samples {

	
	public static void main(String[] args) {
		RuntimeTester.debug(true);
		RuntimeTester.setNumberOfTests(10);
		RuntimeTester.measureEach(() -> {
			double s = 0;
			for (double i = 0; i < 10000000; i++) {
				s += i;
			}
		});
		
		RuntimeTester.setNumberOfTests(1000000);
		RuntimeTester.measure((value) -> {
			long result=value * value;
		},100000);
		
		
		RuntimeTester.setNumberOfTests(1000000);
		RuntimeTester.measure(Math::sqrt, Math.PI);
		
		try {
			RuntimeTester.setPrinter(new PrintStream(new File("output")));
		} catch (FileNotFoundException e) {
			System.err.println("File write error!");
			return;
		}
		RuntimeTester.setNumberOfTests(10);
		RuntimeTester.measureEach(() -> {
			double s = 0;
			for (double i = 0; i < 10000000; i++) {
				s += i;
			}
		});
		
	
	}
}
