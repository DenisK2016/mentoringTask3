package com.dk.mentoring.deadlock.one;

/**
 *
 */
public class SimpleDeadlock
{

	/**
	 * @param args
	 */
	public static void main(final String[] args)
	{
		final SampleClass classOne = new SampleClass();
		final SampleClass classTwo = new SampleClass();
		final SampleThread threadOne = new SampleThread();
		threadOne.setName("ThreadOne");
		threadOne.sampleMethod(classOne, classTwo);
		
		final SampleThread threadTwo = new SampleThread();
		threadTwo.setName("ThreadTwo");
		threadTwo.sampleMethod(classTwo, classOne);
		threadOne.start();
		threadTwo.start();
		System.out.println("Main thread go out");
	}

}
