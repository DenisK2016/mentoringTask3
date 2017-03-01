package com.dk.mentoring.deadlock.three;


/**
 *
 */
public class Deadlock4Resources
{

	/**
	 * @param args
	 */
	public static void main(final String[] args)
	{
		final SampleDeadlockClass classOne = new SampleDeadlockClass();
		classOne.setNameClass("classOne");
		final SampleDeadlockClass classTwo = new SampleDeadlockClass();
		classTwo.setNameClass("classTwo");
		final SampleDeadlockClass classThree = new SampleDeadlockClass();
		classThree.setNameClass("classThree");
		final SampleDeadlockClass classFour = new SampleDeadlockClass();
		classFour.setNameClass("classFour");

		final SampleDeadlockThread threadOne = new SampleDeadlockThread();
		threadOne.setName("ThreadOne");
		threadOne.sampleMethod(classOne, classTwo);
		final SampleDeadlockThread threadTwo = new SampleDeadlockThread();
		threadTwo.setName("ThreadTwo");
		threadTwo.sampleMethod(classTwo, classThree);
		final SampleDeadlockThread threadThree = new SampleDeadlockThread();
		threadThree.setName("ThreadThree");
		threadThree.sampleMethod(classThree, classFour);
		final SampleDeadlockThread threadFour = new SampleDeadlockThread();
		threadFour.setName("ThreadFour");
		threadFour.sampleMethod(classFour, classOne);

		try
		{
			threadOne.start();
			Thread.sleep(1000);
			threadTwo.start();
			Thread.sleep(1000);
			threadThree.start();
			Thread.sleep(1000);
			threadFour.start();
			Thread.sleep(1000);
			System.out.println("Main thread go out");
		}
		catch (final InterruptedException e)
		{
			e.printStackTrace();
		}
	}

}
