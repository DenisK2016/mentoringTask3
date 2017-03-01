package com.dk.mentoring.deadlock.four;

/**
 *
 */
public class SlowDeadlock
{

	/**
	 * @param args
	 */
	public static void main(final String[] args)
	{
		final SampleSlowClass classOne = new SampleSlowClass();

		final SampleSlowThread threadOne = new SampleSlowThread();
		threadOne.setName("ThreadOne");
		threadOne.sampleMethod(classOne);
		final SampleSlowThread threadTwo = new SampleSlowThread();
		threadTwo.setName("ThreadTwo");
		threadTwo.sampleMethod(classOne);
		final SampleSlowThread threadThree = new SampleSlowThread();
		threadThree.setName("ThreadThree");
		threadThree.sampleMethod(classOne);
		final SampleSlowThread threadFour = new SampleSlowThread();
		threadFour.setName("ThreadFour");
		threadFour.sampleMethod(classOne);
		final SampleSlowThread threadFive = new SampleSlowThread();
		threadFive.setName("ThreadFive");
		threadFive.sampleMethod(classOne);

		try
		{
			threadOne.start();
			Thread.sleep(500);
			threadTwo.start();
			Thread.sleep(500);
			threadThree.start();
			Thread.sleep(500);
			threadFour.start();
			Thread.sleep(500);
			threadFive.start();
			Thread.sleep(500);
			System.out.println("Main thread go out");
		}
		catch (final InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
