package com.dk.mentoring.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dk.mentoring.dao.FigureDao;
import com.dk.mentoring.model.Dimension;
import com.dk.mentoring.model.Figure;
import com.dk.mentoring.service.DimensionService;
import com.dk.mentoring.service.FigureService;


@Service
public class FigureSeviceImpl implements FigureService
{
	private static Logger LOGGER = LoggerFactory.getLogger(FigureSeviceImpl.class);
	private static final String FORMAT = "#0.00";
	private static final String CONUS = "conus";
	private static final String SPHERE = "sphere";
	private static final String CUBE = "cube";
	@Inject
	private FigureDao figureDao;
	@Inject
	private DimensionService dimensionService;
	private List<String> list;

	@Override
	public List<Figure> getAllFigures()
	{
		return figureDao.getAll();
	}

	@Override
	public Figure getFigureById(final Long id)
	{
		return figureDao.get(id);
	}

	@Override
	public Figure createFigure(final Figure figure)
	{
		final Dimension newDimension = new Dimension();
		final Dimension dimensions = figure.getDimensions();
		if (null != dimensions)
		{
			newDimension.setHeight(dimensions.getHeight());
			newDimension.setLength(dimensions.getLength());
			newDimension.setWidth(dimensions.getWidth());
			newDimension.setLengthOfCircle(dimensions.getLengthOfCircle());
		}
		final Figure newFigure = new Figure();
		newFigure.setName(figure.getName());
		newDimension.setFigure(newFigure);

		dimensionService.createDimension(newDimension);
		newFigure.setDimensions(newDimension);
		this.updateFigure(newFigure);
		return newFigure;
	}

	@Override
	public Figure updateFigure(final Figure figure)
	{
		return figureDao.update(figure);
	}

	@Override
	public void deleteFigure(final Long id)
	{
		final Figure figure = figureDao.get(id);
		figure.setDimensions(null);
		figureDao.update(figure);
		dimensionService.deleteDimension(id);
		figureDao.delete(id);
	}

	@Override
	public void volume(final Long figureID)
	{
		final Figure figure = figureDao.get(figureID);
		recalculateVolume(figure);

	}

	private void recalculateVolume(final Figure figure)
	{
		final Dimension dimension = figure.getDimensions();
		Double volume = -1d;
		final String nameFigure = figure.getName();
		final Long lengthOfCircle = dimension.getLengthOfCircle();
		final Double radius = lengthOfCircle != null ? lengthOfCircle.doubleValue() / (2 * 3.14) : null;

		if (CUBE.equals(nameFigure))
		{
			volume = dimension.getLength() * dimension.getHeight() * dimension.getWidth();
		}
		else if (SPHERE.equals(nameFigure))
		{
			volume = ((radius * radius * radius) * (4 / 3) * 3.14);
		}
		else if (CONUS.equals(nameFigure))
		{
			final double height = dimension.getHeight();
			volume = (height * 3.14 * radius * radius) / 3;
		}

		final String shortVolume = new DecimalFormat(FORMAT).format(volume);
		volume = Double.valueOf(shortVolume);
		dimension.setVolume(volume);
		dimensionService.updateDimension(dimension);
	}

	@SuppressWarnings("unused")
	public void reader()
	{
		list = new ArrayList<>();
		final File file = new File(
				"D:\\Program Files\\mentor\\mentoringTask2\\servicelayer\\src\\main\\resources\\Task #5 - Data.txt");
		FileInputStream inputStream = null;
		BufferedReader reader = null;
		try
		{
			inputStream = new FileInputStream(file);
			reader = new BufferedReader(new InputStreamReader(inputStream));
			String line = reader.readLine();
			while (null != line)
			{
				list.add(line.substring(0, 3));
				line = reader.readLine();
			}
			LOGGER.info(list.size() + " " + list.toString());
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (null != reader && null != inputStream)
			{
				try
				{
					reader.close();
					inputStream.close();
					list = null;
				}
				catch (final IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	//	@PersistenceContext
	//	protected EntityManager entityManager;

	//	@Override
	//	public String volume(final Long figureID)
	//	{
	//		final Figure figure = entityManager.find(Figure.class, figureID);
	//
	//		final Dimension dimension = figure.getDimensions();
	//		Double volume = -1d;
	//		final String nameFigure = figure.getName();
	//		final Long lengthOfCircle = dimension.getLengthOfCircle();
	//		final Double radius = lengthOfCircle != null ? lengthOfCircle.doubleValue() / (2 * 3.14) : null;
	//		if (CUBE.equals(nameFigure))
	//		{
	//			volume = dimension.getLength() * dimension.getHeight() * dimension.getWidth();
	//		}
	//		else if (SPHERE.equals(nameFigure))
	//		{
	//			volume = ((radius * radius * radius) * (4 / 3) * 3.14);
	//		}
	//		else if (CONUS.equals(nameFigure))
	//		{
	//			final double height = dimension.getHeight();
	//			volume = (height * 3.14 * radius * radius) / 3;
	//		}
	//		final String shortVolume = new DecimalFormat(FORMAT).format(volume);
	//		volume = Double.valueOf(shortVolume);
	//		dimension.setVolume(volume);
	//
	//		entityManager.merge(dimension);
	//		entityManager.flush();
	//    return "redirect:/index";
	//	}
}
