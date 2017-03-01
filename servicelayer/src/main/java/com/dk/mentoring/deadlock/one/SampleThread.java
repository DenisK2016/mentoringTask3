package com.dk.mentoring.deadlock.one;

/**
 *
 */
public class SampleThread extends Thread
{

	SampleClass sampleClassOne;
	SampleClass sampleClassTwo;

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
	public void sampleMethod(final SampleClass one, final SampleClass two)
	{
		this.sampleClassOne = one;
		this.sampleClassTwo = two;
	}

}
