package com.dk.mentoring.readfile.five;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class CopyOfReader
{

	public static void main(final String[] args)
	{

		final Thread thread = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				final List<String> list = new ArrayList<>();
				final File file = new File(
						"D:\\Programs\\Eclipse\\workspace\\mentoring\\mentoring\\custom_sample_project\\servicelayer\\src\\main\\resources\\Task #5 - Data.txt");


				try (FileInputStream inputStream = new FileInputStream(file);
						BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));)
				{
					String line = reader.readLine();
					while (null != line)
					{
						list.add(line.substring(0, 3));
						line = reader.readLine();
					}
				}
				catch (final IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println(list);
			}
		});
		thread.start();

		System.out.println("Main thread off.");
	}
}
