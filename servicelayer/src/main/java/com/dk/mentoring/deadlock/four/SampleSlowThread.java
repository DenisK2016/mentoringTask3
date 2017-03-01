package com.dk.mentoring.deadlock.four;

/**
 *
 */
public class SampleSlowThread extends Thread
{

	SampleSlowClass sampleClassOne;

	@Override
	public void run()
	{
		System.out.format("%s thread start\n", Thread.currentThread().getName());
		sampleClassOne.foo();
		System.out.format("%s go out\n", Thread.currentThread().getName());
	}

	/**
	 * @param one
	 * @param two
	 */
	public void sampleMethod(final SampleSlowClass one)
	{
		this.sampleClassOne = one;
	}

}
