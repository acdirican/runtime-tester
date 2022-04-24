package analysis.acdirican.runtime;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 
 * @author Ahmet Cengizhan Dirican
 * @version 1.0
 *
 */

public class RuntimeTester {

	private static boolean DEBUG = false;
	private static int NUMBER_OF_TESTS = 1;
	private static PrintStream OUTPUT = System.out;
	
	public static double measure(Runnable algorithm) {
				
		if(DEBUG) {
			OUTPUT.println("* Tests has been started.");
		}
	
		double elepsedTime = 0;

		for (int j = 0; j < NUMBER_OF_TESTS; j++) {
			elepsedTime += test(algorithm);
		}
		
		if(DEBUG) {
			OUTPUT.println("Test result:" + elepsedTime + "ms");
		}
		
		return elepsedTime/NUMBER_OF_TESTS;
	}

	private static double test(Runnable algorithm) {
		long startTime = System.nanoTime();

		algorithm.run();

		long finishTime = System.nanoTime();

		return (finishTime - startTime) / 1000000.0;
	}

	public static double[] measureEach(Runnable algorithm) {
		
		if(DEBUG) {
			OUTPUT.println("* Tests heve been started.");
		}
	
		double[] results =  new double[NUMBER_OF_TESTS + 1];
		
		double elepsedTime = 0;

		// Test for Selection sort
		for (int j = 0; j < NUMBER_OF_TESTS; j++) {
			results[j] += test(algorithm);
			if(DEBUG) {
				OUTPUT.println("Test#" + j + ":" + results[j] + "ms");
			}
		}

		
		results[NUMBER_OF_TESTS] = Arrays.stream(results).average().getAsDouble();
		
		if(DEBUG) {
			OUTPUT.println("Test Result:" + results[NUMBER_OF_TESTS] + "ms");
		}
		
		return results;
	}
	
	public static void debug(boolean value) {
		DEBUG = value;		
	}
	
	public static void setNumberOfTests(int number) {
		NUMBER_OF_TESTS = number;
	}
	
	public static void setPrinter(PrintStream output) {
		OUTPUT = output;
	}

	public static <T> double measure(Consumer<T> algorithm, T value) {
		return measure(()-> algorithm.accept(value));
	}
	
	public static <T> double[] measureEach(Consumer<T> algorithm, T value) {
		return measureEach(()-> algorithm.accept(value));
	}
	
	public static <T,R> double measure(Function<T, R> algorithm, T value) {
		return measure(()-> algorithm.apply(value) );
	}
	
	public static <T,R> double[] measureEach(Function<T, R> algorithm, T value) {
		return measureEach(()-> algorithm.apply(value));
	}
	

}
