/**
 * Copyright (c) @2014-10-20. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.mapreduce.maxtemperature;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2014-10-20
 * @Version: 1.0
 */
public class MaxTemperatureMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	Log log = LogFactory.getLog(this.getClass());
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		log.info("data: ["+line+"]");
		String year = line.substring(0,4);
		int temp = Integer.parseInt(line.substring(5).trim());
		context.write(new Text(year), new IntWritable(temp));
	}

}
