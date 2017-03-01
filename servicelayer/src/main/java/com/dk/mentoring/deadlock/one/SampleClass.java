package com.dk.mentoring.deadlock.one;

public class SampleClass
{
	/**
	 *
	 */
	public synchronized void foo(final SampleClass sampleClass)
	{
		System.out.format("%s start SampleClass\n", Thread.currentThread().getName());
		sampleClass.bow(this);
		System.out.format("%s end SampleClass\n", Thread.currentThread().getName());
	}

	private synchronized void bow(final SampleClass sampleClass)
	{
		System.out.format("%s never achieves the bow method\n", Thread.currentThread().getName());
	}
}
