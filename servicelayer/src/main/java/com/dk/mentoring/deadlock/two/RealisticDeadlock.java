package com.dk.mentoring.deadlock.two;

import com.dk.mentoring.deadlock.one.SampleClass;
import com.dk.mentoring.deadlock.one.SampleThread;


/**
 *
 */
public class RealisticDeadlock
{

	/**
	 * @param args
	 */
	public static void main(final String[] args)
	{
		final SampleClass classOne = new SampleClass();
		final SampleClass classTwo = new SampleClass();
		SampleThread threadOne;
		SampleThread threadTwo;
		for (int i = 4; i > 0; i--)
		{
			threadOne = new SampleThread();
			threadOne.setName("ThreadOne" + i);
			threadOne.sampleMethod(classOne, classTwo);
			threadTwo = new SampleThread();
			threadTwo.setName("ThreadTwo" + i);
			threadTwo.sampleMethod(classTwo, classOne);
			threadOne.start();
			threadTwo.start();
		}
		System.out.println("Main thread go out");
	}

}
