package com.dk.mentoring.deadlock.four;

public class SampleSlowClass
{
	/**
	 *
	 */
	public synchronized void foo()
	{
		System.out.format("%s start SampleClass\n", Thread.currentThread().getName());
		this.bow();
		System.out.format("%s end SampleClass\n", Thread.currentThread().getName());
	}

	private synchronized void bow()
	{
		System.out.format("%s achieves the bow method\n", Thread.currentThread().getName());
		while (true)
		{

		}
	}
}
