package com.dk.mentoring.deadlock.three;

/**
 *
 */
public class SampleDeadlockClass
{

	String nameClass = "";

	/**
	 *
	 */
	public synchronized void foo(final SampleDeadlockClass sampleClass)
	{
		try
		{
			System.out.format("%s start %s => %s\n", Thread.currentThread().getName(), getNameClass(), sampleClass.getNameClass());
			Thread.sleep(2000);
			sampleClass.bow(this);
			System.out.format("%s end %s => %s\n", Thread.currentThread().getName(), getNameClass(), sampleClass.getNameClass());
		}
		catch (final InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	private synchronized void bow(final SampleDeadlockClass sampleClass)
	{
		System.out.format("% never achieves the bow method\n", Thread.currentThread().getName());
	}

	public String getNameClass()
	{
		return nameClass;
	}

	public void setNameClass(final String nameClass)
	{
		this.nameClass = nameClass;
	}


}
