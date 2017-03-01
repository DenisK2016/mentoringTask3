package com.dk.mentoring.deadlock.three;

/**
 *
 */
public class SampleDeadlockThread extends Thread
{

	SampleDeadlockClass sampleClassOne;
	SampleDeadlockClass sampleClassTwo;

	@Override
	public void run()
	{
		System.out.format("%s thread start\n", Thread.currentThread().getName());
		sampleClassOne.foo(sampleClassTwo);
		System.out.format("%s go out\n", Thread.currentThread().getName());
	}

	/**
	 * @param one
	 * @param two
	 */
	public void sampleMethod(final SampleDeadlockClass one, final SampleDeadlockClass two)
	{
		this.sampleClassOne = one;
		this.sampleClassTwo = two;
	}

}
